package org.kilon.android.trainride.test.util.cacher;

import org.kilon.android.trainride.model.ride.RideGroupResults;
import org.kilon.android.trainride.util.cacher.Cacher;
import org.kilon.android.trainride.util.cacher.CacherFactory;

import android.test.AndroidTestCase;

public class RideResultsCacherTestCase extends AndroidTestCase {

	private static final String TAG = "RideResultsCacherTestCase";
	private static final String CACHE_KEY = "dummyCacheKey";

	private RideGroupResults res;
	Cacher<RideGroupResults> cacher;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		cacher = CacherFactory.getCacher(TAG);
		
	}

	public void testNotExisits() {
		cacher.clear();
		res = cacher.get(CACHE_KEY);
		assertNull(res);
	}

	public void testStorable() {
		cacher.clear();
		res = new RideGroupResults();
		
		cacher.put(CACHE_KEY, res, -1);
		
		res = cacher.get(CACHE_KEY);
		
		assertNotNull(res);

	}

	public void testExpireable() throws InterruptedException {

		synchronized (this) {
			cacher.clear();
			
			cacher.put(CACHE_KEY, new RideGroupResults(), 1);

			wait(900);
			res = cacher.get(CACHE_KEY);
			assertNotNull(res);
			
			wait(101);
			res = cacher.get(CACHE_KEY);
			assertNull(res);
		}

	}


}
