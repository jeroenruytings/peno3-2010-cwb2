package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/LocationHandler")
public class LocationDAO extends DAO{
	
	@POST
	@Path ("/getLocation")
	@Produces ("application/json")
	public String getLocation(@FormParam("locationId") String locationId){
		String query = "SELECT * FROM location";
		if(locationId!=null)query+= " WHERE locationId like '%" + locationId + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getLocationByCoordinates")
	@Produces ("application/json")
	public String getLocationByCoordinates(@FormParam("xcoordinate") String xcoordinate, @FormParam("ycoordinate") String ycoordinate){
		String query = "SELECT * FROM location";
		if(xcoordinate!=null && ycoordinate!=null)query+= " WHERE xcoordinate like '" + xcoordinate + "' or ycoordinate like '" + ycoordinate + "'";
		return super.get(query);
	}
	
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
	@POST
	@Path ("/addLocation")
	@Produces ("application/json")
	public String addLocation(@FormParam("xcoordinate") String xcoordinate, @FormParam("ycoordinate") String ycoordinate, @FormParam("street") String street, @FormParam("number") String number, @FormParam("city") String city, @FormParam("zipcode") String zipcode){
			String query = "INSERT INTO location (locationId,xcoordinate,ycoordinate,street,number,city,zipcode) VALUES (NULL,'"+ xcoordinate + "','" + ycoordinate + "','" + street + "','" + number + "','" + city +"','" + zipcode + "')";
			return super.add(query);
	}
}
