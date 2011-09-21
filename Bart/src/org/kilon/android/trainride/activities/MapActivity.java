package org.kilon.android.trainride.activities;

import org.kilon.android.trainride.BartActivity;
import org.kilon.android.trainride.R;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MapActivity extends BartActivity {

	private static final String mapUrl = "http://www.bart.gov/images/global/system-map29.gif";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		
		WebView webView = (WebView) findViewById(R.id.map);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

		webView.loadUrl(mapUrl);
	}
}
