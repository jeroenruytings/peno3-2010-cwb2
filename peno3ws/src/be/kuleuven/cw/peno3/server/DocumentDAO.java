package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/DocumentHandler")
public class DocumentDAO extends DAO{
	
	@POST
	@Path ("/getDocumentByWord")
	@Produces ("application/json")
	public String getDocumentByWord(@FormParam("word") String word){
		String query = "SELECT * FROM document";
		if(word!=null)query+= " WHERE title like '%" + word + "%' or description like '%" + word + "%' ORDER BY uploadDate DESC";
		return super.get(query);
	}
	
	@POST
	@Path ("/getDocumentByCourseCode")
	@Produces ("application/json")
	public String getDocumentByCourseCode(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM document";
		if(courseCode!=null)query+= " WHERE courseCode like '%" + courseCode + "%' ORDER BY uploadDate DESC";
		return super.get(query);
	}
	
	@POST
	@Path ("/getDocumentByUploadDate")
	@Produces ("application/json")
	public String getDocumentByUploadDate(@FormParam("uploadDate") String uploadDate){
		String query = "SELECT * FROM document";
		if(uploadDate!=null)query+= " WHERE uploadDate>= '" + uploadDate + "' ORDER BY uploadDate DESC";
		return super.get(query);
	}
	
	@POST
	@Path ("/getDocumentByDateLastChange")
	@Produces ("application/json")
	public String getDocumentByDateLastChange(@FormParam("dateLastChange") String dateLastChange){
		String query = "SELECT * FROM document";
		if(dateLastChange!=null)query+= " WHERE dateLastChange>= '" + dateLastChange + "' ORDER BY dateLastChange DESC";
		return super.get(query);
	}
	
	@POST
	@Path ("/getDocumentByUserId")
	@Produces ("application/json")
	public String getDocumentByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM document";
		if(userId!=null)query+= " WHERE userId like '%" + userId + "%' ORDER BY uploadDate DESC";
		return super.get(query);
	}

	@POST
	@Path ("/listDocuments")
	@Produces ("application/json")
	public String listDocuments(){
		String query = "SELECT * FROM document";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	@POST
	@Path ("/addDocument")
	@Produces ("application/json")
	public String addDocument(@FormParam("description") String description, @FormParam("type") String type, @FormParam("size") int size, @FormParam("uploadDate") String uploadDate, @FormParam("userId") String userId, @FormParam("title") String title, @FormParam("link") String link, @FormParam("courseCode") String courseCode){
			String query = "INSERT INTO document (documentId,description,type,size,uploadDate,dateLastChange,userId,title,link,courseCode) VALUES (NULL,'"+ description + "','" + type + "','" + size + "'," + uploadDate + ",NOW(),'" + userId + "','" + title + "','" + title + "','" + link + "','" + courseCode +")";
			return super.add(query);
	}
}