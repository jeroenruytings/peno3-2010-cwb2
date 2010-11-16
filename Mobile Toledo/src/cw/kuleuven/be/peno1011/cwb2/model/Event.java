package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.Date;

public class Event {
	
	private String description;
	private Date startDate;
	private Date stopDate;
	private GPSLocation place;
	private String category;
	
	public Event(String description, GPSLocation place, String category, Date startDate, Date stopDate) {
		setDescription(description);
		setStartDate(startDate);
		setStopDate(stopDate);
		setPlace(place);
		setCategory(category);
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

	public GPSLocation getPlace() {
		return place;
	}

	public void setPlace(GPSLocation place) {
		this.place = place;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
