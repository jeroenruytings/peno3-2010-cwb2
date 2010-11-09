package cw.kuleuven.be.peno1011.cwb2;

import java.sql.Date;

public class Event {
	
	private String description;
	private Date startDate;
	private Date stopDate;
	private Location place;
	private String category;
	
	public Event() {
		
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

	public Location getPlace() {
		return place;
	}

	public void setPlace(Location place) {
		this.place = place;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
