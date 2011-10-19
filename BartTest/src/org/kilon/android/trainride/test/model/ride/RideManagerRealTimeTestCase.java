package org.kilon.android.trainride.test.model.ride;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import org.kilon.android.trainride.model.ride.BartRideManager;
import org.kilon.android.trainride.model.ride.RideGroupResults;
import org.kilon.android.trainride.model.ride.RideGroupResults.Sort;
import org.kilon.android.trainride.model.ride.RideGroupResults.Status;
import org.kilon.android.trainride.test.ActivityTestCase;
import org.kilon.android.trainride.R;
import org.w3c.dom.Document;

import android.content.res.XmlResourceParser;
import android.util.Log;

@SuppressWarnings("unused")
public class RideManagerRealTimeTestCase extends ActivityTestCase {

	private static final String TAG = "RideManagerRealTimeTestCase";
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
	
	private RideGroupResults res;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		InputStream is = app.getResources().openRawResource(R.raw.rides_realtime);
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		res = BartRideManager.getRealTimeDepartures(doc);
	}
	
	public void testStatus() {
		assertEquals(Status.OK, res.getStatus());
	}
	
	public void testOrigin() {
		assertEquals("12TH", res.getOrigin().getId());
	}
	
	public void testNumResults() throws ParseException {
		assertEquals(17, res.getAll().size());
		
		assertEquals(0, res.getByDate().size());
		
		Date date;
		
		date = simpleDateFormat.parse("2011-06-22 5:43 PM");
		assertEquals(17, res.getByDate(date).size());

		date = simpleDateFormat.parse("2011-06-22 5:47 PM");
		assertEquals(14, res.getByDate(date).size());
		
		assertEquals(6, res.getByDestination(Sort.BY_DATE, date).size());
		
	}
	
}
