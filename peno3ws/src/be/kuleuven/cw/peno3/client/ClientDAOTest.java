package be.kuleuven.cw.peno3.client;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.BasicConfigurator;

import be.kuleuven.cw.peno3.model.Announcement;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class ClientDAOTest {
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		testListAnnouncements();
		testAddAnnouncements();
	}

	private static void testAddAnnouncements() {
		try {
			
			HttpClient client = new HttpClient();
			
			GetMethod method = new GetMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/addAnnouncement");
			
			String message = "Test%20test";
			String userId = "s0199104";
			String title = "Test";
			String courseCode = "123";
			String qry = "?message="+message+"&userId="+userId+"&title="+title+"&courseCode="+courseCode;
			System.out.println(qry);
			//method.setQueryString("?message=Hopelijk werkt dit&userId=s0199104&title=test&courseCode=123");
			method.setQueryString(qry);
			
			//PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/addAnnouncement");
//			method.addParameter("message", "Hoera! Hoera! Eerste databaserecord toegevoegd!");
//			method.addParameter("userId", "s0215121");
////			method.addParameter("date", null);
//			method.addParameter("title", "Joepie!");
//			method.addParameter("courseCode", "1");
			int returnCode = client.executeMethod(method);
			System.out.println(returnCode);
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
	public static void testListAnnouncements() {

		try {
			String json = stringOfUrl("http://" + ipAdress.getIp() + "/AnnouncementHandler/listAnnouncements");
			System.out.println(json);
			Announcement[] obj2 = new Gson().fromJson(json.toString(), Announcement[].class);  
			for (Announcement announcement : obj2) {
				System.out.println("Message = "+announcement.getMessage());	
				System.out.println("Title = "+announcement.getTitle());	
				System.out.println("UserId = "+ announcement.getUserId());	
				System.out.println("Coursecode = "+announcement.getCourseCode());	
				System.out.println("AnnouncementId = " + announcement.getAnnouncementId());	
			
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
