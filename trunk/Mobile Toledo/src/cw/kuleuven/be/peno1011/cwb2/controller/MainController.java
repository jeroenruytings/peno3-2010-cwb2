// DIT IS DE ALLERLAATSTE COMMIT
package cw.kuleuven.be.peno1011.cwb2.controller;

import cw.kuleuven.be.peno1011.cwb2.model.User;

public class MainController {
	private static User user;
	private static MainController MainController;
	
	public static MainController getInstance(){
		if (MainController == null) {
			MainController = new MainController();
		}
		return MainController; 
	}

	public void setUser(User user){
		MainController.user = user;
	}
	
	public User getUser(){
		return user;
	}
}
