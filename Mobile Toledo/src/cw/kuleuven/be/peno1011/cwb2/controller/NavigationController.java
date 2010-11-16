package cw.kuleuven.be.peno1011.cwb2.controller;

import cw.kuleuven.be.peno1011.cwb2.model.User;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class NavigationController {
	private static NavigationController navigationController;
	private User user;
	private LocationManager locationManager;
	private LocationListener listener;
	
	private NavigationController(User user) {
		this.user=user;
	}

	public static NavigationController getInstance(User user) {
		if (navigationController == null) {
			navigationController = new NavigationController(user);
		}
		return navigationController;
	}
	private void setLocation(){
		user.setLocation(locationManager.getLastKnownLocation("xyz"));
	}
	public void startUpdating(){
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500,15, listener);

	}
	class Listener implements LocationListener {

		public void onLocationChanged(Location location) {
			NavigationController.this.setLocation();
		}

		public void onProviderDisabled(String provider) {
			// TODO		
		}

		public void onProviderEnabled(String provider) {
			// TODO		
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO
		}
	}
    public void stopUpdating(){
    	locationManager.removeUpdates(listener);
    }
	

}
