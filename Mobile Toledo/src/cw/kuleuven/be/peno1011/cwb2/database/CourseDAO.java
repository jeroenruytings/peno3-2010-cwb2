package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.ISP;

public class CourseDAO {
    
    private static CourseDAO courseDAO;
    private Cryptography cryptography = Cryptography.getInstance();
    
    // Singleton
    private CourseDAO(){ }
    
    public static CourseDAO getInstance() {
            if (courseDAO == null){
                    courseDAO = new CourseDAO();
            }
            return courseDAO;
    }
    
    
    
    public ArrayList<Course> getCourseByUserId(String userId) throws HttpException, IOException{
		ArrayList<Course> courses = new ArrayList<Course>();		
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/CourseHandler/getCourseByUserId");
		method.addParameter("userId", userId);
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		if(json.contains("[]")) {
			courses = null;
		}
		else {
			Course[] courseArray = new Gson().fromJson(json.toString(), Course[].class);
			
			int endId = 0;
			int startId = 0;
			int counter = 0;
			String relationalName = "courseCode";
			
			while(counter < courseArray.length){
				startId = json.indexOf(relationalName,endId)+ relationalName.length() + 3;
				endId = json.indexOf("}", startId)-1;
				String courseCode = json.substring(startId,endId);
						
				PostMethod methodRelational = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/CourseHandler/getCourseByCourseCode");
				methodRelational.addParameter(relationalName,courseCode);
				int returnCode = client.executeMethod(methodRelational);
				String jsonRelational = cryptography.decrypt(methodRelational.getResponseBodyAsString());
				Course[] relationalCourse = new Gson().fromJson(jsonRelational.toString(), Course[].class);
				if(jsonRelational.contains("[]")) {
					relationalCourse[0] = null;
				}
				else {
					courses.add(relationalCourse[0]);
					counter++;
				}
			}			
		}
		return courses;
	}

}
