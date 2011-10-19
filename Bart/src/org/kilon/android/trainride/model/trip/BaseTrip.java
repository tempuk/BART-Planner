package org.kilon.android.trainride.model.trip;

import java.util.Date;

import org.kilon.android.trainride.model.ride.Ride.ExtraArg;
import org.kilon.android.trainride.model.station.Station;

public interface BaseTrip {

	Station getOrigin();
	Station getDestination();
	Station getFinalDestination();
	
	Date getDepartureDate();
	Date getArrivalDate();
	long getLength();
	
	void putExtra(ExtraArg key, Object value);
	Object getExtra(ExtraArg key);
	
}
