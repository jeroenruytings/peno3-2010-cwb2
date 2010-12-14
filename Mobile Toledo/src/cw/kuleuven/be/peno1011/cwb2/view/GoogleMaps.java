package cw.kuleuven.be.peno1011.cwb2.view;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
	    locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    if (location == null){
	    	Intent intent = new Intent(GoogleMaps.this,NavigationMenu.class);
	    	intent.putExtra("gps", "geengps");
	    	startActivity(intent);
	    }
	    else{
	    setContentView(R.layout.googlemaps);
	    //Set the mapview
	    mapView = (MapView) findViewById(R.id.mapview);
	    //show zoom controls on the map
	    mapView.setBuiltInZoomControls(true);
	    // maak een nieuwe overlay
	    mapOverlays = mapView.getOverlays();
	    ImageView b1 = (ImageView) findViewById(R.id.aangekomen);
	    b1.setVisibility(View.GONE);
	    ImageView b2 = (ImageView) findViewById(R.id.sat);
	    b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mapView.setSatellite(true);
			}
		});
	    ImageView b3 = (ImageView) findViewById(R.id.card);
	    b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mapView.setSatellite(false);
			}
		});
	    drawable = this.getResources().getDrawable(R.drawable.marker);
	    itemizedOverlay = new MapOverlay(drawable);
	    locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
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
	    mc.setZoom(15);
	    mapView.invalidate();
	}}

	@Override
	public void onLocationChanged(Location arg0) {
		
		 	double latitude =  arg0.getLatitude();
		    double longitude =  arg0.getLongitude();
		    GeoPoint point = new GeoPoint((int)(latitude*1E6),(int)(longitude*1E6));
		    //zet overlay bij laatste locatie
		    mapOverlays.clear();
		    OverlayItem overlayitem = new OverlayItem(point, "", "");
		    itemizedOverlay.removeOverlays();
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
