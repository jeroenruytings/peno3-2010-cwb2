//
//		IS WAARSCHIJNLIJK NIET NODIG!!





//package cw.kuleuven.be.peno1011.cwb2.controller;
//
//	import java.util.ArrayList;
//	import java.util.Date;
//	import java.util.LinkedList;
//	import java.util.List;
//	import java.util.Set;
//	import java.util.TreeSet;
//
//import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
//import cw.kuleuven.be.peno1011.cwb2.model.Course;
//	import cw.kuleuven.be.peno1011.cwb2.model.Question;
//	import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
//	import cw.kuleuven.be.peno1011.cwb2.model.ISP;
//import cw.kuleuven.be.peno1011.cwb2.model.User;
//
//
//
//	public class QuestionsController {
//		
//			
//		public QuestionsController(){
//		}
//
//		public String[] makeStrings(List<Question> questions){
//			String[] displayStrings = new String[questons.size()];
//			for(int i = 0;i< lectures.size();i++){
//				try{
//				String displayString = questions.
//				String displayString = announcements.get(i).getCourse().getCourseName() + ": " + announcements.get(i).getTitle();
//				displayStrings[announcements.size()-i-1] = displayString;
//				}
//				catch(NullPointerException ne){//course = null
//					String displayString = "??" + ": " + announcements.get(i).getTitle();
//					displayStrings[announcements.size()-i-1] = displayString;
//				}
//			}
//			return displayStrings;
//		}
//		
//	}
//		
////		private User questioner = MainController.getUser();
////		
////		
////		
////		
////		public List<Question> allQuestions(){
////			Date currentDate = new Date();
////			ArrayList<Course> courses = isp.getCourses();
////				for (Course course : courses){
////					ArrayList<Lecure> lectures = Course.getLectures();
////				}
////				for (Lecture lecture: lectures){
////					boolean before = Lecture.getStartDate() before (currentDate);
////					boolean after = Lecture.getStopDate() after (currentDate);
////					if before == 1 && after ==1 {
////						
////					}
////				}
////			
////			
////			
////			
////			for (Lecture lecture : lectures){
////	        	announcements.addAll(course.getQuestions());
////	        }	
////			
////		}
////		
////		
////		public List<Questions> allQuestions(){ //alle vragen
////	        ISP isp = user.getIsp();
////			ArrayList<Course> courses = isp.getCourses();
////	        List<Questions> questions = new LinkedList<Questions>();
////	        	for (Course course : courses){
////	        	announcements.addAll(course.getQuestions());
////	        }
////	        
////	        return questions;
////		}
////		
////		public List<Announcement> courseAnnouncements(Course course){ //1vak
////			List<Announcement> announcements = new LinkedList<Announcement>();
////			announcements.addAll(course.getAnnouncements());
////			return announcements;
////		}
////		
////		public List<Announcement> recentAnnouncements(int maxDaysAgo){ // recente
////			ISP isp = user.getIsp();
////			ArrayList<Course> courses = isp.getCourses();
////	        List<Announcement> announcements = new LinkedList<Announcement>();
////	        for (Course course : courses){
////	        	announcements.addAll(course.getAnnouncements());
////	        }
////			Date currentDate = new Date();
////			Date oldestDate = new Date();
////			oldestDate.setDate(currentDate.getDay()-14);
////			int size = announcements.size();
////			for(int i=0;i<size;i++){
////				if(announcements.get(i).getDate().compareTo(oldestDate)<0){
////					announcements.remove(i);
////				}
////			}
////			sortAnnouncements(announcements);
////			return announcements; //postconditie: announcements zijn gesorteerd
////		}
////		public List<Announcement> sortAnnouncements(List<Announcement> announcements){
////			int i=announcements.size()-1;
////	        boolean isSorted = false;
////	        while (i>0 && isSorted ==false)
////	        {
////	            Date first = ((announcements.get(i-1))).getDate();
////	            Date second = ((announcements.get(i))).getDate();
////	            int compare = first.compareTo(second);
////	            if(compare < 0)
////	            {
////	                Announcement a = announcements.get(i-1);
////	                announcements.set(i-1,announcements.get(i));
////	                announcements.set(i,a);
////	            }
////	            else
////	            {
////	            	isSorted = true;
////	            }
////	            i--;
////	        }
////	        return announcements;
////		}
////	}
//
//
//	
//	
////	package cw.kuleuven.be.peno1011.cwb2.controller;
////
////	import java.util.ArrayList;
////	import java.util.Date;
////	import java.util.LinkedList;
////	import java.util.List;
////	import java.util.Set;
////	import java.util.TreeSet;
////
////	import cw.kuleuven.be.peno1011.cwb2.model.Question;
////	import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
////	import cw.kuleuven.be.peno1011.cwb2.model.ISP;
////	import cw.kuleuven.be.peno1011.cwb2.model.User;
////
////
////
////	public class Questions {
////		private User questioner = MainController.getUser();
////		
////		
////		
////		
////		public List<Questions> allQuestions(){
////			Date currentDate = new Date();
////			ArrayList<Course> courses = isp.getCourses();
////				for (Course course : courses){
////					ArrayList<Lecure> lectures = Course.getLectures();
////				}
////				for (Lecture lecture: lectures){
////					boolean before = Lecture.getStartDate() before (currentDate);
////					boolean after = Lecture.getStopDate() after (currentDate);
////					if before == 1 && after ==1 {
////						
////					}
////				}
////			
////			
////			
////			
////			for (Lecture lecture : lectures){
////	        	announcements.addAll(course.getQuestions());
////	        }	
////			
////		}
////		
////		
////		public List<Questions> allQuestions(){ //alle vragen
////	        ISP isp = user.getIsp();
////			ArrayList<Course> courses = isp.getCourses();
////	        List<Questions> questions = new LinkedList<Questions>();
////	        	for (Course course : courses){
////	        	announcements.addAll(course.getQuestions());
////	        }
////	        
////	        return questions;
////		}
////		
////		public List<Announcement> courseAnnouncements(Course course){ //1vak
////			List<Announcement> announcements = new LinkedList<Announcement>();
////			announcements.addAll(course.getAnnouncements());
////			return announcements;
////		}
////		
////		public List<Announcement> recentAnnouncements(int maxDaysAgo){ // recente
////			ISP isp = user.getIsp();
////			ArrayList<Course> courses = isp.getCourses();
////	        List<Announcement> announcements = new LinkedList<Announcement>();
////	        for (Course course : courses){
////	        	announcements.addAll(course.getAnnouncements());
////	        }
////			Date currentDate = new Date();
////			Date oldestDate = new Date();
////			oldestDate.setDate(currentDate.getDay()-14);
////			int size = announcements.size();
////			for(int i=0;i<size;i++){
////				if(announcements.get(i).getDate().compareTo(oldestDate)<0){
////					announcements.remove(i);
////				}
////			}
////			sortAnnouncements(announcements);
////			return announcements; //postconditie: announcements zijn gesorteerd
////		}
////		public List<Announcement> sortAnnouncements(List<Announcement> announcements){
////			int i=announcements.size()-1;
////	        boolean isSorted = false;
////	        while (i>0 && isSorted ==false)
////	        {
////	            Date first = ((announcements.get(i-1))).getDate();
////	            Date second = ((announcements.get(i))).getDate();
////	            int compare = first.compareTo(second);
////	            if(compare < 0)
////	            {
////	                Announcement a = announcements.get(i-1);
////	                announcements.set(i-1,announcements.get(i));
////	                announcements.set(i,a);
////	            }
////	            else
////	            {
////	            	isSorted = true;
////	            }
////	            i--;
////	        }
////	        return announcements;
////		}
////	}
