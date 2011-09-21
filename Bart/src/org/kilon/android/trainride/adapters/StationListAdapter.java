package org.kilon.android.trainride.adapters;

import java.util.List;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.views.StationListItemView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class StationListAdapter extends BaseAdapter {

	private List<Station> stations;
	private Context context;
	
	
	public StationListAdapter(Context context, List<Station> stations) {
		this.stations = stations;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return stations.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return stations.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		StationListItemView sv;
		
		if (null == convertView) {
			sv = (StationListItemView)View.inflate(context, R.layout.station_list_item, null);
			
		} else {
			sv = (StationListItemView) convertView;
		}
		
		sv.setStation(stations.get(position));
		
		return sv;
	}

}
