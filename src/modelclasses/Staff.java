package modelclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CỌP
 */
public class Staff {

    private String maNV; // change passowrd  methods is static. vì vậy k convert được. non-static
    private String ho;
    private String ten;
    private Date dob;
    private String sex;
    private String CMND;
    private String pW;
    private String status;// Khai báo các fields cụ thể và thứ tự như vậy để khi sử dụng lên quan đến constructor nó theo thứ tự khỏi nhầm.
    static Connection connect = null;// tại sao bỏ in constructor not para lại lỗi?, để gọi bên bước 3, 4?. nếu bỏ in hàm dựng đó, nó là thuộc tính cục bộ.^^
    //step 1, 2: Nạp trình điều khiển driver và setup (thiết lập) connection database in constructor not parameter.

    public Staff() {
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
    }

    public Staff(String maNV, String ho, String ten, Date dob, String sex, String CMND, String pW, String status) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.dob = dob;
        this.sex = sex;
        this.CMND = CMND;
        this.pW = pW;
        this.status = status;
    }

    ///step 3,4 :Create statement and excute sql by ResultSet in constructor 1 parameter | lấy dữ liệu từ databse QuanLyNhanSu by parameter MaNV, query select.
    //Viết trong hàm dựng 1 đối số
    public Staff(int MaNV) {
        this();// it call lại method contructor not parameter.               
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("Select MaNV, Ho, Ten, Dob, Sex, CMND, PW, Status from Staff where MaNV=" + MaNV);// là MaNV truyền vào paramenter                                    
            if (rs.next()) { // .next() : is methods of interface ResultSet, return boolean | vậy nó để làm gì? Di chuyển con trỏ tới hàng tiếp theo. Phương thức trả về false nếu không có hàng tiếp theo                
                // or agrument is interger, 1 thứ tự column first, 2,3,4 next.
                //maNV=rs.getInt(1); 
                // maNV không lấy ra, cũng được 
                maNV = rs.getString("MaNV");
                ho = rs.getString("Ho");
                ten = rs.getString("Ten");
                dob = rs.getDate("Dob");
                sex = rs.getString("Sex");
                CMND = rs.getString("CMND");
                pW = rs.getString("PW");
                status = rs.getString("Status");
                //display information any Staff.
                //System.out.print(" \t" + maNV + " \t" + ho + " \t" + ten + " \t" + dob + " \t" + sex + " \t" + CMND + " \t" + pW + " \t" + status+"\n"); // to excute then delete.     
                //System.out.printf("%-10s %-15s %-15s %-15s %-1s %-20s %-20s %-20s", "MaNV", "Họ", "Tên", "Dob","Sex","CMND","PW","Status\n");
                //System.out.printf("\n %-10d %-15s %-15s %-15s %-1s %-20d %-20s %-20s ", maNV, ho, ten, dob, sex, CMND, pW, status);
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
    }

    // lấy mã đv và tên đơn vị:
    public static Vector<String> StaffListMethod() {
        Vector<String> list = new Vector<String>(10, 5);
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
            ResultSet rs = stmt.executeQuery("Select * from Departmant");
            list.add("Choice departmant: ");
            while (rs.next()) {
                int maDV = rs.getInt("MaDV");
                String tenDV = rs.getString("TenDV");
                list.add(maDV + " " + tenDV);
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
        return list;
    }

    // get MaNV
    public String getMaNVShow(String CMND) {
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
            ResultSet rs = stmt.executeQuery("Select MaNV from Staff where CMND=" + CMND);// là MaNV truyền vào paramenter                                    
            if (rs.next()) { // .next() : is methods of interface ResultSet, return boolean | vậy nó để làm gì? Di chuyển con trỏ tới hàng tiếp theo. Phương thức trả về false nếu không có hàng tiếp theo                
                // or agrument is interger, 1 thứ tự column first, 2,3,4 next.
                //maNV=rs.getInt(1); 
                // maNV không lấy ra, cũng được 
                maNV = rs.getString("MaNV");
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
        return maNV;
    }

    /*
    1. get MaNV - TenNV, để xem nhật ký của nhân viên đó:(this methods and toString(){})
    2. toString() and constructor 2 parameter. (note: in 1 class, only 1 toString())
     */
    //1. get MaNV - TenNV, để xem nhật ký của nhân viên đó:
    public static Vector<Staff> getMaNVandTenNV() {
        Staff s = new Staff();
        Vector<Staff> v = new Vector<Staff>();
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
            PreparedStatement stmt = connect.prepareStatement("SELECT MaNV, Ho, Ten FROM Staff");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String a1 = rs.getString("MaNV");
                String a2 = rs.getString("Ho");
                String a3 = rs.getString("Ten");
                s = new Staff(a1, a2, a3);
                v.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi khi get manv, ho and tên: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi chung khi get manv, ho and tên: " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println("Lỗi đóng kết nối: " + ex.getMessage());
            }
        }
        return v;
    }

    //2. toString and constructor liên quan:
    public Staff(String MaNV, String Ho, String Ten) {
        this.maNV = MaNV;
        this.ho = Ho;
        this.ten = Ten;
    }

    public String toString() {
        return "Mã nhân viên: " + maNV + " Tên nhân viên: " + ho + " " + ten;
    }

    // methods xem info a staff 2
    public Staff getStaff(String MaNV) {
        Staff st = new Staff();
        st = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            Connection connect = DriverManager.getConnection(connectionURL);
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("Select MaNV, Ho, Ten, Dob, Sex, CMND, PW, Status from Staff where MaNV=" + MaNV);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
        return null;
    }

//    // Display infomation a staff,if use ToString else write methods is: public Staff getStaff(int MaNV){ retrun new Staff(more parameter of constructor )}
//    @Override
//    public String toString() { // khác ToString() nhé  bị lỗi hoài ^^
//        return " \t" + maNV + " \t" + ho + " \t" + ten + " \t" + dob + " \t" + sex + " \t" + CMND + " \t" + pW + " \t" + status + " ";
//    }
    // Change password methods:
    public void ChangePassword(String MaNV, String newPW) {//static hàm dùng chung, gọi thông qua tên lớp. k cần new instance. (object)
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            Connection connect = DriverManager.getConnection(connectionURL);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
        try {
            PreparedStatement ps = connect.prepareStatement("update Staff set PW=? where MaNV=" + MaNV);
            ps.setString(1, newPW); // 1 tương đương ? fisrt.            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: Quá trình update bị lỗi: " + e.getMessage());
        }
    }

    // Login mehtods:
    /*
         là methods đăng nhập ngoài hệ thống, static
         id: MaNV
         password: PW
         -   kiểm tra trong database 2 dữ liệu đó có đúng k.(use string methods: equals(): so sánh string phân biệt hoa thường, equalsIgnoreCase: so sánh string not phân biệt hoa thường) nếu đúng login success else display login thất bại.
         -   hàm dùng chung (static).
     */
    public Staff Login(int MaNV, String PW) { // stactic?
        Staff s4 = new Staff(MaNV);
//        if(s4.equals(maNV) ==true && s4.equals(pW) == true){
//            System.out.println("Login success");
//        }else{
//            System.out.println("Login faild");
//        }
        if (s4.pW.equalsIgnoreCase(PW) == true) {
            //System.out.println("\nLogin success"); // to excute then delete ^^
            return s4;
        } else {
            // System.out.println("\nLogin faild"); //to excute then delete ^^
            return null;
        }
    }

    //
    // Watch staff list of a department: Đang demo.
    public Vector<Vector> getAll(int MaDV) {
//        StaffList sl = new StaffList();
        Vector<Vector> vsl = new Vector<Vector>();
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
            PreparedStatement stmt = connect.prepareStatement("select * from DSNV, Staff,Departmant\\n\"\n"
                    + "//                    + \"where DSNV.MaNV = Staff.MaNV \\n\"\n"
                    + "//                    + \"and	DSNV.MaDV = Departmant.MaDV		\\n\"\n"
                    + "//                    + \"and DSNV.MaDV =?\"" + MaDV);
            stmt.setInt(1, MaDV);
            ResultSet rs = stmt.executeQuery();

            //Statement stmt = connect.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from DSNV, Staff,Departmant\n"
//                    + "where DSNV.MaNV = Staff.MaNV \n"
//                    + "and	DSNV.MaDV = Departmant.MaDV		\n"
//                    + "and DSNV.MaDV =" + MaDV);
            while (rs.next()) {
                int a1 = rs.getInt("MaNV");
                int a2 = rs.getInt("MaDV");
                String a3 = rs.getString("ChucVu");
                java.sql.Date a4 = rs.getDate("CapNhat");
                String a5 = rs.getString("GhiChu");
                int a13 = rs.getInt("MaNV");
                String a6 = rs.getString("Ho");
                String a7 = rs.getString("Ten");
                java.sql.Date a8 = rs.getDate("Dob");
                String a9 = rs.getString("Sex");
                int a10 = rs.getInt("CMND");
                String a11 = rs.getString("PW");
                String a12 = rs.getString("Status");
                //String ho, String ten, java.util.Date dob, String sex, int CMND, String pW, String status
                // sl = new StaffList(a1, a2, a3, a4, a5, a13, a6, a7, a8, a9, a10, a11, a12); // not this, null all.
                //vsl.add(sl);
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
        return null;//s return 
    }

    // Update infor staff:
    public void UpdateStaff(String ho, String ten, String dob, String sex, String CMND, String status) {
        //update Staff set CMND='193032314', PW='123456' where MaNV='105'   
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            Connection connect = DriverManager.getConnection(connectionURL);
            PreparedStatement ps = connect.prepareStatement("update Staff set Ho=?, Ten=?, Dob=?, Sex=?,CMND=?, Status=? where MaNV=?");
            System.out.println("A1");
            // chuyển String to Date, để nhập chuổi
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsed = null;
            try {
                parsed = d.parse(dob);
            } catch (ParseException ex) {
                System.out.println("Error: chuyển data type String to Date bị lỗi" + ex.getMessage());
            } catch (Exception e) {
                System.out.println("Error general: Chuyển type Sring to date" + e.getMessage());
            }
            java.sql.Date sq = new java.sql.Date(parsed.getTime());
            //st.setString(1, textArea_Code.getText()); // ItemCode is fourth in the SQL, should be 4 (lỗi đúng: xóa static maNV. update nhân viên k đc.)
            ps.setString(1, ho); // 1 tương đương ? fisrt.
            ps.setString(2, ten);
            ps.setDate(3, sq);
            ps.setString(4, sex);
            System.out.println("Â");
            ps.setString(5, CMND);
            ps.setString(6, status);
            ps.setString(7, maNV);
            System.out.println("B");
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: not found class when nạp trình điều khiển đăng ký driver " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: getconnection from databse by drivermanager" + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general, when nạp trình điều khiển and setup connection: " + e.getMessage());
        }
    }

    //Insert a new staff:
    public void InsertStaff(String ho, String ten, String dob, String sex, String CMND, String pW, String status) { // data type of dob is String nhé, để dễ chuyển qua date. còn ngược lại thì tìm hiểu tiếp.
        //Tại sao chổ này không gọi là get driver and get connection mà viết lại dài như thế này?
        // Nạp trình điều khiển driver and setup connection:
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
        // chuyển String to Date, để nhập chuổi
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsed = d.parse(dob);
            java.sql.Date sq = new java.sql.Date(parsed.getTime());

            // insert:
            try {
                PreparedStatement p1;// same Statement biên soạn trước SQL,Nó được sử dụng để thực thi các truy vấn được tham số hóa.
                ResultSet rs; // Cho phép truy xuất các Row của các câu lệnh sql đã thực thi
                String sql = "insert into Staff(Ho, Ten,Dob, Sex, CMND,PW,Status) values (?, ?, ?,?,?,?,?)";
                p1 = connect.prepareStatement(sql);//
                p1.setString(1, ho); // số thứ tự là 1 dấu ?
                p1.setString(2, ten);////Thiết lập tham số đã cho thành giá trị String trong Java đã cung cấp. Driver chuyển đổi giá trị này thành một kiểu VARCHAR hoặc LONGVARCHAR (tùy thuộc vào kích cỡ tham số) khi nó gửi giá trị tới Database. 
                p1.setDate(3, sq);
                p1.setString(4, sex);
                p1.setString(5, CMND);
                p1.setString(6, pW);
                p1.setString(7, status);
                int row = p1.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error query when insert a staff: " + ex.getMessage());
            } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Lỗi chung khi chèn 1 nhân viên: " + e.getMessage());
        }
    }

    //Delete a staff: delete not, because constraint  foregin key with other tables.
    public void DeleteStaff(int MaNV) {
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
            PreparedStatement stmt = connect.prepareStatement("delete from Staff where MaNV=?");
            stmt.setInt(1, MaNV);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: SQLException excute query delete: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }
    }

 

    // Các methods get and set. (C# property, java methods?)
    public String getMaNV() {
        return maNV;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public Date getDob() {
        return dob;
    }

    public String getSex() {
        return sex;
    }

    public String getCMND() {
        return CMND;
    }

    public String getpW() {
        return pW;
    }

    public String getStatus() {
        return status;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setpW(String pW) {
        this.pW = pW;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Main demo thử:
    public static void main(String[] args) {
//        System.out.println("Mỗi lần thay đổi, data thay đổi liên tục. comment to excute each them");
//        // get info a staff:
//        System.out.println("1. Infomation a staff: ");
//        Staff s1 = new Staff(100); // to agrument of constructor 1 parameter.
//        System.out.println(s1.toString());
//        // excute change password methods:
//        System.out.println("\n2. Change password: ");
//        Staff s2 = new Staff(101);
//        //s2.ChangePassword("999");// methods not static used
////        Staff.ChangePassword("000000");
////        System.out.println(s2.toString());
////        Staff s3 = new Staff(102);
////        Staff.ChangePassword("1111111");
//        // Excute Login:          
//        System.out.println("\n3. Excute login: ");
//        System.out.println("Login đúng mật khẩu:");
////        Staff.Login(100, "30031821");
////        System.out.println("Login sai mật khẩu: ");
////        Staff.Login(100, "30031000");
//        //Insert a staff:
//        System.out.println("4. Insert a staff");
//        Staff s5 = new Staff();
//        s5.InsertStaff("Trần", "Toàn", "30/03/1995", "M", "312342123", "123456", "Nhân viên làm việc");// tại sao không inset date được, chuyển nó từ date sang string như thế nào? hay nguoc lai
//        //s5.InsertStaff(ho, ten, dob, sex, maNV, pW, status);// ? maNV not is CMND?
//        Staff s7 = new Staff();
//        s7.InsertStaff("Quân", "Tú", "12/3/1997", "M", "132342123", "1234567", "Nhân viên đi làm");
//        System.out.println("A staff sau khi insert:");
//        Staff s6 = new Staff(108);
//        System.out.println(s6.toString());
//
//        System.out.println("Xem result thay đổi dữ liệu result tại database");
//        System.out.println("Vì bản ghi này có CMND là duy nhất, chèn bản ghi khác CMND ");
//        //(int maNV, String ho, String ten, Date dob, String sex, int CMND, String pW, String status)
////        Staff s8 = new Staff(108);
////        s8.UpdateStaff("Anh", "Tú", "30/03/1995", "M", 193123312, "123412321", "Nhân viên đi làm");
////        System.out.println("Check at sql server");
//        // delete a staff:
//        System.out.println("Xoa khong duoc, bởi vi bị ràng buộc khóa ngoại với bảng khác.");
//        Staff s9 = new Staff(); // xoa khong duoc, boi vi bị ràng buộc khóa ngoại với bảng khác.
//        s9.DeleteStaff(104);
//        Staff s10 = new Staff();
//        s10.UpdateStaff("Mai", "Tu2", "30/03/1995", "Nam", "123123545", "nhân viên");


    }
}
/*
    // tìm nguồn code: Navigate => Go to Soure => Find Code.
    Tóm  tắt:
    Class Staff
        - private int maNV;
        - private String ho;
        - private String ten;
        - private Date dob;
        - private String sex;
        - private int CMND;
        - private String pW;
        - private String status
        + public Staff() {}// Nạp trình đăng ký driver and setup connection.
        + public Staff(int maNV, String ho, String ten, Date dob, String sex, int CMND, String pW, String status) {} 
        + public Staff(int MaNV) {}// create statement and excute query sql by resultset 
        + public static  String ChangePassword(String newPW) { return newPW} // changepassword
        + public static Staff Login(int MaNV, String PW){return new Staff} 
        + public void InsertStaff(String ho, String ten, String dob, String sex, int CMND, String pW, String status) {}
 */
/// cọp, how to?
//cọp nè
