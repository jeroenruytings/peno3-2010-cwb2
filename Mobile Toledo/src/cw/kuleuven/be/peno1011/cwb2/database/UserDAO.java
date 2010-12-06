package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.User;


public class UserDAO {

	private static UserDAO userDAO;
	private Cryptography cryptography = Cryptography.getInstance();

	public static UserDAO getInstance() {
		if (userDAO == null){
			userDAO = new UserDAO();
		}
		return userDAO;
	}
	
	public User getUser(String username) throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/UserHandler/getUserById");
		method.addParameter("userId", username);
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		if(json.contains("[]")) {
			return new User(null,null,null,null,-1,null,null);
		}
		
		User[] user = new Gson().fromJson(json.toString(), User[].class);  
		
//		int start, end;
		
//		start = json.indexOf("userId")+9;
//		end = json.indexOf(",", start)-1;
//		String userId = json.substring(start,end);
//		start = json.indexOf("lastName")+11;
//		end = json.indexOf(",", start)-1;
//		String lastName = json.substring(start, end);
//		start = json.indexOf("firstName")+12;
//		end = json.indexOf(",", start)-1;
//		String firstName = json.substring(start, end);
//		start = json.indexOf("password")+11;
//		end = json.indexOf(",", start)-1;
//		String password = json.substring(start, end);
//		
//		User user = new User(userId,firstName,lastName,password,1,null,null);
		
		
		return user[0];
		
	}
}
