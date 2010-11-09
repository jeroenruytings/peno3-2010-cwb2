package cw.kuleuven.be.peno1011.cwb2;

import java.util.ArrayList;

public class Course {
	
	private final String courseName;
	private final String courseCode;
	private User prof;
	private final String academicYear;
	private ArrayList<Lecture> lectures;
	private ArrayList<Document> documents;
	private ArrayList<Announcement> announcements;
	
	
	public Course(String courseName, String courseCode,
			User prof, String academicYear) {
		setProf(prof);
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.academicYear = academicYear;
	}

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

	public void setAnnouncements(ArrayList<Announcement> announcements) {
		this.announcements = announcements;
	}

	public ArrayList<Announcement> getAnnouncements() {
		return announcements;
	}
	
}
