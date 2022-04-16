package UI;

import java.util.Scanner;
import Controller.OrderController;
import static Class.StatusOrder.CONFIRMED;

/**
 * Represents the user interface when they want to create or make changes to room service.
 * @author Mary Soh
 * @version 1.0
 * @since 2022-04-12
 */
public class OrderUI {
    /**
     * Displays the selection page of Room Service.
     * The options include:
     * Create an order, update an order, remove an order, display  orders for a room. display all orders, display menu, and return to main page.
     */
    public static void mainPage(){
        int choice=0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter choice:\n" +
                    "1: Create order\n" +
                    "2: Update order\n" +
                    "3: Remove order\n" +
                    "4: Display orders for a room\n" +
                    "5: Display all orders\n" +
                    "6: Display menu\n"+
                    "7: Return to main page");
            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        OrderController.displayMenu();
                        createOrderUI();
                        break;
                    case 2:
                        updateOrderUI();
                        break;
                    case 3:
                        removeOrder();
                        break;
                    case 4:
                        displayOrdersForARoom();
                        break;
                    case 5:
                        OrderController.displayAllOrders();
                        break;
                    case 6:
                        OrderController.displayMenu();
                        break;
                    case 7:
                        System.out.println("Exiting room service main page.");
                        break;
                    default:
                        System.out.println("Please enter a valid choice.");
                        break;
                }
            }catch(Exception e){
                System.out.println("Invalid input, please try again");
                sc.nextLine();
            }

        }while(choice!=7);
    }

    /**
     * Displays the interface and gets user input to create a room service order.
     */
    public static void createOrderUI(){
            int choice=0, size, quantity=0;
            double price;;
            String roomNum;
            Scanner sc= new Scanner(System.in);
            while(true){
                System.out.println("Enter room number:");
                roomNum = sc.next();
                if(!OrderController.checkRoomExists(roomNum)){
                    System.out.println("Please enter a valid room number.");
                    continue;
                }
                if(OrderController.searchConfirmedOrder(roomNum)>=0){
                    System.out.println("This room has an existing confirmed order. Please choose to update order instead.");
                    return;
                }
                break;
            }
            OrderController.createOrder(roomNum);
            int count=0;
            do{
                System.out.println("Enter foodID of menu item(-1 to exit):");
                try {
                    choice = sc.nextInt();
                    if (count == 0 && choice == -1) {
                        OrderController.removeOrder(OrderController.getOrderListSize() - 1);
                        System.out.println("No food item has been added.");
                        break;
                    }
                    if (choice == -1) {
                        System.out.println("Order has been created.");
                        break;
                    }
                    //check if foodID exists
                    if (!OrderController.checkFoodIdExists(choice)) {
                        System.out.println("Please enter a valid foodID.");
                        continue;
                    }
                }catch(Exception e){
                System.out.println("Invalid input, please try again");
                sc.nextLine();
                continue;
            }
                count =1;
                size = OrderController.getOrderListSize();

                String remarks;
                while(true) {
                    System.out.println("Enter quantity:");
                    try {
                        quantity = sc.nextInt();
                        if(quantity==0){
                            System.out.println("Quantity must be more than zero.");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again");
                        sc.nextLine();
                        continue;
                    }
                    break;
                }
                System.out.println("Enter remarks:");
                String dummy = sc.nextLine();
                remarks = sc.nextLine();
                price = OrderController.getMenuItemPrice(choice-1);
                OrderController.addOrderItem(size-1, choice, remarks, quantity, price);
                System.out.println("Item is added.");
            }while(choice!=-1);
        }

    /**
     * Displays the interface and gets user input to update an existing room service order.
     */
    public static void updateOrderUI(){
        String roomNum;
        int orderChoice=0;
        Scanner sc =new Scanner(System.in);
        while(true){
            System.out.println("Enter room number:");
            roomNum = sc.nextLine();
            if(!OrderController.checkRoomExists(roomNum)){
                System.out.println("Please enter a valid room number.");
                continue;
            }
            int indexPreparing = OrderController.searchPreparingOrder(roomNum);
            int indexConfirmed = OrderController.searchConfirmedOrder(roomNum);

            //if more than 1 preparing & confirmed orders
            if(indexPreparing!=-1 && indexConfirmed!=-1){
                OrderController.displayUndeliveredOrders(roomNum);
                System.out.println("Enter Order no.:");
                while(true) {
                    try {
                        orderChoice = sc.nextInt();
                    }catch(Exception e){
                        System.out.println("Invalid input, please try again.");
                        sc.nextLine();
                    }
                    break;
                }
                if(OrderController.getOrder(orderChoice).getStatus()== CONFIRMED){
                    updateConfirmedOrder(orderChoice);
                }
                else{
                    updatePreparingOrder(orderChoice);
                }
            }
            //if more than 1 preparing order
            else if(indexPreparing== -2){
                OrderController.displayPreparingOrders(roomNum);
                while(true) {
                    try {
                         orderChoice = sc.nextInt();
                    }catch(Exception e){
                        System.out.println("Invalid input, please try again.");
                        sc.nextLine();
                    }
                    break;
                }
               updatePreparingOrder(orderChoice);
                break;
            }
            // if only 1 preparing order
            else if(indexPreparing>-1){
                updatePreparingOrder(indexPreparing);
                break;
            }
            else if(indexConfirmed!=-1){
                updateConfirmedOrder(indexConfirmed);
                break;
            }
            //if no undelivered orders
            if(indexConfirmed==-1 && indexPreparing==-1){
                System.out.println("This room does not have any undelivered orders.");
                return;
            }


            break;
        }
    }

    /**
     * Gets user input to update an order with the status PREPARING.
     * @param indexPreparing the index of the order in the arraylist OrderList in Order Class to be updated.
     */
    public static void updatePreparingOrder(int indexPreparing){
        int status;
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter status to update to:\n" +
                "1: PREPARING\n" +
                "2: DELIVERED");
        while(true) {
            try {
                status = sc.nextInt();
                break;
            }catch(Exception e){
                System.out.println("Invalid input, please try again.");
                sc.nextLine();
            }
        }
        OrderController.updateOrderStatus(status+1, indexPreparing);
        System.out.println("Order status changed.");
    }
    /**
     * Gets user input to update an order with the status CONFIRMED.
     * @param index the index of the order in the arraylist OrderList in Order Class to be updated.
     */
    public static void updateConfirmedOrder(int index){
        int choice=0, status=0, attributeChoice=0,itemChoice=0,quantity=0;
        String remarks;
        Scanner sc = new Scanner(System.in);
        OrderController.displayOrder(index);

        System.out.println("Enter choice to update:\n" +
                "1: Order status\n" +
                "2: Update existing order item\n" +
                "3: Add food items to order");
        while(true) {
            try {
                choice = sc.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Please enter a valid choice.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Invalid input, please try again");
                sc.nextLine();
            }
        }
        switch(choice){
            case 1:
                System.out.println("Enter status:\n" +
                        "1: CONFIRMED\n" +
                        "2: PREPARING\n" +
                        "3: DELIVERED");
                while(true) {
                    try {
                        status = sc.nextInt();
                        OrderController.updateOrderStatus(status, index);
                        System.out.println("Order status changed.");
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input, please try again");
                        sc.nextLine();
                    }
                }
                break;
            case 2:
                do {
                    System.out.println("Enter item no. of item to update:");
                    while(true) {
                        try{
                            itemChoice = sc.nextInt();
                            break;
                        }catch(Exception e){
                            System.out.println("Invalid input, please try again");
                            sc.nextLine();
                        }
                    }
                    if (itemChoice < 1 || itemChoice > OrderController.getOrderItemListSize(index)) {
                        System.out.println("Invalid item no. entered.");
                        continue;
                    }
                    System.out.println("Enter the number of what you want to update:\n" +
                            "1: Remarks\n" +
                            "2: Quantity");
                    while(true) {
                        try {
                            attributeChoice = sc.nextInt();
                            break;
                        }catch(Exception e){
                            System.out.println("Invalid input, please try again");
                            sc.nextLine();
                        }
                    }
                    do {
                        switch (attributeChoice) {
                            case 1:
                                System.out.println("Enter remarks: ");
                                String dummy = sc.nextLine();
                                remarks = sc.nextLine();
                                OrderController.updateOrderItemRemarks(index, itemChoice, remarks);
                                System.out.println("Remarks updated.");
                                break;
                            case 2:
                                System.out.println("Enter quantity: ");
                                while(true) {
                                    try {
                                        quantity = sc.nextInt();
                                        if(quantity==0){
                                            OrderController.removeOrderItem(index, itemChoice-1);
                                            System.out.println("Item is removed.");
                                        }
                                        else{
                                            OrderController.updateOrderItemQuantity(index, itemChoice, quantity);
                                            System.out.println("Quantity updated.");
                                        }
                                        break;
                                    }catch(Exception e){
                                        System.out.println("Invalid input, please try again");
                                        sc.nextLine();
                                    }
                                }
                                break;
                            default:
                                System.out.println("Please enter a valid choice.");
                                break;
                        }
                    } while (attributeChoice > 2 || attributeChoice < 1);
                    break;
                }while(itemChoice<1 || itemChoice>OrderController.getOrderItemListSize(index));
                break;
            case 3:
                double price;
                OrderController.displayMenu();
                do{
                    System.out.println("Enter foodID of menu item(-1 to exit):");
                    while(true) {
                        try {
                            choice = sc.nextInt();
                            break;
                        }catch(Exception e){
                            System.out.println("Invalid input, please try again");
                            sc.nextLine();
                        }
                    }
                    if(choice==-1){
                        System.out.println("Order item has been created.");
                        break;
                    }
                    //check if foodID EXIST
                    if(!OrderController.checkFoodIdExists(choice)) {
                        System.out.println("Please enter a valid foodID.");
                        continue;
                    }
                    System.out.println("Enter quantity:");
                    while(true) {
                        try {
                            quantity = sc.nextInt();
                            break;
                        }catch(Exception e){
                            System.out.println("Invalid input, please try again");
                            sc.nextLine();
                        }
                    }
                    System.out.println("Enter remarks:");
                    String dummy = sc.nextLine();
                    remarks = sc.nextLine();
                    price = OrderController.getMenuItemPrice(choice-1);
                    OrderController.addOrderItem(index, choice, remarks, quantity, price);
                    System.out.println("Item is added.");
                }while(choice!=-1);
                break;
        }
    }

    /**
     * Gets user input of room number to remove an order. Only orders with the status CONFIRMED can be removed.
     */
    public static void removeOrder(){
        String roomNum;
        int index;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter room number:");
            roomNum = sc.nextLine();
            if(!OrderController.checkRoomExists(roomNum)){
                System.out.println("Please enter a valid room number.");
                continue;
            }
            index = OrderController.searchConfirmedOrder(roomNum);
            if(index==-1){
                System.out.println("This room has no confirmed orders");
                return;
            }
            OrderController.removeOrder(index);
            System.out.println("Order is removed.");
            break;
        }
    }

    /**
     * Gets user input of room number which orders are to be displayed.
     */
    public static void displayOrdersForARoom(){
        int found=0;
        while(true){
            System.out.println("Enter room number:");
            Scanner sc = new Scanner(System.in);
            String roomNum = sc.nextLine();
            if(!OrderController.checkRoomExists(roomNum)){
                System.out.println("Please enter a valid room number.");
                continue;
            }
            for(int j=0; j<OrderController.getOrderListSize(); j++){
                if(OrderController.checkRoomHasOrder(j,roomNum)!=-1){
                    OrderController.displayOrder(j);
                    found =1;
                }
            }
            if(found==0){
                System.out.println("This room does not have any orders.");
                return;
            }
            break;
        }
    }
}
