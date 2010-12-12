package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class QuestionDAO {

	private static QuestionDAO QuestionDAO;

	public static QuestionDAO getInstance() {
		if (QuestionDAO == null) {
			QuestionDAO = new QuestionDAO();
		}
		return QuestionDAO; 
	}

	public void addQuestion(String userId, String question, int eventId) throws HttpException, IOException {
		HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/QuestionHandler/addQuestion");
        
        method.addParameter("userId", userId);
        method.addParameter("question", question);
        method.addParameter("eventId","" + eventId);

        int returnCode = client.executeMethod(method);
        String encryptedJson = method.getResponseBodyAsString();
	}
}
