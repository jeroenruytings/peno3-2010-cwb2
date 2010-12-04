package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/MultipleChoiceHandler")
public class MultipleChoiceDAO extends DAO{
	
	@POST
	@Path ("/getAnswerByUserId")
	@Produces ("application/json")
	public String getAswerByUserId(@FormParam("userId") String userId){
		String query = "SELECT * FROM answer";
		if(userId!=null)query+= " WHERE userId like '%" + userId + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getAnswerByPossibleAnswerId")
	@Produces ("application/json")
	public String getAnswerByPossibleAnswerId(@FormParam("possibleAnswerId") String possibleAnswerId){
		String query = "SELECT * FROM answer";
		if(possibleAnswerId!=null)query+= " WHERE possibleAnswerId like '%" + possibleAnswerId + "%'";
		return super.get(query);
	}

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
	@POST
	@Path ("/addAnswer")
	@Produces ("application/json")
	public String addAnnouncement(@FormParam("userId") String userId, @FormParam("possibleAnswerId") String possibleAnswerId){
			String query = "INSERT INTO answer (answerId,userId,possibleAnswerId) VALUES (NULL,'"+ userId + "','" + possibleAnswerId + "')";
			return super.add(query);
	}
	
	@POST
	@Path ("/getMultipleChoiceByEventId")
	@Produces ("application/json")
	public String getMultipleChoice(@FormParam("eventId") String eventId){
		String query = "SELECT * FROM multipleChoice";
		if(eventId!=null)query+= " WHERE eventId like '" + eventId + "'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getMultipleChoiceByWord")
	@Produces ("application/json")
	public String getMultipleChoiceByWord(@FormParam("word") String word){
		String query = "SELECT * FROM multipleChoice";
		if(word!=null)query+= " WHERE question like '%" + word + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/listMultipleChoices")
	@Produces ("application/json")
	public String listMultipleChoices(){
		String query = "SELECT * FROM multipleChoice";
		return super.list(query);
	}
	
	@POST
	@Path ("/addMultipleChoice")
	@Produces ("application/json")
	public String addMultipleChoice(@FormParam("question") String question, @FormParam("eventId") String eventId){
			String query = "INSERT INTO multipleChoice (multipleChoiceId,question,eventId) VALUES (NULL,'"+ question + "','" + eventId + "')";
			return super.add(query);
	}
	
	@POST
	@Path ("/getPossibleAnswerByMultipleChoiceId")
	@Produces ("application/json")
	public String getPossibleAnswerByMultipleChoiceId(@FormParam("multipleChoiceId") String multipleChoiceId){
		String query = "SELECT * FROM possibleAnswer";
		if(multipleChoiceId!=null)query+= " WHERE multipleChoiceId like '%" + multipleChoiceId + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/listPossibleAnswers")
	@Produces ("application/json")
	public String listPossibleAnswers(){
		String query = "SELECT * FROM possibleAnswer";
		return super.list(query);
	}
	
	@POST
	@Path ("/addPossibleAnswer")
	@Produces ("application/json")
	public String addPossibleAnswer(@FormParam("multipleChoiceId") String multipleChoiceId, @FormParam("answer") String answer){
			String query = "INSERT INTO possibleAnswer (possibleAnswerId,multipleChoiceId,answer) VALUES (NULL,'"+ multipleChoiceId + "','" + answer + "')";
			return super.add(query);
	}
}