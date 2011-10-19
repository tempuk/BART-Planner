package org.kilon.android.trainride.util.cacher;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

public class Cacher<T> {

	private static final String TAG = "Cacher";

	private final Map<String, CachedObject<T>> cache = new HashMap<String, CachedObject<T>>();

	private final String cacherId;
	
	protected Cacher(String cacherId) {
		this.cacherId = cacherId;
		clear();
	}
	
	/**
	 * 
	 * @return the cacher ID
	 */
	public String getId() {
		return cacherId;
	}
	
	
	public boolean has(String cacheKey) {
		return cache.containsKey(cacheKey);
	}

	/**
	 * Retrieve an object from cache
	 * 
	 * @param cacheKey - cached object unique identifier
	 * @return The stored object
	 */
	public T get(String cacheKey) {

		if ( ! has(cacheKey) ) {
			Log.d(TAG, getId() + " :: cache is empty: " + cacheKey);
			return null;
		}

		CachedObject<T> cachedObject = cache.get(cacheKey);

		Date expiresDate = cachedObject.getDate();
		Date now = ((Calendar) Calendar.getInstance().clone()).getTime();

		if ( expiresDate != null && expiresDate.before(now) ) {
			Log.d(TAG, getId() + " :: cache expired: " + cacheKey);
			return null;
		}
		
		Log.d(TAG, getId() + " :: cache is valid: " + cacheKey);

		return cachedObject.getValue();
	}

	/**
	 * Store an object in cache
	 * 
	 * @param cacheKey - cached object unique identifier
	 * @param value - value to store in cache
	 * @param ttl - Time to live is seconds
	 */
	public void put(String cacheKey, T value, int ttl) {
		Date expiresDate = null;
		if ( ttl > 0 ) {
			Calendar cal = (Calendar) Calendar.getInstance().clone();
			cal.add(Calendar.SECOND, ttl);
			expiresDate = cal.getTime();
		}
		cache.put(cacheKey, new CachedObject<T>(expiresDate, cacheKey, value));
	}

	/**
	 * Clear a specific entry from cache
	 * 
	 * @param cacheKey - cached object unique identifier
	 */
	public void clear(String cacheKey) {
		Log.d(TAG, getId() + " :: Cacher.clear("+cacheKey+") triggered");
		if ( cache.containsKey(cacheKey) )
			cache.remove(cacheKey);	
	}

	/**
	 * Reset the cacher (clear all cache)
	 */
	public void clear() {
		Log.d(TAG, getId() + " :: Cacher.clear triggered");
		cache.clear();
	}

}
