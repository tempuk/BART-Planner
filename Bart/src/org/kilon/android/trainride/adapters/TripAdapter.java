package org.kilon.android.trainride.adapters;

import java.util.List;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.trip.Trip;
import org.kilon.android.trainride.views.TripView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TripAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private static final String TAG = "TripAdapter";
	
	private final List<Trip> trips;
	private final Context context;

	public TripAdapter(Context context, List<Trip> trips) {
		this.trips = trips;
		this.context = context;
	}
	
	
	@Override
	public int getCount() {
		return trips.size();
	}

	@Override
	public Object getItem(int position) {
		return trips.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TripView tv;
		
		if (null == convertView) {
			tv = (TripView) View.inflate(context, R.layout.trip_item_view, null);
			tv.setTrip((Trip) getItem(position));
		} 
		else {
			tv = (TripView) convertView;
		}

		
		return tv;
	}

}
