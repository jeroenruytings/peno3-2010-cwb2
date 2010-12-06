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

@Path ("/DocumentHandler")
public class DocumentDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();
	private Cryptography cryptography = Cryptography.getInstance();

	@POST
	@Path ("/getDocument")
	@Produces ("application/json")
	public String getDocument(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM appreciation";
		if(courseCode != null )query += " WHERE courseCode like '%" + courseCode + "%'";
		String result = queryForDocuments(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}

	@POST
	@Path ("/listDocuments")
	@Produces ("application/json")
	public String listDocuments(){

		String query = "SELECT * FROM appreciation";
		System.out.println(query);
		String result = queryForDocuments(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}

	private String queryForDocuments(String query) {
		JsonArray documents = new JsonArray();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				JsonObject document = (JsonObject) gson.toJsonTree(manager.getColumnValues(rs));
				documents.add(document);
			}
		} catch (SQLException e) {
			JSONObject result = new JSONObject();
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		String asString = documents.toString();
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

	@POST
	@Path ("/addDocument")
	@Produces ("application/json")
	public String addDocument(@FormParam("description") String description, @FormParam("type") String type, @FormParam("size") String size, @FormParam("uploadDate") String uploadDate, @FormParam("dateLastChange") String dateLastChange, @FormParam("userId") String userId, @FormParam("title") String title, @FormParam("link") String link, @FormParam("courseCode") String courseCode){
		JSONObject result = new JSONObject();
		try {
			String query = "INSERT INTO document (documentId,type,description,size,uploadDate,dateLastChange,userId,title,link,courseCode) VALUES (NULL,'"+ type + "','" + description + "','"  + size + "'," + uploadDate + "," + dateLastChange+ ",'" +userId + "','" + title + "','" + link + "','" + courseCode +"')";
			System.out.println(query);
			manager.update(query);
			manager.disconnect();
		} catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}

		result.put("result", "document succesfully added.");

		return result.toString();
	}

}
