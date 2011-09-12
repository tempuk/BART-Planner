package org.kilon.android.trainride.activities;

/*
import java.util.ArrayList;
import java.util.List;

import org.kilon.android.trainride.R;
import org.kilon.android.trainride.model.station.Station;
import org.kilon.android.trainride.model.station.StationManager;

import android.app.AlertDialog;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
*/

public class _GoogleMap { /* extends MapActivity {

	private static final int WIDTH = 2;
	private static final int HEIGHT = 2;
	private MapView mapView; 
	private GeoPoint point;

	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);

		mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);

		List<Overlay> mapOverlays = mapView.getOverlays();
		
		Drawable marker = new ScaleDrawable(getResources().getDrawable(R.drawable.logo_bart), 0, WIDTH, HEIGHT).getDrawable();
		marker.setBounds(0,0,WIDTH,HEIGHT);
		
		MyItemizedOverlay itemizedoverlay = new MyItemizedOverlay(marker);

		for ( Station s : StationManager.getInstance().getAll() ) {
			int lat = (int) (s.getLocation().getLatitude() * 1E6);
			int lon = (int) (s.getLocation().getLongitude() * 1E6);
			point = new GeoPoint(lat, lon);
			OverlayItem overlayitem = new OverlayItem(point, s.getName() + " Station", s.getAddress().getAddressLine(0) + "\n" + s.getAddress().getLocality() + ", " + s.getAddress().getAdminArea());
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);
		}

		MapController mapController = mapView.getController();
		
		mapController.animateTo(point);
		mapController.setZoom(18);
		
		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {

		private List<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
		private Drawable marker;

		public MyItemizedOverlay(Drawable defaultMarker) {
			super(defaultMarker);
			marker = defaultMarker;
			
		}

		@Override
		protected OverlayItem createItem(int i) {
			return mOverlays.get(i);
		}

		@Override
		public int size() {
			return mOverlays.size();
		}

		public void addOverlay(OverlayItem overlay) {
			mOverlays.add(overlay);
			populate();
		}
		
		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {
			super.draw(canvas, mapView, shadow);
			
			marker.setBounds(0,0,1,1);
			boundCenterBottom(marker);
			
		}

		@Override
		protected boolean onTap(int index) {
			OverlayItem item = mOverlays.get(index);
			AlertDialog.Builder dialog = new AlertDialog.Builder(_GoogleMap.this);
			dialog.setTitle(item.getTitle());
			dialog.setMessage(item.getSnippet());
			dialog.show();
			return true;
		}

	}
	*/
}
