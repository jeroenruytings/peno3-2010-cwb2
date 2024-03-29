package cw.kuleuven.be.peno1011.cwb2.model;

public class Question {
	
	private User questioner;
	private Lecture lecture;
	private String question;
	private Appreciation appreciation;
	private int questionId;
	
	
	private Question(){
		
	}
	
	public Appreciation getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(Appreciation appreciation) {
		this.appreciation = appreciation;
	}

	public Question(User questioner, Lecture lecture, String message) {
		setQuestioner(questioner);
		setLecture(lecture);
		setMessage(message);
	}
	
	public String getMessage() {
		return question;
	}

	public User getQuestioner() {
		return questioner;
	}
	
	public void setMessage(String message) {
		this.question = message;
	}
	
	
	public void setQuestioner(User questioner) {
		this.questioner = questioner;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionId() {
		return questionId;
	}


}
