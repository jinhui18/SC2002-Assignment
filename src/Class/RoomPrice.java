package Class;

/**
 * Represents a room's prices in the hotel.
 * One room can have different room type and prices in the hotel
 * @author Loke Kah Hou
 * @version 1.1
 * @since 2022-04-12
 */

public enum RoomPrice {
	
	/**
	* The room type that a room can be
	*/
	/**
	 * Single Room
	 */
    SINGLE(),
    /**
	 * Double Room
	 */
    DOUBLE(),
    /**
	 * Deluxe Room
	 */
    DELUXE(),
    /**
	 * VIP Suite Room
	 */
    VIP_SUITE();
	
	/**
	* The price of the room on weekdays
	*/
	private double weekday_price;
	
	/**
	* The price of the room on weekends
	*/
	private double weekend_price;
	
	/**
	* Gets the Room's weekday price
	* @return this Room's weekday price
	*/
	public double getWeekdayPrice()
	{
		return this.weekday_price;
	}
	
	/**
	* Changes the Room's weekday price
	* @param price This Room's new weekday price
	*/
	public void setWeekdayPrice(double price)
	{
		this.weekday_price = price;
	}
	
	/**
	* Gets the Room's weekend price
	* @return this Room's weekend price
	*/
	public double getWeekendPrice()
	{
		return this.weekend_price;
	}
	
	/**
	* Changes the Room's weekday price
	* @param price This Room's new weekday price
	*/
	public void setWeekendPrice(double price)
	{
		this.weekend_price=price;
	}
}
