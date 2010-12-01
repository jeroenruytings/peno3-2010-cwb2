package be.kuleuven.cw.peno3.client;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

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
	
	private static Cryptography cryptography = Cryptography.getInstance();
	
	public static void main(String[] args) {
//		testAddAnnouncements();
//		testListAnnouncements();
//		testGetAnnouncement();
		testAddCourse();
	}
	
	private static void testAddAnnouncements() {
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/addAnnouncement");
			method.addParameter("message", "Dit is de postmethod test");
			method.addParameter("userId","s0215121");
			method.addParameter("title", "De eerste postmethode werkt!");
			method.addParameter("courseCode", "Randomcode");
			//volgende regels zorgen voor toevoegen van de date vertrekkende van een dateobject
			Calendar calendar = Calendar.getInstance();
			calendar.set(2010, 11, 29, 22, 14);
			Date date = calendar.getTime();
			String dateString = cryptography.toMysqlDate(date);
			method.addParameter("date", dateString);
			
			int returnCode = client.executeMethod(method);

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
	
	public static void testGetAnnouncement() {
		try {
			HttpClient client = new HttpClient();
			
			GetMethod method = new GetMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/getAnnouncementByWord");
			method.setQueryString("?date=test");
			int returnCode = client.executeMethod(method);
			String json = method.getResponseBodyAsString();
			if(json.contains("[]")) {
					System.out.println("Geen zoekresultaten gevonden");
				}
			else {
				System.out.println(json);
				Announcement[] obj2 = new Gson().fromJson(json.toString(), Announcement[].class);  
				for (Announcement announcement : obj2) {
					
					System.out.println("Message = "+announcement.getMessage());	
					System.out.println("Title = "+announcement.getTitle());	
					System.out.println("UserId = "+ announcement.getUserId());	
					System.out.println("Coursecode = "+announcement.getCourseCode());	
					System.out.println("AnnouncementId = " + announcement.getAnnouncementId());	
				}
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

	private static void testAddCourse() {
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/CourseHandler/addCourse");
			method.addParameter("courseCode", "H44444");
			method.addParameter("academicYear","1112");
			method.addParameter("course", "Akoestische golven");
			int returnCode = client.executeMethod(method);

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
    
    /*
     * Onderstaande methodes zetten date-objecten om in sql compatibele strings
     */

}
