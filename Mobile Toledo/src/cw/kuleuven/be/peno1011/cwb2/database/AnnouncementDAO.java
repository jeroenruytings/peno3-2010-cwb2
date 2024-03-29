package cw.kuleuven.be.peno1011.cwb2.database;

import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class AnnouncementDAO {
        
        private static AnnouncementDAO announcementDAO;
    	private Cryptography cryptography = Cryptography.getInstance();
        
        // Singleton
        private AnnouncementDAO(){
                
        }
        
       
        
        public static AnnouncementDAO getInstance() {
                if (announcementDAO == null){
                        announcementDAO = new AnnouncementDAO();
                }
                return announcementDAO;
        }
        
        public void add(String userId, String message, String date, String title, String courseCode) throws IOException {
               
                HttpClient client = new HttpClient();
                // maak nieuwe postmethode waarbij de posts op het domein meegegeven als parameter worden geplaatst
                PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/AnnouncementHandler/addAnnouncement");
                
                method.addParameter("userId", userId);
                method.addParameter("message", message);
                method.addParameter("date", date);
                method.addParameter("title", title);
                method.addParameter("courseCode", courseCode);
                // voert de methode ook werkelijk uit en retourneert een  getal
                int returnCode = client.executeMethod(method);
                String encryptedJson = method.getResponseBodyAsString();
        }
        
        public boolean delete(Announcement announcement) {
                boolean deleted=false;
                return deleted;
        }
        
        
    public static String stringOfUrl(String addr) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URL url = new URL(addr);
        IOUtils.copy(url.openStream(), output);
        return output.toString();
    }
    
    public static String streamToString(InputStream stream) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(stream, output);
        return output.toString();
    }

	public List<Announcement> getAnnouncements(Course course) {
		HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/AnnouncementHandler/getAnnouncementByCourseCode");
        
        method.addParameter("courseCode", course.getCourseCode());
        String json = null;
		try {
			int returnCode = client.executeMethod(method);
			String encryptedJson = method.getResponseBodyAsString();
			json = Cryptography.getInstance().decrypt(encryptedJson);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Announcement[] announcements = new Gson().fromJson(json.toString(), Announcement[].class);  
        
        List<Announcement> announcementsToReturn = new ArrayList<Announcement>();
        
        for(int i = 0; i<announcements.length; i++){
        	Announcement currentAnnouncement = announcements[i];
        	currentAnnouncement.setCourse(course);
        	announcementsToReturn.add(currentAnnouncement);
        }
        
        return announcementsToReturn;
        
	}



	public List<Announcement> getRecentAnnouncements(Course course, int numberofdays){
			HttpClient client = new HttpClient();
	        PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/AnnouncementHandler/getAnnouncementByStartDateAndCourseCode");
	        
	        Date date = new Date();
	        date.setDate(date.getDate()-numberofdays);
	        
	        method.addParameter("courseCode", course.getCourseCode());
	        method.addParameter("date",cryptography.toMysqlDate(date));
//	        Calendar calendar = Calendar.getInstance();
//	        calendar.setTime(date);
//	        String sqlDate = "" + calendar.YEAR + calendar.MONTH + calendar.DAY_OF_MONTH
//	        	+ calendar.HOUR_OF_DAY + calendar.MINUTE + calendar.SECOND;
//	        
//	        String maand = null, dag = null, uren = null, minuten = null, seconden = null;
//	        int year = date.getYear()+1900;
//	        int month = date.getMonth()+1;
//	        maand = ""+month;
//	        if(month<10)
//	        	maand = "0"+month;
//	        int day = date.getDate();
//	        dag = ""+day;
//	        if(day<10)
//	        	dag = "0"+day;
//	        int hours = date.getHours();
//	        uren = ""+hours;
//	        if(hours<10)
//	        	uren = "0"+hours;
//	        int minutes = date.getMinutes();
//	        minuten = ""+minutes;
//	        if(minutes<10)
//	        	minuten = "0"+minutes;
//	        int seconds = date.getSeconds();
//	        seconden = ""+seconds;
//	        if(seconds<10)
//	        	seconden = "0"+seconds;
//			String sqlDate = "" + year + maand + dag + uren + minuten + seconden; 
//	        method.addParameter("date", sqlDate);
	        
	        String json = null;
			try {
				int returnCode = client.executeMethod(method);
				String encryptedJson = method.getResponseBodyAsString();
				json = Cryptography.getInstance().decrypt(encryptedJson);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Announcement[] announcements = new Gson().fromJson(json.toString(), Announcement[].class);  
	        
	        List<Announcement> announcementsToReturn = new ArrayList<Announcement>();
	        
	        for(int i = 0; i<announcements.length; i++){
	        	Announcement currentAnnouncement = announcements[i];
	        	currentAnnouncement.setCourse(course);
	        	announcementsToReturn.add(currentAnnouncement);
	        }
	        
	        return announcementsToReturn;
	        
	}
    
}
