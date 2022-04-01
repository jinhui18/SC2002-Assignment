import java.util.Scanner;

public class ReservationUI {
	
	public static void displayReservationUI()
	{
		RoomController rc = new RoomController();
		String stChoice=null;
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
			System.out.println("6:Display all available rooms");
			System.out.println("7:Display one type of available rooms");
			System.out.println("8:Quit");
			Scanner sc = new Scanner(System.in);
			stChoice = sc.next();
			if(!ReservationController.isInteger(stChoice)) continue;
			choice = Integer.parseInt(stChoice);
			switch(choice)
			{
			/*
			case 1:
				System.out.println("Do you have reservation?");
				System.out.println("1:Have reservation");
				System.out.println("2:No reservation");
				int sub = sc.nextInt();
				if(sub==1) CheckInController.checkIn();
				else if (sub==2) CheckInController.walkIn();
				else System.out.println("Invalid key");
				break;
			*/
			case 1:
				Reservation r = ReservationController.createReservation();
				if(r!=null) ReservationController.getReservationList().add(r);
				break;
			case 2:
				ReservationController.updateReservation();
				break;
			case 3:
				ReservationController.removeReservationByCode();
				break;
			case 4:
				ReservationController.printReservation();
				break;
			case 5:
				ReservationController.printAllReservation();
				break;
			/*
			case 7:
				ReservationController.checkOut();
				break;
			*/
			case 6:
				System.out.println(rc.printAllVacant());
				break;
			case 7:
				System.out.println("Which room type would you want to display?");
				System.out.println("SINGLE/DOUBLE/DELUXE/VIP_SUITE");
				String stType = sc.next().toUpperCase();
				RoomType type = RoomType.valueOf(stType);
				System.out.println(rc.printVacantRoomsList(type));
				break;
			default:
				break;
			}
		}while(choice<8);
		System.out.println("Going back to mainPage.....");
	}

}
