import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;

/**
 * The list of all tables can be navigated here
 */
public class HomePage {

	static Connection connection = SetDatabase.conn;
	static Statement statement = SetDatabase.stmt;
    static ResultSet result = SetDatabase.rs;
    
    public static void HomeMenuOptions() throws ClassNotFoundException, SQLException, ParseException {

    	Scanner scan = new Scanner(System.in);
    	int selection = 0;
    	do {
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.println("3. Store");
        System.out.println("4. Supplier");
        System.out.println("5. Merchandise & Inventory");
        System.out.println("6. Billing & Transaction");
        System.out.println("7. Reports");
        System.out.println("8. Exit");
        
        int selection1 = scan.nextInt();
        
        selection =  selection1;
        
        switch (selection) {
        case 1:
        	CustomerMenu.customerOptions();
            break;
        case 2:
        	StaffMenu.staffOptions();
            break;
        case 3:
        	StoreMenu.storeOptions();
            break;
        case 4:
        	SupplierMenu.supplierOptions();
            break;
        case 5:
        	MerchandiseMenu.merchandiseOptions();
            break;
        case 6:
        	BillingTransactionMenu.billingOptions();
            break;
        case 7:
        	ReportsMenu.reportsOptions();
            break;
        case 8:
        	
            break;    
        default:
            System.out.println("The selection is invalid");
            break;
        }
    	}while(selection != 8);
        System.out.println("Bye");
    	scan.close();
        
    }
}