import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * This java file has all the menu options for the Customer table
 */
public class BillingTransactionMenu {
    static Scanner scan = new Scanner(System.in);
    static int billNum = 0;
    static int prodID = 0;
    static int suppID = 0;
    static int staffID = 0;
    static int custID = 0;
    static int storeID = 0;
    static String date = "";
    static int quantity = 0;
    static int year = 0;
    static int rewardNum = 0;
    static int transID = 0;
    static String time = "";
    static int selection = 0;


    /**
     * Generates a new Bill to be paid to a supplier.
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void genNewBill() throws ParseException, SQLException, ClassNotFoundException {

        do {
        	System.out.println("1. Bill Number: " + billNum);
            System.out.println("2. Product ID: " + prodID);
            System.out.println("3. Supplier ID: " + suppID);
            System.out.println("4. Staff ID: " + staffID);
            System.out.println("5. Date: " + date);
            System.out.println("6. Quantity: " + quantity);
            System.out.println("7. Submit new Bill information");
            System.out.println("8. Go back to previous screen");

            System.out.println("Enter # to add respective info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Bill Number:");
                billNum = scan.nextInt();
                break;
            case 2:
                System.out.println("Enter Product ID:");
                prodID = scan.nextInt();
                break;
            case 3:
                System.out.println("Enter Supplier ID:");
                suppID = scan.nextInt();
                break;
            case 4:
                System.out.println("Enter Staff ID:");
            	staffID = scan.nextInt();
                break;
            case 5:
                System.out.println("Enter Date (yyyy-[m]m-[d]d):");
                date = scan.nextLine();
                break;
            case 6:
                System.out.println("Enter Quantity:");
                quantity = scan.nextInt();
                break;
            case 7:
                // SQL parameters for statement
                // return entry of new customer
                BillingTransaction.createBill(billNum, prodID, suppID, staffID, date, quantity);
                billNum = 0;
                prodID = 0;
                suppID = 0;
                staffID = 0;
                date = "";
                quantity = 0;
                break;
            case 8:
                System.out.println("Back to Billing & Transaction menu");
                break;
            default:
                System.out.println("The selection is invalid!");
                break;
            }
            System.out.println();

        } while (selection != 8);
    }
    
    /**
     * Generates reward for a customer in a particular year.
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void genNewReward() throws ParseException, SQLException, ClassNotFoundException {

        do {
        	System.out.println("1. Customer ID: " + custID);
            System.out.println("2. Year: " + year);
            System.out.println("3. Staff ID: " + staffID);
            System.out.println("4. Reward Number: " + rewardNum);
            System.out.println("5. Submit the values");
            System.out.println("6. Back to Billing & Transaction menu");

            System.out.println("Enter # to add respective info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Customer ID:");
                custID = scan.nextInt();
                break;
            case 2:
                System.out.println("Enter Year:");
                year = scan.nextInt();
                break;
            case 3:
                System.out.println("Enter Staff ID:");
            	staffID = scan.nextInt();
                break;
            case 4:
                System.out.println("Enter Reward Number:");
                rewardNum = scan.nextInt();
                break;
            case 5:
                // SQL parameters for statement
                // return entry of new customer
                BillingTransaction.genReward(custID, year, staffID, rewardNum);;
                custID = 0;
                year = 0;
                staffID = 0;
                rewardNum = 0;
                break;
            case 6:
                System.out.println("Back to Billing & Transaction menu");
                break;
            default:
                System.out.println("The selection is invalid!");
                break;
            }
            System.out.println();

        } while (selection != 6);
    }

    /**
     * Generates a new Bill to be paid to a supplier.
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void genNewTransaction() throws ParseException, SQLException, ClassNotFoundException {

        do {
        	System.out.println("1. Transaction ID: " + transID);
            System.out.println("2. Date: " + date);
            System.out.println("3. Time: " + time);
            System.out.println("4. Customer ID: " + custID);
            System.out.println("5. Staff ID: " + staffID);
            System.out.println("6. Store ID: " + storeID);
            System.out.println("7. Submit new Bill information");
            System.out.println("8. Go back to previous screen");

            System.out.println("Enter # to add respective info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Transaction ID:");
                transID = scan.nextInt();
                break;
            case 2:
                System.out.println("Enter Date (yyyy-[m]m-[d]d):");
                date = scan.nextLine();
                break;
            case 3:
                System.out.println("Enter Time (hh:mm:ss):");
                time = scan.nextLine();
                break;
            case 4:
                System.out.println("Enter Customer ID:");
            	custID = scan.nextInt();
                break;
            case 5:
                System.out.println("Enter Staff ID:");
                staffID = scan.nextInt();
                break;
            case 6:
                System.out.println("Enter Store ID:");
                storeID = scan.nextInt();
                break;
            case 7:
                // SQL parameters for statement
                // return entry of new customer
                BillingTransaction.genNewTransaction(transID, date, time, custID, staffID, storeID);
                transID = 0;
                time = "";
                custID = 0;
                staffID = 0;
                date = "";
                storeID = 0;
                break;
            case 8:
                System.out.println("Back to Billing & Transaction menu");
                break;
            default:
                System.out.println("The selection is invalid!");
                break;
            }
            System.out.println();

        } while (selection != 8);
    }

    /**
     * Generates a new Bill to be paid to a supplier.
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void addProdTransaction() throws ParseException, SQLException, ClassNotFoundException {

        do {
        	System.out.println("1. Transaction ID: " + transID);
            System.out.println("2. Product ID: " + prodID);
            System.out.println("3. Supplier ID: " + suppID);
            System.out.println("4. Store ID: " + storeID);
            System.out.println("5. Quantity: " + quantity);
            System.out.println("6. Submit new Product information");
            System.out.println("7. Go back to previous screen");

            System.out.println("Enter # to add respective info:");
            System.out.println("Current Input will show next to attribute");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Transaction ID:");
                transID = scan.nextInt();
                break;
            case 2:
                System.out.println("Enter Product ID:");
                prodID = scan.nextInt();
                break;
            case 3:
                System.out.println("Enter Supplier ID:");
                suppID = scan.nextInt();
                break;
            case 4:
                System.out.println("Enter Store ID:");
                storeID = scan.nextInt();
                break;
            case 5:
                System.out.println("Enter Quantity:");
                quantity = scan.nextInt();
                break;
            case 6:
                // SQL parameters for statement
                // return entry of new customer
                BillingTransaction.addTransactionItems(transID, prodID, suppID, storeID, quantity);
                transID = 0;
                prodID = 0;
                suppID = 0;
                storeID = 0;
                quantity = 0;
                break;
            case 7:
                System.out.println("Back to Billing & Transaction menu");
                break;
            default:
                System.out.println("The selection is invalid!");
                break;
            }
            System.out.println();

        } while (selection != 7);
    }

    /**
     * Generates a new Bill to be paid to a supplier.
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void calcTotPrice() throws ParseException, SQLException, ClassNotFoundException {

        do {
            System.out.println("1. Apply Discounts and calculate Total Price for Transaction ID: " + transID);
            System.out.println("2. Perform calculation");
            System.out.println("3. Back to Billing & Transaction menu");

            selection = scan.nextInt();
            scan.nextLine();

            switch (selection) {
            case 1:
                System.out.println("Enter Transaction ID:");
                transID = scan.nextInt();
                break;
            case 2:
                // SQL parameters for statement
                // return entry of new customer
                BillingTransaction.calculateTransaction(transID);
                transID = 0;
                break;
            case 3:
                System.out.println("Back to Billing & Transaction menu");
                break;
            default:
                System.out.println("The selection is invalid!");
                break;
            }
            System.out.println();

        } while (selection != 3);
    }

    /**
     * All the operations performed by the Billing Staff and Cashier
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static void billingOptions() throws ClassNotFoundException, SQLException, ParseException {

        do {
        	System.out.println();
            System.out.println("<-- Billing & Transaction -->");
            System.out.println();
            System.out.println("1. Generate a new Bill");
            System.out.println("2. Generate Reward for a Customer");
            System.out.println("3. Generate a new Transaction");
            System.out.println("4. Add Products to a Transaction");
            System.out.println("5. Apply Discounts and Calculate Price for a Transaction");
            System.out.println("6. Back to Home Menu");
            System.out.println("Make a selection: ");

            selection = scan.nextInt();

            switch (selection) {
            case 1:
                genNewBill();
                break;
            case 2:
                genNewReward();
                break;
            case 3:
                genNewTransaction();
                break;
            case 4:
                addProdTransaction();
                break;
            case 5:
                calcTotPrice();
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
