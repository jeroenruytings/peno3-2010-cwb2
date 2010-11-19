package cw.kuleuven.be.peno1011.cwb2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.ISP;
import cw.kuleuven.be.peno1011.cwb2.model.User;



public class InfoController {
	private User user = MainController.getUser();
	
	public Set<Announcement> allAnnouncements(){ //allemaal
        ISP isp = user.getIsp();
		ArrayList<Course> courses = isp.getCourses();
        Set<Announcement> announcements = new TreeSet<Announcement>();
        for (Course course : courses){
        	announcements.addAll(course.getAnnouncements());
        }
        
        return announcements;
	}
	
	public Set<Announcement> courseAnnouncements(Course course){ //1vak
		Set<Announcement> announcements = new TreeSet<Announcement>();
		announcements.addAll(course.getAnnouncements());
		return announcements;
	}
	
	public Set<Announcement> recentAnnouncements(int maxDaysAgo){ // recente
		ISP isp = user.getIsp();
		ArrayList<Course> courses = isp.getCourses();
		Date currentDate = new Date();
		Date oldestDate = new Date();
		oldestDate.setDate(currentDate.getDay()-14);
		Set<Announcement> announcements = new TreeSet<Announcement>();
		for (Course course : courses){
			for(Announcement announcement : course.getAnnouncements()){
				if (announcement.getDate().compareTo(oldestDate)>0)
				{
					announcements.add(announcement);
				}
			}
        }
		return announcements;
	}
}
