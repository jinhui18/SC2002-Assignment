package UI;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Controller.RoomController;

/**
 * Generates the User Interface for staff to create and update rooms
 * @author Loke Kah Hou
 * @version 1.0
 * @since 2022-04-12
 */

public class RoomUI {
	/**
	 * Displays the Room UI for the staff to select the function they would need <br>
	 * Options: <br>
	 * Create Room - Creates a room based on what the staff defines a room to have. Calls the createRoom() function <br>
	 * Check Room Specifications - Prints out the characteristics of a room. Staff can choose to print for a particular room or all rooms in the hotel <br>
	 * Update Room Specifications - Updates a particular characteristic of particular Room the staff wants to update.
	 * A separate menu will be shown for the staff to select the characteristic needed to update <br>
	 * Search for a Room - Searches for a particular Room's existence <br>
	 * Print Report - Prints out the Statistics Reports for the Hotel. The printRoomStatusReport function inside the RoomController function will be called
	 * and staff can select if they wish to have the Occupancy Report by Room Type or by Occupancy Report by Room Status<br>
	 * Exit Room App - Closes the application and saves the details the staff has created or modified. <br>
	 */
    public static void displayRoomUI(){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
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
                            createRoom();
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
                                found = RoomController.searchRoom(search);
                                if (found != -1)
                                {
                                    System.out.println("The roomNumber you have entered has been found.");
                                    System.out.println("Details of Room Number " + search + ":");
                                    System.out.printf("Room Status: %s\n", RoomController.getRoomList().get(found).getRoomStatus());
                                    System.out.printf("Bed Type: %s\n", RoomController.getRoomList().get(found).getBedType());
                                    System.out.printf("Room Type: %s\n", RoomController.getRoomList().get(found).getRoomType());
                                    System.out.printf("Availability of WIFI: %s\n", RoomController.getRoomList().get(found).getWifiAvailability());
                                    System.out.printf("Availability of View: %s\n", RoomController.getRoomList().get(found).getViewAvailability());
                                    System.out.printf("Smoking Rule: %s\n", RoomController.getRoomList().get(found).getSmokingRule());
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
                        for (int i=0;i<RoomController.getRoomList().size();i++)
                        {
                            System.out.println("Details of Room Number " + RoomController.getRoomList().get(i).getRoomNumber() + ":");
                            System.out.printf("Room Status: %s\n", RoomController.getRoomList().get(i).getRoomStatus());
                            System.out.printf("Bed Type: %s\n", RoomController.getRoomList().get(i).getBedType());
                            System.out.printf("Room Type: %s\n", RoomController.getRoomList().get(i).getRoomType());
                            System.out.printf("Availability of WIFI: %s\n", RoomController.getRoomList().get(i).getWifiAvailability());
                            System.out.printf("Availability of View: %s\n", RoomController.getRoomList().get(i).getViewAvailability());
                            System.out.printf("Smoking Rule: %s\n\n", RoomController.getRoomList().get(i).getSmokingRule());
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
                            found = RoomController.searchRoom(search);
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
                                        updateRoomStatusUI(found, search);
                                        break;
                                    case 2:
                                        updateBedTypeUI(search, found);
                                        break;
                                    case 3:
                                        updateRoomTypeUI(search, found);
                                        break;
                                    case 4:
                                        updateWifiUI(search,found);
                                        break;
                                    case 5:
                                        updateViewUI(search, found);
                                        break;
                                    case 6:
                                        updateSmokingUI(search,found);
                                        break;
                                }
                                RoomController.saveRoom();
                            }
                            else
                            {
                                System.out.println("You have input an error while creating Room. Please try again.");
                            }
                        }
                    } while (search == "");
                    sc.nextLine();
                    System.out.println("\n");
                    break;
                case 4:
                    System.out.println("Please input in the roomNumber you would like to search:");
                    search = sc.next();
                    found = RoomController.searchRoom(search);
                    if (found != -1)
                    {
                        System.out.println("The room Number " + search + " has been found in our database.");
                    }
                    else{
                        System.out.println("The room number "+search+ " does not exist.");
                    }
                    sc.nextLine();
                    System.out.println("\n");
                    break;
                case 5:
                    // ask which type of report you would like to search for
                    System.out.println("Please input in the type of Report needed:");
                    // Occupancy Report by Room Type
                    // Occupancy Report by Room Status
                    RoomController.printRoomStatusReport();
                    sc.nextLine();
                    System.out.println("\n");
                    break;
                case 6:
                    System.out.println("Thank you for using the Room Application!");
                    break;
            }
        } while (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5);
    }
    
    /**
     * Method to create a new room
     * @throws IOException if the Integer selection the staff has made is of an invalid type
     */
    public static void createRoom() throws IOException{
        Scanner sc = new Scanner(System.in);
        int bed = 0, roomType = 0, status = 0, wifi = 0, view = 0, smoking = 0;
        String roomNumber = "";
        Pattern roomNumberFormat = Pattern.compile("[0][2-7]-[0][1-8]");

        // Set method for RoomNumber
        do {
            System.out.println("Please enter the Room Number.");
            System.out.println("Enter in the format xx-yy (xx = floor number and yy = room id)");
            roomNumber = sc.next();
            Matcher matcher = roomNumberFormat.matcher(roomNumber);
            if (roomNumber.length() != 5 || matcher.matches() == false) {
                roomNumber = "";
                System.out.println("You have entered an invalid Room Number.");
            } else if (RoomController.searchRoom(roomNumber) != -1) {
                roomNumber = "";
                System.out.println("The room number you have entered already exists.");
            }

        } while (roomNumber == "");


        // Set method for BedType
        do {
            System.out.println("Enter the Type of Bed available in the room");
            System.out.println("Press 1 for SINGLE");
            System.out.println("Press 2 for DOUBLE");
            System.out.println("Press 3 for MASTER");

            try {
                bed = sc.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("You have entered an invalid input. Please try again.");
                sc.nextLine();
                continue;
            }
            if (bed <= 0 || bed >= 4) {
                System.out.println("You have entered an invalid bed type. Please try again.");
            }
        } while (bed <= 0 || bed >= 4);

        // Set method for roomType
        do {
            System.out.println("Enter the Type of Room");
            System.out.println("Press 1 for SINGLE");
            System.out.println("Press 2 for DOUBLE");
            System.out.println("Press 3 for DELUXE");
            System.out.println("Press 4 for VIP SUITE");

            try {
                roomType = sc.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("You have entered an invalid input. Please try again.");
                sc.nextLine();
                continue;
            }
            if (roomType <= 0 || roomType >= 5) {
                System.out.println("You have entered an invalid room type. Please try again.");
            }

        } while (roomType <= 0 || roomType >= 5);

        // Set method for RoomStatus
        do {
            System.out.println("Enter the Initial Status of Room");
            System.out.println("Press 1 for VACANT");
            System.out.println("Press 2 for OCCUPIED");
            System.out.println("Press 3 for RESERVED");
            System.out.println("Press 4 for UNDER MAINTENANCE");

            try {
                status = sc.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("You have entered an invalid input. Please try again.");
                sc.nextLine();
                continue;
            }
            if (status <= 0 || status >= 5) {
                System.out.println("You have entered an invalid status. Please try again.");
            }
        } while (status <= 0 || status >= 5);

        // Set method for WIFI availability
        do {
            System.out.println("Please enter the following");
            System.out.println("Press 1 to indicate Wifi is available in the room.");
            System.out.println("Press 2 to indicate Wifi is not available in the room.");

            try {
                wifi = sc.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("You have entered an invalid input. Please try again.");
                sc.nextLine();
                continue;
            }
            if (wifi <= 0 || wifi >= 3) {
                System.out.println("You have entered an invalid number. Please try again.");
            }
        } while (wifi < 1 && wifi > 2);

        // Set method for View availability
        do {
            System.out.println("Please enter the following");
            System.out.println("Press 1 to indicate view is available in the room");
            System.out.println("Press 2 to indicate no view is available in the room");

            try {
                view = sc.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("You have entered an invalid input. Please try again.");
                sc.nextLine();
                continue;
            }
            if (view <= 0 || view >= 3) {
                System.out.println("You have entered an invalid number. Please try again.");
            }
        } while (view < 1 || view > 2);

        // Set method for Smoking availability
        do {
            System.out.println("Please enter the following");
            System.out.println("Press 1 to indicate smoking is allowed in the room.");
            System.out.println("Press 2 to indicate smoking is NOT allowed in the room.");

            try {
                smoking = sc.nextInt();
            } catch (InputMismatchException a) {
                System.out.println("You have entered an invalid input. Please try again.");
                sc.nextLine();
                continue;
            }
            if (smoking <= 0 || smoking >= 3) {
                System.out.println("You have entered an invalid number. Please try again.");
            }
        } while (smoking < 1 || smoking > 2);
        RoomController.createRoom(roomNumber, bed, roomType, status, wifi, view, smoking);
    }
    
    /**
     * Method to generate the UI for a staff to update the Room Status of a particular room
     * @param index The particular index of the room in the ArrayList Room
     * @param roomNum The room number in string format that the staff inputed
     */
    public static void updateRoomStatusUI(int index, String roomNum){
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int choice=0;
        System.out.println("To update Room Status for room Number " + roomNum);
        System.out.println("Please enter the new Room Status");
        System.out.println("Press 1 for VACANT");
        System.out.println("Press 2 for OCCUPIED");
        System.out.println("Press 3 for RESERVED");
        System.out.println("Press 4 for UNDER MAINTENANCE");

        while (!done)
        {
            try {
                choice = scanner.nextInt();
                if(choice>0 && choice<5) {
                    done = true;
                }
                else{
                    System.out.println("Invalid choice entered.");
                }
            } catch (InputMismatchException a)
            {
                System.out.println("You have entered an invalid input. Please try again.");
                scanner.nextLine();
            }
        }
        RoomController.updateRoomStatus(index, choice);
    }
    
    /**
     * Method to generate the UI for a staff to update the Bed Type of a particular room
     * @param roomNum The room number in string format that the staff inputed
     * @param index The particular index of the room in the ArrayList Room
     */
    public static void updateBedTypeUI(String roomNum, int index){
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int choice = 0;

            System.out.println("To update Bed Type for room Number " + roomNum);
            System.out.println("Please enter the new Bed Type");
            System.out.println("Press 1 for SINGLE");
            System.out.println("Press 2 for DOUBLE");
            System.out.println("Press 3 for MASTER");

            while (!done)
            {
                try {
                    choice = scanner.nextInt();
                    if(choice>0 && choice<4) {
                        done = true;
                    }
                    else{
                        System.out.println("Invalid choice entered.");
                    }
                } catch (InputMismatchException a)
                {
                    System.out.println("You have entered an invalid input. Please try again.");
                    scanner.nextLine();
                }
            }
        RoomController.updateBedType(index, choice);
    }
    
    /**
     * Method to generate the UI for a staff to update the Room Type of a particular room
     * @param roomNum The room number in string format that the staff inputed
     * @param index The particular index of the room in the ArrayList Room
     */
    public static void updateRoomTypeUI(String roomNum, int index){
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int choice = 0;
        System.out.println("To update Room Type for room Number " + roomNum);
        System.out.println("Please enter the new Room Type");
        System.out.println("Press 1 for SINGLE");
        System.out.println("Press 2 for DOUBLE");
        System.out.println("Press 3 for DELUXE");
        System.out.println("Press 4 for VIP SUITE");

        while (!done)
        {
            try {
                choice = scanner.nextInt();
                if(choice>0 && choice<5) {
                    done = true;
                }
                else{
                    System.out.println("Invalid choice entered.");
                }
            } catch (InputMismatchException a)
            {
                System.out.println("You have entered an invalid input. Please try again.");
                scanner.nextLine();
            }
        }
        RoomController.updateRoomType(index, choice);
    }
    
    /**
     * Method to generate the UI for a staff to update the Wifi availability of a particular room
     * @param roomNum The room number in string format that the staff inputed
     * @param index The particular index of the room in the ArrayList Room
     */
    public static void updateWifiUI(String roomNum, int index){
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int choice = 0;
        System.out.println("To update the availability of Wifi for room number " + roomNum);
        System.out.println("Please enter the following");
        System.out.println("Press 1 to indicate Wifi is available");
        System.out.println("Press 2 to indicate Wifi is not available");

        while (!done)
        {
            try {
                choice = scanner.nextInt();
                if(choice>0 && choice<3) {
                    done = true;
                }
                else{
                    System.out.println("Invalid choice entered.");
                }
            } catch (InputMismatchException a)
            {
                System.out.println("You have entered an invalid input. Please try again.");
                scanner.nextLine();
            }
        }
        RoomController.updateWIFI(index, choice);
    }

    /**
     * Method to generate the UI for a staff to update the View availability of a particular room
     * @param roomNum The room number in string format that the staff inputed
     * @param index The particular index of the room in the ArrayList Room
     */
    public static void updateViewUI(String roomNum, int index){
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int choice = 0;

        System.out.println("To update the availability of a view for room number " + roomNum);
        System.out.println("Please enter the following");
        System.out.println("Press 1 to indicate room has view");
        System.out.println("Press 2 to indicate room has no view");

        while (!done)
        {
            try {
                choice = scanner.nextInt();
                if(choice>0 && choice<3) {
                    done = true;
                }
                else{
                    System.out.println("Invalid choice entered.");
                }
            } catch (InputMismatchException a)
            {
                System.out.println("You have entered an invalid input. Please try again.");
                scanner.nextLine();
            }
        }
        RoomController.updateView(index, choice);
    }

    /**
     * Method to generate the UI for a staff to update the Smoking Rule of a particular room
     * @param roomNum The room number in string format that the staff inputed
     * @param index The particular index of the room in the ArrayList Room
     */
    public static void updateSmokingUI(String roomNum, int index){
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int choice = 0;

            System.out.println("To update if room number " + roomNum + " is smoke-free");
            System.out.println("Please enter the following");
            System.out.println("Press 1 to indicate smoking room");
            System.out.println("Press 2 to indicate non-smoking room");

            while (!done)
            {
                try {
                    choice = scanner.nextInt();
                    if(choice>0 && choice<3) {
                        done = true;
                    }
                    else{
                        System.out.println("Invalid choice entered.");
                    }
                } catch (InputMismatchException a)
                {
                    System.out.println("You have entered an invalid input. Please try again.");
                    scanner.nextLine();
                }
            }
            RoomController.updateSmoking(index, choice);
    }
}
