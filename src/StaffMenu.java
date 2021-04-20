import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * This java file has all the menu options for the Staff table
 */
public class StaffMenu {
    static Scanner scan = new Scanner(System.in);
    static int selection = 0;
    static int staffId = 0;
    static int storeId = 0;
    static String name = "";
    static int age = 0;
    static String phone = "";
    static String address = "";
    static String email = "";
    static String dateHired = "";
    static String jobType = "";

    /**
     * Prints staff info for user
     * 
     * @param result
     * @throws SQLException
     */
    public static void printStaffInfo(ResultSet result) throws SQLException {
        if (!result.next()) {
            System.out.println("No Staff Member with this ID exists");
        } else {
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
    }

    /**
     * Views a staff member in the DB by their ID
     */
    public static void viewById() throws ClassNotFoundException, SQLException, ParseException {
        do {

            System.out.println("Enter staff ID");
            System.out.println("Enter 0 to go back");

            System.out.println("Staff ID: ");

            selection = scan.nextInt();
            scan.nextLine();

            if (selection > 0) {
                ResultSet result = Staff.viewStaffById(selection);
                printStaffInfo(result);
                System.out.println();
                result.close();

                System.out.println();
            } else if (selection < 0) {
                System.out.println("The selection is invalid");
            } else if (selection == 0) {
                System.out.println("Back to Staff screen");
                break;
            }

        } while (selection != 0);
    }

    /**
     * Views all staff members by their jobType
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static void viewByJobTitle() throws ClassNotFoundException, SQLException, ParseException {

        String jobType = "";
        do {

            System.out.println("1. Job Title: " + jobType);
            System.out.println("2. Submit");
            System.out.println("3. Back to Staff");
            System.out.println("Enter # for option: ");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Job Title:");
                jobType = scan.nextLine();
                break;
            case 2:
                // return entries
                ResultSet result = Staff.viewStaffByJobTitle(jobType);
                printStaffInfo(result);
                System.out.println();
                result.close();

                jobType = "";
                break;
            case 3:
                System.out.println("Back to Staff Menu");
                jobType = "";
                break;
            default:
                System.out.println("Selection not valid");
                break;

            }

        } while (selection != 3);
    }

    /**
     * Views all staff members in the DB
     */
    public static void viewAllStaff() throws ClassNotFoundException, SQLException, ParseException {
        do {

            ResultSet result = Staff.viewAllStaff();
            printStaffInfo(result);
            System.out.println();
            result.close();

            System.out.println("1. Back to Staff Menu");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Back to Staff Menu");
                break;
            default:
                System.out.println("Selection not valid");
                break;

            }

        } while (selection != 1);

    }

    /**
     * Deletes a staff member in the DB
     */
    public static void delete() {
        do {

            System.out.println("Enter staff ID to delete staff member");
            System.out.println("Enter 0 to go back");

            System.out.println("Staff ID: ");

            staffId = scan.nextInt();
            scan.nextLine();

            if (staffId > 0) {
                // SQL drop statement
                Staff.deleteStaff(staffId);
            } else if (staffId < 0) {
                System.out.println("The selection is invalid");
            } else if (staffId == 0) {
                System.out.println("Back to Staff screen");
                break;
            }
            System.out.println();
        } while (staffId != 0);
    }

    /**
     * Updates a staff member in the DB
     * 
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void update() throws ClassNotFoundException, SQLException, ParseException {

        int selection2 = 0;

        do {

            System.out.println("Enter 0 to go back to Staff menu");
            System.out.println("Enter Staff ID to edit:");

            selection = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (selection > 0) {
                ResultSet result = Staff.viewStaffById(selection);
                if (!result.next()) {
                    System.out.println("No Staff with this id exists");
                    System.out.println();
                } else {
                    do {
                    	staffId = result.getInt("staff_id");
                    	storeId = result.getInt("store_id");
                        name = result.getString("name");
                        age = result.getInt("age");
                        phone = result.getString("phone");
                        address = result.getString("address");
                        email = result.getString("email");
                        dateHired = result.getString("date_hired");
                        jobType = result.getString("job_type");
                    } while (result.next());
                    do {
                    	System.out.println("1. Store ID: " + storeId);
                        System.out.println("2. Name: " + name);
                        System.out.println("3. Age: " + age);
                        System.out.println("4. Phone: " + phone);
                        System.out.println("5. Address: " + address);
                        System.out.println("6. Email: " + email);
                        System.out.println("7. Date Hired: " + dateHired);
                        System.out.println("8. Job Type: " + jobType);
                        System.out.println("9. Submit edited staff");
                        System.out.println("10. Go back to edit another staff member");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                        case 1:
                            System.out.println("Enter Store ID:");
                            storeId = scan.nextInt();
                            break;
                        case 2:
                            System.out.println("Enter name:");
                            name = scan.nextLine();
                            break;
                        case 3:
                            System.out.println("Enter Age:");
                            age = scan.nextInt();
                            break;
                        case 4:
                            System.out.println("Enter Phone Number (10 digits):");
                            phone = scan.nextLine();
                            break;
                        case 5:
                            System.out.println("Enter Address:");
                            address = scan.nextLine();
                            break;
                        case 6:
                            System.out.println("Enter Email:");
                            email = scan.nextLine();
                            break;
                        case 7:
                            System.out.println("Enter Date Hired:");
                            dateHired = scan.nextLine();
                            break;
                        case 8:
                            System.out.println("Enter Job Type:");
                            jobType = scan.nextLine();
                            break;
                        case 9:
                            // SQL parameters for statement
                            // return entry of new customer
                            Staff.updateStaff(staffId, storeId, name, age, phone, address, email, dateHired, jobType);
                            ResultSet result2 = Staff.viewStaffById(staffId);
                            printStaffInfo(result2);
                            System.out.println();
                            result2.close();
                            storeId = 0;
                            name = "";
                            age = 0;
                            phone = "";
                            address = "";
                            email = "";
                            dateHired = "";
                            jobType = "";
                            staffId = 0;
                            break;
                        case 10:
                            System.out.println("Back to Edit another Staff Member");
                            storeId = 0;
                            name = "";
                            age = 0;
                            phone = "";
                            address = "";
                            email = "";
                            dateHired = "";
                            jobType = "";
                            staffId = 0;
                            break;
                        default:
                            System.out.println("The selection is invalid");
                            break;
                        }
                        System.out.println();
                    } while (selection2 != 9 && selection2 != 10);
                }
                result.close();
            }

        } while (selection != 0);
    }

    /**
     * Assigns role to a staff member in the DB
     * 
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void assign() throws ClassNotFoundException, SQLException, ParseException {

        int selection2 = 0;

        do {

            System.out.println("Enter 0 to go back to Staff menu");
            System.out.println("Enter Staff ID to edit:");

            selection = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (selection > 0) {
                ResultSet result = Staff.viewStaffById(selection);
                if (!result.next()) {
                    System.out.println("No Staff with this id exists");
                    System.out.println();
                } else {
                    do {
                    	storeId = result.getInt("store_id");
                        jobType = result.getString("job_type");
                    } while (result.next());
                    do {
                    	System.out.println("1. Store ID: " + storeId);
                        System.out.println("2. Job Type: " + jobType);
                        System.out.println("3. Submit assigned staff");
                        System.out.println("4. Go back to assign another staff member");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                        case 1:
                            System.out.println("Enter Store ID:");
                            storeId = scan.nextInt();
                            break;
                        case 2:
                            System.out.println("Enter Job Type:");
                            jobType = scan.nextLine();
                            break;
                        case 3:
                            // SQL parameters for statement
                            // return entry of new customer
                            Staff.updateStaff(staffId, storeId, name, age, phone, address, email, dateHired, jobType);
                            ResultSet result2 = Staff.viewStaffById(staffId);
                            printStaffInfo(result2);
                            System.out.println();
                            result2.close();
                            name = "";
                            age = 0;
                            phone = "";
                            address = "";
                            staffId = 0;
                            break;
                        case 4:
                            System.out.println("Back to Edit another Staff Member");
                            storeId = 0;
                            name = "";
                            age = 0;
                            phone = "";
                            address = "";
                            email = "";
                            dateHired = "";
                            jobType = "";
                            staffId = 0;
                            break;
                        default:
                            System.out.println("The selection is invalid");
                            break;
                        }
                        System.out.println();
                    } while (selection2 != 3 && selection2 != 4);
                }
                result.close();
            }

        } while (selection != 0);
    }

    /**
     * Adds a staff member to the DB
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void add() throws ParseException, SQLException, ClassNotFoundException {

        do {
            System.out.println("1. Staff ID: " + staffId);
            System.out.println("2. Store ID: " + storeId);
            System.out.println("3. Name: " + name);
            System.out.println("4. Age: " + age);
            System.out.println("5. Phone: " + phone);
            System.out.println("6. Address: " + address);
            System.out.println("7. Email: " + email);
            System.out.println("8. Date Hired: " + dateHired);
            System.out.println("9. Job Type: " + jobType);
            System.out.println("10. Submit new staff member");
            System.out.println("11. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
            	System.out.println("Enter Staff's ID:");
                staffId = scan.nextInt();
                break;
            case 2:
            	System.out.println("Enter Store ID:");
            	storeId = scan.nextInt();
                break;
            case 3:
                System.out.println("Enter name:");
                name = scan.nextLine();
                break;
            case 4:
                System.out.println("Enter Age:");
                age = scan.nextInt();
                break;
            case 5:
                System.out.println("Enter Phone Number (10 digits):");
                phone = scan.nextLine();
                break;
            case 6:
                System.out.println("Enter Address:");
                address = scan.nextLine();
                break;
            case 7:
                System.out.println("Enter Email:");
                email = scan.nextLine();
                break;
            case 8:
                System.out.println("Enter Date Hired:");
                dateHired = scan.nextLine();
                break;
            case 9:
                System.out.println("Enter Job Type:");
                jobType = scan.nextLine();
                break;
            case 10:
                // SQL parameters for statement
                // return entry of new staff
                Staff.addStaff(staffId, storeId, name, age, phone, address, email, dateHired, jobType);
                ResultSet result = Staff.viewStaffById(staffId);
                printStaffInfo(result);
                System.out.println();
                result.close();
                staffId = 0;
                name = "";
                age = 0;
                phone = "";
                address = "";
                email = "";
                dateHired = "";
                jobType = "";
                break;
            case 11:
                System.out.println("Back to Staff menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }
            System.out.println();

        } while (selection != 11);
    }

    /**
     * Adds a staff member to the DB
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void add(int staff_id, int store_id) throws ParseException, SQLException, ClassNotFoundException {

        do {
            System.out.println("1. Name: " + name);
            System.out.println("2. Age: " + age);
            System.out.println("3. Phone: " + phone);
            System.out.println("4. Address: " + address);
            System.out.println("5. Email: " + email);
            System.out.println("6. Date Hired: " + dateHired);
            System.out.println("7. Job Type: " + jobType);
            System.out.println("8. Submit new staff member");
            System.out.println("9. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter name:");
                name = scan.nextLine();
                break;
            case 2:
                System.out.println("Enter Age:");
                age = scan.nextInt();
                break;
            case 3:
                System.out.println("Enter Phone Number (10 digits):");
                phone = scan.nextLine();
                break;
            case 4:
                System.out.println("Enter Address:");
                address = scan.nextLine();
                break;
            case 5:
                System.out.println("Enter Email:");
                email = scan.nextLine();
                break;
            case 6:
                System.out.println("Enter Date Hired:");
                dateHired = scan.nextLine();
                break;
            case 7:
                System.out.println("Enter Job Type:");
                jobType = scan.nextLine();
                break;
            case 8:
                // SQL parameters for statement
                // return entry of new staff
                Staff.addStaff(staff_id, store_id, name, age, phone, address, email, dateHired, jobType);
                ResultSet result = Staff.viewStaffById(staffId);
                printStaffInfo(result);
                System.out.println();
                result.close();
                name = "";
                age = 0;
                phone = "";
                address = "";
                email = "";
                dateHired = "";
                jobType = "";
                break;
            case 9:
                System.out.println("Back to Staff menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }
            System.out.println();

        } while (selection != 9);
    }

    
    /**
     * All the options you can do for the Staff table
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static void staffOptions() throws ClassNotFoundException, SQLException, ParseException {

        do {

            System.out.println("<-- Staff -->");
            System.out.println();
            System.out.println("1. View Staff By ID");
            System.out.println("2. View Staff By Job Title");
            System.out.println("3. View All Staff");
            System.out.println("4. Add Staff Member");
            System.out.println("5. Update Staff Member");
            System.out.println("6. Assign Role Staff Member");
            System.out.println("7. Delete Staff Member");
            System.out.println("8. Back to Home Menu");
            System.out.println("Make a selection: ");

            selection = scan.nextInt();

            switch (selection) {
            case 1:
                viewById();
                break;
            case 2:
                viewByJobTitle();
                break;
            case 3:
                viewAllStaff();
                break;
            case 4:
                add();
                break;
            case 5:
                update();
                break;
            case 6:
                assign();
                break;
            case 7:
                delete();
                break;
            case 8:
                System.out.println("Back to Home menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }

        } while (selection != 8);
    }
}