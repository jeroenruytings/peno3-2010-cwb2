package cw.kuleuven.be.peno1011.cwb2.database;


import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.Room;

public class BuildingDAO {
	
	private static BuildingDAO buildingDAO;
	
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
	
	public ArrayList<Building> getBuildings()
	{
		ArrayList<Building> buildings = null;
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
		int lat = 0;
		int lng = 0;
		// vraag latitude en longitude op 
		GeoPoint gp = new GeoPoint((int)(lat*1E6),(int)(lng*1E6));
		return gp;
	}
}
