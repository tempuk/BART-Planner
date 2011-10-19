package org.kilon.android.trainride.model.trip;

import java.util.List;

import org.kilon.android.trainride.model.ride.Ride;

public interface Trip extends BaseTrip{

	Ride getRide(int postion);
	List<Ride> getRides();
	int numRides();
	
	String getFare();
	void setFare(String fare);
}
