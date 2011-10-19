package org.kilon.android.trainride.model.station;

import java.util.Locale;

import android.location.Address;
import android.location.Location;
import android.location.LocationManager;

public class BartStation extends Location implements Station {
	
	private String id;
	private String name;
	private Address address = new Address(new Locale("en", "US"));
	private static FavoriteStationManager fm;

	public BartStation(String id, String name) {
		super(LocationManager.PASSIVE_PROVIDER);
		fm = FavoriteStationManager.getInstance();;
		this.id = id;
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Address getAddress() {
		return address;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	
	public String toString() {
		return name;
	}

	@Override
	public Location getLocation() {
		return this;
	}

	@Override
	public void setFavorite(Boolean setFavorite) {
		if ( setFavorite  )
			fm.add(this);
		else
			fm.remove(this);
	}

	@Override
	public Boolean isFavorite() {
		return fm.isFavorite(this);
	}

	@Override
	public int compareTo(Station another) {
		return getId().compareTo(another.getId());
	}
	
}
