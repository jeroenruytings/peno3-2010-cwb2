/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the EventDAO class.
 * EventDAO will be used to communicate with our MySQL database.
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
 * The Class EventDAO.
 */
@Path ("/EventHandler")
public class EventDAO extends DAO{

	/**
	 * Gets the event corresponding to the given id.
	 *
	 * @param eventId the event id
	 * @return the event corresponding to the given id
	 */
	@POST
	@Path ("/getEventById")
	@Produces ("application/json")
	public String getEventById(@FormParam("eventId") String eventId) {
		String query = "SELECT * FROM event";
		if(eventId!=null)query+= " WHERE eventId like '" + eventId + "'";
		return super.get(query);
	}
	
	/**
	 * List events.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listEvents")
	@Produces ("application/json")
	public String listEvents(){
		String query = "SELECT * FROM event ORDER BY startDate DESC";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	/**
	 * Adds the event.
	 *
	 * @param description the description
	 * @param startDate the start date
	 * @param stopDate the stop date
	 * @param locationId the location id
	 * @param categorie the categorie
	 * @param title the title
	 * @return the resultstring
	 */
	@POST
	@Path ("/addEvent")
	@Produces ("application/json")
	public String addEvent(@FormParam("description") String description, @FormParam("startDate") String startDate, @FormParam("stopDate") String stopDate, @FormParam("locationId") String locationId, @FormParam("categorie") String categorie, @FormParam("title") String title){
		String query = "INSERT INTO event (eventId,description,startDate,stopDate,locationId,categorie,title) VALUES (NULL,'"+ description + "'," + startDate + "," + stopDate + ",'" + locationId + "','" + categorie + "','" + title +"')";
		return super.add(query);
	}	
	
	@POST
	@Path("/getEventsByCategorieAndDateWithLocation")
	@Produces("application/json")
	public String getEventsByCategorieAndDateWithLocation(@FormParam("categorie") String category,@FormParam("startDate") String startDate, @FormParam("stopDate") String stopDate){
		String query = "SELECT * FROM event INNER JOIN building USING (locationId) WHERE (categorie like '" + category + "') AND (startDate BETWEEN " + startDate + " AND " + stopDate + " OR stopDate BETWEEN " + startDate + " AND " + stopDate+")"; 
		return super.list(query);
	}
	
	@POST
	@Path("/getEventsByCategorieAndDate")
	@Produces("application/json")
	public String getEventsByCategorieAndDate(@FormParam("categorie") String categorie,@FormParam("startDate") String startDate, @FormParam("stopDate") String stopDate){
		String query = "SELECT * FROM event WHERE (categorie like '" + categorie + "') AND (startDate BETWEEN " + startDate + " AND " + stopDate + " OR stopDate BETWEEN " + startDate + " AND " + stopDate+")"; 
		return super.list(query);
	}
	
	@POST
	@Path("/getEventsByTwoCategoriesAndDate")
	@Produces("application/json")
	public String getEventsByTwoCategoriesAndDate(@FormParam("categorie1") String categorie1,@FormParam("categorie2") String categorie2, @FormParam("startDate") String startDate, @FormParam("stopDate") String stopDate){
		String query = "SELECT * FROM event WHERE (categorie like '" + categorie1 + "' OR categorie like '" + categorie2 +"') AND (startDate BETWEEN " + startDate + " AND " + stopDate + " OR stopDate BETWEEN " + startDate + " AND " + stopDate+")"; 
		return super.list(query);
	}
	
	@POST
	@Path("/getEventsByTwoCategoriesAndDateWithLocation")
	@Produces("application/json")
	public String getEventsByTwoCategoriesAndDateWithLocation(@FormParam("categorie1") String categorie1,@FormParam("categorie2") String categorie2, @FormParam("startDate") String startDate, @FormParam("stopDate") String stopDate){
		String query = "SELECT * FROM event INNER JOIN building USING (locationId) WHERE (categorie like '" + categorie1 + "' OR categorie like '" + categorie2 +"') AND (startDate BETWEEN " + startDate + " AND " + stopDate + " OR stopDate BETWEEN " + startDate + " AND " + stopDate+")"; 
		return super.list(query);
	}
}