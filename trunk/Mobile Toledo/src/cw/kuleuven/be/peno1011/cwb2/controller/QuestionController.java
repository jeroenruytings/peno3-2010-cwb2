package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.database.QuestionDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;



public class QuestionController{

	private static QuestionController QuestionController;

	public static QuestionController getInstance() {
		if (QuestionController == null) {
			QuestionController = new QuestionController();
		}
		return QuestionController; 
	}

	public void insert(String question, int id) throws HttpException, IOException {
		String userId = MainController.getInstance().getUser().getUserId();
		QuestionDAO.getInstance().addQuestion(userId,question,""+id);
	}
	
}