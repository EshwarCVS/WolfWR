import java.sql.*;
import java.text.ParseException;

/**
 * This java file holds all the SQL code for the Customer table
 */
class Customer {

    static Connection connection = SetDatabase.conn;
    static Statement statement = SetDatabase.stmt;
    static ResultSet result = SetDatabase.rs;

    /**
     * Looks for Customer in DB by inputed customer ID
     * 
     * @param customer_id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewCustomerById(int customer_id) throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Customer WHERE customer_id = ?;");
            ps.setInt(1, customer_id);
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
     * Gets all customers in the DB
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewAllCustomer() throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Customer;");
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
     * Adds a customer to DB with inputed parameters from the user
     * 
     * @param customerId
     * @param firstName
     * @param lastName
     * @param membership_level
     * @param email
     * @param age
     * @param phone
     * @param address
     * @param activeStatus
     * @throws ParseException
     * @throws SQLException 
     */
    static void addCustomer(int customerId, String firstName, String lastName, String membership_level, String email, int age, String phone, 
    		String address) throws ParseException, SQLException {
        int id = 0;

        try {
        	connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Customer (customer_id, first_name, last_name, membership_level, email, age, phone, address, status) "
                    + "VALUES (?,?,?,?,?,?,?,?,?);");

            ps.setInt(1, customerId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, membership_level);
            ps.setString(5,email);
            ps.setInt(6, age);
            ps.setString(7, phone);
            ps.setString(8, address);
            ps.setInt(9, 1);
            id = ps.executeUpdate();
            ps.close();
            
            if (id > 0) {
                System.out.println("Customer added");

            } else {
                System.out.println("Customer not added to database");

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

    /**
     * Updates a customer with inputed parameters from the user
     * 
     * @param customerId
     * @param firstName
     * @param lastName
     * @param membership_level
     * @param email
     * @param age
     * @param phone
     * @param address
     * @param activeStatus
     * @throws ParseException
     */
    static void updateCustomer(int customerId, String firstName, String lastName, String membership_level, String email, int age, 
    		String phone, String address) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Customer SET first_name = ?, last_name = ?, membership_level = ?,  email = ?, age = ?, phone = ?, address = ? "
                    + " WHERE customer_id= ?;");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, membership_level);
            ps.setString(4,email);
            ps.setInt(5, age);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.setInt(8, customerId);
            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Customer updated");

            } else {
                System.out.println("Customer not updated");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Upgrade a customer with inputed parameters from the user
     * 
     * @param customerId
     * @param membership_level
     * @throws ParseException
     */
    static void upgradeCustomer(int customerId, String membership_level) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Customer SET membership_level = ? WHERE customer_id= ?;");
            ps.setString(1, membership_level);
            ps.setInt(2, customerId);
            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Customer upgraded");

            } else {
                System.out.println("Customer not upgraded");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Denigrate a customer with inputed parameters from the user
     * 
     * @param customerId
     * @param membership_level
     * @throws ParseException
     */
    static void downgradeCustomer(int customerId, String membership_level) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Customer SET membership_level = ? WHERE customer_id= ?;");
            ps.setString(1, membership_level);
            ps.setInt(2, customerId);
            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Customer Denigrated");

            } else {
                System.out.println("Customer not Denigrated");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    /**
     * Signup a customer with inputed parameters from the user
     * 
     * @param customerId
     * @param staff_id
     * @param signup_date
     * @throws ParseException
     */
    static void signupCustomer(int customerId, int staff_id, String signup_date) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO SignUp (staff_id, customer_id, signup_date) VALUES (?,?,?);");
            ps.setInt(1, staff_id);
            ps.setInt(2, customerId);
            ps.setString(3, signup_date);
            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Customer signed up");

            } else {
                System.out.println("Customer not signed up");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    /**
     * Cancel membership of a customer with inputed parameters from the user
     * 
     * @param customerId
     * @throws ParseException
     */
    static void cancelCustomer(int customerId) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Customer SET status =? WHERE customer_id= ?;");
            ps.setInt(1, 0);
            ps.setInt(2, customerId);
            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Customer membership cancelled");

            } else {
                System.out.println("Customer membership cancelled");

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