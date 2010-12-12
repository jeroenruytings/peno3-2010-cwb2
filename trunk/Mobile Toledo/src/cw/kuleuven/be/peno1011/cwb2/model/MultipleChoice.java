package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.ArrayList;

public class MultipleChoice {
	
	private String question;
	private int multipleChoiceId;
	private String[] possibleAnswers;
	private ArrayList<User> responders;
	private int[] answers;
	
	public MultipleChoice(Lecture lecture, String question, ArrayList<String> answers, int multipleChoiceId) {
		setQuestion(question);
		setPossibleAnswers(possibleAnswers);
		setMultipleChoiceId(multipleChoiceId);
	}

	public String[] getPossibleAnswers() {
		return possibleAnswers;
	}

	public void setPossibleAnswers(String[] possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public ArrayList<User> getResponders() {
		return responders;
	}

	public void setResponders(ArrayList<User> responders) {
		this.responders = responders;
	}
	
	public void addResponder(User user){
		responders.add(user);
	}

	public int[] getAnswers() {
		return answers;
	}

	public void addAnswer(int i) {
		answers[answers.length] = i;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setMultipleChoiceId(int multipleChoiceId) {
		this.multipleChoiceId = multipleChoiceId;
	}

	public int getMultipleChoiceId() {
		return multipleChoiceId;
	}
}
