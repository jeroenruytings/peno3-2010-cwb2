/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the RoomDAO class.
 * RoomDAO will be used to communicate with our MySQL database.
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
 * The Class RoomDAO.
 */
@Path ("/RoomHandler")
public class RoomDAO extends DAO{
	
	/**
	 * Gets the room corresponding to the given location id.
	 *
	 * @param locationId the location id
	 * @return the room corresponding to the given location id
	 */
	@POST
	@Path ("/getRoomByLocationId")
	@Produces ("application/json")
	public String getRoomByLocationId(@FormParam("locationId") String locationId){
		String query = "SELECT * FROM room";
		if(locationId!=null)query+= " WHERE locationId like '" + locationId + "'";
		return super.get(query);
	}
	
	/**
	 * Gets the room corresponding to the given room id.
	 *
	 * @param room the location id
	 * @return the room corresponding to the given room id
	 */
	@POST
	@Path ("/getRoomByRoomId")
	@Produces ("application/json")
	public String getRoomByRoomId(@FormParam("roomId") String roomId){
		String query = "SELECT * FROM room";
		if(roomId!=null)query+= " WHERE roomId like '" + roomId + "'";
		return super.get(query);
	}
	

	/**
	 * List rooms.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listRooms")
	@Produces ("application/json")
	public String listRooms(){
		String query = "SELECT * FROM room";
		return super.list(query);
	}

	/**
	 * List all roomnames.
	 * 
	 * @return the resultString containing all buildingnames
	 */
	@POST
	@Path ("/listRoomNames")
	@Produces ("application/json")
	public String listRoomNames() {
		String query = "SELECT name FROM room ORDER BY name ASC";
		return super.list(query);
	}
	
	/** 
	 * Adds the room.
	 *
	 * @param roomId the room id
	 * @param locationId the location id
	 * @param function the function
	 * @param accomodation the accomodation
	 * @param capacity the capacity
	 * @param wireless the wireless
	 * @param link the link
	 * @param xcoordinate the xcoordinate
	 * @param ycoordinate the ycoordinate
	 * @return the resultstring
	 */
	@POST
	@Path ("/addRoom")
	@Produces ("application/json")
	public String addRoom(@FormParam("roomId") String roomId, @FormParam("locationId") String locationId, @FormParam("function") String function,@FormParam("capacity") String capacity, @FormParam("wireless") String wireless, @FormParam("link") String link, @FormParam("xcoordinate") String xcoordinate, @FormParam("ycoordinate") String ycoordinate,@FormParam("name") String name){
			String query = "INSERT INTO room (roomId,locationId,function,capacity,wireless,link,xcoordinate,ycoordinate,name) VALUES ('" + roomId + "','" + locationId + "','" + function + "','" + capacity + "','" + wireless + "','" + link + "','" + xcoordinate + "','" + ycoordinate + "','" + name + "')";
			return super.add(query);
	}
	
	@POST
	@Path ("/addRoomWithoutCoordinates")
	@Produces ("application/json")
	public String addRoomWithoutCoordinates(@FormParam("roomId") String roomId, @FormParam("locationId") String locationId, @FormParam("function") String function,@FormParam("capacity") String capacity, @FormParam("wireless") String wireless, @FormParam("link") String link,@FormParam("name") String name){
			String query = "INSERT INTO room (roomId,locationId,function,capacity,wireless,link,name) VALUES ('" + roomId + "','" + locationId + "','" + function + "','" + capacity + "','" + wireless + "','" + link + "','" + name + "')";
			return super.add(query);
	}
	
	/**
	 * Gets the room corresponding to the given user id.
	 *
	 * @param userId the user id
	 * @return the room corresponding to the given user id
	 */
	@POST
	@Path ("/getRoomByUserId")
	@Produces ("application/json")
	public String getRoomByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM room_user";
		if(userId!=null)query+= " WHERE userId like '" + userId + "'";
		return super.get(query);
	}
	
	/**
	 * Adds the room responsible.
	 *
	 * @param roomId the room id
	 * @param userId the user id
	 * @return the resultstring
	 */
	@POST
	@Path ("/addRoomResponsible")
	@Produces ("application/json")
	public String addRoomResponsible(@FormParam("roomId") String roomId, @FormParam("userId") String userId){
			String query = "INSERT INTO room_user (roomUserId,roomId,userId) VALUES (NULL,'"+ roomId + "','" + userId + "')";
			return super.add(query);
	}
	
	@POST
	@Path ("/addPicture")
	@Produces ("application/json")
	public String addPicture(@FormParam("roomId") String roomId, @FormParam("picture") String picture){
		String query = "INSERT INTO room_picture (roomPictureId,roomId,picture) VALUES (NULL,'"+ roomId + "','" + picture + "')";
		return super.add(query);
	}
	
	@POST
	@Path("/getPictureByName")
	@Produces("application/json")
	public String getPictureByName(@FormParam("name") String name){
		String query = "SELECT * FROM room_picture INNER JOIN room USING(roomId)";
		if(name!=null)query+= "WHERE name like '" + name + "'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getRoom")
	@Produces ("application/json")
	public String getRoom(@FormParam("name") String name){
		String query = "SELECT * FROM room";
		if(name !=null)query += " WHERE name like '%" + name + "%'";
		return super.get(query);
	}
}
