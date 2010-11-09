package cw.kuleuven.be.peno1011.cwb2;

import java.util.ArrayList;

public class Appreciation {
	
	private int score;
	private ArrayList<User> users = new ArrayList<User>();
	
	public Appreciation() {
		score = 0;
	}

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
