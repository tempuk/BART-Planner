package org.kilon.android.trainride.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.kilon.android.trainride.BartListActivity;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.adapters.TripAdapter;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.model.trip.BartTripManager;
import org.kilon.android.trainride.model.trip.Trip;
import org.kilon.android.trainride.model.trip.TripHistoryManager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TripActivity extends BartListActivity {

	protected static final String TAG = "TripActivity";
	public List<Trip> trips;
	private TripsLoader thread = null;

	private TextView fareText;
	private TextView originText;
	private TextView destinationText;
	private TextView dateText;
	private TextView lengthText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.trip_activity_layout);

		setupViews();

		Station origin = StationManager.get(getIntent().getExtras().getString(Station.ORIGIN));
		Station destination = StationManager.get(getIntent().getExtras().getString(Station.DESTINATION));
		
		loadTrips(origin, destination);
	}

	private void setupViews() {

		lengthText = (TextView) findViewById(R.id.ride_length);
		dateText = (TextView) findViewById(R.id.date);
		fareText = (TextView) findViewById(R.id.fare);
		originText = (TextView) findViewById(R.id.origin);
		destinationText = (TextView) findViewById(R.id.destination);

		lengthText.setText("");
		dateText.setText("");
		fareText.setText("");
		originText.setText("");
		destinationText.setText("");

	}


	protected void loadTrips(Station origin, Station destination) {
		
		// save in history
		TripHistoryManager.getInstance().add(origin, destination);
		
		thread = new TripsLoader();
		thread.execute(origin, destination);
	}


	private class TripsLoader extends AsyncTask<Station, Integer, List<Trip>> {

		private ProgressDialog dialog;
		private String message = null;

		@Override
		protected List<Trip> doInBackground(Station... stations) {
			try {
				return BartTripManager.getInstance().getTrips(stations[0], stations[1]);
			} catch (Exception e) {
				message = e.getMessage();
			}
			return new ArrayList<Trip>();
		}


		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(TripActivity.this);
			dialog.setMessage("Retrieving data, Please wait...");
			dialog.setCancelable(true);
			dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
				public void onCancel(DialogInterface dialog) {
					if (null != thread ) {
						thread.cancel(true);
						finish();
					}
				}
			});
			dialog.show();
		}

		@Override
		protected void onPostExecute(List<Trip> result) {
			dialog.dismiss();
			if (result.size() > 0 )
			{
				Trip trip = result.get(0);

				long totalTime = 0;
				for ( Trip t : result )
					totalTime += t.getLength();
				
				lengthText.setText("~" + (totalTime / result.size() + " min"));
				
				Date now = (Date) Calendar.getInstance().getTime().clone();
				dateText.setText(new SimpleDateFormat("m/d h:MM").format(now));
				
				fareText.setText("$" + trip.getFare());
				originText.setText(trip.getOrigin().getName());
				destinationText.setText(trip.getDestination().getName());
				
				setListAdapter(new TripAdapter(TripActivity.this, result));
			}
			else if ( null != message ) 
				Toast.makeText(TripActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
		}
	}


}
