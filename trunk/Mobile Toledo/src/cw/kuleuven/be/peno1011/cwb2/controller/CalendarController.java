package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.model.Agenda;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.Event;
import cw.kuleuven.be.peno1011.cwb2.model.ISP;

public class CalendarController {
	private Agenda agenda;
	
	
	
	/**
	 * @param agenda
	 */
	public CalendarController() {
		agenda = new Agenda();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 4);
		Date d1 = cal.getTime();
		cal.add(Calendar.DAY_OF_YEAR, 5);
		Date d2 = cal.getTime();
		cal.add(Calendar.DAY_OF_YEAR, 7);
		Date d3 = cal.getTime();
		Event e1 = new Event("Stelsels differentiaalvergelijkingen en fourierreeksen en ook blablaaaaaa","beschr",null,"college", d1,d1);
		  Event e2 = new Event("DubbelTD","descr",null,"party", d1,d1);
		  Event e4 = new Event("Dynamica","descr",null,"college", d2,d2);
		  Event e5 = new Event("Numerieke wiskunde","descr",null,"college", new Date(),new Date());
		  Event e6 = new Event("Night Of The Proms","descr",null,"culture", d3,d3);
		  Event e7 = new Event("Cantus","descr",null,"party", d3,d3);
		  List<Event> events = new ArrayList<Event>();
		  events.add(e1);
		  events.add(e2);
		  events.add(e4);events.add(e5);events.add(e6);events.add(e7);
		  agenda.setEvents(events);
		//TODO/DAO haalt de juiste (!) events uit db en steekt ze in de agenda
	}
	
	public List<Event> getAllEvents(){
		
		return agenda.getEvents();
	}
	
	public List<Event> getEvents(int numberOfDays){ // recente
		try {
			Event.updateEvents();
		} catch (HttpException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		LinkedHashSet<Event> allEvents = Event.getEvents();
		//List<Event> allEvents = agenda.getEvents();
        List<Event> events = new ArrayList<Event>();
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
        Date maxDate = cal.getTime();
        
        Iterator<Event> it = allEvents.iterator();
        while(it.hasNext()){
        	Event currentEvent = it.next();
        	if(currentEvent.getStartDate().compareTo(currentDate)>0 || currentEvent.getStopDate().compareTo(maxDate)<0){
        		events.add(currentEvent);
        	}
        }
        
        
//        for(int i=0;i<allEvents.size();i++){
//        	if(allEvents.get(i).getStartDate().compareTo(currentDate)>0 || allEvents.get(i).getStopDate().compareTo(maxDate)<0){
//        		events.add(allEvents.get(i));
//        	}
//        }
		//eventueel sort events?
		return events; 
	}
	
	public List<Event> getCategoryEvents(int numberOfDays,String category){
		List<Event> events = getEvents(numberOfDays);
		List<Event> categoryEvents = new ArrayList<Event>();
		for(int i=0;i<events.size();i++){
		  		if(events.get(i).getCategory().equals(category)){
		  			categoryEvents.add(events.get(i));
		  		}
		  	}
		return categoryEvents;
	}


	public void updateCalendar(){ //android kalender updaten, dmv events in agenda
		List<Event> events = agenda.getEvents();
		//TODO/voor elke event de gegevens in de android kalender steken -> dmv ical object?
		//alternatief: haal google calendargegevens op
	}
}
