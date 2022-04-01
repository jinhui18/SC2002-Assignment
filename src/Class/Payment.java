import java.util.List;
import java.util.Scanner;

public class Payment {
	private static RoomPrice[] roomPrices = RoomPrice.values();
	private static double discountFactor;
	private static double taxRate;
	
	public static double getTaxRate()
	{
		return taxRate;
	}
	
	public static double getDiscountFactor()
	{
		return discountFactor;
	}
	
	public static RoomPrice[] getRoomPrices()
	{
		return roomPrices;
	}
	
	public static void setTaxRate(double rate)
	{
		taxRate=rate;
	}
	
	public static void setDiscountFactor(double factor)
	{
		discountFactor=factor;
	}
	
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
