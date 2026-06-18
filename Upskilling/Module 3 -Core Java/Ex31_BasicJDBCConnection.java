import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ex31_BasicJDBCConnection {

    static final String URL      = "jdbc:mysql://localhost:3306/school";
    static final String USER     = "root";
    static final String PASSWORD = "yourpassword";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully!");
            Statement stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT id, name, grade FROM students");

            System.out.println("\nStudents:");
            System.out.println("---------");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                    + " | Name: " + rs.getString("name")
                    + " | Grade: " + rs.getString("grade"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}