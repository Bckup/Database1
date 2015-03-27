package database.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import database.controller.AppController;
import database.model.Person;


public class DatabasePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppController baseController;
	private JButton testButton;
	private SpringLayout baseLayout;
	
	public DatabasePanel(AppController baseController)
	{
		this.baseController = baseController;
		testButton = new JButton("click");
		baseLayout = new SpringLayout();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(testButton);
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.EAST, testButton, -150, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, testButton, 200, SpringLayout.NORTH, this);
		
	}
	
	private void setupListeners()
	{
		testButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				baseController.addDeadPerson(createPersonFromValues());
			}
		});
	}
