package UI;
import java.util.Scanner;
import Controller.MenuController;

/**
 * Menu Item UI Page for menu item related operations
 * @author Cheryl
 */
public class MenuUI {

    /**
     * Main page for menu item related operations
     */
    public static void mainPage() {
        int choice = 1;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("==============================");
            System.out.println("Menu Items");
            System.out.println("==============================");
            System.out.println("0.  Return to Main Menu");
            System.out.println("1.  Create Menu Item");
            System.out.println("2.  Update Menu Item");
            System.out.println("3.  Remove Menu Item");
            System.out.println("4.  Display Menu Items");
            System.out.println("==============================");
            System.out.println("Select Choice:");

            while(true) {
                try {
                    choice = scan.nextInt();
                    if(choice < 0 || choice > 4) {
                        throw new Exception();
                    }
                    MenuController.goTo(choice);
                    break;
                }catch(Exception e) {
                    System.out.println("Invalid choice, please choose again");
                    scan.nextLine();
                }
            }

        } while(choice != 0);
    }

    /**
     * Reads the details of the new menu item to be created from the user before passing it to the MenuItemController to be created
     */
    public static void createMenuItem() {
        String name, description;
        double price;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Name of Menu Item: ");
        name = scan.nextLine();

        System.out.println("Enter Menu Item Description: ");
        description = scan.nextLine();

        System.out.println("Enter Menu Item Price: ");

        while(true) {
            try {
                price = scan.nextDouble();
                while(price < 0.01) {
                    System.out.println("Invalid input, please try again");
                    price = scan.nextDouble();
                }
                MenuController.createMenuItem(name, description, price);
                return;
            }catch(Exception e) {
                System.out.println("Invalid input, please try again");
                scan.nextLine();
            }
        }
    }

    /**
     * Reads the choice of the menu item to be updated from the user
     * and updateChoice of which attribute to update before passing it
     * to the MenuItemController to be updated
     * @return Whether a menu item was updated (true) or no menu item was updated (false)
     */
    public static boolean updateMenuItem() {

        int i, choice, updateChoice;
        int size = MenuController.getMenuItemListSize();
        Scanner scan = new Scanner(System.in);

        if (size == 0) {
            System.out.println("There is no menu item found, returning to menu item main page");
            return false;
        }
        System.out.println("0. Return to menu item main page");
        for(i = 0; i < size; i++) {
            System.out.println((i+1) + ". " + MenuController.getMenuItemName(i));
        }
        System.out.println("Select menu item");
        choice = readInt(0, size);
        if (choice == 0) return false;

        System.out.println("Select option to update: ");
        System.out.println("0.  Return to Main Menu");
        System.out.println("1.  name");
        System.out.println("2.  description");
        System.out.println("3.  price");

        updateChoice = scan.nextInt();
        if(updateChoice < 0 || updateChoice > 3) {
            System.out.println("Invalid choice, please choose again");
            scan.nextLine();
        }
        MenuController.updateMenuItem(choice-1, updateChoice);
        return true;
    }

    /**
     * Deletes a menu item
     * @return Whether a menu item was deleted (true) or no menu item was deleted (false)
     */
    public static boolean deleteMenuItem() {
        int i, choice;
        int size = MenuController.getMenuItemListSize();

        if (size == 0) {
            System.out.println("There is no menu item found, returning to menu item main page");
            return false;
        }

        System.out.println("0. Return to menu item main page");
        for(i = 0; i < size; i++) {
            System.out.println((i+1) + ". " + MenuController.getMenuItemName(i));
        }
        System.out.println("Select menu item");
        choice = readInt(0, size);
        if (choice == 0) return false;
        MenuController.deleteMenuItem(choice - 1);
        return true;
    }

    /**
     * Reads an input while dealing with any errors and ensuring input is within a certain boundary dictated by min and max
     * @param min The minimum number the input integer can be
     * @param max The maximum number the input integer can be
     * @return A valid integer number
     */
    private static int readInt(int min, int max) {
        int choice;
        Scanner scan = new Scanner(System.in);

        while(true) {
            try {
                choice = scan.nextInt();

                while(choice < min || choice > max) {
                    System.out.println("Invalid choice, please choose again");
                    choice = scan.nextInt();
                }

                return choice;

            }catch(Exception e) {
                System.out.println("Invalid choice, please choose again");
                scan.nextLine();
            }
        }
    }


}

