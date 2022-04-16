package UI;

import java.util.InputMismatchException;
import java.util.Scanner;
import Controller.PaymentController;

/**
 * Payment UI will print out all the option that the staff can select when it comes anything that involves the values of the room prices of the each type of room on weekday and weekend, the discount as well as tax rate.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class PaymentUI {
	/**
	Display all room prices allows the staff to display the room price of all room type on a weekday and on a weekend.
	Adjust room prices allows the staff to adjust the price of the room of a particular type either on weekday or on a weekend.
	Adjust discount factor allows the staff to adjust the percentage of discount.
	Adjust tax factor allows the staff to adjust the percentage of tax.
	Print discount factor allows the staff to retrieve the current percentage of discount.
	Print tax factor allows the staff to retrieve the current percentage of tax.
	*/
	public static void displayPage()
	{
		int choice;
		double weekday,weekend;
		double taxRate,discount;
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println("Welcome to Payment Page");
			System.out.println("Please select the function you would like to execute");
			System.out.println("1:Display all room prices");
			System.out.println("2:Adjust room prices");
			System.out.println("3:Adjust discount factor");
			System.out.println("4:Adjust tax Rate");
			System.out.println("5:Print discount factor");
			System.out.println("6:Print Tax Rate");
			System.out.println("7:Quit");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				PaymentController.displayAllPrice();
				break;
			case 2:
				System.out.println("Please enter the room type");
		        System.out.println("Press 1 for SINGLE");
		        System.out.println("Press 2 for DOUBLE");
		        System.out.println("Press 3 for DELUXE");
		        System.out.println("Press 4 for VIP SUITE");
		        int choice2 = 0;
		        String roomTypeInput = null;
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
				System.out.println("Please enter the weekday price");
				weekday = sc.nextDouble();
				System.out.println("Please enter the weekend price");
				weekend = sc.nextDouble();
				PaymentController.adjustRoomPrice(weekday,weekend,roomTypeInput);
				break;
			case 3:
				PaymentController.printDiscountPrice();
				System.out.println("Please enter the new discount factor (input 10 for 10% off)");
				discount = sc.nextInt();
				PaymentController.adjustDiscountPrice(discount/100);
				break;
			case 4:
				PaymentController.printTaxRate();
				System.out.println("Please enter the new tax rate");
				taxRate = sc.nextDouble();
				PaymentController.adjustTaxRate(taxRate/100);
				break;
			case 5:
				PaymentController.printDiscountPrice();
				break;
			case 6:
				PaymentController.printTaxRate();
				break;
			default: break;
			}
		}while(choice<7);
	}

}
