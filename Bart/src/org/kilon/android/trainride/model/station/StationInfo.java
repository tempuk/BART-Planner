package org.kilon.android.trainride.model.station;

import java.util.HashMap;
import java.util.Map;

import org.kilon.android.trainride.BartApplication;
import org.kilon.android.trainride.model.BartRestAPI;
import org.kilon.android.trainride.util.cacher.Cacher;
import org.kilon.android.trainride.util.cacher.CacherFactory;
import org.w3c.dom.Document;

import android.content.SharedPreferences;
import android.util.Log;

public class StationInfo {

	public static final String TAG = "StationInfo";

	private static InfoFactory factory = new InfoFactory();

	private Document doc;

	private SharedPreferences settings;
	
	public StationInfo(String stationId, BartApplication app) {
		doc = factory.getDoc(stationId);
		settings = app.getSettings();
	}
	
	public String getIntro() {
		if ( doc != null ) {
			return doc.getElementsByTagName("intro").item(0).getTextContent();
		}
		return "";
	}
	
	private static class InfoFactory {
		
		private static Cacher<Document> cacher = CacherFactory.getCacher(TAG);
		private int ttl = 2592000; // 30 days ttl
		
		public Document getDoc(String stationId) {
			
			Document doc = null;
			
			if ( cacher.has(stationId) ) {
				Log.i(TAG, "Found " + stationId + " in cache");
				doc = cacher.get(stationId);
			}
			else {
				Map<String,String> extraArgs = new HashMap<String, String>();
				extraArgs.put("orig", stationId);
				try {
					doc = new BartRestAPI("stn", "stninfo", extraArgs).fetch();
					cacher.put(stationId, doc, ttl);
				} 
				catch (Exception e) {
					Log.e(TAG, e.getMessage(), e);
				} 
			}
			return doc;
		}
		
	}
	
}
