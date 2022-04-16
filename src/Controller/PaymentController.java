package Controller;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import Class.Payment;
import Class.RoomPrice;
import UI.PaymentUI;

/**
 * Payment Controller houses the various methods that the staff can use with regards to the updating of room prices, discount and tax rate.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class PaymentController {
	/**
	* databaseLocationPay is the location of the payment data base where we will be storing all the room prices of each type of room on weekdays and on weekends, storing the disocunt as well as tax rate.
	*/
	private static final String databaseLocationPay = "src\\database\\PaymentDB.txt";
	/**
	* The separator is used to separate the values of the attributes stored in our database, this helps us to extract and store the data properly.
	*/
	private static final String SEPARATOR = "|";
	
	/**
	* This method initialize all the values of the room prices of the each type of room on weekday and weekend, the discount as well as tax rate.
	* We extract the values from the data in our payment database as the value of each attribute is separated by '|'.
	* It set those values respectively using the setter method.
	 * @throws IOException if stream to file cannot be read or to closed
	*/
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
	/**
	* This method saves all the values of the room prices of the each type of room on weekday and weekend, the discount as well as tax rate.
	* We extract the values from the prices of each room type on weekday and on weekend, the discount and tax rate in array list guestList and separate the values by '|', our separator.
	* We then combine all the values into a string and add to a list of string containing the values of the attributes.
	 * @throws IOException if stream to file cannot be written to or closed.
	*/
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
		stringsToFile(databaseLocationPay, st.toString());
	}
	/**
	* This method writes the list of values of the attributes of our payment database.
	* @param filename The location of our payment database.
	* @return data A string that is read from our database, it contains information of the attributes of payment.
	* @throws IOException if stream to file cannot be read or to closed
	*/
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
	/**
	* This method writes the list of values of the attributes of our payment database.
	* @param filename The location of our payment database.
	* @param data A list containing strings that contains the values of payment's attribute. Each attribute is separated by '|'.
	* @throws IOException if stream to file cannot be written to or closed.
	*/
	private static void stringsToFile(String filename,String data) throws IOException
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
	
	/**
	* This method allows the staff to set the percentage of discount the hotel wants to implement.
	* @param d This is the percentage of the discount the hotel wants to set
	*/
	public static void adjustDiscountPrice(double d)
	{
		Payment.setDiscountFactor(d);
	}
	/**
	* This method allows the staff to print the current percentage of discount of the hotel.
	*/
	public static void printDiscountPrice()
	{
		double discountRate = Payment.getDiscountFactor();
		System.out.printf("The hotel current discount is %.0f percent off on all room prices\n", discountRate*100);
	}
	/**
	* This method allows the staff to set the percentage of tax the hotel wants to implement.
	* @param d This is the percentage of the tax the hotel wants to set
	*/
	public static void adjustTaxRate(double d)
	{
		Payment.setTaxRate(d);
	}
	/**
	* This method allows the staff to print the current percentage of tax of the hotel.
	*/
	public static void printTaxRate()
	{
		double taxRate = Payment.getTaxRate();
		System.out.printf("The hotel current tax rate is %.0f percent on total bill\n", taxRate*100);

	}

	/**
	* Sets the prices of a particular room type on weekday and on weekend.
	* @param weekday Price of that particular room on a weekday.
	* @param weekend Price of that particular room on a weekend.
	* @param s Type of the room.
	*/
	public static void adjustRoomPrice (double weekday,double weekend, String s)
	{
		Payment.setRoomPriceByType(weekday,weekend,s);
	}
	/**
	* Display the prices of every room type on weekday and on weekend.
	* This allows the staff to have an overview of the cost of each room.
	*/
	public static void displayAllPrice()
	{
		Payment.displayAllRoomPrice();
	}
	/**
	* This method calls the payment user interface which will display all the options that the staff can choose with regards to adjusting values of the room prices of the each type of room on weekday and weekend, the discount as well as tax rate.
	*/	
	public static void displayPage()
	{
		PaymentUI.displayPage();
	}
	/**
	* Gets the percentage of the discount rate of the hotel from payment class.
	* @return the hotel's discount rate.
	*/
	public static double getDiscountRate() {
		return Payment.getDiscountFactor();
	}
	/**
	* Gets the percentage of the tax rate of the hotel from payment class.
	* @return the hotel's tax rate.
	*/
	public static double getTaxRate() {
		return Payment.getTaxRate();
	}

}
