import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex33_TransactionHandlingJDBC {

    static final String URL      = "jdbc:mysql://localhost:3306/school";
    static final String USER     = "root";
    static final String PASSWORD = "yourpassword";

    static void transfer(Connection conn, int fromId, int toId, double amount) throws SQLException {
        String debit  = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        String credit = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        conn.setAutoCommit(false); // Begin transaction

        try (PreparedStatement debitStmt  = conn.prepareStatement(debit);
             PreparedStatement creditStmt = conn.prepareStatement(credit)) {

            // Debit sender
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromId);
            debitStmt.executeUpdate();

            // Simulate error: uncomment to test rollback
            // if (true) throw new SQLException("Simulated failure!");

            // Credit receiver
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toId);
            creditStmt.executeUpdate();

            conn.commit(); // Both succeeded → commit
            System.out.println("Transfer of ₹" + amount + " from ID " + fromId
                + " to ID " + toId + " successful.");

        } catch (SQLException e) {
            conn.rollback(); // One failed → rollback both
            System.out.println("Transaction rolled back: " + e.getMessage());
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            transfer(conn, 1, 2, 1000.00); // Transfer ₹1000 from Alice to Bob
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}