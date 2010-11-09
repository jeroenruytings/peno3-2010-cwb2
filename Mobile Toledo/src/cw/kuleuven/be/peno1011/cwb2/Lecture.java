package cw.kuleuven.be.peno1011.cwb2;

import java.util.ArrayList;
import java.util.Date;

public class Lecture extends Event{
	
	private Room room;
	private Course course;
	private ArrayList <MultipleChoice> multipleChoices = new ArrayList<MultipleChoice>();
	private ArrayList <Question> questions = new ArrayList<Question>();
	
	
	public Lecture(Room room, Course course, Date startDate, String category, Location place, Date stopDate) {
		super(category, place, category, startDate, stopDate);
		this.room = room;
		this.course = course;
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


	public ArrayList<MultipleChoice> getMultipleChoices() {
		return multipleChoices;
	}


	public void setMultipleChoices(ArrayList<MultipleChoice> multipleChoices) {
		this.multipleChoices = multipleChoices;
	}


	public ArrayList<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	
}
