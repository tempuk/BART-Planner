package org.kilon.android.trainride.util.cacher;

import java.util.Date;

public class CachedObject<T> {

	private final Date date;
	private final String key;
	private final T value;

	public CachedObject(Date date, String key, T value) {
		this.date = date;
		this.value = value;
		this.key = key;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}