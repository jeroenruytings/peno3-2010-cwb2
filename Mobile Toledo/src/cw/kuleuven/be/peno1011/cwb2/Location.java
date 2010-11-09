package cw.kuleuven.be.peno1011.cwb2;


/**
 * Een locatie op de kaart.
 * 
 */
public class Location {
	private int[] coordinates = new int[2];
	private String street;
	private int number;
	private int zipCode;
	private String city;
	
	public Location(int[] coordinates, String street, int number, int zipCode,
			String city) {
		this.coordinates = coordinates;
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
	}

}
