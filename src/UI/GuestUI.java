package UI;
import Controller.GuestController;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Guest UI will print out the options that the staff can select when it comes anything that involves the details of the guest.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class GuestUI {
	/**
	Display guest UI will print out the options with regards to guest details. It includes:
	Creation of guest profile, this allows staff to call the method in guest controller to create a new guest profile. Staff have to ask guest for their name and IC/Passport number.
	Update of guest details, this allows the staff to call the method in guest controller to update guest profile. Staff have to ask guest for their name and IC/Passport number.
	Removal of guest from database, this allows the staff to call the method in guest controller to remove guest profile. Staff have to ask guest for their name and IC/Passport number.
	Viewing a particular guest profile, this method allows the staff to view that particular guest profile. Staff have to ask guest for their name and IC/Passport number.
	Viewing all guest profile by existing guest and by all guest regardless if they are currently staying in the hotel a not.
	*/
	public static void displayGuestUI() {
		int choice =0;
		Scanner sc = new Scanner(System.in);
		do {
				System.out.println("==================================================");
				System.out.println("  Guest Details Management: ");
				System.out.println("==================================================");
				System.out.println("1: Create Guest Profile");
				System.out.println("2: Update Guest Profile");
				System.out.println("3: Remove Guest Profile");
				System.out.println("4: View a Guest Profile");
				System.out.println("5: View all Guests Profile");
				System.out.println("6: Go back to main menu");
				
				while (true) {
					try {
						choice = sc.nextInt();
					}
					catch(Exception e) {
						System.out.println("Please enter a valid choice");
					}
					break;
				}

				
				sc.nextLine();
				System.out.println("\n");
				
				switch (choice) {
				 //Create Guest Profile
				 case 1:GuestController.createGuestProfile();
				 		break;
				 		
				 //Update Guest Profile
				 case 2:System.out.println("Please provide the Customer's Name: ");
				 		String name = sc.nextLine();
				 		System.out.println("Please provide the Customer's Passport/ IC number: ");
				 		String identity = sc.nextLine();
				 		int success = GuestController.updateGuestDetails(name,identity);
				 		if (success == 1) {
					 		System.out.println("Updated sucessfully.");
				 		}
				 		else {
					 		System.out.println("Update not successful.");

				 		}
				 		break;
				 		
				 //Remove a Guest Profile
				 case 3:System.out.println("Please provide the Customer's Name: ");
			 			name = sc.nextLine();
			 			System.out.println("Please provide the Customer's Passport/ IC number: ");
			 			identity = sc.nextLine();
			 			success = GuestController.removeGuestProfile(name,identity);
				 		if (success==1) {
					 		System.out.println("Removed sucessfully.");
				 		}
				 		else {
					 		System.out.println("Removal not successful.");
				 		}
				 		break;
				 		
				 //View a particular Guest's Profile
				 case 4:System.out.println("Please provide the Customer's Name: ");
			 			name = sc.nextLine();
			 			System.out.println("Please provide the Customer's Passport/ IC number: ");
			 			identity = sc.nextLine();
			 			GuestController.printGuestProfile(name,identity);
			 			break;
			 			
				 //View all Guest's Profile
				 case 5:
					 	System.out.println("Please select the following:");
					 	System.out.println("(1) Print all existing customer details:");
					 	System.out.println("(2) Print all customer details:");
			            try {
			                choice = sc.nextInt();
			            } catch (InputMismatchException a)
			            {
			                System.out.println("You have entered an invalid input. Please try again.");
			                sc.nextLine();
			                continue;
			            }
			            switch (choice) {
			            // view all existing guest's profile
			            case 1: GuestController.printGuestProfileAllExisting();
			            		break;
			            // view all guest's profile including guest that have not checked in or checked out already
			            case 2: GuestController.printGuestProfileAll();
			            		break;
			            }
			 			break;
				 case 6: 
				 		break; 
				 default:
					 break;
				}
		}
		while(choice != 6);
	}
	
}
