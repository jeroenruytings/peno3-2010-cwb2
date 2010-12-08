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
	private String phonenumber;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phoneNumber) {
		this.phonenumber = phoneNumber;
	}


	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
    
}

