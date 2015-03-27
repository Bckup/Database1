package database.controller;

import database.view.DatabaseFrame;


public class AppControllerNew 
{
	private DatabaseFrame appFrame;
	private DatabaseController database;
	
	public AppControllerNew()
	{
		database = new DatabaseController(this);
		appFrame = new DatabaseFrame(this);
	}

	public DatabaseFrame getAppFrame()
	{
		return appFrame;
	}
	
	public DatabaseController getDatabase()
	{
		return database;
	}
	public void start() 
	{
		// need to try the connectionStringBuilder and
	}
}