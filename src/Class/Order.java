package Class;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents a room service order.
 * @author Mary Soh
 * @version 1.0
 * @since 2022-04-12
 */

public class Order {
    /**
     * The order items that the Order has.
     */
    private ArrayList<OrderItem> orderItemList;
    /**
     * The time that the order was created.
     */
    private LocalTime timeOrdered;
    /**
     * The date that the order was created.
     */
    private LocalDate dateOrdered;
    /**
     * Total bill of all order items in the order.
     */
    private double foodBill;
    /**
     * The status of the order.
     */
    private StatusOrder status;
    /**
     * The room number that this order belongs to.
     */
    private String roomNumber;

    /**
    * Creates a new Order.
    */
    public Order(){
        orderItemList = new ArrayList<>();
        timeOrdered=null;
        dateOrdered=null;
        foodBill=0;
        status = null;
        roomNumber=null;
    }

    /**
     * Creates a new Order with the given time, date, status and room number.
     * @param timeOrdered The time that the order was created.
     * @param dateOrdered The date that the order was created.
     * @param status The status of the order.
     * @param roomNumber  The room number that this order belongs to.
     */
    public Order(LocalTime timeOrdered, LocalDate dateOrdered, StatusOrder status, String roomNumber){
        orderItemList= new ArrayList<>();
        this.timeOrdered=timeOrdered;
        this.dateOrdered=dateOrdered;
        this.status=status;
        this.roomNumber=roomNumber;
        foodBill=0;
    }

    /**
     * Gets the ArrayList of Order items in this Order.
     * @return the items in this order.
     */
    public ArrayList<OrderItem> getOrderItemList(){
        return orderItemList;
    }

    /**
     * Gets the time that this Order was created.
     * @return the time that this Order was created.
     */
    public LocalTime getTimeOrdered(){
        return timeOrdered;
    }

    /**
     * Gets the date that this Order was created.
     * @return the date that this Order was created.
     */
    public LocalDate getDateOrdered(){
        return dateOrdered;
    }

    /**
     * Gets the room number that this Order was created for.
     * @return the room number that this Order was created for.
     */
    public String getRoomNumber(){
        return roomNumber;
    }

    /**
     * Gets the total bill of all items in this Order.
     * @return the bill of all items in this Order.
     */
    public double getFoodBill(){
        return foodBill;
    }

    /**
     * Gets the status of this Order.
     * @return the status of this Order.
     */
    public StatusOrder getStatus(){
        return status;
    }

    /**
     * Gets the number of order items in this Order.
     * @return the number of order items in this Order.
     */
    public int getOrderItemListSize(){ return orderItemList.size();}

    /**
     * Changes the ArrayList of order items in this Order.
     * @param orderItemList the new ArrayList of order items.
     */
    public void setOrderItemList(ArrayList<OrderItem> orderItemList){
        this.orderItemList=orderItemList;
    }

    /**
     * Changes the time that this Order was created.
     * @param timeOrdered new time that this Order was created.
     */
    public void setTimeOrdered(LocalTime timeOrdered){
        this.timeOrdered=timeOrdered;
    }

    /**
     * Changes the date that this Order was created.
     * @param dateOrdered new date that this Order was created.
     */
    public void setDateOrdered(LocalDate dateOrdered){
        this.dateOrdered=dateOrdered;
    }

    /**
     * Changes the total bill of all order items in this Order.
     * @param foodBill new total bill of all order items in this Order.
     */
    public void setFoodBill(double foodBill){
        this.foodBill=foodBill;
    }

    /**
     * Changes the status of this Order.
     * @param status new status of this Order.
     */
    public void setStatus(StatusOrder status){
        this.status=status;
    }

    /**
     * Changes the room number of this Order.
     * @param roomNumber new room number of this Order.
     */
    public void setRoomNumber(String roomNumber){
        this.roomNumber=roomNumber;
    }

    /**
     * Calculates the total bill of all items in this Order.
     * @return the total bill of this Order.
     */
    public double calBill(){
        double total=0;
        for(int i=0; i<orderItemList.size(); i++){
            total += orderItemList.get(i).getPrice()*orderItemList.get(i).getQuantity();
        }
        return total;
    }

    /**
     * Adds an item into this Order using the name of the menu item, remarks, quantity and price.
     * @param name name of the menu item.
     * @param foodRemark remarks for this order item.
     * @param quantity quantity of this order item.
     * @param price price of this order item.
     */
    public void addOrderItem(String name, String foodRemark, int quantity, double price){
        OrderItem item = new OrderItem(name, foodRemark, quantity, price);
        orderItemList.add(item);
    }

    /**
     * Changes the remarks of a specific order item.
     * @param foodIndex the index of the order item in the ArrayList of order items to be changed.
     * @param remarks the new remarks of the order item.
     */
    public void updateOrderItemRemarks(int foodIndex, String remarks){
        orderItemList.get(foodIndex).setFoodRemark(remarks);
    }

    /**
     * Changes the quantity of a specific order item.
     * @param index index of the specific order item in the ArrayList of Order items.
     * @param quantity new quantity of the order item.
     */
    public void updateOrderItemQuantity(int index, int quantity){
        orderItemList.get(index).setQuantity(quantity);
    }


    /**
     * Prints the date, time, bill, status and order items of this Order.
     */
    public void displayOrder(){
    	System.out.println("Date ordered: "+dateOrdered+"\nTime ordered: "+timeOrdered);
        System.out.printf("\nFood bill: %.2f",calBill());
        System.out.println("\nStatus: "+ status+"\nRoom number: "+roomNumber);
        System.out.println("Food items ordered:");
        for(int i=0; i<orderItemList.size(); i++){
            System.out.printf("Item no. %d\n", i+1);
            orderItemList.get(i).displayOrderItem();
            System.out.println("---------------");
        }
        System.out.println("All items displayed.");
    }

    /**
     * Deletes a specific order item in this Order.
     * @param itemChoice the index of the order item in the ArrayList of Order Items to be removed.
     */
    public void removeOrderItem(int itemChoice){
        orderItemList.remove(itemChoice);
    }
}

