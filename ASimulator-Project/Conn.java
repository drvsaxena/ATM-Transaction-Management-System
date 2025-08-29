package ASimulatorSystem;
import java.sql.*;  

public class Conn{
    Connection c;
    Statement s;
    
    public Conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","root");    
            s = c.createStatement(); 
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }
    
    // Method to create PreparedStatement
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return c.prepareStatement(sql);
    }
    
    // Method to close connection and statement
    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}