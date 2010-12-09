package cw.kuleuven.be.peno1011.cwb2.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class RouteOverlay extends Overlay{
	
	 private GeoPoint gp;  
	 private GeoPoint gpp;  
	 private int color;  
	  
	public RouteOverlay(GeoPoint gp, GeoPoint gpp, int color) {  
	        this.gp = gp;  
	        this.gpp = gpp;  
	        this.color = color;  
	    }  
	//teken de route
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {  
		//vraag een projectie van de kaart op 
	    Projection projection = mapView.getProjection();  
	    Paint paint = new Paint();  
	    Point point = new Point();  
	    //bepaal de eigenschappen van de tekening
	    projection.toPixels(gp, point);  
	    paint.setColor(color);  
	    Point point2 = new Point();  
	    projection.toPixels(gpp, point2);  
	    paint.setStrokeWidth(5);  
	    paint.setAlpha(120);  
	    //teken een lijn
	    canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);  
	    super.draw(canvas, mapView, shadow);  
	}  
	
}
