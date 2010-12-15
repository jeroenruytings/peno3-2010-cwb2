package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.ArrayList;

public class Appreciation {
	
	private int score;
	private ArrayList<User> users = new ArrayList<User>();
	
	public Appreciation(int score, ArrayList<User> users) {
		setScore(score);
		setUsers(users);
	}
	private Appreciation() {}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

}