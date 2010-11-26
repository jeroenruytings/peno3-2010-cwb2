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
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

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
	
	public void add(String jsonannouncement) {
		try {
			HttpClient client = new HttpClient();
			// maak nieuwe postmethode waarbij de posts op het domein meegegeven als parameter worden geplaatst
			PostMethod method = new PostMethod("http://134.58.253.55:9876/AnnouncementHandler/addAnnouncement");
			method.addParameter("announcement", jsonannouncement);
			// voert de methode ook werkelijk uit en retourneert een onbekend getal
			int returnCode = client.executeMethod(method);
			// displayt de respone op de postmethode
			System.out.println(method.getResponseBodyAsString());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean delete(Announcement announcement) {
		boolean deleted=false;
		return deleted;
	}
	
	/**
	 * Methode moet nog uitgewerkt worden:
	 */
	public static void testListUsers() {

		try {
			String json = stringOfUrl("http://localhost:9876/UserHandler/listUsers");

			User[] obj2 = new Gson().fromJson(json.toString(), User[].class);  
			for (User user : obj2) {
				System.out.println(user);	
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
    
}
