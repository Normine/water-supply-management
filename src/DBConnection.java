import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class DBConnection {
    private static final String URL =  "jdbc:sqlserver://localhost:1434;"
            + "databaseName=WaterSupplyManagementSystemDatabase;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    private static final String USERNAME = "sa"; // Thay bằng username SQL Server của bạn
    private static final String PASSWORD = "sa"; // Thay bằng password của bạn

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public static String[] authenticateUser(String username, String password) {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String[] results = new String[2]; // Index 0: userID, Index 1: role

    try {
        con = getConnection();

        // Step 1: Validate the user and retrieve their role and userID
        String sqlUser = "SELECT UserID, Role FROM Users WHERE Username = ? AND Password = ?";
        stmt = con.prepareStatement(sqlUser);
        stmt.setString(1, username);
        stmt.setString(2, password);

        rs = stmt.executeQuery();

        if (rs.next()) {
            String userID = rs.getString("UserID");
            String role = rs.getString("Role");
            results[0] = userID;
            results[1] = role;

            // Step 2: Retrieve the associated ID based on the role
            if ("Supplier".equalsIgnoreCase(role)) {
                String sqlSupplier = "SELECT SupplierID FROM Supplier WHERE UserID = ?";
                try (PreparedStatement stmt2 = con.prepareStatement(sqlSupplier)) {
                    stmt2.setString(1, userID);
                    try (ResultSet rs2 = stmt2.executeQuery()) {
                        if (rs2.next()) {
                            results[0] = rs2.getString("SupplierID"); // Replace userID with SupplierID
                        }
                    }
                }
            } else if ("Customer".equalsIgnoreCase(role)) {
                String sqlCustomer = "SELECT CustomerID FROM Customer WHERE UserID = ?";
                try (PreparedStatement stmt3 = con.prepareStatement(sqlCustomer)) {
                    stmt3.setString(1, userID);
                    try (ResultSet rs3 = stmt3.executeQuery()) {
                        if (rs3.next()) {
                            results[0] = rs3.getString("CustomerID"); // Replace userID with CustomerID
                        }
                    }
                }
            }
        } else {
            // If no user found, return null or an appropriate value
            results[0] = null;
            results[1] = "Invalid credentials";
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
    public static String registerSup(String username, String password, String email, String address) {
           boolean exists = false;
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet rs = null;
        ResultSet rs2 = null;
        String[] results = new String[2];    
        try {
            con = getConnection();  {
                if (con != null) {
                     String sql = "SELECT COUNT(*) FROM Users WHERE Username = ? AND password = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs= pstmt.executeQuery();
                if (rs.next()) {
                exists = rs.getInt(1) > 0;
                if(exists){
                    return "User account already exists. Please log in";
                }
            }
                    
                    String sql2 = "INSERT INTO dbo.Users (Username, Password, Role) VALUES (?,?, ?)";
                    pstmt2 = con.prepareStatement(sql2);
                    pstmt2.setString(1,username);
                    pstmt2.setString(2,password);
                    pstmt2.setString(3,"Supplier");
                    int rowsAffected = pstmt2.executeUpdate();
                                        
                    int userId=-1;
                    System.out.println("This got executed");
                    String sql3 = "SELECT Users.UserID FROM Users WHERE Username = ? AND Password = ?";
                    pstmt3 = con.prepareStatement(sql3);
                    pstmt3.setString(1,username);
                    pstmt3.setString(2,password);
                    rs2 = pstmt3.executeQuery();
                    if (rs2.next()) {
                    userId = rs2.getInt("UserID");
            }
                    System.out.println(userId);
                    
                    rowsAffected =0;
                    String query = "INSERT INTO Supplier (UserID,Name, Password, Email, Address) VALUES (?,?, ?, ?, ?)";
                    stmt = con.prepareStatement(query);
                    stmt.setInt(1,userId);
                    stmt.setString(2, username);
                    stmt.setString(3, password);
                    stmt.setString(4, email);
                    stmt.setString(5, address);
                    rowsAffected = stmt.executeUpdate();
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
public static String registerCus(String username, String password, String email, String address) {
            boolean exists = false;
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet rs = null;
        ResultSet rs2 = null;
        String[] results = new String[2];    
        try {
            con = getConnection();  {
                if (con != null) {
                     String sql = "SELECT COUNT(*) FROM Users WHERE Username = ? AND password = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs= pstmt.executeQuery();
                if (rs.next()) {
                exists = rs.getInt(1) > 0;
                if(exists){
                    return "User account already exists. Please log in";
                }
            }
                    
                    String sql2 = "INSERT INTO dbo.Users (Username, Password, Role) VALUES (?,?, ?)";
                    pstmt2 = con.prepareStatement(sql2);
                    pstmt2.setString(1,username);
                    pstmt2.setString(2,password);
                    pstmt2.setString(3,"Customer");
                    int rowsAffected = pstmt2.executeUpdate();
                                        
                    int userId=-1;
                    System.out.println("This got executed");
                    String sql3 = "SELECT Users.UserID FROM Users WHERE Username = ? AND Password = ?";
                    pstmt3 = con.prepareStatement(sql3);
                    pstmt3.setString(1,username);
                    pstmt3.setString(2,password);
                    rs2 = pstmt3.executeQuery();
                    if (rs2.next()) {
                    userId = rs2.getInt("UserID");
            }
                    System.out.println(userId);
                    
                    rowsAffected =0;
                    String query = "INSERT INTO Customer (UserID,Name, Password, Email, Address) VALUES (?,?, ?, ?, ?)";
                    stmt = con.prepareStatement(query);
                    stmt.setInt(1,userId);
                    stmt.setString(2, username);
                    stmt.setString(3, password);
                    stmt.setString(4, email);
                    stmt.setString(5, address);
                    rowsAffected = stmt.executeUpdate();
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
    public static String showSearchQuery(String supplierName) {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        StringBuilder result = new StringBuilder();
        try {
            con = getConnection();
            String preparedQuery =
                       "SELECT WR.ResourceID, WR.Type, WR.Location, WR.Capacity, S.Name, S.Address, S.Rating, S.SupplyType " +
    "FROM WaterResource WR, Supplier S, Supplier_WaterResource SW " +
    "WHERE WR.ResourceID = SW.ResourceID " +
    "  AND SW.SupplierID = S.SupplierID " +
    "  AND (S.Name = ?)";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, supplierName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result
                        .append("Resource ID: ")
                        .append(rs.getString("ResourceID"))
                        .append("\n")
                        .append("Supplier Name: ")
                        .append(rs.getString("Name"))
                        .append("\n")
                        .append("Water Type: ")
                        .append(rs.getString("Type"))
                        .append("\n")
                        .append("Water Resource Capacity: ")
                        .append(rs.getString("Capacity"))
                        .append("\n")
                        .append("Water Resource Location:  ")
                        .append(rs.getString("Location"))
                        .append("\n")
                        .append("Address of Supplier: ")
                        .append(rs.getString("Address"))
                        .append("\n")
                        .append("Rating: ")
                        .append(rs.getString("Rating"))
                        .append("\n")
                        .append("SupplyType: ")
                        .append(rs.getString("SupplyType"))
                        .append("\n")
                        .append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("exec h");
        System.out.println(result.toString());
        return result.toString();
    }
    
    
    public static String UpdateSupplierInformation(String columnName, String value, String id){
        Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
            try{
            con = getConnection();
              String sql = "UPDATE Supplier SET " + columnName + " = ? WHERE SupplierID = ?";

        // Create a prepared statement
        stmt = con.prepareStatement(sql);

        // Set the values for the placeholders
        stmt.setString(1, value); // Set the new value for the column
        stmt.setInt(2, Integer.parseInt(id));   // Set the SupplierID

        // Execute the update
        int rowsAffected = stmt.executeUpdate();

        // Print the result
        System.out.println("Rows updated: " + rowsAffected);
        return "Updated Supplier information successfully";
            }catch(SQLException e){
                        return "Failed to update Supplier information";
            }finally{
             try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            }
    }
    
    
    public static String getOrders(String SupplierID){
            int id = Integer.parseInt(SupplierID);
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String finalOrdersResult = "";
            try{
                conn = getConnection();
                String sql = "select * from Orders where SupplierID = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,id);
                rs = stmt.executeQuery();
                while(rs.next()){
                    int orderID = rs.getInt("OrderID");
                    String status = rs.getString("Status");
                    String orderDate = rs.getString("OrderDate");
                    int quantity = rs.getInt("Quantity");
                    int customerID = rs.getInt("CustomerID");
                    
                    String rsEachrow="OrderID: "+orderID + "\n" + "CustomerID: "+ customerID + "\n" + "Status: " + status + "\n" + "Order Date: " + orderDate + "\n" + "Quantity: " + quantity + "\n" + "\n"; 
                    finalOrdersResult += rsEachrow;

                }
            }catch(SQLException e){
            e.printStackTrace();
            }
           return finalOrdersResult;
    }
    
    public static String getSupplierName(String supplierID){
         int id = Integer.parseInt(supplierID);
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String supplierName = "";
            try{
                conn = getConnection();
                String sql = "select Name from Supplier where SupplierID = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,id);
                rs = stmt.executeQuery();
                while(rs.next()){
                    supplierName = rs.getString("Name");
                }
    }catch(SQLException e){
    e.printStackTrace();
    }
            return supplierName;
    }
    
    public static String getSupplierPost(String SupplierName){
        String finalRs ="Address: \t Rating \t Email \t SupplyType \t \n\n";
        Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try{
                conn = getConnection();
                String sql = "select * from Supplier where Name = ?";
                System.out.println("exec");
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,SupplierName);
                rs = stmt.executeQuery();
                while(rs.next()){
                    String address = rs.getString("Address");
                    String rating = rs.getString("Rating");
                    String email = rs.getString("Email");
                    String supplyType = rs.getString("SupplyType");
                    
                    finalRs += address + rating + email + supplyType;
                    finalRs += "\n";
                }
            }catch(SQLException e){
            e.printStackTrace();
            }finally {   
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
        }
        return finalRs;
    }
    
    public static void post(String waterType, String location, String capacity, String id){
            int supplierID = Integer.parseInt(id);
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try{
                conn = getConnection();
                String sql = "insert into WaterResource (Type, Location, Capacity) values (?,?,?)";
                stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1,waterType);
                stmt.setString(2,location);
                stmt.setString(3,capacity);

                stmt.executeUpdate();
                int resourceId =-1;
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        resourceId = generatedKeys.getInt(1); // The generated ID
                    } else {
                        System.out.println("No ID generated!");
                    }
                }
                if(resourceId!=-1){
                                PreparedStatement stmt2 = null;

                    String sql1 = "insert into Supplier_WaterResource (SupplierID, ResourceID) values (?,?)";
                stmt2 = conn.prepareStatement(sql1);
                stmt2.setInt(1,supplierID);
                stmt2.setInt(2,resourceId);

                stmt2.executeUpdate();
                }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {   
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
        }
            
    
    }
    public static ResultSet executeQuery(String query) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery(query);
    }
    
    public static Object[][] getPostList(String supplierID){
         Object[][] data = {
    {"Reservoir", "HCMC", 3000},
    {"Underground Well", "HCMC", 3000},
    {"Rainwater Harvest", "Da Nang", 2000}
};
        String query ="select Type, Location, Capacity from WaterResource w join Supplier_WaterResource sw on w.ResourceID = sw.ResourceID where sw.SupplierID = ?";
        int id = Integer.parseInt(supplierID);
            PreparedStatement stmt = null;
            Connection conn = null;
            ResultSet rs = null;
            try{
                conn = getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                 List<Object[]> rows = new ArrayList<>();
            while (rs.next()) {
                String source = rs.getString("Type");
                String location = rs.getString("Location");
                int capacity = rs.getInt("Capacity");

                rows.add(new Object[]{source, location, capacity});
            }

            // Convert list to Object[][]
            data = rows.toArray(new Object[0][]);
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                 try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
                         return data;

    }
    public static void createNewOrder(String strCustomerID, String supplierName, int resourceID, int amount){
        
            Connection conn = null;
            PreparedStatement stmt = null;

            ResultSet rs = null;
            try{
                conn= getConnection();
                java.util.Date currentDate = new java.util.Date(); 
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            int customerID = Integer.parseInt(strCustomerID);
                int supplierID = getSupplierID(supplierName);
                 
                //insert into Orders table
                 String orderSql = "insert into Orders(Status, OrderDate,Quantity,SupplierID,CustomerID) values (?,?,?,?,?)";
                 stmt = conn.prepareStatement(orderSql,Statement.RETURN_GENERATED_KEYS);
                 stmt.setString(1, "Pending");
                 stmt.setDate(2, sqlDate);
                 stmt.setInt(3, amount);
                 stmt.setInt(4, supplierID);
                stmt.setInt(5, customerID);
                stmt.executeUpdate();
                int orderID = -1;
                 try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderID = generatedKeys.getInt(1); // The generated ID
                    } else {
                        System.out.println("No ID generated!");
                    }
                }
                if(orderID!=-1){
                                PreparedStatement stmt2 = null;

                    String sql1 = "update WaterResource set OrderID = ? where ResourceID = ?";
                stmt2 = conn.prepareStatement(sql1);
                stmt2.setInt(1,orderID);
                stmt2.setInt(2,resourceID);
                stmt2.executeUpdate();
                }
                
                
                
                
                
                
                JOptionPane.showMessageDialog(null, "Ordered successfully");

            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                 try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
    }
   
    
    public static int getSupplierID (String supplierName){
         Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            int supplierID =-1;
            try{
                conn = getConnection();
                String sql = "select SupplierID from Supplier where Name = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, supplierName);
                rs = stmt.executeQuery();
                if(rs.next()){
                    supplierID = rs.getInt("SupplierID");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
            return supplierID;
    }
    
    public static Object [][] getCustomerBuyingList (int customerID){
        Object data[][] ={};
         String query ="select WC.ConnectionID, Name, OrderDate, Quantity, Amount, SupplyType,B.PaymentStatus "
                 + "from WaterConnection WC join Billing B on WC.ConnectionID =  B.ConnectionID "
                 + "join WaterResource WR on WC.ResourceID = WR.ResourceID join Orders O on WR.OrderID = O.OrderID "
                 + "join Supplier S on O.SupplierID = S.SupplierID where WC.CustomerID = ?;";
            PreparedStatement stmt = null;
            Connection conn = null;
            ResultSet rs = null;
            try{
                conn = getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, customerID);
                rs = stmt.executeQuery();
                 List<Object[]> rows = new ArrayList<>();
            while (rs.next()) {
                int connectionID = rs.getInt("ConnectionID");
                String name = rs.getString("Name");
                java.util.Date orderDate = rs.getDate("OrderDate");
                int quantity = rs.getInt("Quantity");
                int amount = rs.getInt("Amount");
                String supplyType = rs.getString("SupplyType");
                String paymentStatus = rs.getString("PaymentStatus");
                rows.add(new Object[]{connectionID, name, orderDate,quantity,amount,supplyType,paymentStatus});
            }

            // Convert list to Object[][]
            data = rows.toArray(new Object[0][]);
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                 try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
        
        return data;
    }
    
    public static void Pay(int connectionID, int customerID){
         PreparedStatement stmt = null;
            Connection conn = null;
            ResultSet rs = null;
            try{
                conn = getConnection();
                String sqlChecking = "select PaymentStatus from Billing where CustomerID = ? and ConnectionID = ?";
                stmt = conn.prepareStatement(sqlChecking);
                stmt.setInt(1, customerID);
                stmt.setInt(2, connectionID);
                rs = stmt.executeQuery();
                while(rs.next()){
                    String paymentStatus = rs.getString("PaymentStatus");
                    if(paymentStatus=="Paid"){
                                        JOptionPane.showMessageDialog(null, "This bill is already paid");
                                        return;
                    }
                    else{         
                        PreparedStatement stmt2 = null;

                        String sqlUpdate = "update Billing set PaymentStatus = ? where CustomerID = ? and ConnectionID = ?";
                        stmt2 = conn.prepareStatement(sqlUpdate);
                        stmt2.setString(1, "Paid");
                        stmt2.setInt(2, customerID);
                        stmt2.setInt(3,connectionID);
                        stmt2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Paid successfully");
                    }
                }
            }catch(SQLException e){
            e.printStackTrace();
            }finally{
                try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
    }
    public static String getCustomerName(int customerID){
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String supplierName = "";
            try{
                conn = getConnection();
                String sql = "select Name from Customer where CustomerID = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,customerID);
                rs = stmt.executeQuery();
                while(rs.next()){
                    supplierName = rs.getString("Name");
                }
    }catch(SQLException e){
    e.printStackTrace();
    }
            return supplierName;
    }
    public static void establishWaterConnection(int orderID, int supplierID){
        Connection conn = null;
            PreparedStatement stmt = null;
            PreparedStatement cusIDstmt = null;

            ResultSet rs = null;
            java.util.Date currentDate = new java.util.Date(); 
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
                        PreparedStatement stmt1 = null;

            try{
                conn = getConnection();
                
                // get ResourceID first
                    PreparedStatement stmt3 = null;
                    ResultSet rs3 = null;
                    String sql3 = "select ResourceID from WaterResource where OrderID = ?";
                    stmt3 = conn.prepareStatement(sql3);
                    stmt3.setInt(1, orderID);
                    rs3= stmt3.executeQuery();
                    int resourceID =-1;
                    while(rs3.next()){
                        resourceID = rs3.getInt("ResourceID");
                        if(resourceID ==-1){
                            System.out.println("Resource id cannot be found");
                            return;
                        }
                    }
                
                // insert to Delivery table
                String sql = "insert into Delivery (OrderID, Date, Status,SupplierID) values (?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,orderID);
                stmt.setDate(2,sqlDate);
                stmt.setString(3,"Delivered");
                stmt.setInt(4, supplierID);
                stmt.executeUpdate();
                
                //get customerID && Quantity->price 
                String getCustomerIDSql = "select CustomerID, Quantity from Orders where OrderID =?";
                cusIDstmt = conn.prepareStatement(getCustomerIDSql);
                cusIDstmt.setInt(1,orderID);
                rs = cusIDstmt.executeQuery();
                int customerID =-1;
                int price=-1;
                while(rs.next()){
                    customerID = rs.getInt("CustomerID");
                    price = rs.getInt("Quantity") * 3000;
                    if(customerID ==-1 || price ==-1){
                        System.out.println("Customer ID Or Quantity cannot be found");
                        return;
                    }
                }
                
                
                //insert to WaterConnection table
                String sql1 = "insert into WaterConnection (Date,Status, CustomerID,ResourceID) values (?,?,?,?)";
                stmt1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                stmt1.setDate(1,sqlDate);
                stmt1.setString(2,"Active");
                stmt1.setInt(3,customerID);
                stmt1.setInt(4, resourceID);
                stmt1.executeUpdate();
                int connectionID = -1;
                
                // get connection ID first
                try (ResultSet generatedKeys = stmt1.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        connectionID = generatedKeys.getInt(1); // The generated ID
                    } else {
                        System.out.println("No ID generated!");
                    }
                }
                // insert into Supplier_WaterConnection table
                if(connectionID!=-1){
                                PreparedStatement stmt2 = null;

                    String sql2 = "insert into  Supplier_WaterConnection(SupplierID, ConnectionID) values (?,?)";
                stmt2 = conn.prepareStatement(sql2);
                stmt2.setInt(1,supplierID);
                stmt2.setInt(2,connectionID);

                stmt2.executeUpdate();
                }
                
                
                
                //insert into WaterResource_WaterConnection
                
                    PreparedStatement stmt4 = null;
                    String sql4 = "insert into WaterResource_WaterConnection(ResourceID, ConnectionID) values(?,?)";
                    stmt4 = conn.prepareStatement(sql4);
                    stmt4.setInt(1, resourceID);
                    stmt4.setInt(2, connectionID);
                    stmt4.executeUpdate();
                    
                    
                    //insert into Billing table (status: Unpaid)
                String sql5 = "insert into Billing (CustomerID,Date,Amount,PaymentStatus,ConnectionID) values(?,?,?,?,?)";
                PreparedStatement stmt5 = null;
                stmt5 = conn.prepareStatement(sql5);
                stmt5.setInt(1,customerID);
                stmt5.setDate(2,sqlDate);
                stmt5.setInt(3, price);
                stmt5.setString(4,"Unpaid");
                stmt5.setInt(5, connectionID);
                stmt5.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Water Connection established!");

                    
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
    }
}
