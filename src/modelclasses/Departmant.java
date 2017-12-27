package modelclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import static modelclasses.Staff.connect;

/**
 *
 * @author CỌP
 */
public class Departmant {

    private String maDV;
    private String tenDV;
    private String tel;
    private String email;
    public static Connection connect = null;

    // step 1,2:  Nạp trình điều khiển và setup connection  
    public Departmant() {
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
    }

    // constructor 4 parameter:
    public Departmant(String maDV, String tenDV, String tel, String email) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.tel = tel;
        this.email = email;
    }

    //step 3, 4, 5, 6: Create statement and excute sql by Resutset methods constructor a parameter, read database, close connection:
    public Departmant(int MaDV) {
        this();
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select MaDV, TenDV, Tel, Email from Departmant where MaDV=" + MaDV);
            if (rs.next() == true) { // can use while instance of if statement
                maDV = rs.getString("MaDV");
                tenDV = rs.getString("TenDV");
                tel = rs.getString("Tel");
                email = rs.getString("Email");
            }
        } catch (SQLException ex) {
            System.out.println("Error: not create Statement: " + ex.getMessage());
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
                System.out.println("Error General: When close connection");
            }
        }
    }

    // Insert a new department:
    public void InsertDepartment(String tenDV, String tel, String email) {// 'cuz MaDV identity nên not to paramenter.
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
        //insert:       
        try {
            PreparedStatement p1; // is a interface extends Statement, same Statement biên soạn trước SQL
            ResultSet rs;
            String sql = "insert into Departmant(TenDV, Tel, Email) values (?,?,?)";
            p1 = connect.prepareStatement(sql); // is a methods of I Connection, return a PreparedStatement: dùng để thực thi query sql            
            p1.setString(1, tenDV);//Thiết lập tham số đã cho thành giá trị String trong Java đã cung cấp. Driver chuyển đổi giá trị này thành một kiểu VARCHAR hoặc LONGVARCHAR (tùy thuộc vào kích cỡ tham số) khi nó gửi giá trị tới Database. 
            p1.setString(2, tel);
            p1.setString(3, email);
            int row = p1.executeUpdate(); // is a methods of I PreparedStatement, return int, Thực thi lệnh SQL đã cho, có thể là INSERT, UPDATE, DELETE hoặc một lệnh SQL 
        } catch (SQLException ex) {
            System.out.println("Error prepareStatement and set thiết lập tham số trong Java đã cung cấp, driver chuyển to database: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: When thực hiện insert a departement" + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println("Error: Đóng kết nối bị lỗi: " + ex.getMessage());
            } catch (Exception e) {
                System.out.println("Error general close connection" + e.getMessage());
            }
        }
    }

    /*
    // lấy lấy về tên đơn vị khi chọn 2 combobox, danh sách nhân viên trong 1 đv:
    public static Vector<String> DepartmantListMethod() {
        Vector<String> listdv = new Vector<>(10, 5);
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
            while (rs.next()) {
                int maDV = rs.getInt("MaDV");
                String tenDV = rs.getString("TenDV");
                listdv.add(maDV+" "+tenDV);
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
        return listdv;
    }
     */
    // lấy về deparmantlist khi chọn 2 combobox, danh sách nhân viên trong 1 đv:
    public Vector<Departmant> getModelDonVi(String MaDV) {
        Vector<Departmant> departmantall = new Vector<Departmant>();
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
            ResultSet rs = stmt.executeQuery("Select * from Departmant where MaDV=" + MaDV);

            while (rs.next()) {
                String maDV = rs.getString("MaDV");
                String tenDV = rs.getString("TenDV");
                String tel = rs.getString("Tel");
                String email = rs.getString("Email");

                Departmant dv1 = new Departmant(maDV, tenDV, tel, email);
                departmantall.add(dv1);
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
        return departmantall;
    }

    /*    
    1. Get MaDV va TenDV vào combobox khi xem nhân viên trong 1 đơn vị:(DefaultComboboxModel)
    2. Get records vào table khi xem nhân viên trong 1 đợn vị:(DefaultTableModel)
     */
    //1. Get MaDV va TenDV vào combobox khi xem nhân viên trong 1 đơn vị:
    public static Vector<Departmant> getMaDVandTenDV() {
        Departmant d = new Departmant();
        Vector<Departmant> v = new Vector<Departmant>();
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
            PreparedStatement stmt = connect.prepareStatement("SELECT MaDV, TenDV\n"
                    + "FROM Departmant");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String a1 = rs.getString("MaDV");
                String a2 = rs.getString("TenDV");
                d = new Departmant(a1, a2);
                v.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy MaDV and TenDV: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lỗi chung khi lấy MaDV and TenDV: " + e.getMessage());
        } finally {
            try {
                connect.close();
            } catch (Exception e) {
                System.out.println("Đóng kết nối bị lỗi: " + e.getMessage());
            }
        }
        return v;
    }

    // constructor hổ trợ cho getMaDVandTenDV cùng với toString:
    public Departmant(String MaDV, String TenDV) {
        this.maDV = MaDV;
        this.tenDV = TenDV;
    }

    public String toString() {
        return "Mã đơn vị: " + maDV + " Tên đơn vị: " + tenDV;
    }

//
//    // Display a departmant:
//    @Override
//    public String toString() {
//        return " \t" + maDV + " \t" + tenDV + " \t" + tel + " \t" + email;
//    }
    
    
    // Các methods get and set:
    public String getMaDV() {
        return maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public Connection getConnect() {
        return connect;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    // Demo thử:
    public static void main(String[] args) {
        /*
        // get a departmant:
        System.out.println("1. Get a department: ");
        Departmant d = new Departmant(1000);
        String result = d.toString();
        System.out.println("A department after when get from databse: ");
        System.out.println(result);
        // insert a departmant:
        System.out.println("2. Insert a department: ");
        Departmant d3 = new Departmant();
        d3.InsertDepartment("Khoa Hóa", "0234-3123431", "khoahoa@gmail.com");
        Departmant d4 = new Departmant();
        d4.InsertDepartment("Khoa Kiến Trúc", "0983341343", "khoakientruc@gmail.com");
        System.out.println("Xem thay đổi tại database.");
        // Exception: Can not insert TenDV null? why?, mình truyền đối số đúng mà ? haizz => à à tại vì parameter truyền vào là biến (fileds), phải cùng tên, có phân biệt hoa thường.(tốn mấy lần mã tự tăng, thiệt là)
        //d2.InsertDepartment(result, result, result);
        //
         
        Departmant d5 = new Departmant();
        Vector<Departmant> v = d5.getModelDonVi("1001");
        for (int i = 0; i < v.size(); i++) {
            System.out.println(v.get(i));
        }
        */
        
        

    }
}
