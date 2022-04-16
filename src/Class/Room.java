package Class;

/**
 * Represents one room in the hotel.
 * One room can have different statuses and characteristics in the hotel
 * @author Loke Kah Hou
 * @version 1.3
 * @since 2022-04-12
 */

public class Room {

	/**
	 * The room status that a room can have.
	 */
	public enum RoomStatus {
		/**
		 * The room is Vacant
		 */
	    VACANT,
	    /**
		 * The room is Occupied
		 */
	    OCCUPIED,
	    /**
		 * The room is Reserved
		 */
	    RESERVED,
	    /**
		 * The room is Under Maintenance
		 */
	    UNDER_MAINTENANCE
	  }

	/**
	* The bed type that is available in the room
	*/
	public enum BedType {
		/**
		 * The room has a single bed
		 */
	    SINGLE,
	    /**
		 * The room has a double bed
		 */
	    DOUBLE,
	    /**
		 * The room has a master bed
		 */
	    MASTER,
	  }
	
	/**
	* The room type that a room can be
	*/
	public enum RoomType {
		/**
		 * Single Room
		 */
	    SINGLE,
	    /**
		 * Double Room
		 */
	    DOUBLE,
	    /**
		 * Deluxe Room
		 */
	    DELUXE,
	    /**
		 * VIP Suite Room
		 */
	    VIP_SUITE
	  }

	/**
	* The room number of the room
	*/
	private String roomNumber;

	/**
	* The bed type available in the room
	*/
	private BedType bedType;

	/**
	* The room type of the room
	*/
	private RoomType roomType;

	/**
	* The room status of the room
	*/
	private RoomStatus roomStatus;

	/**
	* The availability of wifi in the room
	*/
	private boolean wifiAvailability;

	/**
	* The availability of a view in the room
	*/
	private boolean viewAvailability;

	/**
	* The smoking rule of the room
	*/
	private boolean smokingRule;
	
	/**
	* Creates a new room with no attributes
	*/
	public Room() {};

	/**
	* Creates a new room with the various attributes
	* @param roomNumber This Room's room number
	* @param bedType This Room's available bed type
	* @param roomType This Room's type
	* @param roomStatus This Room's status
	* @param wifiAvailability This Room's WIFI availability
	* @param viewAvailability This Room's availability of a view
	* @param smokingRule This Room's smoking rule
	*/
	public Room(String roomNumber, BedType bedType, RoomType roomType, RoomStatus roomStatus, boolean wifiAvailability, boolean viewAvailability, boolean smokingRule) {
		this.roomNumber = roomNumber;
		this.bedType = bedType;
		this.roomType = roomType;
		this.roomStatus = roomStatus;
		this.wifiAvailability = wifiAvailability;
		this.viewAvailability = viewAvailability;
		this.smokingRule = smokingRule;
	}

	/**
	* Gets the room number associated with the Room
	* @return this Room's room number
	*/
	public String getRoomNumber() {return roomNumber;}

	/**
	* Changes the room number of this Room
	* @param roomNumber This Room's new room Number
	*		    Will be in the format of xx-yy
	*		    xx represents the floor number from 02-07
	*		    yy represents the room number on the floor from 01-08
	*/
	public void setRoomNumber(String roomNumber) {this.roomNumber = roomNumber;}
	
	/**
	* Gets the bed type available in the Room
	* @return this Room's bed type
	*/
	public BedType getBedType() {return bedType;}

	/**
	* Changes the bed type of this Room
	* @param bedType This Room's new bed type
	*		 Will be in the format of either SINGLE, DOUBLE or MASTER
	*/
	public void setBedType(BedType bedType) {this.bedType = bedType;}
	
	/**
	* Gets the Room's room type
	* @return this Room's room type
	*/
	public RoomType getRoomType() {return roomType;}

	/**
	* Changes the room type of this Room
	* @param roomType This Room's new room type
	*		  Will be in the format of either SINGLE, DOUBLE, DELUXE or VIP_SUITE
	*/
	public void setRoomType(RoomType roomType) {this.roomType = roomType;}
	
	/**
	* Gets the Room's current room status
	* @return this Room's room status
	*/
	public RoomStatus getRoomStatus() {return this.roomStatus;}

	/**
	* Changes the room status of this Room
	* @param roomStatus This Room's new room status
	*		    Will be in the format of either VACANT, OCCUPIED, RESERVED or UNDER_MAINTENANCE
	*/
	public void setRoomStatus(RoomStatus roomStatus) {this.roomStatus = roomStatus;}
	
	/**
	* Gets the WIFI availability in the Room
	* @return this Room's availability of Wifi
	*/
	public boolean getWifiAvailability() {return wifiAvailability;}

	/**
	* Changes the Wifi availability of this Room
	* @param wifiAvailability This Room's new WIFI availability
	*		          Will be in the format of either True or False
	*/
	public void setWifiAvailability(boolean wifiAvailability) {this.wifiAvailability = wifiAvailability;}
	
	/**
	* Gets the view availability in the Room
	* @return this Room's availability of view
	*/
	public boolean getViewAvailability() {return viewAvailability;}

	/**
	* Changes the view availability of this Room
	* @param viewAvailability This Room's new view availability
	*		          Will be in the format of either True or False
	*/
	public void setViewAvailability(boolean viewAvailability) {this.viewAvailability = viewAvailability;}
	
	/**
	* Gets the smoking rule of the Room
	* @return this Room's smoking rule
	*/
	public boolean getSmokingRule() {return smokingRule;}

	/**
	* Changes the view availability of this Room
	* @param smokingRule This Room's Smoking Rule
	*		     Will be in the format of either True or False
	*/
	public void setSmokingRule(boolean smokingRule) {this.smokingRule = smokingRule;}
	
	/**
	* This method prints out the Room Details of an individual room
	* Prints out Room Number, Bed Type, Room Type, Room Status, Wifi Availability, View Availability and Smoking Rule of the room
	*/
	public void printRoomDetails(){
	    System.out.println("Room Number: "+ roomNumber);
	    System.out.println("Bed Type: "+ bedType);
	    System.out.println("Room Type: "+ roomType);
	    System.out.println("Room Status: "+ roomStatus);
	    if(wifiAvailability) {
	      System.out.println("Wifi Availability: Yes");
	    }
	    else{
	      System.out.println("Wifi available? No");
	    }
	    if(viewAvailability) {
	      System.out.println("View available? Yes");
	    }
	    else{
	      System.out.println("View available? No");
	    }
	    if(smokingRule) {
	      System.out.println("Smoking available? Yes");
	    }
	    else{
	      System.out.println("Smoking available? No");
	    }
	  }
}
