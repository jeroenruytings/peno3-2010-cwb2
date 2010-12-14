package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.database.MultipleChoiceDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;



public class MultipleController{

	private static MultipleController MultipleController;

	public static MultipleController getInstance() {
		if (MultipleController == null) {
			MultipleController = new MultipleController();
		}
		return MultipleController; 
	}

	//TODO: Controller moet nog gemaakt worden.
	public void insert(String question, Lecture lecture) throws HttpException, IOException {
		
		MultipleChoiceDAO.getInstance().getMultipleChoice(lecture.getEventId());
	}
	
	
	
}