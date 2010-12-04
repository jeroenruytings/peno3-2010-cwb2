/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the UserDAO class.
 * UserDAO will be used to communicate with our MySQL database.
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
 * The Class UserDAO.
 */
@Path ("/UserHandler")
public class UserDAO extends DAO{

	/**
	 * Gets the user corresponding to the given name.
	 *
	 * @param name the name
	 * @return the user corresponding to the given name
	 */
	@POST
	@Path ("/getUserByName")
	@Produces ("application/json")
	public String getUserByName(@FormParam("name") String name){
		String query = "SELECT * FROM user";
		if(name !=null)query += " WHERE firstName like '%" + name + "%' or lastName like '%" + name + "%'";
		return super.get(query);
	}

	/**
	 * Gets the user corresponding to the given id.
	 *
	 * @param userId the user id
	 * @return the user corresponding to the given id
	 */
	@POST
	@Path ("/getUserById")
	@Produces("application/json")
	public String getUserById(@FormParam("userId") String userId){
		String query = "SELECT * FROM user";
		if(userId !=null)query += " WHERE userId like '" + userId + "'";
		return super.get(query);
	}
	
	/**
	 * List users.
	 *
	 * @return the resultstring
	 */
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
	/**
	 * Adds the user.
	 *
	 * @param userId the user id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param password the password
	 * @param birthDate the birth date
	 * @param rank the rank
	 * @return the resultstring
	 */
	@POST
	@Path ("/addUser")
	@Produces ("application/json")
	public String addUser(@FormParam("userId") String userId, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("password") String password, @FormParam("birthDate") String birthDate, @FormParam("rank") int rank){
		String query = "INSERT INTO user (userId, firstName, lastName, password, birthDate, rank) VALUES ('"+ userId + "','" + firstName + "','" + lastName + "','" + password + "'," + birthDate + ",'" + rank +"')";
		return super.add(query);
	}
}
