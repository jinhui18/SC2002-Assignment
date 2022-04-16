package Class;
/**
 * Represents the menu items that is served in the room service
 * @author Cheryl
 *
 */
public class MenuItem {
    /**
     * Price of the menu item
     */
    private double price;
    /**
     * Name of the menu item
     */
    private String name;
    /**
     * Description of the menu item is prepared
     */
     private String description;

    /**
     * Sets the price of the menu item
     * @param price of the menu item
     */
    public void setPrice(double price){
        this.price = price;
    }
    /**
     * Sets the name of the menu item
     * @param name of the menu item
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Sets the description of the menu item
     * @param description of the menu item
     */
    public void setDesc(String description){
        this.description = description;
    }

    /**
     * Gets the price of the menu item
     * @return price of the menu item
     */
    public double getPrice(){
        return price;
    }
    /**
     * Gets the name of the menu item
     * @return name of the menu item
     */
    public String getName(){ return name; }
    /**
     * Gets the description of the menu item
     * @return description of the menu item
     */
    public String getDescription(){ return description; }
    /**
     * Constructor to create a menu item
     * @param name Name of the menu item
     * @param description Description of the menu item
     * @param price Price of the menu item
     */
    public MenuItem(String name, String description, double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
