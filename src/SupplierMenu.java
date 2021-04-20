import java.util.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * This java file has all the menu options for the Supplier table
 */
public class SupplierMenu {
    static Scanner scan = new Scanner(System.in);
    static int selection = 0;
    static int supplierId = 0;
    static String supplierName = "";
    static String email = "";
    static String phone = "";
    static String location = "";

    /**
     * Prints supplier info
     * 
     * @param result
     * @throws SQLException
     */
    public static void printSupplierInfo(ResultSet result) throws SQLException {
        if (!result.next()) {
            System.out.println("No Supplier with this ID exists");
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
     * Views a supplier in the DB by their ID
     */
    public static void viewById() throws ClassNotFoundException, SQLException, ParseException {
        do {

            System.out.println("Enter Supplier ID");
            System.out.println("Enter 0 to go back");

            System.out.println("Supplier ID: ");

            selection = scan.nextInt();
            scan.nextLine();

            if (selection > 0) {
                // SQL select statement
                ResultSet result = Supplier.viewSupplierById(selection);
                printSupplierInfo(result);
                System.out.println();
                result.close();

                System.out.println();
            } else if (selection < 0) {
                System.out.println("The selection is invalid");
            } else if (selection == 0) {
                System.out.println("Back to Supplier screen");
                break;
            }

        } while (selection != 0);
    }

    /**
     * Views all supplier in the DB
     */
    public static void viewAllSupplier() throws ClassNotFoundException, SQLException, ParseException {
            ResultSet result = Supplier.viewAllSupplier();
            printSupplierInfo(result);
            System.out.println();
            result.close();
    }

    /**
     * Updates a supplier in the DB
     * 
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void update() throws ClassNotFoundException, SQLException, ParseException {

        int selection2 = 0;

        do {

            System.out.println("Enter 0 to go back to Supplier menu");
            System.out.println("Enter Supplier ID to edit:");

            selection = scan.nextInt();
            scan.nextLine();
            System.out.println();

            if (selection > 0) {
                ResultSet result = Supplier.viewSupplierById(selection);
                if (!result.next()) {
                    System.out.println("No Supplier with this id exists");
                    System.out.println();
                } else {
                    do {
                    	supplierId = result.getInt("supplier_id");
                    	supplierName = result.getString("supplier_name");
                        phone = result.getString("phone");
                        email = result.getString("email");
                        location = result.getString("address");
                    } while (result.next());
                    do {
                        System.out.println("1. Supplier Name: " + supplierName);
                        System.out.println("2. Phone: " + phone);
                        System.out.println("3. Email: " + email);
                        System.out.println("4. Location: " + location);
                        System.out.println("5. Submit edited supplier");
                        System.out.println("6. Go back to edit another Supplier member");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                        case 1:
                            System.out.println("Enter Supplier name:");
                            supplierName = scan.nextLine();
                            break;
                        case 2:
                            System.out.println("Enter Phone Number (10 digits):");
                            phone = scan.nextLine();
                            break;
                        case 3:
                            System.out.println("Enter Email:");
                            email = scan.nextLine();
                            break;
                        case 4:
                            System.out.println("Enter Location:");
                            location = scan.nextLine();
                            break;
                        case 5:
                            // SQL parameters for statement
                            // return entry of new supplier
                        	Supplier.updateSupplier(supplierId, supplierName, phone, email, location);
                            ResultSet result2 = Supplier.viewSupplierById(supplierId);
                            printSupplierInfo(result2);
                            System.out.println();
                            result2.close();
                            supplierName = "";
                            email = "";
                            phone = "";
                            location = "";
                            break;
                        case 6:
                            System.out.println("Back to Edit another Supplier");
                            supplierId = 0;
                            supplierName = "";
                            email = "";
                            phone = "";
                            location = "";
                            break;
                        default:
                            System.out.println("The selection is invalid");
                            break;
                        }
                        System.out.println();
                    } while (selection2 != 5 && selection2 != 6);
                }
                result.close();
            }

        } while (selection != 0);
    }

    /**
     * Adds a supplier to the DB
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void add() throws ParseException, SQLException, ClassNotFoundException {

        do {
        	System.out.println("1. Supplier ID: " + supplierId);
        	System.out.println("2. Supplier Name: " + supplierName);
            System.out.println("3. Phone: " + phone);
            System.out.println("4. Email: " + email);
            System.out.println("5. Address: " + location);
            System.out.println("6. Submit new Supplier member");
            System.out.println("7. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Supplier ID:");
                supplierId = scan.nextInt();
                break;
            case 2:
                System.out.println("Enter Supplier name:");
                supplierName = scan.nextLine();
                break;
            case 3:
                System.out.println("Enter Phone Number (10 digits):");
                phone = scan.nextLine();
                break;
            case 4:
                System.out.println("Enter Email:");
                email = scan.nextLine();
                break;
            case 5:
                System.out.println("Enter Location:");
                location = scan.nextLine();
                break;
            case 6:
                // SQL parameters for statement
                // return entry of new supplier
            	Supplier.addSupplier(supplierId, supplierName, phone, email, location);
                ResultSet result = Supplier.viewSupplierById(supplierId);
                printSupplierInfo(result);
                System.out.println();
                result.close();                            
                supplierId = 0;
                supplierName = "";
                email = "";
                phone = "";
                location = "";
                break;
            case 7:
                System.out.println("Back to Supplier menu");
                break;
            default:
                System.out.println("The selection is invalid");
                break;
            }
            System.out.println();

        } while (selection != 7);
    }
    
    /**
     * Deletes a supplier in the DB
     */
    public static void delete() {
        do {

            System.out.println("Enter Supplier ID to delete");
            System.out.println("Enter 0 to go back");

            System.out.println("Supplier ID: ");

            supplierId = scan.nextInt();
            scan.nextLine();

            if (selection > 0) {
                // SQL drop statement
                Supplier.deleteSupplier(supplierId);
            } else if (supplierId < 0) {
                System.out.println("The selection is invalid");
            } else if (supplierId == 0) {
                System.out.println("Back to supplier screen");
                break;
            }
            System.out.println();
        } while (supplierId != 0);
    }
    
    /**
     * All the options you can do for the Supplier table
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static void supplierOptions() throws ClassNotFoundException, SQLException, ParseException {

        do {

            System.out.println("<-- Supplier -->");
            System.out.println();
            System.out.println("1. View Supplier By ID");
            System.out.println("2. View All Suppliers");
            System.out.println("3. Add Supplier");
            System.out.println("4. Update Supplier");
            System.out.println("5. Delete Supplier");
            System.out.println("6. Back to Home Menu");
            System.out.println("Make a selection: ");
            System.out.println();
            selection = scan.nextInt();

            switch (selection) {
            case 1:
                viewById();
                break;
            case 2:
                viewAllSupplier();
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