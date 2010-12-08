package cw.kuleuven.be.peno1011.cwb2.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.Building;
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
	
	public ArrayList<Room> listRooms() throws HttpException, IOException{
		ArrayList<Room> rooms = new ArrayList<Room>();		
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/RoomHandler/listRooms");
				
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		if(json.contains("[]")) {
			rooms = null;
		}
		else {
			Room[] roomArray = new Gson().fromJson(json.toString(), Room[].class);
			rooms = (ArrayList<Room>) Arrays.asList(roomArray);
		}
		return rooms;
	}

}
