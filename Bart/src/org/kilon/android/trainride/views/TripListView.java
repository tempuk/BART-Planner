package org.kilon.android.trainride.views;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.ride.Ride;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TripListView extends RelativeLayout {

	private TextView originTextView;
	private TextView destinationTextView;

	public TripListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		originTextView = (TextView)findViewById(R.id.origin);
		destinationTextView = (TextView)findViewById(R.id.destination);
	}
	
	public void setRide(Ride ride) {
		originTextView.setText(ride.getOrigin().toString());
		destinationTextView.setText(ride.getDestination().toString());
	}

}
