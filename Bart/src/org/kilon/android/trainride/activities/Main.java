package org.kilon.android.trainride.activities;


import org.kilon.android.trainride.BartActivity;
import org.kilon.android.trainride.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends BartActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		setupViews();
	}

	private void setupViews() {
		
		View stationListButton = findViewById(R.id.main_trains_by_station);
		stationListButton.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Main.this, StationsTabsActivity.class);
				startActivity(intent);
			}
		}
		);
		
//		View favoritesButton = findViewById(R.id.main_favorites);
//		favoritesButton.setOnClickListener( new View.OnClickListener() {
//			public void onClick(View v) {
//				Intent intent = new Intent(Main.this, FavoritesActivity.class);
//				startActivity(intent);
//			}
//		}
//		);

		View  tripPlannerButton = findViewById(R.id.trip_planner);
		tripPlannerButton.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent intent = new Intent(Main.this, TripHistoryListActivity.class);
				startActivity(intent);
			}
		}
		);
		
		View stationMapButton = findViewById(R.id.station_map);
		stationMapButton.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent intent = new Intent(Main.this, StationMap.class);
				startActivity(intent);
				
			}
		}
		);
		
		
	}
	

}