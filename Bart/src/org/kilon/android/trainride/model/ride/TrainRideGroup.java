package org.kilon.android.trainride.model.ride;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.trip.AbstractBaseTrip;

public class TrainRideGroup extends AbstractBaseTrip implements RideGroup {


	@SuppressWarnings("unused")
	private static final String TAG = "TrainRideGroup";

	private final List<Ride> rides = new ArrayList<Ride>();

	public TrainRideGroup() {
		this(null);
	}

	public TrainRideGroup(Ride ride) {
		if ( null != ride )
			addRide(ride);
	}

	@Override
	public Station getOrigin() {
		return rides.get(0).getOrigin();
	}

	@Override
	public Station getDestination() {
		return rides.get(0).getDestination();
	}

	@Override
	public void addRide(Ride ride) {
		rides.add(ride);
	}

	@Override
	public Ride getRide(int location) {
		return rides.get(location);
	}

	@Override
	public int size() {
		return rides.size();
	}

	@Override
	public Set<Integer> getColors() {
		Set<Integer> colors = new HashSet<Integer>();
		for ( Ride ride: rides ) {
			colors.add(ride.getColor());
		}
		return colors;
	}

	@Override
	public Date getDepartureDate() {
		if ( rides.size() == 0 )
			return null;

		return rides.get(0).getDepartureDate();
	}

	@Override
	public Date getArrivalDate() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDates(Date departureDate, Date arrivalDate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLine(String line) {
		throw new UnsupportedOperationException();
		//		putExtra(ExtraArg.LINE, line);
		//		String color = RouteManager.getInstance().get(line).getColor();
		//		setColor(color);
	}

	@Override
	public void setColor(String color) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getColor() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLine() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Station getFinalDestination() {
		throw new UnsupportedOperationException();
	}
}
