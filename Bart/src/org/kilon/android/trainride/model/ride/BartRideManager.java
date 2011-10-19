package org.kilon.android.trainride.model.ride;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.kilon.android.trainride.model.BartRestAPI;
import org.kilon.android.trainride.model.ride.Ride.ExtraArg;
import org.kilon.android.trainride.model.route.RouteManager;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.util.DateUtils;
import org.kilon.android.trainride.util.cacher.Cacher;
import org.kilon.android.trainride.util.cacher.CacherFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;

public class BartRideManager {

	private static final String TAG = "BartRideManager";
	private static final String DATE_FORMAT = "MM/dd/yyyy hh:mm a";
	
	private static final Cacher<RideGroupResults> cacher = CacherFactory.getCacher(TAG);

	public static RideGroupResults getRides(Document doc) {

		RideGroupResults res = new RideGroupResults();

		Station origin = StationManager.get(getNodeValue(doc, "abbr"));
		String date = getNodeValue(doc, "date");

		NodeList nodeList = doc.getElementsByTagName("item");
		Log.i(TAG, "Found " + nodeList.getLength() + " Rides");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element rideElement = (Element) nodeList.item(i);
			Station destination = StationManager.get(rideElement.getAttribute("trainHeadStation"));
			try {
				String origTimeStr = date + " " + rideElement.getAttribute("origTime");
				String destTimeStr = date + " " + rideElement.getAttribute("destTime");
				Date origTime = DateUtils.parseDate(DATE_FORMAT, origTimeStr);
				Date destTime = DateUtils.parseDate(DATE_FORMAT, destTimeStr);

				String line = rideElement.getAttribute("line");
				String color = RouteManager.getInstance().get(line).getColor();

				Ride ride = new TrainRide(origin, destination);
				ride.setDates(origTime, destTime);
				ride.setLine(line);
				ride.setColor(color);
				ride.putExtra(ExtraArg.BIKE_ALLOWED, rideElement.getAttribute("bikeflag"));
				res.add(ride);
			}
			catch (ParseException e) {
				Log.e(TAG, e.getMessage(), e);
				res.setErrorMsg(e.getMessage());
				return null;
			}
		}
		return res;
	}

	public static RideGroupResults getRides(Station origin) throws Exception {

		String cacheKey = "getRides-" +  origin.getId();

		RideGroupResults res = cacher.get(cacheKey);
		if ( null != res )
			return res;

		try {
			Map<String,String> extraArgs = new HashMap<String, String>();
			extraArgs.put("orig",  origin.getId());

			Document doc = new BartRestAPI("sched", "stnsched", extraArgs).fetch();
			res = getRides(doc);
			cacher.put(cacheKey, res, 3600);
			return res;
		}
		catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw e;
		}

	}

	public static RideGroupResults getRealTimeDepartures(Document doc) throws ParseException {

		RideGroupResults res = new RideGroupResults();

		String stationId = ((Element)doc.getElementsByTagName("station").item(0)).getElementsByTagName("abbr").item(0).getTextContent();
		Station origin = StationManager.get(stationId);

		NodeList nodeList =  doc.getElementsByTagName("etd");

		String dateString = getNodeValue(doc, "date") + " " +  getNodeValue(doc, "time");
		String dateFormat = "MM/dd/yyyy hh:mm:ss a 'PDT'";

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element rideNode = (Element) nodeList.item(i);
			String destStr = rideNode.getElementsByTagName("abbreviation").item(0).getTextContent();

			Station destination =  StationManager.get(destStr);
			//			RideGroup rideGroup = new TrainRideGroup();
			NodeList myNodes = rideNode.getElementsByTagName("estimate");
			for ( int j=0; j<myNodes.getLength(); j++ ) {
				Element myElement = (Element) myNodes.item(j);

				int minutes = Integer.parseInt(myElement.getElementsByTagName("minutes").item(0).getTextContent().replace("Arrived", "0"));

				Date departureDate = DateUtils.parseDate(dateFormat, dateString, minutes);

				Ride ride = new TrainRide(origin, destination);
				ride.setDates(departureDate, null);

				String color = myElement.getElementsByTagName("hexcolor").item(0).getTextContent();
				ride.setColor(color);

				//				rideGroup.addRide(ride);
				res.add(ride);
			}
			//			res.add(ride);
		}
		Log.i(TAG, "found " + res.size() + " rides");
		return res;
	}


	public static RideGroupResults getRealTimeDepartures(Station origin) throws Exception {

		String cacheKey = "getRealTimeDepartures-" +  origin.getId();

		RideGroupResults res = cacher.get(cacheKey);
		if ( null != res )
			return res;

		try {
			Map<String,String> extraArgs = new HashMap<String, String>();
			extraArgs.put("orig",  origin.getId());
			Document doc = new BartRestAPI("etd", extraArgs).fetch();
			res = getRealTimeDepartures(doc);
			cacher.put(cacheKey, res, 30);
			return res;
		} 
		catch (Exception e) {
			Log.e(TAG, "Exception: " + e.getMessage());
			throw e;
		}



	}

	private static String getNodeValue(Document doc, String nodeName) {
		NodeList list = doc.getElementsByTagName(nodeName);
		return list.getLength() > 0 ? list.item(0).getTextContent() : "NuLL";
	}


}
