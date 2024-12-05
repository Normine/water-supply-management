import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Billing {

    // Method to calculate total bill and save it to the database
    public static String processBill(int customerId, double totalAmount) {
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "INSERT INTO billing (customer_id, total_amount, date) VALUES (?, ?, NOW())";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, customerId);
                stmt.setDouble(2, totalAmount);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return "Billing successful!";
                } else {
                    return "Error in processing the bill.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Database connection error.";
    }
}
