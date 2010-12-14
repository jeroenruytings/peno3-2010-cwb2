package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.ArrayList;

public class MultipleChoice {
	
	private String question;
	private int multipleChoiceId;
	private ArrayList<Answer> answers;
	
	public MultipleChoice(String question, ArrayList<Answer> answers, int multipleChoiceId) {
		setQuestion(question);
		setAnswers(answers);
		setMultipleChoiceId(multipleChoiceId);
	}
	private MultipleChoice() { }

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
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
