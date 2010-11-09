package cw.kuleuven.be.peno1011.cwb2;

import java.util.Calendar;
import java.util.Date;

public class Announcement {
	
	private final Calendar calendar = Calendar.getInstance();
	private final Date date;
	private final User user;
	private String title;
	private final Course course;
	
	public Announcement(User user, Course course, String title) {
		 date = calendar.getTime();
		 this.user = user;
		 this.course = course;
		 setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public Date getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public Course getCourse() {
		return course;
	}
	
	
}
