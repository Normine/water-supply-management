import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Buying {

    // Method to process buying a product
    public static String buyProduct(int productId, int quantity, int customerId) {
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                // Start transaction
                conn.setAutoCommit(false);

                // Update stock in products table
                String updateStockQuery = "UPDATE products SET stock = stock - ? WHERE id = ? AND stock >= ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateStockQuery);
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, productId);
                updateStmt.setInt(3, quantity);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated == 0) {
                    conn.rollback();
                    return "Not enough stock for this product.";
                }

                // Insert into orders table
                String insertOrderQuery = "INSERT INTO orders (customer_id, product_id, quantity, order_date) VALUES (?, ?, ?, NOW())";
                PreparedStatement insertStmt = conn.prepareStatement(insertOrderQuery);
                insertStmt.setInt(1, customerId);
                insertStmt.setInt(2, productId);
                insertStmt.setInt(3, quantity);

                int rowsInserted = insertStmt.executeUpdate();
                if (rowsInserted > 0) {
                    conn.commit();
                    return "Purchase successful!";
                } else {
                    conn.rollback();
                    return "Error in processing the order.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Database connection error.";
    }
}
