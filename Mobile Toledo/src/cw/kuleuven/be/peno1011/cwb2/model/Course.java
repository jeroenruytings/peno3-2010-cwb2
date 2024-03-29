package cw.kuleuven.be.peno1011.cwb2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Course  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7925129656165780396L;
	private String course;
	private String courseCode;
	private User prof;
	private String academicYear;
	private ArrayList<Lecture> lectures = new ArrayList<Lecture>();
	private ArrayList<Document> documents = new ArrayList<Document>();
	private LinkedList<Announcement> announcements;
	
	
	public Course(String courseName, String courseCode,
			User prof, String academicYear) {
		setProf(prof);
		this.courseCode = courseCode;
		this.academicYear = academicYear;
	}
	
	private Course(){}

	public String getCourseName() {
		return course;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public User getProf() {
		return prof;
	}

	public void setProf(User prof) {
		this.prof = prof;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setLectures(ArrayList<Lecture> lectures) {
		this.lectures = lectures;
	}

	public ArrayList<Lecture> getLectures() {
		return lectures;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public LinkedList<Announcement> getAnnouncements() {
		return announcements;
	}
	
}
