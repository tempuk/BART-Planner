package org.kilon.android.trainride.test.util.cacher;

import org.kilon.android.trainride.util.cacher.Cacher;
import org.kilon.android.trainride.util.cacher.CacherFactory;

import android.test.AndroidTestCase;

public class CacherTestCase extends AndroidTestCase {

	private Cacher<String> cacher1;
	private Cacher<String> cacher2;
	
	private static final String KEY = "key"; 
	private static final String CACHE_KEY_1 = "key1"; 
	private static final String CACHE_KEY_2 = "key2"; 
	private static final String VALUE = "value"; 

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		cacher1 =  CacherFactory.getCacher(CACHE_KEY_1);
		assertNotNull(cacher1);
		
		cacher2 = CacherFactory.getCacher(CACHE_KEY_2);
		assertNotNull(cacher2);
		
		assertNotSame(cacher1, cacher2);

	}

	public void testCacheExpires() throws InterruptedException {

		synchronized (this) {
			
			assertNull(cacher1.get(KEY));
			
			cacher1.put(KEY, VALUE, 1);
			assertEquals(VALUE, cacher1.get(KEY));
			
			wait(900);
			assertEquals(VALUE, cacher1.get(KEY));

			wait(101);
			assertNull(cacher1.get(KEY));
			
			reset();
		}

	}
	
	public void testCacherUnique() {
		
		cacher1.put(KEY, VALUE, 1);
		Object value2 = cacher2.get(KEY);
		assertNull("value is " + value2, value2);
		
		reset();
	}
	
	public void testClearCache() {
		
		cacher1.put(KEY, VALUE, 30);
		assertEquals(VALUE, cacher1.get(KEY));
		
		cacher1.clear(KEY);
		assertNull(cacher1.get(KEY));
		
		cacher1.put(KEY, VALUE, 30);
		assertEquals(VALUE, cacher1.get(KEY));
		
		cacher2.put(KEY, VALUE, 30);
		assertEquals(VALUE, cacher2.get(KEY));
		
		assertEquals(cacher1.get(KEY), cacher2.get(KEY));
		assertSame(cacher1.get(KEY), cacher2.get(KEY));
		
		reset();
		
		assertNull(cacher1.get(KEY));
		assertNull(cacher2.get(KEY));
		
	}
	
	
	private void reset() {
		cacher1.clear();
		cacher2.clear();
	}

}
