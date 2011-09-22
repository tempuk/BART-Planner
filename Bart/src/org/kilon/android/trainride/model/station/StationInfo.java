package org.kilon.android.trainride.model.station;

import java.util.HashMap;
import java.util.Map;

import org.kilon.android.trainride.model.BartRestAPI;
import org.w3c.dom.Document;

import android.util.Log;

public class StationInfo {

	public static final String TAG = "StationInfo";

	private static Cacher cacher = new Cacher();

	private Document doc;
	
	public StationInfo(String stationId) {
		doc = cacher.getDoc(stationId);
	}
	
	public String getIntro() {
		return doc.getElementsByTagName("intro").item(0).getTextContent();
	}
	
	
	
	
	
	private static class Cacher extends HashMap<String, Document> {
		
		private static final long serialVersionUID = 1L;

		public Document getDoc(String stationId) {
			if ( containsKey(stationId) ) {
				Log.i(TAG, "Found " + stationId + " in cache");
				return get(stationId);
			}
			else {
				Map<String,String> extraArgs = new HashMap<String, String>();
				extraArgs.put("orig", stationId);
				try {
					Document doc = new BartRestAPI("stn", "stninfo", extraArgs).fetch();
					put(stationId, doc);
					return doc;
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			return null;
		}
		
	}
	
}
