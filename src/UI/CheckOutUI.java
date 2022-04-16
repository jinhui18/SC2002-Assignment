package UI;

import java.util.Scanner;

import Class.Reservation;
import Controller.CheckOutController;
import Controller.ReservationController;

/**
 * Check out UI will print out the total bill that the guest has to pay when checking out and the payment option.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class CheckOutUI {
	/**
	The payment option includes paying by cash or by credit card.
	For payment by cash, the method will automatically inform the staff if the amount given is enough and if change is needed to give to the guest.
	For payment by credit card, the credit card details that the guest provided previously will be printed and staff will use that detail to pay. No change is given.
	After payment, all details like the reservation, order details of the room, guest details will be removed accordingly
	 */
	public static void displayPage()
	{
		double bill;
		Reservation r = ReservationController.searchReservationSelection();
		if(r==null) return;
		
		ReservationController.printReservation(r);
		bill = CheckOutController.getTotalBill(r);
		
		System.out.printf("Total Bill is :%.2f\n",bill);		
		
		System.out.println("Do you want to pay by cash/credit card?");
		Scanner sc = new Scanner(System.in);
		int flag = 0;
		int choice = 0;
		double input = 0;
		double add;
		System.out.println("1:By cash");
		System.out.println("2:By credit card");
		System.out.println("3:Quit");
		while (true) {
			try {
				choice = sc.nextInt();
			}
			catch(Exception e) {
				sc.next();
				flag =1;
			}
			if ((choice >=1 && choice <=3) && flag ==0) {
				break;
			}
			flag =0;
			System.out.println("Please enter a valid choice");
		}

		if(choice==1)
		{
			System.out.println("Please enter the amount given");
			while (true) {
				try {
					input = sc.nextDouble();
				}
				catch(Exception e) {
					sc.next();
					flag =1;
				}
				if ((choice >=1 && choice <=3) && flag ==0) {
					break;
				}
				flag =0;
				System.out.println("Please enter a valid amount");
			}
			   while((input-bill)<-0.005)
			   {
			    System.out.println("Amount paid is not enough");
			    System.out.printf("Please add %.2f\n",bill-input);
			    System.out.println("Please enter the amount added");
			    add = sc.nextDouble();
			    input+=add;
			   }
			   if((input-bill)>=-0.005 && (input-bill)<0.005)
			   {
			    System.out.println("Exact amount is given, no change needed");
			   }
			   else
			   {
			    System.out.println("Amount exceeded, change is needed");
			    System.out.printf("Change needed: %.2f\n",input-bill);
			   }
		}
		if (choice == 2) {
			CheckOutController.checkOutPrintCreditCardDetails(r);	
		}
		CheckOutController.checkOut(r);
		System.out.println("Thanks for choosing our hotel");
		System.out.println("Have a nice day");
	}


}
