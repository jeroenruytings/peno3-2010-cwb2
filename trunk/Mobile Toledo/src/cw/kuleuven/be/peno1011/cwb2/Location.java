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
	
	public Location(int[] coordinates, String street, int number, int zipCode, String city) {
		setCoordinates(coordinates);
		setStreet(street);
		setNumber(number);
		setZipCode(zipCode);
		setCity(city);
	}
	public int[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
