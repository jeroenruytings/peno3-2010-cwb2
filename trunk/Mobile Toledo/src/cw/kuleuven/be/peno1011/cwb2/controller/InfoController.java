package cw.kuleuven.be.peno1011.cwb2.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cw.kuleuven.be.peno1011.cwb2.database.AnnouncementDAO;
import cw.kuleuven.be.peno1011.cwb2.database.Cryptography;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.ISP;
import cw.kuleuven.be.peno1011.cwb2.model.User;



public class InfoController {
	private static InfoController InfoController;
	private User user = MainController.getUser();
	
	private InfoController(){
	}
	
	public static InfoController getInstance(){
		if (InfoController == null) {
			InfoController = new InfoController();
		}
		return InfoController; 
	}
	
	public List<Announcement> allAnnouncements(){ //allemaal
        ISP isp = user.getIsp();
		ArrayList<Course> courses = isp.getCourses();
        List<Announcement> announcements = new LinkedList<Announcement>();
        for (Course course : courses){
        	announcements.addAll(course.getAnnouncements());
        }
        
        return announcements;
	}
	
	public List<Announcement> courseAnnouncements(Course course){ //1vak
		List<Announcement> announcements = new LinkedList<Announcement>();
		announcements.addAll(course.getAnnouncements());
		return announcements;
	}
	
	public List<Announcement> recentAnnouncements(int maxDaysAgo){ // recente
//		ISP isp = user.getIsp();
//		ArrayList<Course> courses = isp.getCourses();
//        List<Announcement> announcements = new LinkedList<Announcement>();
//        for (Course course : courses){
//        	announcements.addAll(course.getAnnouncements());
//        }
//		Date currentDate = new Date();
//		Date oldestDate = new Date();
//		oldestDate.setDate(currentDate.getDay()-14);
//		int size = announcements.size();
//		for(int i=0;i<size;i++){
//			if(announcements.get(i).getDate().compareTo(oldestDate)<0){
//				announcements.remove(i);
//			}
//		}
//		sortAnnouncements(announcements);
//		return announcements; //postconditie: announcements zijn gesorteerd
		Announcement a1 = new Announcement(null, null, "Test", "tesst");
		Announcement a2 = new Announcement(null, null, "Test", "Boodschap");
		Announcement a3 = new Announcement(null, null, "Test", "tesst");
		Announcement a4 = new Announcement(null, null, "Test", "tesst");
		Announcement a5 = new Announcement(null, null, "Test", "tesst");
		List<Announcement> anns = new ArrayList<Announcement>();
		anns.add(a1);anns.add(a2);anns.add(a3);anns.add(a4);anns.add(a5);
        return anns;
	}
//	public List<Announcement> sortAnnouncements(List<Announcement> announcements){
//		int i=announcements.size()-1;
//        boolean isSorted = false;
//        while (i>0 && isSorted ==false)
//        {
//            Date first = ((announcements.get(i-1))).getDate();
//            Date second = ((announcements.get(i))).getDate();
//            int compare = first.compareTo(second);
//            if(compare < 0)
//            {
//                Announcement a = announcements.get(i-1);
//                announcements.set(i-1,announcements.get(i));
//                announcements.set(i,a);
//            }
//            else
//            {
//            	isSorted = true;
//            }
//            i--;
//        }
//        return announcements;
//	}
	
	public Announcement findAnnouncement(List<Announcement> announcements, String title){
		Announcement announcement = null;
		for(int i = 0; i<announcements.size();i++){
			if(announcements.get(i).getTitle().equals(title)){
				announcement = announcements.get(i);
			}
		}
		return announcement;
	}
	
	public Course findCourse(String title){
		List<Course> courses = user.getIsp().getCourses();
		boolean isFound = false;
		Course course = null;
		for(int i=0;i<courses.size() && !isFound;i++){
			if(courses.get(i).equals(title)){
				isFound = true;
				course = courses.get(i);
			}
		}
		return course;
	}
	public String[] makeStrings(List<Announcement> announcements){
		String[] displayStrings = new String[announcements.size()];
		for(int i = 0;i< announcements.size();i++){
			try{
			String displayString = announcements.get(i).getCourse().getCourseName() + ": " + announcements.get(i).getTitle();
			displayStrings[announcements.size()-i-1] = displayString;
			}
			catch(NullPointerException ne){//course = null
				String displayString = "??" + ": " + announcements.get(i).getTitle();
				displayStrings[announcements.size()-i-1] = displayString;
			}
		}
		return displayStrings;
	}

	public void insert(String title, String message, Course course) {
		Date date = new Date();
		AnnouncementDAO.getInstance().add(MainController.getInstance().getUser().getUserId(), message, Cryptography.getInstance().toMysqlDate(date), title, course.getCourseCode());
	}
	
}
