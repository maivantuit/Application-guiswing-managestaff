package modelclasses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static modelclasses.Departmant.connect;
import static modelclasses.Staff.connect;
/**
 *
 * @author CỌP
 */
public class StaffList {

    private String maNV;
    private String maDV;
    private String chucVu;
    private Date capNhat;
    private String ghiChu;
    static Connection connect = null;
    //constructor when inheritance:

    // Constructor not parameter:
    public StaffList() {
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
    //

    //get MaDV and TenDV
    public static Vector<String> StaffListMethod() {
        Vector<String> list = new Vector<>(10, 5);
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
            list.add("Chọn mã đơn vị:");
            while (rs.next()) {
                int maDV = rs.getInt("MaDV");
                list.add("" + maDV);
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

    //
    // Watch staff list of a department 2:
    public static Vector<Vector> getRecordStaffList(int MaDV) {
        Vector<Vector> ds = new Vector<Vector>(10, 5);
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
            PreparedStatement stmt = connect.prepareStatement("select DSNV.MaNV,DSNV.MaDV, Staff.Ho, Staff.Ten, DSNV.ChucVu from DSNV inner join Staff on DSNV.MaNV = Staff.MaNV where (DSNV.MaDV=?)");
            stmt.setInt(1, MaDV);
            ResultSet rs = stmt.executeQuery();
            //ResultSet rs = stmt.executeQuery("select DSNV.MaNV, Staff.Ho, Staff.Ten, DSNV.ChucVu from DSNV inner join Staff on DSNV.MaNV = Staff.MaNV where (DSNV.MaDV=" + MaDV+")");            
            while (rs.next()) {
                Vector v = new Vector(10, 5);
                v.add(rs.getInt("MaNV"));
                v.add(rs.getString("Ho"));
                v.add(rs.getString("Ten"));
                v.add(rs.getString("ChucVu"));
                v.add(rs.getString("MaDV"));
                ds.add(v);
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
        return ds;
    }

//    // Dislay danh sách nhân viên trong một đơn vị:
//    @Override
//    public String toString() {
//        return " \t" + maDV + " \t" + getMaNV() + " \t" + getHo() + " \t" + getTen() + " \t" + chucVu + " \t" + capNhat + " \t" + ghiChu + " \t\t" + getDob() + " \t" + getSex() + " \t" + getCMND() + " \t" + getpW() + " \t\t" + getStatus();
//    }
    //getMaNV(): methods get này là của class StaffList, còn thừa kế lại (mANV). biến đó vẫn được sử dụng nhưng không in ra.
    // contructor all prameter:
    public StaffList(String maNV, String maDV, String chucVu, Date capNhat, String ghiChu) {
        this.maNV = maNV;
        this.maDV = maDV;
        this.chucVu = chucVu;
        this.capNhat = capNhat;
        this.ghiChu = ghiChu;
    }

    /* thêm nhân viên mới: */
    //1: thêm vào table Staff:
    public static String InsertInTableStaff(String ho, String ten, String dob, String sex, String cmnd, String pw, String status, int madv, String chucvu, String ghichu) {
        String str = new String();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            try {
                connect = DriverManager.getConnection(connectionURL);
            } catch (SQLException ex) {
                System.out.println("Error: DriverManager get connection from Database cụ thể (SQL Server): " + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: Not find trình điều khiển driver");
        } catch (Exception e) {
            System.out.println("Error General: " + e.getMessage());
        }
        // chuyển String to Date, để nhập chuổi
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsed = d.parse(dob);
            java.sql.Date sq = new java.sql.Date(parsed.getTime());
            // insert:
            try {
                // same Statement biên soạn trước SQL,Nó được sử dụng để thực thi các truy vấn được tham số hóa.
                // Cho phép truy xuất các Row của các câu lệnh sql đã thực thi                
                System.out.println("Chuỗi 3:");
                PreparedStatement p1 = connect.prepareStatement("insert into Staff(Ho,Ten,Dob,Sex,CMND,PW,Status) "
                        + "values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);//                
                p1.setString(1, ho); // số thứ tự là 1 dấu ?
                p1.setString(2, ten);////Thiết lập tham số đã cho thành giá trị String trong Java đã cung cấp. Driver chuyển đổi giá trị này thành một kiểu VARCHAR hoặc LONGVARCHAR (tùy thuộc vào kích cỡ tham số) khi nó gửi giá trị tới Database. 
                p1.setDate(3, sq);
                p1.setString(4, sex);
                p1.setString(5, cmnd);
                p1.setString(6, pw);
                p1.setString(7, status);                                
                p1.executeUpdate();
                ResultSet rs = p1.getGeneratedKeys();// bị lỗi ở đây, how to? :(                              
                //int row = p1.executeUpdate();
                int manvcuoi;
                if (rs.next()) {                    
                    manvcuoi = rs.getInt(1);                    
                    String v = StaffList.InsertInTableDSNV(manvcuoi, madv, chucvu, ghichu);                    
                    str = v + "* Mã nhân viên: " + manvcuoi;                    
                }
                p1.close();
            } catch (SQLException ex) {                
                System.out.println("Error query when insert a staff ^: " + ex.getMessage());
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
        return str;
    }

    //2: thêm vào table DSNV:
    public static String InsertInTableDSNV(int manv, int madv, String chucvu, String ghichu) {
        String str = new String();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhanSu;user=sa;password=123";
            try {
                connect = DriverManager.getConnection(connectionURL);
            } catch (SQLException ex) {
                System.out.println("Error: DriverManager get connection from Database cụ thể (SQL Server): " + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: Not find trình điều khiển driver");
        } catch (Exception e) {
            System.out.println("Error General: " + e.getMessage());
        }
        try {
            PreparedStatement stmt = connect.prepareStatement("Insert into DSNV(MaNV, MaDV, ChucVu, GhiChu) values (?,?,?,?)");
            stmt.setInt(1, manv);
            stmt.setInt(2, madv);
            stmt.setString(3, chucvu);
            System.out.println("Chuooi4:");
            stmt.setString(4, ghichu);
            int rows = stmt.executeUpdate();
            if (rows != 0) {
                str = "Chèn thành công";
            } else {
                str = "Chèn bị lỗi";
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn 1 nhân viên vào table DSNV: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi chung khi chèn 1 nhân viên vào table DSNV: " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
        return str;
    }
     

    // Methods get and set:
    // vì trên đó hàm getMaNV(){}: sử dụng lại thừa kế nên methods get này comment lại
    public String getMaNV() {
        return maNV;
    }

    public String getMaDV() {
        return maDV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public Date getCapNhat() {
        return capNhat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public void setCapNhat(Date capNhat) {
        this.capNhat = capNhat;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public static void main(String[] args) {
        /*
        System.out.println("1. Xem danh sách nhân viên trong một đơn vị cụ thể: ");
        StaffList sl = new StaffList();
        Vector a = new Vector();
        //a = sl.getAllStaffListADeparmant(1001);
        System.out.println("StaffList in a department MaDV=1001 include: ");
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
        Vector<String> v2 = new Vector<String>();
        v2=StaffList.StaffListMethod();
        for(int i=0; i<v2.size();i++){
            System.out.println(v2.get(i));            
        }
         */

 /*
        Result:
        StaffList in a department MaDV=1001 include: 
 	1. Xem danh sách nhân viên trong một đơn vị cụ thể: 
        StaffList in a department MaDV='1001' include: 
        // use when inheritance
 	1001 	101 	Vũ Xuân Quỳnh 	Phó Trưởng phòng    2017-09-19 	null 		1997-02-20 	F 	193412341 	000000                  Nhân viên đi làm
 	1001 	102 	Trần 	Thế 	Nhân viên           2017-09-19 	null 		1996-09-20 	M 	194031324 	1111111 		Nhân viên nghĩ hưu
 	1001 	108 	Anh 	Tú 	Nhân viên           2017-09-23 	null 		1995-03-30 	M 	193123312 	123412321 		Nhân viên đi làm       
            -null: not insert value.
         */
        //StaffList.InsertInTableDSNV(0, 0, chucvu, ghichu)
        //StaffList.InsertInTableDSNV(132, 1001, "Nhân viên quản lý 2", "Nhân viên ql đc 2 tháng");
        StaffList.InsertInTableStaff("Mai", "TuB", "30/03/1995", "M", "427321413", "123", "Nhan vien thuc tap",1001, "Nhân viên", "Nhân viên mới thực tập 4 tháng");
    }

}
