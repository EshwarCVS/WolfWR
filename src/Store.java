import java.sql.*;
import java.text.ParseException;

/**
 * This java file holds all the SQL code for the Store table
 */
class Store {

    static Connection connection = SetDatabase.conn;
    static Statement statement = SetDatabase.stmt;
    static ResultSet result = SetDatabase.rs;

    /**
     * Looks for Store in DB by inputed store ID
     * 
     * @param storeId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewStoreById(int storeId) throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Store WHERE store_id = ?;");
            ps.setInt(1, storeId);
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        return returnSet;
    }

    /**
     * Gets all store members in the DB
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewAllStore() throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Store;");
            result = ps.executeQuery();
            returnSet = result;
            ps.close();
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
            return null;
        }
        return returnSet;
    }

    /**
     * Adds a store to DB with inputed parameters from the user
     * 
     * @param storeId
     * @param address
     * @param phone
     * @param staffId
     * @throws ParseException
     * @throws ClassNotFoundException 
     */
    static void addStore(int storeId, String address, String phone, int staffId) throws ParseException, ClassNotFoundException {
        int id = 0;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Store (store_id, address, phone, status) "
                    + "VALUES (?,?,?,?);");

            ps.setInt(1, storeId);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setInt(4, 1);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();
            
            System.out.println("Add manager to the store:");
            System.out.println();
            StaffMenu.add(staffId, storeId);

            if (id > 0) {
                System.out.println("Store added");

            } else {
                System.out.println("Store  not added to database");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Updates a store with inputed parameters from the user
     * 
     * @param storeId
     * @param address
     * @param phone
     * @param staffId
     * @throws ParseException
     * @throws ClassNotFoundException 
     */
    static void updateStore(int storeId, String address, String phone, int staffId) throws ParseException, ClassNotFoundException {
        try {
            PreparedStatement inps = connection.prepareStatement(
                    "UPDATE Staff SET job_type = ? WHERE (store_id= ? AND job_type = 'Manager');");

            inps.setString(1, null);
            inps.setInt(2, storeId);
            inps.executeUpdate();
            System.out.println(inps.executeUpdate());
            inps.close();

            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Store SET phone = ?, address = ? WHERE store_id= ?;");
            ps.setInt(3, storeId);
            ps.setString(1, phone);
            ps.setString(2, address);
            int id = ps.executeUpdate();
            ps.close();
            
            System.out.println("Add manager to the store:");
            System.out.println();
            StaffMenu.add(staffId, storeId);

            if (id > 0) {
                System.out.println("Store updated");

            } 
            else {
            	System.out.println("Store not updated");
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    /**
     * Deletes a store by inputed store ID
     * 
     * @param storeId
     */
    static void deleteStore(int storeId) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Store SET status= ? WHERE store_id= ?;");
            ps.setInt(1, 0);
            ps.setInt(2, storeId);
            int id = ps.executeUpdate();

            System.out.println(id);

            if (id > 0) {
                System.out.println("Store deleted");
            } else {
                System.out.println("No Store with that id");
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    // Close functions

    /**
     * Closes connection object
     * 
     * @param connection
     */
    static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Throwable whatever) {
            }
        }
    }

    /**
     * Closes statement objects
     * 
     * @param statement
     */
    static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Throwable whatever) {
            }
        }
    }

    /**
     * Closes result objects
     * 
     * @param result
     */
    static void close(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (Throwable whatever) {
            }
        }
    }
}