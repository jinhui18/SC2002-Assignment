package Class;

/**
 * Represents an order item in a room service order.
 * @author Mary Soh
 * @version 1.0
 * @since 2022-04-12
 */

public class OrderItem {
    /**
     * The name of this order item.
     */
    private String name;
    /**
     * The remarks of this order item.
     */
    private String foodRemark;
    /**
     * The quantity of this order item.
     */
    private int quantity;
    /**
     * The price of this order item.
     */
    private double price;

    /**
     * Creates an Order item.
     */
    public OrderItem(){
        name=null;
        foodRemark=null;
        quantity=0;
        price=0;
    }

    /**
     * Creates an Order item using its name, remarks, quantity and price.
     * @param name the name of this order item.
     * @param foodRemark the remarks of this order item.
     * @param quantity the quantity of this order item.
     * @param price the price of this order item.
     */
    public OrderItem(String name,String foodRemark, int quantity, double price){
        this.name=name;
        this.foodRemark = foodRemark;
        this.quantity=quantity;
        this.price=price;
    }

    /**
     * Gets the name of this order item.
     * @return the name of this order item.
     */
    public String getName(){ return name;}

    /**
     * Gets the quantity of this order item.
     * @return the quantity of this order item.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Gets the remarks of this order item.
     * @return the remarks of this order item.
     */
    public String getFoodRemark(){
        return foodRemark;
    }

    /**
     * Gets the price of this order item.
     * @return the price of this order item.
     */
    public double getPrice(){
        return price;
    }

    /**
     * Changes the name of this order item.
     * @param name the new name of this order item.
     */
    public void setName(String name){this.name = name; }

    /**
     * Changes the remarks of this order item.
     * @param foodRemark the new remakrs of this order item.
     */
    public void setFoodRemark(String foodRemark){
        this.foodRemark =  foodRemark;
    }

    /**
     * Changes the quantity of this order item.
     * @param quantity the new quantity of this order item.
     */
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }

    /**
     * Changes the price of this order item.
     * @param price the new price of this order item.
     */
    public void setPrice(double price){
        this.price=price;
    }

    /**
     * Prints the name, remarks, quantity and price of this order item.
     */
    public void displayOrderItem(){
        System.out.printf("Food name: %s\n" +
                "Remarks: %s\n" +
                "Quantity: %d\n" +
                "Price: %.2f\n", name, foodRemark, quantity, price);
    }
}
