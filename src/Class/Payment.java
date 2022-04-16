package Class;

import java.util.List;
import java.util.Scanner;

/**
 * This class controls the room prices of our hotel, the discount rate that can be applied if any and the tax (inclusive of goods and service tax as well as service charge).
 * Hotel room price differs with the room type as well as the day of the week, weekend prices are more expensive than weekday prices the guest that is going to check in to our hotel.
 * For discount rate, if hotel wants to offer discount they can add it in, discount is accounted with the total bill (total bill includes room bill and food bill).
 * For tax rate, it will be applied to the total bill after discount if applied if any.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */

public class Payment {
	/**
	* This list contains the price of each room type on weekdays as well as on weekends.
	*/
	private static RoomPrice[] roomPrices = RoomPrice.values();
	/**
	* The percentage of the discount, if any.
	* If there is no discount, it will be zero.
	*/
	private static double discountFactor;
	/**
	* The percentage of the tax.
	* Default tax is at 7% (0.07), we based it on current gst rate in Singapore.
	*/
	private static double taxRate;
	/**
	* Gets the percentage of the tax rate of the hotel.
	* @return the hotel's tax rate.
	*/
	public static double getTaxRate()
	{
		return taxRate;
	}
	/**
	* Sets the percentage of the tax rate of the hotel.
	* @param rate The percentage of the tax that hotel is charging the customer.
	*/
	public static void setTaxRate(double rate)
	{
		taxRate=rate;
	}	
	/**
	* Gets the percentage of the discount rate of the hotel.
	* @return the hotel's discount rate.
	*/
	public static double getDiscountFactor()
	{
		return discountFactor;
	}
	/**
	* Sets the percentage of the discount rate of the hotel.
	* @param factor The percentage of the discount that hotel is charging the customer.
	*/
	public static void setDiscountFactor(double factor)
	{
		discountFactor=factor;
	}	
	
	/**
	* Gets the list of room price of each room type on weekends and weekdays.
	* @return The hotel's room prices.
	*/
	public static RoomPrice[] getRoomPrices()
	{
		return roomPrices;
	}
	
	/**
	* Sets the list of room price of each room type on weekends and weekdays.
	* @param newRoomPrices List containing the room prices of each room type on weekends and weekdays.
	*/
	public static void setRoomPrice(List <Double> newRoomPrices)
	{
        int i=0;
        for (RoomPrice price : roomPrices)
        {
        	price.setWeekdayPrice(newRoomPrices.get(i));
        	i++;
        	price.setWeekendPrice(newRoomPrices.get(i));
        	i++;
        }
	}
	
	/**
	* Sets the prices of a particular room type on weekday and on weekend.
	* @param weekday Price of that particular room on a weekday.
	* @param weekend Price of that particular room on a weekend.
	* @param s Type of the room.
	*/
	public static void setRoomPriceByType(double weekday,double weekend, String s)
	{
		for (RoomPrice price : roomPrices)
		{
			if(s.equalsIgnoreCase(price.name()))
			{
				price.setWeekdayPrice(weekday);
				price.setWeekendPrice(weekend);
			}
		}
	}
	
	/**
	* Gets the prices of a particular room type on weekday or on weekend, depending on what the staff wants.
	* @param s Type of the room.
	* @param day Either weekday or weekend.
	* @return Weekend price if day is falls on a weekend, Weekday price if day is falls on a weekday
	*/
	public static double getRoomPriceByType(String s, String day)
	{
		for(RoomPrice price : roomPrices)
		{
			if (s.equalsIgnoreCase(price.name()))
			{
				if(day.equalsIgnoreCase("Weekday")) return price.getWeekdayPrice();
				else if(day.equalsIgnoreCase("Weekend")) return price.getWeekendPrice();
			}
		}
		return -1;
	}
	
	/**
	* Display the prices of every room type on weekday and on weekend.
	* This allows the staff to have an overview of the cost of each room.
	*/
	public static void displayAllRoomPrice()
	{
		for(RoomPrice price : roomPrices)
		{
			System.out.printf("-----%s-----\n",price.name());
			System.out.printf("Weekday: %.2f\n",price.getWeekdayPrice());
			System.out.printf("Weekend: %.2f\n",price.getWeekendPrice());
		}
	}

}
