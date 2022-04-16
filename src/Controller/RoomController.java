package Controller;
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

import Class.Room;
import UI.RoomUI;

/**
 * RoomController contains all the methods required by the entity class Room and the UI class RoomUI
 * @author Loke Kah Hou
 * @version 1.0
 * @since 2022-04-12
 */

public class RoomController {
	/**
	 * The Array List containing all the rooms of the hotel
	 */
	private static ArrayList<Room> roomList;

    /**
     * The room txt file where the data for each room of the hotel is stored in
     */
    private static final String roomDB="src\\database\\Room.txt";
    
    /**
     * Gets the roomList of the hotel
     * @return this Hotel's list of rooms
     */
    public static ArrayList<Room> getRoomList(){return roomList;}
    /**
     * Sets the roomList of the hotel.
     * @param rL An array list containing Room objects.
     */
    public static void setRoomList(ArrayList<Room> rL){roomList = rL ;}

    
    /**
     * Creates a new room with the various attributes
     * @param roomNum This Room's room number
     * @param bedType This Room's available bed type
     * @param roomType This Room's type
     * @param status This Room's status
     * @param wifi This Room's WIFI availability
     * @param view This Room's availability of a view
     * @param smoking This Room's smoking rule
     */
    public static void createRoom(String roomNum, int bedType, int roomType, int status, int wifi, int view, int smoking)
    {

        Room room = new Room();

        room.setRoomNumber(roomNum);

        switch (bedType)
        {
            case 1:
                room.setBedType(Room.BedType.SINGLE);
                break;
            case 2:
                room.setBedType(Room.BedType.DOUBLE);
                break;
            case 3:
                room.setBedType(Room.BedType.MASTER);
                break;
        }

        // Set method for roomType
        switch (roomType)
        {
            case 1:
                room.setRoomType(Room.RoomType.SINGLE);
                break;
            case 2:
                room.setRoomType(Room.RoomType.DOUBLE);
                break;
            case 3:
                room.setRoomType(Room.RoomType.DELUXE);
                break;
            case 4:
                room.setRoomType(Room.RoomType.VIP_SUITE);
                break;
        }

        // Set method for RoomStatus
        switch (status)
        {
            case 1:
                room.setRoomStatus(Room.RoomStatus.VACANT);
                break;
            case 2:
                room.setRoomStatus(Room.RoomStatus.OCCUPIED);
                break;
            case 3:
                room.setRoomStatus(Room.RoomStatus.RESERVED);
                break;
            case 4:
                room.setRoomStatus(Room.RoomStatus.UNDER_MAINTENANCE);
                break;
        }

        // Set method for WIFI availability

        switch (wifi)
        {
            case 1:
                room.setWifiAvailability(true);
                break;
            case 2:
                room.setWifiAvailability(false);
                break;
        }
        // Set method for View availability
        switch (view)
        {
            case 1:
                room.setViewAvailability(true);
                break;
            case 2:
                room.setViewAvailability(false);
                break;
        }


        // Set method for Smoking availability
        switch (smoking)
        {
            case 1:
                room.setSmokingRule(true);
                break;
            case 2:
                room.setSmokingRule(false);
                break;
        }

        //Add room to roomList
        roomList.add(room);

    }
    
    /**
     * Method for a staff to update the Room Status of a particular room <br>
     * Method is accessed using the RoomUI
     * @param index The particular index of the room in the ArrayList Room
     * @param choice The choice that the staff has made
     */
    public static void updateRoomStatus(int index, int choice)
    {
        Room temp = new Room();
        temp = roomList.get(index);
        switch (choice)
        {
            case 1:
                RoomController.toVacant(temp.getRoomNumber());
                roomList.set(index, temp);
                break;
            case 2:
                temp.setRoomStatus(Room.RoomStatus.OCCUPIED);
                roomList.set(index, temp);
                break;
            case 3:
                temp.setRoomStatus(Room.RoomStatus.RESERVED);
                roomList.set(index, temp);
                break;
            case 4:
                temp.setRoomStatus(Room.RoomStatus.UNDER_MAINTENANCE);
                roomList.set(index, temp);
                break;
        }
    }

    /**
     * Method for a staff to update the Bed Type of a particular room <br>
     * Method is accessed using the RoomUI
     * @param index The particular index of the room in the ArrayList Room
     * @param choice The choice that the staff has made
     */
    public static void updateBedType(int index, int choice)
    {
        Room temp = new Room();
        boolean done = false;
        switch (choice)
        {
            case 1:
                temp.setBedType(Room.BedType.SINGLE);
                roomList.set(index, temp);
                break;
            case 2:
                temp.setBedType(Room.BedType.DOUBLE);
                roomList.set(index, temp);
                break;
            case 3:
                temp.setBedType(Room.BedType.MASTER);
                roomList.set(index, temp);
                break;
        }
    }
    
    /**
     * Method for a staff to update the Room Type of a particular room <br>
     * Method is accessed using the RoomUI
     * @param index The particular index of the room in the ArrayList Room
     * @param choice The choice that the staff has made
     */
    public static void updateRoomType(int index, int choice)
    {
        Room temp = new Room();
        temp = roomList.get(index);
        switch (choice)
        {
            case 1:
                temp.setRoomType(Room.RoomType.SINGLE);
                roomList.set(index, temp);
                break;
            case 2:
                temp.setRoomType(Room.RoomType.DOUBLE);
                roomList.set(index, temp);
                break;
            case 3:
                temp.setRoomType(Room.RoomType.DELUXE);
                roomList.set(index, temp);
                break;
            case 4:
                temp.setRoomType(Room.RoomType.VIP_SUITE);
                roomList.set(index, temp);
                break;
        }

    }

    /**
     * Method for a staff to update the Wifi availability of a particular room <br>
     * Method is accessed using the RoomUI
     * @param index The particular index of the room in the ArrayList Room
     * @param choice The choice that the staff has made
     */
    public static void updateWIFI(int index, int choice)
    {
        Room temp = new Room();
        temp = roomList.get(index);
        switch (choice)
        {
            case 1:
                temp.setWifiAvailability(true);
                roomList.set(index, temp);
                break;
            case 2:
                temp.setWifiAvailability(false);
                roomList.set(index, temp);
                break;
        }
    }

    /**
     * Method for a staff to update the View Availability of a particular room <br>
     * Method is accessed using the RoomUI
     * @param index The particular index of the room in the ArrayList Room
     * @param choice The choice that the staff has made
     */
    public static void updateView(int index, int choice)
    {
        Room temp = new Room();
        temp = roomList.get(index);
        switch (choice)
        {
            case 1:
                temp.setViewAvailability(true);
                roomList.set(index, temp);
                break;
            case 2:
                temp.setViewAvailability(false);
                roomList.set(index, temp);
                break;
        }
}

    /**
     * Method for a staff to update the Smoking Rule of a particular room <br>
     * Method is accessed using the RoomUI
     * @param index The particular index of the room in the ArrayList Room
     * @param choice The choice that the staff has made
     */
    public static void updateSmoking(int index, int choice)
    {
        Room temp = new Room();

            temp = roomList.get(index);
            switch (choice)
            {
                case 1:
                    temp.setSmokingRule(true);
                    roomList.set(index, temp);
                    break;
                case 2:
                    temp.setSmokingRule(false);
                    roomList.set(index, temp);
                    break;
            }
    }

    /**
     * This method compares the staff's room number input with the room numbers of the rooms in the array list RoomsList
     * @param roomNumber The staff's room number input
     * @return the index of the room in the array list RoomsList if search is successful, else it would return -1
     */
    public static int searchRoom(String roomNumber)
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

    /**
     * This method prints the room status of the room. <br>
     * If the room is not found, an error message would be produced.
     * @param roomNumber The staff's room number input
     */
    public static void printRoomStatus(String roomNumber)
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

    /**
     * Method calls the staff to either input 1 for the Occupancy Report by Room Type or 2 for the Occupancy Report by Room Status
     */
    public static void printRoomStatusReport()
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
                        System.out.println("Single: " + printRoomOccupancyRate(Room.RoomType.SINGLE));
                        System.out.println("\tRooms:" + printVacantRoomsList(Room.RoomType.SINGLE));
                        System.out.println("Double: " + printRoomOccupancyRate(Room.RoomType.DOUBLE));
                        System.out.println("\tRooms:" + printVacantRoomsList(Room.RoomType.DOUBLE));
                        System.out.println("Deluxe: " + printRoomOccupancyRate(Room.RoomType.DELUXE));
                        System.out.println("\tRooms:" + printVacantRoomsList(Room.RoomType.DELUXE));
                        System.out.println("VIP Suite: " + printRoomOccupancyRate(Room.RoomType.VIP_SUITE));
                        System.out.println("\tRooms:" + printVacantRoomsList(Room.RoomType.VIP_SUITE));
                        printed = true;
                        break;
                    case 2:
                        System.out.println("Room Status Statistic Report by Room Status:");
                        System.out.println("Vacant: " + printRoomReportByType( 0));
                        System.out.println("Occupied: " + printRoomReportByType( 1));
                        System.out.println("Reserved: " + printRoomReportByType( 2));
                        System.out.println("Under Maintenance: " + printRoomReportByType( 3));
                        printed = true;
                        break;
                }
            }
        } while(printed == false);
    }

    /**
     * Calls the relevant method based on the integer i
     * @param i represents the choice within the print statement of the printRoomStatusReport() function
     * @return the string generated by the called method
     */
    public static String printRoomReportByType( int i)
    {
        String s = "";
        if (i==0)
        {
            s = printAllVacant();
        }
        else if (i==1)
        {
            s = printAllOccupied();
        }
        else if (i==2)
        {
            s = printAllReserved();
        }
        else if (i==3)
        {
            s = printAllUnderMaintenance();
        }
        return s;
    }
    
    /**
     * Generates the string of the the number of vacant rooms and the respective room numbers
     * @return the print statement of the number of vacant rooms and the respective room numbers
     */
    public static String printAllVacant()
    {
        Room.RoomStatus m;
        String list = " ";
        ArrayList<String> rooms = new ArrayList<String>();
        for (int i=0; i<roomList.size(); i++)
        {
            m = roomList.get(i).getRoomStatus();
            if (m == Room.RoomStatus.VACANT)
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

    /**
     * Generates the string of the the number of occupied rooms and the respective room numbers
     * @return the print statement of the number of occupied rooms and the respective room numbers
     */
    public static String printAllOccupied()
    {
        Room.RoomStatus m;
        String list = " ";
        ArrayList<String> rooms = new ArrayList<String>();
        for (int i=0; i<roomList.size(); i++)
        {
            m = roomList.get(i).getRoomStatus();
            if (m == Room.RoomStatus.OCCUPIED)
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

    /**
     * Generates the string of the the number of reserved rooms and the respective room numbers
     * @return the print statement of the number of reserved rooms and the respective room numbers
     */
    public static String printAllReserved()
    {
        Room.RoomStatus m;
        String list = " ";
        ArrayList<String> rooms = new ArrayList<String>();
        for (int i=0; i<roomList.size(); i++)
        {
            m = roomList.get(i).getRoomStatus();
            if (m == Room.RoomStatus.RESERVED)
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

    /**
     * Generates the string of the the number of rooms under maintenance and the respective room numbers
     * @return the print statement of the number of rooms under maintenance and the respective room numbers
     */
    public static String printAllUnderMaintenance()
    {
        Room.RoomStatus m;
        String list = " ";
        ArrayList<String> rooms = new ArrayList<String>();
        for (int i=0; i<roomList.size(); i++)
        {
            m = roomList.get(i).getRoomStatus();
            if (m == Room.RoomStatus.UNDER_MAINTENANCE)
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
    
    /**
     * Method to calculate the number of Vacant Rooms in the specific Room Type requested
     * @param roomType The requested Room Type by the user
     * @return the number of vacant rooms that have the room type.
     */
    public static int numberOfVacantByRoomType(Room.RoomType roomType)
    {
    	int count = 0;
    	for(int i =0; i<roomList.size();i++)
    	{
    		Room r = roomList.get(i);
    		if(r.getRoomType() == roomType)
    		{
    			if(r.getRoomStatus()==Room.RoomStatus.VACANT){count++;}
    		}
    	}
    	return count;
    }
    
    /**
     * Method to make a string of the list of rooms of a certain room type that are vacant
     * @param type The requested Room Type by the user
     * @return the string that indicates the rooms of a certain room type that are vacant
     */
    public static String printVacantRoomsList(Room.RoomType type)
    {
        Room.RoomStatus m;
        Room.RoomType n;
        String list = " ";
        ArrayList<String> rooms = new ArrayList<String>();
        if (type == Room.RoomType.SINGLE)
        {
            for (int i=0;i<roomList.size();i++)
            {
                m = roomList.get(i).getRoomStatus();
                n = roomList.get(i).getRoomType();
                if (n == Room.RoomType.SINGLE && m == Room.RoomStatus.VACANT)
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
        else if (type == Room.RoomType.DOUBLE)
        {
            for (int i=0;i<roomList.size();i++)
            {
                m = roomList.get(i).getRoomStatus();
                n = roomList.get(i).getRoomType();
                if (n == Room.RoomType.DOUBLE && m == Room.RoomStatus.VACANT)
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
        else if (type == Room.RoomType.DELUXE)
        {
            for (int i=0;i<roomList.size();i++)
            {
                m = roomList.get(i).getRoomStatus();
                n = roomList.get(i).getRoomType();
                if (n == Room.RoomType.DELUXE && m == Room.RoomStatus.VACANT)
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
        else if (type == Room.RoomType.VIP_SUITE)
        {
            for (int i=0;i<roomList.size();i++)
            {
                m = roomList.get(i).getRoomStatus();
                n = roomList.get(i).getRoomType();
                if (n == Room.RoomType.VIP_SUITE && m == Room.RoomStatus.VACANT)
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

    /**
     * Method to get a string of the number of vacant rooms per the requested Room Type
     * @param type The requested Room Type by the user
     * @return the string that indicates the number of rooms of a requested Room Type that are vacant
     */
    public static String printRoomOccupancyRate(Room.RoomType type)
    {
        Room.RoomStatus m;
        Room.RoomType n;
        int vacant = 0;
        int total = 0;
        if (type == Room.RoomType.SINGLE)
        {
            for (int i=0;i<roomList.size();i++)
            {
                m = roomList.get(i).getRoomStatus();
                n = roomList.get(i).getRoomType();
                if (n == Room.RoomType.SINGLE)
                {
                    total += 1;
                    if (m == Room.RoomStatus.VACANT)
                    {
                        vacant += 1;
                    }
                }
            }
        }
        else if (type == Room.RoomType.DOUBLE)
        {
            for (int i=0;i<roomList.size();i++)
            {
                m = roomList.get(i).getRoomStatus();
                n = roomList.get(i).getRoomType();
                if (n == Room.RoomType.DOUBLE)
                {
                    total += 1;
                    if (m == Room.RoomStatus.VACANT)
                    {
                        vacant += 1;
                    }
                }
            }
        }
        else if (type == Room.RoomType.DELUXE)
        {
            for (int i=0;i<roomList.size();i++)
            {
                m = roomList.get(i).getRoomStatus();
                n = roomList.get(i).getRoomType();
                if (n == Room.RoomType.DELUXE)
                {
                    total += 1;
                    if (m == Room.RoomStatus.VACANT)
                    {
                        vacant += 1;
                    }
                }
            }
        }
        else if (type == Room.RoomType.VIP_SUITE)
        {
            for (int i=0;i<roomList.size();i++)
            {
                m = roomList.get(i).getRoomStatus();
                n = roomList.get(i).getRoomType();
                if (n == Room.RoomType.VIP_SUITE)
                {
                    total += 1;
                    if (m == Room.RoomStatus.VACANT)
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
    /**
     * Method to initialize the room based on the text file into the roomList <br>
     * The details in the text file will be in the following format: <br>
     * Room Number, Bed Type, Room Type, Room Status, Wifi Availability, View Availability, Smoking Rule, Price <br>
     * If there are files that do not exist or are in the wrong format, the error message will be printed.
     */
    public static void initRoom(){
        String[] arrLine;
        String line;
        String sep ="@";
        roomList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(roomDB));
            while((line=reader.readLine())!=null){
                arrLine = line.split(sep);
                Room newRoom = new Room(arrLine[0], Room.BedType.valueOf(arrLine[1]), Room.RoomType.valueOf(arrLine[2]), Room.RoomStatus.valueOf(arrLine[3]),Boolean.parseBoolean(arrLine[4]),Boolean.parseBoolean(arrLine[5]),Boolean.parseBoolean(arrLine[6])); // Add in all the parameters for room
                roomList.add(newRoom);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();
            return;
        }
    }
    
    /**
     * Method to save any changes made to the room by the staff <br>
     * If the room has not been saved successfully, then the error message would be printed
     */
    public static void saveRoom(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(roomDB));
            for(int i=0; i<roomList.size(); i++){
                writer.write(roomList.get(i).getRoomNumber().toString() + '@' + roomList.get(i).getBedType().name() + '@' + roomList.get(i).getRoomType().toString() + '@' + roomList.get(i).getRoomStatus().toString() + '@' +
                        roomList.get(i).getWifiAvailability() + '@' + roomList.get(i).getViewAvailability() + '@' + roomList.get(i).getSmokingRule() + '@' );
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to display the room UI for the staff to add, make changes or print out using methods defined in the room controller
     */
    public static void displayPage() {
        RoomUI.displayRoomUI();
    }
    
    /**
    * This method changes this Room's status to RESERVED if Reservation has been confirmed
    * Else, this Room's status will be VACANT
    * @param roomNumber The room number inputted in by the staff
    *                   Will be in the format of xx-yy
    *                   xx represents the floor number from 02-07
    *                   yy represents the room number on the floor from 01-08
    */
    public static void toVacant(String roomNumber)
    {
    	Room r = roomList.get(searchRoom(roomNumber));
    	r.setRoomStatus(Room.RoomStatus.VACANT);
    	if(ReservationController.autoUpdateReservationDetails(r))
    	{
    		r.setRoomStatus(Room.RoomStatus.RESERVED);
    	}
    }
    
    /**
     * This method takes in the room number inputted by the staff. <br>
     * Calls the searchRoom function to get the index of the room in the room list <br>
     * If no room has been found, an error message would be printed.
     * @param roomNum The room number inputted in by the staff
    *                 Will be in the format of xx-yy
    *                 xx represents the floor number from 02-07
    *                 yy represents the room number on the floor from 01-08
     * @return the Room to the user with its specific details
     */
    public static Room getRoom(String roomNum){
        int index;
        index = searchRoom(roomNum);
        if(index==-1){
            System.out.println("Room is not found.");
            return null;
        }
        else{
            return roomList.get(index);
        }
    }
    
    /**
     * This method displays the vacant rooms of the specific room type requested by the staff <br>
     * If the room type matches and the room status is vacant, the printRoomDetails function will be called and the individual room will be printed
     * @param type The requested Room Type by the user
     */
    public static void displayVacantRoomsByType(Room.RoomType type)
    {
        Room.RoomStatus m;
        Room.RoomType n;
        System.out.println("The following rooms are vacant "+ type + " rooms.");
        for (int i=0;i<roomList.size();i++)
        {
            m = roomList.get(i).getRoomStatus();
            n = roomList.get(i).getRoomType();
            if (n == type && m == Room.RoomStatus.VACANT)
            {
                    roomList.get(i).printRoomDetails();
                    System.out.println("==================================================");
            }
        }
    }
    
    /**
     * This method displays all the rooms of the specific room type requested by the staff <br>
     * If the room type matches, the printRoomDetails function will be called and the individual room will be printed
     * @param type The requested Room Type by the user
     */
    public static void displayAllRoomsByType(Room.RoomType type)
    {
        Room.RoomStatus m;
        Room.RoomType n;
        System.out.println("The following rooms are not vacant "+ type + " rooms.");
        for (int i=0;i<roomList.size();i++)
        {
            //m = roomList.get(i).getRoomStatus();
            n = roomList.get(i).getRoomType();
            if (n == type )
            {
                    roomList.get(i).printRoomDetails();
                    System.out.println("==================================================");
            }
        }
    }
    
    /**
     * This method lets the staff choose a specific room type requested by the Reservation Controller
     * @return the room type to the staff
     */
    public static Room.RoomType scanRoomType()
    {
		System.out.println("Please enter the room type");
        System.out.println("Press 1 for SINGLE");
        System.out.println("Press 2 for DOUBLE");
        System.out.println("Press 3 for DELUXE");
        System.out.println("Press 4 for VIP SUITE");
        System.out.println("Press 5 to quit this function.");
        int choice = ReservationController.scanChoice(5);
        if(choice == 5) return null;
        switch(choice)
        {
        case 1:
        	return Room.RoomType.SINGLE;
		case 2:
        	return Room.RoomType.DOUBLE;
		case 3:
        	return Room.RoomType.DELUXE;
        case 4:
        	return Room.RoomType.VIP_SUITE;
        }
		return null;
    }
    
    /**
     * This method will ask for the room number in the following format <br>
     * The room number inputted in by the staff
     * Will be in the format of xx-yy
     * xx represents the floor number from 02-07
     * yy represents the room number on the floor from 01-08 <br>
     * If the room number is incorrectly formatted or does not match, the error message would be produced
     * @return the room number of the Room
     */
    public static String scanRoomNumber()
    {
    	Pattern roomNumberFormat = Pattern.compile("[0][2-7]-[0][1-8]");
    	String result;
    	Matcher matcher;
    	do
    	{
        	System.out.println("Please enter the roomID (xx-yy) (xx:02->07 ; yy:01->08)");
        	System.out.println("Format : (xx = floor number and yy = room id)");
        	System.out.println("Input -1 to end this function.");
        	Scanner sc = new Scanner(System.in);
            sc.reset();
            result = sc.next();
            if(result.equals("-1")) return null;
            matcher = roomNumberFormat.matcher(result);
    		if(result.length() != 5 || matcher.matches() == false)
    		{
    			System.out.println("You have entered an invalid RoomNumber.");
    		}
    	}while(result.length()!=5 || matcher.matches() == false);
    	return result;
    }
    
    /**
     * Method to check if the room exists
     * @param roomNumber The room number inputted in by the user
     * @return true IF the room exists in the room list. ELSE it return false
     */
    public static boolean checkRoomExist(String roomNumber)
    {
    	for(int i = 0; i<roomList.size();i++)
    	{
    		Room r = roomList.get(i);
    		if(roomNumber.equals(r.getRoomNumber())) return true;
    	}
    	return false;
    }
}