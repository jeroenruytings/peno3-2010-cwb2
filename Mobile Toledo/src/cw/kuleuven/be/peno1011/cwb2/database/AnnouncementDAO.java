package cw.kuleuven.be.peno1011.cwb2.database;

import cw.kuleuven.be.peno1011.cwb2.model.Announcement;

public class AnnouncementDAO {
	
	private static AnnouncementDAO announcementDAO;
	
	// Singleton has a private constructor
	private AnnouncementDAO(){
		
	}
	
	public static AnnouncementDAO getInstance() {
		if (announcementDAO == null){
			announcementDAO = new AnnouncementDAO();
		}
		return announcementDAO;
	}
	//TO DO
	public boolean insert(String title, String message) {
		//Announcement newAnnouncement = new Announcement(prof,course,title,message);
		boolean inserted=false;
		return inserted;
	}
	//TO DO
	public boolean delete(Announcement announcement) {
		boolean deleted=false;
		return deleted;
	}
}
