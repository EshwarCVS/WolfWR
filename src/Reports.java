import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class Reports {

    static Connection connection = SetDatabase.conn;
    static Statement statement = SetDatabase.stmt;
    static ResultSet result = SetDatabase.rs;
    
    static Scanner scan = new Scanner(System.in);

    public static void totalSalesByYear() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            System.out.print("Enter Year: ");
            String year = scan.next();
            System.out.println();
            String query = "SELECT SUM(price) AS total FROM Transaction WHERE YEAR(date)=" + year;
            
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
                int sales = returnSet.getInt("total");
                System.out.printf("Total Sales for %s : $ %d\n", year, sales);
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    public static void totalSalesByMonth() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            System.out.print("Enter Year: ");
            String year = scan.next();
            System.out.print("Enter Month:");
            String month = scan.next();
            System.out.println();
            String query = "SELECT SUM(price) AS total FROM Transaction WHERE YEAR(date)=" + year +" AND MONTH(date)=" + month;
            
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
                int sales = returnSet.getInt("total");
                System.out.printf("Total Sales for %s-%s : $ %d\n", year, month, sales);
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    public static void totalSalesByDay() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
        	System.out.print("Enter Year: ");
            String year = scan.next();
            System.out.print("Enter Month:");
            String month = scan.next();
            System.out.print("Enter Day:");
            String day = scan.next();
            System.out.println();
            String query = "SELECT SUM(price) AS total FROM Transaction WHERE YEAR(date)=" + year + 
                " AND MONTH(date)=" + month + " AND DAY(date)=" + day;
            
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
                int sales = returnSet.getInt("total");
                System.out.printf("Total Sales for %s-%s-%s : $ %d\n", year, month, day, sales);
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    public static void generateSalesGrowth() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
        	System.out.print("Enter Year: ");
            String year = scan.next();
            System.out.print("Enter Month:");
            String month = scan.next();
            System.out.println();
            System.out.print("Sales Growth Report For A Specific Store For Two Consecutive Months: ");
            System.out.println();
            int month2 = Integer.parseInt(month);
            int year2 = Integer.parseInt(year);
            if(month2 > 12) {
            	month2 = month2 % 12;
            	year2++;
            }
            String monthCalculated = Integer.toString(month2);
            String yearCalculated = Integer.toString(year2);
            String query = "SELECT DATE_FORMAT(date, '%m-%Y') AS Month, SUM(quantity_purchased) AS "
            		+ "Quantity FROM Transaction NATURAL JOIN Purchases WHERE ((YEAR(date)=" + year+ " OR YEAR(date)=" + yearCalculated
            		+ ") AND (MONTH(date)=" + month+ " OR MONTH(date)=" + monthCalculated + ")) GROUP BY DATE_FORMAT(date, '%m-%Y');";
            
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
            	ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1)
                            System.out.print(",  ");
                        String columnValue = result.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println("");
                } while (result.next());
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    public static void  storeMerchandiseReport() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
        	System.out.println();
            String query = "SELECT store_id, SUM(quantity_in_stock) AS Quantity FROM Stocks GROUP BY store_id;";
            
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
            	ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1)
                            System.out.print(",  ");
                        String columnValue = result.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println("");
                } while (result.next());
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    public static void  productStockReport() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            
            System.out.println();
            String query = "SELECT product_id, supplier_id, total_quantity FROM Merchandise;";
            
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
            	ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1)
                            System.out.print(",  ");
                        String columnValue = result.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println("");
                } while (result.next());
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    public static void  salesProduct() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            System.out.print("Enter Year: ");
            String year = scan.next();
            System.out.println();
            String query = "SELECT product_id, supplier_id, COUNT(*) AS Count FROM Bills WHERE YEAR(date)=" + year+" GROUP BY product_id,supplier_id;";
            
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
            	ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1)
                            System.out.print(",  ");
                        String columnValue = result.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println("");
                } while (result.next());
                System.out.println();
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    public static void  customerYearlyGrowth() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            System.out.print("Enter From Year: ");
            String fYear = scan.next();
            System.out.println();
            System.out.print("Enter To Year: ");
            String tYear = scan.next();
            System.out.println();
            String query = "SELECT YEAR(date) as Year, COUNT(*) AS Customers_Visited FROM Transaction "
            		+ "NATURAL JOIN Purchases WHERE YEAR(date) BETWEEN " + fYear + " AND " + tYear + " GROUP BY YEAR(date) ORDER BY Year;";
            
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
            	ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1)
                            System.out.print(",  ");
                        String columnValue = result.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println("");
                } while (result.next());
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    public static void  customerMonthlyGrowth() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            System.out.print("Enter From Date in Format(YYYY-MM-DD): ");
            String fDate = scan.next();
            System.out.println();
            System.out.print("Enter To Date in Format(YYYY-MM-DD): ");
            String tDate = scan.next();
            System.out.println();
            String query = "SELECT DATE_FORMAT(date, '%m-%Y') AS Month, COUNT(*) AS Customers_Visited FROM Transaction NATURAL JOIN "
            		+ "Purchases WHERE date BETWEEN '" + fDate + "' AND '" + tDate + "' GROUP BY DATE_FORMAT(date, '%m-%Y');";
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
            	ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1)
                            System.out.print(",  ");
                        String columnValue = result.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println("");
                } while (result.next());
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    public static void  customerSpendingReport() throws ClassNotFoundException, SQLException, ParseException {
    	ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
        	System.out.print("Enter Customer id: ");
            int customerId = scan.nextInt();
            System.out.println();
            System.out.print("Enter From Date in Format(YYYY-MM-DD): ");
            String fDate = scan.next();
            System.out.println();
            System.out.print("Enter To Date in Format(YYYY-MM-DD): ");
            String tDate = scan.next();
            System.out.println();
            String query = "SELECT DATE_FORMAT(date, '%m-%Y') AS Month, SUM(price) AS Amount_Spent FROM Performs NATURAL JOIN Transaction WHERE"
            		+ " customer_id=? AND date BETWEEN '" + fDate + "' AND '" + tDate + "' GROUP BY DATE_FORMAT(date,'%m-%Y');";
            
            ps = connection.prepareStatement(query);
            ps.setInt(1, customerId);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
            if (returnSet.next()) {
            	ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1)
                            System.out.print(",  ");
                        String columnValue = result.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println("");
                } while (result.next());
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
}