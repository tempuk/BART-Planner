package org.kilon.android.trainride.activities;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.station.Station;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class StationsTabsActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stations_tab_layout);

		setTitle(R.string.stations);
		setUpTabs();
	}
	
	@Override
	public void setTitle(CharSequence title)  {
		super.setTitle(title);
		TextView titleText = (TextView) findViewById(R.id.layout_title);
		titleText.setText(title);
	}
	
	
	private void setUpTabs() {

		TabHost.TabSpec spec;  // Resusable TabSpec for each tab
		Drawable drawable; // Reusable Drawable for each tab
		String title;

		Resources res = getResources(); // Resource object to get Drawables
		final TabHost tabHost = getTabHost();  // The activity TabHost
		
		tabHost.getTabWidget().removeAllViews();

		// Initialize a TabSpec for each tab and add it to the TabHost
		drawable = res.getDrawable(R.drawable.ic_tab_schedules);
		title = res.getString(R.string.favorites);
		spec = tabHost.newTabSpec(Station.FAVORITES).setIndicator(title, drawable).setContent(getIntent(Station.FAVORITES));
		tabHost.addTab(spec);

		drawable = res.getDrawable(R.drawable.ic_tab_schedules);
		title = res.getString(R.string.all);
		spec = tabHost.newTabSpec(Station.ALL).setIndicator(title, drawable).setContent(getIntent(Station.ALL));
		tabHost.addTab(spec);
	}
	
	private Intent getIntent(String type) {
		if ( type.equals(Station.ALL) ) {
			return new Intent().setClass(this, StationListActivity.class);
		}
		return new Intent().setClass(this, FavoritesActivity.class);
	}
}
