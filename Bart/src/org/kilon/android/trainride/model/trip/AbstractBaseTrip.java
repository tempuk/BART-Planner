package org.kilon.android.trainride.model.trip;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.kilon.android.trainride.model.ride.Ride.ExtraArg;
import org.kilon.android.trainride.util.DateUtils;

abstract public class AbstractBaseTrip implements BaseTrip {

	private final Map<ExtraArg, Object> extraArgs = new HashMap<ExtraArg, Object>();
	
	@Override
	public void putExtra(ExtraArg key, Object value) {
		extraArgs.put(key, value);
	}

	@Override
	public Object getExtra(ExtraArg key) {
		return extraArgs.containsKey(key) ? extraArgs.get(key) : null;
	}
	
	
	@Override
	public long getLength() {
		
		Date depart = getDepartureDate();
		Date arrive = getArrivalDate();
		
		if ( null == depart || null == arrive ) {
			return -1;
		}
		
		return DateUtils.getMinutesDiff(depart, arrive);
	}

	protected Integer getInt(ExtraArg key) {
		String value = getString(key);
		if ( null == value )
			return null;
		
		return Integer.parseInt(value);
	}
	
	protected String getString(ExtraArg key) {
		Object value = getExtra(key);
		if ( null == value )
			return null;
		
		return value.toString();
	}
}
