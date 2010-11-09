package cw.kuleuven.be.peno1011.cwb2;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class Schoolbuilding extends Building
{
	private String department;
	
	public Schoolbuilding(String department, String name, HashMap <String,String> openinghours,String phonenumber, URL groundplan, boolean isRentable, ArrayList<Room> rooms, Location location)
	{
			super(name, openinghours, phonenumber, groundplan, isRentable, rooms, location);
			this.department = department;
	}
			
	
	
	public String getDepartment()
	{
		return department;		
	}
	
	public void setDepartment(String department)
	{
		this.department = department;
	}
	
}