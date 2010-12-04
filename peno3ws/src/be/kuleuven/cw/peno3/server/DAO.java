package be.kuleuven.cw.peno3.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class DAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();
	private Cryptography cryptography = Cryptography.getInstance();
	
	public DAO() {
		// TODO Auto-generated constructor stub
	}
	
	protected String executeQuery(String query) {
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

	public String list(String query){
		String result = executeQuery(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}
	
	public String add(String query){
		JSONObject result = new JSONObject();
		try {
			System.out.println(query);
			manager.update(query);
			manager.disconnect();
			}
		catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		result.put("result", "User sucessfully added");
		return cryptography.encrypt(result.toString());
	}
	
	public String get(String query){
		String result = executeQuery(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}
}
