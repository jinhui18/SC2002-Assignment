import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PaymentController {
	private static final String databaseLocationPay = "C:\\Users\\User\\OneDrive\\Desktop\\PaymentDB.txt";
	private static final String SEPARATOR = "|";
	
	public static void init() throws IOException
	{
		String str = fileToStrings(databaseLocationPay);
		if (str.isEmpty()) return;
		StringTokenizer star = new StringTokenizer (str, SEPARATOR);
		String discountFactor_string = star.nextToken().trim();
		String taxRate_string = star.nextToken().trim();
		List <String> roomPrices_string = new ArrayList();
		while(star.hasMoreTokens()) 
		{
			roomPrices_string.add(star.nextToken().trim());
			roomPrices_string.add(star.nextToken().trim());
		}
		double discountFactor = Double.parseDouble(discountFactor_string);
		double taxRate = Double.parseDouble(taxRate_string);
		List <Double> roomPrices = new ArrayList();
		for(int i = 0; i<roomPrices_string.size();i++)
		{
			roomPrices.add(Double.parseDouble(roomPrices_string.get(i)));
		}
		Payment.setDiscountFactor(discountFactor);
		Payment.setRoomPrice(roomPrices);
		Payment.setTaxRate(taxRate);
		
		
	}
	
	public static void save() throws IOException
	{
		StringBuilder st = new StringBuilder();
		st.append(String.valueOf(Payment.getDiscountFactor()).trim());
		st.append(SEPARATOR);
		st.append(String.valueOf(Payment.getTaxRate()).trim());
		st.append(SEPARATOR);
		RoomPrice[] roomPrices = Payment.getRoomPrices();
		for(RoomPrice price : roomPrices)
		{
			st.append(String.valueOf(price.getWeekdayPrice()).trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(price.getWeekendPrice()).trim());
			st.append(SEPARATOR);
		}
		StringsToFile(databaseLocationPay, st.toString());
	}
	
	private static String fileToStrings(String file) throws IOException
	{
		//Convert file to array of strings
		String data = "";
		Scanner sc = new Scanner(new FileInputStream(file));
		try
		{
			while(sc.hasNextLine()) {data=(sc.nextLine());}
		}
		finally
		{
			sc.close();
		}
		return data;
	}
	
	private static void StringsToFile(String filename,String data) throws IOException
	{
		PrintWriter out = new PrintWriter (new FileWriter(filename));
		try
		{

				out.println((String) data);
		}
		finally
		{
			out.close();
		}
	}
	

	public static void adjustDiscountPrice(double d)
	{
		Payment.setDiscountFactor(d);
	}
	
	public static void adjustTaxRate(double d)
	{
		Payment.setTaxRate(d);
	}
	
	public static void adjustRoomPrice (double weekday,double weekend, String s)
	{
		Payment.setRoomPriceByType(weekday,weekend,s);
	}
	
	public static void displayAllPrice()
	{
		Payment.displayAllRoomPrice();
	}
	public static void displayPage()
	{
		PaymentUI.displayPage();
	}

}
