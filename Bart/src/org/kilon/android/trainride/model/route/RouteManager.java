package org.kilon.android.trainride.model.route;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.kilon.android.trainride.BartApplication;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.util.StreamToString;

public class RouteManager {
	private static RouteManager instance = null;
	private final Map<String, Route> routes = new HashMap<String, Route>();
	
	
	private RouteManager(){
	}
	
	public static RouteManager getInstance() {
		if ( instance == null )
			instance = new RouteManager();
		
		return instance;
	}
	
	public void init(BartApplication app) {
		try {
			InputStream is = app.getResources().openRawResource(R.raw.routes);
			String data = StreamToString.convertStreamToString(is);
			for ( String line: data.split("[\n\r]+") ) {

				String[] cells = line.split("\t");
				
				String[] stationIds = cells[1].split("-");
				Station origin = StationManager.get(stationIds[0]);
				Station destination = StationManager.get(stationIds[1]);
				
				Route route = new BartRoute(origin, destination, cells[0], cells[1], cells[2], cells[3], cells[4]);
				addRoute(route);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void addRoute(Route route) {
		routes.put(route.getId(), route);
	}
	
	
	public Route get(String id) {
		return routes.containsKey(id) ? routes.get(id) :  null;		
	}
	
	public int numRoutes() {
		return routes.size();
	}
	
	
	
}
