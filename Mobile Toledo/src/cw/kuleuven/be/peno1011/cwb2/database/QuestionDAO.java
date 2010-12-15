package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.Appreciation;
import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.Question;
import cw.kuleuven.be.peno1011.cwb2.model.User;

public class QuestionDAO {

	private static QuestionDAO QuestionDAO;

	public static QuestionDAO getInstance() {
		if (QuestionDAO == null) {
			QuestionDAO = new QuestionDAO();
		}
		return QuestionDAO; 
	}

	public void addQuestion(String userId, String question, String eventId) throws HttpException, IOException {
		HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/QuestionHandler/addQuestion");
        
        method.addParameter("userId", userId);
        method.addParameter("question", question);
        method.addParameter("eventId","" + eventId);

        int returnCode = client.executeMethod(method);
        String encryptedJson = method.getResponseBodyAsString();
	}
	
	
	public Question[] getQuestionByEventId(String eventId) throws HttpException, IOException {
		HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/QuestionHandler/getQuestionByEventId");
        
        method.addParameter("eventId","" + eventId);

        int returnCode = client.executeMethod(method);
        String encryptedJson = method.getResponseBodyAsString();
		String json = Cryptography.getInstance().decrypt(encryptedJson);
		
		Question[] questions = new Gson().fromJson(json.toString(), Question[].class);
		
		for(int i = 0; i < questions.length; i++){
	        PostMethod method2 = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/AppreciationHandler/getAppreciation");
	        
	        method.addParameter("docQuestionId","" + questions[i].getQuestionId());
	        method.addParameter("isDocument", "0");

	        returnCode = client.executeMethod(method2);
	        String encryptedJson2 = method2.getResponseBodyAsString();
			String json2 = Cryptography.getInstance().decrypt(encryptedJson2);
			
			if(json2.contains("[]")){
				questions[i].setAppreciation(new Appreciation(0, new ArrayList<User>()));
			}
			else{
				Appreciation[] appreciations = new Gson().fromJson(json.toString(), Appreciation[].class);
				questions[i].setAppreciation(appreciations[0]);
			}
			
		}
		return questions;
	}
	
}
