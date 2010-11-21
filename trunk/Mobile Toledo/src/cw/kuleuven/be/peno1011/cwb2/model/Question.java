package cw.kuleuven.be.peno1011.cwb2.model;

public class Question {
	
	private User questioner;
	private Lecture lecture;
	private String message;
	
	public Question(User questioner, Lecture lecture String message) {
		setQuestioner(questioner);
		setLecture(lecture);
		setMessage(message);
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
