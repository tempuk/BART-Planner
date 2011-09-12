package org.kilon.android.trainride.model.trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.ride.Ride.ExtraArg;
import org.kilon.android.trainride.model.station.Station;

public class TrainTrip extends AbstractBaseTrip implements Trip {

	@SuppressWarnings("unused")
	private static final String TAG = "TrainTrip";
	
	private final List<Ride> rides = new ArrayList<Ride>();

	public TrainTrip(List<Ride> list) {
		setRides(list);
	}
	
	public void setRides(List<Ride> list) {
		if ( null == list )
			return ;
		
		rides.removeAll(rides);
		for ( Ride ride : list ) {
			addRide(ride);
		}
	}

	private void addRide(Ride ride) {
		int numRides = numRides();
		if ( numRides > 0 )
			if ( ! ride.getOrigin().equals( getRide(numRides-1).getDestination() ) )
				throw new IllegalArgumentException("New ride's origin doesn't match to previous ride's destination");
		
		rides.add(ride);
	}

	@Override
	public List<Ride> getRides() {
		return rides;
	}

	@Override
	public Ride getRide(int postion) {
		return getRides().size() > postion ? getRides().get(postion) : null;
	}

	@Override
	public int numRides() {
		return rides.size();
	}
	
	@Override
	public Date getDepartureDate() {
		return getRide(0).getDepartureDate();
	}

	@Override
	public Date getArrivalDate() {
		return getRide(rides.size()-1).getArrivalDate();
	}
	
	@Override
	public void setFare(String fare) {
		putExtra(ExtraArg.FARE, fare);
	}

	@Override
	public String getFare() {
		return getString(ExtraArg.FARE);
	}

	@Override
	public Station getOrigin() {
		return getRide(0).getOrigin();
	}

	@Override
	public Station getDestination() {
		return getRide(getRides().size()-1).getDestination();
	}

	@Override
	public Station getFinalDestination() {
		return getDestination();
	}
}
