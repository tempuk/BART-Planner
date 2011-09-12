package org.kilon.android.trainride.test.model.ride;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.ride.BartRideManager;
import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.ride.Ride.ExtraArg;
import org.kilon.android.trainride.model.ride.RideGroup;
import org.kilon.android.trainride.model.ride.RideGroupResults;
import org.kilon.android.trainride.model.ride.RideGroupResults.Sort;
import org.kilon.android.trainride.model.ride.RideGroupResults.Status;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.test.ActivityTestCase;
import org.w3c.dom.Document;

public class BartRideManagerSchedulesTestCase extends ActivityTestCase {

	private RideGroupResults res;
	private List<Ride> allRides;
	private int size;
	private Date queryDate;
	private Date medianDate;
	private SimpleDateFormat simpleDateFormat;
	
	public BartRideManagerSchedulesTestCase() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();

		InputStream is = app.getResources().openRawResource(R.raw.rides_schedules);
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		res = BartRideManager.getRides(doc);
		
		allRides = res.getAll();
		size = allRides.size();
		
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		queryDate = simpleDateFormat.parse("2011-06-17 4:27 AM");
		medianDate = simpleDateFormat.parse("2011-06-17 11:50 AM");
	}

	public void testOrigin() {
		
		Station origin = res.getOrigin();
		assertNotNull(origin);
		assertEquals("ASHB", origin.getId());
	}

	public void testStatus() {

		assertEquals(Status.OK, res.getStatus());

		final String errorMessage = "MY DUMMY MESSAGE";

		res.setErrorMsg(errorMessage);
		assertEquals(Status.ERROR, res.getStatus());
		assertEquals(errorMessage, res.getMessage());

		res.setErrorMsg(null);

		assertEquals(Status.OK, res.getStatus());
	}

	public void testRides() throws ParseException {
		
		assertEquals(262, size);
		assertEquals(0, res.getByDate().size());
		assertEquals(145, res.getByDate(medianDate).size());
		
		assertEquals(StationManager.get("MLBR"), allRides.get(0).getDestination());
		assertEquals(StationManager.get("RICH"), allRides.get(size-1).getDestination());
		
		int bikeFlags = 0;
		for ( Ride r : allRides ) {
			bikeFlags += Integer.parseInt((String) r.getExtra(ExtraArg.BIKE_ALLOWED));
		}
		
		assertEquals(245, bikeFlags);
		
		List<RideGroup> list = res.getByDestination(Sort.BY_DATE, queryDate);
		assertEquals(3, list.size());
		
	}



}
