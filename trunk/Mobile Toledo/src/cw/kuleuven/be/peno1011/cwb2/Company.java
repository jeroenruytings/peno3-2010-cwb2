package cw.kuleuven.be.peno1011.cwb2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Company extends Building
{
	private Catalogue catalogue;
	
	public Company(Catalogue catalogue, String name,
			Map<String, String> openinghours,String phonenumber,
			URL groundplan, boolean isRentable, ArrayList<Room> rooms,
			Location location){
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
