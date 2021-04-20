import java.sql.*;
import java.text.ParseException;

/**
 * This java file holds all the SQL code for the Staff table
 */
class Staff {

    static Connection connection = SetDatabase.conn;
    static Statement statement = SetDatabase.stmt;
    static ResultSet result = SetDatabase.rs;

    /**
     * Looks for Staff in DB by inputed staff ID
     * 
     * @param staff_id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewStaffById(int staff_id) throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Staff WHERE staff_id = ?;");
            ps.setInt(1, staff_id);
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
     * Looks for all staff members by inputed job type
     * 
     * @param jobType
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewStaffByJobTitle(String jobType)
            throws ClassNotFoundException, SQLException, ParseException {

        jobType.toLowerCase();
        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Staff WHERE job_type = ?;");
            ps.setString(1, jobType);
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
     * Gets all staff members in the DB
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewAllStaff() throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Staff;");
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
     * Gets manager in the store
     * 
     * @param storeId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewManager(int storeId) throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT staff_id FROM Staff WHERE store_id = ? AND job_type='Manager';");
            ps.setInt(1, storeId);
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
     * Adds a staff member to DB with inputed parameters from the user
     * 
     * @param staffId
     * @param storeId
     * @param name
     * @param age
     * @param phone
     * @param address
     * @param email
     * @param dateHired
     * @param jobType
     * @throws ParseException
     */
    static void addStaff(int staffId, int storeId, String name, int age, String phone, String address, String email, String dateHired,
    		String jobType) throws ParseException {
        int id = 0;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Staff (staff_id, store_id, name, age, phone, address, email, date_hired, job_type, status) "
                    + "VALUES (?,?,?,?,?,?,?,?,?);");

            ps.setInt(1, staffId);
            ps.setInt(2, storeId);
            ps.setString(3, name);
            ps.setInt(4, age);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7,email);
            ps.setString(8, dateHired);
            ps.setString(9, jobType);
            ps.setInt(10, 1);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();

            if (id > 0) {
                System.out.println("Staff Member added");

            } else {
                System.out.println("Staff Member  not added to database");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Updates a staff member with inputed parameters from the user
     * 
     * @param staffId
     * @param storeId
     * @param name
     * @param age
     * @param phone
     * @param address
     * @param email
     * @param dateHired
     * @param jobType
     * @throws ParseException
     */
    static void updateStaff(int staffId, int storeId, String name, int age, String phone, String address, String email, String dateHired,
    		String jobType) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Staff SET store_id = ?, name = ?, age = ?, phone = ?, address = ?, email = ?, date_hired = ?, "
                    + "job_type = ? WHERE staff_id= ?;");
            ps.setInt(1, storeId);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6,email);
            ps.setString(7, dateHired);
            ps.setString(8, jobType);
            ps.setInt(9, staffId);
            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Staff Member updated");

            } else {
                System.out.println("Staff Member not updated");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    /**
     * Assign role to a staff member with inputed parameters from the user
     * 
     * @param staffId
     * @param storeId
     * @param jobType
     * @throws ParseException
     */
    static void assignRoleStaff(int staffId, int storeId, String jobType) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Staff SET store_id = ?, job_type = ? WHERE staff_id= ?;");
            ps.setInt(1, storeId);
            ps.setString(2, jobType);
            ps.setInt(3, staffId);
            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Staff Member assigned");

            } else {
                System.out.println("Staff Member not assigned");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    /**
     * Deletes a staff member by inputed staff ID
     * 
     * @param staff_id
     */
    static void deleteStaff(int staff_id) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Staff SET status = ? WHERE staff_id= ?;");
            ps.setInt(1, 0);
            ps.setInt(2, staff_id);
            int id = ps.executeUpdate();

            if (id > 0) {
                System.out.println("Staff deleted");
            } else {
                System.out.println("No Staff Member with that id");
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