package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/IspHandler")
public class IspDAO extends DAO{
	
	@POST
	@Path ("/getIspByUserId")
	@Produces ("application/json")
	public String getIspByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM isp";
		if(userId!=null)query+= " WHERE userId like '%" + userId + "%'";
		return super.get(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addIsp")
	@Produces ("application/json")
	public String addIsp(@FormParam("userId") String userId, @FormParam("group") String group, @FormParam("studies") String studies, @FormParam("phase") int phase){
			String query = "INSERT INTO isp (userId,group,studies,phase) VALUES ('" + userId + "','" + group + "','" + studies + "','" + phase +"')";
			return super.add(query);
	}
	
	@POST
	@Path ("/getCoursesByUserId")
	@Produces ("application/json")
	public String getCoursesByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM course_isp";
		if(userId!=null)query+= " WHERE userId like '%" + userId + "%'";
		return super.get(query);
	}
}