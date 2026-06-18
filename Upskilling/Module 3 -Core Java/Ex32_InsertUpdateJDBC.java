import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Ex32_InsertUpdateJDBC {

    static final String URL      = "jdbc:mysql://localhost:3306/school";
    static final String USER     = "root";
    static final String PASSWORD = "yourpassword";

  

    static void insertStudent(Connection conn, String name, char grade) throws Exception {
        String sql = "INSERT INTO students (name, grade) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, String.valueOf(grade));
            int rows = ps.executeUpdate();
            System.out.println("Inserted: " + rows + " row(s) — " + name + ", Grade " + grade);
        }
    }

    static void updateStudentGrade(Connection conn, int id, char newGrade) throws Exception {
        String sql = "UPDATE students SET grade = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(newGrade));
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println("Updated: " + rows + " row(s) — ID " + id + " → Grade " + newGrade);
        }
    }

   

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected!\n");

            insertStudent(conn, "Alice",   'A');
            insertStudent(conn, "Bob",     'B');
            updateStudentGrade(conn, 1,    'A'); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}