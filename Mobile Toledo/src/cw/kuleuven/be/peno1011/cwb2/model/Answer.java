package cw.kuleuven.be.peno1011.cwb2.model;

public class Answer {
	private int total;
	private String answer;
	private User user;
	
	public Answer(int total, String answer){
		setTotal(total);
		setAnswer(answer);
	}
	
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

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
}
