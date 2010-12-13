package be.kuleuven.cw.peno3.model;

public class Question {
	
	private User questioner;
	private Lecture lecture;
	private String message;
	private Appreciation appreciation;
	
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
		return message;
	}

	public User getQuestioner() {
		return questioner;
	}
	
	public void setMessage(String message) {
		this.message = message;
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

}
