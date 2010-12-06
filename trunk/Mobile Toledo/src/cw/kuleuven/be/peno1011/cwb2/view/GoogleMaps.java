package cw.kuleuven.be.peno1011.cwb2.view;
import java.util.ArrayList;


import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import cw.kuleuven.be.peno1011.cwb2.model.MapOverlay;
import cw.kuleuven.be.peno1011.cwb2.model.User;


import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.MapView.LayoutParams;  
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.MapView.LayoutParams;
import android.R.drawable;

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
 
	    mapOverlays = mapView.getOverlays();
	    drawable = this.getResources().getDrawable(R.drawable.marker);
	    itemizedOverlay = new MapOverlay(drawable);
	    LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    int latitude = (int) location.getLatitude();
	    int longitude = (int) location.getLongitude();
	    GeoPoint point = new GeoPoint((int)(latitude*1E6),(int)(longitude*1E6));
	    OverlayItem overlayitem = new OverlayItem(point, "", "");
	    itemizedOverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedOverlay);
	       //draw the map
	    
        mapView.invalidate();
        mc = mapView.getController();
        mc.animateTo(point);
        
	}
	

}
