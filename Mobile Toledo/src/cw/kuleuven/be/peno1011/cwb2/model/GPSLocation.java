package cw.kuleuven.be.peno1011.cwb2.model;

import android.location.Location;
import android.location.LocationManager;
/**
 * Een locatie op de kaart.
 * 
 */
public class GPSLocation {
	private String street;
	private int number;
	private int zipCode;
	private String city;
	private Location location;
	private LocationManager locationManager;
	
	public GPSLocation(int[] coordinates, String street, int number, int zipCode, String city) {
		this.street=street;
		this.number=number;
		this.zipCode=zipCode;
		this.city=city;
	}
	public Location getLocation() {
		return location;
	}
	public String getStreet() {
		return street;
	}

	public int getNumber() {
		return number;
	}

	public int getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}
}
