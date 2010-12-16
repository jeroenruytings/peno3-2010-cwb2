
package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.maps.GeoPoint;

import cw.kuleuven.be.peno1011.cwb2.database.BuildingDAO;
import cw.kuleuven.be.peno1011.cwb2.database.RoomDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.Room;
import cw.kuleuven.be.peno1011.cwb2.view.OwnLocationManager;

//opmerking: user kan statisch opgeslagen worden in de globale controller,
//daarom kunnen we user hier als veld schrappen een gewoon daar een statische get oproepen
public class NavigationController {
	private RoomDAO dao2;
	private BuildingDAO dao1;
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
		String [] buildingnames = null;
		dao1 = BuildingDAO.getInstance();
		try {
			buildingnames = dao1.listBuildingNames();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return buildingnames;
	}
	
	public String[] getRoomNames()
	{
		String [] roomnames = null;
		dao2 = RoomDAO.getInstance();
		try {
			roomnames = dao2.listRoomNames();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomnames;
	}
	
	
	public boolean buildingExists(String buildingname) 
	{
		Boolean existing = false;
		dao1 = BuildingDAO.getInstance();
		try {
			existing = dao1.buildingExists(buildingname);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existing;
	}
	
	 public boolean roomExists(String roomname)
		{
			Boolean existing = false;
			dao2 = RoomDAO.getInstance();
			try {
				existing = dao2.roomExists(roomname);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return existing;
		}
	 
	 
		public static Bitmap downloadFile(String urlString) {
			
			    try {
			        URL url = new URL(urlString);
			        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			        connection.setDoInput(true);
			        connection.connect();
			        InputStream in = connection.getInputStream();
			        Bitmap mImg = BitmapFactory.decodeStream(in);
			        return mImg;
			    } catch (IOException e) {
			        e.printStackTrace();
			        return null;
			    }
			}
          
        

	
	public Bitmap[] getPictureArray(String locationname, boolean isbuilding)
	{
		String[] links = null;
		try {
			if(isbuilding == true){
			links = BuildingDAO.getInstance().getPictures(locationname);
			}	
			else if (isbuilding == false){
			links = RoomDAO.getInstance().getPictures(locationname);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		if(links != null){
		
			for(int i = 0 ; i<links.length ; i++ ){
				links[i] = links[i].replaceAll(" ", "%20");
				links[i] = links[i].replace("http","https");
				links[i] = links[i].replace("/foto.htm?img=","/");
				links[i] = links[i].split("jpg")[0]+"jpg";
				
			}
			
		}
		
		
		Bitmap[] pictureArray = null;
		if (links != null){
			pictureArray = new Bitmap[links.length];
			for (int i = 0; i<links.length ; i++) {
				Bitmap img = downloadFile(links[i]);
				Array.set(pictureArray,i,img);		
			}
		}
			return pictureArray;		
	}

	
	public void startUpdating(){
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500,15, listener);

	}


    public void stopUpdating(){
    	locationManager.removeUpdates(listener);
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
		String buildingname = "";
		
		RoomDAO dao = RoomDAO.getInstance();
		Room room = null;
		try {
			room = dao.getRoom(location);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Building building = room.getBuilding();
		buildingname = building.getName();
		return buildingname;
		
	}
	


	

}
