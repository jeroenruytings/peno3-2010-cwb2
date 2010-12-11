package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.PictureLink;
import cw.kuleuven.be.peno1011.cwb2.model.Room;

public class RoomDAO {
	private static RoomDAO roomDAO;
	private Cryptography cryptography = Cryptography.getInstance();
	
	// Singleton has a private constructor
	private RoomDAO(){
		
	}
	
	public static RoomDAO getInstance() {
		if (roomDAO == null){
			roomDAO = new RoomDAO();
		}
		return roomDAO;
	}
	
	public Room [] listRooms() throws HttpException, IOException{
		Room [] roomArray = null;
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/RoomHandler/listRooms");
				
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		if(json.contains("[]")) {
			roomArray = null;
		}
		else {
			roomArray = new Gson().fromJson(json.toString(), Room[].class);
		}
		return roomArray;
	}
	
	public String[] listRoomNames() throws HttpException, IOException {
		Room[] roomNames = null;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/RoomHandler/listRoomNames");
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		roomNames = new Gson().fromJson(json.toString(), Room[].class);
		String[] names = new String[roomNames.length];
		if(roomNames.length!=0) {
			int i=0;
			while(i<roomNames.length) {
				names[i]=roomNames[i].getName();
				i++;
			}
		}
		else {
			names = null;
		}
		
		return names;
	}
	
	public Boolean roomExists(String roomname) throws IOException
	{
		Boolean exists = false;
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/RoomHandler/getRoom");
		method.addParameter("name", roomname);
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		if(json.contains("[]")) {
			exists = false;
		}
		else {
			exists = true;
		
		}
// if naam komt voor in database exists = true;
		
		return exists;
	}

	public String[] getPictures(String roomname) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/RoomHandler/getPictureByName");
		method.addParameter("name", roomname);
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		PictureLink[] pictures = new Gson().fromJson(json.toString(), PictureLink[].class);
		String[] links = null;
		for(int i = 0; i<pictures.length; i++){
			links[i] = pictures[i].getPicture();
		}
		return links;
	}

}
