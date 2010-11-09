package cw.kuleuven.be.peno1011.cwb2;

import java.util.Calendar;
import java.util.Date;

public class Event {
	
	private Calendar calendar = Calendar.getInstance();
	private Date date;
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

	public void setStartDate(int year, int month, int day, int hourOfDay, int minute) {
		calendar.set(year, month, day, hourOfDay, minute);
		this.startDate = calendar.getTime();
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(int year, int month, int day, int hourOfDay, int minute) {
		calendar.set(year, month, day, hourOfDay, minute);
		this.startDate = calendar.getTime();
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
