package cw.kuleuven.be.peno1011.cwb2.model;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.database.EventDAO;

public class Event {
	
	private String title;
	private String description;
	private Date startDate;
	private Date stopDate;
	private Building building;
	private String categorie;
	private Room room;
	
	public Event(String title,String description, String categorie, Date startDate, Date stopDate, Building building, Room room) {
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setStopDate(stopDate);
		setCategorie(categorie);
		setRoom(room);
		setBuilding(building);
	}
	
	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	protected Event(){};
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

}
