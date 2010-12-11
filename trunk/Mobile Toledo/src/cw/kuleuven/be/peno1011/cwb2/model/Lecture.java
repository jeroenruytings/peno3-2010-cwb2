package cw.kuleuven.be.peno1011.cwb2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Lecture extends Event implements Serializable{
	
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private Room room;
	private ArrayList <MultipleChoice> multipleChoice;
	private ArrayList <Question> questions = new ArrayList<Question>();
	
	
	public Lecture(Room room, Course course, Date startDate, String category, Date stopDate) {
		super(course.getCourseName(), category, category, startDate, stopDate, room.getBuilding(), room);
		setRoom(room);
	}
	
	private Lecture(){super();}

	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}

	public ArrayList<MultipleChoice> getMultipleChoice() {
		return multipleChoice;
	}


	public void setMultipleChoice(ArrayList<MultipleChoice> multipleChoice) {
		this.multipleChoice = multipleChoice;
	}


	public ArrayList<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	
}
