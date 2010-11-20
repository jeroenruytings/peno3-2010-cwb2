package cw.kuleuven.be.peno1011.cwb2.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
	
	public List<Announcement> recentAnnouncements(int maxDaysAgo){ // recente
		ISP isp = user.getIsp();
		ArrayList<Course> courses = isp.getCourses();
        List<Announcement> announcements = new LinkedList<Announcement>();
        for (Course course : courses){
        	announcements.addAll(course.getAnnouncements());
        }
		Date currentDate = new Date();
		Date oldestDate = new Date();
		oldestDate.setDate(currentDate.getDay()-14);
		int size = announcements.size();
		for(int i=0;i<size;i++){
			if(announcements.get(i).getDate().compareTo(oldestDate)<0){
				announcements.remove(i);
			}
		}
		sortAnnouncements(announcements);
		return announcements; //postconditie: announcements zijn gesorteerd
	}
	public List<Announcement> sortAnnouncements(List<Announcement> announcements){
		int i=announcements.size()-1;
        boolean isSorted = false;
        while (i>0 && isSorted ==false)
        {
            Date first = ((announcements.get(i-1))).getDate();
            Date second = ((announcements.get(i))).getDate();
            int compare = first.compareTo(second);
            if(compare < 0)
            {
                Announcement a = announcements.get(i-1);
                announcements.set(i-1,announcements.get(i));
                announcements.set(i,a);
            }
            else
            {
            	isSorted = true;
            }
            i--;
        }
        return announcements;
	}
}
