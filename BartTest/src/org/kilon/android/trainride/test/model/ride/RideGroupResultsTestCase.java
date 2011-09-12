package org.kilon.android.trainride.test.model.ride;

import java.util.Calendar;
import java.util.Date;

import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.ride.RideGroupResults;
import org.kilon.android.trainride.model.ride.RideGroupResults.Sort;
import org.kilon.android.trainride.model.ride.RideGroupResults.Status;
import org.kilon.android.trainride.model.ride.TrainRide;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.test.ActivityTestCase;

public class RideGroupResultsTestCase extends ActivityTestCase {

	private Station origin;
	private Station destination;

	public RideGroupResultsTestCase() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		origin = StationManager.get("WCRK");
		destination = StationManager.get("ORIN");

		assertNotNull(origin);
		assertNotNull(destination);
	}
	
	
	public void testStatus() {

		final String ERROR_MESSAGE = "MY ERROR MESSAGE";

		RideGroupResults res = new RideGroupResults();

		assertEquals(Status.OK, res.getStatus());
		assertNull(res.getMessage());

		res.setErrorMsg(ERROR_MESSAGE);
		assertEquals(Status.ERROR, res.getStatus());
		assertEquals(ERROR_MESSAGE, res.getMessage());

		res.setErrorMsg(null);
		assertEquals(Status.OK, res.getStatus());
		assertNull(res.getMessage());

	}

	public void testResults1() {

		RideGroupResults rides = new RideGroupResults();

		final int RIDE_LENGTH = 20;
		final int NUM_RIDES = 5;

		for ( int i=0; i<NUM_RIDES; i++ ) {
			int startMin = (i+1) * RIDE_LENGTH;

			Calendar departureCalendar = (Calendar) Calendar.getInstance().clone();
			departureCalendar.add(Calendar.MINUTE, startMin);
			Date departureDate = (Date) departureCalendar.getTime().clone();

			Calendar arrivalCalendar = (Calendar) Calendar.getInstance().clone();
			arrivalCalendar.add(Calendar.MINUTE, RIDE_LENGTH + startMin);
			Date arrivalDate = (Date) arrivalCalendar.getTime().clone();

			Ride ride = new TrainRide(origin, destination);
			ride.setDates(departureDate, arrivalDate);
			
			rides.add(ride);
		}

		assertEquals(NUM_RIDES, rides.size());
		assertEquals(NUM_RIDES, rides.getByDate().size());

		assertEquals(1, rides.getByDestination(Sort.BY_NAME).size());

	}

}
