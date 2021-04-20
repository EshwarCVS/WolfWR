import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * This java file has all the menu options for the Customer table
 */
public class CustomerMenu {
    static Scanner scan = new Scanner(System.in);
    static int selection = 0;
    static int customerId = 0;
    static String firstName = "";
    static String lastName = "";
    static String membershipLevel = "";
    static String email = "";
    static int age = 0;
    static String phone = "";
    static String address = "";
    static String activeStatus = "";
    static int staffId = 0;
    static String signupDate = "";

    /**
     * Prints customer info for user
     * 
     * @param result
     * @throws SQLException
     */
    public static void printCustomerInfo(ResultSet result) throws SQLException {
        if (!result.next()) {
            System.out.println("No Customer with this ID exists");
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
     * Views a customer in the DB by their ID
     */
    public static void viewById() throws ClassNotFoundException, SQLException, ParseException {
        do {

            System.out.println("Enter Customer ID");
            System.out.println("Enter 0 to go back");

            System.out.println("Customer ID: ");

            selection = scan.nextInt();
            scan.nextLine();

            if (selection > 0) {
                // SQL select statement
                ResultSet result = Customer.viewCustomerById(selection);
                printCustomerInfo(result);
                System.out.println();
                result.close();

                System.out.println();
            } else if (selection < 0) {
                System.out.println("The selection is invalid");
            } else if (selection == 0) {
                System.out.println("Back to Customer screen");
                break;
            }

        } while (selection != 0);
    }

    /**
     * Views all customers in the DB
     */
    public static void viewAllCustomer() throws ClassNotFoundException, SQLException, ParseException {
        do {

            ResultSet result = Customer.viewAllCustomer();
            printCustomerInfo(result);
            System.out.println();
            result.close();

            System.out.println("1. Back to Customer Menu");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Back to Customer Menu");
                break;
            default:
                System.out.println("Selection not valid");
                break;

            }

        } while (selection != 1);

    }

    /**
     * Updates a customer in the DB
     * 
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void update() throws ClassNotFoundException, SQLException, ParseException {

        int selection2 = 0;

        do {

            System.out.println("Enter 0 to go back to Customer menu");
            System.out.println("Enter Customer ID to edit:");

            selection = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (selection > 0) {
                ResultSet result = Customer.viewCustomerById(selection);
                if (!result.next()) {
                    System.out.println("No Customer with this id exists");
                    System.out.println();
                } else {
                    do {
                    	customerId = result.getInt("customer_id");
                    	firstName = result.getString("first_name");
                    	lastName = result.getString("last_name");
                        membershipLevel = result.getString("membership_level");
                        email = result.getString("email");
                        age = result.getInt("age");
                        phone = result.getString("phone");
                        address = result.getString("address");
                    } while (result.next());
                    do {
                        System.out.println("1. First Name: " + firstName);
                        System.out.println("2. Last Name: " + lastName);
                        System.out.println("3. Membership Level: " + membershipLevel);
                        System.out.println("4. Email: " + email);
                        System.out.println("5. Age: " + age);
                        System.out.println("6. Phone: " + phone);
                        System.out.println("7. Address: " + address);
                        System.out.println("8. Submit edited customer");
                        System.out.println("9. Go back to edit another customer member");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                        case 1:
                            System.out.println("Enter First name:");
                            firstName = scan.nextLine();
                            break;
                        case 2:
                            System.out.println("Enter Last name:");
                            lastName = scan.nextLine();
                            break;
                        case 3:
                            System.out.println("Enter Membership Level:");
                        	membershipLevel = scan.nextLine();
                            break;
                        case 4:
                            System.out.println("Enter Email:");
                            email = scan.nextLine();
                            break;
                        case 5:
                            System.out.println("Enter Age:");
                            age = scan.nextInt();
                            break;
                        case 6:
                            System.out.println("Enter Phone Number (10 digits):");
                            phone = scan.nextLine();
                            break;
                        case 7:
                            System.out.println("Enter Address:");
                            address = scan.nextLine();
                            break;
                        case 8:
                            
                            // SQL parameters for statement
                            // return entry of new customer
                            Customer.updateCustomer(customerId, firstName, lastName, membershipLevel, email, age, phone, 
                            		address);
                            ResultSet result2 = Customer.viewCustomerById(customerId);
                            printCustomerInfo(result2);
                            System.out.println();
                            result2.close();
                            firstName = "";
                            lastName = "";
                            membershipLevel = "";
                            age = 0;
                            phone = "";
                            address = "";
                            email = "";
                            break;
                        case 9:
                            System.out.println("Back to Edit another Customer");
                            customerId = 0;
                            firstName = "";
                            lastName = "";
                            membershipLevel = "";
                            age = 0;
                            phone = "";
                            address = "";
                            email = "";
                            activeStatus = "";
                            break;
                        default:
                            System.out.println("The selection is invalid");
                            break;
                        }
                        System.out.println();
                    } while (selection2 != 9 && selection2 != 8);
                }
                result.close();
            }

        } while (selection != 0);
    }

    /**
     * Adds a customer to the DB
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void add() throws ParseException, SQLException, ClassNotFoundException {

        do {
        	System.out.println("1. Customer ID: " + customerId);
            System.out.println("2. First Name: " + firstName);
            System.out.println("3. Last Name: " + lastName);
            System.out.println("4. Membership Level: " + membershipLevel);
            System.out.println("5. Email: " + email);
            System.out.println("6. Age: " + age);
            System.out.println("7. Phone: " + phone);
            System.out.println("8. Address: " + address);
            System.out.println("9. Submit new customer member");
            System.out.println("10. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Customer ID:");
                customerId = scan.nextInt();
                break;
            case 2:
                System.out.println("Enter First name:");
                firstName = scan.nextLine();
                break;
            case 3:
                System.out.println("Enter Last name:");
                lastName = scan.nextLine();
                break;
            case 4:
                System.out.println("Enter Membership Level:");
            	membershipLevel = scan.nextLine();
                break;
            case 5:
                System.out.println("Enter Email:");
                email = scan.nextLine();
                break;
            case 6:
                System.out.println("Enter Age:");
                age = scan.nextInt();
                break;
            case 7:
                System.out.println("Enter Phone Number (10 digits):");
                phone = scan.nextLine();
                break;
            case 8:
                System.out.println("Enter Address:");
                address = scan.nextLine();
                break;
            case 9:
                // SQL parameters for statement
                // return entry of new customer
                Customer.addCustomer(customerId, firstName, lastName, membershipLevel, email, age, phone, address);
                ResultSet result = Customer.viewCustomerById(customerId);
                printCustomerInfo(result);
                System.out.println();
                result.close();
                customerId = 0;
                firstName = "";
                lastName = "";
                membershipLevel = "";
                age = 0;
                phone = "";
                address = "";
                email = "";
                break;
            case 10:
                System.out.println("Back to Customer menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }
            System.out.println();

        } while (selection != 10);
    }
    
    /**
     * Signup a customer to the DB
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void signup() throws ParseException, SQLException, ClassNotFoundException {

        do {
        	System.out.println("1. Customer ID: " + customerId);
            System.out.println("2. Staff ID: " + staffId);
            System.out.println("3. Signup Date: " + signupDate);
            System.out.println("4. Submit new customer member");
            System.out.println("5. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Customer ID:");
                customerId = scan.nextInt();
                break;
            case 2:
                System.out.println("Enter Staff ID:");
                staffId = scan.nextInt();
                break;
            case 3:
                System.out.println("Enter Signup Date:");
                signupDate = scan.nextLine();
                break;
            case 4:
                // SQL parameters for statement
                // return entry of new customer
                Customer.signupCustomer(customerId, staffId, signupDate);
                ResultSet result = Customer.viewCustomerById(customerId);
                printCustomerInfo(result);
                System.out.println();
                result.close();
                customerId = 0;
                staffId = 0;
                signupDate = "";
                break;
            case 5:
                System.out.println("Back to Customer menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }
            System.out.println();

        } while (selection != 5);
    }
    
    /**
     * Cancels membership of a customer in the DB
     * 
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void cancel() throws ClassNotFoundException, SQLException, ParseException {

        int selection2 = 0;

        do {

            System.out.println("Enter 0 to go back to Customer menu");
            System.out.println("Enter Customer ID to cancel membership:");

            selection = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (selection > 0) {
                ResultSet result = Customer.viewCustomerById(selection);
                if (!result.next()) {
                    System.out.println("No Customer with this id exists");
                    System.out.println();
                } else {
                    do {
                    	customerId = result.getInt("customer_id");
                    } while (result.next());
                    do {
                        System.out.println("1. Cancel membership customer");
                        System.out.println("2. Go back to cancel membership of another customer");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                        case 1:
                            // SQL parameters for statement
                            // return entry of new customer
                            Customer.cancelCustomer(customerId);
                            ResultSet result2 = Customer.viewCustomerById(customerId);
                            printCustomerInfo(result2);
                            System.out.println();
                            result2.close();
                            customerId = 0;
                            break;
                        case 2:
                            System.out.println("Back to Upgrade another Customer");
                            customerId = 0;
                            break;
                        default:
                            System.out.println("The selection is invalid");
                            break;
                        }
                        System.out.println();
                    } while (selection2 != 1 && selection2 != 2);
                }
                result.close();
            }

        } while (selection != 0);
    }
    
    /**
     * Upgrade a customer in the DB
     * 
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void upgrade() throws ClassNotFoundException, SQLException, ParseException {

        int selection2 = 0;

        do {

            System.out.println("Enter 0 to go back to Customer menu");
            System.out.println("Enter Customer ID to edit:");

            selection = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (selection > 0) {
                ResultSet result = Customer.viewCustomerById(selection);
                if (!result.next()) {
                    System.out.println("No Customer with this id exists");
                    System.out.println();
                } else {
                    do {
                    	customerId = result.getInt("customer_id");
                        membershipLevel = result.getString("membership_level");
                    } while (result.next());
                    do {
                        System.out.println("1. Membership Level: " + membershipLevel);
                        System.out.println("2. Submit upgraded customer");
                        System.out.println("3. Go back to upgrade another customer member");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                        case 1:
                            System.out.println("Enter Membership Level:");
                        	membershipLevel = scan.nextLine();
                            break;
                        case 2:
                            // SQL parameters for statement
                            // return entry of new customer
                            Customer.upgradeCustomer(customerId, membershipLevel);
                            ResultSet result2 = Customer.viewCustomerById(customerId);
                            printCustomerInfo(result2);
                            System.out.println();
                            result2.close();
                            membershipLevel = "";
                            break;
                        case 3:
                            System.out.println("Back to Upgrade another Customer");
                            customerId = 0;
                            membershipLevel = "";
                            break;
                        default:
                            System.out.println("The selection is invalid");
                            break;
                        }
                        System.out.println();
                    } while (selection2 != 2 && selection2 != 3);
                }
                result.close();
            }

        } while (selection != 0);
    }


    /**
     * Denigrate a customer in the DB
     * 
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void denigrate() throws ClassNotFoundException, SQLException, ParseException {

        int selection2 = 0;

        do {

            System.out.println("Enter 0 to go back to Customer menu");
            System.out.println("Enter Customer ID to edit:");

            selection = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (selection > 0) {
                ResultSet result = Customer.viewCustomerById(selection);
                if (!result.next()) {
                    System.out.println("No Customer with this id exists");
                    System.out.println();
                } else {
                    do {
                    	customerId = result.getInt("customer_id");
                        membershipLevel = result.getString("membership_level");
                    } while (result.next());
                    do {
                        System.out.println("1. Membership Level: " + membershipLevel);
                        System.out.println("2. Submit denigrated customer");
                        System.out.println("3. Go back to denigrate another customer member");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                        case 1:
                            System.out.println("Enter Membership Level:");
                        	membershipLevel = scan.nextLine();
                            break;
                        case 2:
                            // SQL parameters for statement
                            // return entry of new customer
                            Customer.downgradeCustomer(customerId, membershipLevel);
                            ResultSet result2 = Customer.viewCustomerById(customerId);
                            printCustomerInfo(result2);
                            System.out.println();
                            result2.close();
                            membershipLevel = "";
                            break;
                        case 3:
                            System.out.println("Back to Downgrade another Customer");
                            customerId = 0;
                            membershipLevel = "";
                            break;
                        default:
                            System.out.println("The selection is invalid");
                            break;
                        }
                        System.out.println();
                    } while (selection2 != 2 && selection2 != 3);
                }
                result.close();
            }

        } while (selection != 0);
    }

    /**
     * All the options you can do for the Customer table
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static void customerOptions() throws ClassNotFoundException, SQLException, ParseException {

        do {

            System.out.println("<-- Customer -->");
            System.out.println();
            System.out.println("1. View Customer By ID");
            System.out.println("2. View All Customers");
            System.out.println("3. Sign up Customer");
            System.out.println("4. Add Customer");
            System.out.println("5. Update Customer");
            System.out.println("6. Cancel membership of Customer");
            System.out.println("7. Upgrade Customer");
            System.out.println("8. Denigrate Customer");
            System.out.println("9. Back to Home Menu");
            System.out.println("Make a selection: ");

            selection = scan.nextInt();

            switch (selection) {
            case 1:
                viewById();
                break;
            case 2:
                viewAllCustomer();
                break;
            case 3:
                signup();
                break;
            case 4:
                add();
                break;
            case 5:
                update();
                break;
            case 6:
                cancel();
                break;
            case 7:
            	upgrade();
            	break;
            case 8:
            	denigrate();
            	break;
            case 9:
                System.out.println("Back to Home menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }

        } while (selection != 9);
    }
}