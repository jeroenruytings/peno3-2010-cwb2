/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:14
 * 
 * This is the AnnouncementDAO class.
 * AnnouncementDAO will be used to communicate with our MySQL database.
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
 * The Class AnnouncementDAO.
 */
@Path ("/AnnouncementHandler")
public class AnnouncementDAO extends DAO{
	
	/**
	 * Gets the announcement corresponding to the given word.
	 *
	 * @param word the word
	 * @return the announcement that has word in his title or message
	 */
	@POST
	@Path ("/getAnnouncementByWord")
	@Produces ("application/json")
	public String getAnnouncementByWord(@FormParam("word") String word){
		String query = "SELECT * FROM announcement";
		if(word!=null)query+= " WHERE title like '%" + word + "%' or message like '%" + word + "%'";
		return super.get(query);
	}
	
	/**
	 * Gets the announcement corresponding to course code. Results are ordered by descending date.
	 *
	 * @param courseCode the course code
	 * @return the announcement corresponding to the given course code
	 */
	@POST
	@Path ("/getAnnouncementByCourseCode")
	@Produces ("application/json")
	public String getAnnouncementByCourseCode(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM announcement";
		if(courseCode!=null)query+= " WHERE courseCode like '" + courseCode + "' ORDER BY date DESC";
		return super.get(query);
	}
	
	/**
	 * Gets the announcement corresponding to the given exact date. Results are ordered by descending date.
	 *
	 * @param date the date
	 * @return the announcement corresponding to the given exact date
	 */
	@POST
	@Path ("/getAnnouncementByExactDate")
	@Produces ("application/json")
	public String getAnnouncementByExactDate(@FormParam("date") String date){
		String query = "SELECT * FROM announcement";
		if(date!=null)query+= " WHERE date= '" + date + "' ORDER BY date DESC";
		return super.get(query);
	}
	
	@POST
	@Path ("/getAnnouncementByStartDateAndCourseCode")
	@Produces ("application/json")
	public String getAnnouncementByStartDateAndCourse(@FormParam("courseCode") String courseCode, @FormParam("date") String date){
		String query = "SELECT * FROM announcement";
		if(date!=null)query+= " WHERE courseCode like '" + courseCode + "' AND date>= '" + date + "' ORDER BY date DESC";
		return super.get(query);
	}
	
	/**
	 * Gets the announcement corresponding to the given start date. Results are ordered by descending date.
	 *
	 * @param date the date
	 * @return the announcement corresponding to the given start date
	 */
	@POST
	@Path ("/getAnnouncementByStartDate")
	@Produces ("application/json")
	public String getAnnouncementByStartDate(@FormParam("date") String date){
		String query = "SELECT * FROM announcement";
		if(date!=null)query+= " WHERE date>= '" + date + "' ORDER BY date DESC";
		return super.get(query);
	}

	/**
	 * List announcements.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listAnnouncements")
	@Produces ("application/json")
	public String listAnnouncements(){
		String query = "SELECT * FROM announcement ORDER BY date DESC";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	/**
	 * Adds the announcement.
	 *
	 * @param message the message
	 * @param userId the user id
	 * @param title the title
	 * @param courseCode the course code
	 * @param date the date
	 * @return the resultstring
	 */
	@POST
	@Path ("/addAnnouncement")
	@Produces ("application/json")
	public String addAnnouncement(@FormParam("message") String message, @FormParam("userId") String userId, @FormParam("title") String title, @FormParam("courseCode") String courseCode, @FormParam("date") String date){
			String query = "INSERT INTO announcement (announcementId,message,userId,title,courseCode,date) VALUES (NULL,'"+ message + "','" + userId + "','" + title + "','" + courseCode + "'," + date +")";
			return super.add(query);
	}
}