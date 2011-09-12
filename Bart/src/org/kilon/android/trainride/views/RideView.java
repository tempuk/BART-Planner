package org.kilon.android.trainride.views;

import java.text.SimpleDateFormat;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.ride.RideGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RideView extends LinearLayout {

	private TextView nameText;
	private TextView rideTimes;
	private Context context;
	private RelativeLayout circlesHolder;
	private static final long HOW_MANY_RIDES = 3; 

	public RideView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		nameText = (TextView)findViewById(R.id.ride_destination);
		rideTimes = (TextView)findViewById(R.id.ride_time);
		circlesHolder = (RelativeLayout)findViewById(R.id.ride_circles);
	}
	
	
	public void setRide(RideGroup rideGroup) {
		String dest = rideGroup.getDestination().getName();
		
		String times = "";
		for ( int i=0; i< rideGroup.size() && i < HOW_MANY_RIDES; i++ ) {
			if ( i > 0 )
				times += " | ";
			
			times +=  new SimpleDateFormat("h:mm").format(rideGroup.getRide(i).getDepartureDate());
		}
		
		nameText.setText(dest);
		rideTimes.setText(times);
		
		float x = 0;
		int circleSize = 8;
		
//		Circle circle = new Circle(context, 0, 0, 5, 0xFF097286);
		
		for ( int color: rideGroup.getColors() ) {
			Circle circle = new Circle(context, x + 10, (20-circleSize), circleSize, color);
			circlesHolder.addView( circle );
			x += Math.round(2.5 * circleSize);
		}
		
	}
}
