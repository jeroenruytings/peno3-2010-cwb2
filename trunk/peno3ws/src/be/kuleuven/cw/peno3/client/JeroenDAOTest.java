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

import be.kuleuven.cw.peno3.model.Announcement;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class JeroenDAOTest {
	public static void main(String[] args) {
		//testListAnnouncements();
		//testAddAnnouncements();
		//testGetAnnouncement();
		//testAddCourse();
		//testGetCourseByName();
		testGetUser();
	}
	
	public static void testGetUser(){
		try {
			HttpClient client = new HttpClient();
			
			GetMethod method = new GetMethod("http://" + ipAdress.getIp() + "/UserHandler/getUserByName");
			method.setQueryString("?name=Jeroen");
			int response = client.executeMethod(method);
			String json = method.getResponseBodyAsString();
			if(json.contains("[]")) {
					System.out.println("Geen zoekresultaten gevonden");
				}
			else {
				System.out.println(json);
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
}
