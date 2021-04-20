import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * This java file has all the menu options for the Store table
 */
public class StoreMenu {
    static Scanner scan = new Scanner(System.in);
    static int selection = 0;
    static int storeId = 0;
    static String address = "";
    static String phone = "";
    static int managerId = 0;

    /**
     * Prints store info for user
     * 
     * @param result
     * @throws SQLException
     */
    public static void printStoreInfo(ResultSet result) throws SQLException {
        if (!result.next()) {
            System.out.println("No Store with this ID exists");
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
     * Views a store in the DB by their ID
     */
    public static void viewById() throws ClassNotFoundException, SQLException, ParseException {
        do {

            System.out.println("Enter Store ID");
            System.out.println("Enter 0 to go back");
         
            System.out.println("Store ID: ");
            
            selection = scan.nextInt();
            scan.nextLine();

            if (selection > 0) {
                // SQL select statement
                ResultSet result = Store.viewStoreById(selection);
                printStoreInfo(result);
                System.out.println();
                result.close();

                System.out.println();
            } else if (selection < 0) {
                System.out.println("The selection is invalid");
            } else if (selection == 0) {
                System.out.println("Back to Store screen");
                break;
            }

        } while (selection != 0);
    }

    
    /**
     * Views all store in the DB
     */
    public static void viewAllstore() throws ClassNotFoundException, SQLException, ParseException {
        do {

            ResultSet result = Store.viewAllStore();
            printStoreInfo(result);
            System.out.println();
            result.close();

            System.out.println("1. Back to Store Menu");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Back to Store Menu");
                break;
            default:
                System.out.println("Selection not valid");
                break;

            }

        } while (selection != 1);

    }

    /**
     * Deletes a store in the DB
     */
    public static void delete() {
        do {

            System.out.println("Enter Store ID to delete store");
            System.out.println("Enter 0 to go back");

            System.out.println("Store ID: ");

            selection = scan.nextInt();
            scan.nextLine();

            if (selection > 0) {
                // SQL drop statement
                Store.deleteStore(selection);
            } else if (selection < 0) {
                System.out.println("The selection is invalid");
            } else if (selection == 0) {
                System.out.println("Back to store screen");
                break;
            }
            System.out.println();
        } while (selection != 0);
    }

    /**
     * Updates a store in the DB
     * 
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void update() throws ClassNotFoundException, SQLException, ParseException {

        int selection2 = 0;

        do {

            System.out.println("Enter 0 to go back to Store menu");
            System.out.println("Enter Store ID to edit:");

            selection = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (selection > 0) {
                ResultSet result = Store.viewStoreById(selection);
                ResultSet result1 = Staff.viewManager(selection);
                if (!result.next()) {
                    System.out.println("No store with this id exists");
                    System.out.println();
                } else {
                	if (!result1.next()) {
                        System.out.println("No manager at this store");
                        System.out.println();
                    }
                	else {
                    do {
                    	storeId = result.getInt("store_id");
                        address = result.getString("address");
                        phone = result.getString("phone");
                        managerId = result1.getInt("staff_id");
                    } while (result.next());
                    do {
                        System.out.println("1. Address: " + address);
                        System.out.println("2. Phone: " + phone);
                        System.out.println("3. Manager ID: " + managerId);
                        System.out.println("4. Submit edited store");
                        System.out.println("5. Go back to edit another store");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                        case 1:
                            System.out.println("Enter Address:");
                            address = scan.nextLine();
                            break;
                        case 2:
                            System.out.println("Enter Phone Number (10 digits):");
                            phone = scan.nextLine();
                            break;
                        case 3:
                            System.out.println("Enter Manager ID:");
                            managerId = scan.nextInt();
                            break;
                        case 4:
                            // SQL parameters for statement
                            // return entry of new store
                            Store.updateStore(storeId, address, phone, managerId);
                            ResultSet result2 = Store.viewStoreById(storeId);
                            printStoreInfo(result2);
                            System.out.println();
                            result2.close();
                            phone = "";
                            address = "";
                            storeId = 0;
                            managerId = 0;
                            break;
                        case 5:
                            System.out.println("Back to Edit another store");
                            phone = "";
                            address = "";
                            storeId = 0;
                            managerId = 0;
                            break;
                        default:
                            System.out.println("The selection is invalid");
                            break;
                        }
                        System.out.println();
                    } while (selection2 != 4 && selection2 != 5);
                }
                result.close();
            }
            }

        } while (selection != 0);
    }

    /**
     * Adds a store to the DB
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void add() throws ParseException, SQLException, ClassNotFoundException {

        do {
            System.out.println("1. store ID: " + storeId);
            System.out.println("2. Address: " + address);
            System.out.println("3. Phone: " + phone);
            System.out.println("4. Manager ID: " + managerId);
            System.out.println("5. Submit new store");
            System.out.println("6. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
            	System.out.println("Enter Store ID:");
                storeId = scan.nextInt();
                break;
            case 2:
                System.out.println("Enter Address:");
                address = scan.nextLine();
                break;
            case 3:
                System.out.println("Enter Phone Number (10 digits):");
                phone = scan.nextLine();
                break;
            case 4:
            	System.out.println("Enter Manager ID:");
            	managerId = scan.nextInt();
                break;
            case 5:
                // SQL parameters for statement
                // return entry of new store
                Store.addStore(storeId, address, phone, managerId);
                ResultSet result = Store.viewStoreById(storeId);
                printStoreInfo(result);
                System.out.println();
                result.close();
                storeId = 0;
                address = "";
                phone = "";
                managerId = 0;
                break;
            case 6:
                System.out.println("Back to Store menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }
            System.out.println();

        } while (selection != 6);
    }

    /**
     * All the options you can do for the store table
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static void storeOptions() throws ClassNotFoundException, SQLException, ParseException {

        do {
            System.out.println();
            System.out.println("<-- Store-->");
            System.out.println();
            System.out.println("1. View Store By ID");
            System.out.println("2. View All Stores");
            System.out.println("3. Add Store");
            System.out.println("4. Update Store");
            System.out.println("5. Delete Store");
            System.out.println("6. Back to Home Menu");
            System.out.println("Make a selection: ");

            selection = scan.nextInt();

            switch (selection) {
            case 1:
                viewById();
                break;
            case 2:
                viewAllstore();
                break;
            case 3:
                add();
                break;
            case 4:
                update();
                break;
            case 5:
                delete();
                break;
            case 6:
                System.out.println("Back to Home menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }

        } while (selection != 6);
    }
}