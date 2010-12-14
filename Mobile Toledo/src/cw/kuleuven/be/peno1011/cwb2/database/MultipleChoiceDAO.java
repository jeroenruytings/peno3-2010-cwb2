package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.Answer;
import cw.kuleuven.be.peno1011.cwb2.model.MultipleChoice;

public class MultipleChoiceDAO {

	private static MultipleChoiceDAO multipleChoiceDAO;
	private Cryptography cryptography = Cryptography.getInstance();
	
	private MultipleChoiceDAO() {}
	
	public static MultipleChoiceDAO getInstance() {
		if(multipleChoiceDAO==null){
			multipleChoiceDAO = new MultipleChoiceDAO();
		}
		return multipleChoiceDAO;
	}
	
	public ArrayList<MultipleChoice> getMultipleChoice(int eventId) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/MultipleChoiceHandler/getMultipleChoiceByEventId");
		method.addParameter("eventId","" + eventId);
		
		int returnCode = client.executeMethod(method);
		String json = cryptography.decrypt(method.getResponseBodyAsString());
		
		MultipleChoice[] multipleArray;
		Answer[] answerArray;
		ArrayList<MultipleChoice> multipleChoices = new ArrayList<MultipleChoice>();
		
		multipleArray = new Gson().fromJson(json.toString(), MultipleChoice[].class);
		answerArray = new Gson().fromJson(json.toString(), Answer[].class);
		
		if(json.contains("[]")) {
			multipleChoices = null;
		}
		// add all the multipleChoices only once to multipleChoices
		else {
			int i = 0;
			multipleChoices.add(multipleArray[0]);
			while(i<multipleArray.length) {
				int j =0;
				Boolean present = false;
				while(j<multipleChoices.size()) {
					System.out.println(multipleArray[i].getMultipleChoiceId());
					System.out.println(multipleChoices.get(j).getMultipleChoiceId());
					if(multipleArray[i].getMultipleChoiceId()==multipleChoices.get(j).getMultipleChoiceId()) {
						present = true;
					}
				j++;
				}
				if(!present)
					multipleChoices.add(multipleArray[i]);
			i++;
			}
		}
		// add all the corresponding answers to the multipleChoice
		if(multipleChoices!=null){
			int k =0;
			while(k<multipleChoices.size()) {
				int l = 0;
				MultipleChoice multipleChoice = multipleChoices.get(k);
				if(multipleChoice.getAnswers()==null){
					multipleChoice.setAnswers(new ArrayList<Answer>());
				}
				while(l<answerArray.length) {
					Answer answer = answerArray[l];
					if(multipleChoice.getMultipleChoiceId()==answer.getMultipleChoiceId()) {
						multipleChoice.addAnswer(answer);
					}
					l++;
				}
				k++;
			}
		}
		
		return multipleChoices;
	}
}
