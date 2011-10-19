package org.kilon.android.trainride.test.model.route;

import org.kilon.android.trainride.model.route.Route;
import org.kilon.android.trainride.model.route.RouteManager;
import org.kilon.android.trainride.test.ActivityTestCase;

public class RouteTest extends ActivityTestCase {

	private Route route;

	public RouteTest() {
		super();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testRoute() {
		route = RouteManager.getInstance().get("ROUTE 12");
		assertEquals("DALY-DUBL", route.getAbbreviation());
		assertEquals("Daly City - Dublin/Pleasanton", route.getName());
		assertEquals("#0099cc", route.getColor());
	}
	
	public void testRoute1() {
		route = RouteManager.getInstance().get("ROUTE 5");
		assertEquals("FRMT-DALY", route.getAbbreviation());
		assertEquals("Fremont - Daly City", route.getName());
		assertEquals("#339933", route.getColor());
	}
	
	public void testRoute2() {
		route = RouteManager.getInstance().get("ROUTE 22");
		assertNull(route);
	}
	
	public void testNumRoutes() {
		assertEquals(10, RouteManager.getInstance().numRoutes());
	}
}
