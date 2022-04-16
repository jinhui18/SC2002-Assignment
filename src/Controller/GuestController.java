package Controller;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import UI.GuestUI;
import Class.CreditCardDetails;
import Class.Guest;

/**
 * Guest Controller houses the various methods that the staff can use with regards to the guest details.
 * Guest Controller also connect the Guest User interface with the Guest class and any changes made to guest will go through guest controller.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class GuestController {
	/**
	* The separator is used to separate the values of the attributes stored in our database, this helps us to extract and store the data properly.
	*/
	private static final String SEPARATOR = "|";
	/**
	* Guest List is a array of guest classes. 
	* We store each individual guest into this array.
	*/
	private static ArrayList<Guest> guestList = new ArrayList();
	/**
	* databaseLocationGuest is the location of the guest data base where we will be storing all the attributes of guest.
	*/
	private static String databaseLocationGuest= "src\\database\\Guest.txt";

	/**
	* This method of display guest UI connects the guest controller and the guest UI class.
	* By using this method, guest UI will then display all the options that the staff can choose with regards to guest details.
	*/
	public static void displayGuestUI() {
		GuestUI.displayGuestUI();
	}
	
	/**
	* This method of display guest UI connects the guest controller and the guest UI class.
	* By using this method, guest UI will then display all the options that the staff can choose with regards to guest details.
	* @return guest object that was create with all the values of its attribute.
	*/
	public static Guest createGuestProfile() {
			Scanner sc = new Scanner(System.in);
			String name;
			String identity;
			String address;
			String country;
			int gender;
			String nationality;
			String contact;
			CreditCardDetails creditCardDetails;
			int flag;
			String cardName;
			String cardNum;
			String expiryDate;
			String cvvNum;
			String roomNumber;
			String cardBillingAddress;

			Guest guest = new Guest();
			System.out.println("==================================");
			System.out.println(" Guest Creation ");
			System.out.println("==================================");
			
			// set name
			do{
				System.out.println("Please enter your full name (accordance with passport): ");
				name = sc.nextLine();
				flag =0;
				if (name.length() == 0) {
					System.out.println("Please enter a valid name\n");
					flag =1;
				}
				for (int i =0; i<name.length(); i++) {
					if ( ( (!Character.isLetter(name.charAt(i))  ) && (name.charAt(i) != ' ' ) ) || name == "\n") {
						
						System.out.println("Please enter a valid name\n");
						flag =1;
						break;
					}
				}
				if (flag != 1) {
					guest.setName(name);
				}

			}
			while( flag == 1);
			flag=0;
			
			// set identity or passport number
			do{
				System.out.println("\nPlease enter your passport/ IC number: ");
				identity = sc.nextLine();
				flag =0;
				if (identity == "") {
					System.out.println("Please enter a valid passport/ IC number\n");
					flag=1;
				}
				// error checking for duplicate passport number
				for (int i = 0; i<guestList.size() ; i++) {
					Guest g = guestList.get(i);
					if ( identity.equals(g.getIdentity() ) && !(g.getRoomNumber().equals("NA")) ) {
						System.out.println("Error. Passport/ IC number already exist, please enter a new passport/ IC number\n");
						flag=1;
						break;
					}
					
				}
				if (flag != 1) {
					guest.setIdentity(identity);
				}
			}
			while( flag == 1); 
			
			// This method is to remove any duplicate guest profile in our database
			// In the case where guest decides to come back to our hotel, their details will still be in our entire guest database but will be replaced with a new profile
			if (searchGuestProfile(name, identity)!= -1) {
				removeGuestProfile(name,identity);
			}
			
			
			flag=0;
			// set address
			do{
				System.out.println("\nPlease enter your local residental address: ");
				address = sc.nextLine();
				flag =0;
				if (address == "") {
					System.out.println("Please enter a valid local residental address:\n");
					flag=1;
				}
				if (flag != 1) {
					guest.setAddress(address);
				}
			}
			while( flag == 1); 
			
			flag=0;
			// set country
			do{
				System.out.println("\nPlease enter your Country of residence: ");
				country = sc.nextLine();
				flag =0;
				if (country == "") {
					System.out.println("Please enter a valid Country of residence:\n");
					flag=1;
				}
				for (int i =0; i<country.length(); i++) {
					if (!Character.isLetter(country.charAt(i)) && country.charAt(i) != ' ' ) {
						System.out.println("Please enter a valid Country of residence\n");
						flag =1;
						break;
					}
				}
				if (flag != 1) {
					guest.setCountry(country);
				}
			}
			while( flag == 1); 
			
			flag=0;
			// set gender
			do{
				System.out.println("\nPlease choose your Gender: ");
				System.out.println("(1) Male");
				System.out.println("(2) Female ");
				System.out.println("(3) Others");
				
				
		  
		        try {
		        	gender = sc.nextInt();
		        } 
		        catch (InputMismatchException a)
		        {
		            System.out.println("You have entered an invalid input. Please try again.");
		            sc.nextLine();
		            flag =1;
		            continue;
		        } 
				
				flag =0;
				if (gender != 1 && gender != 2 && gender != 3) {
					System.out.println("Please enter a valid input:\n");
					flag=1;
				}
				else {
					switch(gender) {
						case 1:
							guest.setGender("Male");
							break;
						case 2:
							guest.setGender("Female");
							break;
						case 3:
							guest.setGender("Others");
							break;
					}	
					sc.nextLine();
				}

			}
			while( flag == 1); 
			
			flag=0;
			// set nationality
			do{
				System.out.println("\nPlease enter your Nationality: ");
				nationality = sc.nextLine();
				flag =0;
				if (nationality == "") {
					System.out.println("Please enter a valid Nationality\n");
					flag =1;
				}
				for (int i =0; i<nationality.length(); i++) {
					if (!Character.isLetter(nationality.charAt(i))) {
						System.out.println("Please enter a valid Nationality\n");
						flag =1;
						break;
					}
				}
				if (flag != 1) {
					guest.setNationality(nationality);
				}
			}
			while( flag == 1);
			
			flag =0;
			// set contact
			do{
				System.out.println("\nPlease enter your Phone Number: ");
				contact = sc.nextLine();
				flag =0;
				if (contact == "") {
					System.out.println("Please enter a valid Phone Number:\n");
					flag=1;
				}
				for (int i =0; i<contact.length(); i++) {
					if (!Character.isDigit(contact.charAt(i))) {
						System.out.println("Please enter a valid Phone Number\n");
						flag =1;
						break;
					}
				}
				if (flag != 1) {
					guest.setContact(contact);
				}
			}
			while( flag == 1);

			// set creditCardDetails
			flag = 0;
			System.out.println("\nPlease enter your Credit Card Details: ");
			while(true) {
				System.out.print("\nPlease Enter name on your credit card: ");
				System.out.println("\nPlease enter NA if not applicable");
				cardName = sc.nextLine();
				flag =0;
				if (cardName.equals("NA")) {
					flag =1;
					break;
				}
				for (int i =0; i<cardName.length(); i++) {
					if ( (!Character.isLetter(cardName.charAt(i) ) && (cardName.charAt(i) != ' ' )  )) {
						System.out.println("Please enter a valid name\n");
						flag =1;
						break;
					}
				}
				if (flag ==0) {
					break;
				}
			}
			
			if (flag ==0) {
				while(true) {
					System.out.println("Please enter your Card Number: (16 digits)");
					cardNum = sc.nextLine();
					flag=0;
					if (cardNum == "" || cardNum.length() != 16) {
						System.out.println("Please enter a valid Card Number\n");
						flag =1;
						continue;
					}

					for (int i =0; i<cardNum.length(); i++) {
						if (!Character.isDigit(cardNum.charAt(i))) {
							System.out.println("Please enter a valid Card Number\n");
							flag =1;
							break;
						}
					}
					if (flag == 0) {
						break;
					}
				}
			}
			else {
				cardNum = "NA";
			}

			if (flag == 0) {
				while (true) {
					System.out.println("\nPlease enter your Card Expiry Date (MM/YY): ");
					String strPattern = "^(0[1-9]|1[012])/\\d{2}$";
					expiryDate = sc.nextLine();
					if (expiryDate.matches(strPattern) ) {
						break;
					}	
					System.out.println("Please enter a valid Card Expiry Date (MM/YY)\n");
				}
			}
			else {
				expiryDate = "NA";
			}

			if (flag == 0) {
				while(true) {
					System.out.println("\nPlease enter your CVV number: ");
					cvvNum = sc.nextLine();
					if (cvvNum.length() != 3) {
						System.out.println("Please enter a valid CVV Number\n");
						continue;
					}
					for (int i =0; i<cvvNum.length(); i++) {
						if (!Character.isDigit(cvvNum.charAt(i))) {
							System.out.println("Please enter a valid CVV Number\n");
							flag =1;
							break;
						}
					}
					if (flag == 0) {
						break;
					}
				}
			}
			else {
				cvvNum = "NA";
			}
			if (flag ==0) {
				while(true) {
					System.out.print("\nPlease Enter billing address on your credit card: ");
					cardBillingAddress = sc.nextLine();
					flag =0;
					if (cardBillingAddress == "") {
						System.out.println("Please enter a valid billing address:\n");
						flag=1;
						break;
					}
					if (flag ==0) {
						break;
					}
				}
			}
			else {
				cardBillingAddress = "NA";
			}

			//create CC details and set it in guest
			creditCardDetails = new CreditCardDetails(cardName,cardNum, expiryDate, cvvNum, cardBillingAddress);
			guest.setCreditCardDetails(creditCardDetails);

			// whenever guest is created, the room number will be NA and we will update when guest checks in
            guest.setRoomNumber("NA");		
			guestList.add(guest);
			return guest;
			
	}
	
	// remove guest by name and IC number/ Passport Number
	/**
	* This method removes guest from the array list guestList.
	* It does so by searching the index of the particular guest in the array list guestList by matching the name and IC/Passport number of the guest.
	* If no such guest is found, it will be conveyed to the staff and 0 will be returned.
	* If the guest is found, the guest will be removed from array list guestList.
	* @param name Guest's name.
	* @param icNum Guest's IC/Passport number.
	* @return If guest is found, it returns 1 as removal of guest is successful, if guest is not found, it returns 0 as removal is unsuccessful.
	*/
	public static int removeGuestProfile(String name, String icNum) {
		int index = -1;
		index = searchGuestProfile( name,  icNum);
		if (index == -1) {
			System.out.println("No such customer in our records.");
			return 0;
		}
		guestList.remove(index);
		return 1;
	}
	
	// Remove guest by room number
	/**
	* This method removes guest from the array list guestList.
	* It does so by searching through the entire array list guestList and for each index of guest, the room number will be compared with the parameter roomNum, if it matches the guest is then removed.
	* This method removes all guest staying in the same room when the guest checks out.
	* @param roomNum Guest's room number.
	*/
	public static void removeGuestProfile(String roomNum) {
		for (int i=0; i< guestList.size(); i++) {
			if (guestList.get(i).getRoomNumber().equals(roomNum)) {
				guestList.remove(i);
			}
		}
	}

	// Search guest name and IC number/ Passport Number
	/**
	* This method search guest from the array list guestList.
	* It does so by searching the index of the particular guest in the array list guestList by matching the name and IC/Passport number of the guest.
	* Both the guest name and IC/Passport number must be the same as the parameter.
	* @param name Guest's name.
	* @param icNum Guest's IC/Passport number.
	* @return j Successfully search, it returns the index of the array list guestList that contains the guest with given name and IC/Passport number from the parameter. If not successful, return -1;
	*/
	public static int searchGuestProfile(String name, String icNum) {
		for (int j=0; j< guestList.size(); j++) {
			if ( name.equals(guestList.get(j).getName())  && icNum.equals(guestList.get(j).getIdentity()) ) {
				return j;
			}
		}
		return -1;
	}
	
	//update guest details by name and IC number/ Passport Number
	/**
	* This method updates the guest details (attributes from guest class).
	* It does so by searching the index of the particular guest in the array list guestList by matching the name and IC/Passport number of the guest.
	* Then the staff is given the options to choose which attribute to change and the particular guest detail can be updated.
	* @param name_input Guest's name.
	* @param icNum_input Guest's IC/Passport number.
	* @return  Unsuccessfully update, returns 0,no such customer is found in database, unable to update details. Successful update, returns 1.
	*/
	public static int updateGuestDetails(String name_input, String icNum_input) {
		Scanner sc = new Scanner(System.in);
		String nameNew;
		String identityNew;
		String addressNew;
		String countryNew;
		int genderNew;
		String nationalityNew;
		String contactNew;
		CreditCardDetails creditCardDetailsNew;
		String cardNameNew;
		String cardNumNew;
		String expiryDateNew;
		String cvvNumNew;
		String cardBillingAddressNew;
		int flag =0;
		int index;
		int choice =0;
		
		index = searchGuestProfile( name_input,  icNum_input);
		if (index == -1) {
			System.out.println("No such customer in our records.");
			return 0;
		}
		while (choice !=10) {
			System.out.println("\nPlease choose Guest Detail to update");
			System.out.println("(1) Name");
			System.out.println("(2) Identity");
			System.out.println("(3) Address");
			System.out.println("(4) Country of residence");
			System.out.println("(5) Gender");
			System.out.println("(6) Nationality");
			System.out.println("(7) Contact Number");
			System.out.println("(8) Credit Card");
			System.out.println("(9) Room Number");
			System.out.println("(10) Exit");

			choice = sc.nextInt();
			sc.nextLine();
			flag =0;
			switch(choice) {
			case 1:	while(true) {
					System.out.print("\nPlease Enter New Name (-1 to exit): ");
					nameNew = sc.nextLine();
					
						if (nameNew == "") {
							System.out.println("Please enter a valid name\n");
							continue;
						}
						else if (nameNew.equals("-1")) {
							flag = -1;
							break;
						}
						for (int i =0; i<nameNew.length(); i++) {
							if (!Character.isLetter(nameNew.charAt(i))) {
								System.out.println("Please enter a valid name\n");
								flag =1;
								break;
							}
						}
						if (flag ==0) {
							System.out.println("Name updated successfully\n");
							guestList.get(index).setName(nameNew);
							break;
						}
						else if (flag == -1) {
							System.out.println("Cancel update\n");
						}
						flag=0;
					}
					break;
					
			case 2: 
					do {
						System.out.println("\nPlease enter your new Passport/ IC number (-1 to exit): ");
						identityNew = sc.nextLine();
						
						flag =0;
						if (identityNew == "") {
							System.out.println("Please enter a valid passport/ IC number\n");
							flag=1;
						}
						else if (identityNew.equals("-1")) {
							flag = -1;
							break;
						}
						// error checking for duplicate passport number
						for (int i = 0; i<guestList.size() ; i++) {
							Guest g = guestList.get(i);
							if ( identityNew.equals(g.getIdentity() ) ) {
								System.out.println("Error. Passport number already exist, please enter a new passport/ IC number\n");
								flag=1;
								break;
							}
							
						}
					}
					while(flag ==1);
					if (flag == 0) {
						System.out.println("Passport/ IC number updated successfully\n");
						guestList.get(index).setIdentity(identityNew);
					}
					else if (flag == -1) {
						System.out.println("Cancel update\n");
					}
					break;
			case 3:	
					do {
						System.out.println("\nPlease enter your new Residental Address (-1 to exit): ");
						addressNew = sc.nextLine();
						flag =0;
						if (addressNew == "") {
							System.out.println("Please enter a valid residental address:\n");
							flag=1;
						}
						else if (addressNew.equals("-1")) {
							flag = -1;
							break;
						}
						
					}
					while(flag ==1);
					if (flag == 0) {
						System.out.println("Address updated successfully\n");
						guestList.get(index).setAddress(addressNew);
					}
					else if (flag == -1) {
						System.out.println("Cancel update\n");
					}
					break;
					
			case 4:	
					do {
						System.out.println("\nPlease enter your new Country of residence (-1 to exit): ");
						countryNew = sc.nextLine();
						flag =0;
						if (countryNew == "") {
							System.out.println("Please enter a valid Country of residence:\n");
							flag=1;
						}
						else if (countryNew.equals("-1")) {
							flag = -1;
							break;
						}
						for (int i =0; i<countryNew.length(); i++) {
							if (!Character.isLetter(countryNew.charAt(i))) {
								System.out.println("Please enter a valid Country of residence\n");
								flag =1;
								break;
							}
						}		
					}
					while(flag ==1);	
					if (flag ==0) {
						System.out.println("Country of residence updated successfully\n");
						guestList.get(index).setCountry(countryNew);
					}
					else if (flag == -1) {
						System.out.println("Cancel update\n");
					}
					break;
					
			case 5:	
					do{
						System.out.println("\nPlease choose your new Gender: ");
						System.out.println("(1) Male");
						System.out.println("(2) Female ");
						System.out.println("(3) Others");
						
						
				  
				        try {
				        	genderNew = sc.nextInt();
				        } 
				        catch (InputMismatchException a)
				        {
				            System.out.println("You have entered an invalid input. Please try again.");
				            sc.nextLine();
				            flag =1;
				            continue;
				        } 
				        
						flag =0;
						if (genderNew == -1) {
							flag = -1;
							break;
						}
						if (genderNew != 1 && genderNew != 2 && genderNew != 3) {
							System.out.println("Please enter a valid input:\n");
							flag=1;
						}
						else {
								switch(genderNew) {
								case 1:
									guestList.get(index).setGender("Male");
									break;
								case 2:
									guestList.get(index).setGender("Female");
									break;
								case 3:
									guestList.get(index).setGender("Others");
									break;
							}
							System.out.println("Gender updated successfully\n");
							sc.nextLine();
							break;
						}
	
					}
					while( flag == 1); 
				
					if (flag == -1) {
						System.out.println("Cancel update\n");
					}
					break;
			case 6: do{
						System.out.println("\nPlease enter your New Nationality (-1 to exit): ");
						nationalityNew = sc.nextLine();
						flag =0;
						if (nationalityNew == "") {
							System.out.println("Please enter a valid Nationality\n");
							flag =1;
						}
						else if (nationalityNew.equals("-1")) {
							flag = -1;
							break;
						}
						for (int i =0; i<nationalityNew.length(); i++) {
							if (!Character.isLetter(nationalityNew.charAt(i))) {
								System.out.println("Please enter a valid Nationality\n");
								flag =1;
								break;
							}
						}				
						
					}
					while( flag == 1);
					if (flag ==0) {
						System.out.println("Nationality updated successfully\n");
						guestList.get(index).setNationality(nationalityNew);	
					}
					else if (flag == -1) {
						System.out.println("Cancel update\n");
					}
					break;
			case 7:	do{
						System.out.println("\nPlease enter your phone number (-1 to exit): ");
						contactNew = sc.nextLine();
						flag =0;
						if (contactNew == "") {
							System.out.println("Please enter a valid phone number:\n");
							flag=1;
						}	
						else if (contactNew.equals("-1")) {
							flag = -1;
							break;
						}
						else {
							for (int i =0; i<contactNew.length(); i++) {
								if (!Character.isDigit(contactNew.charAt(i))) {
									System.out.println("Please enter a valid Phone Number\n");
									flag =1;
									break;
								}
							}
						}

						
					}
					while( flag == 1);
					if (flag == 0) {
						System.out.println("Phone Number updated successfully\n");
						guestList.get(index).setContact(contactNew);
					}
					else if (flag == -1) {
						System.out.println("Cancel update\n");
					}
					break;
				
			case 8:	System.out.println("\nPlease enter your Credit Card Details: ");
					flag = 0;
					while(true) {
						System.out.print("\nPlease Enter name on your credit card: ");
						System.out.println("Please enter NA if not applicable");
						cardNameNew = sc.nextLine();
						flag =0;
						if (cardNameNew.equals("NA")) {
							flag =1;
							break;
						}
						for (int i =0; i<cardNameNew.length(); i++) {
							if (!Character.isLetter(cardNameNew.charAt(i)) && cardNameNew.charAt(i) != ' ' ) {
								System.out.println("Please enter a valid name\n");
								flag =1;
								break;
							}
						}
						if (flag ==0) {
							break;
						}
					}
					if (flag ==0) {
						while(true) {
							System.out.println("Please enter your Card Number: (16 digits)");
							cardNumNew = sc.nextLine();
							flag =0;
							if (cardNumNew == "" || cardNumNew.length() != 16) {
								System.out.println("Please enter a valid Card Number\n");
								flag =1;
								continue;
							}
							for (int i =0; i<cardNumNew.length(); i++) {
								if (!Character.isDigit(cardNumNew.charAt(i))) {
									System.out.println("Please enter a valid Card Number\n");
									flag =1;
									break;
								}
							}
							if (flag == 0) {
								break;
							}
						}
					}
					else {
						cardNumNew = "NA";
					}

					if (flag == 0) {
						while (true) {
							System.out.println("\nPlease enter your Card Expiry Date (MM/YY): ");
							String strPattern = "^(0[1-9]|1[012])/\\d{2}$";
			
							expiryDateNew = sc.nextLine();
							if (expiryDateNew.matches(strPattern) ) {
								break;
							}	
							System.out.println("Please enter a valid Card Expiry Date (MM/YY)\n");
						}
					}
					else {
						expiryDateNew = "NA";
					}

					if (flag == 0) {
						while(true) {
							System.out.println("\nPlease enter your CVV number: ");
							cvvNumNew = sc.nextLine();
							if (cvvNumNew.length() != 3) {
								System.out.println("Please enter a valid CVV Number\n");
								continue;
							}
							for (int i =0; i<cvvNumNew.length(); i++) {
								if (!Character.isDigit(cvvNumNew.charAt(i))) {
									System.out.println("Please enter a valid CVV Number\n");
									flag =1;
									break;
								}
							}
							if (flag == 0) {
								break;
							}
						}
					}
					else {
						cvvNumNew = "NA";
					}
					if (flag ==0) {
						while(true) {
							System.out.print("\nPlease Enter billing address on your credit card: ");
							cardBillingAddressNew = sc.nextLine();
							flag =0;
							if (cardBillingAddressNew.equals(" ")) {
								System.out.println("Please enter a valid billing address\n");
								flag =1;
								break;
							}
							if (flag ==0) {
								break;
							}
						}
					}
					else {
						cardBillingAddressNew = "NA";
					}
						creditCardDetailsNew = new CreditCardDetails(cardNameNew, cardNumNew, expiryDateNew, cvvNumNew, cardBillingAddressNew);
						guestList.get(index).setCreditCardDetails(creditCardDetailsNew);
						System.out.println("Credit Card Details updated successfully\n");
						break;
			case 9:
				while (true) {
					System.out.println("Please enter your new room numnber\n");
					Pattern roomNumberFormat = Pattern.compile("[0][2-7]-[0][1-8]");
	                System.out.println("Enter in the format xx-yy (xx = floor number and yy = room id)");
	                sc.reset();
	                String search = sc.next();
	                Matcher matcher = roomNumberFormat.matcher(search);
	                if (search.length() != 5 || matcher.matches() == false)
	                {
	                    search = "";
	                    System.out.println("You have entered an invalid Room Number.");
	                    continue;
	                }	
					guestList.get(index).setRoomNumber(search);
	                break;
				}
				System.out.println("Room number updated successfully\n");
				break;
			case 10: 
				break;

			}
			
		}
		return 1;
		
	}
	
	// Print a guest profile by name and IC/Passport number
	/**
	* This method prints a particular guest details (attributes from guest class).
	* It does so by searching the index of the particular guest in the array list guestList by matching the name and IC/Passport number of the guest.
	* If guest is not found, it will inform the staff and the values of the attribute will not be printed.
	* Else if the guest is found, it will print out all the attributes of that particular guest.
	* @param name Guest's name.
	* @param icNum Guest's IC/Passport number.
	*/
	public static void printGuestProfile(String name, String icNum) {
		int index = -1;
		index = searchGuestProfile( name,  icNum);
		if (index == -1) {
			System.out.println("No such customer in our records.");
		}
		else {
			System.out.println("Name: " + guestList.get(index).getName() +
								"\nPassport/ IC number: " + guestList.get(index).getIdentity() +
								"\nAddress: " + guestList.get(index).getAddress() +
								"\nCountry of residence: " + guestList.get(index).getCountry() + 
								"\nGender: " + guestList.get(index).getGender() + 
								"\nNationality: " + guestList.get(index).getNationality() + 
								"\nContact Number: " + guestList.get(index).getContact() +
								"\nRoom Number: " + guestList.get(index).getRoomNumber() + 
								"\nCredit Card Details" +
								"\nCard Name:" +guestList.get(index).getCreditCardDetails().getName() + 
								"\nCard Number:" +guestList.get(index).getCreditCardDetails().getCardNumber() + 
								"\nExpiry Date:" +guestList.get(index).getCreditCardDetails().getExpiryDate() + 
								"\nCVV Number:" +guestList.get(index).getCreditCardDetails().getCvvNumber() +
								"\nCard Billing Address:" +guestList.get(index).getCreditCardDetails().getBillingAddress() + "\n");
		}

	}
	
	// Print all existing guest profile 
	/**
	* This method prints all existing guest's details (attributes from guest class).
	* Existing guest means that they are currently staying in the hotel.
	* Guest who made the reservation but have not check in are not considered as existing guest because it is possible that they do not show up to check in.
	* To know if guest is existing, we will check the room number of each guest in array list guestList, if its not NA then guest has a room and it means guest is a existing guest.
	*/
	public static void printGuestProfileAllExisting() {
		int index =0;
		Scanner sc = new Scanner(System.in);
		for (int k =0; k<guestList.size() ; k++ ) {
			if(guestList.get(k) == null) {
				continue;
			}
			else if ((guestList.get(k).getRoomNumber()).equals("NA")) {
				continue;
			}
			else {
				System.out.println("Existing Customer " + (index+1) + ":\n" + "Name: " + guestList.get(k).getName() +
									"\nPassport/ IC number: " + guestList.get(k).getIdentity() +
									"\nContact Number: " + guestList.get(k).getContact() + 
									"\nAddress: " + guestList.get(k).getAddress() +
										"\nCountry of residence: " + guestList.get(k).getCountry() + 
										"\nGender: " + guestList.get(k).getGender() + 
										"\nNationality: " + guestList.get(k).getNationality() + 
										"\nContact Number: " + guestList.get(k).getContact() +
										"\nRoom Number: " + guestList.get(k).getRoomNumber() + 
										"\nCredit Card Details" +
										"\nCard Name:" +guestList.get(k).getCreditCardDetails().getName() + 
										"\nCard Number:" +guestList.get(k).getCreditCardDetails().getCardNumber() + 
										"\nExpiry Date:" +guestList.get(k).getCreditCardDetails().getExpiryDate() + 
										"\nCVV Number:" +guestList.get(k).getCreditCardDetails().getCvvNumber() +
										"\nCard Billing Address:" +guestList.get(k).getCreditCardDetails().getBillingAddress() + "\n");
				index++;
			}
		}

		
	}
	
	// Print all guest profile 
	/**
	* This method prints all the guest's details (attributes from guest class).
	* All guest means that it includes existing guest and non existing guest. Existing guest means that they are currently staying in the hotel while non-existing guest are those that have checked out, made a reservation but have not checked in or guest that made a reservation but reservation have expired.
	*/
	public static void printGuestProfileAll() {
		int index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Printing all customer details in database:");
		for (int k =0; k<guestList.size() ; k++ ) {
			if(guestList.get(k) == null) {
				continue;
			}
			else {
				System.out.println("Customer " + (index+1) + ":\n" + "Name: " + guestList.get(k).getName() +
									"\nPassport/ IC number: " + guestList.get(k).getIdentity() +
									"\nContact Number: " + guestList.get(k).getContact() + 
									"\nAddress: " + guestList.get(k).getAddress() +
										"\nCountry of residence: " + guestList.get(k).getCountry() + 
										"\nGender: " + guestList.get(k).getGender() + 
										"\nNationality: " + guestList.get(k).getNationality() + 
										"\nContact Number: " + guestList.get(k).getContact() +
										"\nRoom Number: " + guestList.get(k).getRoomNumber() + 
										"\nCredit Card Details" +
										"\nCard Name:" +guestList.get(k).getCreditCardDetails().getName() + 
										"\nCard Number:" +guestList.get(k).getCreditCardDetails().getCardNumber() + 
										"\nExpiry Date:" +guestList.get(k).getCreditCardDetails().getExpiryDate() + 
										"\nCVV Number:" +guestList.get(k).getCreditCardDetails().getCvvNumber() +
										"\nCard Billing Address:" +guestList.get(k).getCreditCardDetails().getBillingAddress() + "\n");
				index++;
			}
		}

		
	}
	

	//save file
	/**
	* This method saves all the values of the attribute of the guest class and saves it into our database.
	* We extract the values from each guest in array list guestList and separate the values by '|', our separator.
	* We then combine all the values of the attribute of the guest into a string and add to a list of string containing the values of the attributes.
	* @throws IOException if stream to file cannot be written to or closed.
	*/
	public static void saveFile() throws IOException{
		List alw = new ArrayList(); 
        for (int i = 0 ; i < guestList.size() ; i++) {
			
				Guest g = (Guest) guestList.get(i);				
				StringBuilder st =  new StringBuilder() ;
				
				st.append(g.getName().trim());
				st.append(SEPARATOR);
				st.append(g.getIdentity().trim());
				st.append(SEPARATOR);
				st.append(g.getAddress().trim());
				st.append(SEPARATOR);
				st.append(g.getCountry().trim());
				st.append(SEPARATOR);
				st.append(g.getGender().trim());
				st.append(SEPARATOR);
				st.append(g.getNationality().trim());
				st.append(SEPARATOR);
				st.append(g.getContact().trim());
				st.append(SEPARATOR);
				CreditCardDetails c = g.getCreditCardDetails();
				st.append(c.getName().trim());
				st.append(SEPARATOR);
				st.append(c.getCardNumber().trim());
				st.append(SEPARATOR);
				st.append(c.getExpiryDate().trim());
				st.append(SEPARATOR);
				st.append(c.getCvvNumber().trim());
				st.append(SEPARATOR);
				st.append(c.getBillingAddress().trim());
				st.append(SEPARATOR);
				st.append(g.getRoomNumber().trim());
				st.append(SEPARATOR);
				
				alw.add(st.toString()) ;

			}
        stringsToFile(databaseLocationGuest, alw);
	}
	
	/**
	* This method writes the list of values of the attributes to our guest database.
	* @param filename The location of our guest database.
	* @param data A list containing strings that contains the values of guest's attribute. Each attribute is separated by '|'.
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
	* This method reads the values of the attributes in our database.
	* Each line in our database corresponds to a different guest and this function is able to read all the lines in our database to extract those values.
	* @param fileName The location of our guest database.
	* @return data is the list of strings.
	* @throws IOException if stream to file cannot be read or to closed
	*/
	public static List read(String fileName) throws IOException {
		List data = new ArrayList() ;
	    Scanner scanner = new Scanner(new FileInputStream(fileName));
	    try {
	      while (scanner.hasNextLine()){
	        data.add(scanner.nextLine());
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    return data;
	  }
	 
	/**
	* This method initialize all the values of the attribute of the guest class and creates a guest class.
	* We extract the values from the data in our guest database as the value of each attribute is separated by '|'.
	* It then adds those guest created into guestList, our array list of guest.
	* @throws IOException if stream to file cannot be read or to closed
	*/
	public static void init() throws IOException{
		ArrayList <String> stringArray = (ArrayList) read (databaseLocationGuest);
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","		
				String  name = star.nextToken().trim();	
				String  identity = star.nextToken().trim();	
				String  address = star.nextToken().trim();	
				String  country = star.nextToken().trim();	
				String  gender = star.nextToken().trim();	
				String  nationality = star.nextToken().trim();	
				String  contact = star.nextToken().trim();	
				String  cardName = star.nextToken().trim();	
				String  cardNumber = star.nextToken().trim();	
				String  expiryDate = star.nextToken().trim();	
				String  cvvNumber = star.nextToken().trim();	
				String  cardBillingAddress = star.nextToken().trim();	
				String  roomNumber = star.nextToken().trim();
				CreditCardDetails ccd = new CreditCardDetails( cardName,cardNumber,  expiryDate,  cvvNumber,cardBillingAddress);
				Guest guest = new Guest( name,  identity,  address,  country,  gender,  nationality,  contact, 
						 ccd ,roomNumber);
				guestList.add(guest);
			}
	}
	
	//getter and setter for array list
	/**
	* Gets the array list containing guest classes.
	* @return guestList, array list containing guest.
	*/
	public static ArrayList<Guest> getGuestList() {
		return guestList;
	}
	/**
	* Sets the guestList, the array list containing guest.
	* @param gL array list containing guest class.
	*/
	public static void setGuestList(ArrayList<Guest> gL){
		guestList=gL;
	}
	
	// returns the guest store in array by searching via name and IC/Passport number
	/**
	* This method returns the guest in array list of guest.
	* Firstly it will search the array list, guestList and find the index containing that particular guest by comparing the name and IC/Passport number given.
	* Returns the guest class if such a guest if found, if not found returns null.
	* @param name array list containing guest class.
	* @param icNum array list containing guest class.
	* @return guest class if guest is found in database, returns null if guest is not found in database.
	* 
	*/
	public static Guest getGuest(String name, String icNum) {
		int index = searchGuestProfile(name,icNum);
		if (index == -1) {
			return null;
		}
		return guestList.get(index);
	}



}

