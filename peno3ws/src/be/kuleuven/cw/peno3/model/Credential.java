package be.kuleuven.cw.peno3.model;

public class Credential {

	protected String username;
	protected String password;
	protected Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id : " + getId() + "\n" + "username : " + getUsername() + "\n" + "password : " + getPassword();
	}
}
