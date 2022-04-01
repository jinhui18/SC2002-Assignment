import java.util.ArrayList;

public class CheckInController {
	private static ReservationController reser_c = new ReservationController();
	private static GuestController gc = new GuestController();
	private static RoomController rc = new RoomController();
	private static ArrayList <Reservation> reservationList = ReservationController.getReservationList();
	public static void walkIn()
	{
		Reservation r = ReservationController.createReservation();
		reservationList.add(r);
		
		checkIn(r);
	}
	
	public static void checkIn()
	{
		ArrayList<Guest> guestList = gc.getGuestList();
		//changable
		int index = ReservationController.searchReservationByName();
		
		//create guest
		if(index!=-1) {
			Reservation r = (Reservation) reservationList.get(index);
			r.displayPartRes();
			if(r.isComfirmed()) {
				Guest g = r.getGuestDetails();
				CreditCardDetails c = g.getCreditCardDetails();
				CreditCardDetails ccd = new CreditCardDetails(c.getCardNumber(),c.getExpiryDate(),c.getCvvNumber());
				Guest newGuest = new Guest(g.getName(),g.getIdentity(),g.getAddress(),g.getCountry(),g.getGender(),g.getNationality(),g.getContact(),ccd);
				guestList.add(newGuest);
				
				//update reservation status
				r.setStatus(ResStatus.CHECKED_IN);
				
				//UPDATE ROOM STATUS
				ArrayList<Room> roomList = rc.getRoomList();
				int room_index = rc.searchRoom(r.getRoom().getRoomNumber());
				Room room = (Room) roomList.get(room_index);
				room.setStatus(RoomStatus.OCCUPIED);
			}
			else
			{
				if(r.isExpired()){
					System.out.printf("The reservation has expired\n");
				}
				else if(r.isWaitlist())
				{
					System.out.printf("The reservation is in the waitlist\n");				
				}
				else if(r.isCheckedIn())
				{
					System.out.printf("The reservation has checked-in\n");
				}
			}
		}
		else
		{
			System.out.println("");
		}
		
	}
	
	public static void checkIn(Reservation r)
	{
		ArrayList<Guest> guestList = gc.getGuestList();
		
		//create guest
		r.displayPartRes();
		Guest g = r.getGuestDetails();
		CreditCardDetails c = g.getCreditCardDetails();
		CreditCardDetails ccd = new CreditCardDetails(c.getCardNumber(),c.getExpiryDate(),c.getCvvNumber());
		Guest newGuest = new Guest(g.getName(),g.getIdentity(),g.getAddress(),g.getCountry(),g.getGender(),g.getNationality(),g.getContact(),ccd);
		guestList.add(newGuest);
		
		//update reservation status
		r.setStatus(ResStatus.CHECKED_IN);
		
		//UPDATE ROOM STATUS
		ArrayList<Room> roomList = rc.getRoomList();
		int room_index = rc.searchRoom(r.getRoom().getRoomNumber());
		Room room = (Room) roomList.get(room_index);
		room.setStatus(RoomStatus.OCCUPIED);
		
	}


}
