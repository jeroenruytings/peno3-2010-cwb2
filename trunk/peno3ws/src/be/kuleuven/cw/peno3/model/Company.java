package be.kuleuven.cw.peno3.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Company extends Building
{
	private Catalogue catalogue;
	
	public Company(Catalogue catalogue, String name,
			Map<String, String> openinghours,String phonenumber,
			URL groundplan, boolean isRentable, ArrayList<Room> rooms,
			GPSLocation location, int[] coordinates, String street,
			int number, int zipCode, String city, String phoneNumber,
			URL map){
		super(coordinates, street, number, zipCode, city, name,
				openinghours, phoneNumber, map, isRentable, rooms, location);
		setCatalogue(catalogue);
	
	}

	public Catalogue getCatalogue(){
		return catalogue;
	}
	
	public void setCatalogue(Catalogue catalogue){
		this.catalogue = catalogue;
	}
}
