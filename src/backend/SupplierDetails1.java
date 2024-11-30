import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDetails {

    // Method to get details of a supplier
    public static String getSupplierDetails(int supplierId) {
        String result = "";
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "SELECT * FROM suppliers WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, supplierId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    result = "Supplier Name: " + rs.getString("name") + "\n" +
                             "Phone: " + rs.getString("phone");
                } else {
                    result = "Supplier not found.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
