/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:02
 * 
 * This is the CourseDAO class.
 * CourseDAO will be used to communicate with our MySQL database.
 * All inside methods are called using the postmethods of a webservice.
 * All returned strings are encrypted following the standard of our Cryptography class.
 */

package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// TODO: Auto-generated Javadoc
/**
 * The Class CourseDAO.
 */
@Path ("/CourseHandler")
public class CourseDAO extends DAO{
	
	/**
	 * Gets the course corresponding to the given course code.
	 *
	 * @param courseCode the course code
	 * @return the course corresponding to the given course code
	 */
	@POST
	@Path ("/getCourseByCourseCode")
	@Produces ("application/json")
	public String getCourseByCourseCode(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM course";
		if(courseCode!=null)query += " WHERE courseCode like '" + courseCode + "'";
		return super.get(query);
	}
	
	/**
	 * Gets the course corresponding to the given name. Results are ordered by ascending coursename.
	 *
	 * @param course the course
	 * @return the course corresponding to the given name
	 */
	@POST
	@Path ("/getCourseByName")
	@Produces ("application/json")
	public String getCourseByName(@FormParam("course") String course){
		String query = "SELECT * FROM course";
		if(course!=null)query+= " WHERE course like '%" + course + "%' ORDER BY course ASC";
		return super.get(query);
	}
	
	/**
	 * Gets the course corresponding to the given year. Results are ordered by ascending coursename.
	 *
	 * @param year the year
	 * @return the course corresponding to the given year
	 */
	@POST
	@Path ("/getCourseByYear")
	@Produces ("application/json")
	public String getCourseByYear(@FormParam("year") String year){
		String query = "SELECT * FROM course";
		if(year!=null)query+= " WHERE academicYear like '%" + year + "%' ORDER BY course ASC";
		return super.get(query);
	}
	
	/**
	 * List courses. Results are ordered first by descending academic year then by ascending coursename
	 *
	 * @return the resultstring
	 */
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
	/**
	 * Adds the course.
	 *
	 * @param courseCode the course code
	 * @param academicYear the academic year
	 * @param course the course
	 * @return the resultstring
	 */
	@POST
	@Path ("/addCourse")
	@Produces ("application/json")
	public String addCourse(@FormParam("courseCode") String courseCode, @FormParam("academicYear") String academicYear, @FormParam("course") String course){
		String query = "INSERT INTO course (courseCode,academicYear,course) VALUES ('"+ courseCode + "','" + academicYear + "','" + course +"')";
		return super.add(query);
	}
	
	/**
	 * Adds the user.
	 *
	 * @param courseCode the course code
	 * @param userId the user id
	 * @return the resultstring
	 */
	@POST
	@Path ("/addUser")
	@Produces ("application/json")
	public String addUser(@FormParam("courseCode") String courseCode, @FormParam("userId") String userId){
		String query = "INSERT INTO course_user (courseUserId,courseCode,userId) VALUES (NULL,'"+ courseCode + "','" + userId + "')";
		return super.add(query);
	}
	
	/**
	 * Gets the course corresponding to the given user id. Results are ordered by ascending course name
	 *
	 * @param userId the user id
	 * @return the course corresponding to the given user id
	 */
	@POST
	@Path ("/getCourseByUserId")
	@Produces ("application/json")
	public String getCourseByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM course_user";
		if(userId!=null)query+= " WHERE userId like '" + userId + "'";
		return super.get(query);
	}
	
	/**
	 * 
	 * @param userId
	 * @param date
	 * @param courseCode
	 * @return
	 */
	@POST
	@Path ("/getCourseByDateAndCourseCode")
	@Produces ("application/json")
	public String getCourseByDateAndCourseCode(@FormParam("userId") String userId, @FormParam("date") String date, @FormParam("courseCode") String courseCode) {
		String query = "SELECT * FROM lecture, course_isp, event WHERE lecture.courseCode like '" + courseCode + "' AND " + date + " BETWEEN event.startDate AND event.stopDate AND course_isp.userId like '"+ userId + "'";
		return super.get(query);
	}
	
	/**
	 * 
	 * @param courseCode
	 * @return
	 */
	@POST
	@Path("/initializeCourse")
	@Produces ("application/json")
	public String initializeCourse(@FormParam("courseCode") String courseCode) {
		String query = "SELECT * FROM course, course_user, user WHERE course.courseCode like '"+ courseCode + "' AND course_user.courseCode = course.courseCode AND user.userId = course_user.userId";
		return super.get(query);
	}
	
}
