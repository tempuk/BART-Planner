package org.kilon.android.trainride.model.ride;

import java.util.Date;

import org.kilon.android.trainride.model.route.RouteManager;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.trip.AbstractBaseTrip;

import android.graphics.Color;

public class TrainRide extends AbstractBaseTrip implements Ride {

	private final Station origin;
	private final Station destination;
	private final Station finalDestination;
	
	public TrainRide(Station origin, Station destination, Station finalDestination) {
		this.destination = destination;
		this.origin = origin;
		this.finalDestination = finalDestination;
	}
	
	public TrainRide(Station origin, Station destination) {
		this(origin, destination, destination);
	}
	
	@Override
	public Station getOrigin() {
		return origin;
	}

	@Override
	public Station getDestination() {
		return destination;
	}
	
	@Override
	public Station getFinalDestination() {
		return finalDestination;
	}

	@Override
	public Date getDepartureDate() {
		return (Date) getExtra(ExtraArg.DEPARTURE_DATE);
	}

	@Override
	public Date getArrivalDate() {
		return (Date) getExtra(ExtraArg.ARRIVAL_DATE);
	}

	@Override
	public void setDates(Date departureDate, Date arrivalDate) {
		if ( null != departureDate )
			putExtra(ExtraArg.DEPARTURE_DATE, departureDate);
		
		if ( null != arrivalDate )
			putExtra(ExtraArg.ARRIVAL_DATE, arrivalDate);

	}

	@Override
	public void setColor(String color) {
		putExtra(ExtraArg.COLOR, color);
	}

	@Override
	public int getColor() {
		String color = getString(ExtraArg.COLOR);
		if ( null == color )  
			return -1;

		return Color.parseColor(color);
	}

	@Override
	public void setLine(String line) {
		putExtra(ExtraArg.LINE, line);
		String color = RouteManager.getInstance().get(line).getColor();
		setColor(color);
	}

	@Override
	public String getLine() {
		return getString(ExtraArg.LINE);
	}

}
