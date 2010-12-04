/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:36
 * 
 * This is the AppreciationDAO class.
 * AppreciationDAO will be used to communicate with our MySQL database.
 * All inside methods are called using the postmethods of a webservice. AppreciationDAO
 */

package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// TODO: Auto-generated Javadoc
/**
 * The Class AppreciationDAO.
 */
@Path ("/AppreciationHandler")
public class AppreciationDAO extends DAO{

	/*
	 * Return the average score for a document or a question corresponding to the given giving the documentId or questionId
	 */
	/**
	 * Gets the appreciation.
	 *
	 * @param docQuestionId the doc question id
	 * @param isDocument the is document
	 * @return the appreciation
	 */
	@POST
	@Path ("/getAppreciation")
	@Produces ("application/json")
	public String getAppreciation(@FormParam("docQuestionId") String docQuestionId,@FormParam("isDocument") String isDocument){
		String query = "SELECT AVG(score) FROM appreciation";
		if(docQuestionId != null )query += " WHERE docQuestionId like '" + docQuestionId + "' and isDocument like '" + isDocument + "'";
		return super.get(query);
	}

	/**
	 * List appreciations.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listAppreciations")
	@Produces ("application/json")
	public String listAppreciations(){
		String query = "SELECT * FROM appreciation";
		return super.list(query);
	}

	/**
	 * Adds the appreciation.
	 *
	 * @param docQuestionId the doc question id
	 * @param isDocument the is document
	 * @param userId the user id
	 * @param score the score
	 * @return the resultstring
	 */
	@POST
	@Path ("/addAppreciation")
	@Produces ("application/json")
	public String addAppreciation(@FormParam("docQuestionId") String docQuestionId, @FormParam("isDocument") String isDocument, @FormParam("userId") String userId, @FormParam("score") String score){
		String query = "INSERT INTO appreciation (appreciationId,docQuestionId,isDocument,userId,score) VALUES (NULL,'"+ docQuestionId + "','" + isDocument + "','" + userId + "','" + score +"')";
		return super.add(query);
	}

}
