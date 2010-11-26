package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.RouteOverlay;

import android.app.Activity;
import android.os.Bundle;

public class ShowRoute extends MapActivity {
	
	private MapView mapView;
	private String encoded;
	private List<GeoPoint> gp;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googlemaps);
		mapView = (MapView)findViewById(R.id.mapview);
		GeoPoint from = new GeoPoint((int)50.86353*1000000,(int)4.680047*1000000);
		GeoPoint to = new GeoPoint((int)50.866888*1000000,(int)4.706182*1000000);
		getUrl(from,to);
		drawPath(gp,1);
		mapView.invalidate();
		
	}


	  
	public void getUrl(GeoPoint src, GeoPoint dest){  
	  
	StringBuilder urlString = new StringBuilder();  
	  
	urlString.append("http://maps.google.com/maps?f=d&hl=en");  
	urlString.append("&saddr=");  
	urlString.append(Double.toString((double) src.getLatitudeE6() / 1.0E6));  
	urlString.append(",");  
	urlString.append(Double.toString((double) src.getLongitudeE6() / 1.0E6));  
	urlString.append("&daddr=");// to  
	urlString.append(Double.toString((double) dest.getLatitudeE6() / 1.0E6));  
	urlString.append(",");  
	urlString.append(Double.toString((double) dest.getLongitudeE6() / 1.0E6));  
	urlString.append("&ie=UTF8&0&om=0&output=kml"); 
	encoded = urlString.toString();
	
	// get only the encoded geopoints  
    encoded = encoded.split("points:\"")[1].split("\",")[0];  
	// replace two backslashes by one (some error from the transmission)  
	encoded = encoded.replace("1", "1");  
	  
	//decoding  
	List<GeoPoint> poly = new ArrayList<GeoPoint>();  
	        int index = 0, len = encoded.length();  
	        int lat = 0, lng = 0;  
	  
	        while (index < len) {  
	            int b, shift = 0, result = 0;  
	            do {  
	                b = encoded.charAt(index++) - 63;  
	                result |= (b & 0x1f) << shift;  
	                shift += 5;  
	            } while (b >= 0x20);  
	            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));  
	            lat += dlat;  
	  
	            shift = 0;  
	            result = 0;  
	            do {  
	  
	                b = encoded.charAt(index++) - 63;  
	                result |= (b & 0x1f) << shift;  
	                shift += 5;  
	            } while (b >= 0x20);  
	            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));  
	            lng += dlng;  
	  
	            GeoPoint p = new GeoPoint((int) (((double) lat / 1E5) * 1E6), (int) (((double) lng / 1E5) * 1E6));  
	            poly.add(p);  
	        }
	        gp = poly;
	
	} 

	private void drawPath(List<GeoPoint> geoPoints, int color) {  
		   List overlays = mapView.getOverlays();  
		  
		   for (int i = 1; i < geoPoints.size(); i++) {  
		   overlays.add(new RouteOverlay(geoPoints.get(i - 1), geoPoints.get(i), color));  
		   }  
		}



	@Override
	protected boolean isRouteDisplayed() {

		return false;
	}  
}
