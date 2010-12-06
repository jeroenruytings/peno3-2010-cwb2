package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.Date;

import android.location.Location;


public class User {
	private String userId;
	private String firstName;
	private String lastName;
	private String password;
	private int rank;
	private Date birthDate;
	private ISP isp;
	
	private User(){
		
	}
	
	public User(String userId, String firstName, String lastName,String password, int rank, Date birthDate, ISP isp) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		setPassword(password);
		setRank(rank);
		this.birthDate = birthDate;
		setIsp(isp);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int level) {
		this.rank = level;
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
