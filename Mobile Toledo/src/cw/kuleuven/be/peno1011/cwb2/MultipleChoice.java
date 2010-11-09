package cw.kuleuven.be.peno1011.cwb2;

import java.util.ArrayList;

public class MultipleChoice {
	
	private String question;
	private Lecture lecture;
	private ArrayList<String> possibleAnswers;
	private ArrayList<User> responders;
	private int[] results;
	
	public MultipleChoice(Lecture lecture, String question, ArrayList<String> answers) {
		setLecture(lecture);
		setQuestion(question);
		setPossibleAnswers(possibleAnswers);
	}

	public ArrayList<String> getPossibleAnswers() {
		return possibleAnswers;
	}

	public void setPossibleAnswers(ArrayList<String> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public ArrayList<User> getResponders() {
		return responders;
	}

	public void setResponders(ArrayList<User> responders) {
		this.responders = responders;
	}

	public int[] getResults() {
		return results;
	}

	public void setResults(int[] results) {
		this.results = results;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
}
