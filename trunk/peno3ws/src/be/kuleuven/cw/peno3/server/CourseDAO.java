package be.kuleuven.cw.peno3.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Path ("/CourseHandler")
public class CourseDAO {
	protected DatabaseManager manager = DatabaseManager.getInstance();
	private Cryptography cryptography = Cryptography.getInstance();

	
	private String getCourse(String searchString, String query) {
		String executeQuery = "SELECT * FROM course";
		if(searchString !=null)executeQuery += query;
		String result = queryForCourses(executeQuery);
		manager.disconnect();
		return cryptography.encrypt(result);
	}
	
	@POST
	@Path ("/getCourseByCourseCode")
	@Produces ("application/json")
	public String getCourseByCourseCode(@FormParam("courseCode") String courseCode){
		String query = " WHERE courseCode like '%" + courseCode + "%'";
		return getCourse(courseCode,query);
	}
	
	@POST
	@Path ("/getCourseByName")
	@Produces ("application/json")
	public String getCourseByName(@FormParam("course") String course){
		String query = " WHERE course like '%" + course + "%'";
		return getCourse(course,query);
	}
	
	@POST
	@Path ("/getCourseByYear")
	@Produces ("application/json")
	public String getCourseByYear(@FormParam("year") String year){
		String query = " WHERE academicYear like '%" + year + "%'";
		return getCourse(year,query);
	}
	
	@POST
	@Path ("/listCourses")
	@Produces ("application/json")
	public String listCourses(){
		String query = "SELECT * FROM course";
		String result = queryForCourses(query);
		manager.disconnect();
		return cryptography.encrypt(result);
	}

	private String queryForCourses(String query) {
		JsonArray courses = new JsonArray();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				JsonObject course = (JsonObject) gson.toJsonTree(manager.getColumnValues(rs));
				courses.add(course);
			}
		} catch (SQLException e) {
			JSONObject result = new JSONObject();
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		String asString = courses.toString();
		return asString;
	}

	/*
	 * Method adds a course to the database	
	 */
	@POST
	@Path ("/addCourse")
	@Produces ("application/json")
	public String addCourse(@FormParam("courseCode") String courseCode, @FormParam("academicYear") String academicYear, @FormParam("course") String course){

		JSONObject result = new JSONObject();
		String query = "INSERT INTO course (courseCode,academicYear,course) VALUES ('"+ courseCode + "','" + academicYear + "','" + course +"')";
		if(courseCode != null) {
			try {
				System.out.println(query);
				manager.update(query);
				manager.disconnect();
				}
			catch (SQLException e) {
				result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
				return result.toString();
			}
			result.put("result", "Course sucessfully added");
		}
		else {
			result.put("result", "The courseCode was empty, no Course added...");
		}
		return result.toString();
	}
}
