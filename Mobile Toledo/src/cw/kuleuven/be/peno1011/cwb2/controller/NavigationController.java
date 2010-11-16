package cw.kuleuven.be.peno1011.cwb2.controller;

import cw.kuleuven.be.peno1011.cwb2.model.Location;

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
	
	public void drawRoute(Location location) {
		int[] locCoordinates = location.getCoordinates();
		int X=locCoordinates[1];
		int Y=locCoordinates[2];
		
	}

}
