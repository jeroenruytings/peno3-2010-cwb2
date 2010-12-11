package cw.kuleuven.be.peno1011.cwb2.controller;



public class QuestionController{

	private static QuestionController QuestionController;

	public static QuestionController getInstance() {
		if (QuestionController == null) {
			QuestionController = new QuestionController();
		}
		return QuestionController; 
	}
	
	
	
}