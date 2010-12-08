package cw.kuleuven.be.peno1011.cwb2.model;

import android.location.Location;
/**
 * Een locatie op de kaart.
 * 
 */
public class GPSLocation {
	private String street;
	private String number;
	private int zipcode;
	private String city;
	private double xcoordinate;
	private double ycoordinate;
	
	private GPSLocation(){
	}
	
	public String getStreet() {
		return street;
	}

	public String getNumber() {
		return number;
	}

	public int getZipcode() {
		return zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setXcoordinate(int xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public double getXcoordinate() {
		return xcoordinate;
	}

	public void setYcoordinate(int ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	public double getYcoordinate() {
		return ycoordinate;
	}
}
