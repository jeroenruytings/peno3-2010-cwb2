package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

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
	
}
