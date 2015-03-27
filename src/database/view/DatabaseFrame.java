package database.view;

import javax.swing.JFrame;

import database.controller.AppController;

public class DatabaseFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatabasePanel myDatabasePanel;

	public DatabaseFrame(AppController baseController)
	{
		myDatabasePanel = new DatabasePanel(baseController);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(myDatabasePanel);
		this.setSize(600, 800);
		this.setVisible(true);
	}
}
