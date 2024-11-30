import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =  "jdbc:sqlserver://DESKTOP-PSPA667\\SQLEXPRESS:1433;"
            + "databaseName=WaterSupplyManagementSystemDatabase2;"
            + "encrypt=false;"
            + "trustServerCertificate=true;";;
    private static final String USERNAME = "sa"; // Thay bằng username SQL Server của bạn
    private static final String PASSWORD = "123123123"; // Thay bằng password của bạn

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
