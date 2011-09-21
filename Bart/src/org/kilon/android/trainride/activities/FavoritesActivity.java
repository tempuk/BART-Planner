package org.kilon.android.trainride.activities;

import org.kilon.android.trainride.model.station.FavoriteStationManager;


public class FavoritesActivity extends StationListActivity {
	
	@Override
	protected void setStations() {
		stations = FavoriteStationManager.getInstance().getAll();
	}
	
}
