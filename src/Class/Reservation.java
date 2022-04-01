import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


public final class Reservation {
	
	//ATTRIBUTES
	private String reservationCode;
	private Guest guestDetails;
	private Room room;
	private LocalDate dateIn;
	private LocalDate dateOut;
	private int numAdults;
	private int numChildren;
	private ResStatus status;
	
	private long numDaysBetween;
	private double bill; //Haven't decided how to get price of each room
	
	//Constructor
	
	public Reservation(String RC, Guest G, Room R, LocalDate DI, LocalDate DO, int NA, int NC, ResStatus S)
	{
		//Constructor: consider every information is given upon Reservation
		//Including: check-in and check-out day.
		this.reservationCode=RC;
		this.guestDetails=G;
		this.room=R;
		this.dateIn=DI;
		this.dateOut=DO;
		this.numAdults=NA;
		this.numChildren=NC;
		this.status=S;
		//numDaysBetween
		this.numDaysBetween=calNumDays();
		this.bill=calRoomBill();
	}
	
	public Reservation(String RC, Guest G, Room R, LocalDate DI, LocalDate DO, int NA, int NC, ResStatus S,double bill)
	{
		//Constructor: consider every information is given upon Reservation
		//Including: check-in and check-out day.
		this.reservationCode=RC;
		this.guestDetails=G;
		this.room=R;
		this.dateIn=DI;
		this.dateOut=DO;
		this.numAdults=NA;
		this.numChildren=NC;
		this.status=S;
		//numDaysBetween
		this.numDaysBetween=calNumDays();
		this.bill=bill;
	}
	
	//Accessor
	public String getReservationCode() {return reservationCode;}
	public Guest getGuestDetails() {return guestDetails;}
	public Room getRoom() {return room;}
	public LocalDate getDateIn() {return dateIn;}
	public LocalDate getDateOut() {return dateOut;}
	public int getNumAdults() {return numAdults;}
	public int getNumChildren() {return numChildren;}
	public ResStatus getStatus() {return status;}
	public long getNumDaysBetween() {return numDaysBetween;}
	public double getRoomBill() {return bill;}
	
	//Mutator
	public void setReservationCode(String rc) {this.reservationCode=rc;}
	public void setGuestDetails(Guest g) {this.guestDetails=g;};
	public void setRoom (Room r) {this.room=r;};
	public void setDateIn(LocalDate DI) {this.dateIn=DI; this.numDaysBetween=calNumDays();}
	public void setDateOut(LocalDate DO) {this.dateOut=DO;this.numDaysBetween=calNumDays();} //DateIn & DateOut will change Bill
	public void setNumAdults(int num) {this.numAdults=num;}
	public void setNumChildren(int num) {this.numChildren=num;}
	public void setStatus (ResStatus status) {this.status=status;}
	
	//Boolean Methods: isExpired? isReserved? isWaitList? isCheckedIn?
	public boolean isExpired() {if (status==ResStatus.EXPIRED) return true; else return false;}
	public boolean isComfirmed() {if (status==ResStatus.COMFIRMED) return true; else return false;}
	public boolean isWaitlist() {if (status==ResStatus.WAITLIST) return true; else return false;}
	public boolean isCheckedIn() {if (status==ResStatus.CHECKED_IN) return true; else return false;}
	
	
	//Support (private methods)
	private long calNumDays()
	{
		long daysBetween=ChronoUnit.DAYS.between(getDateIn(),getDateOut());
		return daysBetween;
	}
	
	private double calRoomBill()
	{
		this.bill=0;
		RoomType rt = room.getType();
		double weekdayPrice = Payment.getRoomPriceByType(String.valueOf(rt), "Weekday");
		double weekendPrice = Payment.getRoomPriceByType(String.valueOf(rt),"Weekend");
		for (LocalDate start = dateIn; start.isBefore(dateOut);start=start.plusDays(1))
		{
			int day = start.get(ChronoField.DAY_OF_WEEK);
			if(day==6 || day == 7)
			{
				this.bill+=weekendPrice;
			}
			else this.bill+=weekdayPrice;
		}
		return bill;
	}
	
	//Functions (public methods)
	public void updateReservation()
	{
		int choice,sub;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.printf("Which of the following data would you want to update?\n");
			System.out.println("1:Guest Details");
			System.out.println("2:Room Details");
			System.out.println("3:Check-in date");
			System.out.println("4:Check-out date");
			System.out.println("5:Number of adults");
			System.out.println("6:Number of children");
			System.out.println("7:Reservation Status");
			System.out.println("8:Quit");
			System.out.println("Choice:");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				guestDetails.updateGuestDetails();
				break;
			case 2:
				System.out.println("Currently this function still not available");
				break;
			case 3:
				int flagIn = 0;
				System.out.println("Please enter your check-in date (dd/MM/yyyy) (input -1 to remain unchanged)");
				String stIn = sc.next();
				if(stIn.equals("-1")) break;
				LocalDate dateIn = ReservationController.stringToDate(stIn, formatter);
				while(dateIn==null)
				{
					System.out.println("Please enter check-in date (dd/MM/yyyy) (input -1 to remain unchanged)");
					stIn = sc.next();
					if(stIn.equals("-1")) {flagIn =1 ;break;}
					dateIn = ReservationController.stringToDate(stIn, formatter);
				}
				if(flagIn == 1) break;
					this.setDateIn(dateIn);
					this.bill = calRoomBill();
				break;
			case 4:
				int flagOut = 0;
				System.out.println("Please enter your check-out date (dd/MM/yyyy) (input -1 to remain unchanged)");
				String stOut = sc.next();
				if(stOut.equals("-1")) break;
				LocalDate dateOut = ReservationController.stringToDate(stOut, formatter);
				while(dateOut==null)
				{
					System.out.println("Please enter check-out date (dd/MM/yyyy) (input -1 to remain unchanged)");
					stOut = sc.next();
					if(stOut.equals("-1")) {flagOut =1 ;break;}
					dateOut = ReservationController.stringToDate(stOut, formatter);
				}
				if(flagOut == 1) break;
				this.setDateOut(LocalDate.parse(stOut,formatter)) ;
				this.bill=calRoomBill();
				break;
			case 5:
				//Didn't do error-checking if user input string
				System.out.println("Please enter number of adults (input -1 to remain unchanged)");
				int numAdults = sc.nextInt();
				if(numAdults==-1) break;
				this.setNumAdults(numAdults);
				break;
			case 6:
				System.out.println("Please enter number of children (input -1 to remain unchanged)");
				int numChildren = sc.nextInt();
				if(numChildren==-1) break;
				this.setNumChildren(numChildren);
				break;
			case 7:
				int flagStatus=0;
				System.out.println("Please enter the new Reservation Status (input -1 to remain unchanged)");
				String stStatus = sc.next();
				if(stStatus.equals("-1")) break;
				while(!stStatus.equalsIgnoreCase("comfirmed") && !stStatus.equalsIgnoreCase("waitlist"))
				{
					System.out.println("Invalid input!");
					System.out.println("Please enter Reservation Status (COMFIRMED/WAITLIST)");
					stStatus = sc.next();
					if(stStatus.equals("-1")) {flagStatus=1; break;}
				}
				if(flagStatus==1) break;
				this.setStatus(ResStatus.valueOf(stStatus));
				break;
			default:
				System.out.println("Returning to Reservation Page....");
				break;
			}
		}while(choice<8);
	}
	
	public void displayRes()
	{
		System.out.println("--------------------------------------------------");
		System.out.println("                Reservation receipt                 ");
		System.out.println("--------------------------------------------------");
		System.out.printf("Reservation Code: %s\nReservation status: %s\n",reservationCode,status.name());
		//guestDetails.display();
		//room.display();
		System.out.printf("Guest Name: %s\nGuest Identity: %s\nGuest Contact: %s\n",guestDetails.getName(),guestDetails.getIdentity(),guestDetails.getContact());
		System.out.printf("Room number: %s\nRoom Status: %s\n",room.getRoomNumber(),room.getStatus().name());
		System.out.printf("Check-in date: %s\nCheck-out date: %s\n",dateIn.toString(),dateOut.toString());
		System.out.printf("Number of adults: %d\tNumber of children: %d\n", numAdults,numChildren);
	}
	
	public void displayPartRes()
	{
		System.out.printf("Reservation Code: %s\nReservation status: %s\n",reservationCode,status.name());
		System.out.printf("Guest Name: %s\nGuest Identity: %s\nGuest Contact: %s\n",guestDetails.getName(),guestDetails.getIdentity(),guestDetails.getContact());
	}
	
	public void displayPartGuest()
	{
		System.out.printf("Guest Name: %s\nGuest Identity: %s\nGuest Contact: %s\n",guestDetails.getName(),guestDetails.getIdentity(),guestDetails.getContact());
		System.out.println("--------------------------------------------------");
	}
	

}
