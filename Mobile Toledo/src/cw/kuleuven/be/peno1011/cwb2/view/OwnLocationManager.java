package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class OwnLocationManager extends Activity {

public Location getOwnLocation()

{
	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
	return location;
	
}

}
