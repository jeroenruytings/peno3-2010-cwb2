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

@Path ("/AnnouncementHandler")
public class AnnouncementDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();

	@GET
	@Path ("/getAnnouncement")
	@Produces ("application/json")
	public String getAnnouncement(@QueryParam("word") String word){
		String query = "SELECT * FROM announcement";
		if(word !=null)query += " WHERE title like '%" + word + "%' or message like '%" + word + "%'";
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
					int courseCode = jsonElement.getAsInt();
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

	@GET
	@Path ("/addAnnouncement")
	@Produces ("application/json")
	public String addAnnouncement(@QueryParam("message") String message, @QueryParam("userId") String userId, @QueryParam("title") String title, @QueryParam("courseCode") String courseCode) { //@QueryParam("date") String date, 

		JSONObject result = new JSONObject();
		try {
		
			String query = "INSERT INTO announcement (announcementId,message,userId,title,courseCode) VALUES (NULL,'"+ message + "','" + userId + "','" + title + "'," + courseCode +")";//+ date + "',"
			manager.update(query);
			manager.disconnect();
		} catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}

		result.put("result", "Announcement succesfully added.");

		return result.toString();
	}

}
