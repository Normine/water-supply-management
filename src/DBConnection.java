import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBConnection {
    private static final String URL =  "jdbc:sqlserver://DESKTOP-PSPA667\\SQLEXPRESS:1433;"
            + "databaseName=WaterSupplyManagementSystemDatabase;"
            + "encrypt=false;"
            + "trustServerCertificate=true;";;
    private static final String USERNAME = "sa"; // Thay bằng username SQL Server của bạn
    private static final String PASSWORD = "123"; // Thay bằng password của bạn

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static String[] authenticateUser(String username, String password) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] results = new String[2];
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
            stmt = con.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                results[0] = rs.getString("UserID"); 
                results[1] = rs.getString("Role"); 
            }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
    public static String registerSup(String name, String password, String email, String address) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] results = new String[2];    
        try {
            con = DriverManager.getConnection(URL);  {
                if (con != null) {
                    String query = "INSERT INTO Supplier (name, password, email, address) VALUES (?, ?, ?, ?)";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, name);
                    stmt.setString(2, password);
                    stmt.setString(3, email);
                    stmt.setString(4, address);
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        return "User registered successfully!";
                    } else {
                        return "Error during registration.";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {   
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return "Database connection error.";
    }
    public static String registerCus(String name, String password, String email, String address) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] results = new String[2];    
        try {
            con = DriverManager.getConnection(URL);  {
                if (con != null) {
                    String query = "INSERT INTO Customer (name, password, email, address) VALUES (?, ?, ?, ?)";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, name);
                    stmt.setString(2, password);
                    stmt.setString(3, email);
                    stmt.setString(4, address);
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        return "User registered successfully!";
                    } else {
                        return "Error during registration.";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {   
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
        }
        return "Database connection error.";
    }        
    public static String showSearchQuery(String queryTxt) {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        StringBuilder result = new StringBuilder();
        try {
            con = DriverManager.getConnection(URL);           
            String preparedQuery =            
                "SELECT WR.Type, WR.Location, WR.Capacity, S.Name, S.Address, S.Rating, S.SupplyType " +
                "FROM WaterResource WR " +
                "JOIN Supplier_WaterResource SW ON WR.ResourceID = SW.ResourceID " +
                "JOIN Supplier S ON S.SupplierID = SW.SupplierID " +
                "WHERE LOWER(S.Name) = LOWER(?)";

            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, queryTxt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result
                        .append("Name: ")
                        .append(rs.getString("Name"))
                        .append("\n")
                        .append(" Type: ")
                        .append(rs.getString("Type"))
                        .append("\n")
                        .append("Capacity: ")
                        .append(rs.getString("Capacity"))
                        .append("\n")
                        .append("Location:  ")
                        .append(rs.getString("Location"))
                        .append("\n")
                        .append("Address: ")
                        .append(rs.getString("Address"))
                        .append("\n")
                        .append("Rating: ")
                        .append(rs.getString("Rating"))
                        .append("\n")
                        .append("SupplyType: ")
                        .append(rs.getString("SupplyType"))
                        .append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }
    
    public static void loadData(JTable table) {
        try {
            // Create database connection
            Connection connection = DriverManager.getConnection("your-database-url", "username", "password");

            // Query to fetch data from the database
            String query = "SELECT * FROM your_table";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Create a table model and set it to the JTable
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Column1", "Column2", "Column3"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Column1"),
                    rs.getString("Column2"),
                    rs.getString("Column3")
                });
            }
            table.setModel(model);

            // Close the connection
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static String insertData(String queryData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String insertSupplier(String username, String email, String phone, String address, String supplyType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String updateSupplier(String username, String email, String phone, String address, String supplyType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
