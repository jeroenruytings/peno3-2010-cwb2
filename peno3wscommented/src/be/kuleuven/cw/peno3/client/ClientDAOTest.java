package be.kuleuven.cw.peno3.client;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;

import be.kuleuven.cw.peno3.model.Credential;
import be.kuleuven.cw.peno3.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;


public class ClientDAOTest {
	public static void main(String[] args) {
		testListUsers();
		testaddUserJson();
	}
	
	// methode gaat:
	//1) een object omzetten naar zijn overeenkomstige JSON String
	//2) deze JSON String in een postmethode plaatsen en deze uitvoeren
	private static void testaddUserJson() {
		try {
			User newUser = new User();
			newUser.setFirstName("Jelle");
			newUser.setLastName("Vloeibergs");	
			
			Credential cred = new Credential();			
			cred.setPassword("mysecret");
			cred.setUsername("jel");
			newUser.setCredential(cred);
			
			// maak van het object User een String volgens de JSON standaard
			String jsonuser = new Gson().toJson(newUser);
			
			HttpClient client = new HttpClient();
			// maak nieuwe postmethode waarbij de posts op het domein meegegeven als parameter worden geplaatst
			PostMethod method = new PostMethod("http://localhost:9876/UserHandler/addUser");
			method.addParameter("user", jsonuser);
			// voert de methode ook werkelijk uit en retourneert een onbekend getal
			int returnCode = client.executeMethod(method);
			// displayt de respone op de postmethode
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
	public static void testListUsers() {

		try {
			String json = stringOfUrl("http://localhost:9876/UserHandler/listUsers");

			User[] obj2 = new Gson().fromJson(json.toString(), User[].class);  
			for (User user : obj2) {
				System.out.println(user);	
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
    
}
