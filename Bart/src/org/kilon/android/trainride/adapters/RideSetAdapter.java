package org.kilon.android.trainride.adapters;

import java.util.Set;
import java.util.TreeSet;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.views.TripListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RideSetAdapter extends BaseAdapter {

	
	Set<Ride> rides = new TreeSet<Ride>();
	private Context context;
	
	
	public RideSetAdapter(Context context, Set<Ride> rides) {
		this.rides = rides;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return rides.size();
	}

	@Override
	public Object getItem(int position) {
		return rides.toArray()[position];
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TripListView tv;
		if (null == convertView) {
			tv = (TripListView) View.inflate(context, R.layout.trip_hostory_item, null);
		} 
		else {
			tv = (TripListView) convertView;
		}
		Ride ride = (Ride) getItem(position);
		tv.setRide(ride);
		return tv;
	}

}
