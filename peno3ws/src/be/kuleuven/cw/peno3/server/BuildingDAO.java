package be.kuleuven.cw.peno3.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Path ("/BuildingHandler")
public class BuildingDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();
	private Cryptography cryptography = Cryptography.getInstance();

	@POST
	@Path ("/getBuilding")
	@Produces ("application/json")
	public String getAnnouncement(@QueryParam("name") String name){
		String query = "SELECT * FROM building";
		if(name !=null)query += " WHERE name like '%" + name + "%'";
		String result = queryForBuildings(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}

	@POST
	@Path ("/listBuildings")
	@Produces ("application/json")
	public String listAnnouncements(){

		String query = "SELECT * FROM building";
		String result = queryForBuildings(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}

	private String queryForBuildings(String query) {
		JsonArray buildings = new JsonArray();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				JsonObject building = (JsonObject) gson.toJsonTree(manager.getColumnValues(rs));
				JsonElement jsonElement = building.get("locationId");
				if (!jsonElement.isJsonNull()) {
					int locationId = jsonElement.getAsInt();
					query = "SELECT * FROM locationId WHERE locationId='" + locationId + "'";
					JsonArray result = querySimpleTable(query);
					if(result.size() >0)building.add("location", result.get(0));
				}
				buildings.add(building);
			}
		} catch (SQLException e) {
			JSONObject result = new JSONObject();
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		String asString = buildings.toString();
		return asString;
	}

	private JsonArray querySimpleTable(String query) {
		Vector users = new Vector();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				users.add(manager.getColumnValues(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (JsonArray) gson.toJsonTree(users);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addBuilding")
	@Produces ("application/json")
	public String addBuilding(@FormParam("name") String name, @FormParam("openinghours") String openinghours, @FormParam("phonenumber") String phonenumber, @FormParam("isRentable") String isRentable , @FormParam("googleMap") String googleMap){

		JSONObject result = new JSONObject();
		try {
			String query = "INSERT INTO building (locationId,name,openinghours,phonenumber,isRentable,googleMap) VALUES (NULL,'"+ name + "','" + openinghours + "','" + phonenumber + "','" + isRentable + "','" + googleMap +"')";
			System.out.println(query);
			manager.update(query);
			manager.disconnect();
			}
		catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		result.put("result", "Building sucessfully added");
		return result.toString();
	}
	
	@POST
	@Path ("/addMap")
	@Produces ("application/json")
	public String addMap(@FormParam("locationId") String locationId, @FormParam("map") String map){

		JSONObject result = new JSONObject();
		try {
			String query = "INSERT INTO building_map (buildingMapId,locationId,map) VALUES (NULL,'"+ locationId + "','" + map + "')";
			System.out.println(query);
			manager.update(query);
			manager.disconnect();
			}
		catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		result.put("result", "Building sucessfully added");
		return result.toString();
	}
	
	/*
	 * Nog te schrijven: een slimme listbuilding die ineens ook alle maps meegeeft
	 */
	
}
