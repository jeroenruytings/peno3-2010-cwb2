package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.ISP;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;

public class LectureDAO {
	private static LectureDAO lectureDAO;
	private Cryptography cryptography = Cryptography.getInstance();
	
	private LectureDAO() { }
	
	public static LectureDAO getInstance() {
		if(lectureDAO==null){
			lectureDAO = new LectureDAO();
		}
		return lectureDAO;
	}
	
	public Lecture[] getLectureByDate(Date date, String courseCode) throws IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/LectureHandler/getLectureByDate");
		method.addParameter("date",cryptography.toMysqlDate(date));
		method.addParameter("courseCode", courseCode);
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		Lecture[] lectures;
		if(json.contains("[]")) {
			 lectures = null;
		}
		else {
			lectures = new Gson().fromJson(json.toString(), Lecture[].class);
		}
		return lectures;
	}
	
	
	public ArrayList<Course> initializeLectures(String userId) throws HttpException, IOException{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/LectureHandler/initializeLectures");
		method.addParameter("userId",userId);
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		Lecture[] lectures;
		ArrayList<Course> courses = new ArrayList<Course>();

		if(json.contains("[]")) {
			return null;
		}
		else {
			lectures = new Gson().fromJson(json.toString(), Lecture[].class);
			Course[] coursesDAO = new Gson().fromJson(json.toString(), Course[].class);
			
			for(int i = 0; i<coursesDAO.length; i++){
				if(!courses.contains(coursesDAO[i])){
					courses.add(coursesDAO[i]);
				}
			}
			
			for(int i = 0; i<lectures.length; i++){
				courses.get(courses.indexOf(coursesDAO[i])).getLectures().add(lectures[i]);
			}
		}
		return courses;
	}
}
