package be.kuleuven.cw.peno3.model;
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
//	private GPSLocation location;

	
	public Building(){
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		if(phonenumber == null){
			phonenumber = "telefoonnummer";
		}
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

	public String getOpeninghours() {
		
		if (openinghours == null){
			openinghours = "openingsuren";
		}
		return openinghours;
	}

	public void setOpeninghours(String openinghours) {
			this.openinghours = openinghours;
	}

//	public GPSLocation getLocation() {
//		return location;
//	}
//
//	public void setLocation(GPSLocation location) {
//		this.location = location;
//	}
    
}

