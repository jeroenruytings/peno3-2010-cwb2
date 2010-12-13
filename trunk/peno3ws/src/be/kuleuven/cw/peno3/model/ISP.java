package be.kuleuven.cw.peno3.model;

import java.util.ArrayList;

public class ISP {
	private ArrayList<Course> courses;
	private String group;
	private String studies;
	private int phase;
	
	public ISP(ArrayList<Course> courses, String group, String studies,int phase) {
		setCourses(courses);
		setGroup(group);
		setStudies(studies);
		setPhase(phase);
	}
	
	private ISP() { }

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStudies() {
		return studies;
	}

	public void setStudies(String studies) {
		this.studies = studies;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}
	
}
