package org.kilon.android.trainride.test.model.station;

import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;
import org.kilon.android.trainride.test.ActivityTestCase;

import android.location.Address;
import android.location.Location;

public class StationTestCase extends ActivityTestCase {

	public StationTestCase() {
		super();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testNumStations() {
		assertEquals(44, StationManager.getInstance().getAll().size());
	}
	
	public void testNullStation() {
		Station station = StationManager.get("POOP");
		assertNull(station);
	}
	
	public void testStation() {

		Station station = StationManager.get("ASHB");
		assertNotNull(station);
		assertEquals("Ashby", station.getName());
		
		Address address = station.getAddress();
		assertNotNull(address);
		assertEquals("3100 Adeline Street", address.getAddressLine(0));
		assertEquals("Berkeley", address.getLocality());
		assertEquals("CA", address.getAdminArea());
		assertEquals("94703", address.getPostalCode());
		
		Location loc = station.getLocation();
		assertNotNull(loc);
		assertEquals(37.8, loc.getLatitude(), 0.09);
		assertEquals(-122.2, loc.getLongitude(), 0.09);
	}
	
	public void testStation1() {
		Station station = StationManager.get("DELN");
		assertEquals("El Cerrito del Norte", station.getName());
		
		Address address = station.getAddress();
		assertEquals("6400 Cutting Blvd.", address.getAddressLine(0));
		assertEquals("El Cerrito", address.getLocality());
		assertEquals("CA", address.getAdminArea());
		assertEquals("94530", address.getPostalCode());
		
		Location loc = station.getLocation();
		assertEquals(37.9, loc.getLatitude(), 0.09);
		assertEquals(-122.3, loc.getLongitude(), 0.09);
	}
	
	
	public void testStationFavorites() {
		
		Station station = StationManager.get("LAKE");
		assertNotNull(station);
		
		// save local copy of the station status to revert later
		Boolean isFavorite = station.isFavorite();
		
		station.setFavorite(true);
		assertTrue(station.isFavorite());
		station.setFavorite(true);
		assertTrue(station.isFavorite());
		
		station.setFavorite(false);
		assertFalse(station.isFavorite());
		station.setFavorite(false);
		assertFalse(station.isFavorite());
		
		// revert to original status
		station.setFavorite(isFavorite);
	}
	

}
