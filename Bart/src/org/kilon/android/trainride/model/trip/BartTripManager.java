package org.kilon.android.trainride.model.trip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kilon.android.trainride.model.BartRestAPI;
import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.ride.TrainRide;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.util.DateUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;

public class BartTripManager {

	private static final String TAG = "BartTripManager";
	private static BartTripManager instance = null;
	
	
	private static final String DATE_FORMAT = "MM/dd/yyyy hh:mm a";

	private BartTripManager() {
	}

	public static BartTripManager getInstance() {
		if (  null == instance )
			instance = new BartTripManager();

		return instance;
	}
	
	public List<Trip> getTrips(Station origin, Station destination) throws Exception {
		
		List<Trip> trips = new ArrayList<Trip>();
		
//		TripHistoryManager.getInstance().add(origin, destination);
		
		try {
			
			Map<String,String> extraArgs = new HashMap<String, String>();
			extraArgs.put("orig", origin.getId());
			extraArgs.put("dest", destination.getId());
			extraArgs.put("date", "now");
			extraArgs.put("b"	, "0");
			extraArgs.put("a"	, "4");
			
			Calendar cal = (Calendar) Calendar.getInstance().clone();
			cal.add(Calendar.MINUTE, 5);
			
			extraArgs.put("time", new SimpleDateFormat("h:mm+a").format(cal.getTime()));
			
			Document doc = new BartRestAPI("sched", "depart", extraArgs).fetch();
			NodeList tripNodes =  doc.getElementsByTagName("trip");
			Log.i(TAG, "Found " + tripNodes.getLength() + " Trips");
			
			for (int i = 0; i < tripNodes.getLength(); i++) {
				
				Element tripNode = (Element) tripNodes.item(i);
				
				List<Ride> rides = new ArrayList<Ride>();
				
				NodeList rideNodes = tripNode.getElementsByTagName("leg");
				Log.i(TAG, "Found " + rideNodes.getLength() + " Rides");
				
				for (int j = 0; j < rideNodes.getLength(); j++) {
					
					Element rideNode = (Element) rideNodes.item(j);
					
					Station rideOrigin = StationManager.get(rideNode.getAttribute("origin"));
					Station rideDestination = StationManager.get(rideNode.getAttribute("destination"));
					Station rideFinalDestination = StationManager.get(rideNode.getAttribute("trainHeadStation"));
					
					
					
					Ride ride = new TrainRide(rideOrigin, rideDestination, rideFinalDestination);
					
					
//					Date departureDate = DateUtils.parseDate(rideNode.getAttribute("origTimeMin"), rideNode.getAttribute("origTimeDate"));
//					Date arrivalDate = DateUtils.parseDate(rideNode.getAttribute("destTimeMin"), rideNode.getAttribute("destTimeDate"));
					Date departureDate = DateUtils.parseDate(DATE_FORMAT, rideNode.getAttribute("origTimeDate") + " " + rideNode.getAttribute("origTimeMin"), 0);
					Date arrivalDate = DateUtils.parseDate(DATE_FORMAT, rideNode.getAttribute("destTimeDate") + " " + rideNode.getAttribute("destTimeMin"), 0);
					
					ride.setDates(departureDate, arrivalDate);
					ride.setLine(rideNode.getAttribute("line"));
					
//					rides.add(new TrainRideGroup(ride));
					rides.add(ride);
				}
				
				Trip trip = new TrainTrip(rides);
				trip.setFare(tripNode.getAttribute("fare"));
				
				trips.add(trip);
			}
		}
		catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw e;
		}
		
		return trips;
	}
	
	
	
}
