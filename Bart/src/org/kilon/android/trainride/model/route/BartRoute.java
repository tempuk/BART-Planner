package org.kilon.android.trainride.model.route;

import org.kilon.android.trainride.model.station.Station;

public class BartRoute implements Route {

	@SuppressWarnings("unused")
	private static final String TAG = "BartRoute";
	
	private String name;
	private String abbr;
	private String id;
	private int number;
	private String color;
	private Station origin;
	private Station destination;

	public BartRoute(Station origin, Station destination, String name, String abbr, String id, String number, String color) {
		this.name = name;
		this.abbr = abbr;
		this.id = id;
		
		this.number =  Integer.parseInt(number);
		this.color = color;
		
		this.origin = origin;
		this.destination = destination;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Integer getNumber() {
		return number;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public String getAbbreviation() {
		return abbr;
	}

	@Override
	public Station getOrigin() {
		return origin;
	}

	@Override
	public Station getDestination() {
		return destination;
	}

	@Override
	public int compareTo(Route another) {
		return getId().compareTo(another.getId());
	}

}
