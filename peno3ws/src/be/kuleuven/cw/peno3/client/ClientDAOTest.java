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
import be.kuleuven.cw.peno3.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class ClientDAOTest {
	
	private static Cryptography cryptography = Cryptography.getInstance();
	
	public static void main(String[] args) {
//		testAddAnnouncements();
//		testGetAppreciation();
//		testAddAppreciation();
//		testListBuildingNames();
//		testGetDocumentByWord();
//		testGetAnnouncementByStartDate();
//		testAddMap();
//		testListAnnouncements();
//		testListUsers();
//		testGetLectureByDate();
//		testDate();
		testGetAnnouncementByStartDateAndCourseCode();
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
	private static void testGet(PostMethod postMethod) {
		try {
			HttpClient client = new HttpClient();
			int returnCode = client.executeMethod(postMethod);
			String json = cryptography.decrypt(postMethod.getResponseBodyAsString());
			if(json.contains(cryptography.encrypt("[]"))) {
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
	private static void testList(PostMethod postMethod) {
		try {
			HttpClient client = new HttpClient();
			int response = client.executeMethod(postMethod);
			System.out.println(cryptography.decrypt(postMethod.getResponseBodyAsString()));
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
	
	private static void testAdd(PostMethod postMethod) {
		try {
			HttpClient client = new HttpClient();
			
			
			int returnCode = client.executeMethod(postMethod);

			System.out.println(cryptography.decrypt(postMethod.getResponseBodyAsString()));
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
	
	public static void testGetAnnouncementByStartDateAndCourseCode() {
		PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/getAnnouncementByStartDateAndCourseCode");
		method.addParameter("courseCode", "H04125a");
		method.addParameter("date","20101211173000");
		testGet(method);
	}
	
	public static void testGetAnnouncementByWord() {
		PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/getAnnouncementByWord");
		method.addParameter("word","test");
		testGet(method);
	}
	
	public static void testGetAnnouncementByCourseCode() {
		PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/getAnnouncementByCourseCode");
		method.addParameter("courseCode","Rand");
		testGet(method);
	}
	
	public static void testGetAnnouncementByStartDate() {
		PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/getAnnouncementByStartDate");
		method.addParameter("date","20101229");
		testGet(method);
	}

	public static void testListBuildingNames() {
		PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/RoomHandler/listRoomNames");
		testList(method);
	}
	
	public static void testAddAppreciation() {
		PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AppreciationHandler/addAppreciation");
		method.addParameter("docQuestionId", "1234");
		method.addParameter("isDocument","1");
		method.addParameter("userId","s0215121");
		method.addParameter("score","5");
		testAdd(method);
	}
	
	public static void testGetAppreciation() {
		PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/CourseHandler/initializeCourses");
		method.addParameter("userId", "s0199104");
		testGet(method);
	}
	
	public static void testGetDocumentByWord() {
		PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/DocumentHandler/getDocumentByWord");
		method.addParameter("word","link");
		testGet(method);
	}
	
	private static void testAddMap() {
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/BuildingHandler/addMap");
			method.addParameter("locationId", "1");
			method.addParameter("map","kuleuven.belinkkomterhier");
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
	
	public static void testListAnnouncements(){
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/AnnouncementHandler/listAnnouncements");
			
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
	public static void testListUsers(){
		try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/UserHandler/listUsers");
			
			int response = client.executeMethod(method);
			String json = method.getResponseBodyAsString();
			System.out.println(json);
			User[] obj2 = new Gson().fromJson(json.toString(), User[].class);  
			for (User user : obj2) {
				System.out.println(user);
				if(user.getIsp()==null) {
					
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
    
    private static void testGetLectureByDate() {
    	try {
			HttpClient client = new HttpClient();
			
			PostMethod method = new PostMethod("http://" + ipAdress.getIp() + "/CourseHandler/getCourseByDateAndCourseCode");
			method.addParameter("courseCode", "H44444");
			method.addParameter("userId", "s0215121");
			method.addParameter("date", "20101215170700");
			int returnCode = client.executeMethod(method);

			System.out.println(cryptography.decrypt(method.getResponseBodyAsString()));
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
    
    private static void testDate() {
    	//volgende regels zorgen voor toevoegen van de date vertrekkende van een dateobject
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 29);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, 2010);
        Date date = calendar.getTime();
        String dateString = toMysqlDate(date);
        System.out.println(dateString);

    }
    
    private static String toMysqlDate(Date date){
        if (date==null) return "NULL";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
        return sqlValueWithQuotas(sdf.format(date));
       }

       public static String sqlValueWithQuotas(Object obj){
        if ( obj == null ) return "NULL";
        
        String str = obj.toString();
        str.replaceAll("'", "\\'");
        str = '\''+str+'\'';
        
        return str;
        
       }


}
