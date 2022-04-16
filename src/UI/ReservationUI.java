package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import Class.Reservation;
import Class.Room.RoomType;
import Controller.RoomController;
import Controller.GuestController;
import Controller.ReservationController;
/**
 * Represents the user interfaces for the user to communicate with the reservation related operations
 * @author Chia Jer Wen
 */
public class ReservationUI {
	/**
	 * Print out the options related to reservation for the user to select.
	 * Such options includes:<br>
	 * Creation of reservation involves two methods from {@code ReservationController}: 
	 * {@code createReservation} to capture input from user, then construct and return the reservation
	 * and {@code addReservation} adds the reservation created into the {@code reservationList}.
	 * <p>
	 * Update of reservation calls the method {@code updateReservation} from {@code ReservationController}
	 * for searching the target reservation and updating attributes chosen by the user to that reservation.
	 * <p>
	 * Removal of reservation involves two methods from {@code ReservationController}:
	 * {@code searhReservationByCode} to get the target reservation, 
	 * and {@code removeReservation} to remove that reservation.
	 * <p>
	 * Printing of reservation involves two methods including:
	 * {@code searchReservationByCode} to get the target reservation,
	 * and {@code printReservation} to print the reservation receipt of that reservation.
	 * <p>
	 * Display of all reservation, this method calls {@code printAllReservationExisting} or {@code printAllReservation}
	 * according to choice of selection of the user.
	 * <p>
	 * Display of one type of available rooms ask input for room type from user
	 * then display the vacant rooms of that room type by calling method {@code displayVacantRoomsByType} from {@code RoomController}
	 * <p>
	 * Display all available rooms through calling {@code displayVacantRoomsByType} for every room type.
	 * @see ReservationController#createReservation()
	 * @see ReservationController#updateReservation()
	 * @see ReservationController#searchReservationByCode(String)
	 * @see ReservationController#removeReservation(Reservation)
	 * @see ReservationController#printReservation(Reservation)
	 * @see ReservationController#printAllReservationExisting()
	 * @see ReservationController#printAllReservation()
	 * @see RoomController#displayVacantRoomsByType(RoomType)
	 */
	public static void displayReservationUI()
	{
		RoomController rc = new RoomController();
		int choice =0 ;
		do
		{
			System.out.println("=======================================");
			System.out.println("    Welcome to Reservation Page");
			System.out.println("=======================================");
			System.out.println("Select the option you want to execute");
			System.out.println("1:Create reservation");
			System.out.println("2:Update reservation details");
			System.out.println("3:Remove reservation");
			System.out.println("4:Print reservation");
			System.out.println("5:Display all reservation");
			System.out.println("6:Display one type of available rooms");
			System.out.println("7:Display all available rooms");
			System.out.println("8:Quit");
			Scanner sc = new Scanner(System.in);
			choice = ReservationController.scanChoice(8);
			switch(choice)
			{

			case 1:
				Reservation r = ReservationController.createReservation();
				if(r!=null) ReservationController.addReservation(r);
				break;
			case 2:
				ReservationController.updateReservation();
				break;
			case 3:
				Reservation r3 = ReservationController.searchReservationByCode(ReservationController.scanReservationCode());
				if(r3 == null) break;
				GuestController.removeGuestProfile(r3.getGuestDetails().getName(),r3.getGuestDetails().getIdentity());
				ReservationController.removeReservation(r3);
				break;
			case 4:
				Reservation r4 = ReservationController.searchReservationByCode(ReservationController.scanReservationCode());
				ReservationController.printReservation(r4);
				break;
			case 5:
			 	System.out.println("Please select the following:");
			 	System.out.println("(1) Print all existing reservation details:");
			 	System.out.println("(2) Print all reservation details:");
	            try {
	                choice = sc.nextInt();
	            } catch (InputMismatchException a)
	            {
	                System.out.println("You have entered an invalid input. Please try again.");
	                sc.nextLine();
	                continue;
	            }
	            switch (choice) {
	            case 1: ReservationController.printAllReservationExisting();
	            		break;
	            case 2: ReservationController.printAllReservation();
	            		break;
	            }
				break;
			case 6:
				System.out.println("Which room type would you want to display?");
				String roomTypeInput = null;		
				System.out.println("Please enter the room type");
		        System.out.println("Press 1 for SINGLE");
		        System.out.println("Press 2 for DOUBLE");
		        System.out.println("Press 3 for DELUXE");
		        System.out.println("Press 4 for VIP SUITE");
				
				int choice2 = 0;
		        do 
		        {
		        try {
		            choice2 = sc.nextInt();
		        } 
		        catch (InputMismatchException a)
		        {
		            System.out.println("You have entered an invalid input. Please try again.");
		            sc.nextLine();
		            continue;
		        } 
		        if (choice2 >=1 && choice2<=4 ) {
		        	switch (choice2) 
		        	{
		        	case 1: roomTypeInput = "SINGLE" ;
		        	break;
		        	case 2: roomTypeInput = "DOUBLE" ;
		        	break;
		        	case 3:roomTypeInput = "DELUXE" ;
		        	break;
		        	case 4:roomTypeInput = "VIP_SUITE" ;
		        	break;
		        	}
		        }
		        else System.out.println("You have entered an invalid choice. Please try again.");
		        } while(choice2 <=0 || choice2>4);

				RoomType type = RoomType.valueOf(roomTypeInput);
				rc.displayVacantRoomsByType(type);
				break;
			case 7:
				roomTypeInput = null;		
				for (int k=1; k<=4; k++) {
					switch (k) 
		        	{
		        	case 1: roomTypeInput = "SINGLE" ;
		        	break;
		        	case 2: roomTypeInput = "DOUBLE" ;
		        	break;
		        	case 3:roomTypeInput = "DELUXE" ;
		        	break;
		        	case 4:roomTypeInput = "VIP_SUITE" ;
		        	break;
		        	}
					type = RoomType.valueOf(roomTypeInput);
					rc.displayVacantRoomsByType(type);
					System.out.println();
				}
				break;
			default:
				break;
			}
		}while(choice!=8);
		System.out.println("Going back to mainPage.....");
	}

}
