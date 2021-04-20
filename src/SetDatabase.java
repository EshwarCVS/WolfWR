import java.sql.*;
import java.util.Scanner;

public class SetDatabase {

    static String jdbcURL;
//    = "jdbc:mariadb://localhost:3306/";
    

    public static Connection conn = null;
    public static Statement stmt = null;
    public static ResultSet rs = null;
    public static void main(String[] args) {
        try {
        	@SuppressWarnings("resource")
        	Scanner startscan = new Scanner(System.in);
            System.out.println("Enter the connection string (jdbcURL)");
            jdbcURL = startscan.nextLine(); 
        	Class.forName("org.mariadb.jdbc.Driver");

        	System.out.print("MariaDB Username: ");
            String user = startscan.next();
            System.out.print("MariaDB Password: ");
            String passwd = startscan.next();
            
            try {

		        conn = DriverManager.getConnection(jdbcURL + user, user, passwd);

		        stmt = conn.createStatement();
		        
		        System.out.println("Do you want to restart the system?");
		        System.out.println("1.Yes");
		        System.out.println("2.No");
		        int restart = startscan.nextInt();
		        if(restart == 1) {
		        	
		        
		// Drop all existing tables
        try{
		    stmt.executeUpdate("DROP TABLE Purchases;");
        }  
        catch(Exception e){
            System.out.println("Purchases table not dropped!");
        }
        try{
		    stmt.executeUpdate("DROP TABLE Rewards;");
        }  
        catch(Exception e){
            System.out.println("Rewards table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Generates;");
        }
        catch(Exception e){
            System.out.println("Generates table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Performs;");
        }
        catch(Exception e){
            System.out.println("Performs table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE StoreTransactions;");
        }
        catch(Exception e){
            System.out.println("StoreTransactions table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Stocks;");
        }
        catch(Exception e){
            System.out.println("Stocks table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Signup;");
        }
        catch(Exception e){
            System.out.println("Signup table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Bills;");
        }
        catch(Exception e){
            System.out.println("Bills table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Staff;");
        }
        catch(Exception e){
            System.out.println("Staff table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Store;");
        }
        catch(Exception e){
            System.out.println("Store table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Transaction;");
        }
        catch(Exception e){
            System.out.println("Transaction table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Customer;");
        }
        catch(Exception e){
            System.out.println("Customer table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Merchandise;");
        }
        catch(Exception e){
            System.out.println("Merchandise table not dropped!");
        }
        try{
            stmt.executeUpdate("DROP TABLE Supplier;");
        }
        catch(Exception e){
            System.out.println("Supplier table not dropped!");
        }
		// Create Tables

        // Create Store Table
        stmt.executeUpdate("CREATE TABLE Store ( store_id INT NOT NULL, " +
            "address varchar(128) NOT NULL, phone char(10) NOT NULL, status BOOLEAN NOT NULL, PRIMARY KEY(store_id));");

        // Create Staff Table
        stmt.executeUpdate("CREATE TABLE Staff (" +
            "staff_id INT NOT NULL, store_id INT NOT NULL, " +
            "name varchar(128) NOT NULL, age INT NOT NULL, " +
            "phone char(10) NOT NULL, address varchar(128) NOT NULL, " +
            "email varchar(128) NOT NULL, date_hired DATE NOT NULL, " +
            "job_type varchar(128), status BOOLEAN NOT NULL, PRIMARY KEY(staff_id), " +
            "FOREIGN KEY(store_id) REFERENCES Store(store_id) ON UPDATE CASCADE );");

        // Create Customer Table
        stmt.executeUpdate("CREATE TABLE Customer ( customer_id INT NOT NULL, " +
            "first_name varchar(128) NOT NULL, " +
            "last_name varchar(128) NOT NULL, membership_level varchar(16) NOT NULL, email varchar(128) NOT NULL, " +
            "age INT, phone char(10) NOT NULL, address varchar(128) NOT NULL, " +
            "status BOOLEAN NOT NULL, PRIMARY KEY(customer_id));");

        // Create Sign Up Table
        stmt.executeUpdate("CREATE TABLE Signup ( staff_id INT NOT NULL, " +
            "customer_id INT NOT NULL, " +
            "signup_date DATE NOT NULL, " +
            "PRIMARY KEY(customer_id), " +
            "FOREIGN KEY(staff_id) REFERENCES Staff(staff_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(customer_id) REFERENCES Customer(customer_id) " +
            "ON UPDATE CASCADE );");

        // Create Supplier Table
        stmt.executeUpdate("CREATE TABLE Supplier ( supplier_id INT NOT NULL, " +
            "supplier_name varchar(128) NOT NULL, phone char(10) NOT NULL, " +
            "email varchar(128) NOT NULL, " +
            "address varchar(128) NOT NULL, status BOOLEAN NOT NULL, PRIMARY KEY(supplier_id));");

        // Create Merchandise Table
        stmt.executeUpdate("CREATE TABLE Merchandise ( product_id INT NOT NULL, supplier_id INT NOT NULL, " +
            "product_name varchar(128), total_quantity INT NOT NULL, " +
            "buy_price DECIMAL(7,2) NOT NULL, market_price DECIMAL(7,2) NOT NULL, production_date DATE NOT NULL, expiration_date DATE, " +
            "discount DECIMAL(5,2) CHECK (0<= discount <= 100.00), discount_start DATE, discount_expiry DATE, " +
            "PRIMARY KEY(product_id, supplier_id), " +
            "FOREIGN KEY(supplier_id) REFERENCES Supplier(supplier_id) " +
            "ON UPDATE CASCADE );");

        // Create Rewards Table
        stmt.executeUpdate("CREATE TABLE Rewards ( staff_id INT NOT NULL, " +
            "reward_number INT NOT NULL, customer_id INT NOT NULL, total_reward DECIMAL(7,2) NOT NULL, year INT NOT NULL, " +
            "PRIMARY KEY(reward_number, staff_id), " +
            "FOREIGN KEY(staff_id) REFERENCES Staff(staff_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(customer_id) REFERENCES Customer(customer_id) " +
            "ON UPDATE CASCADE );");

        // Create Bills Table
        stmt.executeUpdate("CREATE TABLE Bills ( " +
            "bill_number INT NOT NULL, " +
            "product_id INT NOT NULL, " +
            "supplier_id INT NOT NULL, " +
            "staff_id INT NOT NULL, " +
            "date DATE NOT NULL, " +
            "quantity INT NOT NULL, " +
            "PRIMARY KEY(bill_number), " +
            "FOREIGN KEY(staff_id) REFERENCES Staff(staff_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(supplier_id) REFERENCES Supplier(supplier_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(product_id) REFERENCES Merchandise(product_id) " +
            "ON UPDATE CASCADE );");

        // Create Transaction Table
        stmt.executeUpdate("CREATE TABLE Transaction ( transaction_id INT NOT NULL, " +
            "date DATE NOT NULL, time TIME NOT NULL, " +
            "price DECIMAL(7,2) NOT NULL, PRIMARY KEY(transaction_id) );");

        // Create Generates Table
        stmt.executeUpdate("CREATE TABLE Generates( " +
            "transaction_id INT NOT NULL, " +
            "staff_id INT NOT NULL, " +
            "PRIMARY KEY(transaction_id), " +
            "FOREIGN KEY(transaction_id) REFERENCES Transaction(transaction_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(staff_id) REFERENCES Staff(staff_id));");

        // Create Purchases Table
        stmt.executeUpdate("CREATE TABLE Purchases( " +
            "transaction_id INT NOT NULL, " +
            "product_id INT NOT NULL, " +
            "supplier_id INT NOT NULL, " +
            "quantity_purchased INT NOT NULL, " +
            "PRIMARY KEY(transaction_id, product_id, supplier_id), " +
            "FOREIGN KEY(transaction_id) REFERENCES Transaction(transaction_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(supplier_id) REFERENCES Supplier(supplier_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(product_id) REFERENCES Merchandise(product_id));");

        // Create Performs Table
        stmt.executeUpdate("CREATE TABLE Performs( " +
            "transaction_id INT NOT NULL, " +
            "customer_id INT NOT NULL, " +
            "PRIMARY KEY(transaction_id), " +
            "FOREIGN KEY(transaction_id) REFERENCES Transaction(transaction_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(customer_id) REFERENCES Customer(customer_id) " +
            "ON UPDATE CASCADE);");

        // Create StoreTransactions Table
        stmt.executeUpdate("CREATE TABLE StoreTransactions ( " +
            "transaction_id INT NOT NULL, " +
            "store_id INT NOT NULL, " +
            "PRIMARY KEY(transaction_id), " +
            "FOREIGN KEY(transaction_id) REFERENCES Transaction(transaction_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(store_id) REFERENCES Store (store_id) ON UPDATE CASCADE);");

        // Create Stocks Table
        stmt.executeUpdate("CREATE TABLE Stocks ( product_id INT NOT NULL, " +
            "supplier_id INT NOT NULL, " +
            "store_id INT NOT NULL, " +
            "quantity_in_stock INT NOT NULL, " +
            "PRIMARY KEY(product_id, supplier_id, store_id), " +
            "FOREIGN KEY(product_id) REFERENCES Merchandise(product_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(supplier_id) REFERENCES Supplier(supplier_id) " +
            "ON UPDATE CASCADE, " +
            "FOREIGN KEY(store_id) REFERENCES Store (store_id) " +
            "ON UPDATE CASCADE );");

        // Populate Tables

        // Stores
        stmt.executeUpdate("INSERT INTO Store VALUES(2001, '2221, B Street, NC', '9192222123', 1)");
        stmt.executeUpdate("INSERT INTO Store VALUES(2002, '2222, C Street, NC', '9192222456', 1)");

        // Staff
        stmt.executeUpdate("INSERT INTO Staff VALUES(1001, 2001, 'John', 32,'9191111123', '1101, S Street, NC', 'john01@gmail.com', '2018-10-10', 'Manager', 1)");
        stmt.executeUpdate("INSERT INTO Staff VALUES(1002, 2002, 'Alex', 42, '9191111456','1102, T Street, NC', 'alex12@gmail.com', '2015-07-19', 'Manager', 1)");
        stmt.executeUpdate("INSERT INTO Staff VALUES(1003, 2001, 'Mary', 28, '9191111789', '1103, U Street, NC', 'mary34@gmail.com', '2019-07-19', 'Cashier', 1)");
        stmt.executeUpdate("INSERT INTO Staff VALUES(1004, 2001, 'Connor', 25, '919111987', '1104, V Street, NC', 'connor6@gmail.com', '2019-12-03', 'Billing', 1)");
        stmt.executeUpdate("INSERT INTO Staff VALUES(1005, 2002, 'Lauren', 27, '919111654', '1105, W Street, NC', 'lauren11@gmail.com', '2020-01-01', 'Billing', 1)");

        // Supplier
        stmt.executeUpdate("INSERT INTO Supplier VALUES(4001, 'A Food Wholesale', '9194444123', 'afood@gmail.com', '4401, A Street, NC', 1)");
        stmt.executeUpdate("INSERT INTO Supplier VALUES(4002, 'US Foods', '9194444456', 'usfoods@gmail.com', '4402, G Street, NC', 1)");

        // Merchandise
        stmt.executeUpdate("INSERT INTO Merchandise VALUES(3001, 4001, 'AAA Paper Towels', 250, 10.00, 20.00, '2020-01-01', '2025-01-01', 20.0, '2020-01-01', '2021-05-01')");
        stmt.executeUpdate("INSERT INTO Merchandise VALUES(3002, 4002, 'BBB Hand soap', 200, 5.00, 10.00, '2020-01-01', '2022-01-01', 0, NULL, NULL)");
        stmt.executeUpdate("INSERT INTO Merchandise VALUES(3003, 4002, 'CCC Red Wine', 100, 15.00, 30.00, '2021-01-01', '2022-01-01', 20.0, '2020-01-01', '2021-05-01')");

        //Stocks
        stmt.executeUpdate("INSERT INTO Stocks VALUES(3001, 4001, 2001, 100)");
        stmt.executeUpdate("INSERT INTO Stocks VALUES(3001, 4001, 2002, 150)");
        stmt.executeUpdate("INSERT INTO Stocks VALUES(3002, 4002, 2001, 200)");
        stmt.executeUpdate("INSERT INTO Stocks VALUES(3002, 4002, 2002, 0)");
        stmt.executeUpdate("INSERT INTO Stocks VALUES(3003, 4002, 2001, 100)");

        // Club Members
        stmt.executeUpdate("INSERT INTO Customer VALUES(5001, 'James', 'Smith', 'Gold', 'James5001@gmail.com', 35, '9195555123', '5500, E Street, NC', 1)");
        stmt.executeUpdate("INSERT INTO Customer VALUES(5002, 'David', 'Smith', 'Platinum', 'David5002@gmail.com', 40, '9195555456', '5501, F Street, NC', 1)");


        // Transaction 1
        stmt.executeUpdate("INSERT INTO Transaction VALUES(6001, '2020-05-01', '11:43:04', 0)");
        stmt.executeUpdate("INSERT INTO Generates VALUES(6001, 1003)");
        stmt.executeUpdate("INSERT INTO Performs VALUES(6001, 5002)");
        stmt.executeUpdate("INSERT INTO StoreTransactions VALUES(6001, 2001)");
        stmt.executeUpdate("INSERT INTO Purchases VALUES(6001, 3001, 4001, 5)");
        stmt.executeUpdate("INSERT INTO Purchases VALUES(6001, 3002, 4002, 2)");

        // Transaction 2
        stmt.executeUpdate("INSERT INTO Transaction VALUES(6002, '2020-06-01', '13:22:45', 0)");
        stmt.executeUpdate("INSERT INTO Generates VALUES(6002, 1003)");
        stmt.executeUpdate("INSERT INTO Performs VALUES(6002, 5002)");
        stmt.executeUpdate("INSERT INTO StoreTransactions VALUES(6002, 2001)");
        stmt.executeUpdate("INSERT INTO Purchases VALUES(6002, 3002, 4002, 10)");

        // Transaction 3
        stmt.executeUpdate("INSERT INTO Transaction VALUES(6003, '2020-06-01', '13:22:45', 0)");
        stmt.executeUpdate("INSERT INTO Generates VALUES(6003, 1003)");
        stmt.executeUpdate("INSERT INTO Performs VALUES(6003, 5001)");
        stmt.executeUpdate("INSERT INTO StoreTransactions VALUES(6003, 2001)");
        stmt.executeUpdate("INSERT INTO Purchases VALUES(6003, 3001, 4001, 10)");


        // Sign Up
        stmt.executeUpdate("INSERT INTO Signup VALUES(1003, 5001, '2019-08-01')");
        stmt.executeUpdate("INSERT INTO Signup VALUES(1003, 5002, '2018-01-01')");

        // Supplier Bills

        stmt.executeUpdate("INSERT INTO Bills VALUES(8001, 3001, 4001, 1004, '2020-01-04', 100)");
        stmt.executeUpdate("INSERT INTO Bills VALUES(8002, 3002, 4002, 1004, '2020-01-04', 180)");
        stmt.executeUpdate("INSERT INTO Bills VALUES(8003, 3001, 4001, 1005, '2020-01-04', 150)");
        stmt.executeUpdate("INSERT INTO Bills VALUES(8004, 3002, 4001, 1005, '2020-01-04', 20)");
        stmt.executeUpdate("INSERT INTO Bills VALUES(8005, 3003, 4002, 1004, '2020-01-04', 100)");
        
        PreparedStatement psTrans = conn.prepareStatement("SELECT transaction_id FROM Transaction;");
        ResultSet rsTrans = psTrans.executeQuery();
        ResultSetMetaData rsmd = rsTrans.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rsTrans.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rsTrans.getString(i);
                BillingTransaction.calculateTransaction(Integer.parseInt(columnValue));
            }
        }
        
		        }
        System.out.println();
        System.out.println("<-- WolfWR Store Management System -->");
        System.out.println();
        HomePage.HomeMenuOptions();
            } finally {
            	System.out.println("Closed connection");
                close(rs);
                close(stmt);
                close(conn);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}
