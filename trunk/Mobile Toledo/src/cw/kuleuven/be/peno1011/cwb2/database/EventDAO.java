package cw.kuleuven.be.peno1011.cwb2.database;


import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.Event;

public class EventDAO {
	
	private static EventDAO eventDAO ;
	private static Cryptography cryptography = Cryptography.getInstance();
	
	public static EventDAO getInstance() {
		if (eventDAO == null){
			eventDAO = new EventDAO();
		}
		return eventDAO;
	}
	
	public Event[] getEvents() throws HttpException, IOException{
		HttpClient client = new HttpClient();
		
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/EventHandler/listEvents");
		int returnCode = client.executeMethod(method);
		String json = cryptography.decrypt(method.getResponseBodyAsString());
		
		String encrypted = "BEC92C4B21A174DCF20B30A5E84B75C52B87E1FF82E491FF62E2EE5AA11008B0FF68F879DFD46B326128C055750267BDF594F6029D1B74AC24D703FFB13B0ED9B1469A149A59353C80C958CCA8A110D491C1697F6CA505EC8DED8CDE99B0B8605D12FA68C4E5387FFAFEC9A429A962130965974AD64220E59280940A1AEEBFAFE13D6C6136FF468AEDEDA604958E6C36E04FE90C30233018AE47BE0C1C35CD9F79ED0D72AA231C56D1EF22150BFDBC64A4EDAA83F20250E5C5B7C14E8FFDAB8B1CC85E1A526FEB8B00453A3FAB0020AD";
		json = cryptography.decrypt(encrypted);
		
		Event[] events = new Gson().fromJson(json.toString(), Event[].class);  

		return events;
	}
}
