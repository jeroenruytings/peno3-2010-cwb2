package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Course {
	private String courseName;
	private String courseCode;
	private User prof;
	private String academicYear;
	private ArrayList<Lecture> lectures;
	private ArrayList<Document> documents;
	private LinkedList<Announcement> announcements;
	
	
	public Course(String courseName, String courseCode,
			User prof, String academicYear) {
		setProf(prof);
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.academicYear = academicYear;
	}
	
	private Course(){}

	public String getCourseName() {
		return courseName;
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
