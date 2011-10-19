package org.kilon.android.trainride.dialog;

import org.kilon.android.trainride.BartApplication;
import org.kilon.android.trainride.R;
import org.kilon.android.trainride.activities.TripActivity;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TripDialog extends Dialog {

	private BartApplication app;
	private Spinner spinnerDepart;
	private Spinner spinnerArrive;
	private Station origin;
	private Station destination;
	private ArrayAdapter<Station> adapter;

	public TripDialog(Context context, BartApplication app) {
		super(context);
		this.app = app;
		
		origin = StationManager.get(app.getSettings().getString(Station.LAST_ORIGIN, null));
		destination = StationManager.get(app.getSettings().getString(Station.LAST_DESTINATION, null));
		
		adapter = new ArrayAdapter<Station>(context, android.R.layout.simple_spinner_item, StationManager.getInstance().getAll());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		setContentView(R.layout.trip_select_dialog);
		
		setTitle(R.string.search);

		setUpSpinners();
		setUpButtons();
	}
	
	
	private Station getOriginFromSpinner() {
		return (Station) spinnerDepart.getSelectedItem();
	}
	
	private Station getDestinationFromSpinner() {
		return (Station) spinnerArrive.getSelectedItem();
	}
	

	private void setUpSpinners() {
		spinnerDepart = (Spinner) findViewById(R.id.spinner_depart);
		spinnerDepart.setAdapter(adapter);
		if ( null != origin )
			spinnerDepart.setSelection(adapter.getPosition(origin));
		
		spinnerArrive = (Spinner) findViewById(R.id.spinner_arrive);
		spinnerArrive.setAdapter(adapter);
		if ( null != destination )
			spinnerArrive.setSelection(adapter.getPosition(destination));
	}

	private void setUpButtons() {
		View go = findViewById(R.id.go);
		go.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				go();
			}
		});
		
		View reverse = findViewById(R.id.reverse_stations);
		reverse.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				int from = adapter.getPosition(getOriginFromSpinner());
				int to = adapter.getPosition(getDestinationFromSpinner());
				
				spinnerDepart.setSelection(to);
				spinnerArrive.setSelection(from);
			}
		});
	}
	
	protected void go() {
		dismiss();
		saveState();
		search();
	}


	private void saveState() {
		Editor editor = app.getSettings().edit();
		
		origin = getOriginFromSpinner();
		destination = getDestinationFromSpinner();
		
		editor.putString(Station.LAST_ORIGIN, origin.getId());
		editor.putString(Station.LAST_DESTINATION, destination.getId());
		
		editor.commit();
	}
	
	private void search() {
		Intent intent = new Intent().setClass(getContext(), TripActivity.class);
		intent.putExtra(Station.ORIGIN, origin.getId());
		intent.putExtra(Station.DESTINATION, destination.getId());
		getContext().startActivity(intent);
	}

}
