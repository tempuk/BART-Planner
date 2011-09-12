package org.kilon.android.trainride.model.station;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kilon.android.trainride.BartApplication;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.util.StreamToString;

import android.location.Location;
import android.util.Log;

public class StationManager {
	
	private static final String TAG = "StationManager";
	private static StationManager instance = null;

	private Map<String, Station> stations = new HashMap<String, Station>(); 
	
	private  StationManager() {
	}
	
	public void init(BartApplication app) {
		try {
			InputStream is = app.getResources().openRawResource(R.raw.stations);
			String data = StreamToString.convertStreamToString(is);
//			Log.v(TAG, "csv: " + data);
			for ( String line : data.split("[\n\r]+") ) {

				String[] cells = line.split("\t");

				// id + name
				BartStation station = new BartStation(cells[0], cells[1]);
				
				// location (latitude/longitude)
				station.setLatitude(Double.parseDouble(cells[2]));
				station.setLongitude(Double.parseDouble(cells[3]));
				
				// street address
				station.getAddress().setAddressLine(0, cells[4]);
				station.getAddress().setThoroughfare(cells[4]);
				
				// city
				station.getAddress().setLocality(cells[5]);
				
				// state
				station.getAddress().setAdminArea(cells[6]);
				
				// country
				station.getAddress().setCountryCode("US");
				station.getAddress().setCountryName("United States");
				
				// zip code
				station.getAddress().setPostalCode(cells[7]);

				stations.put(station.getId(), station);
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, e.getMessage(), e);
		}
	}
	
	public static StationManager getInstance() {
		if ( instance == null )
			instance = new StationManager();
		
		return instance;
	}
	
	
	public List<Station> getAll() {
		List<Station> list = new ArrayList<Station>();
		for ( Entry<String, Station> entry  : stations.entrySet() ) {
			final Station station = entry.getValue(); 
			list.add(station);
		}
		
		Collections.sort(list, new Comparator<Station>() {
			@Override
			public int compare(Station s1, Station s2) {
				return s1.getName().compareTo(s2.getName());
			}
		});
		
		return list;
	}
	
	public String[] getAllNames() {
		StringBuilder sb = new StringBuilder("");
		for ( Entry<String, Station> entry  : stations.entrySet() ) {
			if ( sb.toString() != "" )
				sb.append(";");
			
			sb.append(entry.getValue().getName());
		}
		return sb.toString().split(";");
	}
	
	
	
	public static Station get(String id) {
		return getInstance().stations.containsKey(id) ? getInstance().stations.get(id) : null;
	}

	public Station getClosest(Location location) {

		if ( null == location )
			return null;
		
		Station closestStation = null;
		float closestDistance = -1;

		for ( Entry<String, Station> e : stations.entrySet() ) {
			Station station = e.getValue();
			
			float distance = location.distanceTo(station.getLocation());
			if ( closestDistance == -1 || distance < closestDistance ) {
				closestStation = station;
				closestDistance = distance;
			}
		}
		
		Log.e(getClass().getCanonicalName(), "Distance: " + Integer.toString(Math.round(closestDistance / 1000)) + " KM");
		
		return closestStation;
	}
}
