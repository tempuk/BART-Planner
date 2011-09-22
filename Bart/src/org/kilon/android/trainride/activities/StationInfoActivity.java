package org.kilon.android.trainride.activities;

import org.kilon.android.trainride.BartActivity;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationInfo;
import org.kilon.android.trainride.model.station.StationManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StationInfoActivity extends BartActivity {
	
	private Station station;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.station_info);
		
		String stationId = getIntent().getStringExtra(Station.ID);
		station  = StationManager.get(stationId);
		
		TextView addressView1 = (TextView) findViewById(R.id.address1);
		addressView1.setText(getFormattedAddress());
		
		findViewById(R.id.address).setOnClickListener( new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=\"" + station.getName() + "\" +BART"));
				startActivity(intent);
			}
		}
		);
		
		TextView info = (TextView) findViewById(R.id.station_info);
		info.setText( new StationInfo(stationId).getIntro() );
	}
	
	private String getFormattedAddress() {
		String address = station.getAddress().getThoroughfare();
		address += "\n";
		address += station.getAddress().getLocality();
		address += ", ";
		address += station.getAddress().getAdminArea();
		address += " ";
		address += station.getAddress().getPostalCode();
		
		return address;
	}
}
