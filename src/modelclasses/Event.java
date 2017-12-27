package modelclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CỌP
 */
public class Event {

    private int eventID;
    private String nameEvent;
    private String statusEvent;
    static Connection connect = null;

    //Step 1,2: Nạp trình điều khiển and setup connection:    
    public Event() {
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

    //Step 3,4: Create statement and excute sql, get database and close connection:
    public Event(int MaSK) {
        this();
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select MaSK, TenSK, MoTa from SuKien where MaSK=" + MaSK);
            if (rs.next() == true) {
                eventID = rs.getInt("MaSK");
                nameEvent = rs.getString("TenSK");
                statusEvent = rs.getString("MoTa");
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

    // return vetor event gồm 2 fileds is MaSK, TenSK
    public static Vector<Event> getALL() {
        Event e1= new Event();
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
                int a1= rs.getInt("MaSK");
                String a2 = rs.getString("TenSK");
                e1= new Event(a1,a2);
                v.add(e1);
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }finally{
            try {
                connect.close();
            } catch (SQLException ex) {
                System.out.println("Error: close connection"+ex.getMessage());
            }
        }
        return v;
    }

    public Event(int MaSK, String TenSK) {
        this.eventID=MaSK;
        this.nameEvent=TenSK;
    }
    //
    @Override
    public String toString() {
        return "Mã sự kiện:  " + eventID + " Tên sự kiện: " + nameEvent;
    }
    
    //dislay a event:
//    @Override
//    public String toString() {
//        return "\t " + eventID + "\t " + nameEvent + "\t " + statusEvent;
//    }

    // constructor four parameter:
    public Event(int eventID, String nameEvent, String statusEvent) {
        this.eventID = eventID;
        this.nameEvent = nameEvent;
        this.statusEvent = statusEvent;
    }
    // Methods get and set:

    public int getEventID() {
        return eventID;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public String getStatusEvent() {
        return statusEvent;
    }


    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public void setStatusEvent(String statusEvent) {
        this.statusEvent = statusEvent;
    }


    public static void main(String[] args) {
        //dislay a event
        System.out.println("1. Dislay a event: ");
        Event e1 = new Event(10006);
        String result1 = e1.toString();
        System.out.println(result1);
    }

}
