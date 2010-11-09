package cw.kuleuven.be.peno1011.cwb2;

import java.net.URL;
import java.util.*;


public class Sport extends Building{
	
	private String discipline;
	
	public Sport(String name, Map<String,String> openinghours, String phoneNumber, URL map, boolean isRentable, ArrayList<Room> rooms, Location location, String discipline){
		super(name, openinghours, phoneNumber, map, isRentable, rooms, location);
		this.discipline = discipline;
	}
	
	public void setDiscipline(String discipline){
		this.discipline =  discipline;
	}
	
	public String getDiscipline(){
		return discipline;
	}
}