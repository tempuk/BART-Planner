package org.kilon.android.trainride.model.station;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.kilon.android.trainride.BartApplication;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


public class FavoriteStationManager {

	private static FavoriteStationManager instance = null;
	private final Set<String> favStations = new TreeSet<String>();
	private SharedPreferences settings;

	private static final String ID = "FavoriteStations.ID";
	private static final String TAG = "FavoriteStations"; 

	private FavoriteStationManager() {
	}

	public static FavoriteStationManager getInstance() {
		if ( instance == null )
			instance = new FavoriteStationManager();

		return instance;
	}

	public void init(BartApplication app) {
		settings = app.getSettings();
		String[] ids = settings.getString(ID, "").split(";");
		for ( String stationId : ids )
			if ( stationId != null && stationId.length() > 0 )
				favStations.add(stationId);
	}

	public void add(Station station) {
		Log.i(TAG, "adding " + station.getName() + " to favorites");
		if ( ! isFavorite(station) ) {
			favStations.add(station.getId());
			save();
		}
		else
			Log.w(TAG, TAG + ".add : " + station.getName() + " is already a favorite");
	}

	public void remove(Station station) {
		Log.i(TAG, "removing " + station.getName() + " from favorites");
		if ( isFavorite(station) ) {
			favStations.remove(station.getId());
			save();
		}
		else
			Log.w(TAG, TAG + ".remove : " + station.getName() + " is not a favorite");
	}

	public Boolean isFavorite(Station station) {
		return favStations.contains(station.getId());
	}

	private void save() {
		Editor editor = settings.edit();
		editor.putString(ID, getIdString());
		editor.commit();
	}

	public List<Station> getAll() {
		List<Station> stations = new ArrayList<Station>();
		for ( String s : favStations ) {
			stations.add(StationManager.get(s));
		}
		return stations;
	}
	
	public String getIdString() {
		StringBuilder sb = new StringBuilder();
		for ( String s : favStations ) {
			if ( sb.length() > 0 )
				sb.append(";");
			
			sb.append(s);
		}
		return sb.toString();
	}

}
