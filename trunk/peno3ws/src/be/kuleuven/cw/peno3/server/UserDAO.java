package be.kuleuven.cw.peno3.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
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

@Path ("/UserHandler")
public class UserDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();

	@POST
	@Path ("/getUserByName")
	@Produces ("application/json")
	public String getUserByName(@QueryParam("name") String name){
		String query = "SELECT * FROM user";
		if(name !=null)query += " WHERE firstName like '%" + name + "%' or lastName like '%" + name + "%'";
		String result = queryForUsers(query);
		manager.disconnect();
		return result;
	}

	@POST
	@Path ("/getUserByUserId")
	@Produces("application/json")
	public String getUserByUserId(@QueryParam("userId") String userId){
		String query = "SELECT * FROM user";
		if(userId !=null)query += " WHERE userId like '%" + userId + "%'";
		String result = queryForUsers(query);
		manager.disconnect();
		return result;
	}
	
	@POST
	@Path ("/listUsers")
	@Produces ("application/json")
	public String listAnnouncements(){
		String query = "SELECT * FROM user";
		String result = queryForUsers(query);
		manager.disconnect();
		return result;
	}

	private String queryForUsers(String query) {
		JsonArray users = new JsonArray();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				JsonObject user = (JsonObject) gson.toJsonTree(manager.getColumnValues(rs));
				JsonElement jsonElement = user.get("userId");
				if (!jsonElement.isJsonNull()) {
					String userId = jsonElement.getAsString();
					query = "SELECT * FROM isp WHERE userId ='" + userId + "'";
					JsonArray result = querySimpleTable(query);
					if(result.size() >0)user.add("group", result.get(1));
					if(result.size() >0)user.add("studies", result.get(2));
					if(result.size() >0)user.add("phase", result.get(3));
					
					query = "SELECT * FROM isp_course WHERE userId ='" + userId + "'";
					JsonArray resultaat = querySimpleTable(query);
					Iterator<JsonElement> it = resultaat.iterator();
					while(it.hasNext()){
						if(result.size() >0)user.add("courseCode", result.get(2));
					}
				}
				users.add(user);
			}
		} catch (SQLException e) {
			JSONObject result = new JSONObject();
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		String asString = users.toString();
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
	@Path ("/addUser")
	@Produces ("application/json")
	public String addAnnouncement(@FormParam("userId") String userId, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("password") String password, @FormParam("birthDate") String birthDate, @FormParam("rank") int rank){

		JSONObject result = new JSONObject();
		try {
			String query = "INSERT INTO announcement (userId, firstName, lastName, password, birthDate, rank) VALUES ('"+ userId + "','" + firstName + "','" + lastName + "','" + password + "'," + birthDate + "'," + rank +")";
			System.out.println(query);
			manager.update(query);
			manager.disconnect();
			}
		catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		result.put("result", "User sucessfully added");
		return result.toString();
	}
}