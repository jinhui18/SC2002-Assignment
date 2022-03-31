package Assignment_SC2002;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import Assignment_SC2002.Room.*;
//import Database.RoomDB;

public class RoomController{
	private static ArrayList<Room> roomList;
	Room hotelRoom = new Room();
	//private static final String filename = "Room.txt";
	
	public void createRoom() throws IOException
	{
		String roomNumber = "";
		
		Pattern roomNumberFormat = Pattern.compile("[0][2-7]-[0][1-8]");
		
		Room room = new Room();
		Room check = new Room();
		double price = 0;
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		boolean nope = false;
		int choice = 0;
		
		// Set method for RoomNumber
		do {
			System.out.println("Please enter the Room Number.");
			System.out.println("Enter in the format xx-yy (xx = floor number and yy = room id)");
			roomNumber = sc.next();
			Matcher matcher = roomNumberFormat.matcher(roomNumber);
			if (roomNumber.length() != 5 || matcher.matches() == false)
			{
				roomNumber = "";
				System.out.println("You have entered an invalid Room Number.");
			}
			else if (searchRoom(roomNumber) != -1)
			{
				roomNumber = "";
				System.out.println("The room number you have entered already exists.");
			}
			else
			{
				for (int i = 0; i < roomList.size(); i++)
				{
					check = roomList.get(i);
					
					if (roomNumber == check.getRoomNumber())
					{
						roomNumber = "";
						System.out.println("The Room Number you have entered already exists.");
						nope = true;
					}
				}
				if (nope == false)
				{
					room.setRoomNumber(roomNumber);
				}
			}
		} while (roomNumber == "");
		
		
		// Set method for BedType
		do {
			System.out.println("Enter the Type of Bed available in the room");
			System.out.println("Press 1 for SINGLE");
			System.out.println("Press 2 for DOUBLE");
			System.out.println("Press 3 for MASTER");
			
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException a)
			{
				System.out.println("You have entered an invalid input. Please try again.");
				sc.nextLine();
				continue;
			}
			if (choice <= 0 || choice >= 4)
			{
				System.out.println("You have entered an invalid bed type. Please try again.");
			}
			else
			{
				switch (choice)
				{
				case 1:
					room.setBedType(BedType.SINGLE);
					break;
				case 2:
					room.setBedType(BedType.DOUBLE);
					break;
				case 3:
					room.setBedType(BedType.MASTER);
					break;
				}
			}
		} while (choice <= 0 || choice >= 4);
		
		// Set method for roomType
		do {
			System.out.println("Enter the Type of Room");
			System.out.println("Press 1 for SINGLE");
			System.out.println("Press 2 for DOUBLE");
			System.out.println("Press 3 for DELUXE");
			System.out.println("Press 4 for VIP SUITE");
			
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException a)
			{
				System.out.println("You have entered an invalid input. Please try again.");
				sc.nextLine();
				continue;
			}
			if (choice <= 0 || choice >= 5)
			{
				System.out.println("You have entered an invalid room type. Please try again.");
			}
			else
			{
				switch (choice)
				{
				case 1:
					room.setRoomType(RoomType.SINGLE);
					break;
				case 2:
					room.setRoomType(RoomType.DOUBLE);
					break;
				case 3:
					room.setRoomType(RoomType.DELUXE);
					break;
				case 4:
					room.setRoomType(RoomType.VIP_SUITE);
					break;
				}
			}
		} while (choice <= 0 || choice >= 5);
		
		// Set method for RoomStatus
		do {
			System.out.println("Enter the Initial Status of Room");
			System.out.println("Press 1 for VACANT");
			System.out.println("Press 2 for OCCUPIED");
			System.out.println("Press 3 for RESERVED");
			System.out.println("Press 4 for UNDER MAINTENANCE");
			
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException a)
			{
				System.out.println("You have entered an invalid input. Please try again.");
				sc.nextLine();
				continue;
			}
			if (choice <= 0 || choice >= 5)
			{
				System.out.println("You have entered an invalid status. Please try again.");
			}
			else
			{
				switch (choice)
				{
				case 1:
					room.setRoomStatus(RoomStatus.VACANT);
					break;
				case 2:
					room.setRoomStatus(RoomStatus.OCCUPIED);
					break;
				case 3:
					room.setRoomStatus(RoomStatus.RESERVED);
					break;
				case 4:
					room.setRoomStatus(RoomStatus.UNDER_MAINTENANCE);
					break;
				}
			}
		} while (choice <= 0 || choice >= 5);
		
		// Set method for WIFI availability
		do{
			System.out.println("Please enter the following");
			System.out.println("Press 1 to indicate Wifi is available in the room.");
			System.out.println("Press 2 to indicate Wifi is not available in the room.");
			
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException a)
			{
				System.out.println("You have entered an invalid input. Please try again.");
				sc.nextLine();
				continue;
			}
			if (choice <= 0 || choice >= 3)
			{
				System.out.println("You have entered an invalid number. Please try again.");
			}
			else
			{
				switch (choice)
				{
				case 1:
					room.setWifiAvailability(nope);
					done = true;
					break;
				case 2:
					room.setWifiAvailability(false);
					done = true;
					break;
				}
			}
		} while (done == false);
		
		done = false;
		// Set method for View availability
		do{
			System.out.println("Please enter the following");
			System.out.println("Press 1 to indicate view is available in the room");
			System.out.println("Press 2 to indicate no view is available in the room");
			
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException a)
			{
				System.out.println("You have entered an invalid input. Please try again.");
				sc.nextLine();
				continue;
			}
			if (choice <= 0 || choice >= 3)
			{
				System.out.println("You have entered an invalid number. Please try again.");
			}
			else
			{
				switch (choice)
				{
				case 1:
					room.setViewAvailability(true);
					done = true;
					break;
				case 2:
					room.setViewAvailability(false);
					done = true;
					break;
				}
			}
		} while (done = false);
		
		done = false;
		// Set method for Smoking availability
		do{
			System.out.println("Please enter the following");
			System.out.println("Press 1 to indicate smoking is allowed in the room.");
			System.out.println("Press 2 to indicate smoking is NOT allowed in the room.");
			
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException a)
			{
				System.out.println("You have entered an invalid input. Please try again.");
				sc.nextLine();
				continue;
			}
			if (choice <= 0 || choice >= 3)
			{
				System.out.println("You have entered an invalid number. Please try again.");
			}
			else
			{
				switch (choice)
				{
				case 1:
					room.setSmokingRule(true);
					done = true;
					break;
				case 2:
					room.setSmokingRule(false);
					done = true;
					break;
				}
			}
		} while (done = false);
		
		// Set price of room
		do
		{
			System.out.println("Please enter the price of the room");
			try {
				price = sc.nextDouble();
			} catch (InputMismatchException a)
			{
				System.out.println("You have entered an invalid input. Please try again.");
				sc.nextLine();
				continue;
			}
			if (price <= 0)
			{
				System.out.println("You have entered an invalid price. Please try again.");
			}
			else
			{
				room.setPrice(price);
			}
		} while (price == 0);
		
		roomList.add(room);
		/*RoomDB data = new RoomDB(); // This will be imported once we configure the DB classes
		// For now, I'll just use roomList as the array list. Need to check if we should import from the txt file.
		// ArrayList al = RoomDB.read(filename) ;
		roomList.add(room);
		try
		{
			RoomDB.save(filename, roomList);
			System.out.println("Room has been created and saved into the internal database.");
		}
		catch (IOException e)
		{
			System.out.println("IOException > " + e.getMessage());
		}*/
		saveRoom();
	}
	
	public void updateRoomStatus(String roomNumber)
	{
		Scanner scanner = new Scanner(System.in);
		Room temp = new Room();
		boolean done = false;
		int choice = 0;
		
		int i = searchRoom(roomNumber);
		
		if (i != -1)
		{
			System.out.println("To update Room Status for room Number " + roomNumber);
			System.out.println("Please enter the new Room Status");
			System.out.println("Press 1 for VACANT");
			System.out.println("Press 2 for OCCUPIED");
			System.out.println("Press 3 for RESERVED");
			System.out.println("Press 4 for UNDER MAINTENANCE");
			
			while (!done)
			{
				try {
					choice = scanner.nextInt();
					done = true;
				} catch (InputMismatchException a)
				{
					System.out.println("You have entered an invalid input. Please try again.");
					scanner.nextLine();
				}
			}
			temp = roomList.get(i);
			switch (choice)
			{
			case 1:
				temp.setRoomStatus(RoomStatus.VACANT);
				roomList.set(i, temp);
				break;
			case 2:
				temp.setRoomStatus(RoomStatus.OCCUPIED);
				roomList.set(i, temp);
				break;
			case 3:
				temp.setRoomStatus(RoomStatus.RESERVED);
				roomList.set(i, temp);
				break;
			case 4:
				temp.setRoomStatus(RoomStatus.UNDER_MAINTENANCE);
				roomList.set(i, temp);
				break;
			}
		}
		else
		{
			System.out.println("The room number you tried to access is invalid.");
		}
		scanner.close();
	}
	
	public void updateBedType(String roomNumber)
	{
		Scanner scanner = new Scanner(System.in);
		Room temp = new Room();
		boolean done = false;
		int choice = 0;
		
		int i = searchRoom(roomNumber);
		
		if (i != -1)
		{
			System.out.println("To update Bed Type for room Number " + roomNumber);
			System.out.println("Please enter the new Bed Type");
			System.out.println("Press 1 for SINGLE");
			System.out.println("Press 2 for DOUBLE");
			System.out.println("Press 3 for MASTER");
			
			while (!done)
			{
				try {
					choice = scanner.nextInt();
					done = true;
				} catch (InputMismatchException a)
				{
					System.out.println("You have entered an invalid input. Please try again.");
					scanner.nextLine();
				}
			}
			temp = roomList.get(i);
			switch (choice)
			{
			case 1:
				temp.setBedType(BedType.SINGLE);
				roomList.set(i, temp);
				break;
			case 2:
				temp.setBedType(BedType.DOUBLE);
				roomList.set(i, temp);
				break;
			case 3:
				temp.setBedType(BedType.MASTER);
				roomList.set(i, temp);
				break;
			}
		}
		else
		{
			System.out.println("The room number you tried to access is invalid.");
		}
		scanner.close();
	}
	
	public void updateRoomType(String roomNumber)
	{
		Scanner scanner = new Scanner(System.in);
		Room temp = new Room();
		boolean done = false;
		int choice = 0;
		
		int i = searchRoom(roomNumber);
		
		if (i != -1)
		{
			System.out.println("To update Room Type for room Number " + roomNumber);
			System.out.println("Please enter the new Room Type");
			System.out.println("Press 1 for SINGLE");
			System.out.println("Press 2 for DOUBLE");
			System.out.println("Press 3 for DELUXE");
			System.out.println("Press 4 for VIP SUITE");
			
			while (!done)
			{
				try {
					choice = scanner.nextInt();
					done = true;
				} catch (InputMismatchException a)
				{
					System.out.println("You have entered an invalid input. Please try again.");
					scanner.nextLine();
				}
			}
			temp = roomList.get(i);
			switch (choice)
			{
			case 1:
				temp.setRoomType(RoomType.SINGLE);
				roomList.set(i, temp);
				break;
			case 2:
				temp.setRoomType(RoomType.DOUBLE);
				roomList.set(i, temp);
				break;
			case 3:
				temp.setRoomType(RoomType.DELUXE);
				roomList.set(i, temp);
				break;
			case 4:
				temp.setRoomType(RoomType.VIP_SUITE);
				roomList.set(i, temp);
				break;
			}
		}
		else
		{
			System.out.println("The room number you tried to access is invalid.");
		}
		scanner.close();
	}
	
	public void updateWIFI(String roomNumber)
	{
		Scanner scanner = new Scanner(System.in);
		Room temp = new Room();
		boolean done = false;
		int choice = 0;
		
		int i = searchRoom(roomNumber);
		
		if (i != -1)
		{
			System.out.println("To update the availability of Wifi for room number " + roomNumber);
			System.out.println("Please enter the following");
			System.out.println("Press 1 to indicate Wifi is available");
			System.out.println("Press 2 to indicate Wifi is not available");
			
			while (!done)
			{
				try {
					choice = scanner.nextInt();
					done = true;
				} catch (InputMismatchException a)
				{
					System.out.println("You have entered an invalid input. Please try again.");
					scanner.nextLine();
				}
			}
			temp = roomList.get(i);
			switch (choice)
			{
			case 1:
				temp.setWifiAvailability(true);
				roomList.set(i, temp);
				break;
			case 2:
				temp.setWifiAvailability(false);
				roomList.set(i, temp);
				break;
			}
		}
		else
		{
			System.out.println("The room number you tried to access is invalid.");
		}
		scanner.close();
	}
	
	public void updateView(String roomNumber)
	{
		Scanner scanner = new Scanner(System.in);
		Room temp = new Room();
		boolean done = false;
		int choice = 0;
		
		int i = searchRoom(roomNumber);
		
		if (i != -1)
		{
			System.out.println("To update the availability of a view for room number " + roomNumber);
			System.out.println("Please enter the following");
			System.out.println("Press 1 to indicate room has view");
			System.out.println("Press 2 to indicate room has no view");
			
			while (!done)
			{
				try {
					choice = scanner.nextInt();
					done = true;
				} catch (InputMismatchException a)
				{
					System.out.println("You have entered an invalid input. Please try again.");
					scanner.nextLine();
				}
			}
			temp = roomList.get(i);
			switch (choice)
			{
			case 1:
				temp.setViewAvailability(true);
				roomList.set(i, temp);
				break;
			case 2:
				temp.setViewAvailability(false);
				roomList.set(i, temp);
				break;
			}
		}
		else
		{
			System.out.println("The room number you tried to access is invalid.");
		}
		scanner.close();
	}
	
	public void updateSmoking(String roomNumber)
	{
		Scanner scanner = new Scanner(System.in);
		Room temp = new Room();
		boolean done = false;
		int choice = 0;
		
		int i = searchRoom(roomNumber);
		
		if (i != -1)
		{
			System.out.println("To update if room number " + roomNumber + " is smoke-free");
			System.out.println("Please enter the following");
			System.out.println("Press 1 to indicate smoking room");
			System.out.println("Press 2 to indicate non-smoking room");
			
			while (!done)
			{
				try {
					choice = scanner.nextInt();
					done = true;
				} catch (InputMismatchException a)
				{
					System.out.println("You have entered an invalid input. Please try again.");
					scanner.nextLine();
				}
			}
			temp = roomList.get(i);
			switch (choice)
			{
			case 1:
				temp.setSmokingRule(true);
				roomList.set(i, temp);
				break;
			case 2:
				temp.setSmokingRule(false);
				roomList.set(i, temp);
				break;
			}
		}
		else
		{
			System.out.println("The room number you tried to access is invalid.");
		}
		scanner.close();
	}
	
	private int searchRoom(String roomNumber)
	{
		for (int i=0;i<roomList.size();i++)
		{
			if (roomList.get(i).getRoomNumber().equalsIgnoreCase(roomNumber))
			{
				return i;
			}
		}
		return -1;
	}
	
	public void printRoomStatus(String roomNumber)
	{
		int i = searchRoom(roomNumber);
		
		if (i != -1)
		{
			System.out.println("Room Status for room number " + roomNumber + " is " + roomList.get(i).getRoomStatus());
		}
		else
		{
			System.out.println("The room number you tried to access is invalid.");
		}
	}
	
	public void printRoomStatusReport(RoomController room)
	{
		Scanner sc = new Scanner(System.in);
		boolean printed = false;
		boolean done = false;
		int choice = 0;
		
		System.out.println("Please enter the following:");
		System.out.println("Enter 1 to print Occupancy Report by Room Type.");
		System.out.println("Enter 2 to print Occupancy Report by Room Status");
		while (!done)
		{
			try {
				choice = sc.nextInt();
				done = true;
			} catch (InputMismatchException a)
			{
				System.out.println("You have entered an invalid input. Please try again.");
				sc.nextLine();
			}
		}
		do {
			if (choice != 1 && choice != 2)
			{
				System.out.println("You have entered an invalid choice. Please try again.");
			}
			else
			{
				switch (choice)
				{
				case 1:
					System.out.println("Room Status Statistic Report by Room Type:");
					System.out.println("Single: " + room.printRoomOccupancyRate(RoomType.SINGLE));
					System.out.println("\tRooms:" + room.printVacantRoomsList(RoomType.SINGLE));
					System.out.println("Double: " + room.printRoomOccupancyRate(RoomType.DOUBLE));
					System.out.println("\tRooms:" + room.printVacantRoomsList(RoomType.DOUBLE));
					System.out.println("Deluxe: " + room.printRoomOccupancyRate(RoomType.DELUXE));
					System.out.println("\tRooms:" + room.printVacantRoomsList(RoomType.DELUXE));
					System.out.println("VIP Suite: " + room.printRoomOccupancyRate(RoomType.VIP_SUITE));
					System.out.println("\tRooms:" + room.printVacantRoomsList(RoomType.VIP_SUITE));
					printed = true;
					break;
				case 2:
					System.out.println("Room Status Statistic Report by Room Status:");
					System.out.println("Vacant: " + room.printRoomReportbyType(room, 0));
					System.out.println("Occupied: " + room.printRoomReportbyType(room, 1));
					System.out.println("Reserved: " + room.printRoomReportbyType(room, 2));
					System.out.println("Under Maintenance: " + room.printRoomReportbyType(room, 3));
					printed = true;
					break;
				}
			}
		} while(printed == false);
	}

	// Consolidate all four of these functions into one main function
	public String printRoomReportbyType(RoomController room, int i)
	{
		String s = "";
		if (i==0)
		{
			s = room.printAllVacant();
		}
		else if (i==1)
		{
			s = room.printAllOccupied();
		}
		else if (i==2)
		{
			s = room.printAllReserved();
		}
		else if (i==3)
		{
			s = room.printAllUnderMaintenance();
		}
		return s;
	}
	
	public String printAllVacant()
	{
		RoomStatus m;
		String list = " ";
		ArrayList<String> rooms = new ArrayList<String>();
		for (int i=0; i<roomList.size(); i++)
		{
			m = roomList.get(i).getRoomStatus();
			if (m == RoomStatus.VACANT)
			{
				rooms.add(roomList.get(i).getRoomNumber());
			}
		}
		for (int i=0 ; i<rooms.size(); i++)
		{
			list += rooms.get(i);
			if (i != rooms.size()-1)
				list += ", ";
		}
		return list;
	}
	
	public String printAllOccupied()
	{
		RoomStatus m;
		String list = " ";
		ArrayList<String> rooms = new ArrayList<String>();
		for (int i=0; i<roomList.size(); i++)
		{
			m = roomList.get(i).getRoomStatus();
			if (m == RoomStatus.OCCUPIED)
			{
				rooms.add(roomList.get(i).getRoomNumber());
			}
		}
		for (int i=0 ; i<rooms.size(); i++)
		{
			list += rooms.get(i);
			if (i != rooms.size()-1)
				list += ", ";
		}
		return list;
	}
	
	public String printAllReserved()
	{
		RoomStatus m;
		String list = " ";
		ArrayList<String> rooms = new ArrayList<String>();
		for (int i=0; i<roomList.size(); i++)
		{
			m = roomList.get(i).getRoomStatus();
			if (m == RoomStatus.RESERVED)
			{
				rooms.add(roomList.get(i).getRoomNumber());
			}
		}
		for (int i=0 ; i<rooms.size(); i++)
		{
			list += rooms.get(i);
			if (i != rooms.size()-1)
				list += ", ";
		}
		return list;
	}
	
	public String printAllUnderMaintenance()
	{
		RoomStatus m;
		String list = " ";
		ArrayList<String> rooms = new ArrayList<String>();
		for (int i=0; i<roomList.size(); i++)
		{
			m = roomList.get(i).getRoomStatus();
			if (m == RoomStatus.UNDER_MAINTENANCE)
			{
				rooms.add(roomList.get(i).getRoomNumber());
			}
		}
		for (int i=0 ; i<rooms.size(); i++)
		{
			list += rooms.get(i);
			if (i != rooms.size()-1)
				list += ", ";
		}
		return list;
	}
	
	public String printVacantRoomsList(RoomType type)
	{
		RoomStatus m;
		RoomType n;
		String list = " ";
		ArrayList<String> rooms = new ArrayList<String>();
		if (type == RoomType.SINGLE)
		{
			for (int i=0;i<roomList.size();i++)
			{
				m = roomList.get(i).getRoomStatus();
				n = roomList.get(i).getRoomType();
				if (n == RoomType.SINGLE && m == RoomStatus.VACANT)
				{
					rooms.add(roomList.get(i).getRoomNumber());
				}
			}
			for (int i=0 ; i<rooms.size(); i++)
			{
				list += rooms.get(i);
				if (i != rooms.size()-1)
					list += ", ";
			}
		}
		else if (type == RoomType.DOUBLE)
		{
			for (int i=0;i<roomList.size();i++)
			{
				m = roomList.get(i).getRoomStatus();
				n = roomList.get(i).getRoomType();
				if (n == RoomType.DOUBLE && m == RoomStatus.VACANT)
				{
					rooms.add(roomList.get(i).getRoomNumber());
				}
			}
			for (int i=0 ; i<rooms.size(); i++)
			{
				list += rooms.get(i);
				if (i != rooms.size()-1)
					list += ", ";
			}
		}
		else if (type == RoomType.DELUXE)
		{
			for (int i=0;i<roomList.size();i++)
			{
				m = roomList.get(i).getRoomStatus();
				n = roomList.get(i).getRoomType();
				if (n == RoomType.DELUXE && m == RoomStatus.VACANT)
				{
					rooms.add(roomList.get(i).getRoomNumber());
				}
			}
			for (int i=0 ; i<rooms.size(); i++)
			{
				list += rooms.get(i);
				if (i != rooms.size()-1)
					list += ", ";
			}
		}
		else if (type == RoomType.VIP_SUITE)
		{
			for (int i=0;i<roomList.size();i++)
			{
				m = roomList.get(i).getRoomStatus();
				n = roomList.get(i).getRoomType();
				if (n == RoomType.VIP_SUITE && m == RoomStatus.VACANT)
				{
					rooms.add(roomList.get(i).getRoomNumber());
				}
			}
			for (int i=0 ; i<rooms.size(); i++)
			{
				list += rooms.get(i);
				if (i != rooms.size()-1)
					list += ", ";
			}
		}
		return list;
	}
	
	public String printRoomOccupancyRate(RoomType type)
	{
		RoomStatus m;
		RoomType n;
		int vacant = 0;
		int total = 0;
		if (type == RoomType.SINGLE)
		{
			for (int i=0;i<roomList.size();i++)
			{
				m = roomList.get(i).getRoomStatus();
				n = roomList.get(i).getRoomType();
				if (n == RoomType.SINGLE)
				{
					total += 1;
					if (m == RoomStatus.VACANT)
					{
						vacant += 1;
					}
				}
			}
		}
		else if (type == RoomType.DOUBLE)
		{
			for (int i=0;i<roomList.size();i++)
			{
				m = roomList.get(i).getRoomStatus();
				n = roomList.get(i).getRoomType();
				if (n == RoomType.DOUBLE)
				{
					total += 1;
					if (m == RoomStatus.VACANT)
					{
						vacant += 1;
					}
				}
			}
		}
		else if (type == RoomType.DELUXE)
		{
			for (int i=0;i<roomList.size();i++)
			{
				m = roomList.get(i).getRoomStatus();
				n = roomList.get(i).getRoomType();
				if (n == RoomType.DELUXE)
				{
					total += 1;
					if (m == RoomStatus.VACANT)
					{
						vacant += 1;
					}
				}
			}
		}
		else if (type == RoomType.VIP_SUITE)
		{
			for (int i=0;i<roomList.size();i++)
			{
				m = roomList.get(i).getRoomStatus();
				n = roomList.get(i).getRoomType();
				if (n == RoomType.VIP_SUITE)
				{
					total += 1;
					if (m == RoomStatus.VACANT)
					{
						vacant += 1;
					}
				}
			}
		}
		String string = "Number: " + vacant + " out of " + total;
		return string;
	}
	
	// Init Function to trim and token the text file details
	// Save Function to overwrite the text file with new info
	public void initRoom(){
        String[] arrLine;
        String line;
        String sep ="@";
        roomList = new ArrayList<>();
        //System.out.println("Initializing");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Room.txt"));
            //System.out.println("file read");
            while((line=reader.readLine())!=null){
                arrLine = line.split(sep);
                //System.out.printf("arrLine[0] is %s\n", arrLine[0]);
                Room newRoom = new Room(arrLine[0],BedType.valueOf(arrLine[1]),RoomType.valueOf(arrLine[2]),RoomStatus.valueOf(arrLine[3]),Boolean.parseBoolean(arrLine[4]),Boolean.parseBoolean(arrLine[5]),Boolean.parseBoolean(arrLine[6]),Double.parseDouble(arrLine[7])); // Add in all the parameters for room
                // Format is Room Number, Bed Type, Room Type, Room Status, Wifi Availability, View Availability, Smoking Rule, Price
                roomList.add(newRoom);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();
            return;
        }
        //System.out.println("Orders initialized.");
    }
    public void saveRoom(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Room.txt"));
            for(int i=0; i<roomList.size(); i++){
                writer.write(roomList.get(i).getRoomNumber().toString() + '@' + roomList.get(i).getBedType().name() + '@' + roomList.get(i).getRoomType().toString() + '@' + roomList.get(i).getRoomStatus().toString() + '@' +
                		roomList.get(i).getWifiAvailability() + '@' + roomList.get(i).getViewAvailability() + '@' + roomList.get(i).getSmokingRule() + '@' + roomList.get(i).getPrice() + '@');
                writer.write('\n');
            }
            //writer.write("hello\n");
            writer.close();
            System.out.println("RoomDB saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayPage()
    {
    	int choice = 0;
    	Scanner sc = new Scanner(System.in);
    	RoomController room = new RoomController();
    	boolean done = false;
    	String search = "";
    	int found = -1;
    	
    	do {
    		done = false;
			System.out.println("|======== Room App Menu ========|\r\n"
							+ "|(1) Create Room                |\r\n"
							+ "|(2) Check Room Specifications  |\r\n"
							+ "|(3) Update Room Specifications |\r\n"
							+ "|(4) Search for a Room          |\r\n"
							+ "|(5) Print Report               |\r\n"
							+ "|(6) Exit Room App              |\r\n");
			while (!done)
			{
				try {
					choice = sc.nextInt();
					if (choice <= 0 || choice >= 7)
					{
						System.out.println("You have entered an invalid number. Please try again.");
						sc.nextLine();
					}
					else
					{
						done = true;
					}
				} catch (InputMismatchException a)
				{
					System.out.println("You have entered an invalid input. Please try again.");
					sc.nextLine();
				}
			}
			done = false;
			switch (choice)
			{
			case 1:
				do {
					try {
						room.createRoom();
						done = true;
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("You have inputted an error while creating Room. Please try again.");
					}
				} while (done == false);
				sc.nextLine();
				System.out.println("\n");
				break;
			case 2:
				System.out.println("|=============== Room Specs Menu ===============|\r\n"
								+ "|(1) Print for one Room                 |\r\n"
								+ "|(2) Print for all Rooms                |\r\n");
				while (!done)
				{
					try {
						choice = sc.nextInt();
						if (choice <= 0 || choice >= 3)
						{
							System.out.println("You have entered an invalid number. Please try again.");
							sc.nextLine();
						}
						else
						{
							done = true;
						}
					} catch (InputMismatchException a)
					{
						System.out.println("You have entered an invalid input.");
						sc.nextLine();
					}
				}
				
				if (choice == 1)
				{
					do {
						Pattern roomNumberFormat = Pattern.compile("[0][2-7]-[0][1-8]");
						System.out.println("Please input in the roomNumber you would like to check:");
						System.out.println("Enter in the format xx-yy (xx = floor number and yy = room id)");
						sc.reset();
						search = sc.next();
						Matcher matcher = roomNumberFormat.matcher(search);
						if (search.length() != 5 || matcher.matches() == false)
						{
							search = "";
							System.out.println("You have entered an invalid Room Number.");
						}
						else
						{
							found = room.searchRoom(search);
							if (found != -1)
							{
								System.out.println("The roomNumber you have entered has been found.");
								System.out.println("Details of Room Number " + search + ":");
								System.out.printf("Room Status: %s\n", roomList.get(found).getRoomStatus());
								System.out.printf("Bed Type: %s\n", roomList.get(found).getBedType());
								System.out.printf("Room Type: %s\n", roomList.get(found).getRoomType());
								System.out.printf("Availability of WIFI: %s\n", roomList.get(found).getWifiAvailability());
								System.out.printf("Availability of View: %s\n", roomList.get(found).getViewAvailability());
								System.out.printf("Smoking Rule: %s\n", roomList.get(found).getSmokingRule());
							}
							else
							{
								search = "";
								System.out.println("The roomNumber you have entered is invalid. Please try again.");
							}
						}
					} while (search == "");
				}
				else
				{
					for (int i=0;i<roomList.size();i++)
					{
						System.out.println("Details of Room Number " + roomList.get(i).getRoomNumber() + ":");
						System.out.printf("Room Status: %s\n", roomList.get(i).getRoomStatus());
						System.out.printf("Bed Type: %s\n", roomList.get(i).getBedType());
						System.out.printf("Room Type: %s\n", roomList.get(i).getRoomType());
						System.out.printf("Availability of WIFI: %s\n", roomList.get(i).getWifiAvailability());
						System.out.printf("Availability of View: %s\n", roomList.get(i).getViewAvailability());
						System.out.printf("Smoking Rule: %s\n\n", roomList.get(i).getSmokingRule());
					}
				}
				sc.nextLine();
				System.out.println("\n");
				break;
			case 3:
				do {
					Pattern roomNumberFormat = Pattern.compile("[0][2-7]-[0][1-8]");
					System.out.println("Please input in the roomNumber you would like to update:");
					System.out.println("Enter in the format xx-yy (xx = floor number and yy = room id)");
					search = sc.next();
					Matcher matcher = roomNumberFormat.matcher(search);
					if (search.length() != 5 || matcher.matches() == false)
					{
						search = "";
						System.out.println("You have entered an invalid Room Number.");
					}
					else
					{
						found = room.searchRoom(search);
						if (found != -1)
						{
							System.out.println("|====== Update Room Menu =====|\r\n"
									+ "|(1) Update Room Status       |\r\n"
									+ "|(2) Update Bed Type          |\r\n"
									+ "|(3) Update Room Type         |\r\n"
									+ "|(4) Update WIFI Availability |\r\n"
									+ "|(5) Update View Availability |\r\n"
									+ "|(6) Update Smoking Rule      |\r\n");
							while (!done)
							{
								try {
									choice = sc.nextInt();
									done = true;
								} catch (InputMismatchException a)
								{
									System.out.println("You have entered an invalid input.");
									sc.nextLine();
								}
							}
							switch (choice)
							{
							case 1:
								room.updateRoomStatus(search);
								break;
							case 2:
								room.updateBedType(search);
								break;
							case 3:
								room.updateRoomType(search);
								break;
							case 4:
								room.updateWIFI(search);
								break;
							case 5:
								room.updateView(search);
								break;
							case 6:
								room.updateSmoking(search);
								break;
							}
							saveRoom();
						}
						else
						{
							System.out.println("You have inputted an error while creating Room. Please try again.");
						}
					}
				} while (search == "");
				sc.nextLine();
				System.out.println("\n");
				break;
			case 4:
				System.out.println("Please input in the roomNumber you would like to update:");
				search = sc.next();
				found = room.searchRoom(search);
				if (found != -1)
				{
					System.out.println("The room Number " + search + " has been found in our database.");
				}
				sc.nextLine();
				System.out.println("\n");
				break;
			case 5:
				// ask which type of report you would like to search for
				System.out.println("Please input in the type of Report needed:");
				// Occupancy Report by Room Type
				// Occupancy Report by Room Status
				room.printRoomStatusReport(room);
				sc.nextLine();
				System.out.println("\n");
				break;
			case 6:
				System.out.println("Thank you for using the Room Application!");
				break;
			}
    	} while (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5);
    	sc.close();
    }
}