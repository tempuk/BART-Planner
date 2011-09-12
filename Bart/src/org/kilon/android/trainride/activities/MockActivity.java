package org.kilon.android.trainride.activities;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import org.kilon.android.trainride.BartListActivity;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.adapters.RideGroupAdapter;
import org.kilon.android.trainride.model.ride.BartRideManager;
import org.kilon.android.trainride.model.ride.RideGroupResults;
import org.w3c.dom.Document;

import android.os.Bundle;
import android.util.Log;

public class MockActivity extends BartListActivity {

	private static final String TAG = "MockActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
	}
	
/*
	@Override
	protected void onResume() {
		super.onResume();
		InputStream is = getBartApplication().getResources().openRawResource(R.raw.rides_realtime);
		Document doc;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			RideGroupResults res = BartRideManager.getRealTimeDepartures(doc);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
			Date queryDate = simpleDateFormat.parse("2011-06-17 4:27 AM");
			
			setListAdapter(new RideGroupAdapter(this, RideGroupResults.toRideGroups(res.getByDate(queryDate))));
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage(), e);
		}
	}
*/
	
}
