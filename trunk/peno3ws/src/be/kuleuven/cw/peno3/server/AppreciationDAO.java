package be.kuleuven.cw.peno3.server;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path ("/AppreciationHandler")
public class AppreciationDAO extends DAO{

	/*
	 * Return the average score for a document or a question by giving the documentId or questionId
	 */
	@POST
	@Path ("/getAppreciation")
	@Produces ("application/json")
	public String getAppreciation(@FormParam("docQuestionId") String docQuestionId,@FormParam("isDocument") String isDocument){
		String query = "SELECT AVG(score) FROM appreciation";
		if(docQuestionId != null )query += " WHERE docQuestionId like '" + docQuestionId + "' and isDocument like '" + isDocument + "'";
		return super.get(query);
	}

	@POST
	@Path ("/listAppreciations")
	@Produces ("application/json")
	public String listAppreciations(){
		String query = "SELECT * FROM appreciation";
		return super.list(query);
	}

	@POST
	@Path ("/addAppreciation")
	@Produces ("application/json")
	public String addAppreciation(@FormParam("docQuestionId") String docQuestionId, @FormParam("isDocument") String isDocument, @FormParam("userId") String userId, @FormParam("score") String score){
		String query = "INSERT INTO appreciation (appreciationId,docQuestionId,isDocument,userId,score) VALUES (NULL,'"+ docQuestionId + "','" + isDocument + "','" + userId + "','" + score +"')";
		return super.add(query);
	}

}
