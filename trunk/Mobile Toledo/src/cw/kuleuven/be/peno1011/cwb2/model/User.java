package cw.kuleuven.be.peno1011.cwb2.model;
import java.util.Date;


public class User {
	private final String userId;
	private final String firstName;
	private final String lastName;
	private String password;
	private boolean isStudent;
	private final Date birthDate;
	private ISP isp;
	
	public User(String userId, String firstName, String lastName,String password, boolean isStudent, Date birthDate, ISP isp) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		setPassword(password);
		setStudent(isStudent);
		this.birthDate = birthDate;
		setIsp(isp);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
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
