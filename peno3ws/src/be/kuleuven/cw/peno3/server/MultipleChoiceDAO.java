/*
 * @author P&O 3 2010 CWB2
 * @version 4-dec-2010 22:48:55
 * 
 * This is the MultipleChoiceDAO class.
 * MultipleChoiceDAO will be used to communicate with our MySQL database.
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
 * The Class MultipleChoiceDAO.
 */
@Path ("/MultipleChoiceHandler")
public class MultipleChoiceDAO extends DAO{
	
	/**
	 * Gets the aswer corresponding to the given user id.
	 *
	 * @param userId the user id
	 * @return the aswer corresponding to the given user id
	 */
	@POST
	@Path ("/getAnswerByUserId")
	@Produces ("application/json")
	public String getAswerByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM answer";
		if(userId!=null)query+= " WHERE userId like '" + userId + "'";
		return super.get(query);
	}
	
	/*
	 * Return how many users have voted on the possibleAnswer with the given possibleAnswerId
	 */
	/**
	 * Gets the answer corresponding to the given possible answer id.
	 *
	 * @param possibleAnswerId the possible answer id
	 * @return the total amount of users who voted for this answer
	 */
	@POST
	@Path ("/getAnswerByPossibleAnswerId")
	@Produces ("application/json")
	public String getAnswerByPossibleAnswerId(@FormParam("possibleAnswerId") String possibleAnswerId){
		String query = "SELECT COUNT(*) FROM answer";
		if(possibleAnswerId!=null)query+= " WHERE possibleAnswerId like '" + possibleAnswerId + "'";
		return super.get(query);
	}

	/**
	 * List answers.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listAnswers")
	@Produces ("application/json")
	public String listAnswers(){
		String query = "SELECT * FROM answer";
		return super.list(query);
	}

	/*
	 * Method adds an announcement to the database	
	 */
	/**
	 * Adds the announcement.
	 *
	 * @param userId the user id
	 * @param possibleAnswerId the possible answer id
	 * @return the resultstring
	 */
	@POST
	@Path ("/addAnswer")
	@Produces ("application/json")
	public String addAnnouncement(@FormParam("userId") String userId, @FormParam("possibleAnswerId") String possibleAnswerId){
			String query = "INSERT INTO answer (answerId,userId,possibleAnswerId) VALUES (NULL,'"+ userId + "','" + possibleAnswerId + "')";
			return super.add(query);
	}
	
	/**
	 * Gets the multiple choice.
	 *
	 * @param eventId the event id
	 * @return the multiple choice
	 */
	@POST
	@Path ("/getMultipleChoiceByEventId")
	@Produces ("application/json")
	public String getMultipleChoice(@FormParam("eventId") String eventId){
		if(eventId==null || eventId.equals(""))eventId="%";
		String query = "SELECT possibleanswer.answer, multipleChoice.multipleChoiceId, multipleChoice.question, IFNULL(SubTotal.Total,0) AS Total FROM possibleanswer INNER JOIN multipleChoice AS multipleChoice ON multipleChoice.multipleChoiceId = possibleanswer.multipleChoiceId LEFT JOIN (SELECT answer.possibleAnswerId, COUNT(answer.possibleAnswerId) AS Total FROM answer GROUP BY answer.possibleAnswerId) AS SubTotal ON possibleanswer.possibleAnswerId = SubTotal.possibleAnswerId WHERE multipleChoice.eventId = '"+ eventId + "'";
		return super.get(query);
	}
	
	/**
	 * Gets the multiple choice corresponding to the given word.
	 *
	 * @param word the word
	 * @return the multiple choice corresponding to the given word
	 */
	@POST
	@Path ("/getMultipleChoiceByWord")
	@Produces ("application/json")
	public String getMultipleChoiceByWord(@FormParam("word") String word){
		String query = "SELECT * FROM multipleChoice";
		if(word!=null)query+= " WHERE question like '%" + word + "%'";
		return super.get(query);
	}
	
	/**
	 * List multiple choices.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listMultipleChoices")
	@Produces ("application/json")
	public String listMultipleChoices(){
		String query = "SELECT * FROM multipleChoice";
		return super.list(query);
	}
	
	/**
	 * Adds the multiple choice.
	 *
	 * @param question the question
	 * @param eventId the event id
	 * @return the resultstring
	 */
	@POST
	@Path ("/addMultipleChoice")
	@Produces ("application/json")
	public String addMultipleChoice(@FormParam("question") String question, @FormParam("eventId") String eventId){
			String query = "INSERT INTO multipleChoice (multipleChoiceId,question,eventId) VALUES (NULL,'"+ question + "','" + eventId + "')";
			return super.add(query);
	}
	
	/**
	 * Gets the possible answer corresponding to the given multiple choice id.
	 *
	 * @param multipleChoiceId the multiple choice id
	 * @return the possible answer corresponding to the given multiple choice id
	 */
	@POST
	@Path ("/getPossibleAnswerByMultipleChoiceId")
	@Produces ("application/json")
	public String getPossibleAnswerByMultipleChoiceId(@FormParam("multipleChoiceId") String multipleChoiceId){
		String query = "SELECT * FROM possibleAnswer";
		if(multipleChoiceId!=null)query+= " WHERE multipleChoiceId like '" + multipleChoiceId + "'";
		return super.get(query);
	}
	
	/**
	 * List possible answers.
	 *
	 * @return the resultstring
	 */
	@POST
	@Path ("/listPossibleAnswers")
	@Produces ("application/json")
	public String listPossibleAnswers(){
		String query = "SELECT * FROM possibleAnswer";
		return super.list(query);
	}
	
	/**
	 * Adds the possible answer.
	 *
	 * @param multipleChoiceId the multiple choice id
	 * @param answer the answer
	 * @return the resultstring
	 */
	@POST
	@Path ("/addPossibleAnswer")
	@Produces ("application/json")
	public String addPossibleAnswer(@FormParam("multipleChoiceId") String multipleChoiceId, @FormParam("answer") String answer){
			String query = "INSERT INTO possibleAnswer (possibleAnswerId,multipleChoiceId,answer) VALUES (NULL,'"+ multipleChoiceId + "','" + answer + "')";
			return super.add(query);
	}
}