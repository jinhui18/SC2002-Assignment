package UI;
import java.io.IOException;
import Controller.*;

/**
 * Main App class houses the methods to start our application. 
 * It initializes all other classes, saves the data and calls UI.
 */
public class MainApp {
	
	/**
	 * Main class that initializes all other classes and calls UI
	 * @param args Nil
	 * @throws IOException If stream to file cannot be read , write or close.
	 */
    public static void main(String[] args) throws IOException{
		int choice=0;
        initAll();
		do {
			choice =MainUI.displayMainUI();
			goTo(choice);
		}while(choice!=10);
        saveAll();
    }

	/**
	 * Reads all the objects from the respective databases into GuestController,MenuController,RoomController, PaymentController, OrderController, ReservationController.
	 * @throws IOException if any of the database location is not found.
	 */
    public static void initAll() throws IOException{
        GuestController.init();
        RoomController.initRoom();
        ReservationController.init();
        
        MenuController.initMenu();
        OrderController.initOrders();
		PaymentController.init();
    }

	/**
	 * Writes all the objects from GuestController,MenuController,RoomController, PaymentController, OrderController, ReservationController into their respective databases.
	 * @throws IOException if any of the database location is not found.
	 */
	public static void saveAll() throws IOException{
	        GuestController.saveFile();
	        ReservationController.save();
	        RoomController.saveRoom();
	        OrderController.saveOrders();
			//MenuController saves after each update so no need to save at the end
			PaymentController.save();
	    }

	/**
	 * Goes to the respective Controller Classes of the option that is chosen.
	 * @param choice The option of which management page to go to
	 * @throws IOException if the Integer selection the staff has made is of an invalid type
	 */
	public static void goTo(int choice) throws IOException {
		switch(choice) {
			case 1:
				GuestController.displayGuestUI();
				break;
			case 2:
				ReservationController.displayReservationUI();
				break;
			case 3:
				RoomController.displayPage();
				break;
			case 4:
				OrderController.displayOrderUI();
				break;
			case 5:
				MenuController.dispMainPage();
				break;
			case 6:
				PaymentController.displayPage();
				break;
			case 7:
				CheckInController.displayPage();
				break;
			case 8:
				CheckOutController.displayPage();
				break;
			case 9:
				RoomController.printRoomStatusReport( );
				break;
			case 10:
				System.out.println("Exiting and saving...");

		}
	}   
}


