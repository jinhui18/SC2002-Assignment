package UI;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the first interface presented to a user when starting the application.
 */
public class MainUI {

	/**
	 * Displays the options a user can choose. This includes to go to guest details management, reservation management,
	 * room management, room service, menu management, payment management, checking-in a guest, checking-out a guest,getting room statistics report.
	 * @return the user's input of which option to go to.
	 * @throws IOException if the Integer selection the staff has made is of an invalid type.
	 */
	public static int displayMainUI() throws IOException {
		int choice=0;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Welcome to our hotel!");
			System.out.println("==================================================");
			System.out.println(" Please select one of the following functions: ");
			System.out.println("==================================================");
			System.out.println("(1) Guest Details Management");
			System.out.println("(2) Reservation Management");
			System.out.println("(3) Room Management");
			System.out.println("(4) Room Service");
			System.out.println("(5) Menu Management");
			System.out.println("(6) Payment Management");
			System.out.println("(7) Check-in Guest");
			System.out.println("(8) Check-out Guest");
			System.out.println("(9) Room Statistics Report");
			System.out.println("(10) Exit");
			System.out.println("\nEnter your choice:");
			try {
				choice = sc.nextInt();
				if(choice<1 || choice>10){
					System.out.println("Invalid input, please try again.");
					continue;
				}
				break;
			}catch(Exception e){
				System.out.println("Invalid input, please try again.");
				sc.nextLine();
			}
		}
		return choice;
		
	}

}
