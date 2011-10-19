package org.kilon.android.trainride.test.model.trip;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.ride.TrainRide;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.model.trip.TrainTrip;
import org.kilon.android.trainride.model.trip.Trip;
import org.kilon.android.trainride.test.ActivityTestCase;
import org.kilon.android.trainride.util.DateUtils;

public class TripsTestCase extends ActivityTestCase {
	
	private Station origin;
	private Station destination;
	
	private static final String TIME_FORMAT = "hh:mm a";

	public TripsTestCase() {
		super();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		origin = StationManager.get("WCRK");
		assertNotNull(origin);
		destination = StationManager.get("ORIN");
		assertNotNull(destination);
	}
	
	public void testTrip() {
		List<Ride> rides = new ArrayList<Ride>();
		rides.add(new TrainRide(origin, destination));
		Trip trip = new TrainTrip(rides);
		
		assertSame(origin, trip.getOrigin());
		assertSame(destination, trip.getDestination());
		assertSame(destination, trip.getFinalDestination());
	}
	
	public void testTripFare() {
		
		Trip trip = new TrainTrip(null);
		
		assertNull(trip.getFare());
		
		String fare = "3.3";
		
		trip.setFare(fare);
		assertEquals(fare, trip.getFare());
		
	}
	
	
	public void testTripRides() throws ParseException {
		
		TrainTrip trip = new TrainTrip(null);
		assertEquals(0, trip.numRides());
		
		List<Ride> rides = new ArrayList<Ride>();
		
		Ride ride;
		
		ride = new TrainRide(origin, destination);
		ride.setDates(DateUtils.parseDate(TIME_FORMAT, "10:01 AM"), DateUtils.parseDate(TIME_FORMAT, "10:11 AM"));
		rides.add(ride);
		trip.setRides(rides);
		assertEquals(1, trip.numRides());
		
		ride = new TrainRide(destination, origin);
		ride.setDates(DateUtils.parseDate(TIME_FORMAT, "10:12 AM"), DateUtils.parseDate(TIME_FORMAT, "10:21 AM"));
		rides.add(ride);
		
		ride = new TrainRide(origin, destination);
		ride.setDates(DateUtils.parseDate(TIME_FORMAT, "10:22 AM"), DateUtils.parseDate(TIME_FORMAT, "10:28 AM"));
		rides.add(ride);
		
		trip.setRides(rides);
		assertEquals(3, trip.numRides());
		
		assertEquals(trip.getDepartureDate(), trip.getRide(0).getDepartureDate());
		assertEquals(trip.getArrivalDate(), trip.getRide(trip.getRides().size()-1).getArrivalDate());
		
		assertSame(trip.getOrigin(), rides.get(0).getOrigin());
		assertSame(trip.getDestination(), rides.get(rides.size()-1).getDestination());
		
	}

}
