package be.kuleuven.cw.peno3.model;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;


/**
 * Een gebouw stelt elk type van mogelijke locaties voor.
 * 
 */
public class Building extends GPSLocation
{
	private String name;
	private Map<String,String> openinghours;
	private String phoneNumber;
	private URL map;
	private boolean isRentable;
	private ArrayList<Room> rooms;

	/**
	 * @param locationNr
	 * @param coordinates
	 * @param street
	 * @param number
	 * @param zipCode
	 * @param city
	 * @param name
	 * @param openinghours
	 * @param phoneNumber
	 * @param map
	 * @param isRentable
	 * @param rooms
	 * @param location
	 */
	public Building(int[] coordinates, String street,int number, int zipCode, String city, String name,Map<String, String> openinghours, String phoneNumber, URL map,boolean isRentable, ArrayList<Room> rooms, GPSLocation location) {
		super(coordinates, street, number, zipCode, city);
		setName(name);
		setOpeninghours(openinghours);
		setPhoneNumber(phoneNumber);
		setMap(map);
		setRentable(isRentable);
		setRooms(rooms);
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
    
}

