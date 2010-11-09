package cw.kuleuven.be.peno1011.cwb2;

import java.lang.reflect.Array;



public class Room {

	private  int[] coordinates = new int [2];
	private String function;
	private String accomodation;
	private Array responsibles;
	
	public Room(int[] coordinates, String function, String accomodation,
			Array responsibles) {
		this.coordinates = coordinates;
		this.function = function;
		this.accomodation = accomodation;
		this.responsibles = responsibles;
	}

	
	
	public int[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(String accomodation) {
		this.accomodation = accomodation;
	}

	public Array getResponsibles() {
		return responsibles;
	}

	public void setResponsibles(Array responsibles) {
		this.responsibles = responsibles;
	}
	

	
	

	
	                                     
}
