import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


/**
 * This java file has all the menu options for the Merchandise table
 */
public class ReportsMenu {
	

    static Scanner scan = new Scanner(System.in);
    /**
     * All the options you can do for the reports table
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ParseException
     */
    public static void reportsOptions() throws ClassNotFoundException, SQLException, ParseException {

        int selection = 0;
        
        do {

            System.out.println("<-- Reports -->");
            System.out.println();
            System.out.println("1. View Total Sales By Year Report");
            System.out.println("2. View Total Sales By Month Report");
            System.out.println("3. View Total Sales By Day Report");
            System.out.println("4. View Sales Growth Over Two Months Consecutive Report");
            System.out.println("5. View Merchandise Stock Report for all Products");
            System.out.println("6. View Merchandise Stock Report for all Stores");
            System.out.println("7. View Number of Orders for Every Product In a Year Report");
            System.out.println("8. View Customer Sales Growth By Month Report");
            System.out.println("9. View Customer Growth Report by Year");
            System.out.println("10. View Customer Activity Report (Over Specific Time Period)");
            System.out.println("11. Back to Home Menu");

            System.out.println("Make a selection: ");

            selection = scan.nextInt();

            switch (selection) {
                case 1:
                    Reports.totalSalesByYear();
                    break;
                case 2:
                    Reports.totalSalesByMonth();
                    break;
                case 3:
                    Reports.totalSalesByDay();
                    break;
                case 4:
                    Reports.generateSalesGrowth();
                    break;
                case 5:
                  Reports.productStockReport();
                    break;
                case 6:
                    Reports.storeMerchandiseReport();
                    break;
                case 7:
                    Reports.salesProduct();
                    break;
                case 8:
                    Reports.customerMonthlyGrowth();
                    break;
                case 9:
                    Reports.customerYearlyGrowth();
                    break;
                case 10:
                    Reports.customerSpendingReport();
                    break;
                case 11:
                    System.out.println("Back to Home menu");
                    break;
                default:
                    System.out.println("The selection is invalid");
                    break;
            }

        } while (selection != 11);
    }
}