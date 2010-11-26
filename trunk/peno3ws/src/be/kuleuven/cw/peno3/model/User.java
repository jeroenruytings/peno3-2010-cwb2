package be.kuleuven.cw.peno3.model;

public class User {

	protected String firstname;
	protected String lastname;
	protected Integer id;
	protected Credential credentials;
	
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Credential getCredential() {
		return credentials;
	}
	public void setCredential(Credential credential) {
		this.credentials = credential;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id : " + getId() + "\n" + "firstname : " + getFirstName() + "\n" + "lastname : " + getLastName() + "\nCredentials : \n" + getCredential();
	}

	
}
