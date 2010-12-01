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

@Path ("/AppreciationHandler")
public class AppreciationDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();

	@GET
	@Path ("/getAppreciation")
	@Produces ("application/json")
	public String getAppreciation(@QueryParam("docQuestionId") int docQuestionId,@QueryParam("isDocument") boolean isDocument){
		int docOrQuestion;
		if(isDocument)
			docOrQuestion = 1;
		else
			docOrQuestion = 0;
		String query = "SELECT * FROM appreciation";
		if(docQuestionId > 0 )query += " WHERE docQuestionId like '%" + docQuestionId + "%' and isDocument like '%" + docOrQuestion + "%'";
		String result = queryForAppreciations(query);
		manager.disconnect();
		return result;
	}

	@GET
	@Path ("/listAppreciations")
	@Produces ("application/json")
	public String listAppreciations(){

		String query = "SELECT * FROM appreciation";
		System.out.println(query);
		String result = queryForAppreciations(query);
		System.out.println(result);
		manager.disconnect();
		return result;
	}

	private String queryForAppreciations(String query) {
		JsonArray appreciations = new JsonArray();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				JsonObject appreciation = (JsonObject) gson.toJsonTree(manager.getColumnValues(rs));
				appreciations.add(appreciation);
			}
		} catch (SQLException e) {
			JSONObject result = new JSONObject();
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		String asString = appreciations.toString();
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
	@Path ("/addAppreciation")
	@Produces ("application/json")
	public String addAppreciation(@QueryParam("docQuestionId") int docQuestionId, @QueryParam("isDocument") boolean isDocument, @QueryParam("userId") String userId, @QueryParam("score") int score){

		JSONObject result = new JSONObject();
		try {
			String query = "INSERT INTO appreciation (appreciationId,docQuestionId,isDocument,userId,score) VALUES (NULL,'"+ docQuestionId + "','" + isDocument + "','" + userId + "','" + score +"')";
			manager.update(query);
			manager.disconnect();
		} catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}

		result.put("result", "Appreciation succesfully added.");

		return result.toString();
	}

}
