package org.kilon.android.trainride.model.ride;

import java.util.Date;

import org.kilon.android.trainride.model.trip.BaseTrip;

public interface Ride extends BaseTrip {
	
	public enum ExtraArg {
		COLOR,
		LINE,
		DEPARTURE_DATE,
		ARRIVAL_DATE,
		FARE,
		BIKE_ALLOWED, 
		LENGTH
	}
	
	void setDates(Date departureDate, Date arrivalDate);
	
	void setColor(String color);
	int getColor();

	void setLine(String line);
	String getLine();
	
	
}
