package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
	
	private List<Event> events = new ArrayList<Event>();
	
	public Agenda(){
		
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Event> getEvents() {
		return events;
	}
}
