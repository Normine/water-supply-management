import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDetails {

    // Method to fetch customer details based on customer ID
    public static String getCustomerDetails(int customerId) {
        String result = "";
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "SELECT * FROM customers WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, customerId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    result = "Name: " + rs.getString("name") + "\n" +
                             "Email: " + rs.getString("email") + "\n" +
                             "Phone: " + rs.getString("phone");
                } else {
                    result = "Customer not found.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Method to update customer details
    public static String updateCustomerDetails(int customerId, String name, String email, String phone) {
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "UPDATE customers SET name = ?, email = ?, phone = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, phone);
                stmt.setInt(4, customerId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return "Customer details updated successfully!";
                } else {
                    return "Customer not found.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error updating customer details.";
    }
}
