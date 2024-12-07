import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDetails{

    // Method to add a new product
    public static String addProduct(String name, double price, int stock) {
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setDouble(2, price);
                stmt.setInt(3, stock);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return "Product added successfully!";
                } else {
                    return "Failed to add product.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Database connection error.";
    }

    // Method to update product details
    public static String updateProduct(int productId, String name, double price, int stock) {
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "UPDATE products SET name = ?, price = ?, stock = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setDouble(2, price);
                stmt.setInt(3, stock);
                stmt.setInt(4, productId);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return "Product updated successfully!";
                } else {
                    return "Product not found.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Database connection error.";
    }

    // Method to delete a product
    public static String deleteProduct(int productId) {
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "DELETE FROM products WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, productId);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    return "Product deleted successfully!";
                } else {
                    return "Product not found.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Database connection error.";
    }

    // Method to fetch all products
    public static List<String> getAllProducts() {
        List<String> products = new ArrayList<>();
        try (Connection conn = DBConnection.connect()) {
            if (conn != null) {
                String query = "SELECT * FROM products";
                PreparedStatement stmt = conn.prepareStatement(query);
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

    // Method to fetch details of a single product by ID
    public static String getProductDetails(int productId) {
        String result = "";
        try (Connection conn = DBConnection.connect()) {
            if (conn != null){
                String query = "SELECT * FROM products WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, productId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()){
                    result = "ID: " + rs.getInt("id") +
                             "\nName: " + rs.getString("name") +
                             "\nPrice: " + rs.getDouble("price") +
                             "\nStock: " + rs.getInt("stock");
                } else {
                    result = "Product not found.";
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
