
public enum RoomPrice {
	
		SINGLE(),
		DOUBLE(),
		DELUXE(),
		VIP_SUITE();
		
		private double weekday_price;
		private double weekend_price;
		
		public double getWeekdayPrice()
		{
			return this.weekday_price;
		}
		
		public void setWeekdayPrice(double price)
		{
			this.weekday_price = price;
		}
		
		public double getWeekendPrice()
		{
			return this.weekend_price;
		}
		
		public void setWeekendPrice(double price)
		{
			this.weekend_price=price;
		}

}
