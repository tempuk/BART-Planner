package org.kilon.android.trainride.adapters;

import java.util.ArrayList;
import java.util.List;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.ride.RideGroup;
import org.kilon.android.trainride.views.RideView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RideGroupAdapter extends BaseAdapter {

	private List<RideGroup> rides = new ArrayList<RideGroup>();
	private Context context;

	public RideGroupAdapter(Context context, List<RideGroup> rides) {
		this.rides = rides;
		this.context = context;
	}

	@Override
	public int getCount() {
		return rides.size();
	}

	@Override
	public Object getItem(int position) {
		return rides.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RideView rideView;

		if (null == convertView) {
			rideView = (RideView) View.inflate(context, R.layout.ride_view, null);
		} 
		else {
			rideView = (RideView) convertView;
		}

		RideGroup ride = rides.get(position);

		rideView.setRide(ride);

		return rideView;
	}

}
