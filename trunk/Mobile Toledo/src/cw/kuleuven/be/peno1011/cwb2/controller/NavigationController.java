package cw.kuleuven.be.peno1011.cwb2.controller;

public class NavigationController {
	
	private static NavigationController navigationController;
	
	private NavigationController() {
		
	}
	
	public static NavigationController getInstance() {
		if (navigationController == null) {
			navigationController = new NavigationController();
		}
		return navigationController;
	}
	
	

}
