import java.sql.*;
import java.text.ParseException;

/**
 * This java file handles billing information in relation to suppliers.
 */

public class BillingTransaction {

    static Connection connection = SetDatabase.conn;
    static Statement statement = SetDatabase.stmt;
    static ResultSet result = SetDatabase.rs;
    
    /**
     * Creates a Bill and adds it to DB with inputed parameters from the user
     * 
     * @param billNum
     * @param prodID
     * @param suppID
     * @param staffID
     * @param date
     * @param quantity
     * @throws ParseException
     */
    static void createBill(int billNum, int prodID, int suppID, int staffID, String date, int quantity) throws ParseException {
        int id = 0;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Bills (bill_number, product_id, supplier_id, staff_id, date, quantity) "
                    + "VALUES (?,?,?,?,?,?);");

            ps.setInt(1, billNum);
            ps.setInt(2, prodID);
            ps.setInt(3, suppID);
            ps.setInt(4, staffID);
            // Format for a date string should be yyyy-[m]m-[d]d
            ps.setDate(5, java.sql.Date.valueOf(date));
            ps.setInt(6, quantity);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();

            if (id > 0) {
                System.out.println("Bill generated");

            } else {
                System.out.println("Bill not added to database");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Generates reward for a customer and records it in the Rewards table.
     * 
     * @param custID
     * @param year
     * @param staffID
     * @param rewardNum
     * @throws ParseException
     * @throws SQLException 
     */
    static void genReward(int custID, int year, int staffID, int rewardNum) throws ParseException, SQLException {
        int id = 0;
        PreparedStatement ps = null;
        
        try {
            connection.setAutoCommit(false);
        	
        	ps = connection.prepareStatement("SELECT SUM(price) AS amount FROM Performs NATURAL JOIN Transaction "
        			+ "WHERE customer_id= ? AND YEAR(date)= ?;");
            ps.setInt(1, custID);
            ps.setInt(2, year);
            
            result = ps.executeQuery();
            result.next();
            double amount = Double.parseDouble(result.getString(1));
            ps.close();
            
            PreparedStatement as = connection.prepareStatement(
                    "INSERT INTO Rewards (staff_id,  reward_number, customer_id, total_reward, year) "
                    + "VALUES (?,?,?,?,?);");

            as.setInt(1, staffID);
            as.setInt(2, rewardNum);
            as.setInt(3, custID);
            as.setDouble(4, (2.0)*amount/(100.0));
            as.setInt(5, year);
            
            id = as.executeUpdate();
            System.out.println(id);
            as.close();
            
            connection.commit();
            if (id > 0) {
                System.out.println("Reward generated");

            } else {
                System.out.println("Reward not added to database");

            }
        } catch (SQLException oops) {
        	connection.rollback();
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
        finally {

            connection.setAutoCommit(true);
        }
    }

    /**
     * Generates a new transaction by a customer at a particular store at a given day and time.
     * 
     * @param transID
     * @param date
     * @param time
     * @param custID
     * @param staffID
     * @param storeID
     * @throws ParseException
     * @throws SQLException 
     */
    static void genNewTransaction(int transID, String date, String time, int custID, int staffID, int storeID) throws ParseException, SQLException {
        int id = 0;

        try {
        	connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Transaction (transaction_id,  date, time, price) "
                + "VALUES (?,?,?,?);");

            ps.setInt(1, transID);
            ps.setDate(2, java.sql.Date.valueOf(date));
            // Time in the format of HH:MM:SS
            ps.setTime(3, java.sql.Time.valueOf(time));
            ps.setInt(4, 0);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();
            if (id > 0) {
                System.out.println();

            } else {
                System.out.println("Failed to update transaction value");

            }

            ps = connection.prepareStatement(
                "INSERT INTO StoreTransactions (transaction_id,  store_id) "
                + "VALUES (?,?);");

            ps.setInt(1, transID);
            ps.setInt(2, storeID);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();
            if (id > 0) {
                System.out.println();

            } else {
                System.out.println("Failed to update transaction value");

            }

            ps = connection.prepareStatement(
                "INSERT INTO Generates (transaction_id,  staff_id) "
                + "VALUES (?,?);");

            ps.setInt(1, transID);
            ps.setInt(2, staffID);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();
            if (id > 0) {
                System.out.println();

            } else {
                System.out.println("Failed to update transaction value");

            }

            ps = connection.prepareStatement(
                "INSERT INTO Performs (transaction_id,  customer_id) "
                + "VALUES (?,?);");

            ps.setInt(1, transID);
            ps.setInt(2, custID);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();
            connection.commit();
            if (id > 0) {
                System.out.println();

            } else {
                System.out.println("Failed to update transaction value");

            }
        } catch (SQLException oops) {
        	connection.rollback();
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
        finally {

            connection.setAutoCommit(true);
        }
    }

    /**
     * Adds the product and respective quantity (product list) corresponding to a particular transaction.
     * 
     * @param transID
     * @param prodID
     * @param suppID
     * @param quantity
     * @throws ParseException
     * @throws SQLException 
     */
    static void addTransactionItems(int transID, int prodID, int suppID, int storeID, int quantity) throws ParseException, SQLException {
        int id = 0;

        try {
        	
        	connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Purchases (transaction_id, product_id, supplier_id, quantity_purchased) "
                    + "VALUES (?,?,?,?);");

            ps.setInt(1, transID);
            ps.setInt(2, prodID);
            ps.setInt(3, suppID);
            ps.setInt(4, quantity);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();

            if (id > 0) {
                System.out.println("Successfully added purchased items.");

            } else {
                System.out.println("Fail to add purchased items.");

            }

            ps = connection.prepareStatement(
                    "UPDATE Merchandise SET total_quantity=total_quantity-" + "?" + " WHERE product_id=" + "?" +
                    " AND supplier_id=" + "?" + ";");

            ps.setInt(1, quantity);
            ps.setInt(2, prodID);
            ps.setInt(3, suppID);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();

            if (id > 0) {
                System.out.println("Merchandise data updated.");

            } else {
                System.out.println("Failed to update Merchandise data.");

            }

            ps = connection.prepareStatement(
                    "UPDATE Stocks SET quantity_in_stock=quantity_in_stock-" + "?" + " WHERE store_id=" + "?" +
                    " AND product_id=" + "?" + " AND supplier_id=" + "?" + ";");

            ps.setInt(1, quantity);
            ps.setInt(2, storeID);
            ps.setInt(3, prodID);
            ps.setInt(4, suppID);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();
            connection.commit();

            if (id > 0) {
                System.out.println("Stocks updated successfully.");

            } else {
                System.out.println("Failed to update Stocks.");

            }
        } catch (SQLException oops) {
        	connection.rollback();
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
        finally {

            connection.setAutoCommit(true);
        }
    }

    /**
     * Applies discounts for products corresponding to a transaction and calculates total price.
     * 
     * @param transID
     * @throws ParseException
     * @throws SQLException 
     */
    static void calculateTransaction(int transID) throws ParseException, SQLException {
        int id = 0;

        try {

            String dateTrans;
        	PreparedStatement psT = connection.prepareStatement("SELECT date FROM Transaction WHERE transaction_id ="+transID);
        	result = psT.executeQuery();
        	result.next();
        	dateTrans = result.getString(1);
        	connection.setAutoCommit(false);
        	PreparedStatement ps = connection.prepareStatement("SELECT SUM(Sale_Price) AS Price FROM "
        			+ "(SELECT (quantity_purchased * market_price * (100-discount) / 100) AS Sale_Price FROM Purchases "
        			+ "NATURAL JOIN Merchandise WHERE transaction_id=" + transID + " AND (discount<>0) AND "
        					+ "('"+dateTrans+"' BETWEEN discount_start AND discount_expiry) UNION "
        					+ "(SELECT (quantity_purchased * market_price) AS Sale_Price FROM Purchases NATURAL JOIN "
        					+ "Merchandise WHERE transaction_id=" +transID+ "  AND (discount=0 OR (discount<>0 AND '"+dateTrans+"' NOT "
        					+ "BETWEEN discount_start AND discount_expiry)))) AS T;");
        	
        	result = ps.executeQuery();
        	result.next();
            double amount = Double.parseDouble(result.getString(1));
            PreparedStatement ps1 = connection.prepareStatement(
                    "UPDATE Transaction SET price= ? WHERE transaction_id= ?;");
            ps1.setDouble(1, amount);
            ps1.setInt(2, transID);
            
            id = ps1.executeUpdate();
            ps.close();
            ps1.close();

            if (id > 0) {
                System.out.println();

            } else {
                System.out.println("Failed to update transaction value");

            }
            connection.commit();
        } catch (SQLException oops) {
        	connection.rollback();
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
        finally {

            connection.setAutoCommit(true);
        }
    }
}
