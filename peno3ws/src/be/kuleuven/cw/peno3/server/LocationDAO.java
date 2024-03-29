/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the LocationDAO class.
 * LocationDAO will be used to communicate with our MySQL database.
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
 * The Class LocationDAO.
 */
@Path ("/LocationHandler")
public class LocationDAO extends DAO{
	
	/**
	 * Gets the location.
	 *
	 * @param locationId the location id
	 * @return the location
	 */
	@POST
	@Path ("/getLocation")
	@Produces ("application/json")
	public String getLocation(@FormParam("locationId") String locationId){
		String query = "SELECT * FROM location";
		if(locationId!=null)query+= " WHERE locationId like '%" + locationId + "%'";
		return super.get(query);
	}
	
	/**
	 * Gets the location corresponding to the given coordinates.
	 *
	 * @param xcoordinate the xcoordinate
	 * @param ycoordinate the ycoordinate
	 * @return the location corresponding to the given coordinates
	 */
	@POST
	@Path ("/getLocationByCoordinates")
	@Produces ("application/json")
	public String getLocationByCoordinates(@FormParam("xcoordinatemin") String xcoordinatemin,@FormParam("xcoordinatemax") String xcoordinatemax, @FormParam("ycoordinatemin") String ycoordinatemin,  @FormParam("ycoordinatemax") String ycoordinatemax){
		String query = "SELECT * FROM location";
		if(xcoordinatemin!=null && ycoordinatemax!=null && xcoordinatemax!=null && ycoordinatemin!=null)query += " WHERE xcoordinate BETWEEN " + xcoordinatemin + " AND " +xcoordinatemax+ " AND ycoordinate BETWEEN " + ycoordinatemin + " AND " + ycoordinatemax;
		return super.get(query);
	}
	
	@POST
	@Path ("/getBuildingAndLocationByCoordinates")
	@Produces ("application/json")
	public String getBuildingAndLocationByCoordinates(@FormParam("xcoordinatemin") String xcoordinatemin,@FormParam("xcoordinatemax") String xcoordinatemax, @FormParam("ycoordinatemin") String ycoordinatemin,  @FormParam("ycoordinatemax") String ycoordinatemax){
		String query = "SELECT * FROM location INNER JOIN building USING(locationId)";
		if(xcoordinatemin!=null && ycoordinatemax!=null && xcoordinatemax!=null && ycoordinatemin!=null)query += " WHERE xcoordinate BETWEEN " + xcoordinatemin + " AND " +xcoordinatemax+ " AND ycoordinate BETWEEN " + ycoordinatemin + " AND " + ycoordinatemax;
		return super.get(query);
	}
	
	/**
	 * Gets the location corresponding to the given adress. Results are ordered first by ascending street than by ascending number.
	 *
	 * @param word the word
	 * @return the location corresponding to the given adress
	 */
	@POST
	@Path ("/getLocationByAdress")
	@Produces ("application/json")
	public String getLocationByAdress(@FormParam("word") String word){
		String query = "SELECT * FROM location";
		if(word!=null)query+= " WHERE street like '%" + word + "%' or city like '%" + word + "%' ORDER BY street ASC, number ASC";
		return super.get(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	/**
	 * Adds the location.
	 *
	 * @param xcoordinate the xcoordinate
	 * @param ycoordinate the ycoordinate
	 * @param street the street
	 * @param number the number
	 * @param city the city
	 * @param zipcode the zipcode
	 * @return the resultstring
	 */
	@POST
	@Path ("/addLocation")
	@Produces ("application/json")
	public String addLocation(@FormParam("locationId") String locationId, @FormParam("xcoordinate") String xcoordinate, @FormParam("ycoordinate") String ycoordinate, @FormParam("street") String street, @FormParam("number") String number, @FormParam("city") String city, @FormParam("zipcode") String zipcode){
			String query = "INSERT INTO location (locationId,xcoordinate,ycoordinate,street,number,city,zipcode) VALUES ('"+ locationId + "','" + xcoordinate + "','" + ycoordinate + "','" + street + "','" + number + "','" + city +"','" + zipcode + "')";
			return super.add(query);
	}
}
