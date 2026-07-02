import java.sql.*;
public class Q31_BasicJDBCConnection {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:sample.db"; 
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INT, name VARCHAR(50))");
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}