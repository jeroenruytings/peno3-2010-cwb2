/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the QuestionDAO class.
 * QuestionDAO will be used to communicate with our MySQL database.
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
 * The Class QuestionDAO.
 */
@Path ("/QuestionHandler")
public class QuestionDAO extends DAO{
	
	/**
	 * Gets the question corresponding to the given word.
	 *
	 * @param word the word
	 * @return the question corresponding to the given word
	 */
	@POST
	@Path ("/getQuestionByWord")
	@Produces ("application/json")
	public String getQuestionByWord(@FormParam("word") String word){
		String query = "SELECT * FROM question";
		if(word!=null)query+= " WHERE question like '%" + word + "%'";
		return super.get(query);
	}
	
	/**
	 * Gets the question corresponding to the given event id.
	 *
	 * @param eventId the event id
	 * @return the question corresponding to the given event id
	 */
	@POST
	@Path ("/getQuestionByEventId")
	@Produces ("application/json")
	public String getQuestionByEventId(@FormParam("eventId") String eventId){
		String query = "SELECT * FROM question";
		if(eventId!=null)query+= " WHERE eventId like '" + eventId + "'";
		return super.get(query);
	}
	
	
	/**
	 * List questions.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listQuestions")
	@Produces ("application/json")
	public String listQuestions(){
		String query = "SELECT * FROM question";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	/**
	 * Adds the question.
	 *
	 * @param userId the user id
	 * @param eventId the event id
	 * @return the resultstring
	 */
	@POST
	@Path ("/addQuestion")
	@Produces ("application/json")
	public String addQuestion(@FormParam("userId") String userId, @FormParam("eventId") String eventId){
			String query = "INSERT INTO question (questionId,userId,eventId) VALUES (NULL,'"+ userId + "','" + eventId + "')";
			return super.add(query);
	}
}