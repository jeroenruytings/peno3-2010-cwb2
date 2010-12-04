package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/QuestionHandler")
public class QuestionDAO extends DAO{
	
	@POST
	@Path ("/getQuestionByWord")
	@Produces ("application/json")
	public String getQuestionByWord(@FormParam("word") String word){
		String query = "SELECT * FROM question";
		if(word!=null)query+= " WHERE question like '%" + word + "%'";
		return super.get(query);
	}
	
	@POST
	@Path ("/getQuestionByEventId")
	@Produces ("application/json")
	public String getQuestionByEventId(@FormParam("eventId") String eventId){
		String query = "SELECT * FROM question";
		if(eventId!=null)query+= " WHERE eventId like '" + eventId + "'";
		return super.get(query);
	}
	
	
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
	@POST
	@Path ("/addQuestion")
	@Produces ("application/json")
	public String addQuestion(@FormParam("userId") String userId, @FormParam("eventId") String eventId){
			String query = "INSERT INTO question (questionId,userId,eventId) VALUES (NULL,'"+ userId + "','" + eventId + "')";
			return super.add(query);
	}
}