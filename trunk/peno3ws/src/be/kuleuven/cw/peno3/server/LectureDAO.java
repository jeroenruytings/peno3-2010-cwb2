package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/LectureHandler")
public class LectureDAO extends DAO{
	
	@POST
	@Path ("/getLectureByEventId")
	@Produces ("application/json")
	public String getLecureByEventId(@FormParam("eventId") String eventId){
		String query = "SELECT * FROM lecture";
		if(eventId!=null)query+= " WHERE eventId like '" + eventId + "'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getLectureByRoomId")
	@Produces ("application/json")
	public String getLectureByRoomId(@FormParam("roomId") String roomId){
		String query = "SELECT * FROM lecture";
		if(roomId!=null)query+= " WHERE roomId like '" + roomId + "'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getLectureByCourseCode")
	@Produces ("application/json")
	public String getAnnouncementByCourseCode(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM lecture";
		if(courseCode!=null)query+= " WHERE courseCode= '" + courseCode + "'";
		return super.get(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addLecture")
	@Produces ("application/json")
	public String addLecture(@FormParam("eventId") String eventId, @FormParam("roomId") String roomId, @FormParam("courseCode") String courseCode){
			String query = "INSERT INTO lecture (eventId,roomId,courseCode) VALUES ('"+ eventId + "','" + roomId + "','" + courseCode + "')";
			return super.add(query);
	}
}
