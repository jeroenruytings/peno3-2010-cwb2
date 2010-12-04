package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path ("/BuildingHandler")
public class BuildingDAO extends DAO{

	@POST
	@Path ("/getBuilding")
	@Produces ("application/json")
	public String getBuilding(@QueryParam("name") String name){
		String query = "SELECT * FROM building";
		if(name !=null)query += " WHERE name like '%" + name + "%'";
		return super.get(query);
	}

	@POST
	@Path ("/listBuildings")
	@Produces ("application/json")
	public String listBuildings(){
		String query = "SELECT * FROM building";
		return super.list(query);
	}
	
	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addBuilding")
	@Produces ("application/json")
	public String addBuilding(@FormParam("name") String name, @FormParam("openinghours") String openinghours, @FormParam("phonenumber") String phonenumber, @FormParam("isRentable") String isRentable , @FormParam("googleMap") String googleMap){
		String query = "INSERT INTO building (locationId,name,openinghours,phonenumber,isRentable,googleMap) VALUES (NULL,'"+ name + "','" + openinghours + "','" + phonenumber + "','" + isRentable + "','" + googleMap +"')";
		return super.add(query);
	}
	
	@POST
	@Path ("/addMap")
	@Produces ("application/json")
	public String addMap(@FormParam("locationId") String locationId, @FormParam("map") String map){
		String query = "INSERT INTO building_map (buildingMapId,locationId,map) VALUES (NULL,'"+ locationId + "','" + map + "')";
		return super.add(query);
	}
	
}
