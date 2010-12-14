package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.database.EventDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.Event;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.User;

public class CalendarController {	
	
	/**
	 * @param agenda
	 */
	public CalendarController() {
//		agenda = new Agenda();
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.HOUR, 4);
//		Date d1 = cal.getTime();
//		cal.add(Calendar.DAY_OF_YEAR, 5);
//		Date d2 = cal.getTime();
//		cal.add(Calendar.DAY_OF_YEAR, 7);
//		Date d3 = cal.getTime();
//		Event e1 = new Event("Stelsels differentiaalvergelijkingen en fourierreeksen en ook blablaaaaaa","beschr",null,"college", d1,d1);
//		  Event e2 = new Event("DubbelTD","descr",null,"party", d1,d1);
//		  Event e4 = new Event("Dynamica","descr",null,"college", d2,d2);
//		  Event e5 = new Event("Numerieke wiskunde","descr",null,"college", new Date(),new Date());
//		  Event e6 = new Event("Night Of The Proms","descr",null,"culture", d3,d3);
//		  Event e7 = new Event("Cantus","descr",null,"party", d3,d3);
//		  List<Event> events = new ArrayList<Event>();
//		  events.add(e1);
//		  events.add(e2);
//		  events.add(e4);events.add(e5);events.add(e6);events.add(e7);
//		  agenda.setEvents(events);
		//TODO/DAO haalt de juiste (!) events uit db en steekt ze in de agenda
	}

	
	public List<Event> getEvents(int numberOfDays) throws HttpException, IOException{ // recente
		
//		Event[] eventsFromDAO = EventDAO.getInstance().getEvents();
//		
//		//LinkedHashSet<Event> allEvents = Event.getEvents();
//		//List<Event> allEvents = agenda.getEvents();
//        ArrayList<Event> allEvents = new ArrayList<Event>();
//
//		for(int i = 0; i < eventsFromDAO.length; i++){
//			allEvents.add(eventsFromDAO[i]);
//		}
//			
//		ArrayList<Event> events = new ArrayList<Event>();
//		
//        //List<Event> events = new ArrayList<Event>();
//        Date currentDate = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(currentDate);
//        cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
//        Date maxDate = cal.getTime();        
//        
//        for(int i=allEvents.size()-1;i>-1;i--){
//        	if(allEvents.get(i).getStartDate().compareTo(currentDate)>0 && allEvents.get(i).getStartDate().compareTo(maxDate)<0){
//        		events.add(allEvents.get(i));
//        	}
//        }
//		//eventueel sort events?
//		return events; 
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		events.addAll(getLectures(numberOfDays));
		events.addAll(getCategoryEvents(numberOfDays, "party", "culture"));
		
		return events;
	}

	private List<Event> getLectures(int numberOfDays) {
		
		ArrayList<Event> lectures = new ArrayList<Event>();
		
		Date startDate = new Date();
		Date stopDate = startDate;
		stopDate.setDate(stopDate.getDate()+numberOfDays+1);
		stopDate.setHours(0);
		stopDate.setMinutes(0);
		stopDate.setSeconds(0);
		
		User user = MainController.getInstance().getUser();
		Iterator<Course> it = user.getIsp().getCourses().iterator();
		while (it.hasNext()){
			Course course = it.next();
			Iterator<Lecture> iter = course.getLectures().iterator();
			while(iter.hasNext()){
				Lecture lecture = iter.next();
				if(lecture.getStartDate().compareTo(startDate)>0 && lecture.getStartDate().compareTo(stopDate)<0){
					lectures.add(lecture);
				}
				else if(lecture.getStopDate().compareTo(startDate)>0 && lecture.getStopDate().compareTo(stopDate)<0){
					lectures.add(lecture);
				}
			}
		}
		
		return lectures;
	}
	
	public List<Event> getCategoryEvents(int numberOfDays,String categorie1, String categorie2) throws HttpException, IOException{
//		List<Event> events = getEvents(numberOfDays);
//		List<Event> categoryEvents = new ArrayList<Event>();
//		for(int i=0;i<events.size();i++){
//		  		if(events.get(i).getCategorie().equals(category)){
//		  			categoryEvents.add(events.get(i));
//		  		}
//		  	}
//		return categoryEvents;
		Date startDate = new Date();
		Date stopDate = startDate;
		stopDate.setDate(stopDate.getDate()+numberOfDays+1);
		stopDate.setHours(0);
		stopDate.setMinutes(0);
		stopDate.setSeconds(0);
		ArrayList<Event> events = EventDAO.getInstance().getEventsByCategoriesAndDate(startDate, stopDate, categorie1, categorie2);
		return events;
	}
	
	public List<Event> getCategoryEvents(int numberOfDays, String categorie) throws HttpException, IOException{
//		List<Event> events = getEvents(numberOfDays);
//		List<Event> categoryEvents = new ArrayList<Event>();
//		for(int i=0;i<events.size();i++){
//		  		if(events.get(i).getCategorie().equals(category)){
//		  			categoryEvents.add(events.get(i));
//		  		}
//		  	}
//		return categoryEvents;
		Date startDate = new Date();
		Date stopDate = startDate;
		stopDate.setDate(stopDate.getDate()+numberOfDays+1);
		stopDate.setHours(0);
		stopDate.setMinutes(0);
		stopDate.setSeconds(0);
		ArrayList<Event> events = EventDAO.getInstance().getEventsByCategoriesAndDate(startDate, stopDate, categorie);
		return events;
	}


}
