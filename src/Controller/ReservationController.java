import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.UUID;


public class ReservationController {
	//plan A: try to use static (no need pass parameter) (whole class share this array)
	//Plan B: pass parameter so that object not destroyed.
	private static ArrayList <Reservation> reservationList = new ArrayList();
	private static final String SEPARATOR = "|";
	private static final String databaseLocationRes = "C:\\Users\\User\\OneDrive\\Desktop\\ReservationDB.txt";
	private static RoomController rc = new RoomController();
	private static GuestController gc = new GuestController();
	
	
	//Accessor
	public static ArrayList getReservationList()
	{
		return reservationList;
	}
	
	//Display Reservation Page
	public static void displayReservationUI()
	{
		ReservationUI.displayReservationUI();
	}
	
//Read and save file
	private static List fileToStrings(String file) throws IOException
	{
		//Convert file to array of strings
		List data = new ArrayList();
		Scanner sc = new Scanner(new FileInputStream(file));
		try
		{
			while(sc.hasNextLine()) {data.add(sc.nextLine());}
		}
		finally
		{
			sc.close();
		}
		return data;
	}
	
	//ToDo setResStatus to expired if expire; also display the information
	public static void init() throws IOException
	{
		int numExpired=0;
		ArrayList <String> stringArray = (ArrayList) fileToStrings(databaseLocationRes);
		
		for(int i=0;i<stringArray.size();i++)
		{
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer (st, SEPARATOR);
			
			//set variables
			String reservationCode = star.nextToken().trim();
			
			
			//Guest guestDetails;
			String name = star.nextToken().trim();
				//creditCardDetails of guest;
			String cardNumber = star.nextToken().trim();
			String expiryDate = star.nextToken().trim();
			String cvvNumber = star.nextToken().trim();
			//Continue guestDetails
			String address = star.nextToken().trim();
			String country = star.nextToken().trim();
			String gender = star.nextToken().trim();
			String identity = star.nextToken().trim();
			String nationality = star.nextToken().trim();
			String contact = star.nextToken().trim();
			//construct creditcard & guest
			CreditCardDetails ccd = new CreditCardDetails (cardNumber,expiryDate,cvvNumber);
			Guest g = new Guest(name,identity,address,country,gender,nationality,contact,ccd);
			//End of Guest Details
			
			
			//Room room;
			RoomType Type = RoomType.valueOf(star.nextToken().trim());
			String roomNumber = star.nextToken().trim();
			BedType bedType = BedType.valueOf(star.nextToken().trim());
			boolean hasWifi = Boolean.parseBoolean(star.nextToken().trim());
			boolean hasView = Boolean.parseBoolean(star.nextToken().trim());
			boolean hasSmoking = Boolean.parseBoolean(star.nextToken().trim());
			RoomStatus status = RoomStatus.valueOf(star.nextToken().trim());
			//Construct room
			Room r = new Room(Type,roomNumber,bedType,hasWifi,hasView,hasSmoking,status);
			//END OF ROOM
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dateA = star.nextToken().trim();
			String dateB = star.nextToken().trim();
			LocalDate dateIn = LocalDate.parse(dateA,formatter) ;
			LocalDate dateOut = LocalDate.parse(dateB,formatter) ;
			int numAdults = Integer.parseInt(star.nextToken().trim());
			int numChildren = Integer.parseInt(star.nextToken().trim());
			LocalDate today = LocalDate.now();
			ResStatus resStatus;
			String stStatus = star.nextToken().trim();
			if(today.isAfter(dateIn)) 
			{
				numExpired++;
				if(!stStatus.equals("EXPIRED"));
				{
					resStatus = ResStatus.EXPIRED;
					System.out.printf("Reservation Code: %s has Expired\n",reservationCode);
				}
				
			}
			else {resStatus = ResStatus.valueOf(stStatus);}
			double bill = Double.parseDouble(star.nextToken().trim());
			
			//construct object
			Reservation reservation = new Reservation(reservationCode,g,r,dateIn,dateOut,numAdults,numChildren,resStatus,bill);			
			//append to list
			reservationList.add(reservation);			
		}
		System.out.printf("Number of total expired reservation: %d\n", numExpired);
	}
	
	private static void StringsToFile(String filename,List data) throws IOException
	{
		PrintWriter out = new PrintWriter (new FileWriter(filename));
		try
		{
			for(int i=0;i<data.size() ; i++)
			{
				out.println((String)data.get(i));
			}
		}
		finally
		{
			out.close();
		}
	}
	
	public static void save() throws IOException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		List alw = new ArrayList();
		for(int i=0;i<reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			StringBuilder st = new StringBuilder();
			
			st.append(r.getReservationCode().trim());
			st.append(SEPARATOR);
			
			//Guest
			Guest G = r.getGuestDetails();
			CreditCardDetails C = G.getCreditCardDetails();
			st.append(G.getName().trim());
			st.append(SEPARATOR);
			
				st.append(C.getCardNumber().trim());
				st.append(SEPARATOR);
				st.append(C.getExpiryDate().trim());
				st.append(SEPARATOR);
				st.append(C.getCvvNumber().trim());
				st.append(SEPARATOR);
				
			st.append(G.getAddress().trim());
			st.append(SEPARATOR);
			st.append(G.getCountry().trim());
			st.append(SEPARATOR);
			st.append(G.getGender().trim());
			st.append(SEPARATOR);
			st.append(G.getIdentity().trim());
			st.append(SEPARATOR);
			st.append(G.getNationality().trim());
			st.append(SEPARATOR);
			st.append(G.getContact().trim());
			st.append(SEPARATOR);
			//End guest
			
			//Room
			Room room = r.getRoom();
			st.append(room.getType().name().trim());
			st.append(SEPARATOR);
			st.append(room.getRoomNumber().trim());
			st.append(SEPARATOR);
			st.append(room.getBedType().name().trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(room.isHasWifi()).trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(room.isHasView()).trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(room.isHasSmoking()).trim());
			st.append(SEPARATOR);
			st.append(room.getStatus().name().trim());
			st.append(SEPARATOR);
			//End room
			
			String formattedDateIn = r.getDateIn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			st.append(formattedDateIn.trim());
			st.append(SEPARATOR);
			String formattedDateOut = r.getDateOut().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			st.append(formattedDateOut.trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(r.getNumAdults()).trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(r.getNumChildren()).trim());
			st.append(SEPARATOR);
			st.append(r.getStatus().name().trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(r.getRoomBill()).trim());
			
			alw.add(st.toString());
		}
		StringsToFile(databaseLocationRes, alw);
	}
//END Read and Save file
	
	//All functions
	//Possibly allow method overloading for these searching and updating and removing
	public static int searchReservationByCode()
	{
		
		System.out.println("Please enter the Reservation Code");
		Scanner sc = new Scanner(System.in);
		String st = sc.next();
		for(int i=0;i<reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			if(st.equals(r.getReservationCode())) {
				//sc.close();
				return i;			
			}
		}
		//return -1 if not found;
		System.out.println("Reservation Code not found");
		//sc.close();
		return -1;
	}
	
	public static int searchReservationByIC()
	{
		System.out.println("Please enter the IC number/PassPort Number");
		Scanner sc = new Scanner (System.in);
		String st =sc.next();
		for(int i=0;i<reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			if(st.equals(r.getGuestDetails().getIdentity()))return i;
		}
		//return -1 if not found;
		System.out.println("IC not found");
		return -1;
	}
	
	
	public static int searchReservationByName()
	{
		//Ignore upper and lower case
		System.out.println("Please enter the name");
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();
		int flag=0;
		for(int i=0; i< reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			if(st.equalsIgnoreCase(r.getGuestDetails().getName()))
			{
				System.out.printf("Index: %d \n", i);
				r.displayPartGuest();
				flag=1;
			}
		}
		if(flag==1) 
		{
			System.out.println("Please select which index");
			int index = sc.nextInt();
			return index;
		}
			
			System.out.println("Name not found");
			return -1;
		}
	
	public static void updateReservation()
	{
		int index = -1,choice;
		do
		{
			System.out.println("1:By Reservation Code");
			System.out.println("2:By Identity Card");
			System.out.println("3:By Name");
			Scanner sc = new Scanner(System.in);
			choice=sc.nextInt();
			if(choice>3 || choice<1) System.out.println("Error! Please select 1,2 or 3");
		}while(choice>3 || choice<1);
		switch(choice)
		{
		case 1:
			index=searchReservationByCode();
			break;
		case 2:
			index=searchReservationByIC();
			break;
		case 3:
			index=searchReservationByName();
			break;
		}
		if(index==-1) {System.out.println("NotFound"); return;}
		Reservation r = (Reservation) reservationList.get(index);
		r.displayPartRes();
		r.updateReservation();
	}
	
	public static void removeReservationByCode()
	{
		int index = searchReservationByCode();
		if(index==-1) {System.out.println("NotFound"); return;}
		
		Reservation r = (Reservation) reservationList.get(index);
		ArrayList roomList = rc.getRoomList();
		int room_index = rc.searchRoom(r.getRoom().getRoomNumber());
		Room room = (Room) roomList.get(room_index);
		room.setStatus(RoomStatus.VACANT);
		
		reservationList.remove(index);
	}
	
	public static void removeReservationByIC()
	{
		int index = searchReservationByIC();
		if(index==-1) {System.out.println("NotFound"); return;}
		Reservation r = (Reservation) reservationList.get(index);
		ArrayList roomList = rc.getRoomList();
		int room_index = rc.searchRoom(r.getRoom().getRoomNumber());
		Room room = (Room) roomList.get(room_index);
		room.setStatus(RoomStatus.VACANT);
		reservationList.remove(index);
	}
	
	public static Reservation createReservation() 	
	{
		System.out.println("Create Reservation....");
		String reservationCode = UUID.randomUUID().toString();
		//Guest g = null;
		//Room r = null;
		Guest g = gc.createGuestProfileForReservation();
		Room r = rc.createRoomForReservation();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Please enter check-in date (dd/MM/yyyy) (input -1 to cancel)");
			Scanner scan = new Scanner(System.in);
			String stIn;
			stIn = scan.next();
			if(stIn.equals("-1")) return null;
			LocalDate dateIn = ReservationController.stringToDate(stIn, formatter);
			while(dateIn==null)
			{
				System.out.println("Please enter check-in date (dd/MM/yyyy) (input -1 to cancel)");
				stIn = scan.next();
				if(stIn.equals("-1")) return null;
				dateIn = ReservationController.stringToDate(stIn, formatter);
			}
			System.out.println("Please enter check-out date (dd/MM/yyyy) (input -1 to cancel)");
			String stOut=scan.next();
			if(stOut.equals("-1")) return null;
			LocalDate dateOut = ReservationController.stringToDate(stOut, formatter);
			while(dateOut==null)
			{
				System.out.println("Please enter check-out date (dd/MM/yyyy) (input -1 to cancel)");
				stOut = scan.next();
				if(stOut.equals("-1")) return null;
				dateOut = ReservationController.stringToDate(stOut, formatter);
			}
			System.out.println("Please enter number of adults (input -1 to cancel)");
			int numAdults = scan.nextInt();
			if(numAdults==-1) return null;
			System.out.println("Please enter number of children (input -1 to cancel)");
			int numChildren = scan.nextInt();
			if(numChildren==-1) return null;
			//What scenario in wait-list, what scenario in comfirmed?
			//Currently you have to key-in status
			System.out.println("Please enter Reservation Status (COMFIRMED/WAITLIST) (input -1 to cancel)");
			String stStatus = scan.next();
			if(stStatus.equals("-1")) return null;
			while(!stStatus.equalsIgnoreCase("comfirmed") && !stStatus.equalsIgnoreCase("waitlist"))
			{
				System.out.println("Invalid input!");
				System.out.println("Please enter Reservation Status (COMFIRMED/WAITLIST)");
				stStatus = scan.next();
				if(stStatus.equals("-1")) return null;
			}
			ResStatus resStatus = ResStatus.valueOf(stStatus.toUpperCase());
			
			Reservation reservation = new Reservation(reservationCode,g,r,dateIn,dateOut,numAdults,numChildren,resStatus);
			
			//ArrayList roomList = rc.getRoomList();
			//int room_index = rc.searchRoom(r.getRoomNumber());
			//Room room = (Room) roomList.get(room_index);
			//room.setStatus(RoomStatus.RESERVED);
			
			reservation.displayRes();
			int haveChange;
			do
			{
				System.out.println("Do you want to make any changes?");
				System.out.println("1: Yes,I would like to make changes.");
				System.out.println("2: No, but I would like to cancel my reservation.");
				System.out.println("3: No, I would like to proceed and I have comfirmed my reservation.");
				haveChange = scan.nextInt();
				if(haveChange==2) return null;
				if(haveChange==1) reservation.updateReservation();
			}while(haveChange!=3);
			return reservation;
	}
	
	public static void walkIn()
	{
		Reservation r = createReservation();
		reservationList.add(r);
		
		checkIn(r);
	}
	
	public static void checkIn()
	{
		ArrayList guestList = gc.getGuestList();
		int index = searchReservationByCode();
		
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
				ArrayList roomList = rc.getRoomList();
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
		ArrayList guestList = gc.getGuestList();
		
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
		ArrayList roomList = rc.getRoomList();
		int room_index = rc.searchRoom(r.getRoom().getRoomNumber());
		Room room = (Room) roomList.get(room_index);
		room.setStatus(RoomStatus.OCCUPIED);
		
	}

	//probably incomplete
	public static void checkOut()
	{
		int i = searchReservationByCode();
		Reservation r = (Reservation) reservationList.get(i);
		
		//UPDATE ROOM STATUS //Reset may be better
		//possibly delete reservation
		ArrayList roomList = rc.getRoomList();
		int room_index = rc.searchRoom(r.getRoom().getRoomNumber());
		Room room = (Room) roomList.get(room_index);
		room.setStatus(RoomStatus.VACANT);
	}
	
	public static void printAllReservation()
	{
		//有点傻啊。。。
		for(int i=0;i<reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			r.displayRes();
		}
	}

	public static void printReservation()
	{
		int i = searchReservationByCode();
		Reservation r = (Reservation) reservationList.get(i);
		r.displayRes();
		System.out.printf("Bill: %.2f\n", r.getRoomBill());
	}

	//ERROR-CHECKING Method
	//These error-checking method should not be inside reservation controller though
	//it is better we have another class call "ErrorChecking" class.
	public static LocalDate stringToDate(String dateStr, DateTimeFormatter dateFormatter) 
	{
		
		LocalDate date = null;
		try {
			date = LocalDate.parse(dateStr, dateFormatter);
		} catch (DateTimeParseException e) {
				//handle exception
			System.out.println("Invalid Date!");
		}
		return date;
	}	
	
	public static boolean isInteger(String i)
	{
		int integer;
		try
		{
			integer = Integer.parseInt(i);
		}
		catch (NumberFormatException e)
		{
			System.out.println("Invalid Input!");
			return false;
		}
		return true;
	}
	
}
