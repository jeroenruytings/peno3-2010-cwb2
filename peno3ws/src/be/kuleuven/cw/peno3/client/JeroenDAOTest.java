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
		testAddUser();
		//testListAppreciations();
		//testGetAppreciation();
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
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/UserHandler/addUser");
			method.addParameter("userId","s0987654");
			method.addParameter("firstName","Marco");
			method.addParameter("lastName","Pantani");
			method.addParameter("password","epo");
			method.addParameter("rank","5");
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2010, 11, 29, 22, 14);
			Date date = calendar.getTime();
			String dateString = cryptography.toMysqlDate(date);
			method.addParameter("birthDate", dateString);
			
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
	
	public static void testAddAppreciation(){
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AppreciationHandler/addAppreciation");
			method.addParameter("docQuestionId","123");
			method.addParameter("isDocument", "1");
			method.addParameter("userId","s01231");
			method.addParameter("score","1");
			
					//appreciationId,docQuestionId,isDocument,userId,score
			
			
			int response = client.executeMethod(method);
			System.out.println(method.getResponseBodyAsString());
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
	
	public static void testListAppreciations(){
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AppreciationHandler/listAppreciations");
			
			int response = client.executeMethod(method);
			System.out.println(method.getResponseBodyAsString());
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
	
	public static void testGetAppreciation(){
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AppreciationHandler/getAppreciation");
			
			method.addParameter("docQuestionId", "123");
			method.addParameter("isDocument","1");
			
			int response = client.executeMethod(method);
			System.out.println(method.getResponseBodyAsString());
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
