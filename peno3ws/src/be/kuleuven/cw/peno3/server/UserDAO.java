package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path ("/UserHandler")
public class UserDAO extends DAO{

	@POST
	@Path ("/getUserByName")
	@Produces ("application/json")
	public String getUserByName(@FormParam("name") String name){
		String query = "SELECT * FROM user";
		if(name !=null)query += " WHERE firstName like '%" + name + "%' or lastName like '%" + name + "%'";
		return super.get(query);
	}

	@POST
	@Path ("/getUserById")
	@Produces("application/json")
	public String getUserById(@FormParam("userId") String userId){
		String query = "SELECT * FROM user";
		if(userId !=null)query += " WHERE userId like '%" + userId + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/listUsers")
	@Produces ("application/json")
	public String listUsers(){
		String query = "SELECT * FROM user";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addUser")
	@Produces ("application/json")
	public String addUser(@FormParam("userId") String userId, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("password") String password, @FormParam("birthDate") String birthDate, @FormParam("rank") int rank){
		String query = "INSERT INTO user (userId, firstName, lastName, password, birthDate, rank) VALUES ('"+ userId + "','" + firstName + "','" + lastName + "','" + password + "'," + birthDate + ",'" + rank +"')";
		return super.add(query);
	}
}
