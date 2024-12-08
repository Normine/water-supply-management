import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierHome {

    // Method to get all suppliers
    public static List<String> getAllSuppliers() {
        List<String> suppliers = new ArrayList<>();
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "SELECT * FROM suppliers";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String supplier = "ID: " + rs.getInt("id") +
                                      ", Name: " + rs.getString("name") +
                                      ", Phone: " + rs.getString("phone");
                    suppliers.add(supplier);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    // Method to get all products of a supplier
    public static List<String> getProductsBySupplier(int supplierId) {
        List<String> products = new ArrayList<>();
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "SELECT * FROM products WHERE supplier_id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, supplierId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String product = "ID: " + rs.getInt("id") +
                                     ", Name: " + rs.getString("name") +
                                     ", Price: " + rs.getDouble("price") +
                                     ", Stock: " + rs.getInt("stock");
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
