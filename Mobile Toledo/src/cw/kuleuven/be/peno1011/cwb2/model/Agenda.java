package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.ArrayList;

public class Agenda {
	
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public Agenda(){
		
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}
}
