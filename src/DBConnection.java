import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:3306;databaseName=WaterSupplyManagement";
    private static final String USERNAME = "root"; // Thay bằng username SQL Server của bạn
    private static final String PASSWORD = "password"; // Thay bằng password của bạn

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
