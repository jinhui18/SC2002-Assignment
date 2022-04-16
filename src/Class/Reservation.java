package Class;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;

import Class.Room.BedType;
import Class.Room.RoomStatus;
import Class.Room.RoomType;
import Controller.GuestController;
import Controller.ReservationController;

/**
 * Represents a reservation that is created to reserve for a room.
 * @author Chia Jer Wen
 *
 */
public final class Reservation {
	
	//ATTRIBUTES
	/**
	 * A computer-generated unique code that is created when Reservation is made.
	 */
	private String reservationCode;
	/**
	 * An object of Class {@code Guest} who makes this Reservation
	 * <p>
	 * The guest details includes information such as: <br>
	 * Name, Identity, Address, Name of Home Country, Nationality, Contact of this Guest. <br>
	 * This also contains Credit Card Details if applicable.<br>
	 * This also contains roomNumber after check-in.
	 */
	private Guest guestDetails;
	/**
	 * An object of Class {@code Room} which is reserved by this Reservation
	 * <p>
	 * The room details includes information such as: <br>
	 * RoomNumber, Room Type, Bed Type, Room Status, Availability of Wifi and View, and Smoking Rule 
	 * of the room.
	 */
	private Room room;
	/**
	 * The declared check-in date of this Reservation
	 */
	private LocalDate dateIn;
	/**
	 * The declared check-out date of this Reservation
	 */
	private LocalDate dateOut;
	/**
	 * The total number of adults declared in this Reservation
	 */
	private int numAdults;
	/**
	 * The total number of children declared in this Reservation
	 */
	private int numChildren;
	/**
	 * The reservation status of this Reservation. The reservation status is:<br>
	 * WAITLIST if there is no vacant rooms of a specific room type: SINGLE, DOUBLE, DELUXE, VIP_SUITE <br>
	 * CONFIRMED if there is vacant room of a specific room type.<br>
	 * CHECKED-IN if the guest of this Reservation had CONFIRMED reservation, and has successfully checked-in.<br>
	 * EXPIRED if the guest of this Reservation did not check-in after 4pm on the check-in date.
	 */
	private ResStatus status;
	/**
	 * The number of staying nights declared in this Reservation <br>
	 * It is calculated according to the inclusive check-in date and exclusive check-out date.
	 */
	private long numDaysBetween;
	/**
	 * The room bill of this Reservation calculated only when this Reservation is created or 
	 * new room or check-in or check-out date is updated.
	 */
	private double bill;
	
	//Constructor
	/**
	 * Creates a new Reservation with the given attributes:<br>
	 * Reservation Code, {@link #guestDetails}, {@link #room} , Check-in date, Check-out date, Number of adults, Number of children, and {@link #status Reservation Status}
	 * <p>
	 * Bill is auto-calculated according to the room price and the check-in and check-out date.
	 * @param reservationCode This Reservation's Reservation Code
	 * @param guestDetails This Reservation's Guest Details
	 * @param room This Reservation's Room Details
	 * @param dateIn This Reservation's Check-in date
	 * @param dateOut This Reservation's Check-out date
	 * @param numAdults This Reservation's Number of Adults
	 * @param numChildren This Reservation's Number of Children
	 * @param status This Reservation's Reservation Status
	 */
	public Reservation(String reservationCode, Guest guestDetails, Room room, LocalDate dateIn, LocalDate dateOut, int numAdults, int numChildren, ResStatus status)
	{
		//Constructor: consider every information is given upon Reservation
		//Including: check-in and check-out day.
		this.reservationCode=reservationCode;
		this.guestDetails=guestDetails;
		this.room=room;
		this.dateIn=dateIn;
		this.dateOut=dateOut;
		this.numAdults=numAdults;
		this.numChildren=numChildren;
		this.status=status;
		//numDaysBetween
		this.numDaysBetween=calNumDays();
		this.bill=calRoomBill();
	}
	/**
	 * Creates a new Reservation with the given attributes:<br>
	 * Reservation Code, {@link #guestDetails}, {@link #room} , Check-in date, Check-out date, Number of adults, Number of children, and {@link #status Reservation Status}
	 * @param reservationCode This Reservation's Reservation Code
	 * @param guestDetails This Reservation's Guest Details
	 * @param room This Reservation's Room Details
	 * @param dateIn This Reservation's Check-in date
	 * @param dateOut This Reservation's Check-out date
	 * @param numAdults This Reservation's Number of Adults
	 * @param numChildren This Reservation's Number of Children
	 * @param status This Reservation's Reservation Status
	 * @param bill This Reservation's Room Bill
	 */
	public Reservation(String reservationCode, Guest guestDetails, Room room, LocalDate dateIn, LocalDate dateOut, int numAdults, int numChildren, ResStatus status,double bill)
	{
		//Constructor: consider every information is given upon Reservation
		//Including: check-in and check-out day.
		this.reservationCode=reservationCode;
		this.guestDetails=guestDetails;
		this.room=room;
		this.dateIn=dateIn;
		this.dateOut=dateOut;
		this.numAdults=numAdults;
		this.numChildren=numChildren;
		this.status=status;
		//numDaysBetween
		this.numDaysBetween=calNumDays();
		this.bill=bill;
	}
	
	//Accessor
	/**
	 * Gets the Reservation Code associated with this Reservation
	 * @return this Reservation's Reservation Code.
	 */
	public String getReservationCode() {return reservationCode;}
	/**
	 * Gets the {@link #guestDetails} of the Guest who made this Reservation
	 * @return this Reservation's Guest Details.
	 */
	public Guest getGuestDetails() {return guestDetails;}
	/**
	 * Gets the {@link #room Room Details} of the Room reserved in this Reservation
	 * @return this Reservation's Room Details.
	 */
	public Room getRoom() {return room;}
	/**
	 * Gets the check-in date which is declared in this Reservation
	 * @return this Reservation's check-in Date
	 */
	public LocalDate getDateIn() {return dateIn;}
	/**
	 * Gets the check-out date which is declared in this Reservation
	 * @return this Reservation's check-out date
	 */
	public LocalDate getDateOut() {return dateOut;}
	/**
	 * Gets the number of adults which is declared in this Reservation
	 * @return this Reservation's number of adults
	 */
	public int getNumAdults() {return numAdults;}
	/**
	 * Gets the number of children which is declared in this Reservation
	 * @return this Reservation's number of children
	 */
	public int getNumChildren() {return numChildren;}
	/**
	 * Gets the {@link #status Reservation Status} of this Reservation
	 * @return this Reservation's reservation status
	 */
	public ResStatus getStatus() {return status;}
	/**
	 * Gets the number of days between check-in date which is inclusive and check-out date which is exclusive of this Reservation
	 * @return this Reservation's number of nights between check-in date and check-out date
	 */
	public long getNumDaysBetween() {return numDaysBetween;}
	/**
	 * Gets the room bill of this Reservation which is generated while creation or update of this Reservation
	 * @return this Reservation's room bill
	 */
	public double getBill() {return bill;}
	
	//Mutator
	/**
	 * Changes the Reservation Code of this Reservation
	 * @param reservationCode the new reservationCode of this Reservation
	 */
	public void setReservationCode(String reservationCode) {this.reservationCode=reservationCode;}
	/**
	 * Changes the {@link #guestDetails} of this Reservation<br>
	 * @param guestDetails the new guestDetails of this Reservation
	 */
	public void setGuestDetails(Guest guestDetails) {this.guestDetails=guestDetails;};
	/**
	 * Changes the {@link #room} of this Reservation<br>
	 * @param room the new room of this Reservation
	 */
	public void setRoom (Room room) {this.room=room;};
	/**
	 * Changes the check-in date of this Reservation
	 * @param dateIn the new check-in date of this Reservation, must be before dateOut
	 */
	public void setDateIn(LocalDate dateIn) {this.dateIn=dateIn; this.numDaysBetween=calNumDays();}
	/**
	 * Changes the check-out date of this Reservation
	 * @param dateOut the new check-out date of this Reservation, must be after dateIn
	 */
	public void setDateOut(LocalDate dateOut) {this.dateOut=dateOut;this.numDaysBetween=calNumDays();} //DateIn & DateOut will change Bill
	/**
	 * Changes the number of adults of this Reservation
	 * @param numAdults the new number of adults of this Reservation, must not be a negative integer 
	 */
	public void setNumAdults(int numAdults) {this.numAdults=numAdults;}
	/**
	 * Changes the number of children of this Reservation
	 * @param numChildren the new number of adults of this Reservation, must not be a negative integer
	 */
	public void setNumChildren(int numChildren) {this.numChildren=numChildren;}
	/**
	 * Changes the {@link #status Reservation Status} of this Reservation.
	 * @param status Status of reservation.
	 */
	public void setStatus (ResStatus status) {this.status=status;}
	/**
	 * Changes the number of days between the check-in date which is inclusive, and check-out date which is exclusive.
	 * @param numDaysBetween the new number of days between check-in date and check-out date
	 */
	public void setNumDaysBetween(long numDaysBetween){this.numDaysBetween=numDaysBetween;}
	/**
	 * Changes the amount of bill in this Reservation
	 * @param bill the new amount of bill of this Reservation
	 */
	public void setBill(double bill){this.bill = bill;}
	
	/**
	 * Test whether this Reservation is expired or not
	 * @return {@code true} if this Reservation has EXPIRED, or else return {@code false}
	 */
	public boolean isExpired() {if (status==ResStatus.EXPIRED) return true; else return false;}
	/**
	 * Test whether this Reservation is confirmed or not
	 * @return {@code true} if this Reservation is CONFIRMED, or else return {@code false}
	 */
	public boolean isConfirmed() {if (status==ResStatus.CONFIRMED) return true; else return false;}
	/**
	 * Test whether this Reservation is in waitlist or not
	 * @return {@code true} if this Reservation is in WAITLIST, or else return {@code false}
	 */
	public boolean isWaitlist() {if (status==ResStatus.WAITLIST) return true; else return false;}
	/**
	 * Test whether this Reservation is checked-in or not
	 * @return {@code true} if this Reservation is in CHECKED_IN, or else return {@code false}
	 */
	public boolean isCheckedIn() {if (status==ResStatus.CHECKED_IN) return true; else return false;}
	
	
	//Support (private methods)
	/**
	 * Calculate the number of days between inclusive check-in date and exclusive check-out date
	 * @return the number of days of staying
	 */
	private long calNumDays()
	{
		long daysBetween=ChronoUnit.DAYS.between(getDateIn(),getDateOut());
		return daysBetween;
	}
	/**
	 * Calculate the room bill according to the room price when Reservation is created or updated and also the date of check-in and check-out.
	 * @return the new calculated room bill of this Reservation
	 */
	private double calRoomBill()
	{
		this.bill=0;
		if(dateIn == null || dateOut == null) return 0;
		RoomType rt = room.getRoomType();
		double weekdayPrice = Payment.getRoomPriceByType(String.valueOf(rt), "Weekday");
		double weekendPrice = Payment.getRoomPriceByType(String.valueOf(rt),"Weekend");
		for (LocalDate start = dateIn; start.isBefore(dateOut);start=start.plusDays(1))
		{
			int day = start.get(ChronoField.DAY_OF_WEEK);
			if(day==6 || day == 7)
			{
				this.bill+=weekendPrice;
			}
			else this.bill+=weekdayPrice;
		}
		return bill;
	}
	
	/**
	 * call the function {@code calRoomBill()} to calculate the room bill of this Reservation<br>
	 * then update the new room bill to this Reservation.
	 */
	public void autoRoomBill()
	{
	  this.bill = this.calRoomBill();
	}

}
