package org.kilon.android.trainride.util.cacher;

import java.util.HashMap;
import java.util.Map;

public class CacherFactory {

	@SuppressWarnings("rawtypes")
	private static Map<String, Cacher> cachers = new HashMap<String, Cacher>();

	@SuppressWarnings("unchecked")
	public static <T> Cacher<T> getCacher(String key) {
		if ( ! cachers.containsKey(key) )
			cachers.put(key, new Cacher<T>(key));

		return cachers.get(key);
	}

}
