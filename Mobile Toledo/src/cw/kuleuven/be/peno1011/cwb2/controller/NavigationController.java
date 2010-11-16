package cw.kuleuven.be.peno1011.cwb2.controller;

//import cw.kuleuven.be.peno1011.cwb2.model.GPSLocation;

import android.location.Location;
import android.location.LocationManager;

public class NavigationController {
	
	private static NavigationController navigationController;
	private Location location;
	private LocationManager locationManager;
	
	private NavigationController() {
		
	}
	
	public static NavigationController getInstance() {
		if (navigationController == null) {
			navigationController = new NavigationController();
		}
		return navigationController;
	}
	public void setLocation()
	{
		location = locationManager.getLastKnownLocation("xyz");
	}
	public Location getLocation()
	{
		return location;
	}
//	public void drawRoute(Location location) {
//		int[] locCoordinates = location.getCoordinates();
//		int X=locCoordinates[1];
//		int Y=locCoordinates[2];
//		
//	}
	

}
