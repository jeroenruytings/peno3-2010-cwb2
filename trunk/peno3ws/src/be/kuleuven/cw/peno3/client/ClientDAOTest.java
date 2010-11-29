package be.kuleuven.cw.peno3.client;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.BasicConfigurator;

import be.kuleuven.cw.peno3.model.Announcement;
import be.kuleuven.cw.peno3.model.Credential;
import be.kuleuven.cw.peno3.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class ClientDAOTest {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		//testListAnnouncements();
		testAddAnnouncements();
	}

	private static void testAddAnnouncements() {
		try {
			
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/addAnnouncement");
			method.addParameter("message", "Hoera! Hoera! Eerste databaserecord toegevoegd!");
			method.addParameter("userId", "s0215121");
//			method.addParameter("date", null);
			method.addParameter("title", "Joepie!");
			method.addParameter("courseCode", "1");
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

			Announcement[] obj2 = new Gson().fromJson(json.toString(), Announcement[].class);  
			for (Announcement announcement : obj2) {
				System.out.println(announcement);	
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
