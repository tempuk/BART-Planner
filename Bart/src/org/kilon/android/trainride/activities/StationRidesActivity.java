package org.kilon.android.trainride.activities;

import java.util.List;

import org.kilon.android.trainride.BartListActivity;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.adapters.RideGroupAdapter;
import org.kilon.android.trainride.model.ride.RideGroup;
import org.kilon.android.trainride.model.station.Station;

import android.os.Bundle;

public class StationRidesActivity extends BartListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_layout);

		String stationId = getIntent().getStringExtra(Station.ID);
		String type = getIntent().getStringExtra(Station.TYPE);
		
		String cacheKey = type + stationId;
		
		List<RideGroup> rides = getBartApplication().getCachedRides(cacheKey);
		
		RideGroupAdapter adapter = new RideGroupAdapter(this,rides);
		setListAdapter(adapter);
	}
}
