package org.kilon.android.trainride.model.ride;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.kilon.android.trainride.model.station.Station;


@SuppressWarnings("unused")
public class RideGroupResults {

	public static enum Status {
		OK,
		ERROR
	}
	
	public static enum Sort {
		BY_DATE,
		BY_NAME
	}

	private static final String TAG = "RideGroupResults";
	
	private final List<Ride> rides = new ArrayList<Ride>();

	private Status status = Status.OK;
	private String message = null;

	public String getMessage() {
		return message;
	}

	public Status getStatus() {
		return status;
	}

	private void setStatus(Status status) {
		this.status = status;
	}
	
	
	public Station getOrigin() {
		return getAll().size() > 0 ? getAll().get(0).getOrigin() : null;
	}

	public void add(Ride ride) {
		rides.add(ride);
	}

	public int size() {
		return getAll().size();
	}
	
	public List<Ride> getAll() {
		return rides;
	}

	public List<Ride> getByDate() {
		Date now = ((Calendar) Calendar.getInstance().clone()).getTime();
		return getByDate(now);
	}
	
	public List<Ride> getByDate(Date date) {
		List<Ride> list = new ArrayList<Ride>();
		for ( Ride ride : getAll() ) {
			Date departureDate = ride.getDepartureDate();
			if ( departureDate != null && departureDate.after(date) )
				list.add(ride);
		}
		return list;
	}
	
	
	public static List<RideGroup> toRideGroups(List<Ride> rides) {
		Map<String, RideGroup> map = new TreeMap<String, RideGroup>();
		for ( Ride ride : rides) {
			String key = ride.getDestination().getId();
			RideGroup rideGroup = map.containsKey(key) ?  map.get(key) : new TrainRideGroup();
			rideGroup.addRide(ride);
			map.put(key, rideGroup);
		}
		
		List<RideGroup> rideGroups = new ArrayList<RideGroup>();
		
		for ( Entry<String, RideGroup> entry : map.entrySet() ) {
			rideGroups.add(entry.getValue());
		}
		
		return rideGroups;
	}

	public List<RideGroup> getByDestination(final Sort sortBy) {
		Date date = (Date) Calendar.getInstance().getTime().clone();
		return getByDestination(sortBy, date);
	}
	
	public List<RideGroup> getByDestination(final Sort sortBy, Date date) {
		List<RideGroup> list = new ArrayList<RideGroup>();
		Map<String, List<Ride>> map = new TreeMap<String, List<Ride>>();

		for ( Ride ride : getByDate(date)) {
			String key = ride.getDestination().getId();
			List<Ride> rides = map.containsKey(key) ?  map.get(key) : new ArrayList<Ride>();
			rides.add(ride);
			map.put(key, rides);
		}

		for ( Entry<String, List<Ride>> entry : map.entrySet() ) {
			int i = 0;
			RideGroup uniqueRide = new TrainRideGroup();
			for ( Ride ride : entry.getValue() ) {
					uniqueRide.addRide(ride);
			}
			list.add(uniqueRide);
		}

		Collections.sort(list, new Comparator<Ride>() {
			@Override
			public int compare(Ride r1, Ride r2) {
				if ( sortBy.equals(Sort.BY_DATE)) {
					return r1.getDepartureDate().compareTo(r2.getDepartureDate());
				}
				else if ( sortBy.equals(Sort.BY_NAME)) {
					return r1.getDestination().getName().compareTo(r2.getDestination().getName());
				}
				return 0;
			}
		}); 

		return list;
	}

	public void setErrorMsg(String message) {
		this.message = message;
		setStatus(null == message ? Status.OK : Status.ERROR);
		
	}
}
