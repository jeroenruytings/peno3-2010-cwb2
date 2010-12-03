
package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Iterator;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.database.BuildingDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.view.OwnLocationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

//opmerking: user kan statisch opgeslagen worden in de globale controller,
//daarom kunnen we user hier als veld schrappen een gewoon daar een statische get oproepen
public class NavigationController{
	private BuildingDAO dao;
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
	//	dao = BuildingDAO.getInstance();
		//ArrayList<Building> buildings = dao.getBuildings();
		String [] buildingnames = new String []{
		//Iterator<Building> it = buildings.iterator(); 
			//{	
				//	while (it.hasNext()){
					//buildingnames.add(it.next().getName());
					//}
			//}
			
			"haai","hoi","hoor","heftig","helding shana","Lise is de max!"
		};
		
		return buildingnames;
	} 
	public String[] getRoomNames()
	{
		//zelfde als getBuildingNames()
		// use dao.getRooms();
		String [] roomnames = new String []{
				"kamer", "kat","kooi, kraai"
		};
		return roomnames;
		
	}
	
	public boolean buildingExists(String buildingname)
	{
		Boolean existing = false;
		dao = BuildingDAO.getInstance();
		existing = dao.buildingExists(buildingname);
		existing = true;
		return existing;
	}
	
	  public Bitmap downloadFile(String fileUrl){
		  Bitmap bmImg = null;
          URL myFileUrl = null;          
          try {
               myFileUrl= new URL(fileUrl);
          } catch (MalformedURLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
          try {
               HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
               conn.setDoInput(true);
               conn.connect();
               int length = conn.getContentLength();
               InputStream is = conn.getInputStream();
               
               bmImg = BitmapFactory.decodeStream(is);
                              
          } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
          return bmImg;
	  }

	
	public Bitmap[] getPictureArray(String locationname)
	{
		Bitmap img = downloadFile("http://www.trouwshop.nl/images/upload/20080907_234513_bruidspaar_gira.jpg");
		Bitmap img2 = downloadFile("http://t2.gstatic.com/images?q=tbn:a-HKIJ5FgdO_VM:http://www.facebook.com/profile/pic.php?uid=AAAAAQAQZZMBjRztdGKy7zNLtrdyDAAAAApfkafD0SgsVaSJ4V-Hc7KA");
		Bitmap img3 = downloadFile("http://www.appelogen.be/wp-images/derde_prentje.jpg");
		Bitmap img4 = downloadFile("http://www.jouwpagina.nl/fotos2/k-mmygirl/catduck.jpg");
		//String urlString = "http://www.trouwshop.nl/images/upload/20080907_234513_bruidspaar_gira.jpg";
		//Uri[] pictureArray ={
  
				//Uri.parse("http://t2.gstatic.com/images?q=tbn:a-HKIJ5FgdO_VM:http://www.facebook.com/profile/pic.php?uid=AAAAAQAQZZMBjRztdGKy7zNLtrdyDAAAAApfkafD0SgsVaSJ4V-Hc7KA"),
				//Uri.parse("http://www.appelogen.be/wp-images/derde_prentje.jpg"),
				//Uri.parse("http://www.jouwpagina.nl/fotos2/k-mmygirl/catduck.jpg"),
	//	Uri.parse(urlString)
		//};
		
		Bitmap [] pictureArray = {img,img2,img3,img4};
			
		return pictureArray;
	}
	
	public Uri getGoogleMap(String location)
	{
		Uri uri = null;
		return uri;
	}
	
	public void startUpdating(){
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500,15, listener);

	}


    public void stopUpdating(){
    	locationManager.removeUpdates(listener);
    }

	public String getOpeninghours(String location) {
		// geef de openingsuren
		String openinghours = "openingsuren";
		return openinghours;
	}

	public String getAdresse(String location) {
		// geef het adres
		String adresse = "adres";
		return adresse;
	}

	public String getTelephonenr(String location) {
		// geef het telefoonnr
		String telephonenr = "telefoonnr";
		return telephonenr;
	}

	public String isRentable(String location) {
		// geef of m verhuurbaar is
		String rentable = "ja";
		String isrentable = "Mogelijkheid tot huren: " + rentable;
		return null;
	}
	

}
