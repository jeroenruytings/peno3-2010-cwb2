package cw.kuleuven.be.peno1011.cwb2.view;
import java.security.Provider;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import cw.kuleuven.be.peno1011.cwb2.model.MapOverlay;

public class GoogleMaps extends MapActivity implements LocationListener{
	private MapView mapView;
	private MapController mc;
	List<Overlay> mapOverlays;
	Drawable drawable;
	MapOverlay itemizedOverlay;
	private NavigationController navigationController;
	Location location;
	String provider;
	LocationManager locationmanager;
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
	    Button b1 = (Button) findViewById(R.id.aangekomen);
	    b1.setVisibility(View.GONE);
	    Button b2 = (Button) findViewById(R.id.sat);
	    b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mapView.setSatellite(true);
			}
		});
	    drawable = this.getResources().getDrawable(R.drawable.marker);
	    itemizedOverlay = new MapOverlay(drawable);
	    //vraag een nieuwe locationmanager op en vraag laatste locatie
	    locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	    location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    float a = location.getAccuracy();
		double latitude =  location.getLatitude();
		double longitude = location.getLongitude();
		GeoPoint point = new GeoPoint((int)(latitude*1E6),(int)(longitude*1E6));
		 //zet overlay bij laatste locatie
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay); 
		//teken de kaart
		mapView.setSatellite(false);
		mapView.setTraffic(true);
	    mc = mapView.getController();
	    mc.animateTo(point);
	    mapView.invalidate();
	}

	@Override
	public void onLocationChanged(Location arg0) {
		
		 double latitude =  arg0.getLatitude();
		    double longitude =  arg0.getLongitude();
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

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	

}
