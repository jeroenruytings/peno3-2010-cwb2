package be.kuleuven.cw.peno3.model;

import java.util.ArrayList;

public class MultipleChoice {
	
	private String question;
	private Lecture lecture;
	private String[] possibleAnswers;
	private ArrayList<User> responders;
	private int[] answers;
	
	public MultipleChoice(Lecture lecture, String question, ArrayList<String> answers) {
		setLecture(lecture);
		setQuestion(question);
		setPossibleAnswers(possibleAnswers);
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

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
}