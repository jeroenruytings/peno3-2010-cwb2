package cw.kuleuven.be.peno1011.cwb2.view;
import java.util.ArrayList;


import cw.kuleuven.be.peno1011.cwb2.R;
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
    private GeoPoint p;
    private LocationManager locationManager;
	private LocationListener listener;
	private Location location;
	private GeoPoint gp;
	private String locationprovider;
	List<Overlay> mapOverlays;
	Drawable drawable;
	MapOverlay itemizedOverlay;
	
	
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
	    drawable = this.getResources().getDrawable(R.drawable.androidmarker);
	    itemizedOverlay = new MapOverlay(drawable);
	    GeoPoint point = new GeoPoint((int)50.863408*1000000,(int)4.676775*1000000);
	    OverlayItem overlayitem = new OverlayItem(point, "", "");
	    itemizedOverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedOverlay);
	    //draw the map
	    
        mapView.invalidate();
        mc = mapView.getController();
        mc.animateTo(point);
        
	}
	
	class MapOverlay extends com.google.android.maps.ItemizedOverlay {
		
		private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
		
		public MapOverlay(Drawable defaultMarker) {
			super(boundCenterBottom(defaultMarker));
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

	}
}
