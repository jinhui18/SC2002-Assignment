package Assignment_SC2002;

public class Room {
	enum RoomStatus {
	    VACANT,
	    OCCUPIED,
	    RESERVED,
	    UNDER_MAINTENANCE
	  }
	enum BedType {
	    SINGLE,
	    DOUBLE,
	    MASTER,
	  }
	enum RoomType {
	    SINGLE,
	    DOUBLE,
	    DELUXE,
	    VIP_SUITE
	  }
	private String roomNumber;
	private BedType bedType;
	private RoomType roomType;
	private RoomStatus roomStatus;
	private boolean WifiAvailability;
	private boolean ViewAvailability;
	private boolean SmokingRule;
	private double price;
	
	public Room() {};
	public Room(String roomNumber, BedType bedType, RoomType roomType, RoomStatus roomStatus, boolean WifiAvailability, boolean ViewAvailability, boolean SmokingRule, double price) {
		this.roomNumber = roomNumber;
		this.bedType = bedType;
		this.roomType = roomType;
		this.roomStatus = roomStatus;
		this.WifiAvailability = WifiAvailability;
		this.ViewAvailability = ViewAvailability;
		this.SmokingRule = SmokingRule;
		this.price = price;
	}
	public String getRoomNumber() {return roomNumber;}
	public void setRoomNumber(String roomNumber) {this.roomNumber = roomNumber;}
	
	public BedType getBedType() {return bedType;}
	public void setBedType(BedType bedType) {this.bedType = bedType;}
	
	public RoomType getRoomType() {return roomType;}
	public void setRoomType(RoomType roomType) {this.roomType = roomType;}
	
	public RoomStatus getRoomStatus() {return this.roomStatus;}
	public void setRoomStatus(RoomStatus roomStatus) {this.roomStatus = roomStatus;}
	
	public boolean getWifiAvailability() {return WifiAvailability;}
	public void setWifiAvailability(boolean WifiAvailability) {this.WifiAvailability = WifiAvailability;}
	
	public boolean getViewAvailability() {return ViewAvailability;}
	public void setViewAvailability(boolean ViewAvailability) {this.ViewAvailability = ViewAvailability;}
	
	public boolean getSmokingRule() {return SmokingRule;}
	public void setSmokingRule(boolean SmokingRule) {this.SmokingRule = SmokingRule;}
	
	public double getPrice() {return price;}
	public void setPrice(double price) {this.price = price;}
}
