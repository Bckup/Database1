package database.model;

import java.util.ArrayList;


public class GraveMarker
{
	private ArrayList<Person> gravePersonList;
	private boolean isReadable;
	private String location;
	private String graveInformation;
	private String typeOfGrave;
	
	public GraveMarker()
	{
		
	}
	
	
	public String toString()
	{
		String graveInfo = "";
		
		for(Person current : gravePersonList)
		{
			graveInfo += current + " is buried here.\n";
		}
		
		if(isReadable)
		{
			graveInfo += "This grave is readable";	
		}
		else
		{
			graveInfo += "This grave is NOT readable";
		}
		
		return graveInfo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGraveInformation() {
		return graveInformation;
	}

	public void setGraveInformation(String graveInformation) {
		this.graveInformation = graveInformation;
	}

	public String getTypeOfGrave() {
		return typeOfGrave;
	}

	public void setTypeOfGrave(String typeOfGrave) {
		this.typeOfGrave = typeOfGrave;
	}
	

}
