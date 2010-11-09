package cw.kuleuven.be.peno1011.cwb2;
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
	private Map<String,String> openinghours;
	private String phoneNumber;
	private URL map;
	private boolean isRentable;
	private ArrayList<Room> rooms;
	private Location location;
    
    /**
     * 
     */
    public Building(String name, Map<String,String> openinghours, String phoneNumber, URL map, boolean isRentable, ArrayList<Room> rooms, Location location) {
		this.name=name;
		this.openinghours=openinghours;
		this.phoneNumber=phoneNumber;
		this.map=map;
		this.isRentable=isRentable;
		this.rooms=rooms;
		this.location=location;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getOpeninghours() {
		return openinghours;
	}

	public void setOpeninghours(Map<String, String> openinghours) {
		this.openinghours = openinghours;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public URL getMap() {
		return map;
	}

	public void setMap(URL map) {
		this.map = map;
	}

	public boolean isRentable() {
		return isRentable;
	}

	public void setRentable(boolean isRentable) {
		this.isRentable = isRentable;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
    
}

