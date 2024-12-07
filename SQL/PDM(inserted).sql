use WaterSupplyManagementSystemDatabase2
go
-- Entity: Supplier
CREATE TABLE Supplier (
    SupplierID INT PRIMARY KEY,
	UserID VARCHAR(20),
    Name VARCHAR(255),
	Password VARCHAR(255),
    Address VARCHAR(255),
    Rating DECIMAL(3, 2),
    Email VARCHAR(255),
    SupplyType VARCHAR(100),	
);

alter table Supplier add FOREIGN KEY (UserID) REFERENCES Users(UserID)
go
-- Entity: Customer
CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY,
	UserID VARCHAR(20),
    Name VARCHAR(255),
	Password VARCHAR(255),
    Email VARCHAR(255),
    Address VARCHAR(255),	
);

alter table Customer add FOREIGN KEY (UserID) REFERENCES Users(UserID)

-- Entity: Users

CREATE TABLE Users (
    UserID VARCHAR(20) PRIMARY KEY,
    Username VARCHAR(255),
    Password VARCHAR(255),
    Role VARCHAR(50)
);


-- Entity: Order
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    Status VARCHAR(50),
    OrderDate DATE,
    Quantity INT,
    SupplierID INT,
    CustomerID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
);

-- Entity: WaterResource
CREATE TABLE WaterResource (
    ResourceID INT PRIMARY KEY,
    Type VARCHAR(100),
    Location VARCHAR(255),
    Capacity INT,
    OrderID INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Entity: Delivery
CREATE TABLE Delivery (
    DeliveryID INT PRIMARY KEY,
    OrderID INT,
    Date DATE,
    Status VARCHAR(50),
    SupplierID INT,
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Entity: WaterConnection
CREATE TABLE WaterConnection (
    ConnectionID INT PRIMARY KEY,
    Date DATE,
    Status VARCHAR(50),
    CustomerID INT,
    ResourceID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
);

-- Entity: Billing
CREATE TABLE Billing (
    BillingID INT PRIMARY KEY,
    CustomerID INT,
    Date DATE,
    Amount DECIMAL(10, 2),
	Price DECIMAL(10, 2),
    PaymentStatus VARCHAR(50),
    ConnectionID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ConnectionID) REFERENCES WaterConnection(ConnectionID)
);

-- Junction Table: Supplier_WaterResource (Many-to-Many between Supplier and WaterResource)
CREATE TABLE Supplier_WaterResource (
    SupplierID INT,
    ResourceID INT,
    PRIMARY KEY (SupplierID, ResourceID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID),
    FOREIGN KEY (ResourceID) REFERENCES WaterResource(ResourceID)
);

-- Junction Table: Supplier_WaterConnection (Many-to-Many between Supplier and WaterConnection)
CREATE TABLE Supplier_WaterConnection (
    SupplierID INT,
    ConnectionID INT,
    PRIMARY KEY (SupplierID, ConnectionID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID),
    FOREIGN KEY (ConnectionID) REFERENCES WaterConnection(ConnectionID)
);

-- Junction Table: WaterResource_WaterConnection (Many-to-Many between WaterResource and WaterConnection)
CREATE TABLE WaterResource_WaterConnection (
    ResourceID INT,
    ConnectionID INT,
    PRIMARY KEY (ResourceID, ConnectionID),
    FOREIGN KEY (ResourceID) REFERENCES WaterResource(ResourceID),
    FOREIGN KEY (ConnectionID) REFERENCES WaterConnection(ConnectionID)
);


UPDATE Billing
SET Price = Amount * 0.95; -- Apply a 5% discount to the Amount
go

-- Insert data
INSERT INTO Users (UserID, Username, Password, Role) VALUES
('S1', 'Aqua Vietnam','1', 'Supplier'),
('S2', 'Hydro HCMC', '12', 'Supplier'),
('S3', 'Mekong Suppliers', '123', 'Supplier'),
('S4', 'Da Nang Waters', '1234', 'Supplier'),
('S5', 'Hue Pure Water', '12345', 'Supplier'),
('C1', 'Nguyen Van An', '1', 'Customer'),
('C2', 'Tran Thi Bao Phuc', '12', 'Customer'),
('C3', 'Le Hoang Cuong', '123', 'Customer'),
( 'C4', 'Pham Minh Duong', '1234', 'Customer'),
('C5', 'Nguyen Thi Chau', '12345', 'Customer');

INSERT INTO Supplier (SupplierID, UserID, Name, Password, Address, Rating, Email, SupplyType) VALUES
(1, 'S1', 'Aqua Vietnam', '1', '123 Water Lane, Hanoi', 4.8, 'aquavietnam@gmail.com', 'Bottled Water'),
(2, 'S2', 'Hydro HCMC', '12', '456 Stream Rd, HCMC', 4.5, 'hydrohcmc@yahoo.com', 'Water Filters'),
(3, 'S3', 'Mekong Suppliers', '123', '789 Delta Ave, Can Tho', 4.7, 'mekong@gmail.com', 'Purifiers'),
(4, 'S4', 'Da Nang Waters', '1234', '101 Ocean St, Da Nang', 4.3, 'danang@gmail.com', 'Mineral Water'),
(5, 'S5', 'Hue Pure Water', '12345', '202 Perfume River Rd, Hue', 4.2, 'huepure@yahoo.com', 'Bottled Water');
INSERT INTO Customer (CustomerID, UserID, Name, Password, Email, Address) VALUES
(1, 'C1', 'Nguyen Van An', '1', 'nguyenvanan@gmail.com', '123 Le Loi Street, Hanoi'),
(2, 'C2', 'Tran Thi Bao Phuc', '12', 'tranthibaophuc@yahoo.com', '456 Nguyen Trai Street, HCMC'),
(3, 'C3', 'Le Hoang Cuong', '123', 'lehoangcuong@web.com', '789 Vo Nguyen Giap St, Da Nang'),
(4, 'C4', 'Pham Minh Duong', '1234', 'phamminhduong@gmail.com', '101 Tran Phu Street, Hue'),
(5, 'C5', 'Nguyen Thi Chau', '12345', 'nguyenthichau@yahoo.com', '202 Le Thanh Ton St, Hanoi');
INSERT INTO Orders (OrderID, Status, OrderDate, Quantity, SupplierID, CustomerID) VALUES
(1, 'Completed', '2024-11-01', 20, 1, 1),
(2, 'Pending', '2024-11-05', 15, 2, 2),
(3, 'Delivered', '2024-11-08', 10, 3, 3),
(4, 'Canceled', '2024-11-10', 5, 4, 4),
(5, 'Completed', '2024-11-12', 8, 5, 5);
INSERT INTO WaterResource (ResourceID, Type, Location, Capacity, OrderID) VALUES
(1, 'Reservoir', 'Hanoi', 5000, 1),
(2, 'Underground Well', 'HCMC', 3000, 2),
(3, 'Rainwater Harvest', 'Da Nang', 2000, 3),
(4, 'River Water Plant', 'Hue', 4000, 4),
(5, 'Mountain Stream', 'Sapa', 1500, 5);
INSERT INTO Delivery (DeliveryID, OrderID, Date, Status, SupplierID) VALUES
(1, 1, '2024-11-03', 'Delivered', 1),
(2, 2, '2024-11-07', 'In Transit', 2),
(3, 3, '2024-11-09', 'Delivered', 3),
(4, 4, '2024-11-11', 'Pending', 4),
(5, 5, '2024-11-13', 'Delivered', 5);
INSERT INTO WaterConnection (ConnectionID, Date, Status, CustomerID, ResourceID) VALUES
(1, '2024-11-01', 'Active', 1, 1),
(2, '2024-11-03', 'Active', 2, 2),
(3, '2024-11-05', 'Inactive', 3, 3),
(4, '2024-11-07', 'Active', 4, 4),
(5, '2024-11-09', 'Active', 5, 5);
INSERT INTO Billing (BillingID, CustomerID, Date, Amount, PaymentStatus, ConnectionID) VALUES
(1, 1, '2024-11-02', 500, 'Paid', 1),
(2, 2, '2024-11-06', 300, 'Unpaid', 2),
(3, 3, '2024-11-08', 700, 'Paid', 3),
(4, 4, '2024-11-10', 400, 'Unpaid', 4),
(5, 5, '2024-11-12', 600, 'Paid', 5);
INSERT INTO Supplier_WaterResource (SupplierID, ResourceID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);
INSERT INTO Supplier_WaterConnection (SupplierID, ConnectionID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);
INSERT INTO WaterResource_WaterConnection (ResourceID, ConnectionID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);
