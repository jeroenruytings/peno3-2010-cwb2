package cw.kuleuven.be.peno1011.cwb2.model;

import java.net.URL;
import java.util.*;


public class Sport extends Building{
	
	private String discipline;
	
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
	public Sport(int[] coordinates, String street, int number,
			int zipCode, String city, String name,
			Map<String, String> openinghours, String phoneNumber, URL map,
			boolean isRentable, ArrayList<Room> rooms, Location location, String discipline) {
		super(coordinates, street, number, zipCode, city, name,
				openinghours, phoneNumber, map, isRentable, rooms, location);
		this.discipline = discipline;
	}

	public void setDiscipline(String discipline){
		this.discipline =  discipline;
	}
	
	public String getDiscipline(){
		return discipline;
	}
}