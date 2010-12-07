package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

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
    
    public ArrayList<Course> getCourses(String username) throws HttpException, IOException{
		ArrayList<Course> courses = new ArrayList<Course>();		
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/IspHandler/getIspByUserId");
		method.addParameter("userId", username);
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		if(json.contains("[]")) {
			courses = null;
		}
		else {
			Course[] courseArray = new Gson().fromJson(json.toString(), Course[].class);
			courses = (ArrayList<Course>) Arrays.asList(courseArray);
		}
		return courses;
	}

}
