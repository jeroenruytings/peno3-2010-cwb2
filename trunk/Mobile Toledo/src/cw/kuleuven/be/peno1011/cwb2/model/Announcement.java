package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.Date;

/**
 * Modelclass of announcements
 *
 */
public class Announcement {
	
	private Date date;
	private User user; 
	private String title;
	private String message;
	private Course course;
	
	/**
	 * @param user
	 * @param course
	 * @param title
	 * @param message
	 */
	public Announcement(User user, Course course, String title, String message) {
		 setDate(date);
		 setUser(user);
		 setMessage(message);
		 this.course = course;
		 setTitle(title);
	}
	
	private Announcement(){
		
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

	public void setCourse(Course course){
		this.course = course;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
