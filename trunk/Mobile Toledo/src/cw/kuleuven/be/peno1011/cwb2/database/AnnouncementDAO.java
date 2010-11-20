package cw.kuleuven.be.peno1011.cwb2.database;

import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.User;

public class AnnouncementDAO {
	
	private static AnnouncementDAO announcementDAO;
	
	// Singleton
	private AnnouncementDAO(){
		
	}
	
	public static AnnouncementDAO getInstance() {
		if (announcementDAO == null){
			announcementDAO = new AnnouncementDAO();
		}
		return announcementDAO;
	}
	//TO DO: opslaan i/database
	public boolean insert(String title, String message, Course course) {
		User prof = MainController.getUser();
		Announcement newAnnouncement = new Announcement(prof,course,title,message);
		boolean inserted=false;
		return inserted;
	}
	//TO DO
	public boolean delete(Announcement announcement) {
		boolean deleted=false;
		return deleted;
	}
}
