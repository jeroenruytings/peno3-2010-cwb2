package cw.kuleuven.be.peno1011.cwb2.view;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import cw.kuleuven.be.peno1011.cwb2.model.MapOverlay;

public class GoogleMaps extends MapActivity{
	private MapView mapView;
	private MapController mc;
	List<Overlay> mapOverlays;
	Drawable drawable;
	MapOverlay itemizedOverlay;
	private NavigationController navigationController;
	
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	/**Called when the activity is first created*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.googlemaps);
	    
	    //Set the mapview
	    mapView = (MapView) findViewById(R.id.mapview);
	    
	    //show zoom controls on the map
	    mapView.setBuiltInZoomControls(true);
	    // maak een nieuwe overlay
	    mapOverlays = mapView.getOverlays();
	    drawable = this.getResources().getDrawable(R.drawable.marker);
	    itemizedOverlay = new MapOverlay(drawable);
	    //vraag een nieuwe locationmanager op en vraag laatste locatie
	    LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    int latitude = (int) location.getLatitude();
	    int longitude = (int) location.getLongitude();
	    GeoPoint point = new GeoPoint((int)(latitude*1E6),(int)(longitude*1E6));
	    //zet overlay bij laatste locatie
	    OverlayItem overlayitem = new OverlayItem(point, "", "");
	    itemizedOverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedOverlay);
	    //teken de kaart
        mc = mapView.getController();
        mc.animateTo(point);
        mapView.invalidate();

        
	}
	

}
