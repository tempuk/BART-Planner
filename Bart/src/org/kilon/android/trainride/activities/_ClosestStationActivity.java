package org.kilon.android.trainride.activities;

import org.kilon.android.trainride.model.station.StationManager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class _ClosestStationActivity extends StationActivity {

	@SuppressWarnings("unused")
	private static final String TAG = "ClosestStationActivity";
	
	private LocationManager locationManager;
	private ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	}

	@Override
	public void onPause() {
		super.onPause();

		locationManager.removeUpdates(onLocationChange);
	}

	private String getProvider() {
		return LocationManager.NETWORK_PROVIDER;
	}

	@Override
	public void onResume() {
		super.onResume();

		dialog = new ProgressDialog(_ClosestStationActivity.this);
		dialog.setMessage("Acquiring Location, Please wait...");
		dialog.setCancelable(true);
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
			public void onCancel(DialogInterface dialog) {
				finish();
			}
		});
		dialog.show();

		String provider = getProvider();
		locationManager.requestLocationUpdates(provider , 1000, 100000, onLocationChange);

	}

	LocationListener onLocationChange = new LocationListener() {
		
		public void onLocationChanged(Location location) {
			if ( null != location ) {
				dialog.dismiss();
				station = StationManager.getInstance().getClosest(location); 
				loadRides();
			}
		} 

		public void onProviderDisabled(String provider) {
			// required for interface, not used
		}

		public void onProviderEnabled(String provider) {
			// required for interface, not used
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// required for interface, not used
		}
	};


}


