package cw.kuleuven.be.peno1011.cwb2.model;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;


/**
 * Een gebouw stelt elk type van mogelijke locaties voor.
 * 
 */
public class Building
{
	private String name;
	private String openinghours;
	private String phoneNumber;
	private ArrayList<Room> rooms;
	private GPSLocation location;

	
	public Building(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
    
}

