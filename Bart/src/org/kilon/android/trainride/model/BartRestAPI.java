package org.kilon.android.trainride.model;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;

public class BartRestAPI extends RestAPI {

	public final static String API_KEY = "MDDM-R6Y8-5YA6-VDMW";
	
	public BartRestAPI(String cmd, Map<String,String> extraArgs) throws MalformedURLException {
		this("etd", cmd, extraArgs);
	}
	
	public BartRestAPI(String aspx, String cmd, Map<String,String> extraArgs) throws MalformedURLException {
		super("http://api.bart.gov/api/"+aspx+".aspx?cmd=" + cmd + "&key=" + API_KEY + buildUrl(extraArgs));
	}
	
	static String buildUrl( Map<String,String> extraArgs) {
		String s = "";
		if ( null != extraArgs ) {
			for ( Entry<String, String> entry  : extraArgs.entrySet() ) {
				s += "&" + entry.getKey() + "=" + entry.getValue(); 
			}
		}
		return s;
	}

}
