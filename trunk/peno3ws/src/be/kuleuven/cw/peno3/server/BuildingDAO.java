/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:22
 * 
 * This is the BuildingDAO class.
 * BuildingDAO will be used to communicate with our MySQL database.
 * All inside methods are called using the postmethods of a webservice.
 * All returned strings are encrypted following the standard of our Cryptography class.
 */
package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

// TODO: Auto-generated Javadoc
/**
 * The Class BuildingDAO.
 */
@Path ("/BuildingHandler")
public class BuildingDAO extends DAO{

	/**
	 * Gets the building.
	 *
	 * @param name the name
	 * @return the building
	 */
	@POST
	@Path ("/getBuilding")
	@Produces ("application/json")
	public String getBuilding(@FormParam("name") String name){
		String query = "SELECT * FROM building";
		if(name !=null)query += " WHERE name like '%" + name + "%'";
		return super.get(query);
	}
	
	/**
	 * Gets the building.
	 *
	 * @param name the name
	 * @return the building
	 */
	@POST
	@Path ("/getBuildingAndLocationByName")
	@Produces ("application/json")
	public String getBuildingAndLocationByName(@FormParam("name") String name){
		String query = "SELECT * FROM building INNER JOIN location USING(locationId)";
		if(name !=null)query += " WHERE name like '%" + name + "%'";
		return super.get(query);
	}
	
	/**
	 * Gets the building.
	 *
	 * @param locationId the locationId
	 * @return the building
	 */
	@POST
	@Path ("/getBuildingAndLocationById")
	@Produces ("application/json")
	public String getBuildingAndLocationById(@FormParam("locationId") String locationId){
		String query = "SELECT * FROM building INNER JOIN location USING(locationId)";
		if(locationId !=null)query += " WHERE locationId like '" + locationId + "'";
		return super.get(query);
	}
	
	/**
	 * Gets the building.
	 *
	 * @param locationId the locationId
	 * @return the building
	 */
	@POST
	@Path ("/getBuildingById")
	@Produces ("application/json")
	public String getBuildingById(@FormParam("locationId") String locationId){
		String query = "SELECT * FROM building";
		if(locationId !=null)query += " WHERE locationId like '" + locationId + "'";
		return super.get(query);
	}

	/**
	 * List buildings. Results are ordered by acending buildingname.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listBuildings")
	@Produces ("application/json")
	public String listBuildings(){
		String query = "SELECT * FROM building ORDER BY name ASC";
		return super.list(query);
	}
	
	/*
	 * Method adds an announcement to the database	
	 */
	/**
	 * Adds the building.
	 *
	 * @param locationId the location id
	 * @param name the name
	 * @param openinghours the openinghours
	 * @param phonenumber the phonenumber
	 * @param isRentable the is rentable
	 * @param googleMap the google map
	 * @return the resultstring
	 */
	@POST
	@Path ("/addBuilding")
	@Produces ("application/json")
	public String addBuilding(@FormParam("locationId") String locationId, @FormParam("name") String name, @FormParam("openinghours") String openinghours, @FormParam("phonenumber") String phonenumber){
		String query = "INSERT INTO building (locationId,name,openinghours,phonenumber,googleMap) VALUES ('" +locationId + "','"+ name + "','" + openinghours + "','" + phonenumber +"')";
		return super.add(query);
	}
	
	/**
	 * Adds the map.
	 *
	 * @param locationId the location id
	 * @param map the map
	 * @return the resultstring
	 */
	@POST
	@Path ("/addMap")
	@Produces ("application/json")
	public String addMap(@FormParam("locationId") String locationId, @FormParam("map") String map){
		String query = "INSERT INTO building_map (buildingMapId,locationId,map) VALUES (NULL,'"+ locationId + "','" + map + "')";
		return super.add(query);
	}
	
	/**
	 * Gets the map.
	 *
	 * @param locationId the location id
	 * @return the map
	 */
	@POST
	@Path ("/getMap")
	@Produces ("application/json")
	public String getMap(@FormParam("locationId") String locationId){
		String query = "SELECT * FROM building_map";
		if(locationId !=null)query += " WHERE locationId like '" + locationId + "'";
		return super.get(query);
	}
	
	/**
	 * Adds the picture.
	 *
	 * @param locationId the location id
	 * @param picture the picture
	 * @return the resultstring
	 */
	@POST
	@Path ("/addPicture")
	@Produces ("application/json")
	public String addPicture(@FormParam("locationId") String locationId, @FormParam("picture") String picture){
		String query = "INSERT INTO building_picture (buildingPictureId,locationId,picture) VALUES (NULL,'"+ locationId + "','" + picture + "')";
		return super.add(query);
	}
	
	/**
	 * Gets the picture.
	 *
	 * @param locationId the location id
	 * @return the picture
	 */
	@POST
	@Path ("/getPicture")
	@Produces ("application/json")
	public String getPicture(@FormParam("locationId") String locationId){
		String query = "SELECT * FROM building_picture";
		if(locationId !=null)query += " WHERE locationId like '" + locationId + "'";
		return super.get(query);
	}
}
