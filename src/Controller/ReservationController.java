package Controller;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.UUID;

import Class.Guest;
import Class.ResStatus;
import Class.Reservation;
import Class.Room;
import Class.Room.RoomStatus;
import Class.Room.RoomType;
import UI.ReservationUI;


/**
 * ReservationController contains all the methods required by the entity class Reservation and the UI class ReservationUI
 * @author Chia Jer Wen
 * @version 1.0
 * @since 2022-04-14
 * 
 */
public class ReservationController {
	/**
	 * {@code reservationList} is an array of {@code Reservation}
	 * We store each individual object of {@code Reservation} into this array. 
	 */
	private static ArrayList <Reservation> reservationList = new ArrayList();
	/**
	* The {@code SEPARATOR} is used to separate the values of the attributes stored in our database, this helps us to extract and store the data properly.
	*/
	private static final String SEPARATOR = "|";
	/**
	* {@code databaseLocationRes} is the location of the reservation database where we will be storing all the attributes of reservation.
	*/
	private static final String databaseLocationRes = "src\\database\\Reservation.txt";
	/**
	 * {@code formatter} stores the format of date which was set to (dd/MM/yyyy) format
	 */
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	
	
	//Getter
	/**
	* Gets the array list containing {@code Reservation} classes.
	* @return reservationList, array list containing objects of {@code Reservation}
	*/
	public static ArrayList<Reservation> getReservationList()
	{
		return reservationList;
	}
	
	//Setter
	/**
	* Sets the {@code reservationList}, the array list containing objects of {@code Reservation}.
	* @param resList array list containing {@code Reservation} class.
	*/
	public static void setReservationList(ArrayList <Reservation> resList)
	{
		reservationList = resList;
	}
	//Display Reservation Page
	/**
	* connects the {@code ReservationController} and the {@code ReservationUI} class.
	* By using this method, {@code ReservationUI} will then display all the options that the staff can choose with regards to reservation.
	*/
	public static void displayReservationUI()
	{
		ReservationUI.displayReservationUI();
	}
	
	
	//Read and save file
	/**
	 * Reads the values of the attributes in our database.
	 * Each line in our database corresponds to a different guest and this function is able to read all the lines in our database to extract those values.
	 * @param file the location of the reservation database file which can be read
	 * @return the lists of strings of data that are extracted from the database
	 * @throws IOException if stream to file cannot be read or to closed
	 */
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
	/**
	 * Read and convert the text of a file into new object of {@code Reservation}
	 * <br>
	 * The reservation created is updated to {@code EXPIRED} if the Reservation's guest fails to check-in before check-in date and finally then stored in {@code reservationList}.
	 * <p>
	 * Calls {@code fileToStrings} to extract strings of data from the reservation database<br>
	 * extracts each value of attribute from the string of data separated by {@code SEPARATOR}
	 * <br>
	 * Declare an object of {@code Guest} and of {@code Room} and links them to specific object of {@code Guest} and {@code Room} according to the name and identity of Reservation's guest, and roomID of Reservation's room.
	 * <br>
	 * Create a new object of {@code Reservation} by passing through the attributes extracted and declared
	 * <br>
	 * Add the created object of {@code Reservation} into {@code reservationList} and repeat for every string of data
	 * <p>
	 * Call {@code autoToExpired} to update every reservation which fail to check-in before its check-in date to {@code EXPIRED}
	 * @throws IOException if stream to file cannot be read or to closed
	 */
	public static void init() throws IOException
	{
		ArrayList <String> stringArray = (ArrayList) fileToStrings(databaseLocationRes);
		
		for(int i=0;i<stringArray.size();i++)
		{
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer (st, SEPARATOR);
			
			//set variables
			String reservationCode = star.nextToken().trim();
			
			
			//Guest guestDetails;
			String name = star.nextToken().trim();
			String identity = star.nextToken().trim();
			Guest g = GuestController.getGuest(name, identity);

			//Room 
			String roomNumber = star.nextToken().trim();
			Room r = RoomController.getRoom(roomNumber);
			//END OF ROOM
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dateA = star.nextToken().trim();
			String dateB = star.nextToken().trim();
			LocalDate dateIn = LocalDate.parse(dateA,formatter) ;
			LocalDate dateOut = LocalDate.parse(dateB,formatter) ;
			int numAdults = Integer.parseInt(star.nextToken().trim());
			int numChildren = Integer.parseInt(star.nextToken().trim());
			LocalDate today = LocalDate.now();

			String stStatus = star.nextToken().trim();
			
			ResStatus resStatus = ResStatus.valueOf(stStatus);
			double bill = Double.parseDouble(star.nextToken().trim());
			
			//construct object
			Reservation reservation = new Reservation(reservationCode,g,r,dateIn,dateOut,numAdults,numChildren,resStatus,bill);			
			//append to list
			reservationList.add(reservation);			
		}
		ReservationController.autoToExpired();
	}
	/**
	 * overwrite the contents of a text file in its entirety by given list of data
	 * @param filename an existing reservation database file which can be written
	 * @param data the list of texts to be written into the file, each string contributes to a new line in the file
	 * @throws IOException if stream to file cannot be written to or closed.
	 */
	private static void stringsToFile(String filename,List data) throws IOException
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
	/**
	 * Convert each object of {@code Reservation} stored in the {@code reservationList} into list of {@code String}.
	 * Then overwrite the reservation database file with the list of data converted.
	 * <p>
	 * Each object of {@code Reservation} is separated by new line and each attributes of the object is separated by {@code SEPARATOR}
	 * Then calls {@code StringsToFile} to overwrite the content of reservation database file by passing the list of data through parameter.
	 * @throws IOException if stream to file cannot be written to or closed.
	 */
	public static void save() throws IOException
		{
			
			List alw = new ArrayList();
			for(int i=0;i<reservationList.size();i++)
			{
				Reservation r = (Reservation) reservationList.get(i);
				StringBuilder st = new StringBuilder();
				
				st.append(r.getReservationCode().trim());
				st.append(SEPARATOR);
				
				//Guest
				Guest G = r.getGuestDetails();
				st.append(G.getName().trim());
				st.append(SEPARATOR);
				st.append(G.getIdentity().trim());
				st.append(SEPARATOR);
				//End guest
				
				//Room
				Room room = r.getRoom();
				st.append(room.getRoomNumber().trim());
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
				st.append(String.valueOf(r.getBill()).trim());
				
				alw.add(st.toString());
			}
			stringsToFile(databaseLocationRes, alw);
		}
	/**
	 * Ask for user input to search for the target reservation. Then update the attributes of a target reservation. 
	 * <p>
	 * Ask input from user and search through the {@code reservationList} to find the target reservation
	 * according to the user input through method {@link #searchReservationSelection()}.
	 * This method will terminate if the reservation is not found; 
	 * <p>
	 * otherwise if found, it calls {@link #printReservation(Reservation)}
	 * to print the reservation receipt, and then calls and pass the reservation found to 
	 * calls {@code updateReservation(Reservation)} in order to update the attribute for the reservation.
	 * @see #updateReservation(Reservation)
	 * 
	 */
	public static void updateReservation()
	{
		Reservation r = ReservationController.searchReservationSelection();
		if(r==null) {System.out.println("NotFound"); return;}
		ReservationController.printReservation(r);
		ReservationController.updateReservation(r);
	}
	
	/**
	 * Create a new object of {@code Reservation} by asking several essential inputs for constructing.
	 * <br>
	 * Auto update the room status of the Reservation's room, and add the guest details into the guest database.
	 * <p>
	 * Calls method {@code createGuestProfile()} from {@code GuestController} to ask input for creating a guest
	 * and also add the object of {@code Guest} created into the guest database in {@code GuestController}
	 * <p>
	 * From {@code RoomController} :<br>
	 * call method {@code scanRoomType} to ask input for room type from user
	 * If the input room type has vacant rooms 
	 * call method {@code displayVacantRoomsByType} to display the vacant rooms corresponding to the room type
	 * Or else if the input roomtype has no vacant rooms
	 * call method {@code displayAllRoomsByType} to display all rooms corresponding to the room type.
	 * Then this method {@code createReservation} will continue ask input for the roomID.
	 * The user is expected to key in roomID that were displayed before input for roomID was asked.
	 * The input will be repeatedly asked until roomID matches the room type declared previously
	 * An object of {@code Room} is declared and link to the room which is stored in {@code roomList}
	 * by using the method {@code getRoom} in {@code RoomController}
	 * <p>
	 * Continue ask input for check-in date and check-out date by calling the method {@code scanDateIn} and {@code scanDateOut}
	 * The user is expected to key in a valid check-out date which is after check-in date
	 * The input will be repeated to check whether if the dates are valid.
	 * Then, call method {@code scanNumOfAdult} and {@code scanNumOfChildren}
	 * to read input for number of adults and number of children respectively.
	 * The reservation status is {@code CONFIRMED} if there is vacant rooms
	 * otherwise is {@code WAITLIST} if there does not have vacant rooms.
	 * <p>
	 * After having the attributes and the objects, call constructor method {@code Reservation} in Class {@code Reservation} to create an object of {@code Reservation}
	 * Calls method {@code printReservation} to display the reservation receipt.
	 * Selections of proceed,cancel, and update reservation is provided for confirmation.
	 * <br>
	 * If proceed, the room status of this reservation's room will be set to {@code RESERVED} if it is originally {@code VACANT}
	 * <br>
	 * If choose to cancel this reservation,the guest profile created will be removed from the {@code guestList} in the {@code GuestController} and a {@code null} reservation will be returned.
	 * <br>
	 * If choose to update this reservation, call {@code updateReservation(Reservation)} to ask input for attributes the user would like to change.
	 *  Display the reservation receipt which would reflect the changes made by update. And confirmation will be asked again.
	 * 
	 * 
	 * @see Reservation#Reservation(String, Guest, Room, LocalDate, LocalDate, int, int, ResStatus)
	 * @see Controller.GuestController#createGuestProfile()
	 * 
	 * 
	 * @return the object of Class {@code Reservation} which is just created, null if cancelled halfway
	 */
	public static Reservation createReservation()	
	{
		System.out.println("Create Reservation....");
		String reservationCode = UUID.randomUUID().toString();
		Guest g = GuestController.createGuestProfile();
		Scanner scan = new Scanner(System.in);
		
		//RoomType
		String roomID;
		Room.RoomType roomType = RoomController.scanRoomType();
		if(roomType == null)
		{
			GuestController.removeGuestProfile(g.getName(),g.getIdentity());
			return null;
		}
		//Printing rooms
        if(RoomController.numberOfVacantByRoomType(roomType)==0)
        {
        	System.out.println("There is no VACANT rooms");
        	System.out.printf("Printing all %s rooms.....\n",roomType);
        	RoomController.displayAllRoomsByType(roomType);
        }
        else
        {
    		RoomController.displayVacantRoomsByType(roomType);
        }
        //RoomID
        Room room;
        do
        {
    		do
    		{
    			roomID = RoomController.scanRoomNumber();
    			if(roomID == null)
    			{
    				GuestController.removeGuestProfile(g.getName(),g.getIdentity());
    				return null;
    			}
    			if(! RoomController.checkRoomExist(roomID)) System.out.println("Room not exist");
    		}while(! RoomController.checkRoomExist(roomID));
    		room = RoomController.getRoom(roomID);
    		if(room == null)
    		if(room.getRoomType() != roomType) System.out.println("RoomType not match");
        }while(room.getRoomType() != roomType);				
		//Update reservation status
		RoomStatus roomStatus = room.getRoomStatus();
		ResStatus resStatus;
		if(roomStatus!=RoomStatus.VACANT) 
		{ 
			System.out.println("This room is currently unavailable.\nThe reservation will be in the waitlist."); 
			resStatus = ResStatus.WAITLIST;
		}
		else
		{
			resStatus = ResStatus.CONFIRMED;
		}
		//End of Room related stuff
		LocalDate dateIn = ReservationController.scanDateIn(formatter);
		if(dateIn==null)
		{
			GuestController.removeGuestProfile(g.getName(),g.getIdentity());
			return null;
		}
		
		LocalDate dateOut = ReservationController.scanDateOut(formatter);
		if(dateOut==null)
		{
			GuestController.removeGuestProfile(g.getName(),g.getIdentity());
			return null;
		}
		
		while (dateOut.compareTo(dateIn) <=0) {
			System.out.println("\nDates entered are invalid, the minimum stay duration is 1 day");
			dateIn = ReservationController.scanDateIn(formatter);
			if(dateIn==null)
			{
				GuestController.removeGuestProfile(g.getName(),g.getIdentity());
				return null;
			}
			
			dateOut = ReservationController.scanDateOut(formatter);
			if(dateOut==null)
			{
				GuestController.removeGuestProfile(g.getName(),g.getIdentity());
				return null;
			}
		}

		
		int numAdults = ReservationController.scanNumOfAdult();
		if(numAdults == -1)
		{
			GuestController.removeGuestProfile(g.getName(),g.getIdentity());
			return null;
		}
		
		int numChildren = ReservationController.scanNumOfChildren();
		if(numChildren == -1) 
		{
			GuestController.removeGuestProfile(g.getName(),g.getIdentity());
			return null;
		}

			
		Reservation reservation = new Reservation(reservationCode,g,room,dateIn,dateOut,numAdults,numChildren,resStatus);
		int haveChange;
		do
		{
			ReservationController.printReservation(reservation);
			System.out.println("Do you want to make any changes?");
			System.out.println("1: Yes,I would like to make changes.");
			System.out.println("2: No, but I would like to cancel my reservation.");
			System.out.println("3: No, I would like to proceed and confirm my reservation.");
			haveChange = ReservationController.scanChoice(3);
			switch(haveChange)
			{
			case 1: 
				ReservationController.updateReservation(reservation);
				break;
			case 2: 
				GuestController.removeGuestProfile(g.getName(),g.getIdentity());
				return null;
			default:
				break;
			}
			
		}while(haveChange==1);
		
		if(reservation.getRoom().getRoomStatus()==RoomStatus.VACANT) room.setRoomStatus(RoomStatus.RESERVED);
		return reservation;
	}
	/**
	 * display every reservation which are stored in {@code reservationList}
	 */
	public static void printAllReservation()
	{
		for(int i=0;i<reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			ReservationController.printReservation(r);
		}
	}
	/**
	 * display every reservation which is not {@code EXPIRED} which are stored in {@code reservationList}
	 */
	public static void printAllReservationExisting()
	{
		for(int i=0;i<reservationList.size();i++)
		{
			
			Reservation r = (Reservation) reservationList.get(i);
			if (r.getStatus() == ResStatus.EXPIRED) {
				continue;
			}
			ReservationController.printReservation(r);
		}
	}


	/**
	 * Allows selection of search method: {@code searchReservationByCode} and {@code searchReservationByIC}
	 * . Use the selected search method to get the target reservation
	 * @see #searchReservationByCode(String)
	 * @see #searchReservationByIC()
	 * @return the target reservation, {@code null} if not found
	 */	
	public static Reservation searchReservationSelection()
	{
		System.out.println("Please select the search method you would like to use");
		System.out.println("1:By Reservation Code");
		System.out.println("2:By Passport/ IC number");
		int choice = ReservationController.scanChoice(2);
		Reservation r = null;
		switch(choice)
		{
		case 1:
			r = ReservationController.searchReservationByCode(ReservationController.scanReservationCode());
			break;
		case 2:
			r = ReservationController.searchReservationByIC();
			break;

		}
		return r;
	}
	
	//Auto-updaters 
	/**
	 * Search through the {@code reservationList} to get the reservation which is in {@code WAITLIST} and has
	 * the same room type as the argument, and also the earliest check-in date.
	 * If there exist two reservation having the same earliest check-in date,
	 *  the first one will be returned.
	 * @param rt Room Type of the room.
	 * @return the reservation which has the same room type as {@code rt} and earliest check-in date
	 * <br>
	 * {@code null} if none of the reservation which has same room type as {@code rt} is in {@code WAITLIST} 
	 */
	public static Reservation returnEarliestCheckInReservationByRoomType(Room.RoomType rt)
	{
		int flag=0;
		LocalDate earliest = null;
		Reservation result = null;
		for(int i=0;i<reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			if(rt == r.getRoom().getRoomType())
			{
				if(r.isWaitlist())
				{
					if(flag == 0) {flag = 1; earliest = r.getDateIn(); result = r;}
					else
					{
						if(r.getDateIn().isBefore(earliest)) {earliest = r.getDateIn(); result = r;}
					}
				}
			}
		}
		return result;
	}
	/**
	 * Assign the {@code room} given, to a 
	 * reservation in {@code WAITLIST} which this Reservation's room has the same room type as {@code room} given,
	 * and also the earliest check-in date
	 * <p>
	 * calls {@code returnEarliestCheckInReservation} to search and get the reservation which
	 * fulfills the requirement. If no such reservation is found, stop this function and return false
	 * Otherwise, set the reservation status to {@code CONFIRMED} through {@code setStatus},
	 * and also set the Reservation's room to the new room passed through argument.
	 * @param room the room that was just made to {@code VACANT}
	 * @return {@code true} if this reservation is updated, otherwise {@code false} if no suitable reservation is found.
	 */
	public static boolean autoUpdateReservationDetails(Room room)
	{
		Room.RoomType rt = room.getRoomType();
		Reservation r = returnEarliestCheckInReservationByRoomType(rt);
		if(r==null) return false;
		r.setStatus(ResStatus.CONFIRMED);
		/////////////////////
		r.setRoom(room);
		//////////////////////
		return true;
	}
	/**
	 * Changes the reservation status of each reservation in {@code reservationList} to {@code EXPIRED}
	 * if the check-in date has passed, that is after 4pm on the check-in date
	 * <p>
	 * If the reservation is originally {@code CONFIRMED} then the room assigned to this reservation will be made vacant again.
	 */
	public static void autoToExpired()
	{
		LocalTime checkInDeadLineTime = LocalTime.parse("16:00:00");
		for(int i = 0; i<reservationList.size();i++)
		{
			Reservation r = reservationList.get(i);
			if(r.getDateIn().isBefore(LocalDate.now()) ||(r.getDateIn().isEqual(LocalDate.now()) && checkInDeadLineTime.isBefore(LocalTime.now())))
			{
				if(r.isConfirmed())
				{
					r.setStatus(ResStatus.EXPIRED);
					RoomController.toVacant(r.getRoom().getRoomNumber());
				}
				else if(!r.isCheckedIn()) r.setStatus(ResStatus.EXPIRED);
			}
		}
	}
	
	//Validators
	/**
	 * Test if whether the string passed through the argument is integer or not.
	 * Print an error statement if the string is not a integer.
	 * @param st the {@code String} to be tested
	 * @return {@code true} if {@code st} is {@code integer}, otherwise
	 * <br>
	 * {@code false} if {@code st} contains non-integer character
	 */
	public static boolean integerValidator(String st)
	{
		try
		{
			Integer.parseInt(st);
		}
		catch(NumberFormatException e)
		{
			System.out.println(st + " is not a valid input!");
			return false;
		}
		return true;
	}
	/**
	 * Test if whether the string passed through the argument is double or not.
	 * Print an error statement if the string is not a double.
	 * @param st the {@code String} to be tested
	 * @return {@code true} if {@code st} is {@code double}, otherwise
	 * <br>
	 * {@code false} if {@code st} is not a {@code double}
	 */
	public static boolean doubleValidator(String st)
	{
		try
		{
			Double.parseDouble(st);
		}
		catch(Exception e)
		{
			System.out.println(st + " is not a valid input!");
			return false;
		}
		return true;
	}
	/**
	 * Test if whether the string passed through the argument has same format as {@code dateFormatter}.
	 * Print an error statement if the string is not a {@code LocalDate}.
	 * @see #formatter
	 * @param dateStr the {@code String} to be tested
	 * @param dateFormatter Format of the date.
	 * @return {@code true} if {@code dateStr} is {@code LocalDate}, otherwise
	 * <br>
	 * {@code false} if {@code dateStr} is not a {@code LocalDate}
	 */
	public static boolean dateValidator(String dateStr, DateTimeFormatter dateFormatter) 
	{
		
		try {
			LocalDate.parse(dateStr, dateFormatter);
		} catch (DateTimeParseException e) {
				//handle exception
			System.out.println(dateStr + " is an Invalid Date!");
			return false;
		}
		return true;
	}	
		
	//Scanner 
	/**
	 * Asks input for number of children from user, and repeat if input is invalid
	 * <p>
	 * Display messages to ask for input repeatedly for number of children from user until the input is valid. 
	 * Calls method {@code integerValidator} to test whether the input is valid.
	 * Display an error message and then repeat if the input is invalid or the input is smaller than -1
	 * @return the input number of children, must be >= -1
	 */
	public static int scanNumOfChildren()
	{
		Scanner sc = new Scanner(System.in);
		String st;
		int result ;
		do
		{
			do
			{
				System.out.println("Please enter the number of children.");
				System.out.println("Input -1 to end this function.");
				st = sc.next();
			}while(!integerValidator(st));
			result = Integer.parseInt(st);
			if(result == -1) return -1;
			if(result<0) System.out.println("Negative input is not allowed");
		}while(result<0);
		return result;
	}
	/**
	 * Ask input for number of adults from user, and repeat if input is invalid
	 * <p>
	 * Display messages to ask for input repeatedly for number of adults from user until the input is valid. 
	 * Calls method {@code integerValidator} to test whether the input is valid.
	 * Display an error message and then repeat if the input is invalid or the input is smaller than -1
	 * @return the input number of adults, must be >= -1
	 */
	public static int scanNumOfAdult()
	{
		Scanner sc = new Scanner(System.in);
		String st;
		int result;
		do
		{
			do
			{
				System.out.println("Please enter the number of adults.");
				System.out.println("Input -1 to end this function.");
				st = sc.next();
			}while(!integerValidator(st));
			result = Integer.parseInt(st);
			if(result == -1) return -1;
			if(result<0) System.out.println("Negative input is not allowed");
		}while(result<0);

		return result;
	}
	/**
	 * Ask input for the choice made for options from user, and repeat if input is invalid
	 * <p>
	 * Display messages to ask for input repeatedly for choice from user until the input is valid. 
	 * Calls method {@code integerValidator} to test whether the input is valid.
	 * Display an error message and then repeat if the input is invalid
	 * or the choice must be positive integer or choice less than max.
	 * @param max the maximum integer which the user can key in
	 * @return the choice made for options, must between 1 and max inclusive.
	 */
	public static int scanChoice(int max)
	{
		int result = 0;
		do
		{
			Scanner sc = new Scanner(System.in);
			String st;
			do
			{
				System.out.println("Please enter your choice:");
				st = sc.next();
			}while(!integerValidator(st));
			result = Integer.parseInt(st);
			if(result>max || result<1) System.out.println("Choice not within the range");
		}while(result>max || result<1);
		return result;
	}
	/**
	 * Ask input for check-in date from user, and repeat if input is invalid
	 * <p>
	 * Display messages to ask for input repeatedly for Check-In date from user until the input is valid. 
	 * Calls method {@code dateValidator} to test whether the input is valid.
	 * Display an error message and then repeat if the input does not match the {@code dateFormatter}
	 * @param dateFormatter the format of a date
	 * @return check-in date according to the input <br>
	 * {@code null} if -1 is input
	 */
	public static LocalDate scanDateIn(DateTimeFormatter dateFormatter)
	{
		Scanner sc = new Scanner(System.in);
		String st;
		LocalDate result;
		do
		{
			do
			{
				System.out.println("Please enter your check-in date (dd/MM/yyyy)");
				System.out.println("Input -1 to end this function.");
				st = sc.next();
				if(st.equals("-1")) return null;
			}while(!dateValidator(st,dateFormatter));
			result = LocalDate.parse(st,dateFormatter);
			if(result.isBefore(LocalDate.now()))
			{
				System.out.println("You are not allowed to choose date before today");
			}
		}while(result.isBefore(LocalDate.now()));
		return result;
	}
	/**
	 * Ask input for check-out date from user, and repeat if input is invalid
	 * <p>
	 * Display messages to ask for input repeatedly for Check-Out date from user until the input is valid. 
	 * Calls method {@code dateValidator} to test whether the input is valid.
	 * Display an error message and then repeat if the input does not match the {@code dateFormatter}
	 * @param dateFormatter the format of a date
	 * @return check-out date according to the input <br>
	 * {@code null} if -1 is input
	 */
	public static LocalDate scanDateOut(DateTimeFormatter dateFormatter)
	{
		Scanner sc = new Scanner(System.in);
		String st;
		LocalDate result;
		do
		{
			do
			{
				System.out.println("Please enter your check-out date (dd/MM/yyyy)");
				System.out.println("Input -1 to end this function.");
				st = sc.next();
				if(st.equals("-1")) return null;
			}while(!dateValidator(st,dateFormatter));
			result = LocalDate.parse(st,dateFormatter);
			if(result.isBefore(LocalDate.now()))
			{
				System.out.println("You are not allowed to choose date before today");
			}
		}while(result.isBefore(LocalDate.now()));
		return result;
	}
	/**
	 * Ask input for Reservation Code from user
	 * 
	 * @return the reservation code input by user<br>
	 * {@code null} if -1 is input
	 */
	public static String scanReservationCode()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the reservation code");
		System.out.println("Input -1 to end this function.");
		String st = sc.next();
		if(st.equals("-1")) return null;
		return st;
	}

	//Printers
	/**
	 * Prints a particular reservation details (all attributes from {@code Reservation})
	 * @param r the reservation the user wants to print
	 */
	public static void printReservation(Reservation r)
	{
		if(r == null) return;
		System.out.println("--------------------------------------------------");
		System.out.println("                Reservation receipt                 ");
		System.out.println("--------------------------------------------------");
		System.out.printf("Reservation Code: %s\nReservation status: %s\n",r.getReservationCode(),r.getStatus().name());
		Guest guestDetails = r.getGuestDetails();
		Room room = r.getRoom();
		System.out.printf("Guest Name: %s\nGuest Passport/ IC number: %s\nGuest Contact: %s\n",guestDetails.getName(),guestDetails.getIdentity(),guestDetails.getContact());
		System.out.printf("Room number: %s\nRoom Type: %s\n",room.getRoomNumber(),room.getRoomType().name());
		System.out.printf("Check-in date: %s\nCheck-out date: %s\n",r.getDateIn().toString(),r.getDateOut().toString());
		System.out.printf("Number of adults: %d\tNumber of children: %d\n", r.getNumAdults(),r.getNumChildren());
		System.out.printf("Room Bill: %.2f\n", r.getBill());
	}
	/**
	 * Prints the Guest's Name and Passport/IC number and Contact number of a target reservation.
	 * @param r the reservation the uSser wants to print
	 */
	public static void printPartReservation(Reservation r)
	{
		if(r == null) return;
		Guest guestDetails = r.getGuestDetails();
		System.out.printf("Guest Name: %s\nGuest Passport/ IC number: %s\nGuest Contact: %s\n",guestDetails.getName(),guestDetails.getIdentity(),guestDetails.getContact());
		System.out.println("--------------------------------------------------");
	}

	//Data Access Methods (add,remove,retrieve,...........update and create)
	/**
	 * add an object of {@code Reservation} into the {@code reservationList}
	 * @param r the reservation which user would like to add into the reservation database
	 */
	public static void addReservation(Reservation r)
	{
		reservationList.add(r);
	}
	/**
	 * Removal of reservation at the same time update the room status of this Reservation's room 
	 * if the this reservation has checked in or confirmed. 
	 * <p>
	 * Calls method {@code toVacant} from {@code RoomController} to update the room status of this Reservation's room
	 * if this Reservation is under {@code CONFIRMED} or {@code CHECKED_IN}.
	 * Then remove the target object of {@code Reservation} from the {@code reservationList}.
	 * @param r the reservation which user would like to remove from the reservation database
	 * <br> stop execution of this function is {@code r} is {@code null}
	 * @see RoomController#toVacant(String)
	 */
	public static void removeReservation(Reservation r)
	{
		if(r == null) return;
		if(r.isCheckedIn() || r.isConfirmed()) RoomController.toVacant(r.getRoom().getRoomNumber());
		reservationList.remove(r);
	}
	/**
	 * Gets the reservation which is stored in {@code reservationList} according to its index
	 * @param index the index of the reservation which the user would like to get from {@code reservationList}
	 * @return the reservation associated to the index in {@code reservationList}
	 */
	public static Reservation getReservation(int index)
	{
		return reservationList.get(index);
	}

	//Updaters
	/**
	 * Changes the check-in date of the given reservation {@code r} by given new check-in date {@code dateIn}.
	 * If the new check-in date is not before Reservation's check-out date, then it will call
	 * method {@code scanDateOut} and {@code updateDateOut} to ask and read input from user in order
	 * to update new check-out date to the reservation {@code r}.
	 * Finally call method {@code autoRoomBill} to update the Room Bill of this reservation {@code r}.
	 * @param r reservation which user would like to update the check-in date
	 * @param dateIn the new check-in date which replaces the check-in date of reservation {@code r}<br>
	 * stop execution of this function if {@code null} date is passed
	 * @see #scanDateOut(DateTimeFormatter)
	 * @see #updateDateOut(Reservation, LocalDate)
	 * @see Reservation#autoRoomBill()
	 */
	public static void updateDateIn(Reservation r, LocalDate dateIn)
	{
		if(dateIn == null) return;
		r.setDateIn(dateIn);
		if(dateIn.isAfter(r.getDateOut()))
		{
			ReservationController.updateDateOut(r, ReservationController.scanDateOut(formatter));
		}
		r.autoRoomBill();
	}
	/**
	 * Changes the check-out date of the given reservation {@code r} by given new check-out date {@code dateOut}.
	 * If the new check-out date is not after Reservation's check-in date, then it will call
	 * method {@code scanDateIn} and {@code updateDateIn} to ask and read input from user in order
	 * to update new check-in date to the reservation {@code r}.
	 * Finally call method {@code autoRoomBill} to update the Room Bill of this reservation {@code r}.
	 * @param r reservation which user would like to update the check-out date
	 * @param dateOut the new check-out date which replaces the old check-out date of reservation {@code r}<br>
	 * stop execution of this function if {@code null} date is passed
	 * @see #scanDateIn(DateTimeFormatter)
	 * @see #updateDateIn(Reservation, LocalDate)
	 * @see Reservation#autoRoomBill()
	 */
	public static void updateDateOut(Reservation r, LocalDate dateOut)
	{
		if(dateOut == null) return;
		r.setDateOut(dateOut);
		if(dateOut.isBefore(r.getDateIn()))
		{
			ReservationController.updateDateIn(r, ReservationController.scanDateIn(formatter));
		}
		r.autoRoomBill();
	}
	/**
	 * Changes the number of children declared in the reservation {@code r}.
	 * @param r reservation which user would like to update
	 * @param num the new number of children of reservation {@code r}, stop execution of this function if {@code num} is -1
	 * otherwise only accept non-negative integers.
	 */
	public static void updateNumOfChildren(Reservation r, int num)
	{
		if(num == -1) return;
		r.setNumChildren(num);
	}
	/**
	 * Changes the number of adults declared in the reservation {@code r}.
	 * @param r reservation which user would like to update
	 * @param num the new number of adults of reservation {@code r}, stop execution of this function if {@code num} is -1
	 * otherwise only accept non-negative integers.
	 */
	public static void updateNumOfAdults(Reservation r,int num)
	{
		if(num == -1) return;
		r.setNumAdults(num);
	}
	/**
	 * Display the selection page, allowing user to choose the attribute of the reservation {@code r} in order to be changed.
	 * <p>
	 * In case of updating guest , this method calls {@code updateGuestDetails}
	 * <p>
	 * In case of updating room, this method 
	 * call method {@code scanRoomType} to ask input for room type from user.
	 * If the input room type has vacant rooms 
	 * call method {@code displayVacantRoomsByType} to display the vacant rooms corresponding to the room type.
	 * Or else if the input roomtype has no vacant rooms
	 * call method {@code displayAllRoomsByType} to display all rooms corresponding to the room type.
	 * Then this method {@code createReservation} will continue ask input for the roomID.
	 * The user is expected to key in roomID that were displayed before input for roomID was asked.
	 * The input will be repeatedly asked until roomID matches the room type declared previously.
	 * The Reservation's room is then updated to the room which is stored in {@code roomList}
	 * by using the method {@code getRoom} in {@code RoomController} according to the roomID.
	 * Finally update the reservation status and the room status according to the changes.
	 * <p>
	 * In cases of updating check-in date, check-out date, number of adults, and number of children,
	 * these methods are used respectively:<br>
	 * {@code updateDateIn} , {@code updateDateOut}, {@code updateNumOfAdults}, {@code updateNumOfChildren}
	 * @param r reservation which the user would want to make edit, 
	 * if {@code r} is a {@code null} stop execution of this function, 
	 * if {@code r} is {@code EXPIRED} a message will be printed then stop execution of this function.
	 * @see GuestController#updateGuestDetails(String, String)
	 * @see RoomController#scanRoomType()
	 * @see RoomController#displayVacantRoomsByType(RoomType)
	 * @see RoomController#displayAllRoomsByType(RoomType)
	 * @see RoomController#scanRoomNumber()
	 * @see RoomController#getRoom(String)
	 * @see #updateDateIn(Reservation, LocalDate)
	 * @see #updateDateOut(Reservation, LocalDate)
	 * @see #updateNumOfAdults(Reservation, int)
	 * @see #updateNumOfChildren(Reservation, int)
	 * @see ResStatus#EXPIRED
	 */
	public static void updateReservation(Reservation r)
	{
		if(r == null) return;
		else if(r.isExpired()) {System.out.println("This reservation has EXPIRED"); return;}
		int choice;
		do
		{
			System.out.printf("Which of the following data would you want to update?\n");
			System.out.println("1:Guest Details");
			System.out.println("2:Room Details");
			System.out.println("3:Check-in date");
			System.out.println("4:Check-out date");
			System.out.println("5:Number of adults");
			System.out.println("6:Number of children");
			System.out.println("7:Quit");
			choice = ReservationController.scanChoice(7);
			switch(choice)
			{
			case 1:
				GuestController.updateGuestDetails(r.getGuestDetails().getName(),r.getGuestDetails().getIdentity());
				break;
			case 2:
				//Room Type
				String roomID;
				Room.RoomType roomType = RoomController.scanRoomType();
				if(roomType == null) break;
				//Printing rooms
		        if(RoomController.numberOfVacantByRoomType(roomType)==0)
		        {
		        	System.out.println("There is no VACANT rooms");
		        	System.out.printf("Printing all %s rooms.....\n",roomType);
		        	RoomController.displayAllRoomsByType(roomType);
		        }
		        else
		        {
		    		RoomController.displayVacantRoomsByType(roomType);
		        }
		        //Room ID
		        Room room = null;
		        do
		        {
		    		do
		    		{
		    			roomID = RoomController.scanRoomNumber();
		    			if(roomID == null) break;
		    			if(! RoomController.checkRoomExist(roomID)) System.out.println("Room not exist");
		    		}while(! RoomController.checkRoomExist(roomID));
		    		if(roomID == null) break;
		    		room = RoomController.getRoom(roomID);
		    		if(room.getRoomType() != roomType) System.out.println("RoomType not match");
		        }while(room.getRoomType() != roomType);
		        if(roomID == null) break;
		        //Update room status
				if(r.isConfirmed() ||r.isCheckedIn()) RoomController.toVacant(r.getRoom().getRoomNumber());
				
				//Set Reservation Room to NEW ROOM and update reservation status
				r.setRoom(room);
				RoomStatus roomStatus = r.getRoom().getRoomStatus();
				if(roomStatus != RoomStatus.VACANT)
				{
					System.out.println("This room is currently unavailable.");
					System.out.println("The reservation will be in the WAITLIST");
					r.setStatus(ResStatus.WAITLIST);
				}
				else
				{
					r.setStatus(ResStatus.CONFIRMED);
					r.getRoom().setRoomStatus(Room.RoomStatus.RESERVED);
				}
				r.autoRoomBill();
				break;
			case 3:
				ReservationController.updateDateIn(r,ReservationController.scanDateIn(formatter));
				break;
			case 4:
				ReservationController.updateDateOut(r,ReservationController.scanDateOut(formatter));
				break;
			case 5:
				ReservationController.updateNumOfAdults(r,ReservationController.scanNumOfAdult());
				break;
			case 6:
				ReservationController.updateNumOfChildren(r,ReservationController.scanNumOfChildren());
				break;
			default:
				System.out.println("Quiting....");
				break;	
				
			}
		}while(choice<7);
	}	
	//Searchers
	/**
	 * Search through the {@code reservationList} and get the reservation
	 * which has the same {@code reservationCode} as the given {@code String} {@code st} 
	 * @param st the string of reservation code used as key to search for the reservation<br>
	 * @return {@code null} if the {@code String} {@code st} is null
	 * or all of the Reservation's reservation code in {@code reservationList}
	 * does not match with {@code String} {@code st}<br>
	 * otherwise return the reservation found.
	 */
	public static Reservation searchReservationByCode(String st)
	{
		if(st == null) return null;
		for(int i=0;i<reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			if(st.equals(r.getReservationCode())) {
				return r;			
			}
		}
		System.out.println("Reservation Code not found");
		return null;
	}
	/**
	 * Ask for and read the input for IC number from the user.
	 * Search through the {@code reservationList} and get the reservation
	 * which has the same Guest's identity of that reservation as the input IC number 
	 * 
	 * @return reservation found in {@code reservationList}, null if not found in {@code reservationList}
	 */
	public static Reservation searchReservationByIC()
	{
		System.out.println("Please enter the IC number/PassPort Number");
		Scanner sc = new Scanner (System.in);
		String st =sc.next();
		for(int i=0;i<reservationList.size();i++)
		{
			Reservation r = (Reservation) reservationList.get(i);
			if(st.equals(r.getGuestDetails().getIdentity()))return r;
		}
		System.out.println("IC not found");
		return null;
	}

}