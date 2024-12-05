import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signup {

    // Method to register a new user
    public static String registerUser(String username, String password, String email) {
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, email);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return "User registered successfully!";
                } else {
                    return "Error during registration.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Database connection error.";
    }
}
