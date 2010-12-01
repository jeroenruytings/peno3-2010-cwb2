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
	
	private static Cryptography cryptography = Cryptography.getInstance();
	
	public static void main(String[] args) {
		//testListAnnouncements();
		//testAddAnnouncements();
		//testGetAnnouncement();
		//testAddCourse();
		//testGetCourseByName();
		//testGetUser();
		//testAddUser();
	}
	
	public static void testGetUser(){
		try {
			HttpClient client = new HttpClient();
			
			GetMethod method = new GetMethod("http://" + ipAdress.getIp() + "/UserHandler/getUserByName");
			method.setQueryString("?name=Jeroen");
			int response = client.executeMethod(method);
			String encryptedJson = method.getResponseBodyAsString();
			String json = cryptography.decrypt(encryptedJson);
			System.out.println(json);
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
	
	public static void testAddUser(){
//		try {
//			HttpClient client = new HttpClient();
//			
//			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/UserHandler/addUser");
//			method.addParameter("userId","s0199105");
//			method.addParameter("firstName","Jan");
//			method.addParameter("lastName","Janssens");
//			method.addParameter("birthDate", )
//			
//			int response = client.executeMethod(method);
//			String encryptedJson = method.getResponseBodyAsString();
//			String json = cryptography.decrypt(encryptedJson);
//			System.out.println(json);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
