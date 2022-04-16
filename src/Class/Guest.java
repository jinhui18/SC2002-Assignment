package Class;
/**
 * Represents the guest that is going to check in to our hotel.
 * Contains the attributes of guest.
 * @author Cheong Jin Hui
 * @version 1.0
 * @since 2022-04-12
 */
public class Guest {
	
	/**
	* The full name of the guest as per passport.
	*/
	private String name ;
	
	/**
	* The IC number or Passport number of the guest as per passport.
	*/
	private String identity ;
	
	/**
	* The current local address of the guest.
	* If the guest does not have a local address, kindly put the address of our hotel.
	*/
	private String address ;
	
	/**
	* The country of residence of the guest.
	*/
	private String country ;
	
	/**
	* The gender of the guest.
	*/
	private String gender ;
	
	/**
	* The nationality of the guest as per passport.
	*/
	private String nationality ;
	
	/**
	* The contact number of the guest.
	*/
	private String contact ;
	
	/**
	* The credit card details of the guest.
	* The guest who made the reservation have to input their credit card details to allow for credit card payment.
	* If the guest does not want to pay by credit card, kindly still provide the credit card details but opt for cash payment when checking out.
	* For guest that are checking in with the person that book the reservation, they are given the choice to not provide their credit card details.
	*/
	private CreditCardDetails creditCardDetails;
	
	/**
	* The room number of the guest.
	* When guest is created, their room number is automatically initialized to NA because creation of reservation will include creation of guest detail, at that point, the guest does not have a room number because they have not check in yet.
	* Only when a guest checks in, their room number will be updated.
	*/
	private String roomNumber;
	
	/**
	* Class constructor of Guest.
	*/
	//Constructor
	public Guest() {
		creditCardDetails = new CreditCardDetails();
		return;
	}
	
	/**
	* Class constructor specifying the value of Guest's attributes.
	* @param name This Guest's name.
	* @param identity This Guest's identity.
	* @param address This Guest's address.
	* @param country This Guest's country.
	* @param gender This Guest's gender.
	* @param nationality This Guest's nationality.
	* @param contact This Guest's contact.
	* @param creditCardDetails This Guest's creditCardDetails.
	* @param roomNumber This Guest's roomNumber.
	*/
	//Constructor
	public Guest(String name, String identity, String address, String country, String gender, String nationality, String contact, 
		CreditCardDetails creditCardDetails , String roomNumber) {
		this.creditCardDetails = new CreditCardDetails();
		this.name = name;
		this.identity = identity;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.nationality = nationality;
		this.contact = contact;
		this.creditCardDetails = creditCardDetails;
		this.roomNumber = roomNumber;
	}
	

	//Getter and setter for Name
	/**
	* Gets the full name of this Guest as per passport.
	* @return this Guest's name.
	*/
	public String getName() {
		return name;
	}
	
	/**
	* Sets the full name of this Guest as per passport.
	* @param name This Guest's name.
	*/
	public void setName(String name) {
		this.name = name;
	}

	//Getter and setter for Identity/ Passport Number
	/**
	* Gets the IC/Passport number of this Guest as per passport.
	* @return this Guest's IC/Passport number.
	*/
	public String getIdentity() {
		return identity;
	}
	/**
	* Sets the IC/Passport number of this Guest as per passport.
	* @param identity This Guest's IC/Passport number.
	*/
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	//Getter and setter for Address
	/**
	* Gets the local address of the guest.
	* @return this Guest's address.
	*/
	public String getAddress() {
		return address;
	}
	/**
	* Sets the local address of the guest.
	* @param address This Guest's address.
	*/
	public void setAddress(String address) {
		this.address = address;
	}

	//Getter and setter for Gender
	/**
	* Gets the gender of this Guest.
	* @return this Guest's gender.
	*/
	public String getGender() {
		return gender;
	}
	/**
	* Sets the gender of this Guest.
	* @param gender This Guest's gender.
	*/
	public void setGender(String gender) {
		this.gender = gender;
	}

	//Getter and setter for Country
	/**
	* Gets the country of residence of this Guest.
	* @return this Guest's country of residence.
	*/
	public String getCountry() {
		return country;
	}
	/**
	* Sets the country of residence of this Guest.
	* @param country This Guest's country of residence.
	*/
	public void setCountry(String country) {
		this.country = country;
	}

	//Getter and setter for Nationality
	/**
	* Gets the nationality of this Guest.
	* @return this Guest's nationality.
	*/
	public String getNationality() {
		return nationality;
	}
	/**
	* Sets the nationality of this Guest.
	* @param nationality This Guest's nationality.
	*/
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	//Getter and setter for Contact
	/**
	* Gets the contact number of this Guest.
	* @return this Guest's contact number.
	*/
	public String getContact() {
		return contact;
	}
	/**
	* Sets the contact number of this Guest.
	* @param contact This Guest's contact number.
	*/
	public void setContact(String contact) {
		this.contact = contact;
	}

	//Getter and setter for CC detail
	/**
	* Gets the credit card details of this Guest.
	* creditCardDetails is a class that contains credit card number, expiry date and cvv number.
	* @return this Guest's credit card details.
	*/
	public CreditCardDetails getCreditCardDetails() {
		return creditCardDetails;
	}
	/**
	* Sets the creditCardDetails of this Guest.
	* creditCardDetails is a class that contains credit card number, expiry date and cvv number. 
	* @param creditCardDetails This Guest's creditCardDetails.
	*/
	public void setCreditCardDetails(CreditCardDetails creditCardDetails) {
		this.creditCardDetails = creditCardDetails;
	}
	
	//Getter and setter for Room Number
	/**
	* Gets the room number of this Guest.
	* Returns NA if guest is not checked in/ has checked out.
	* @return this Guest's room number.
	*/
	public String getRoomNumber() {
		return roomNumber;
	}
	/**
	* Sets the room number of this Guest.
	* Set as NA if guest has not checked in
	* @param roomNumber This Guest's room number.
	*/
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
}

