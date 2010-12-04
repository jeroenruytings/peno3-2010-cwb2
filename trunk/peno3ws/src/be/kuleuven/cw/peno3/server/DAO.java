/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:58:19
 * 
 * This is the DAO class.
 * DAO will be used to communicate with our MySQL database.
 * All inside methods are called using the postmethods of a webservice.
 * All returned strings are encrypted following the standard of our Cryptography class.
 */
package be.kuleuven.cw.peno3.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class DAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();
	private Cryptography cryptography = Cryptography.getInstance();
	
	public DAO() {	}
	
	/**
	 * Executes a query
	 * 
	 * @param query
	 * @return an encrypted jsonstring of the results
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
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
	/**
	 * Lists all query satisfieing results
	 * 
	 * @param query
	 * @return an encrypted jsonstring of the results
	 */
	public String list(String query){
		String result = executeQuery(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}
	
	/**
	 * Adds records to the database
	 * 
	 * @param query
	 * @return an encrypted resultstring
	 */
	@SuppressWarnings("unchecked")
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
	
	/**
	 * Lists all query satisfieing results
	 * 
	 * @param query
	 * @return an encrypted jsonstring of the results
	 */
	public String get(String query){
		String result = executeQuery(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}
}
