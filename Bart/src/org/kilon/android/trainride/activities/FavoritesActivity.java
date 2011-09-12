package org.kilon.android.trainride.activities;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.station.FavoriteStationManager;

import android.os.Bundle;
import android.view.MenuItem;


public class FavoritesActivity extends StationListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActiveTitle(R.string.favorites);
	}
	
	
	@Override
	protected void setStations() {
		stations = FavoriteStationManager.getInstance().getAll();
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		super.onContextItemSelected(item);
		onResume();
		return true;
	}
	
	
}
