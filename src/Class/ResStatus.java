package Class;
/**
 * The reservation status of a Reservation. The different states of status are:<br>
 * {@link #CONFIRMED} , {@link #WAITLIST} , {@link #CHECKED_IN} , {@link #EXPIRED}
 * 
 * @author Chia Jer Wen
 *
 */
public enum ResStatus {
	/**
	 * The reservation status is CONFIRMED if there is a vacant room of a specific room type which has been declared.
	 */
	CONFIRMED,
	/**
	 * The reservation status is WAITLIST if there is no vacant room of a specific room type which has been declared.
	 */
	WAITLIST,
	/**
	 * The reservation status is CHCECKED_IN, if the guest has successfully checked-in to the hotel.
	 */
	CHECKED_IN,
	/**
	 * The reservation status is EXPIRED, if the guest does not check-in to the hotel on the day of check-in date.
	 */
	EXPIRED
}
