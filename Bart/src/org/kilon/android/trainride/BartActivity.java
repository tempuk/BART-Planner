package org.kilon.android.trainride;


import android.app.Activity;

public abstract class BartActivity extends Activity {

	public BartApplication getBartApplication() {
		return (BartApplication) getApplication();
	}
}
