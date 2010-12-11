package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import android.content.Context;

import cw.kuleuven.be.peno1011.cwb2.database.BuildingDAO;
import cw.kuleuven.be.peno1011.cwb2.database.CourseDAO;
import cw.kuleuven.be.peno1011.cwb2.database.Cryptography;
import cw.kuleuven.be.peno1011.cwb2.database.IspDAO;
import cw.kuleuven.be.peno1011.cwb2.database.LectureDAO;
import cw.kuleuven.be.peno1011.cwb2.database.RoomDAO;
import cw.kuleuven.be.peno1011.cwb2.database.UserDAO;
import cw.kuleuven.be.peno1011.cwb2.database.local.LoginDbAdaptor;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.ISP;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.User;

public class LoginController {
	
	private static LoginController LoginController;

	private LoginController(){
	}
	
	public static LoginController getInstance(){
		if (LoginController == null) {
			LoginController = new LoginController();
		}
		return LoginController; 
	}
	
	public User getUser(String username) throws HttpException, IOException{
		
		return UserDAO.getInstance().getUser(username);
		
	}
	
	
	public void login(User user){
		String userId = user.getUserId();
		ISP isp;
		try {
			isp = IspDAO.getInstance().getIsp(userId);
			if(isp!=null) {
				ArrayList<Course> courses = CourseDAO.getInstance().getCourseByUserId(userId);
				isp.setCourses(courses);
				user.setIsp(isp);	
			}		
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainController.getInstance().setUser(user);
	}

	public void remember(String username, String password, Context context) {
		LoginDbAdaptor.getInstance(context).addUser(username, password);
	}
	
	public String getUsername(Context context){
		return LoginDbAdaptor.getInstance(context).getUsername(1);
	}
	
	public String getPassword(Context context){
		return LoginDbAdaptor.getInstance(context).getPassword(1);
	}
}
