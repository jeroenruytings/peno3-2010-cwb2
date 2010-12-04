package be.kuleuven.cw.peno3.server;


import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path ("/EventHandler")
public class EventDAO extends DAO{

	@POST
	@Path ("/getEventById")
	@Produces ("application/json")
	public String getEventById(@FormParam("eventId") String eventId) {
		String query = "SELECT * FROM event";
		if(eventId!=null)query+= " WHERE eventId like '%" + eventId + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/listEvents")
	@Produces ("application/json")
	public String listEvents(){
		String query = "SELECT * FROM event";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addEvent")
	@Produces ("application/json")
	public String addEvent(@FormParam("description") String description, @FormParam("startDate") String startDate, @FormParam("stopDate") String stopDate, @FormParam("locationId") String locationId, @FormParam("categorie") String categorie, @FormParam("title") String title){
		String query = "INSERT INTO event (eventId,description,startDate,stopDate,locationId,categorie,title) VALUES (NULL,'"+ description + "'," + startDate + "," + stopDate + ",'" + locationId + "','" + categorie + "','" + title +"')";
		return super.add(query);
	}
}