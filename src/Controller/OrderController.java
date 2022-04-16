package Controller;

import UI.OrderUI;
import Class.Order;
import Class.StatusOrder;
import Class.OrderItem;

import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents the control class to create, update or remove room service orders.
 * @author Mary Soh
 * @version 1.0
 * @since 2022-04-12
 */

public class OrderController {
    /**
     * ArrayList of Orders.
     */
    private static ArrayList<Order> orderList;
    /**
     * File location of the database of orders.
     */
    private static final String orderDB="src\\database\\orderDB.txt";

    /**
     * Gets the ArrayList of Orders attribute.
     * @return the ArrayList of orders.
     */
    public static ArrayList<Order> getOrderList(){
        return orderList;
    }

    /**
     * Gets the size of the ArrayList of Orders.
     * @return the number of Orders.
     */
    public static int getOrderListSize(){return orderList.size();}

    /**
     * Mutator of the ArrayList of Orders.
     * @param oL An ArrayList of Orders.
     */
    public static void setOrderList(ArrayList<Order> oL){
        orderList=oL;
    }


    /**
     * Calls OrderUI class to display the Room Service interface to users.
     */
    public static void displayOrderUI(){
        OrderUI.mainPage();
    }

    /**
     * Creates an Order with the status CONFIRMED with no order items yet.
     * @param roomNum the room number that the room service order belongs to.
     */
    public static void createOrder(String roomNum){
        LocalDate date= LocalDate.now();
        LocalTime time = LocalTime.now();
        Order newOrder = new Order(time, date, StatusOrder.CONFIRMED, roomNum);
        orderList.add(newOrder);
    }

    /**
     * Adds an order item into an Order in the ArrayList of Orders.
     * @param index index of the Order object in the ArrayList of Orders to be changed.
     * @param foodID the index+1 of the menu item in the ArrayList of MenuItems in MenuController to be added.
     * @param remarks Remarks of the order item.
     * @param quantity Number of the order item to be added.
     * @param price Current price of the menu item to be added.
     */
    public static void addOrderItem(int index, int foodID, String remarks, int quantity, double price){
        String name = MenuController.getMenuItemName(foodID-1);
        orderList.get(index).addOrderItem(name, remarks, quantity, price);
    }

    /**
     * Finds the index of the first order in the ArrayList of Orders that has the status of DELIVERED, returns -1 if not found.
     * @param roomNumber Room number whose order is to be found.
     * @return the index of the Order in the ArrayList of Orders.
     */
    public static int searchDeliveredOrder(String roomNumber){
        int i=0;
        for(i=0; i<orderList.size(); i++){
            if(orderList.get(i).getRoomNumber().equals(roomNumber) && orderList.get(i).getStatus()== StatusOrder.DELIVERED){
                return i;
            }
        }
        return -1;
    }
    /**
     * Finds the index of the first order in the ArrayList of Orders that has the status of PREPARING, returns -1 if not found.
     * @param roomNum Room number whose order is to be found.
     * @return the index of the Order in the ArrayList of Orders.
     */
    public static int searchPreparingOrder(String roomNum){
        int i, j=0;
        int found=0;
        for(i=0; i<orderList.size(); i++){
            if(orderList.get(i).getRoomNumber().equals(roomNum) && orderList.get(i).getStatus()== StatusOrder.PREPARING){
                found++;
                j=i;
            }
        }
        if(found==1) return j;
        if(found>1) return -2;
        return -1;
    }

    /**
     * Prints all Orders of a specific room that have the status PREPARING.
     * @param roomNum Room Number of the specific room.
     */
    public static void displayPreparingOrders(String roomNum){
        int i;
        for(i=0; i<orderList.size(); i++){
            if(orderList.get(i).getRoomNumber().equals(roomNum) && orderList.get(i).getStatus()== StatusOrder.PREPARING){
                System.out.println("Order no.: "+i);
                displayOrder(i);
                System.out.println("==================================================");
            }
        }
    }

    /**
     * Prints all Orders of a specific room that have the status PREPARING or CONFIRMED.
     * @param roomNum Room Number of the specific room.
     */
    public static void displayUndeliveredOrders(String roomNum){
        int i;
        for(i=0; i<orderList.size(); i++){
            if(orderList.get(i).getRoomNumber().equals(roomNum) && orderList.get(i).getStatus()!= StatusOrder.DELIVERED){
                System.out.println("Order no.: "+i);
                displayOrder(i);
                System.out.println("==================================================");
            }
        }
    }
    /**
     * Finds the index of the first order in the ArrayList of Orders that has the status of CONFIRMED, returns -1 if not found.
     * @param roomNumber Room number whose order is to be found.
     * @return the index of the Order in the ArrayList of Orders.
     */
    public static int searchConfirmedOrder(String roomNumber){
        int i=0;
        for(i=0; i<orderList.size(); i++){
            if(orderList.get(i).getRoomNumber().equals(roomNumber) && orderList.get(i).getStatus()== StatusOrder.CONFIRMED){
                return i;
            }
        }
        return -1;
    }

    /**
     * Changes to status of a specific order.
     * @param status status to be changed to.
     * @param index index of the specific order in the ArrayList of Orders.
     */
    public static void updateOrderStatus(int status, int index){
        switch(status){
            case 1:
                orderList.get(index).setStatus(StatusOrder.CONFIRMED);
                break;
            case 2:
                orderList.get(index).setStatus(StatusOrder.PREPARING);
                break;
            case 3:
                orderList.get(index).setStatus(StatusOrder.DELIVERED);
                break;
        }

    }

    /**
     * Changes the remarks of a specific order item of a specific order.
     * @param index index of the specific order in the ArrayList of Orders.
     * @param foodID index+1 of the order item in the ArrayList of OrderItems in a specific Order.
     * @param remarks remarks to be changed to.
     */
    public static void updateOrderItemRemarks(int index, int foodID, String remarks ){
        orderList.get(index).updateOrderItemRemarks(foodID-1, remarks);
    }

    /**
     * Changes the quantity of a specific order item of a specific order.
     * @param index index of the specific order in the ArrayList of Orders.
     * @param itemChoice index+1 of the order item in the ArrayList of OrderItems in a specific Order.
     * @param quantity new quantity of the order item.
     */
    public static void updateOrderItemQuantity(int index, int itemChoice, int quantity){
        orderList.get(index).updateOrderItemQuantity(itemChoice-1, quantity);
    }

    /**
     * Deletes an Order in the ArrayList of Orders.
     * @param index index of the Order in the ArrayList to be deleted.
     */
    public static void removeOrder(int index){
            orderList.remove(index);
    }

    /**
     * Deletes all Orders of a specific room in the ArrayList of Orders.
     * @param roomNum Room number which orders are to be deleted.
     */
    public static void removeOrderForCheckout(String roomNum){
        int index;
        index = searchDeliveredOrder(roomNum);
        if(index==-1){
            return;
        }
        orderList.remove(index);
    }

    /**
     * Prints Order details and its order items of a specific order.
     * @param index index of the Order in the ArrayList of Orders.
     */
    public static void displayOrder(int index){
            orderList.get(index).displayOrder();

    }

    /**
     * Prints all orders existing.
     */
    public static void displayAllOrders(){
        for(int i=0; i<orderList.size(); i++){
            orderList.get(i).displayOrder();
            System.out.println("---------------");
        }
        System.out.println("All orders displayed.");
    }

    /**
     * 	This method initialize all the values of the attributes of the Order and OrderItem classes and creates OrderItem objects and Order objects.
     * 	We extract the values from the data in our guest database as the value of each attribute is separated by '@'.
     * 	It then adds those OrderItem created into orderItemList in the Order class, our array list of order items.
     * 	Then the Order objects created will be added into OrderList in this OrderController, our array list of orders.
     */
    public static void initOrders(){
        String[] arrLine;
        String line;
        int i=0;
        String sep ="@";
        orderList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderDB));
            while((line=reader.readLine())!=null){
                arrLine = line.split(sep);

                Order newOrder = new Order(LocalTime.parse(arrLine[0]),LocalDate.parse(arrLine[1]), StatusOrder.valueOf(arrLine[2]), arrLine[3]);
                i=0;
                while(4*i+4<arrLine.length){
                    newOrder.addOrderItem(arrLine[4*i+4], arrLine[4*i+5], Integer.parseInt(arrLine[4*i+6]), Double.parseDouble(arrLine[4*i+7]));
                    i++;
                }
                orderList.add(newOrder);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();
            return;
        }
    }

    /**
     * This method reads the values of the attributes in our database.
     * Each line in our database corresponds to a different order and order items and this function is able to read all the lines in our database to extract those values.
     */
    public static void saveOrders(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(orderDB));
            for(int i=0; i<orderList.size(); i++){
                writer.write(orderList.get(i).getTimeOrdered().toString() +'@'+orderList.get(i).getDateOrdered().toString() + '@'+orderList.get(i).getStatus().name() +'@'+
                     orderList.get(i).getRoomNumber()+'@');
                for(int j=0; j<orderList.get(i).getOrderItemList().size(); j++){
                    OrderItem tempOrderItem = orderList.get(i).getOrderItemList().get(j);
                    writer.write(tempOrderItem.getName() +'@'+ tempOrderItem.getFoodRemark()+'@'+tempOrderItem.getQuantity()+ '@'+Double.toString(tempOrderItem.getPrice())+'@');
                }
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays all menu items.
     */
    public static void displayMenu(){
        MenuController.displayMenuItemAll();
    }

    /**
     * Gets the price of a specific menu item.
     * @param index index of the menu item in the ArrayList of MenuItems.
     * @return the price of the specific menu item.
     */
    public static double getMenuItemPrice(int index){
        return MenuController.getMenuItemPrice(index);
    }

    /**
     * Checks if a Room Number exists.
     * @param roomNum the room number to be checked.
     * @return Whether the room exists or not.
     */
    public static boolean checkRoomExists(String roomNum){
        if(RoomController.searchRoom(roomNum)==-1){
            return false;
        }
        else return true;
    }

    /**
     * Checks if the index of the menu item chosen is valid.
     * @param foodID index of the menu item in the ArrayList of MenuItems.
     * @return Whether the food ID exists or not.
     */
    public static boolean checkFoodIdExists(int foodID){
        if(foodID> MenuController.getMenuItemListSize() || foodID<-1){
            return false;
        }
        return true;
    }

    /**
     * Gets the number of order items in a specific order.
     * @param index index of the Order in the ArrayList of Orders.
     * @return number of order items in a specific order.
     */
    public static int getOrderItemListSize(int index){
        return orderList.get(index).getOrderItemListSize();
    }

    /**
     * Checks if a specific room has a specific order.
     * @param index index of the Order in the ArrayList of Orders.
     * @param roomNum room number of the room.
     * @return the index of the Order in the ArrayList of Orders, -1 if not found.
     */
    public static int checkRoomHasOrder(int index, String roomNum){
        if(orderList.get(index).getRoomNumber().equals(roomNum)){
            return index;
        }
        return -1;
    }

    /**
     * Gets a specific Order in the ArrayList of Orders.
     * @param indexOrder index of the Order in the ArrayList of Orders.
     * @return the specific order.
     */
	public static Order getOrder(int indexOrder) {
		return orderList.get(indexOrder);
	}

    /**
     * Remove an order item in a specific order.
     * @param index index of the specific order in the ArrayList of Orders.
     * @param itemChoice index of the order item in the ArrayList of Order items in the Order.
     */
	public static void removeOrderItem(int index, int itemChoice){
        orderList.get(index).removeOrderItem(itemChoice);
    }

    /**
     * This method calculates the total room service bill for a room.
     * If the room has multiple orders, the bills of the individual orders will be added.
     * This is assuming that all orders have been delivered before checkout.
     * @param roomNum This is the room number of the room to calculate bill for.
     * @return The total bill of all orders of that room.
     */
	public static double calTotalBill(String roomNum){
        double bill=0;
        for(int i=0; i<orderList.size(); i++){
            if(roomNum.equals(orderList.get(i).getRoomNumber())){
                bill += orderList.get(i).calBill();
            }
        }
        return bill;
    }

}
