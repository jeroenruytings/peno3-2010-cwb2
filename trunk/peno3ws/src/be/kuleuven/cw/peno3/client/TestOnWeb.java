package be.kuleuven.cw.peno3.client;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import be.kuleuven.cw.peno3.model.User;

public class TestOnWeb {
	public static void main(String[] args) throws HttpException, IOException {
		testGetUser();
	}
	
	private static Cryptography cryptography = Cryptography.getInstance();

	
	private static void testGetUser() throws HttpException, IOException{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/UserHandler/getUserByUserId");
		method.addParameter("userId", "s999999");
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		System.out.println(encryptedJson);
		String json = cryptography.decrypt(encryptedJson);
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
		end = json.indexOf(",", start)-1;
		String password = json.substring(start, end);
		System.out.println(password);
		
	}
}
