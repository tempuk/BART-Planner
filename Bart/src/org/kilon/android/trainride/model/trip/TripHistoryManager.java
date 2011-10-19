package org.kilon.android.trainride.model.trip;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.kilon.android.trainride.BartApplication;
import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.ride.TrainRide;
import org.kilon.android.trainride.model.ride.TrainRideGroup;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class TripHistoryManager {

	private static final String HISTORY = "TripHistory.HISTORY";

	@SuppressWarnings("unused")
	private static final String TAG = "TripHistoryManager";
	
	private static SharedPreferences settings;
	private static Editor editor;

	private static TripHistoryManager instance = null;
	
	private Map<String,Date> rideMap = new HashMap<String, Date>(); 

	private TripHistoryManager(){
		
	}
	
	public static TripHistoryManager getInstance() {
		if (  null == instance )
			instance = new TripHistoryManager();

		return instance;
	}
	
	
	public void init(BartApplication app) {
		settings = app.getSettings();
		editor = settings.edit();
		
		loadHistory();
	}
	
	public void loadHistory() {
		String historyStr = settings.getString(HISTORY, null);
		if ( null == historyStr )
			return ;
		
		String[] rides = historyStr.split(";");
		for ( String s : rides ) {
			String[] vars = s.split("-");
			Station origin = StationManager.get(vars[0]);
			Station destination = StationManager.get(vars[1]);
			add(origin, destination);
		}
	}
	
	public void add(Station origin, Station destination) {
		Date myDate = (Date) Calendar.getInstance().getTime().clone();
		
		rideMap.put(getString(origin, destination), myDate);
//		rides.add();
		commit();
	}
	
	public void remove(Station origin, Station destination) {
		rideMap.remove(getString(origin, destination));
		commit();
	}
	
	
	private String getString(Station origin, Station destination) {
		return origin.getId() + "-" + destination.getId();
	}
	
//	public Set<Ride> getHistory() {
//		Set<Ride> myRides = new HashSet<Ride>();
//		for ( String s : this.rides ) {
//			String[] ids = s.split("-");
//			myRides.add( new TrainRide( app.getStationManager().get(ids[0]) , app.getStationManager().get(ids[1])));
//		}
//		return myRides;
//	}
	
	public Set<Ride> getHistory() {
		Set<Ride> myRides = new TreeSet<Ride>();
		for (  Entry<String, Date> entry : rideMap.entrySet() ) {
			String[] ids = entry.getKey().split("-");
			Station origin = StationManager.get(ids[0]);
			Station destinatin = StationManager.get(ids[1]);
			myRides.add( new SortableRide(origin, destinatin, entry.getValue()) );
		}
		return myRides;
	}
	
	public void commit() {
		String str = toString();
		editor.putString(HISTORY, str);
		editor.commit();
	}
	
	public String toString() {
		String s = "";
		int i = 0;
		for (  Entry<String, Date> entry : rideMap.entrySet() ) {
			if ( i++ > 0 )
				s += ";";
			s += entry.getKey();
		}
		return s;
	}
	
	
	private class SortableRide extends TrainRideGroup implements Comparable<SortableRide> {

		private final Date saveDate;
		
		public SortableRide(Station origin, Station destination, Date saveDate) {
			super();
			addRide(new TrainRide(origin, destination));
			this.saveDate = saveDate;
		}

		@Override
		public int compareTo(SortableRide another) {
			return another.saveDate.compareTo(saveDate);
		}
		
	}
	
}
