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

@Path ("/AnnouncementHandler")
public class AnnouncementDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();

	public String getAnnouncement(String searchString, String query) {
		String executeQuery = "SELECT * FROM announcement";
		System.out.println("executeQuery before:" + executeQuery);
		
		if(searchString !=null){
			executeQuery = executeQuery + query;
		}
		
		System.out.println("executeQuery after:" +executeQuery);
		String result = queryForAnnouncements(executeQuery);
		manager.disconnect();
		return result;
	}
	
	@GET
	@Path ("/getAnnouncementByWord")
	@Produces ("application/json")
	public String getAnnouncementByWord(@QueryParam("word") String word){
		String query = " WHERE title like '%" + word + "%' or message like '%" + word + "%'";
//		return getAnnouncement(word,query);
		String executeQuery = "SELECT * FROM announcement";
		System.out.println("executeQuery before:" + executeQuery);
		System.out.println("word:"+ word);
		
		if(word !=null){ 
			executeQuery +=query;
		}
		
		System.out.println("executeQuery after:" +executeQuery);
		String result = queryForAnnouncements(executeQuery);
		manager.disconnect();
		return result;
	}
	
	@POST
	@Path ("/getAnnouncementByCourseCode")
	@Produces ("application/json")
	public String getAnnouncementByCourseCode(@QueryParam("courseCode") String courseCode){
		String query = " WHERE courseCode like '%" + courseCode + "%'";
		String result = queryForAnnouncements(query);
		manager.disconnect();
		return result;
	}
	
	@POST
	@Path ("/getAnnouncementByDate")
	@Produces ("application/json")
	public String getAnnouncementByDate(@QueryParam("date") String date){
		String query = " WHERE date like '%" + date + "%'";
		String result = queryForAnnouncements(query);
		manager.disconnect();
		return result;
	}

	@GET
	@Path ("/listAnnouncements")
	@Produces ("application/json")
	public String listAnnouncements(){

		String query = "SELECT * FROM announcement";
		String result = queryForAnnouncements(query);
		manager.disconnect();
		return result;
	}

	private String queryForAnnouncements(String query) {
		JsonArray announcements = new JsonArray();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				JsonObject announcement = (JsonObject) gson.toJsonTree(manager.getColumnValues(rs));
				JsonElement jsonElement = announcement.get("courseCode");
				if (!jsonElement.isJsonNull()) {
					String courseCode = jsonElement.getAsString();
					query = "SELECT * FROM course WHERE courseCode='" + courseCode + "'";
					JsonArray result = querySimpleTable(query);
					if(result.size() >0)announcement.add("course", result.get(0));
				}
				announcements.add(announcement);
			}
		} catch (SQLException e) {
			JSONObject result = new JSONObject();
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		String asString = announcements.toString();
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
	@Path ("/addAnnouncement")
	@Produces ("application/json")
	public String addAnnouncement(@FormParam("message") String message, @FormParam("userId") String userId, @FormParam("title") String title, @FormParam("courseCode") String courseCode, @FormParam("date") String date){

		JSONObject result = new JSONObject();
		if(userId != null) {
			try {
				String query = "INSERT INTO announcement (announcementId,message,userId,title,courseCode,date) VALUES (NULL,'"+ message + "','" + userId + "','" + title + "','" + courseCode + "'," + date +")";
				System.out.println(query);
				manager.update(query);
				manager.disconnect();
				}
			catch (SQLException e) {
				result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
				return result.toString();
			}
			result.put("result", "Announcement sucessfully added");
		}
		else {
			result.put("result", "The userId was empty, no User added...");
		}
		return result.toString();
	}
}
