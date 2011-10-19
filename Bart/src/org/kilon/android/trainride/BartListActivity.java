package org.kilon.android.trainride;

import android.app.ListActivity;

public abstract class BartListActivity extends ListActivity {
	
	public BartApplication getBartApplication() {
		return (BartApplication) getApplication();
	}
}
