package cw.kuleuven.be.peno1011.cwb2.database;


import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;


import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.Event;

public class EventDAO {
	
	private static EventDAO eventDAO ;
	private static Cryptography cryptography;

	public static EventDAO getInstance() {
		if (eventDAO == null){
			eventDAO = new EventDAO();
		}
		return eventDAO;
	}
	
	public static Event[] getEvents() throws HttpException, IOException{
		HttpClient client = new HttpClient();
		
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/EventHandler/listEvents");
		int returnCode = client.executeMethod(method);
		String json = cryptography.decrypt(method.getResponseBodyAsString());
		
		Event[] events = new Gson().fromJson(json.toString(), Event[].class);  

		return events;
	}
}
