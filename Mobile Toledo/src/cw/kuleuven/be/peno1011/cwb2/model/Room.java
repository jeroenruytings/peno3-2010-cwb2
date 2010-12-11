package cw.kuleuven.be.peno1011.cwb2.model;

import java.lang.reflect.Array;




/**
 * @author CWB2-1011
 * 
 *
 */
public class Room {
	
	private int xcoordinate;
	private int ycoordinate;
	private String function;
	private Building building;
	private boolean wireless;
	private String link;
	private int capacity;
	private String name;
	
	private Room(){}
	
	public Room(int xcoordinate, int ycoordinate, String function, Building building, boolean wireless, String link, int capacity, String name) {
		setXcoordinate(xcoordinate);
		setYcoordinate(ycoordinate);
		setFunction(function);
		setBuilding(building);
		setWireless(wireless);
		setLink(link);
		setCapacity(capacity);
		setName(name);
	}
	
	public int getXcoordinate() {
		return xcoordinate;
	}

	public void setXcoordinate(int xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public int getYcoordinate() {
		return ycoordinate;
	}

	public void setYcoordinate(int ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	public boolean isWireless() {
		return wireless;
	}

	public void setWireless(boolean wireless) {
		this.wireless = wireless;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	                                     
}
