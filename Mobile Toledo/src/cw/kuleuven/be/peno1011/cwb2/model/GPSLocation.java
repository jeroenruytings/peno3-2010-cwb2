package cw.kuleuven.be.peno1011.cwb2.model;

import android.location.Location;
/**
 * Een locatie op de kaart.
 * 
 */
public class GPSLocation {
	private String street;
	private int number;
	private int zipCode;
	private String city;
	private int xcoordinate;
	private int ycoordinate;
	
	public GPSLocation(){
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

	public void setXcoordinate(int xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public int getXcoordinate() {
		return xcoordinate;
	}

	public void setYcoordinate(int ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	public int getYcoordinate() {
		return ycoordinate;
	}
}
