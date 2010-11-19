package cw.kuleuven.be.peno1011.cwb2.controller;

import java.util.ArrayList;

import cw.kuleuven.be.peno1011.cwb2.model.Agenda;
import cw.kuleuven.be.peno1011.cwb2.model.Event;

public class CalendarController {
	private Agenda agenda;
	
	
	
	/**
	 * @param agenda
	 */
	public CalendarController() {
		agenda = new Agenda();
		//TODO/DAO haalt events uit db en steekt ze in de agenda
	}



	public void updateCalendar(){ //android kalender updaten, dmv events in agenda
		ArrayList<Event> events = agenda.getEvents();
		//TODO/voor elke event gegevens in de android kalender steken
	}
}
