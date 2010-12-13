/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the LectureDAO class.
 * LectureDAO will be used to communicate with our MySQL database.
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
 * The Class LectureDAO.
 */
@Path ("/LectureHandler")
public class LectureDAO extends DAO{
	
	/**
	 * Gets the lecure corresponding to the given event id.
	 *
	 * @param eventId the event id
	 * @return the lecure corresponding to the given event id
	 */
	@POST
	@Path ("/getLectureByEventId")
	@Produces ("application/json")
	public String getLecureByEventId(@FormParam("eventId") String eventId){
		String query = "SELECT * FROM lecture";
		if(eventId!=null)query+= " WHERE eventId like '" + eventId + "'";
		return super.get(query);
	}
	
	/**
	 * 
	 */
	@POST
	@Path ("/getLectureByDate")
	@Produces ("application/json")
	public String getLectureByDate(@FormParam("courseCode") String courseCode, @FormParam("date") String date) {
		String query = "SELECT * FROM event INNER JOIN lecture USING (eventId) WHERE courseCode like '" + courseCode + "' AND " + date + " BETWEEN startDate AND stopDate";
		return super.get(query);
	}
	
	/**
	 * Gets the announcement corresponding to the given course code.
	 *
	 * @param courseCode the course code
	 * @return the announcement corresponding to the given course code
	 */
	@POST
	@Path ("/getLectureByCourseCode")
	@Produces ("application/json")
	public String getLectureByCourseCode(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM lecture";
		if(courseCode!=null)query+= " WHERE courseCode= '" + courseCode + "'";
		return super.get(query);
	}

	@POST
	@Path ("/initializeLectures")
	@Produces ("application/json")
	public String initializeLectures(@FormParam("userId") String userId) {
		String query = "SELECT lecture.courseCode, event.*, room.* FROM course_isp, room, lecture INNER JOIN event USING (eventId) WHERE course_isp.userId = '" + userId + "' AND course_isp.courseCode = lecture.courseCode AND room.roomId = event.roomId";
		return super.get(query);
	}
	
	
	/**
	 * Adds the lecture.
	 *
	 * @param eventId the event id
	 * @param roomId the room id
	 * @param courseCode the course code
	 * @return the resultstring
	 */
	@POST
	@Path ("/addLecture")
	@Produces ("application/json")
	public String addLecture(@FormParam("eventId") String eventId, @FormParam("courseCode") String courseCode){
			String query = "INSERT INTO lecture (eventId,courseCode) VALUES ('"+ eventId + "','" + courseCode + "')";
			return super.add(query);
	}
}
