package cw.kuleuven.be.peno1011.cwb2.database;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.android.maps.GeoPoint;
import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.GPSLocation;
import cw.kuleuven.be.peno1011.cwb2.model.Room;
import cw.kuleuven.be.peno1011.cwb2.model.User;

public class BuildingDAO {
	
	private static BuildingDAO buildingDAO;
	private Cryptography cryptography = Cryptography.getInstance();
	
	// Singleton has a private constructor
	private BuildingDAO(){
		
	}
	
	public static BuildingDAO getInstance() {
		if (buildingDAO == null){
			buildingDAO = new BuildingDAO();
		}
		return buildingDAO;
	}
	//TO DO
	public boolean insert(Building building) {
		boolean inserted=false;
		return inserted;
	}
	//TO DO
	public boolean delete(Building building) {
		boolean deleted=false;
		return deleted;
	}
	
	public ArrayList<Building> listBuildings() throws HttpException, IOException{
		ArrayList<Building> buildings = new ArrayList<Building>();		
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/BuildingHandler/listBuildings");
				
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		if(json.contains("[]")) {
			buildings = null;
		}
		else {
			Building[] buildingArray = new Gson().fromJson(json.toString(), Building[].class);
			buildings = (ArrayList<Building>) Arrays.asList(buildingArray);
		}
		return buildings;
	}

	
	public ArrayList<Room> getRooms()
	{
		ArrayList<Room> buildings = null;
		return buildings;
	}
	
	public Boolean buildingExists(String buildingname)
	{
		Boolean exists = false;
		
		// if naam komt voor in database exists = true;
		
		return exists;
	}
	public Boolean roomExists(String roomname)
	{
		Boolean exists = false;
		
		// if naam komt voor in database exists = true;
		
		return exists;
	}
	public GeoPoint getBuildingCoordinates(String buildingname){
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/BuildingHandler/getBuilding");
		method.addParameter("name", buildingname);
		
		int response;
		try {
			response = client.executeMethod(method);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encryptedJson = null;
		try {
			encryptedJson = method.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = cryptography.decrypt(encryptedJson);
		
		Building[] buildings = new Gson().fromJson(json.toString(), Building[].class);
		Building currentbuilding = buildings[0];
		
		int startLocationId = json.indexOf("locationId")+12;
		int endLocationId = json.indexOf(",", startLocationId);
		String locationId = json.substring(startLocationId,endLocationId);
		
		PostMethod methodLocation = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/LocationHandler/getLocation");
		methodLocation.addParameter("locationId", locationId);
		
		try {
			response = client.executeMethod(methodLocation);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encryptedJsonLocation = null;
		try {
			encryptedJsonLocation = methodLocation.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonLocation = cryptography.decrypt(encryptedJsonLocation);
		
		GPSLocation[] locations = new Gson().fromJson(jsonLocation.toString(), GPSLocation[].class);
		GPSLocation currentlocation = locations[0];
		
		currentbuilding.setLocation(currentlocation);
		 
		double lat = currentbuilding.getLocation().getXcoordinate();
		double lng = currentbuilding.getLocation().getYcoordinate();
		// vraag latitude en longitude op 
		GeoPoint gp = new GeoPoint((int)(lat*1E6),(int)(lng*1E6));
		return gp;
	}
}
