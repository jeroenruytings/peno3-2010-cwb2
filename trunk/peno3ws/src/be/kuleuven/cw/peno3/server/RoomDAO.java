package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/RoomHandler")
public class RoomDAO extends DAO{
	
	@POST
	@Path ("/getRoomByLocationId")
	@Produces ("application/json")
	public String getRoomByLocationId(@FormParam("locationId") String locationId){
		String query = "SELECT * FROM room";
		if(locationId!=null)query+= " WHERE locationId like '" + locationId + "'";
		return super.get(query);
	}

	@POST
	@Path ("/listAnnouncements")
	@Produces ("application/json")
	public String listRooms(){
		String query = "SELECT * FROM room";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addRoom")
	@Produces ("application/json")
	public String addRoom(@FormParam("roomId") String roomId, @FormParam("locationId") String locationId, @FormParam("function") String function, @FormParam("accomodation") String accomodation, @FormParam("capacity") String capacity, @FormParam("wireless") String wireless, @FormParam("picture") String picture, @FormParam("link") String link, @FormParam("xcoordinate") String xcoordinate, @FormParam("ycoordinate") String ycoordinate){
			String query = "INSERT INTO room (roomId,locationId,function,accomodation,capacity,wireless,picture,link,xcoordinate,ycoordinate) VALUES ('"+ roomId + "','" + locationId + "','" + function + "','" + accomodation + "','" + capacity + "','" + wireless + "','" + picture + "','" + link + "','" + xcoordinate + "','" + ycoordinate + "')";
			return super.add(query);
	}
	
	@POST
	@Path ("/getRoomByUserId")
	@Produces ("application/json")
	public String getRoomByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM room_user";
		if(userId!=null)query+= " WHERE userId like '" + userId + "'";
		return super.get(query);
	}
	
	@POST
	@Path ("/addRoomResponsible")
	@Produces ("application/json")
	public String addRoomResponsible(@FormParam("roomId") String roomId, @FormParam("userId") String userId){
			String query = "INSERT INTO room_user (roomUserId,roomId,userId) VALUES (NULL,'"+ roomId + "','" + userId + "')";
			return super.add(query);
	}
}
