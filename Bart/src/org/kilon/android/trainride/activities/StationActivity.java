package org.kilon.android.trainride.activities;

import org.kilon.android.trainride.BartApplication;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.ride.BartRideManager;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class StationActivity extends TabActivity {

	protected static final String TAG = "StationActivity";
	protected BartApplication app;
	protected Station station = null ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_tab_widget);

		setTitle("");
		
		app = (BartApplication) getApplication();

		if ( getIntent().hasExtra(Station.ID) ) {
			String stationId = getIntent().getExtras().getString(Station.ID);
			station = StationManager.get(stationId);
		}
		
		
		setUpViews();
		
		loadRides();
	}

	protected void loadRides() {
		if ( station != null )  
			new Loader().execute(station);

	}

	@Override
	public void setTitle(CharSequence title)  {
		super.setTitle(title);
		TextView titleText = (TextView) findViewById(R.id.layout_title);
		titleText.setText(title);
	}


	private void setUpViews() {

		setTitle(station.getName());


		final int starOn = android.R.drawable.btn_star_big_on;
		final int starOff = android.R.drawable.btn_star_big_off;


		final ImageView favoriteImage = (ImageView) findViewById(R.id.btn_favorite);
		favoriteImage.setImageResource(station.isFavorite() ? starOn : starOff);
		favoriteImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				int msg;
				if ( station.isFavorite() ) {
					station.setFavorite(false);
					favoriteImage.setImageResource(starOff);
					msg = R.string.remove_from_favorites;
				}
				else {
					station.setFavorite(true);
					favoriteImage.setImageResource(starOn);
					msg = R.string.added_to_favorites;
				}
				Toast.makeText(StationActivity.this, msg, Toast.LENGTH_SHORT).show();
			}

		});

	}

	private void setUpTabs() {

		TabHost.TabSpec spec;  // Resusable TabSpec for each tab
		Drawable drawable; // Reusable Drawable for each tab
		String title;

		Resources res = getResources(); // Resource object to get Drawables
		final TabHost tabHost = getTabHost();  // The activity TabHost
		
		tabHost.getTabWidget().removeAllViews();

		// Initialize a TabSpec for each tab and add it to the TabHost
		drawable = res.getDrawable(R.drawable.ic_tab_schedules);
		title = res.getString(R.string.real_time_arrivals);
		spec = tabHost.newTabSpec(Station.REAL_TIMES).setIndicator(title, drawable).setContent(getIntent(Station.REAL_TIMES));
		tabHost.addTab(spec);

//		drawable = res.getDrawable(R.drawable.ic_tab_schedules);
//		title = res.getString(R.string.schedules);
//		spec = tabHost.newTabSpec(Station.SCHEDULES).setIndicator(title).setContent(getIntent(Station.SCHEDULES));
//		tabHost.addTab(spec);

//		tabHost.setCurrentTabByTag(Station.SCHEDULES);
//		tabHost.setCurrentTabByTag(Station.REAL_TIMES);
		
	}

	private Intent getIntent(String type) {
		Intent intent = new Intent().setClass(this, StationRidesActivity.class);
		intent.putExtra(Station.ID, station.getId());
		intent.putExtra(Station.TYPE, type);
		return intent;
	}


	private class Loader extends AsyncTask<Station, Integer, Integer>{
		private ProgressDialog dialog;
		private String message = null;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(StationActivity.this);
			dialog.setTitle("Please wait");
			dialog.setMessage("Retrieving data...");
			dialog.setCancelable(true);
			dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
				public void onCancel(DialogInterface dialog) {
					Loader.this.cancel(true);
					finish();
				}
			});
			dialog.show();
		}

		@Override
		protected void onPostExecute(Integer i) {
			dialog.dismiss();

			setUpTabs();

			if ( null != message )
				Toast.makeText(StationActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();

		}

		@Override
		protected Integer doInBackground(Station... stations) {
			try {
				int cacheTTL = 60;
				Station myStation = stations[0];
				String cacheKey = Station.REAL_TIMES + myStation.getId();
//				app.getCacher().put(Station.SCHEDULES, BartRideManager.getRides(myStation), cacheTTL);
				app.getCacher().put(cacheKey, BartRideManager.getRealTimeDepartures(myStation), cacheTTL);
			} catch (Exception e) {
				message = e.getMessage();
			}
			return 0;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.station_rides_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.refresh_rides:
			loadRides();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
