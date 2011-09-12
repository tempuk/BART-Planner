package org.kilon.android.trainride.activities;

import java.util.ArrayList;
import java.util.List;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.trip.TripHistoryManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class _GridActivity extends Activity {

	@SuppressWarnings("rawtypes")
	private static class Icon {
		private final int id;
		private final Class linkedClass;
		
		public int getId() {
			return id;
		}

		public Class getLinkedClass() {
			return linkedClass;
		}

		public Icon(int id, Class linkedClass) {
			this.id = id;
			this.linkedClass = linkedClass;
		}
		
	}
	
	private List<Icon> list = new ArrayList<Icon>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		list.add(new Icon(R.drawable.ic_hp_stations, StationListActivity.class));
		list.add(new Icon(R.drawable.ic_hp_favorites, FavoritesActivity.class));
		list.add(new Icon(R.drawable.ic_hp_trip_planner, TripHistoryListActivity.class));
		list.add(new Icon(R.drawable.ic_hp_map, StationMap.class));
		
		setContentView(R.layout.grid_layout);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//				Toast.makeText(_GridActivity.this, "" + id, Toast.LENGTH_LONG).show();
				Intent intent = new Intent(_GridActivity.this, list.get(position).getLinkedClass());
				startActivity(intent);
			}
		});
	}

	public BaseAdapter adapter = new BaseAdapter() {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position).getId();
		}

		@Override
		public long getItemId(int position) {
			return list.get(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {  // if it's not recycled, initialize some attributes
				imageView = new ImageView(_GridActivity.this);
				imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(4,30,4,30);
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(list.get(position).getId());
			return imageView;
		}

	};

}
