package org.kilon.android.trainride.model.route;

import org.kilon.android.trainride.model.station.Station;

public interface Route extends Comparable<Route> {

	String getName();
	String getAbbreviation();
	String getId();
	Integer getNumber();
	String getColor();
	
	Station getOrigin();
	Station getDestination();
}
