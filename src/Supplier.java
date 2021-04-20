import java.sql.*;
import java.text.ParseException;

/**
 * This java file holds all the SQL code for manipulation of Supplier information.
 */
class Supplier {

    static Connection connection = SetDatabase.conn;
    static Statement statement = SetDatabase.stmt;
    static ResultSet result = SetDatabase.rs;

    /**
     * Returns Supplier information based on a given Supplier ID.
     * 
     * @param supplier_id
     * @return returnSet
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewSupplierById(int supplier_id) throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Supplier WHERE supplier_id = ?;");
            ps.setInt(1, supplier_id);
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
     * Returns Supplier information for all Suppliers.
     * 
     * @return returnSet
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewAllSupplier() throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Supplier;");
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
     * Adds a new Supplier to the database with given information from the user.
     * 
     * @param supplierId
     * @param supplierName
     * @param phone
     * @param email
     * @param location
     * @throws ParseException
     */
    static void addSupplier(int supplierId, String supplierName, String phone, String email, String location) throws ParseException {
        int id = 0;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Supplier (supplier_id, supplier_name, phone, email, address, status) "
                    + "VALUES (?,?,?,?,?,?);");

            ps.setInt(1, supplierId);
            ps.setString(2, supplierName);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5,location);
            ps.setInt(6, 1);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();

            if (id > 0) {
                System.out.println("Supplier added");

            } else {
                System.out.println("Supplier  not added to database");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Updates Supplier information with the information provided by the user.
     * 
     * @param supplierId
     * @param supplierName
     * @param phone
     * @param email
     * @param location
     * @throws ParseException
     */
    static void updateSupplier(int supplierId, String supplierName, String phone, String email, String location) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Supplier SET supplier_name = ?, phone = ?, email = ?, address = ? WHERE supplier_id= ?;");
            ps.setString(1, supplierName);
            ps.setString(2, phone);
            ps.setString(3,email);
            ps.setString(4, location);
            ps.setInt(5, supplierId);
            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Supplier updated");

            } else {
                System.out.println("Supplier not updated");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    /**
     * Deletes a Supplier with the given Supplier ID.
     * 
     * @param supplierId
     */
    static void deleteSupplier(int supplierId) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Supplier SET status =? WHERE supplier_id= ?;");
            ps.setInt(1, 0);
            ps.setInt(2, supplierId);
            int id = ps.executeUpdate();

            System.out.println(id);

            if (id > 0) {
                System.out.println("Supplier deleted");
            } else {
                System.out.println("No Supplier with that id");
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