import java.util.ArrayList;

public class CheckOutController {
	
	private static PaymentController pc = new PaymentController();
	private static RoomController rc = new RoomController();
	private static ArrayList <Reservation> reservationList = ReservationController.getReservationList();
	
	public static void checkOut()
	{
		int i = ReservationController.searchReservationByName();
		Reservation r = (Reservation) reservationList.get(i);
		
		//UPDATE ROOM STATUS //Reset may be better
		//possibly delete reservation
		double roomBill = r.getRoomBill();
		
		ArrayList roomList = rc.getRoomList();
		String roomID = r.getRoom().getRoomNumber();
		int room_index = rc.searchRoom(roomID);
		Room room = (Room) roomList.get(room_index);
		room.setStatus(RoomStatus.VACANT);
		
		Guest g = r.getGuestDetails();
		GuestController.RemoveGuestProfile(g.getName(), g.getIdentity());
		
		//Not sure how order controller works but the main idea is to
		//Search order by roomID, get the orderbill;
		//At last remove order from list.
		
		/*
		int order_index = oc.searchOrder(room_index);
		ArrayList orderList = rc.getOrderList();
		Order order = (Order) orderList.get(order_index);
		double orderBill = order.getOrderBill();
		orderList.remove(order_index);
		
		double total = (roomBill * (1-Payment.getDiscountFactor()) + orderBill) * Payment.getTaxRate();
		*/
		r.displayRes();
		System.out.printf("RoomBill:%.2f\n",roomBill);
		//remove reservation
		reservationList.remove(i);
		
		//System.out.printf("Total: %.2f\n",total);
	}

}
