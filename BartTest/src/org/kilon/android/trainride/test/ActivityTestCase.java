package org.kilon.android.trainride.test;

import org.kilon.android.trainride.BartApplication;
import org.kilon.android.trainride.activities.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class ActivityTestCase extends ActivityInstrumentationTestCase2<MainActivity> {

	protected BartApplication app;

	public ActivityTestCase() {
		super("org.kilon.android.trainride.activities", MainActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		app = (BartApplication)getActivity().getApplication();
		app.init();
	}
}