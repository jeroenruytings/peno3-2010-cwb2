package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.ArrayList;

public class Answer {
	private int total;
	private String answer;
	private ArrayList<User> users;
	
	public Answer(int total, String answer, ArrayList<User> users){
		setTotal(total);
		setAnswer(answer);
		setUsers(users);
	}
	
	private Answer() { }
	
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal() {
		return total;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer() {
		return answer;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		users.add(user);
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
}
