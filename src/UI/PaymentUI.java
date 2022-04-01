import java.util.Scanner;

public class PaymentUI {
	public static void displayPage()
	{
		int choice;
		double weekday,weekend;
		double taxRate,discount;
		String s;
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println("Welcome to Payment Page");
			System.out.println("Please select the function you would like to execute");
			System.out.println("1:Display all room prices");
			System.out.println("2:Adjust room prices");
			System.out.println("3:Adjust discount factor");
			System.out.println("4:Adjust Tax Rate");
			System.out.println("5:Quit");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				PaymentController.displayAllPrice();
				break;
			case 2:
				System.out.println("Please enter the room type");
				s = sc.next();
				System.out.println("Please enter the weekday price");
				weekday = sc.nextDouble();
				System.out.println("Please enter the weekend price");
				weekend = sc.nextDouble();
				PaymentController.adjustRoomPrice(weekday,weekend,s);
				break;
			case 3:
				System.out.println("Please enter the new discount factor");
				discount = sc.nextDouble();
				PaymentController.adjustDiscountPrice(discount);
				break;
			case 4:
				System.out.println("Please enter the new tax rate");
				taxRate = sc.nextDouble();
				PaymentController.adjustTaxRate(taxRate);
				break;
			default: break;
			}
		}while(choice<5);
	}

}
