
package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;

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
public class NavigationController {
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
		  Bitmap mImg = null;
          URL mFileUrl = null;          
          try {
               mFileUrl= new URL(fileUrl);
          } catch (MalformedURLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
          try {
               HttpURLConnection connection= (HttpURLConnection)mFileUrl.openConnection();
               connection.setDoInput(true);
               connection.connect();
               int length = connection.getContentLength();
               InputStream is = connection.getInputStream();
               
               mImg = BitmapFactory.decodeStream(is);
                              
          } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
          return mImg;
	  }

	
	public Bitmap[] getPictureArray(String locationname)
	{
		Bitmap img = downloadFile("http://www.trouwshop.nl/images/upload/20080907_234513_bruidspaar_gira.jpg");
		Bitmap img2 = downloadFile("http://t2.gstatic.com/images?q=tbn:a-HKIJ5FgdO_VM:http://www.facebook.com/profile/pic.php?uid=AAAAAQAQZZMBjRztdGKy7zNLtrdyDAAAAApfkafD0SgsVaSJ4V-Hc7KA");
		Bitmap img3 = downloadFile("http://www.appelogen.be/wp-images/derde_prentje.jpg");
		Bitmap img4 = downloadFile("http://www.jouwpagina.nl/fotos2/k-mmygirl/catduck.jpg");
		
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


	public static GeoPoint getCoordinates(String name) throws IOException {
		String nameurl;
		// Verwijder komma's uit name
    	if (name.contains(",")){
    	String[] split = name.split(",");
    	int i = 0;
    	String name1 = "";
    	while(i!=split.length){
    		name1 = name1 + split[i]+" ";
    		i++;
    	}
    	nameurl = name1; }
    	else{
    		nameurl = name;
    	}
    	//Vervang spaties in nameurl door %20
    	nameurl = nameurl.replace(" ", "%20");
    	
    	//Maak verbinding met httpURL
		String httpURL = "http://maps.google.com/maps/geo?q="+nameurl+"&output=csv&oe=utf8&sensor=false";
        URL myurl = new URL(httpURL);
        HttpURLConnection con = (HttpURLConnection)myurl.openConnection();
        InputStream ins = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in =new BufferedReader(isr);

        String inputLine = in.readLine();
        
        //Vraag de lengteligging en breedteligging op 
        int eerste = inputLine.indexOf(",");
        int tweede = inputLine.indexOf(",", eerste+1);
        int derde = inputLine.indexOf(",", tweede+1);
        String eerstegetal = inputLine.substring(tweede+1, derde);
        String tweedegetal = inputLine.substring(derde+1, inputLine.length());
        Double lat = Double.parseDouble(eerstegetal);
        Double lng = Double.parseDouble(tweedegetal);
        
        //Maak nieuw geopoint van de opgegeven plaats
        GeoPoint gp = new GeoPoint((int)(lat*1E6),(int)(lng*1E6));
        return gp;
	}

	public boolean isBuilding(String location) {
		// als de locatie een building is return true als de locatie een room is return false
		boolean isbuilding = true;
		return isbuilding;
	}

	public String getBuilding(String location) {
				// zoek het gebouw op waarin het lokaal zich bevindt
		String building = "";
		return building;
		
	}
	


	

}
