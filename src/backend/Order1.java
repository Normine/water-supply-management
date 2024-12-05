import java.sql.*;

public class Order1 {

    // Method to create a new order and save it to the database
    public static String processOrder(int customerId, int supplierId, int quantity, double totalAmount) {
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                // Query to insert the order into the Orders table
                String query = "INSERT INTO orders (customer_id, supplier_id, quantity, total_amount, order_date) VALUES (?, ?, ?, ?, NOW())";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, customerId);
                stmt.setInt(2, supplierId);
                stmt.setInt(3, quantity);
                stmt.setDouble(4, totalAmount);
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return "Order placed successfully!";
                } else {
                    return "Error in placing the order.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Database connection error.";
    }
}
