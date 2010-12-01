package be.kuleuven.cw.peno3.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Path ("/EventHandler")
public class EventDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();

	public String getEvent(String searchString, String query) {
		String executeQuery = "SELECT * FROM event";
		
		if(searchString !=null){
			executeQuery = executeQuery + query;
		}
		
		String result = queryForEvents(executeQuery);
		manager.disconnect();
		return result;
	}

	@POST
	@Path ("/listEvents")
	@Produces ("application/json")
	public String listEvents(){

		String query = "SELECT * FROM event";
		String result = queryForEvents(query);
		manager.disconnect();
		return result;
	}

	private String queryForEvents(String query) {
		JsonArray events = new JsonArray();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				JsonObject event = (JsonObject) gson.toJsonTree(manager.getColumnValues(rs));
				events.add(event);
			}
		} catch (SQLException e) {
			JSONObject result = new JSONObject();
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		String asString = events.toString();
		return asString;
	}

	private JsonArray querySimpleTable(String query) {
		Vector announcements = new Vector();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				announcements.add(manager.getColumnValues(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (JsonArray) gson.toJsonTree(announcements);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addEvent")
	@Produces ("application/json")
	public String addEvent(@FormParam("description") String description, @FormParam("startDate") String startDate, @FormParam("stopDate") String stopDate, @FormParam("locationId") String locationId, @FormParam("categorie") String categorie, @FormParam("title") String title){

		JSONObject result = new JSONObject();
		try {
			String query = "INSERT INTO event (eventId,description,startDate,stopDate,locationId,categorie,title) VALUES (NULL,'"+ description + "'," + startDate + "," + stopDate + ",'" + locationId + "','" + categorie + "','" + title +"')";
			System.out.println(query);
			manager.update(query);
			manager.disconnect();
			}
		catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		result.put("result", "Event sucessfully added");
		return result.toString();
	}
}