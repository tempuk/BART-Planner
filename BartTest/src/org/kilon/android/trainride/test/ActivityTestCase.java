package org.kilon.android.trainride.test;

import org.kilon.android.trainride.BartApplication;
import org.kilon.android.trainride.activities.Main;

import android.test.ActivityInstrumentationTestCase2;

public class ActivityTestCase extends ActivityInstrumentationTestCase2<Main> {

	protected BartApplication app;

	public ActivityTestCase() {
		super("org.kilon.android.trainride.activities", Main.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		app = (BartApplication)getActivity().getApplication();
		app.init();
	}
}