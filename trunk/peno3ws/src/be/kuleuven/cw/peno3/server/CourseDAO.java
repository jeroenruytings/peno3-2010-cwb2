package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/CourseHandler")
public class CourseDAO extends DAO{
	
	@POST
	@Path ("/getCourseByCourseCode")
	@Produces ("application/json")
	public String getCourseByCourseCode(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM course";
		if(courseCode!=null)query += " WHERE courseCode like '" + courseCode + "'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getCourseByName")
	@Produces ("application/json")
	public String getCourseByName(@FormParam("course") String course){
		String query = "SELECT * FROM course";
		if(course!=null)query+= " WHERE course like '%" + course + "%' ORDER BY course ASC";
		return super.get(query);
	}
	
	@POST
	@Path ("/getCourseByYear")
	@Produces ("application/json")
	public String getCourseByYear(@FormParam("year") String year){
		String query = "SELECT * FROM course";
		if(year!=null)query+= " WHERE academicYear like '%" + year + "%' ORDER BY course ASC";
		return super.get(query);
	}
	
	@POST
	@Path ("/listCourses")
	@Produces ("application/json")
	public String listCourses(){
		String query = "SELECT * FROM course ORDER BY academicYear DESC, course ASC";
		return super.list(query);
	}

	/*
	 * Method adds a course to the database	
	 */
	@POST
	@Path ("/addCourse")
	@Produces ("application/json")
	public String addCourse(@FormParam("courseCode") String courseCode, @FormParam("academicYear") String academicYear, @FormParam("course") String course){
		String query = "INSERT INTO course (courseCode,academicYear,course) VALUES ('"+ courseCode + "','" + academicYear + "','" + course +"')";
		return super.add(query);
	}
	
	@POST
	@Path ("/addUser")
	@Produces ("application/json")
	public String addUser(@FormParam("courseCode") String courseCode, @FormParam("userId") String userId){
		String query = "INSERT INTO course_user (courseUserId,courseCode,userId) VALUES (NULL,'"+ courseCode + "','" + userId + "')";
		return super.add(query);
	}
	
	@POST
	@Path ("/getCourseByUserId")
	@Produces ("application/json")
	public String getCourseByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM course_user";
		if(userId!=null)query+= " WHERE userId like '" + userId + "' ORDER BY course ASC";
		return super.get(query);
	}
}
