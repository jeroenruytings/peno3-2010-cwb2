package cw.kuleuven.be.peno1011.cwb2.model;

public class Question {
	
	private User questioner;
	private Lecture lecture;
	
	public Question(User questioner, Lecture lecture) {
		setQuestioner(questioner);
		setLecture(lecture);
	}
	
	public User getQuestioner() {
		return questioner;
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
