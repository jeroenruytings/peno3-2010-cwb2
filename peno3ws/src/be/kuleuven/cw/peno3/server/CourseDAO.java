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

	private String getCourse(String searchString, String query) {
		String executeQuery = "SELECT * FROM course";
		if(searchString !=null)executeQuery += query;
		String result = queryForCourses(executeQuery);
		manager.disconnect();
		return result;
	}
	
	@POST
	@Path ("/getCourseByName")
	@Produces ("application/json")
	public String getCourseByName(@QueryParam("courseCode") String courseCode){
		String query = " WHERE courseCode like '%" + courseCode + "%'";
		String result = getCourse(courseCode,query);
		manager.disconnect();
		return result;
	}
	
	@POST
	@Path ("/tryPostParameter")
	@Produces ("application/json")
	public String tryPostParameter(@QueryParam("param") String param) {
		System.out.println(param);
		return param;
	}
//	
//	@POST
//	@Path ("/getAnnouncementByCourseCode")
//	@Produces ("application/json")
//	public String getAnnouncementByCourseCode(@QueryParam("courseCode") String courseCode){
//		String query = " WHERE courseCode like '%" + courseCode + "%'";
//		String result = queryForAnnouncements(query);
//		manager.disconnect();
//		return result;
//	}
//	
//	@POST
//	@Path ("/getAnnouncementByDate")
//	@Produces ("application/json")
//	public String getAnnouncementByDate(@QueryParam("date") String date){
//		String query = " WHERE date like '%" + date + "%'";
//		String result = queryForAnnouncements(query);
//		manager.disconnect();
//		return result;
//	}
//
	@GET
	@Path ("/listCourses")
	@Produces ("application/json")
	public String listCourses(){

		String query = "SELECT * FROM course";
		String result = queryForCourses(query);
		manager.disconnect();
		return result;
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
//
//	private JsonArray querySimpleTable(String query) {
//		Vector courses = new Vector();
//		ResultSet rs = manager.query(query);
//		Gson gson = new Gson();
//		try {
//			while(rs.next()) {
//				courses.add(manager.getColumnValues(rs));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return (JsonArray) gson.toJsonTree(courses);
//	}

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
