package org.kilon.android.trainride;

import java.util.Collections;
import java.util.List;

import org.kilon.android.trainride.model.ride.RideGroup;
import org.kilon.android.trainride.model.ride.RideGroupResults;
import org.kilon.android.trainride.model.ride.RideGroupResults.Sort;
import org.kilon.android.trainride.model.route.RouteManager;
import org.kilon.android.trainride.model.station.FavoriteStationManager;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.model.trip.TripHistoryManager;
import org.kilon.android.trainride.util.cacher.Cacher;
import org.kilon.android.trainride.util.cacher.CacherFactory;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

public class BartApplication extends Application {

	private static final String PREFS_NAME = "MyPrefsFile";
	private static final String TAG = "BartApplication";
	
	private SharedPreferences settings;
	
	@Override
	public void onCreate() {
		super.onCreate();
		settings = getSharedPreferences(PREFS_NAME, 0);
		init();
	}

	public SharedPreferences getSettings() {
		return settings;
	}
	
	public Cacher<RideGroupResults> getCacher() {
		return CacherFactory.getCacher(PREFS_NAME);
	}
	
	public List<RideGroup> getCachedRides(String key) {
		RideGroupResults res = getCacher().get(key);
		if ( null == res)
			return Collections.emptyList();
	
		return res.getByDestination(Sort.BY_NAME);
	}
	
	public void init() {
		
		Log.w(TAG, "called " + getClass().getCanonicalName() + ".init()");
		
		StationManager.getInstance().init(this);
		FavoriteStationManager.getInstance().init(this);
		RouteManager.getInstance().init(this);
		TripHistoryManager.getInstance().init(this);
	}
	
}
