import java.sql.*;
import java.text.ParseException;

/**
 * This java file holds all the SQL code for the Merchandise table
 */
class Merchandise {

    static Connection connection = SetDatabase.conn;
    static Statement statement = SetDatabase.stmt;
    static ResultSet result = SetDatabase.rs;

    /**
     * Looks for Merchandise in DB by inputed Product ID
     *
     * @param product_id
     * @param supplier_id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewMerchandiseById(int product_id, int supplier_id) throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Merchandise WHERE product_id = ? AND supplier_id = ?;");
            ps.setInt(1, product_id);
            ps.setInt(2, supplier_id);
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
     * Gets all Merchandise in the DB
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewAllMerchandise() throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Merchandise;");
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
     * Handles return of a product
     *
     * @param productId
     * @param supplierID
     * @param storeID
     * @param quantityToAdd
     * @throws ParseException
     */
    static void updateInventoryReturn(int productId, int supplierID, int storeID, int quantityToAdd) throws ParseException {
        int id = 0;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Merchandise SET total_quantity = total_quantity + ? WHERE product_id= ? AND supplier_id = ?;");
            ps.setInt(1, quantityToAdd);
            ps.setInt(2, productId);
            ps.setInt(3, supplierID);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();
            if (id > 0) {
                System.out.println("Inventory Updated");

            } else {
                System.out.println("Inventory Not Updated");

            }

            PreparedStatement ps1 = connection.prepareStatement(
                    "UPDATE Stocks SET quantity_in_stock=quantity_in_stock+? WHERE product_id= ? AND supplier_id = ? AND store_id=?;");

            ps1.setInt(1, quantityToAdd);
            ps1.setInt(2, productId);
            ps1.setInt(3, supplierID);
            ps1.setInt(4, storeID);

            id = ps1.executeUpdate();
            System.out.println(id);
            ps1.close();
            if (id > 0) {
                System.out.println("Inventory Updated");

            } else {
                System.out.println("Inventory Not Updated");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Adds a Product to DB with inputted parameters from the user
     *
     * @param productId
     * @param supplierID
     * @param productName
     * @param totalQuantity
     * @param buyPrice
     * @param marketPrice
     * @param productionDate
     * @param expirationDate
     * @param discount
     * @param discountStart
     * @param discountExpiry
     * @throws ParseException
     */
    static void addProduct(int productId, int supplierID, String productName, int totalQuantity, double buyPrice, double marketPrice, String productionDate, String expirationDate, double discount, String discountStart, String discountExpiry) throws ParseException {
        int id = 0;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Merchandise (product_id, supplier_id, product_name, total_quantity, buy_price, market_price, "
                    + "production_date, expiration_date, discount, discount_start, discount_expiry) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?);");

            ps.setInt(1, productId);
            ps.setInt(2, supplierID);
            ps.setString(3, productName);
            ps.setInt(4, totalQuantity);
            ps.setDouble(5,buyPrice);
            ps.setDouble(6, marketPrice);
            ps.setString(7, productionDate);
            ps.setString(8, expirationDate);
            ps.setDouble(9, discount);
            ps.setString(10, discountStart);
            ps.setString(11, discountExpiry);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();

            if (id > 0) {
                System.out.println("Product added");

            } else {
                System.out.println("Product not added to database");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Updates merchandise with inputed parameters from the user
     *
     *
     * @param supplierID
     * @param productName
     * @param totalQuantity
     * @param buyPrice
     * @param marketPrice
     * @param productionDate
     * @param expirationDate
     * @param discount
     * @param discountStart
     * @param discountExpiry
     * @throws ParseException
     */
    static void updateProduct(int productId, int supplierID, String productName, int totalQuantity, double buyPrice,
                                  double marketPrice, String productionDate, String expirationDate, double discount,
                                  String discountStart, String discountExpiry) throws ParseException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Merchandise SET product_name = ?,total_quantity = ?, buy_price = ?, market_price = ?, production_date = ?, "
                    + "expiration_date = ?, discount = ?, discount_start = ?, discount_expiry = ? WHERE product_id= ? AND supplier_id = ?;");

            ps.setString(1, productName);
            ps.setInt(2, totalQuantity);
            ps.setDouble(3,buyPrice);
            ps.setDouble(4, marketPrice);
            ps.setString(5, productionDate);
            ps.setString(6, expirationDate);
            ps.setDouble(7, discount);
            ps.setString(8, discountStart);
            ps.setString(9, discountExpiry);
            ps.setInt(10, productId);
            ps.setInt(11, supplierID);

            int id = ps.executeUpdate();
            ps.close();

            if (id > 0) {
                System.out.println("Product updated");

            } else {
                System.out.println("Product not updated");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Deletes a product by inputted ID
     *
     * @param productId
     */
    static void deleteProduct(int productId, int supplierId) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Merchandise WHERE product_id = ? AND supplier_id = ?;");
            ps.setInt(1, productId);
            ps.setInt(2, supplierId);
            int id = ps.executeUpdate();

            System.out.println(id);

            if (id > 0) {
                System.out.println("Product deleted");
            } else {
                System.out.println("No Product with that id");
            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }
    
    /**
     * Looks for Inventory in DB by inputted inventory ID
     *
     * @param product_id
     * @param supplier_id
     * @param store_id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewStockById(int product_id, int supplier_id, int store_id) throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Stocks WHERE product_id = ? AND supplier_id = ? AND store_id = ?;");
            ps.setInt(1, product_id);
            ps.setInt(2, supplier_id);
            ps.setInt(3, store_id);
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
     * Gets all Inventory in the DB
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static ResultSet viewAllInventory() throws ClassNotFoundException, SQLException, ParseException {

        ResultSet returnSet = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Stocks;");
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
     * Adds Inventory to DB with inputted parameters from the user
     *
     * @param productId
     * @param supplierID
     * @param storeID
     * @param quantityInStock
     * @throws ParseException
     */
    static void assignProductToStore(int productId, int supplierID, int storeID, int quantityInStock) throws ParseException {
        int id = 0;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Stocks (product_id, supplier_id, store_id, quantity_in_stock) "
                            + "VALUES (?,?,?,?);");

            ps.setInt(1, productId);
            ps.setInt(2, supplierID);
            ps.setInt(3, storeID);
            ps.setInt(4, quantityInStock);
            id = ps.executeUpdate();
            System.out.println(id);
            ps.close();

            if (id > 0) {
                System.out.println("Inventory added");

            } else {
                System.out.println("Inventory not added to database");

            }
        } catch (SQLException oops) {
            System.out.println("Connection Failed! Check output console");
            oops.printStackTrace();
        }
    }

    /**
     * Transfers Inventory Between Stores
     *
     * @param productId
     * @param supplierID
     * @param originStore
     * @param destinationStore
     *  @param inventoryToTransfer
     * @throws ParseException
     */
    static void transferProduct(int productId, int supplierID, int originStore, int destinationStore, int inventoryToTransfer) throws ParseException {
        int id = 0;
        int id1 =0;

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Stocks SET quantity_in_stock = quantity_in_stock + ? WHERE product_id =  ? AND supplier_id = ? AND store_id = ?;");
            PreparedStatement ps1 = connection.prepareStatement(
            		"UPDATE Stocks SET quantity_in_stock = quantity_in_stock - ? WHERE product_id =  ? AND supplier_id = ? AND store_id = ?;");

            ps.setInt(1, inventoryToTransfer);
            ps.setInt(2, productId);
            ps.setInt(3, supplierID);
            ps.setInt(4, destinationStore);
            ps1.setInt(1, inventoryToTransfer);
            ps1.setInt(2, productId);
            ps1.setInt(3, supplierID);
            ps1.setInt(4, originStore);
            id = ps.executeUpdate();
            id1 = ps1.executeUpdate();
            ps1.close();
            if(id<=0) {
            	ps = connection.prepareStatement(
                		"INSERT into Stocks VALUES (?,?,?,?);");
            	ps.setInt(1, productId);
                ps.setInt(2, supplierID);
                ps.setInt(3, destinationStore);
                ps.setInt(4, inventoryToTransfer);

                id = ps.executeUpdate();
                ps.close();
                
            }

            if (id > 0 && id1>0) {
                System.out.println("Inventory transferred");

            } else {
                System.out.println("Inventory not transferred");

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