package modelclasses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import static modelclasses.StaffList.connect;

/**
 *
 * @author CỌP
 */
public class TransLog extends Staff {

    // Some fields have other Class , example Class Staff have maNV, why here declare . Sao không reference lại???, get value để use.
    private  String maNV;
    private String eventID;
    private String content;
    private Date ngayHieuLuc;
    private String qdSo;
    private String logID;
    public static Connection connect = null;
    //construtor sau khi thừa kế:

    public TransLog(String maNV, String eventID, String content, Date ngayHieuLuc, String qdSo, String logID, String mANV, String ho, String ten, java.util.Date dob, String sex, String CMND, String pW, String status) {
        super(maNV, ho, ten, dob, sex, CMND, pW, status);
        this.maNV = maNV;
        this.eventID = eventID;
        this.content = content;
        this.ngayHieuLuc = ngayHieuLuc;
        this.qdSo = qdSo;
        this.logID = logID;
    }

    //step 1,2: Nạp trình điều khiển and setup connection sql server.
    //constructor not parameter:
    public TransLog() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            connect = DriverManager.getConnection(connectionURL);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
    }

    // constructor all parameter:
    public TransLog(String maNV, String eventID, String content, Date ngayHieuLuc, String qdSo, String logID) {
        this.maNV = maNV;
        this.eventID = eventID;
        this.content = content;
        this.ngayHieuLuc = ngayHieuLuc;
        this.qdSo = qdSo;
        this.logID = logID;
    }

    // Ghi nhật ký nhân sự when have an event: Không có LogID: (tự động tăng), ngày hiệu lực: (default ngày hiện hành), làm tạm thời, khi đến GUI sau đó khi có 1 event là nhật ký tự động cập nhật, cái này tìm hiểu sau
    public TransLog(String maNV, String eventID, String content, String qdSo) {
        this();
        this.maNV = maNV;
        this.eventID = eventID;
        this.content = content;
        this.qdSo = qdSo;
        //
        try {
            PreparedStatement p1;// same Statement biên soạn trước SQL,Nó được sử dụng để thực thi các truy vấn được tham số hóa.
            ResultSet rs; // Cho phép truy xuất các Row của các câu lệnh sql đã thực thi
            String sql = "insert into NhatKy(MaNV, MaSK, NoiDung, QDSo) values (?,?,?,?)";
            p1 = connect.prepareStatement(sql);//
            p1.setString(1, maNV); // số thứ tự là 1 dấu ?
            p1.setString(2, eventID);////Thiết lập tham số đã cho thành giá trị String trong Java đã cung cấp. Driver chuyển đổi giá trị này thành một kiểu VARCHAR hoặc LONGVARCHAR (tùy thuộc vào kích cỡ tham số) khi nó gửi giá trị tới Database. 
            p1.setString(3, content);
            p1.setString(4, qdSo);
            int row = p1.executeUpdate();
            if (row == 0) {
                throw new RuntimeException("Error: ");
            }
        } catch (SQLException ex) {
            System.out.println("Error query when insert a translog: " + ex.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error general: " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println("Error: Đóng kết nối bị lỗi: " + ex.getMessage());
            } catch (Exception e) {
                System.out.println("Lỗi chung đóng kết nối" + e.getMessage());
            }
        }

    }
    //  Ghi nhật ký nhân sự use method not return.

    // get MaNV and TenNV:
    public static Vector<String> StaffListMethodOfTranslog() {
        Vector<String> listnv = new Vector<>(10, 5);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            connect = DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
        try {
            Statement stmt = connect.createStatement();
            String sql = "select * from staff";
            ResultSet rs = stmt.executeQuery(sql);
            listnv.add("Chọn nhân viên:");
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String hoNV = rs.getString("Ho");
                String tenNV = rs.getString("Ten");
                listnv.add(maNV + " " + hoNV + " " + tenNV);
            }
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println("Close connection error: " + ex.getMessage());
            }
        }
        return listnv;
    }
    // 
     public static Vector<Event> getALL() {
        Event e1 = new Event();
        Vector<Event> v = new Vector<Event>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            connect = DriverManager.getConnection(connectionURL);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
        try {
            PreparedStatement stmt = connect.prepareStatement("select MaSK, TenSK from SuKien");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //v.add(new Event(rs.getInt("MaSK")), rs.getString("TenSK"));     
                int a1 = rs.getInt("MaSK");
                String a2 = rs.getString("TenSK");
                e1 = new Event(a1, a2);
                v.add(e1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println("Error: close connection" + ex.getMessage());
            }
        }
        return v;
    }

    // truy vấn nhật ký nhân viên
    public static Vector<Vector> getTransLongOfAStaff(int MaNV) {
        Vector<Vector> list = new Vector<Vector>(10, 5);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            connect = DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
        try {
            String sql = "select NhatKy.LogID, NhatKy.MaNV, Staff.Ho,Staff.Ten ,DSNV.ChucVu,NhatKy.NoiDung, NhatKy.NgayHieuLuc, NhatKy.QDSo from Staff, Departmant,DSNV,NhatKy\n"
                    + "where \n"
                    + "Staff.MaNV = DSNV.MaNV and\n"
                    + "DSNV.MaDV = Departmant.MaDV and\n"
                    + "NhatKy.MaNV = Staff.MaNV and\n"
                    + "NhatKy.MaNV =?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, MaNV);
            ResultSet rs = stmt.executeQuery();
            //ResultSet rs = stmt.executeQuery("select DSNV.MaNV, Staff.Ho, Staff.Ten, DSNV.ChucVu from DSNV inner join Staff on DSNV.MaNV = Staff.MaNV where (DSNV.MaDV=" + MaDV+")");            
            while (rs.next()) {
                Vector v = new Vector(10, 5);
                v.add(rs.getInt("LogID"));
                v.add(rs.getInt("MaNV"));
                v.add(rs.getString("Ho"));
                v.add(rs.getString("Ten"));
                v.add(rs.getString("ChucVu"));
                v.add(rs.getString("NoiDung"));
                v.add(rs.getDate("NgayHieuLuc"));
                v.add(rs.getString("QDSo"));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println("Error close connection: " + ex.getMessage());
            }
        }
        return list;
    }

    // Watch translog a staff: (input staffID).
    public Vector<TransLog> getAllTransLogAStaff(int MaNV) {
        TransLog tl = new TransLog();
        Vector<TransLog> vtl = new Vector<TransLog>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            connect = DriverManager.getConnection(connectionURL);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from NhatKy,Staff,SuKien \n"
                    + "where\n"
                    + "NhatKy.MaSK = SuKien.MaSK and\n"
                    + "NhatKy.MaNV = Staff.MaNV and \n"
                    + "NhatKy.MaNV=" + MaNV);
            while (rs.next()) {
                String a1 = rs.getString("MaNV");
                String a2 = rs.getString("MaSK");
                String a3 = rs.getString("NoiDung");
                Date a4 = rs.getDate("NgayHieuLuc");
                String a5 = rs.getString("QDSo");
                String a14 = rs.getString("MaNV");
                String a6 = rs.getString("LogID");
                String a7 = rs.getString("Ho");
                String a8 = rs.getString("Ten");
                Date a9 = rs.getDate("Dob");
                String a10 = rs.getString("Sex");
                String a11 = rs.getString("CMND");
                String a12 = rs.getString("PW");
                String a13 = rs.getString("Status");
                tl = new TransLog(a1, a2, a3, a4, a5, a6, a14, a7, a8, a9, a10, a11, a12, a13);
                //int maNV, int eventID, String content, Date ngayHieuLuc, String qdSo, int logID, int mANV, String ho, String ten, java.util.Date dob, String sex, int CMND, String pW, String status)
                vtl.add(tl);
                // có thể thừa kế class staff: dislay info của staff nữa. Vì ở đây truyền parameter vào constructor, mà contructor nhiều đó số thì phải thừa kế từ lớp Staff. (ý tưởng)
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println("Error close connection: " + ex.getMessage());
            }
        }
        return vtl;
    }
    // getMaNV, để truyền vào getTransLongOfAStaff method.
    public  Vector<String> getMaNVShow() {
        Vector<String> list = new Vector<String>(10, 5);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            connect = DriverManager.getConnection(connectionURL);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("Select MaNV from Staff");// là MaNV truyền vào paramenter                                    
            while (rs.next()) { // .next() : is methods of interface ResultSet, return boolean | vậy nó để làm gì? Di chuyển con trỏ tới hàng tiếp theo. Phương thức trả về false nếu không có hàng tiếp theo                
                // or agrument is interger, 1 thứ tự column first, 2,3,4 next.
                //maNV=rs.getInt(1); 
                // maNV không lấy ra, cũng được 
                maNV = rs.getString("MaNV");
                list.add(maNV);

            }

        } catch (SQLException ex) {
            System.out.println("Error: create statement bị lỗi: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when create statement and excute sql by ResultSet " + e.getMessage());
        } finally {
            try {
                if (connect != (null)) {
                    connect.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error: when close connect at create statement and excute sql by result");
            } catch (Exception e) {
                System.out.println("Error general: When close connection" + e.getMessage());
            }
        }
        return list;
    }

    // Display a Translog:
    @Override
    public String toString() {
        return " \t" + logID + " \t" + maNV + " \t" + super.getHo() + " \t" + super.getTen() + " \t" + eventID + " \t\t\t" + content + " \t\t" + ngayHieuLuc + " \t" + qdSo;
    }

    // Methods get and set:
//    public void setMaNV(int maNV) {
//        this.maNV = maNV;
//    }
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNgayHieuLuc(Date ngayHieuLuc) {
        this.ngayHieuLuc = ngayHieuLuc;
    }

    public void setQdSo(String qdSo) {
        this.qdSo = qdSo;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

//    public int getMaNV() {
//        return maNV;
//    }
    public String getEventID() {
        return eventID;
    }

    public String getContent() {
        return content;
    }

    public Date getNgayHieuLuc() {
        return ngayHieuLuc;
    }

    public String getQdSo() {
        return qdSo;
    }

    public String getLogID() {
        return logID;
    }

    //demo thử:
    public static void main(String[] args) {
//        System.out.println("1. Ghi nhật ký khi có 1 event: ");
//        Diary d = new Diary(105, 10011, "Ghi nhật ký ", "QD So 7");
//        System.out.println("Check at sql server or getDiary(int LogID), toString(): ");
//        System.out.println("2. Xem nhật ký của 1 nhân viên: ");
//        TransLog tl = new TransLog();
//        Vector a = new Vector();
//        a = tl.getAllTransLogAStaff(105);
//        System.out.println("Danh sách nhật ký của 1 nhân viên có MaNV= 105 bao gồm: ");
////        for(Object elements: a){ // element = item, phần tử.
////            System.out.println(elements);
////        }
//        for(int i=0; i< a.size();i++){
//            System.out.println(a.get(i));
//        }
        /*
        Result:
        2. Xem nhật ký của 1 nhân viên: 
        Danh sách nhật ký của 1 nhân viên có MaNV= 105(Trần Hồng Đan) bao gồm: 
        //return " \t" + maNV+ " \t" +getHo()+ " \t" +getTen()  + " \t" + eventID+ " \t\t\t" + content + " \t\t" + ngayHieuLuc + " \t" + qdSo + " \t" + logID;
        2. Xem nhật ký của 1 nhân viên: 
        Danh sách nhật ký của 1 nhân viên có MaNV= 105 bao gồm: 
        MãNK    MaNV    Ho      Tên             MaSK                    Nộidung                                         ngày hiệu lực   QD số
 	100011 	105 	Trần 	Hồng Dan 	10011 			Quản lý nhật xuất nhân viên bảng công 		2017-03-19 	QD So 6
 	100012 	105 	Trần 	Hồng Dan 	10011 			Quản lý nhật xuất nhân viên bảng công 		null            QD So 6
 	100013 	105 	Trần 	Hồng Dan 	10011 			Quản lý nhật xuất nhân viên bảng công 		2017-09-18 	QD So 6
 	100014 	105 	Trần 	Hồng Dan 	10011 			Ghi nhật ký                                     2017-09-19 	QD So 7
 	100015 	105 	Trần 	Hồng Dan 	10011 			Ghi nhật ký                                     2017-09-19 	QD So 7
         */
        // Ý tưởng: thêm biến, xong lấy biến đó sử dụng, 
        //cách 2: thừa kế, getbien() sử dụng??? tối ưu?. Thầy bảo em cứ get ra sử dụng. mà get ra thì private. 
        /*
        Điều rút ra:
        1. Có thể hàm vẫn chưa tối ưu(2 tác vụ sau).
        2. Khi học về GUI interface, thì sẽ thay đổi đúng. theo form frame của thầy
        3. thắc mắc 2 cách lấy khi inner join, ( trên bài đã dùng kiểu cũ), inner join > 3 tables đang tìm hiểu.
        4. Tìm hiểu GUI, listener.       
         */
 /*    
        Vector<String> v = new Vector<>();
        v = TransLog.StaffListMethodOfTranslog();
        for (int i = 0; i < v.size(); i++) {
            System.out.println(v.get(i));
        }
         */
//        Vector<Vector> v2 = TransLog.getTransLongOfAStaff(105);
//        for (int i = 0; i < v2.size(); i++) {
//            System.out.println(v2.get(i));
//        }
        //
//        TransLog tr = new TransLog();
//        Vector<String> v= new Vector<String>();
//        v=TransLog.getMaNVShow();
//        for(int i=0; i<v.size(); i++){
//            System.out.println(v.get(i));
//        }

    }
}
