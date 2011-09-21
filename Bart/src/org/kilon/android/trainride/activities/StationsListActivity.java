package org.kilon.android.trainride.activities;

import java.util.List;

import org.kilon.android.trainride.BartListActivity;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.adapters.StationListAdapter;
import org.kilon.android.trainride.model.station.BartStation;
import org.kilon.android.trainride.model.station.FavoriteStationManager;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class StationsListActivity extends BartListActivity {
	
	private static final String TAG = "StationListActivity";

	private static final int CONTEXTMENU_VIEW = 0;
	private static final int CONTEXTMENU_TOGGLE_FAVORITE = 1;

	protected List<Station> stations;

	private String type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_list_activity_layout);
		
		registerForContextMenu(getListView());
		
		type = getIntent().getStringExtra(Station.TYPE);
		if ( type == Station.ALL )
			stations = StationManager.getInstance().getAll();
		else
			stations = FavoriteStationManager.getInstance().getAll();
	}
		
	@Override
	protected void onResume() {
		super.onResume();
		setListAdapter(new StationListAdapter(this, stations));
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info;
		try {
		    info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		} 
		catch (ClassCastException e) {
		    Log.e(TAG, "bad menuInfo", e);
		    return;
		}
		int position = (int) getListAdapter().getItemId(info.position);
		BartStation station = (BartStation) stations.get(position);
		
		menu.add(0, CONTEXTMENU_VIEW, 0, "View");
		menu.add(0, CONTEXTMENU_TOGGLE_FAVORITE, 0, station.isFavorite() ? R.string.remove_from_favorites : R.string.add_to_favorites);

	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info;
		try {
		    info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		} 
		catch (ClassCastException e) {
		    Log.e(TAG, "bad menuInfo", e);
		    return false;
		}
		
		int position = (int) getListAdapter().getItemId(info.position);
		BartStation station = (BartStation) stations.get(position);
		
		switch ( item.getItemId() ) 
		{
			case CONTEXTMENU_TOGGLE_FAVORITE:
				station.setFavorite( ! station.isFavorite() );
				break;
	
			case CONTEXTMENU_VIEW:
				Intent intent = new Intent(this, StationActivity.class);
				intent.putExtra(Station.ID, station.getId());
				startActivity(intent);
				break;
		}
		onResume();
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(this, StationActivity.class);
		intent.putExtra(Station.ID, stations.get(position).getId());
		startActivity(intent);
		
	}
}
