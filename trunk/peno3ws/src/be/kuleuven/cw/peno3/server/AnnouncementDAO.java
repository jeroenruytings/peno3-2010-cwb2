package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/AnnouncementHandler")
public class AnnouncementDAO extends DAO{
	
	@POST
	@Path ("/getAnnouncementByWord")
	@Produces ("application/json")
	public String getAnnouncementByWord(@FormParam("word") String word){
		String query = "SELECT * FROM announcement";
		if(word!=null)query+= " WHERE title like '%" + word + "%' or message like '%" + word + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getAnnouncementByCourseCode")
	@Produces ("application/json")
	public String getAnnouncementByCourseCode(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM announcement";
		if(courseCode!=null)query+= " WHERE courseCode like '%" + courseCode + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getAnnouncementByExactDate")
	@Produces ("application/json")
	public String getAnnouncementByExactDate(@FormParam("date") String date){
		String query = "SELECT * FROM announcement";
		if(date!=null)query+= " WHERE date= '" + date + "'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getAnnouncementByStartDate")
	@Produces ("application/json")
	public String getAnnouncementByStartDate(@FormParam("date") String date){
		String query = "SELECT * FROM announcement";
		if(date!=null)query+= " WHERE date>= '" + date + "'";
		return super.get(query);
	}

	@POST
	@Path ("/listAnnouncements")
	@Produces ("application/json")
	public String listAnnouncements(){
		String query = "SELECT * FROM announcement";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addAnnouncement")
	@Produces ("application/json")
	public String addAnnouncement(@FormParam("message") String message, @FormParam("userId") String userId, @FormParam("title") String title, @FormParam("courseCode") String courseCode, @FormParam("date") String date){
			String query = "INSERT INTO announcement (announcementId,message,userId,title,courseCode,date) VALUES (NULL,'"+ message + "','" + userId + "','" + title + "','" + courseCode + "'," + date +")";
			return super.add(query);
	}
}