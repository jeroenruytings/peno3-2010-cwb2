package cw.kuleuven.be.peno1011.cwb2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.database.MultipleChoiceDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.MultipleChoice;



public class MultipleController{

	private static MultipleController MultipleController;

	public static MultipleController getInstance() {
		if (MultipleController == null) {
			MultipleController = new MultipleController();
		}
		return MultipleController; 
	}

	
	public ArrayList<MultipleChoice> multiple () {
		
		ArrayList<MultipleChoice> multiplechoices = new ArrayList<MultipleChoice>();
		
		
		
		return multiplechoices;
	}
	
	
	//TODO: Controller moet nog gemaakt worden.ook DAO:  getmultiple
	//Dit is om multiple in te steken, is dit wel nodig???
	public void insert(String question, Lecture lecture) throws HttpException, IOException {
		
		MultipleChoiceDAO.getInstance().getMultipleChoice(lecture.getEventId());
	}
	
	
	
}