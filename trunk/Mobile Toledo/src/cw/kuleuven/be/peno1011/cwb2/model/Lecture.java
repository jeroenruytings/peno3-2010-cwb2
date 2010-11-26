package cw.kuleuven.be.peno1011.cwb2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Lecture extends Event implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Room room;
	private Course course;
	private MultipleChoice multipleChoice;
	private ArrayList <Question> questions = new ArrayList<Question>();
	
	
	public Lecture(Room room, Course course, Date startDate, String category, GPSLocation place, Date stopDate) {
		super(category, place, category, startDate, stopDate);
		setRoom(room);
		setCourse(course);
	}

	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public MultipleChoice getMultipleChoice() {
		return multipleChoice;
	}


	public void setMultipleChoice(MultipleChoice multipleChoice) {
		this.multipleChoice = multipleChoice;
	}


	public ArrayList<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	
}
