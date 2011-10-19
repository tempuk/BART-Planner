package org.kilon.android.trainride.views;


import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.station.Station;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StationListItemView extends RelativeLayout {

	private Station station;
	private TextView nameText;
	
	public StationListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		nameText = (TextView)findViewById(R.id.station_name);
	}
	
	
	public void setStation(Station station) {
		this.station = station;
		nameText.setText(station.getName());
	}

	public Station getStation() {
		return station;
	}

}
