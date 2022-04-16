package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Class.CreditCardDetails;
import Class.Guest;
import Class.ResStatus;
import Class.Reservation;
import Class.Room;
import Class.Room.RoomStatus;
import Controller.GuestController;
import UI.CheckInUI;

/**
 * Check in Controller houses the various methods that the staff can use with regards to the checking in of the guest.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class CheckInController {

	/**
	* This method of allows the guest to check in if they did not make a reservation or if their reservation expires already and they have to walk in.
	* By walking in, this method will create a reservation on the spot and after creation of reservation, they will be checked in immediately.
	*/
	public static void walkIn() 
	{
		Reservation r = ReservationController.createReservation();
		ReservationController.addReservation(r);
		checkIn(r);
	}

	/**
	* This checks if the guest with the reservation is able to check in a not.
	* If the guest's reservation expires already, they will be prompted to walk in instead.
	* If the guest's reservation status is on waitlist, they will not be allowed to check in.
	* If the guest's already check in, they will not be allowed to check in again.
	* If the guest does not check in on the stipulated check in date, the guest cannot check in and will be prompted to walk in instead.
	* @param r This is the reservation of the guest.
	* @return It returns true if reservation is on confirmed and guest is able to check in, It returns false if reservation expires, reservation status is on waitlist, guest already checks in or if guest did not check in on the date that they are supposed to.
	*/
	public static boolean checkInValidator(Reservation r)
	{
		if (r.isExpired()) {
			System.out.println("Your Reservation has expired, please walk-in.");
			return false;
		}
		else if(r.isWaitlist())
		{
			System.out.println("Your Reservation is in WAITLIST, please walk-in.");
			return false;
		}
		else if(r.isCheckedIn())
		{
			System.out.println("You have already CHECKED-IN");
			return false;
		}
		else if (r.getDateIn().isAfter(LocalDate.now()))
		{
			System.out.println("Today is not your check-in date, would you like to go for walk-in?");
			return false;
		}
		return true;		
	}
	
	/**
	* This is the check in method, it checks in the guest if they have a reservation and the reservation status have to be confirmed.
	* If guest's reservation status is confirmed and they check in on the correct date, they are allowed to check in, else they cannot check in and can wish to opt for walk in instead.
	* When checking in, this method will automatically update the reservation status to check in.
	* When checking in, this method will automatically update room number of the guest as previously the room number of the guest is set to a default string value of "NA".
	* When checking in, this method will automatically update room status of the room the guest is staying in to occupied.
	* When checking in, this method asking for the number of accompanying guest that will be checking in with the guest who created the reservation.
	* The details of all the accompanying guest will be recorded in the guest database.
	* @param r This is the reservation of the guest.
	*/	
	public static void checkIn(Reservation r)
	{
		Guest g;
		//create guest
		ReservationController.printReservation(r);
		if(!checkInValidator(r)) return;

		
		//update reservation status
		r.setStatus(ResStatus.CHECKED_IN);
		g = r.getGuestDetails();		
		g.setRoomNumber(r.getRoom().getRoomNumber());
		//UPDATE ROOM STATUS
		ArrayList<Room> roomList = RoomController.getRoomList();
		int room_index = RoomController.searchRoom(r.getRoom().getRoomNumber());
		
		Room room = (Room) roomList.get(room_index);
		room.setRoomStatus(RoomStatus.OCCUPIED);
		
		//create guest
		System.out.println("\nPlease enter the number of guest that are also checking into the hotel (exclude the guest who made the reservation)");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		for (int i=1; i<=choice;i++) {
			System.out.printf("Please enter the details of the %d guest\n",i);
			g =GuestController.createGuestProfile();
			g.setRoomNumber(r.getRoom().getRoomNumber());
		}
		
		System.out.println("\nCheck in completed");
	}

	/**
	* This method calls the check in user interface which will display all the options that the staff can choose with regards to checking in of guest.
	*/	
	public static void displayPage()  {
		CheckInUI.displayPage();
	}


}
