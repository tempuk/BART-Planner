package org.kilon.android.trainride.test.model.ride;

import java.util.Calendar;
import java.util.Date;

import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.ride.TrainRide;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.test.ActivityTestCase;

public class RideTestCase extends ActivityTestCase {

	private Station origin;
	private Station destination;

	public RideTestCase() {
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
	
	
	public void testRide() {
		Ride ride = new TrainRide(origin, destination);
		assertNotNull(ride);
		
		assertEquals(-1, ride.getColor());
		
		assertNull(ride.getDepartureDate());
		assertNull(ride.getArrivalDate());
		
	}
	
	public void testRide1() {
		
		Ride ride = new TrainRide(origin, destination);
		
		assertSame(origin, ride.getOrigin());
		assertSame(destination, ride.getDestination());
		assertSame(ride.getDestination(), ride.getFinalDestination());
	}
	
	public void testRide2() {
		
		Ride ride = new TrainRide(origin, destination);
		
		int RIDE_LENGTH = 20;
		
		Calendar arrivalCalendar = (Calendar) Calendar.getInstance().clone();
		arrivalCalendar.add(Calendar.MINUTE, RIDE_LENGTH);
		Date arrivalDate = (Date) arrivalCalendar.getTime().clone();
		
		Date departurelDate = (Date) Calendar.getInstance().getTime().clone();
		
		ride.setDates(departurelDate, arrivalDate);
		
		assertSame(ride.getArrivalDate(), arrivalDate);
		assertSame(ride.getDepartureDate(), departurelDate);
		assertEquals(ride.getLength(), RIDE_LENGTH);
	}
	
	
}
