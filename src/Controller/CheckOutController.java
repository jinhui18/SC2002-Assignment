package Controller;


import Class.Guest;
import Class.Reservation;
import UI.CheckOutUI;


/**
 * Check out Controller houses the various methods that the staff can use with regards to the checking out of the guest.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class CheckOutController {
	/**
	* This method calls the check out user interface which will display all the options that the staff can choose with regards to checking out of guest.
	*/	
	public static void displayPage() {
		CheckOutUI.displayPage();
	}
	
	/**
	* This checks if the guest with the reservation is able to check out a not.
	* If the guest's reservation status is not checked in, this guest is not able to check out
	* @param r This is the reservation of the guest.
	* @return It returns false if reservation status not checked in and guest not able to check out, it returns true if reservation status is checked in and guest is able to check out.
	*/
	public static boolean checkOutValidator(Reservation r)
	{
		if(r.isCheckedIn())
		{
			return true;
		}
		else
		{
			System.out.println("Not checked-in");
			return false;
		}
	}
	
	/**
	* This is the check out method, it checks out the guest as well as the accompanying guest staying in the same room.
	* If guest's reservation status checked in, guest is allowed to check out if not they cannot use this method since they are not even checked in into our hotel.
	* When checking out, this method will automatically remove the guest details of all the guest staying in that room.
	* When checking out, this method will automatically remove the order details of all the food ordered by the guests in that room.
	* When checking out, this method will automatically remove the reservation of the guest who created the reservation.
	* @param r This is the reservation of the guest.
	*/
	public static void checkOut(Reservation r)
	{		
		if(!checkOutValidator(r)) return;
		
		Guest g = r.getGuestDetails();
		OrderController.removeOrderForCheckout(g.getRoomNumber());
		GuestController.removeGuestProfile(g.getRoomNumber());
		
		//remove reservation
		ReservationController.removeReservation(r);;
		
	}
	
	/**
	* This method gets the total bill of the room that the guest is staying.
	* Total bill includes the food bill as well as the room bill.
	* If guest decides to check out earlier than the original check out date, they are expected to pay the full duration of the stay.
	* Total bill accounts for the discount that the hotel gives if any, discount is applied to the total bill which is the sum of food bill and room bill.
	* Total bill also accounts for the tax, tax is applied after discount is applied.
	* @param r This is the reservation of the guest.
	* @return Final total bill that the guest has to pay
	*/
	public static double getTotalBill(Reservation r)
	{
		double roomBill = r.getBill();
		double foodBill =0;
		double totalBill =0;

		foodBill = OrderController.calTotalBill(r.getRoom().getRoomNumber());
		
		System.out.printf("Room Bill:%.2f\n",roomBill);
		System.out.printf("Food Bill:%.2f\n",foodBill);
		double discountRate = PaymentController.getDiscountRate();
		double taxRate = PaymentController.getTaxRate();
		totalBill = (roomBill + foodBill);
		
		if (discountRate != 0) {
			totalBill = totalBill* (1-discountRate);
		}
		totalBill = totalBill*(1+taxRate);
		
		
		return totalBill;
	}
	/**
	* This method prints the details of the credit card that the guest provided.
	* This method will be called when guest opts to pay using credit card, staff will then key in the details and pay using this credit card detail.
	* @param r This is the reservation of the guest.
	*/
	public static void checkOutPrintCreditCardDetails(Reservation r)
	{		
		if(!checkOutValidator(r)) return;
		
		Guest g = r.getGuestDetails();
		System.out.println("Credit Card Details of guest");
		System.out.println("Name on Credit Card: " + g.getCreditCardDetails().getName());
		System.out.println("Credit Card number: " + g.getCreditCardDetails().getCardNumber());
		System.out.println("Credit Card expiry date: " + g.getCreditCardDetails().getExpiryDate());
		System.out.println("Credit Card cvv number: " + g.getCreditCardDetails().getCvvNumber());
		System.out.println("Billing address: " + g.getCreditCardDetails().getBillingAddress());
		
	}
	
}