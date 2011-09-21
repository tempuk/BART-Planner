package org.kilon.android.trainride.activities;


import org.kilon.android.trainride.BartActivity;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.station.Station;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class Main extends BartActivity {
	
	protected AlertDialog dialog;
	
	private View stationListButton;
//	private View favoritesButton;
	private View tripPlannerText;

	protected ArrayAdapter<Station> spinnerAddapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		setupViews();
	}

	private void setupViews() {
		
		stationListButton = findViewById(R.id.main_trains_by_station);
		stationListButton.setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				
//				Intent intent = new Intent(Main.this, StationListActivity.class);
				Intent intent = new Intent(Main.this, StationsTabsActivity.class);
				startActivity(intent);
			}
		}
		);
//
//		favoritesButton = findViewById(R.id.main_favorites);
//		favoritesButton.setOnClickListener( new View.OnClickListener() {
//			public void onClick(View v) {
//				Intent intent = new Intent(Main.this, FavoritesActivity.class);
//				startActivity(intent);
//			}
//		}
//		);

		tripPlannerText = findViewById(R.id.trip_planner);
		tripPlannerText.setOnClickListener( new View.OnClickListener() {
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