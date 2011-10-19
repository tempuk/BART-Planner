package org.kilon.android.trainride.views;

import java.text.SimpleDateFormat;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.trip.Trip;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TripView extends LinearLayout {

	@SuppressWarnings("unused")
	private static final String TAG = "TripView";

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
	
	private TextView tripDepartTimeText;
	private TextView tripArriveTimeText;
	
	private View tripFirstRideColor;
	private TextView tripFirstRideText;
	private View tripSecondRideColor;
	private TextView tripSecondRideText;
	private TextView tripSwitchStationText;
	
	public TripView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		tripDepartTimeText = (TextView)findViewById(R.id.trip_depart_time);
		tripArriveTimeText = (TextView)findViewById(R.id.trip_arrive_time);
		
		tripFirstRideColor = findViewById(R.id.trip_first_ride_color);
		tripFirstRideText = (TextView)findViewById(R.id.trip_first_ride);
		
		tripSecondRideColor = findViewById(R.id.trip_second_ride_color);
		tripSecondRideText = (TextView)findViewById(R.id.trip_second_ride);
		
		tripSwitchStationText = (TextView)findViewById(R.id.trip_switch_station);
		
	}
	
	public void setTrip(Trip trip) {
//		Log.i(TAG, "num rides: " + trip.numRides());
		
		tripDepartTimeText.setText(dateFormat.format(trip.getDepartureDate()));
		tripArriveTimeText.setText(dateFormat.format(trip.getArrivalDate()));

		Ride firstRide = trip.getRide(0);
		
		tripFirstRideColor.setBackgroundColor(firstRide.getColor());
		tripFirstRideText.setText(firstRide.getFinalDestination().getName());
		
		tripSecondRideText.setVisibility(VISIBLE);
		tripSwitchStationText.setVisibility(VISIBLE);
		tripSecondRideColor.setVisibility(VISIBLE);
		
		if ( trip.numRides() > 1 ) {
			
			Ride secondtRide = trip.getRide(1);
			
			tripSwitchStationText.setText("> " + secondtRide.getOrigin().getName());
			
			tripSecondRideColor.setBackgroundColor(secondtRide.getColor());
			tripSecondRideText.setText(secondtRide.getFinalDestination().getName());
			
			
			tripSecondRideText.setVisibility(VISIBLE);
			tripSwitchStationText.setVisibility(VISIBLE);
			tripSecondRideColor.setVisibility(VISIBLE);
		}
		else
		{
//			tripSecondRideText.setVisibility(GONE);
//			tripSwitchStationText.setVisibility(GONE);
//			tripSecondRideColor.setVisibility(GONE);
			
			tripSecondRideText.setText("");
			tripSwitchStationText.setText("> Direct");
			tripSecondRideColor.setBackgroundColor(Color.BLACK);
		}
		
		/*
		LayoutParams params = (LayoutParams) getLayoutParams();
		params.height = LayoutParams.FILL_PARENT;
		setLayoutParams(params);
		*/
	}
	

}
