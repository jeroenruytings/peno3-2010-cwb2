package cw.kuleuven.be.peno1011.cwb2.controller;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Iterator;

import cw.kuleuven.be.peno1011.cwb2.database.BuildingDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.view.OwnLocationManager;
import android.content.Context;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

//opmerking: user kan statisch opgeslagen worden in de globale controller,
//daarom kunnen we user hier als veld schrappen een gewoon daar een statische get oproepen
public class NavigationController{
	private static NavigationController navigationController;
	private static LocationListener listener;
	private Criteria criteria;
	private LocationManager locationManager;
	
	private NavigationController() {
		criteria = new Criteria();
		//singleton pattern
	}

	public static NavigationController getInstance() {
		if (navigationController == null) {
			navigationController = new NavigationController();
		}
		return navigationController;
	}
	public Location getLocation(){		
		OwnLocationManager olm = new OwnLocationManager();
		Location location = olm.getOwnLocation();
		
		return location;
	}
	
	public String[] getBuildingNames()
	{
	//	BuildingDAO dao = BuildingDAO.getInstance();
		//ArrayList<Building> buildings = dao.getBuildings();
		String [] buildingnames = new String []{
		//Iterator<Building> it = buildings.iterator(); 
			//{	
				//	while (it.hasNext()){
					//buildingnames.add(it.next().getName());
					//}
			//}
			
			"haai","hoi","hoor","heftig","helding shana"
		};
		
		return buildingnames;
	} 
	
	public void startUpdating(){
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500,15, listener);

	}
	class Listener implements LocationListener {

		public void onLocationChanged(Location location) {

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
