package org.kilon.android.trainride.activities;

import java.util.List;

import org.kilon.android.trainride.BartListActivity;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.adapters.RideGroupAdapter;
import org.kilon.android.trainride.model.ride.RideGroup;
import org.kilon.android.trainride.model.station.Station;

import android.os.Bundle;

public class StationScheduleActivity extends BartListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
	}

	@Override
	protected void onResume() {
		super.onResume();
		String cacheKey = getIntent().getExtras().getString(Station.TYPE);
		List<RideGroup> rides = getBartApplication().getCachedRides(cacheKey);
		RideGroupAdapter adapter = new RideGroupAdapter(this,rides);
		setListAdapter(adapter);
	}

}
