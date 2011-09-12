package org.kilon.android.trainride.activities;

import java.util.Set;

import org.kilon.android.trainride.BartListActivity;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.adapters.RideSetAdapter;
import org.kilon.android.trainride.dialog.TripDialog;
import org.kilon.android.trainride.model.ride.Ride;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.trip.TripHistoryManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;

public class TripHistoryListActivity extends BartListActivity {

	@SuppressWarnings("unused")
	private static final String TAG = "TripHistoryListActivity";
	
	private static final int CONTEXTMENU_REMOVE = 0;
	
	private Set<Ride> history;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trip_history_activity_layout);

		registerForContextMenu(getListView());

		setActiveTitle(R.string.trip_hostory);
		
		View addButton = findViewById(R.id.add);
		addButton.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popupDialog();
			}
		});
	}

	protected void setActiveTitle(int ref) {
		setTitle(ref);
		((TextView) findViewById(R.id.layout_title)).setText(ref);
	}


	@Override
	protected void onResume() {
		super.onResume();
		history = TripHistoryManager.getInstance().getHistory();
		setListAdapter(new RideSetAdapter(this, history));
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.trip_hostoty_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_search:
			popupDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	 */
	private void popupDialog() {
		TripDialog dialog = new TripDialog(this, getBartApplication());
		dialog.show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent intent = new Intent(this, TripActivity.class);

		Ride ride = getRide(position);
		intent.putExtra(Station.ORIGIN, ride.getOrigin().getId());
		intent.putExtra(Station.DESTINATION, ride.getDestination().getId());
		startActivity(intent);
	}

	private Ride getRide(int position) {
		return (Ride) history.toArray()[position];
	}
	
	// ADD LONG CLICK CONTEXT MENU
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, CONTEXTMENU_REMOVE, 0, R.string.remove);

	}

	// HANDLE LONG CLICK CONTEXT MENU SELECTION
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		int position = (int) getListAdapter().getItemId(info.position);
		Ride ride = getRide(position);
		switch ( item.getItemId() ) {
		case CONTEXTMENU_REMOVE:
			TripHistoryManager.getInstance().remove(ride.getOrigin(), ride.getDestination());
			onResume();
		}
		return true;
	}
}
