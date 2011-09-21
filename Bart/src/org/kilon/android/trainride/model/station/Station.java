package org.kilon.android.trainride.model.station;

import android.location.Address;
import android.location.Location;


public interface Station extends Comparable<Station> {
	
	public static final String ID = "Station.ID";
	public static final String TYPE = "Station.TYPE";
	
	public static final String LAST_ORIGIN = "Station.LAST_ORIGIN";
	public static final String LAST_DESTINATION = "Station.LAST_DESTINATION";
	
	public static final String REAL_TIMES = "Station.REAL_TIMES";
	public static final String SCHEDULES = "Station.SCHEDULES";
	
	public static final String ORIGIN = "Station.ORIGIN";
	public static final String DESTINATION = "Station.DESTINATION";

	public static final String ALL = "Station.ALL";
	public static final String FAVORITES = "Station.FAVORITES";
	
	String getId();
	String getName();
	Address getAddress();
	Location getLocation();
	
	void setFavorite(Boolean isFavorite);
	Boolean isFavorite();
	
}
