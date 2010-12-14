package cw.kuleuven.be.peno1011.cwb2.database;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.*;

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
		
//		String encrypted = "BEC92C4B21A174DCF20B30A5E84B75C52B87E1FF82E491FF62E2EE5AA11008B0FF68F879DFD46B326128C055750267BDF594F6029D1B74AC24D703FFB13B0ED9B1469A149A59353C80C958CCA8A110D491C1697F6CA505EC8DED8CDE99B0B8605D12FA68C4E5387FFAFEC9A429A962130965974AD64220E59280940A1AEEBFAFE13D6C6136FF468AEDEDA604958E6C36E04FE90C30233018AE47BE0C1C35CD9F79ED0D72AA231C56D1EF22150BFDBC64A4EDAA83F20250E5C5B7C14E8FFDAB8B1CC85E1A526FEB8B00453A3FAB0020AD";
//		String json2 = cryptography.decrypt(encrypted);
//		
//		byte[] raw = {-26, -96, 79, -102, -122, 14, 82, 77, -75, 62, -58, 62, -51, 32, -112, -107};
//		byte[] encrypte = {-66, -55, 44, 75, 33, -95, 116, -36, -14, 11, 48, -91, -24, 75, 117, -59, 43, -121, -31, -1, -126, -28, -111, -1, 98, -30, -18, 90, -95, 16, 8, -80, -1, 104, -8, 121, -33, -44, 107, 50, 97, 40, -64, 85, 117, 2, 103, -67, -11, -108, -10, 2, -99, 27, 116, -84, 36, -41, 3, -1, -79, 59, 14, -39, -79, 70, -102, 20, -102, 89, 53, 60, -128, -55, 88, -52, -88, -95, 16, -44, -111, -63, 105, 127, 108, -91, 5, -20, -115, -19, -116, -34, -103, -80, -72, 96, 93, 18, -6, 104, -60, -27, 56, 127, -6, -2, -55, -92, 41, -87, 98, 19, 9, 101, -105, 74, -42, 66, 32, -27, -110, -128, -108, 10, 26, -18, -65, -81, -31, 61, 108, 97, 54, -1, 70, -118, -19, -19, -90, 4, -107, -114, 108, 54, -32, 79, -23, 12, 48, 35, 48, 24, -82, 71, -66, 12, 28, 53, -51, -97, 121, -19, 13, 114, -86, 35, 28, 86, -47, -17, 34, 21, 11, -3, -68, 100, -92, -19, -86, -125, -14, 2, 80, -27, -59, -73, -63, 78, -113, -3, -85, -117, 28, -56, 94, 26, 82, 111, -21, -117, 0, 69, 58, 63, -85, 0, 32, -83};
//		byte[] test = null;
//		try {
//			test = cryptography.decrypt(raw, encrypte);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String string = new String(test);
		
		Event[] events = new Gson().fromJson(json.toString(), Event[].class);  
		
		int endLocationId = 0;
		int startLocationId = 0;
		int startRoomId = 0;
		int endRoomId = 0;
		int counter = 0;
		
		while(counter < events.length){
			startLocationId = json.indexOf("locationId",endLocationId)+12;
			endLocationId = json.indexOf(",", startLocationId);
			String locationId = json.substring(startLocationId,endLocationId);
			
			startRoomId = json.indexOf("roomId",startRoomId)+8;
			endRoomId = json.indexOf("}", startRoomId);
			String roomId = json.substring(startRoomId,endRoomId);
			
			if(! locationId.equals("0")){
				
				Building currentBuilding = null;
				GPSLocation currentLocation = null;
				
				PostMethod methodBuilding = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/BuildingHandler/getBuildingById");
				methodBuilding.addParameter("locationId",locationId);
				returnCode = client.executeMethod(methodBuilding);
				String jsonBuilding = cryptography.decrypt(methodBuilding.getResponseBodyAsString());
				if(!jsonBuilding.contains("[]")){
					Building[] buildings = new Gson().fromJson(jsonBuilding.toString(), Building[].class);
					currentBuilding = buildings[0];
				}
				
				PostMethod methodLocation = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/LocationHandler/getLocation");
				methodLocation.addParameter("locationId", locationId);
				returnCode = client.executeMethod(methodLocation);
				String jsonLocation = cryptography.decrypt(methodLocation.getResponseBodyAsString());
				if(!jsonLocation.contains("[]")){
					GPSLocation[] locations = new Gson().fromJson(jsonLocation.toString(), GPSLocation[].class);
					currentLocation = locations[0];
				}
				

				if(currentBuilding != null && currentLocation!= null){
					currentBuilding.setLocation(currentLocation);
					events[counter].setBuilding(currentBuilding);
				}
			}
			
			if(! roomId.equals("0")){
				PostMethod methodBuilding = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/RoomHandler/getRoomByRoomId");
				methodBuilding.addParameter("roomId",roomId);
				returnCode = client.executeMethod(methodBuilding);
				String jsonRoom = cryptography.decrypt(methodBuilding.getResponseBodyAsString());
				if(!jsonRoom.contains("[]")){
					Room[] rooms = new Gson().fromJson(jsonRoom.toString(), Room[].class);
					Room currentRoom = rooms[0];
					events[counter].setRoom(currentRoom);
				}
			}
			
			counter++;
		}
		
		
		return events;
	}
	
	public ArrayList<Event> getEventsByCategoriesAndDate(Date startDate, Date stopDate, String categorie1, String categorie2) throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/EventHandler/getEventsByTwoCategoriesAndDateWithLocation");
		method.addParameter("categorie1",categorie1);
		method.addParameter("categorie2",categorie2);
		method.addParameter("startDate", cryptography.toMysqlDate(startDate));
		method.addParameter("stopDate", cryptography.toMysqlDate(stopDate));
		
		int returnCode = client.executeMethod(method);
		String json = cryptography.decrypt(method.getResponseBodyAsString());
		
		return null;
	}

	public ArrayList<Event> getEventsByCategoriesAndDate(Date startDate,
			Date stopDate, String categorie) throws HttpException, IOException {
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/EventHandler/getEventsByCategorieAndDateWithLocation");
		method.addParameter("categorie",categorie);
		method.addParameter("startDate", cryptography.toMysqlDate(startDate));
		method.addParameter("stopDate", cryptography.toMysqlDate(stopDate));
		
		int returnCode = client.executeMethod(method);
		String json = cryptography.decrypt(method.getResponseBodyAsString());
		
		return null;
	}
}
