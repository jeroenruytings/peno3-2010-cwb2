package be.kuleuven.cw.peno3.model;

import java.util.Date;

//import android.location.Location;


public class User {
	private String userId;
	private String firstName;
	private String lastName;
	private String password;
	private int level;
	private Date birthDate;
	private ISP isp;
//	private Location location;
	
	public User(String userId, String firstName, String lastName,String password, int level, Date birthDate, ISP isp) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		setPassword(password);
		setLevel(level);
		this.birthDate = birthDate;
		setIsp(isp);
	}
	
	private User() {}
	
//	public void setLocation(Location location)
//	{
//		this.location=location;
//	}
//	public Location getLocation()
//	{
//		return location;
//	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ISP getIsp() {
		return isp;
	}

	public void setIsp(ISP isp) {
		this.isp = isp;
	}

	public String getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	
}
