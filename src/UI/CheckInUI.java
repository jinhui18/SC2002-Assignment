package UI;

import java.io.IOException;
import java.util.Scanner;

import Class.Reservation;
import Controller.CheckInController;
import Controller.ReservationController;

/**
 * Check in UI will print out the options that the staff can select when it comes anything that involves the checking in of the guest.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class CheckInUI {
	/**
	Such options in displayPage includes:
	Checking in by reservation ID of the guest if guest already created a reservation.
	Checking in by the guest's IC/Passport number, this option only works if the guest already created a reservation.
	Walk in, if the guest does not have a reservation or the reservation expires already. Guest can then opt to walk in instead.
	 */
	public static void displayPage()
	{
		int choice;
		Scanner sc = new Scanner(System.in);
		Reservation r;
		do
		{
			System.out.println("=======================================");
			System.out.println("    Welcome to Check-In Page");
			System.out.println("=======================================");
			System.out.println("Select the option you want to execute");
			System.out.println("1:Check-In by reservation ID");
			System.out.println("2:Check-In by guest Passport/ IC number");
			System.out.println("3:Walk-In (No reservation)");
			System.out.println("4:Quit");
			choice = ReservationController.scanChoice(4);
			switch(choice)
			{
			case 1:
				r= ReservationController.searchReservationByCode(ReservationController.scanReservationCode());
				if(r==null)
				{
					System.out.println("Not found!");
					break;
				}
				CheckInController.checkIn(r);
				break;
			case 2:
				r = ReservationController.searchReservationByIC();
				if(r==null)
				{
					System.out.println("Not found!");
					break;
				}
				CheckInController.checkIn(r);
				break;
			case 3:
				CheckInController.walkIn();
				break;
			default:
				break;
				
			}
		}while(choice<4);
	}

}
