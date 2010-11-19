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
		//TODO/DAO haalt de juiste (!) events uit db en steekt ze in de agenda
	}



	public void updateCalendar(){ //android kalender updaten, dmv events in agenda
		ArrayList<Event> events = agenda.getEvents();
		//TODO/voor elke event de gegevens in de android kalender steken -> dmv ical object?
		//alternatief: haal google calendargegevens op
	}
}
