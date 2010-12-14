package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cw.kuleuven.be.peno1011.cwb2.database.AnnouncementDAO;
import cw.kuleuven.be.peno1011.cwb2.database.Cryptography;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.ISP;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.User;



public class InfoController {
	private static InfoController InfoController;
	private User user = MainController.getInstance().getUser();
	
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
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		
		Iterator<Course> it = courses.iterator();
		
		while(it.hasNext()){
			Course course = it.next();
			announcements.addAll(AnnouncementDAO.getInstance().getAnnouncements(course));
		}
		
		return announcements;
	}
	
	public List<Announcement> courseAnnouncements(Course course){ //1vak
		return AnnouncementDAO.getInstance().getAnnouncements(course);
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
//		Announcement a1 = new Announcement(null, null, "Test", "tesst");
//		Announcement a2 = new Announcement(null, null, "Test", "Boodschap");
//		Announcement a3 = new Announcement(null, null, "Test", "tesst");
//		Announcement a4 = new Announcement(null, null, "Test", "tesst");
//		Announcement a5 = new Announcement(null, null, "Test", "tesst");
//		List<Announcement> anns = new ArrayList<Announcement>();
//		anns.add(a1);anns.add(a2);anns.add(a3);anns.add(a4);anns.add(a5);
//        return anns
		
		ISP isp = user.getIsp();
		ArrayList<Course> courses = isp.getCourses();
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		
		Iterator<Course> it = courses.iterator();
		
		while(it.hasNext()){
			Course course = it.next();
			announcements.addAll(AnnouncementDAO.getInstance().getRecentAnnouncements(course, maxDaysAgo));
		}
		
		return announcements;
		
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
			String displayString = announcements.get(i).getTitle();
			displayStrings[announcements.size()-i-1] = displayString;

		}
		return displayStrings;
	}

	public void insert(String title, String message, Course course) throws IOException {
		Date date = new Date();
		AnnouncementDAO.getInstance().add(user.getUserId(), message, Cryptography.getInstance().toMysqlDate(date), title, course.getCourseCode());
	}
	
	public Lecture findLectureById(int id, String courseCode) {
		List<Course> courses=MainController.getInstance().getUser().getIsp().getCourses();
		Course course = null;
		boolean isFound = false;
		for(int i=0;i<courses.size() && !isFound;i++){
			if(courses.get(i).getCourseCode().equals(courseCode)){
				isFound = true;
				course=courses.get(i);
			}
		}
		Lecture lecture = null;
		if(course != null){
			boolean isFound2 = false;
			List<Lecture> lectures = course.getLectures();
			for(int i=0;i<lectures.size() && !isFound2;i++){
				if(lectures.get(i).getEventId()==(id)){
					isFound = true;
					lecture = lectures.get(i);
				}
			}
		}
		return lecture;
	}
	
}
