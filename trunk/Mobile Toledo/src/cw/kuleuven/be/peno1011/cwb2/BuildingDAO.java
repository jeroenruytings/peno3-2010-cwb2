package cw.kuleuven.be.peno1011.cwb2;

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
	
	public boolean insert(Building building) {
		boolean inserted=false;
		return inserted;
	}
	
	public boolean delete(Building building) {
		boolean deleted=false;
		return deleted;
	}
}
