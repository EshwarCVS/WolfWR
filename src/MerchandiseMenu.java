import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * This java file has all the menu options for the Merchandise table--allows user to navigate to operations related
 * to the merchandise table they would like to execute
 */
public class MerchandiseMenu {
    static Scanner scan = new Scanner(System.in);
    static int selection = 0;
    static int selection1;
    static int productId = 0;
    static int supplierID = 0;
    static String productName = "";
    static int totalQuantity = 0;
    static double buyPrice = 0;
    static double marketPrice = 0;
    static String productionDate = "";
    static String discountStart = "";
    static String expirationDate = "";
    static double discount = 0;
    static String discountExpiry = "";
    static int storeId = 0;
    static int quantityInStock = 0;

    /**
     * Prints Merchandise info
     *
     * @param result
     * @throws SQLException
     */
    public static void printMerchandiseInfo(ResultSet result) throws SQLException {
        if (!result.next()) {
            System.out.println("No Merchandise with this ID exists");
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
     * Views Merchandise in the DB by its ID. Prompts user for the product ID they would like to view.
     */
    public static void viewById() throws ClassNotFoundException, SQLException, ParseException {
        int selection1 = 0;
        do {

            System.out.println("Enter Product ID and its Supplier ID");
            System.out.println("Enter 0 to go back");

            System.out.println("Product ID: ");

            selection = scan.nextInt();
            scan.nextLine();

            System.out.println("Supplier ID: ");

            selection1 = scan.nextInt();
            scan.nextLine();

            if (selection > 0 & selection1 > 0) {
                // SQL select statement
                ResultSet result = Merchandise.viewMerchandiseById(selection, selection1);
                printMerchandiseInfo(result);
                System.out.println();
                result.close();

                System.out.println();
            } else if (selection < 0 || selection1 < 0) {
                System.out.println("The selection is invalid");
            } else if (selection == 0) {
                System.out.println("Back to Supplier screen");
                break;
            }

        } while (selection != 0);
    }

    /**
     * Views all merchandise in the DB.
     */
    public static void viewAllMerchandise() throws ClassNotFoundException, SQLException, ParseException {
        do {

            ResultSet result = Merchandise.viewAllMerchandise();
            printMerchandiseInfo(result);
            System.out.println();
            result.close();

            System.out.println("1. Back to Supplier Menu");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
                case 1:
                    System.out.println("Back to Supplier Menu");
                    break;
                default:
                    System.out.println("Selection not valid");
                    break;

            }
        } while (selection != 1);
    }

    /**
     * Updates Merchandise in the DB. Prompts user for merchandise fields they would like to update.
     *
     * @throws ParseException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void update() throws ClassNotFoundException, SQLException, ParseException {
        int selection1 = 0;
        int selection2 = 0;

        do {
            System.out.println("Enter 0 to go back to Merchandise menu");
            System.out.println("Enter Product ID and its Supplier ID to edit:");
            System.out.println("Product ID:");
            selection1 = scan.nextInt();
            scan.nextLine();
            System.out.println("Supplier ID:");
            selection = scan.nextInt();
            System.out.println();

            if (selection1 > 0 && selection> 0) {
                ResultSet result = Merchandise.viewMerchandiseById(selection1, selection);
                if (!result.next()) {
                    System.out.println("No Product with these IDs exists");
                    System.out.println();
                } else {
                    do {productId = result.getInt("product_id");
                        supplierID = result.getInt("supplier_id");
                        productName = result.getString("product_name");
                        totalQuantity = result.getInt("total_quantity");
                        buyPrice = result.getDouble("buy_price");
                        marketPrice = result.getDouble("market_price");
                        productionDate = result.getString("production_date");
                        expirationDate = result.getString("expiration_date");
                        discount = result.getDouble("discount");
                        discountStart = result.getString("discount_start");
                        discountExpiry = result.getString("discount_expiry");
                    } while (result.next());
                    do {
                        System.out.println("1. Product Name: " + productName);
                        System.out.println("2. Total Quantity: " + totalQuantity);
                        System.out.println("3. Buy Price: " + buyPrice);
                        System.out.println("4. Market Price: " + marketPrice);
                        System.out.println("5. Production Date: " + productionDate);
                        System.out.println("6. Expiration Date: " + expirationDate);
                        System.out.println("7. Discount: " + discount);
                        System.out.println("8. Discount Start: " + discountStart);
                        System.out.println("9. Discount Expiry: " + discountExpiry);
                        System.out.println("10. Submit edited product");
                        System.out.println("11. Go back to edit another product");

                        System.out.println("Enter # to update info:");
                        System.out.println("Current Input will show next to attribute");

                        selection2 = scan.nextInt();
                        scan.nextLine();

                        switch (selection2) {
                            case 1:
                                System.out.println("Enter Product Name:");
                                productName = scan.nextLine();
                                break;
                            case 2:
                                System.out.println("Enter Total Quantity:");
                                totalQuantity = scan.nextInt();
                                break;
                            case 3:
                                System.out.println("Enter Buy Price:");
                                buyPrice = scan.nextInt();
                                break;
                            case 4:
                                System.out.println("Enter Market Price:");
                                marketPrice = scan.nextDouble();
                                break;
                            case 5:
                                System.out.println("Enter Production Date:");
                                productionDate = scan.nextLine();
                                break;
                            case 6:
                                System.out.println("Enter Expiration Date:");
                                expirationDate = scan.nextLine();
                                break;
                            case 7:
                                System.out.println("Enter Discount Info:");
                                discount = scan.nextDouble();
                                break;
                            case 8:
                                System.out.println("Enter Discount Start:");
                                discountStart = scan.nextLine();
                                break;
                            case 9:
                                System.out.println("Enter Discount Expiry:");
                                discountExpiry = scan.nextLine();
                                break;
                            case 10:
                                // SQL parameters for statement
                                // return entry of new supplier
                                Merchandise.updateProduct(productId, supplierID, productName, totalQuantity, buyPrice, marketPrice, productionDate, expirationDate, discount, discountStart, discountExpiry);
                                ResultSet result2 = Merchandise.viewMerchandiseById(productId, supplierID);
                                printMerchandiseInfo(result2);
                                System.out.println();
                                result2.close();
                                productId = 0;
                                supplierID = 0;
                                productName = "";
                                totalQuantity = 0;
                                buyPrice = 0;
                                marketPrice = 0;
                                productionDate = "";
                                expirationDate = "";
                                discount = 0;
                                discountStart = "";
                                discountExpiry = "";
                                break;
                            case 11:
                                System.out.println("Back to Edit another Product");
                                productId = 0;
                                supplierID = 0;
                                productName = "";
                                totalQuantity = 0;
                                buyPrice = 0;
                                marketPrice = 0;
                                productionDate = "";
                                expirationDate = "";
                                discount = 0;
                                discountStart = "";
                                discountExpiry = "";
                                break;
                            default:
                                System.out.println("The selection is invalid");
                                break;
                        }
                        System.out.println();
                    } while ( selection2 != 11);
                }
                result.close();
            }

        } while (selection != 0 && selection1 !=0);
    }

    /**
     * Adds merchandise to the DB--prompts user for necessary merchandise fields
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void add() throws ParseException, SQLException, ClassNotFoundException {

        do {
            System.out.println("1. Product ID: " + productId);
            System.out.println("2. Supplier ID: " + supplierID);
            System.out.println("3. Product Name: " + productName);
            System.out.println("4. Total Quantity: " + totalQuantity);
            System.out.println("5. Buy Price: " + buyPrice);
            System.out.println("6. Market Price: " + marketPrice);
            System.out.println("7. Production Date: " + productionDate);
            System.out.println("8. Expiration Date: " + expirationDate);
            System.out.println("9. Discount: " + discount);
            System.out.println("10. Discount Start: " + discountStart);
            System.out.println("11. Discount Expiry: " + discountExpiry);

            System.out.println("12. Submit New Merchandise");
            System.out.println("13. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
                case 1:
                    System.out.println("Enter Product ID:");
                    productId = scan.nextInt();
                    break;
                case 2:
                    System.out.println("Enter Supplier ID:");
                    supplierID = scan.nextInt();
                    break;
                case 3:
                    System.out.println("Enter Product Name:");
                    productName = scan.nextLine();
                    break;
                case 4:
                    System.out.println("Enter Total Quantity:");
                    totalQuantity = scan.nextInt();
                    break;
                case 5:
                    System.out.println("Enter Buy Price:");
                    buyPrice = scan.nextInt();
                    break;
                case 6:
                    System.out.println("Enter Market Price:");
                    marketPrice = scan.nextDouble();
                    break;

                case 7:
                    System.out.println("Enter Production Date:");
                    productionDate = scan.nextLine();
                    break;
                case 8:
                    System.out.println("Enter Expiration Date:");
                    expirationDate = scan.nextLine();
                    break;
                case 9:
                    System.out.println("Enter Discount Info:");
                    discount = scan.nextDouble();
                    break;
                case 10:
                    System.out.println("Enter Discount Start:");
                    discountStart = scan.nextLine();
                    break;
                case 11:
                    System.out.println("Enter Discount Expiry:");
                    discountExpiry = scan.nextLine();
                    break;
                case 12:
                    // SQL parameters for statement
                    // return entry of new supplier
                    Merchandise.addProduct(productId, supplierID, productName, totalQuantity, buyPrice, marketPrice, productionDate, expirationDate, discount, discountStart, discountExpiry);
                    ResultSet result = Merchandise.viewMerchandiseById(productId, supplierID);
                    printMerchandiseInfo(result);
                    System.out.println();
                    result.close();
                    productId = 0;
                    supplierID = 0;
                    productName = "";
                    totalQuantity = 0;
                    buyPrice = 0;
                    marketPrice = 0;
                    productionDate = "";
                    expirationDate = "";
                    discount = 0;
                    discountStart = "";
                    discountExpiry = "";
                    break;
                case 13:
                    System.out.println("Back to Merchandise menu");
                    break;
                default:
                    System.out.println("The selection is invalid");
                    break;
            }
            System.out.println();

        } while (selection != 13);
    }

    /**
     * Deletes merchandise in the DB--prompts user for ID of merchandise they would like to delete
     */
    public static void delete() {
        do {

            System.out.println("Enter Product ID with its Supplier ID to delete");
            System.out.println("Enter 0 to go back");

            System.out.println("Product ID: ");

            selection = scan.nextInt();
            scan.nextLine();


            System.out.println("Supplier ID: ");
            selection1 = scan.nextInt();
            scan.nextLine();

            if (selection > 0 & selection1> 0) {
                // SQL drop statement
                Merchandise.deleteProduct(selection, selection1);
            } else if (selection < 0 || selection1 < 0) {
                System.out.println("The selection is invalid");
            } else if (selection == 0) {
                System.out.println("Back to merchandise screen");
                break;
            }
            System.out.println();
        } while (selection != 0);
    }
    /**
     * Prints Merchandise info
     *
     * @param result
     * @throws SQLException
     */
    public static void printInventoryInfo(ResultSet result) throws SQLException {
        if (!result.next()) {
            System.out.println("No Merchandise with this ID exists");
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
     * Views all merchandise in the DB
     */
    public static void viewAllInventory() throws ClassNotFoundException, SQLException, ParseException {
        do {

            ResultSet result = Merchandise.viewAllInventory();
            printInventoryInfo(result);
            System.out.println();
            result.close();

            System.out.println("1. Back to Inventory Menu");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
                case 1:
                    System.out.println("Back to Inventory Menu");
                    break;
                default:
                    System.out.println("Selection not valid");
                    break;

            }

        } while (selection != 1);

    }



    /**
     * Adds inventory to the DB
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void assignInventory() throws ParseException, SQLException, ClassNotFoundException {

        do {
            System.out.println("1. Product ID: " + productId);
            System.out.println("2. Supplier ID: " + supplierID);
            System.out.println("3. Store ID: " + storeId);
            System.out.println("4. Total Quantity In Stock: " + quantityInStock);

            System.out.println("5. Submit Return of Product");
            System.out.println("6. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
                case 1:
                    System.out.println("Enter Product ID:");
                    productId = scan.nextInt();
                    break;
                case 2:
                    System.out.println("Enter Supplier ID:");
                    supplierID = scan.nextInt();
                    break;
                case 3:
                    System.out.println("Enter Store ID:");
                    storeId = scan.nextInt();
                    break;
                case 4:
                    System.out.println("Enter Total Quantity In Stock:");
                    quantityInStock = scan.nextInt();
                    break;
                case 5:
                    // SQL parameters for statement
                    // return entry of new supplier
                    Merchandise.updateInventoryReturn(productId, supplierID, storeId, quantityInStock);
                    ResultSet result = Merchandise.viewStockById(productId, supplierID, storeId);
                    printInventoryInfo(result);
                    System.out.println();
                    result.close();
                    productId = 0;
                    supplierID = 0;
                    storeId = 0;
                    quantityInStock = 0;
                    break;
                case 6:
                    System.out.println("Back to Inventory menu");
                    break;
                default:
                    System.out.println("The selection is invalid");
                    break;
            }
            System.out.println();

        } while (selection != 6);
    }

    /**
     * Prompts user for information about a return of a product and updates database accordingly
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void returnProduct() throws ParseException, SQLException, ClassNotFoundException {

        do {
            System.out.println("1. Product ID: " + productId);
            System.out.println("2. Supplier ID: " + supplierID);
            System.out.println("3. Store ID: " + storeId);
            System.out.println("4. Total Quantity In Stock: " + quantityInStock);

            System.out.println("5. Submit New Inventory");
            System.out.println("6. Go back to previous screen");

            System.out.println("Enter # to add info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
                case 1:
                    System.out.println("Enter Product ID:");
                    productId = scan.nextInt();
                    break;
                case 2:
                    System.out.println("Enter Supplier ID:");
                    supplierID = scan.nextInt();
                    break;
                case 3:
                    System.out.println("Enter Store ID:");
                    storeId = scan.nextInt();
                    break;
                case 4:
                    System.out.println("Enter Total Quantity In Stock:");
                    quantityInStock = scan.nextInt();
                    break;
                case 5:
                    // SQL parameters for statement
                    // return entry of new supplier
                    Merchandise.updateInventoryReturn(productId, supplierID, storeId, quantityInStock);
                    ResultSet result = Merchandise.viewStockById(productId, supplierID, storeId);
                    printInventoryInfo(result);
                    System.out.println();
                    result.close();
                    productId = 0;
                    supplierID = 0;
                    storeId = 0;
                    quantityInStock = 0;
                    break;
                case 6:
                    System.out.println("Back to Inventory menu");
                    break;
                default:
                    System.out.println("The selection is invalid");
                    break;
            }
            System.out.println();

        } while (selection != 6);
    }

    /**
     * Prompts user for information about a transfer of inventory between stores
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void transferInventory() throws ParseException {
        productId = 0;
        supplierID = 0;
        int originStore = 0;
        int destinationStore = 0;
        int inventoryToTransfer = 0;
        do {

            System.out.println("Enter Product ID, Supplier ID and Origin Store ID");
            System.out.println("Then enter Destination Store ID");
            System.out.println("Enter 0 to go back");

            System.out.println("Product ID: ");

            productId = scan.nextInt();
            scan.nextLine();

            System.out.println("Supplier ID: ");
            supplierID = scan.nextInt();
            scan.nextLine();

            System.out.println("Origin Store ID: ");
            originStore = scan.nextInt();
            scan.nextLine();

            System.out.println("Destination Store ID: ");
            destinationStore = scan.nextInt();
            scan.nextLine();

            System.out.println("Enter amount of inventory to transfer:");
            inventoryToTransfer = scan.nextInt();
            scan.nextLine();

            // SQL drop statement
            if (productId > 0 & supplierID > 0 & originStore > 0 & destinationStore > 0 & inventoryToTransfer > 0)
                Merchandise.transferProduct(productId, supplierID, originStore, destinationStore, inventoryToTransfer);
            else if (selection < 0) {
                System.out.println("The selection is invalid");
            } else if (selection == 0) {
                System.out.println("Back to inventory screen");
                break;
            }
            System.out.println();
        } while (productId != 0);
    }



    /**
     * Prints a list of all the options available for operations related to merchandise
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static void merchandiseOptions() throws ClassNotFoundException, SQLException, ParseException {

        do {

            System.out.println("<-- Merchandise and Inventory records -->");
            System.out.println();
            System.out.println("1. View Merchandise By ID");
            System.out.println("2. View All Merchandise");
            System.out.println("3. Add Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. View All Inventory");
            System.out.println("7. Assign Product to store");
            System.out.println("8. Transfer Product");
            System.out.println("9. Return Product to Store");
            System.out.println("10. Back to Home Menu");
            System.out.println("Make a selection: ");

            selection = scan.nextInt();

            switch (selection) {
                case 1:
                    viewById();
                    break;
                case 2:
                    viewAllMerchandise();
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
                	viewAllInventory();
                	break;
                case 7:
                	assignInventory();
                	break;
                case 8:
                	transferInventory();
                	break;
                case 9:
                	returnProduct();;
                	break;
                case 10:
                    System.out.println("Back to Home menu");
                    break;
                default:
                    System.out.println("The selection is invalid");
                    break;
            }

        } while (selection != 10);
    }
}