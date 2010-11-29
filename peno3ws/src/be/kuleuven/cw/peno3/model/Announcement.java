package be.kuleuven.cw.peno3.model;

import java.util.Date;

public class Announcement {
	
	private Date date;
	private User user; //degene die de aankondiging maakte
	private String title;
	private String message;
	private String courseCode;
	//private final Course course;
	
	public Announcement(User user, String courseCode, String title, String message) {
		 setDate(date);
		 setUser(user);
		 setMessage(message);
		 this.courseCode = courseCode;
		 setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public String getCourseCode() {
		return courseCode;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
