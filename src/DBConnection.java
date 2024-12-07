import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =  "jdbc:sqlserver://DESKTOP-PSPA667\\SQLEXPRESS:1433;"
            + "databaseName=WaterSupplyManagementSystemDatabase2;"
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
        con = DriverManager.getConnection(URL);       
        String preparedQuery = 
            """
            SELECT 
                CASE 
                    WHEN U.Role = 'Customer' THEN C.CustomerID
                    WHEN U.Role = 'Supplier' THEN S.SupplierID
                    ELSE NULL 
                END AS ID,
                U.Role
            FROM Users U
            LEFT JOIN Customer C ON U.UserID = C.CustomerID AND U.Role = 'Customer'
            LEFT JOIN Supplier S ON U.UserID = (S.SupplierID + 10) AND U.Role = 'Supplier'
            WHERE U.Username = ? AND U.Password = ?
            """;
        
        stmt = con.prepareStatement(preparedQuery);
        stmt.setString(1, username);
        stmt.setString(2, password);
        
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            results[0] = rs.getString("ID");
            results[1] = rs.getString("Role");
        }
    } catch (SQLException e) {
        System.out.print(e);
        e.printStackTrace();
    } finally {
        // Đóng các kết nối
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
    public static String registerSup(String username, String password, String email, String address) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] results = new String[2];    
        try {
            con = DriverManager.getConnection(URL);  {
                if (con != null) {
                    String query = "INSERT INTO Supplier (username, password, email, address) VALUES (?, ?, ?, ?)";
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, username);
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
        }
        return "Database connection error.";
    }
    
}
