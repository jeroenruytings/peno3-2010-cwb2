/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the IspDAO class.
 * IspDAO will be used to communicate with our MySQL database.
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
 * The Class IspDAO.
 */
@Path ("/IspHandler")
public class IspDAO extends DAO{
	
	/**
	 * Gets the isp corresponding to the given user id.
	 *
	 * @param userId the user id
	 * @return the isp corresponding to the given user id
	 */
	@POST
	@Path ("/getIspByUserId")
	@Produces ("application/json")
	public String getIspByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM isp";
		if(userId!=null)query+= " WHERE userId like '" + userId + "'";
		return super.get(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	/**
	 * Adds the isp.
	 *
	 * @param userId the user id
	 * @param group the group
	 * @param studies the studies
	 * @param phase the phase
	 * @return the resultstring
	 */
	@POST
	@Path ("/addIsp")
	@Produces ("application/json")
	public String addIsp(@FormParam("userId") String userId, @FormParam("group") String group, @FormParam("studies") String studies, @FormParam("phase") int phase){
			String query = "INSERT INTO isp (userId,group,studies,phase) VALUES ('" + userId + "','" + group + "','" + studies + "','" + phase +"')";
			return super.add(query);
	}
	
	/**
	 * Gets the courses corresponding to the given user id.
	 *
	 * @param userId the user id
	 * @return the courses corresponding to the given user id
	 */
	@POST
	@Path ("/getCoursesByUserId")
	@Produces ("application/json")
	public String getCoursesByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM course_isp";
		if(userId!=null)query+= " WHERE userId like '" + userId + "'";
		return super.get(query);
	}
}