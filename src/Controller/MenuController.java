package Controller;
import UI.MenuUI;
import Class.MenuItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Control class to deal with all operations on menu items
 * @author Cheryl
 *
 */
public class MenuController {
    /**
     * A list that contains all menu items served in the room service
     */
    private static ArrayList<MenuItem> menuItemList;
    /**
     * The location of the database to be read and saved from
     */
    private static final String menuDB = Paths.get(System.getProperty("user.dir"), "src", "database", "MenuItem.csv").toString();
    /**
     * Gets the list that contains all menu items
     * @return menuItemList
     */
    public static ArrayList<MenuItem> getMenuItemList(){ return menuItemList;}
    /**
     * Sets the list that contains all menu items
     * @param mI menuItemList
     */
    public static void setMenuItemList(ArrayList<MenuItem> mI){ menuItemList=mI;}
    /**
     * Displays the main page for menu items
     */
    public static void dispMainPage(){
        MenuUI.mainPage();}
    /**
     * Handles the input from the user to go to the specific operation chosen
     * @param choice Choice chosen by Users
     */
    public static void goTo(int choice) {
        switch(choice) {
            case 1:
                MenuUI.createMenuItem();
                break;
            case 2:
                MenuUI.updateMenuItem();
                break;
            case 3:
                MenuUI.deleteMenuItem();
                break;
            case 4:
                displayMenuItemAll();
                break;
            default:
                break;
        }

    }
    /**
     * Saves the menu items into the database
     */
    private static void saveMenuItem() {
        int i;
        MenuItem menuItem;
        String name, description, price;
        FileWriter fw = null;

        try {
            fw = new FileWriter(menuDB);
            fw.append("Name, Description, Price\n");

            for(i = 0; i < menuItemList.size(); i++) {
                menuItem = menuItemList.get(i);
                name = menuItem.getName();
                description = menuItem.getDescription();
                price = Double.toString(menuItem.getPrice());

                fw.append(name + "," + description + "," + price + "\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error in updating database");
        } finally {
            try {
                fw.flush();
                fw.close();
            } catch(Exception e) {}
        }
    }
    /**
     * Loads the menu items from the database
     */
    public static void initMenu() {
        BufferedReader reader = null;
        menuItemList = new ArrayList<>();
        String name, description;
        double price;


        try {
            reader = new BufferedReader(new FileReader(menuDB));
            String line = "";
            reader.readLine();

            while((line = reader.readLine()) != null) {
                String[] f = line.split(",");

                if(f.length > 0) {
                    name = f[0];
                    description = f[1];
                    price = Double.valueOf(f[2]);
                    menuItemList.add(new MenuItem(name, description, price));
                }
            }
        }catch (Exception e) {
            System.out.println("Database not found, starting with empty menu item database");
        }finally {
            try {
                reader.close();
            } catch (Exception e) {}
        }
    }
    /**
     * Gets the number of menu items available
     * @return Number of menu items available
     */
    public static int getMenuItemListSize() {
        if(menuItemList == null) {
            return 0;
        }
        return menuItemList.size();
    }
    /**
     * Gets the price of the menu item based on the input choice
     * @param choice Index of the menu item
     * @return Price of the menu item at the index chosen
     */
    public static double getMenuItemPrice(int choice) {
        if(menuItemList == null) {
            System.out.println("Menu is empty.");
            return 0;
        }
        return menuItemList.get(choice).getPrice();
    }
    /**
     * Gets the name of the menu item based on the input choice
     * @param  index of the menu item
     * @return Name of the menu item at the index chosen
     */
    public static String getMenuItemName(int index) {
        if(menuItemList == null) {
            System.out.println("Menu is empty.");
            return null;
        }
        return menuItemList.get(index).getName();
    }
    /**
     * Deletes a menu item based on its index
     * @param choice The index of the menu item to be deleted from the list
     */
    public static void deleteMenuItem(int choice) {
        menuItemList.remove(choice);
        saveMenuItem();
    }
    /**
     * Creates a new menu item based on input given
     * @param name Name of the new menu item
     * @param description Description of the new menu item
     * @param price Price of the new menu item
     */
    public static void createMenuItem(String name, String description, double price) {
        menuItemList.add(new MenuItem(name, description, price));
        saveMenuItem();
    }
    /**
     * Updates a menu item based on choice of item and choice of which attribute to update
     * @param choice Index of the menu item
     * @param updateChoice Index of menu item's attribute
     */
    public static void updateMenuItem(int choice, int updateChoice) {
        Scanner scan = new Scanner(System.in);
        String name, description;
        double price;
        switch(updateChoice) {
            case 1:
                System.out.println("Enter Name of Menu Item: ");
                name = scan.nextLine();
                menuItemList.get(choice).setName(name);
                break;
            case 2:
                System.out.println("Enter Menu Item Description: ");
                description = scan.nextLine();
                menuItemList.get(choice).setDesc(description);
                break;
            case 3:
                System.out.println("Enter Menu Item Price: ");

                while(true) {
                    try {
                        price = scan.nextDouble();
                        while(price < 0.01) {
                            System.out.println("Invalid input, please try again");
                            price = scan.nextDouble();
                        }
                        break;
                    }catch(Exception e) {
                        System.out.println("Invalid input, please try again");
                        scan.nextLine();
                    }
                }
                menuItemList.get(choice).setPrice(price);
                break;

            default:
                System.out.println("Please enter a valid choice");
                break;
        }

        saveMenuItem();

    }
    /**
     * Displays all menu items
     */
    public static void displayMenuItemAll(){
        for(int i = 0; i<menuItemList.size();i++){
            System.out.printf("FoodID: %d\n", i+1);
            System.out.printf("Name: %s\n",menuItemList.get(i).getName());
            System.out.printf("Description: %s\n", menuItemList.get(i).getDescription());
            System.out.printf("Price: %.2f\n", menuItemList.get(i).getPrice());
            System.out.println("==================================================");
        }
    }

}


