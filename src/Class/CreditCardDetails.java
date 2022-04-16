package Class;

/**
 * Represents the credit card details that the guest hold.
 * The details can be NA because not all guest have to provide their credit card detail.
 * Only the guest who made the reservation have to key in this detail, and the other guest checking in can choose not to input their credit card details.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class CreditCardDetails {
	
	
	/**
	* The name on the credit card provided.
	* Name on the credit card can be NA, if guest choose not to enter credit card details.
	*/
	private String name;
	/**
	* The card number of the credit card.
	* Card number must be 16 digits long, according to our research,most credit card's card number is 16 digits long.
	* Card number can be NA, if guest choose not to enter credit card details.
	*/
	private String cardNumber;
	/**
	* The expiry date of the credit card.
	* The format of this is in MM/YY, the month and the year respectively.
	* Expiry Date can be NA, if guest choose not to enter credit card details.
	*/
	private String expiryDate;
	/**
	* The cvv number of the credit card.
	* The cvv number is 3 digits long as according to our research,most credit card's cvv number is 3 digits long.
	* CVV number can be NA, if guest choose not to enter credit card details.
	*/
	private String cvvNumber;
	/**
	* The billing address associated with the guest's credit card.
	* If the guest opts to pay with credit card, the bill will be credit to guest's credit card and eventually be sent to the billing address of the guest.
	*/
	private String billingAddress;
	
	/**
	* CreditCardDetails constructor of Credit Card Details.
	*/
	//Constructor 
	public CreditCardDetails() {
		
	}
	
	/**
	* CreditCardDetails constructor specifying the value of it's attributes.
	* @param name This Guest's name on the credit card.
	* @param cardNumber This Guest's credit card number.
	* @param expiryDate This Guest's credit card expiry date.
	* @param cvvNumber This Guest's credit card cvv number.
	* @param billingAddress This Guest's billing address.
	*/
	//Second constructor
	public CreditCardDetails(String name, String cardNumber, String expiryDate, String cvvNumber, String billingAddress) {
		this.name = name;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvvNumber = cvvNumber;
		this.billingAddress = billingAddress;
		
	}
	/**
	* Gets the name of this Guest on the credit card if provided, if not will return NA.
	* Extracted when guest opts to pay using credit card.
	* @return this Guest's name on the credit card.
	*/
	public String getName() {
		return name;
	}
	
	/**
	* Sets the credit card name of this Guest if provided, if not set as NA.
	* @param name This Guest's name on the credit card.
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* Gets the credit card number of this Guest if provided, if not will return NA.
	* Extracted when guest opts to pay using credit card.
	* @return this Guest's credit card number.
	*/
	//Getter and setter for CC Number
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	* Sets the credit card number of this Guest if provided, if not set as NA.
	* @param cardNumber This Guest's credit card's number.
	*/
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	//Getter and setter for CC Expiry Date
	/**
	* Gets the credit card expiry date of this Guest if provided, if not will return NA.
	* Extracted when guest opts to pay using credit card.
	* @return this Guest's credit card's expiry date.
	*/
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	* Sets the expiry date of the credit card of this Guest if provided, if not set as NA.
	* @param expiryDate This Guest's credit card's expiry date.
	*/
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	//Getter and setter for CC CVV Number
	/**
	* Gets the credit card cvv number of this Guest if provided, if not will return NA.
	* Extracted when guest opts to pay using credit card.
	* @return this Guest's credit card's cvv number.
	*/
	public String getCvvNumber() {
		return cvvNumber;
	}
	/**
	* Sets the cvv number of the credit card of this Guest if provided, if not set as NA.
	* @param cvvNumber This Guest's credit card's cvv number.
	*/
	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

	/**
	* Gets the billing address of the  Guest if provided, if not will return NA.
	* Extracted when guest opts to pay using credit card.
	* @return this Guest's credit card's billing address.
	*/
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	* Sets the billing address of the credit card of this Guest if provided, if not set as NA.
	* @param billingAddress This Guest's credit card's billing address.
	*/
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	
}


