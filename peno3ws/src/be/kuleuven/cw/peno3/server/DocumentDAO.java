/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the DocumentDAO class.
 * DocumentDAO will be used to communicate with our MySQL database.
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
 * The Class DocumentDAO.
 */
@Path ("/DocumentHandler")
public class DocumentDAO extends DAO{
	
	/**
	 * Gets the document corresponding to the given word.
	 *
	 * @param word the word
	 * @return the document corresponding to the given word
	 */
	@POST
	@Path ("/getDocumentByWord")
	@Produces ("application/json")
	public String getDocumentByWord(@FormParam("word") String word){
		String query = "SELECT * FROM document";
		if(word!=null)query+= " WHERE title like '%" + word + "%' or description like '%" + word + "%' ORDER BY uploadDate DESC";
		return super.get(query);
	}
	
	/**
	 * Gets the document corresponding to the given course code. Results are ordered by descending uploaddate.
	 *
	 * @param courseCode the course code
	 * @return the document corresponding to the given course code
	 */
	@POST
	@Path ("/getDocumentByCourseCode")
	@Produces ("application/json")
	public String getDocumentByCourseCode(@FormParam("courseCode") String courseCode){
		String query = "SELECT * FROM document";
		if(courseCode!=null)query+= " WHERE courseCode like '" + courseCode + "' ORDER BY uploadDate DESC";
		return super.get(query);
	}
	
	/**
	 * Gets the document corresponding to the last upload date. Results are ordered by descending uploaddate.
	 *
	 * @param uploadDate the upload date
	 * @return the document corresponding to the last upload date
	 */
	@POST
	@Path ("/getDocumentByUploadDate")
	@Produces ("application/json")
	public String getDocumentByUploadDate(@FormParam("uploadDate") String uploadDate){
		String query = "SELECT * FROM document";
		if(uploadDate!=null)query+= " WHERE uploadDate>= '" + uploadDate + "' ORDER BY uploadDate DESC";
		return super.get(query);
	}
	
	/**
	 * Gets the document corresponding to the last changed date. Results are ordered by descending date of the last change.
	 *
	 * @param dateLastChange the last changed date
	 * @return the document  corresponding to the given esponding to the last changed
	 */
	@POST
	@Path ("/getDocumentByDateLastChange")
	@Produces ("application/json")
	public String getDocumentByDateLastChange(@FormParam("dateLastChange") String dateLastChange){
		String query = "SELECT * FROM document";
		if(dateLastChange!=null)query+= " WHERE dateLastChange>= '" + dateLastChange + "' ORDER BY dateLastChange DESC";
		return super.get(query);
	}
	
	/**
	 * Gets the document corresponding to the given user id. Results are ordered by descending uploaddate.
	 *
	 * @param userId the user id
	 * @return the document corresponding to the given user id
	 */
	@POST
	@Path ("/getDocumentByUserId")
	@Produces ("application/json")
	public String getDocumentByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM document";
		if(userId!=null)query+= " WHERE userId like '%" + userId + "%' ORDER BY uploadDate DESC";
		return super.get(query);
	}

	/**
	 * List documents. Results are ordered first by descending uploaddate then by ascending title.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listDocuments")
	@Produces ("application/json")
	public String listDocuments(){
		String query = "SELECT * FROM document ORDER BY uploadDate DESC, title ASC";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	/**
	 * Adds the document.
	 *
	 * @param description the description
	 * @param type the type
	 * @param size the size
	 * @param uploadDate the upload date
	 * @param userId the user id
	 * @param title the title
	 * @param link the link
	 * @param courseCode the course code
	 * @return the resultstring
	 */
	@POST
	@Path ("/addDocument")
	@Produces ("application/json")
	public String addDocument(@FormParam("description") String description, @FormParam("type") String type, @FormParam("size") int size, @FormParam("uploadDate") String uploadDate, @FormParam("userId") String userId, @FormParam("title") String title, @FormParam("link") String link, @FormParam("courseCode") String courseCode){
			String query = "INSERT INTO document (documentId,description,type,size,uploadDate,dateLastChange,userId,title,link,courseCode) VALUES (NULL,'"+ description + "','" + type + "','" + size + "'," + uploadDate + ",NOW(),'" + userId + "','" + title + "','" + title + "','" + link + "','" + courseCode +")";
			return super.add(query);
	}
}