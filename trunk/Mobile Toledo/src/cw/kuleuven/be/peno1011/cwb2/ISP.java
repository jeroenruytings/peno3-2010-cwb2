package cw.kuleuven.be.peno1011.cwb2;

import java.util.ArrayList;

public class ISP {
	private ArrayList<Course> courses;
	private String group;
	private String studies;
	private int phase;
	
	public ISP(ArrayList<Course> courses, String group, String studies,int phase) {
		this.courses = courses;
		this.group = group;
		this.studies = studies;
		this.phase = phase;
	}
	
}
