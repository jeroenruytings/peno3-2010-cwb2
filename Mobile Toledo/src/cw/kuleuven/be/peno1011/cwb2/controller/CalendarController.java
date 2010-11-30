package cw.kuleuven.be.peno1011.cwb2.controller;

import java.util.ArrayList;
import java.util.List;

import cw.kuleuven.be.peno1011.cwb2.model.Agenda;
import cw.kuleuven.be.peno1011.cwb2.model.Event;

public class CalendarController {
	private Agenda agenda;
	
	
	
	/**
	 * @param agenda
	 */
	public CalendarController() {
		agenda = new Agenda();
		//TODO/DAO haalt de juiste (!) events uit db en steekt ze in de agenda
	}
	
	public List<Event> getEvents(String category){
		
		return agenda.getEvents();
	}
	
	public List<Event> getCategoryEvents(String category){
		List<Event> events = agenda.getEvents();
		List<Event> categoryEvents = new ArrayList<Event>();
		for(int i=0;i<events.size();i++){
		  		if(events.get(i).getCategory().equals(category)){
		  			categoryEvents.add(events.get(i));
		  		}
		  	}
		return categoryEvents;
	}


	public void updateCalendar(){ //android kalender updaten, dmv events in agenda
		ArrayList<Event> events = agenda.getEvents();
		//TODO/voor elke event de gegevens in de android kalender steken -> dmv ical object?
		//alternatief: haal google calendargegevens op
	}
}
