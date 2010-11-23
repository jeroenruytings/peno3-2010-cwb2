package cw.kuleuven.be.peno1011.cwb2.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;

import cw.kuleuven.be.peno1011.cwb2.database.local.LoginDbAdaptor;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.ISP;
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
	
	public User getUser(String username, String password){
		//Normaal uit DAO iemand, nu even fictief persoon
		Date currentDate = new Date(Calendar.DATE);
		ISP isp = new ISP(new ArrayList<Course>(),"1","1",1);
		return new User("1","Test","Persoon","wachtwoord",false,currentDate,isp);
	}
	
	public void login(User user){
		MainController.setUser(user);
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
