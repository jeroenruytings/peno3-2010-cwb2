package be.kuleuven.cw.peno3.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.google.gson.JsonParser;


import be.kuleuven.cw.peno3.model.User;
import be.kuleuven.cw.peno3.model.Event;

public class TestOnWeb {
	public static void main(String[] args) throws HttpException, IOException {
		//testGetUser();
		//testGetEvents();
		//testUser();
		//testBuilding();
		//testGetCourseByCourseCode();
		//testGetLectureByCourseCode();
		testGetAnnouncementByExactDate();
		//testListAnnouncements();
	}
	
	private static void testBuilding() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod methodBuilding = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/BuildingHandler/getBuildingById");
		methodBuilding.addParameter("locationId","2");
		int returnCode = client.executeMethod(methodBuilding);
		String jsonBuilding = cryptography.decrypt(methodBuilding.getResponseBodyAsString());
		System.out.println(jsonBuilding);
	}

	private static Cryptography cryptography = Cryptography.getInstance();

	private static void testGetCourseByCourseCode() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod methodBuilding = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/CourseHandler/getCourseByCourseCode");
		methodBuilding.addParameter("courseCode","H44444");
		int returnCode = client.executeMethod(methodBuilding);
		String jsonBuilding = cryptography.decrypt(methodBuilding.getResponseBodyAsString());
		System.out.println(jsonBuilding);
	}
	
	private static void testUser() throws HttpException, IOException{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/UserHandler/getUserById");
		method.addParameter("userId", "s0199104");
		//method.addParameter("name","jeroen");
		
		int response = client.executeMethod(method);
		String json = method.getResponseBodyAsString();
		System.out.println(cryptography.decrypt(json));
		System.out.println(json);
	}
	
	private static void testListAnnouncements() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/AnnouncementHandler/listAnnouncements");
		
		int response = client.executeMethod(method);
		String json = method.getResponseBodyAsString();
		System.out.println(cryptography.decrypt(json));
		System.out.println(json);
	}
	
	private static void testGetUser() throws HttpException, IOException{
		
		System.out.println(cryptography.decrypt("BEC92C4B21A174DCF20B30A5E84B75C52B87E1FF82E491FF62E2EE5AA11008B0FF68F879DFD46B326128C055750267BDF594F6029D1B74AC24D703FFB13B0ED9B1469A149A59353C80C958CCA8A110D491C1697F6CA505EC8DED8CDE99B0B8605D12FA68C4E5387FFAFEC9A429A962130965974AD64220E59280940A1AEEBFAFE13D6C6136FF468AEDEDA604958E6C36E04FE90C30233018AE47BE0C1C35CD9F79ED0D72AA231C56D1EF22150BFDBC64A4EDAA83F20250E5C5B7C14E8FFDAB8B1CC85E1A526FEB8B00453A3FAB0020AD"));
		
		
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/UserHandler/getUserByUserId");
		method.addParameter("userId", "s999999");
		
		int response = client.executeMethod(method);
		//String encryptedJson = method.getResponseBodyAsString();
		String json = method.getResponseBodyAsString();
		//System.out.println(encryptedJson);
		//String json = cryptography.decrypt(encryptedJson);
		System.out.println(json);
		
		String[] parsed = json.split(",");
		System.out.println(parsed[1]);
		
		
		int start;
		int end;
		start = json.indexOf("userId")+9;
		end = json.indexOf(",", start)-1;
		String userId = json.substring(start,end);
		System.out.println(userId);
		start = json.indexOf("lastName")+11;
		end = json.indexOf(",", start)-1;
		String lastName = json.substring(start, end);
		System.out.println(lastName);
		start = json.indexOf("firstName")+12;
		end = json.indexOf(",", start)-1;
		String firstName = json.substring(start, end);
		System.out.println(firstName);
		start = json.indexOf("password")+11;
		end = json.indexOf("}", start)-1;
		String password = json.substring(start, end);
		System.out.println(password);
		
	}
	
	public static void testGetEvents() throws HttpException, IOException{
		HttpClient client = new HttpClient();
		
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/EventHandler/listEvents");
		int returnCode = client.executeMethod(method);
		String json = cryptography.decrypt(method.getResponseBodyAsString());
		
		String encrypted = "BEC92C4B21A174DCF20B30A5E84B75C52B87E1FF82E491FF62E2EE5AA11008B0FF68F879DFD46B326128C055750267BDF594F6029D1B74AC24D703FFB13B0ED9B1469A149A59353C80C958CCA8A110D491C1697F6CA505EC8DED8CDE99B0B8605D12FA68C4E5387FFAFEC9A429A962130965974AD64220E59280940A1AEEBFAFE13D6C6136FF468AEDEDA604958E6C36E04FE90C30233018AE47BE0C1C35CD9F79ED0D72AA231C56D1EF22150BFDBC64A4EDAA83F20250E5C5B7C14E8FFDAB8B1CC85E1A526FEB8B00453A3FAB0020AD";
		json = cryptography.decrypt(encrypted);
		
		System.out.println(json);
		
		Event[] events = new Gson().fromJson(json.toString(), Event[].class);  

	}
	
	public static void testGetLectureByCourseCode() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/LectureHandler/getLectureByCourseCode");
		method.addParameter("courseCode","H44444");
		int returnCode = client.executeMethod(method);
		String json = cryptography.decrypt(method.getResponseBodyAsString());
		
		System.out.println(json);
	}
	
	private static void testGetAnnouncementByExactDate() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/AnnouncementHandler/getAnnouncementByExactDate");
		
		//volgende regels zorgen voor toevoegen van de date vertrekkende van een dateobject
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, 2011);
        Date date = calendar.getTime();
        String dateString = toMysqlDate(date);

		method.addParameter("date",dateString);
		int returnCode = client.executeMethod(method);
		String json = cryptography.decrypt(method.getResponseBodyAsString());
		
		System.out.println(json);
	}
	
	private static String toMysqlDate(Date date){
        if (date==null) return "NULL";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
       }
}
