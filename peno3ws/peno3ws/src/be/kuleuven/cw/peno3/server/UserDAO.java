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

import be.kuleuven.cw.peno3.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Path ("/UserHandler")
public class UserDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();

	@GET
	@Path ("/getUser")
	@Produces ("application/json")
	public String getUser(@QueryParam("name") String name){
		String query = "SELECT * FROM user";
		if(name !=null)query += " WHERE firstname like '%" + name + "%' or lastname like '%" + name + "%'";
		System.out.println(query);
		String result = queryForUsers(query);
		manager.disconnect();
		return result;
	}

	@GET
	@Path ("/listUsers")
	@Produces ("application/json")
	public String listUsers(){

		String query = "SELECT * FROM user";
		System.out.println(query);
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
				JsonElement jsonElement = user.get("cred_id");
				if (!jsonElement.isJsonNull()) {
					int id = jsonElement.getAsInt();
					query = "SELECT * FROM credentials WHERE id='" + id + "'";
					JsonArray result = querySimpleTable(query);
					if(result.size() >0)user.add("credentials", result.get(0));
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
		System.out.println(query);
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
	@Path ("/addUser")
	@Produces ("application/json")
	public String addUser(@QueryParam("firstname") String firstName,@QueryParam("lastname") String lastName){

		JSONObject result = new JSONObject();
		try {
			int cred_id = insertDefaultCredentials();
			if (cred_id >-1) {
				String query = "INSERT INTO user (firstname,lastname,cred_id) VALUES ('" + firstName + "','" + lastName + "'," + cred_id + ")";
				System.out.println(query);
				manager.update(query);
				manager.disconnect();
			}else {
				manager.disconnect();
				result.put("result", "INSERT of credentials failed");
				return result.toString();
			}
		} catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}

		result.put("result", "User "+firstName + " " + lastName+" succefully added.");

		return result.toString();
	}

	@POST
	@Path ("/addUser")
	@Produces ("application/json")
	public String addUser(@FormParam("user") String userJson){
		JSONObject result = new JSONObject();
		if(userJson != null) {
			User newUser = new Gson().fromJson(userJson, User.class);

			try {
				int cred_id = -1;
				if(newUser.getCredential() != null) {
					cred_id = insertCredentials(newUser.getCredential().getUsername(), newUser.getCredential().getPassword());
				}else {
					cred_id = insertDefaultCredentials();
				}

				if (cred_id >-1) {
					String query = "INSERT INTO user (firstname,lastname,cred_id) VALUES ('" + newUser.getFirstName() + "','" + newUser.getLastName() + "'," + cred_id + ")";
					System.out.println(query);
					manager.update(query);
					manager.disconnect();
				}else {
					manager.disconnect();
					result.put("result", "INSERT of credentials failed");
					return result.toString();
				}
			} catch (SQLException e) {
				result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
				return result.toString();
			}
			result.put("result", "User "+newUser.getFirstName() + " " + newUser.getLastName()+" succefully added.");
		}
		else {
			result.put("result", "User was empty, no User added...");
		}
		return result.toString();
	}

	private int insertCredentials(String username, String password) throws SQLException {
		int cred_id = -1;

		String update = "INSERT INTO credentials (username,password) VALUES ('"+username+"','"+password+"')";
		String query = "SELECT LAST_INSERT_ID() from credentials";
		System.out.println(query);
		ResultSet rs = manager.updateAndQuery(update, query);
		rs.last();
		HashMap result = manager.getColumnValues(rs);
		cred_id = ((Long)result.get("LAST_INSERT_ID()")).intValue();

		return cred_id;
	}

	private int insertDefaultCredentials() throws SQLException {

		return insertCredentials("user", "default");
	}
}
