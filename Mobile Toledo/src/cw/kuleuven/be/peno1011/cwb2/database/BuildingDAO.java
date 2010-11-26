package cw.kuleuven.be.peno1011.cwb2.database;


import java.util.ArrayList;

import cw.kuleuven.be.peno1011.cwb2.model.Building;

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
}
