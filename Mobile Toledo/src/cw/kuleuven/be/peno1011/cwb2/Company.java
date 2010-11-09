package cw.kuleuven.be.peno1011.cwb2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Company extends Building
{
	private Catalogue catalogue;
	

	
	public Company(Catalogue catalogue, int locationNr, int[] coordinates, String street,
			int number, int zipCode, String city, String name,
			Map<String, String> openinghours, String phoneNumber, URL map,
			boolean isRentable, ArrayList<Room> rooms, Location location)
	{
		super(locationNr, coordinates, street, number, zipCode, city, name,
				openinghours, phoneNumber, map, isRentable, rooms, location);
		this.catalogue = catalogue;
	
	}

	public Catalogue getCatalogue()
	{
		return catalogue;
	}
	
	public void setCatalogue(Catalogue catalogue)
	{
		this.catalogue = catalogue;
	}
}
