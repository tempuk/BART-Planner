package org.kilon.android.trainride.test.model;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.kilon.android.trainride.model.BartRestAPI;

import android.test.AndroidTestCase;

public class BartRestAPITestCase extends AndroidTestCase {

	public void testUrl() throws MalformedURLException {
		String cmd = "poop";
		BartRestAPI api;
		
		api = new BartRestAPI("etd", "poop", null);
		assertEquals(buildApiUrl("etd", cmd), api.getUrl());
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("foo", "bar");
		
		api = new BartRestAPI("etd", "poop", map);
		assertEquals(buildApiUrl("etd", cmd) + "&foo=bar", api.getUrl());
	}
	
	private String buildApiUrl(String aspx, String cmd) {
		return "http://api.bart.gov/api/"+aspx+".aspx?cmd=" + cmd + "&key=" + BartRestAPI.API_KEY;
	}
	
}
