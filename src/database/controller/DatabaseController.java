package database.controller;

import java.sql.*;

import javax.swing.JOptionPane;

*Data base Controller
public class DatabaseController
{

	private String connectionString;
	private Connection databaseConnection;
	private AppControllerNew baseController;
	private String currentQuery;

	public DatabaseController(AppControllerNew baseController)
	{
		this.baseController = baseController;
		connectionString = "jdbc:mysql://localhost/afternoon?user=derp";
		checkDriver();
		setupConnection();
	}

	/**
	 * Checks driver
	 */
	*Check the windows drivers
	private void checkDriver()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception current)
		{
			System.err.println("Check that the driver is loaded in the project build path");
			System.exit(1);
		}
	}
    *Close the connections 
	public void closeConnection()
	{
		try
		{
			databaseConnection.close();
		}
		catch (SQLException error)
		{
		     displaySQLErrors(error);
	    }
		
	}
	*Setup the Connections
	public void setupConnection()
	{
		try
		{
			databaseConnection = DriverManager.getConnection(connectionString);
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
	}

	*Check for structure violations
	private boolean checkForStructureViolation()
	{
		if(currentQuery.toUpperCase().contains("DATABASE"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	*Check for violations
	private boolean checkForViolation()
	{
		if (currentQuery.toUpperCase().contains("DROP")
				|| currentQuery.toUpperCase().contains("TRUNCATE")
				|| currentQuery.toUpperCase().contains("SET")
				|| currentQuery.toUpperCase().contains("ALTER"))
		{
			return true;
		
		}
		else
		{
			return false;
		}
	}
	*Results of the select Query 
	public String [][] selectQueryResults(String query)
	{
		this.currentQuery = query;
		String[][] columns;
		
		try
		{
			if(checkForStructureViolation())
			{
				throw new SQLException("Attempted illegal modification of data",
						"Done tried to mess up da data State :(",
						Integer.MIN_VALUE);
			}
			
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answer = firstStatement.executeQuery(query);
			int columnCount = answer.getMetaData().getColumnCount();
			
			answer.last();
			int rowCount = answer.getRow();
			answer.beforeFirst();
			columns = new String[rowCount][columnCount];
			
			while (answer.next())
			{
				for (int col = 0; col <columnCount; col++)
				{
					columns[answer.getRow()-1][col]=answer.getString(col+1);
				}
			}
			answer.close();
			firstStatement.close();	
		}
		catch (SQLException currentSQLError)
		{
			columns = new String [][] {"error processing query"}, {"try sending a better query"}, {currentSQLError.getMessage()}};
			displaySQLErrors(currentSQLError);	
	     }
	return columns;
}
	*Information on the tables
	public String displayTables()
	{
		String columns = "";
		String query = "SHOW TABLES";
		
		try
		{
			Statement fiestStatement = databaseConnection.createStatement();
			ResultSet answer = firstStatement.executeQuery(query);
			while (answer.next())
			{
				columns += answer.getString(1)+"\n";
			}
			answer.close();
			firstStatement.close();
		}
		catch (SQLException currentSQLError)
		{
			displaySQLErrors(currentSQLError);
		}
		return columns;
	}
	*Meta data tiles
	public String[] getMetaDataTiles()
	{
		String[] columns;
		String query = "SHOW TABLES";
		try
		{
			Statement firstStatement = databaseConnection.createStatement();
			ResultSet answers = firstStatement.executeQuery(query);
			ResultSetMetaData myMeta = answers.getMetaData();

			columns = new String[myMeta.getColumnCount()];

			for (int spot = 0; spot < myMeta.getColumnCount(); spot++)
			{
				columns[spot] = myMeta.getColumnName(spot + 1);
				
			}
			*The table information
			public String [][] tableInfo()
			{
				String[][] columns;
				String query = "SHOW TABLES";
				
				try
				{
					Statement fiestStatement = databaseConnection.createStatement();
					ResultSet answer = firstStatement.executeQuery(query);
					int columnCount = answer.getMetaData().getColumnCount();
					int rowCount;
					answer.last();
					rowCount = answer.getRow();
					answer.beforeFirst();
					columns = new String[rowCount][1];
					
					while (answer.next())
					{
						columns[answer.getRow() - 1][0] = answer.getString(1);
					}
					answer.close();
					firstStatement.close();
				}
				catch (SQLException currentSQLError)
				{
					columns = new String [] {"problem occured"};
					displaySQLErrors(currentSQLError);
				}
				*The actual info contained
			public String [] [] realInfo()
			{
				String[][] columns;
				String query = "SELECT * FROM `INNODB_SYS_COLUMNS`";
				
				try
				{
					Statement fiestStatement = databaseConnection.createStatement();
					ResultSet answer = firstStatement.executeQuery(query);
					int columnCount = answer.getMetaData().getColumnCount();
					int rowCount;
					answer.last();
					rowCount = answer.getRow();
					answer.beforeFirst();
					columns = new String[rowCount][columnCount];
					
					while (answer.next())
					{
						for (int col = 0; col < columnCount; col++)
						{
							columns[answer.getRow()-1][col] = answer.getString(col + 1);
						}
				
					}

			answer.close();
			firstStatement.close();
		}
		catch (SQLException currentException)
		{
			columns = new String[] { "empty" };
			displaySQLErrors(currentException);
		}
		return columns;
	}
    *Delete the index in the table
	public void dropStatement(String query)
	{
		currentQuery = query;
		String results;
		try
		{
			if(checkForStructureViolation())
			{
				throw new SQLException("you is no allowed to dropping db's)
				                       "duh",
				                       Integer.MIN_VALUE);
			}
			
			if(currentQuery.toUpperCase().contains("INDEX"))
			{
				results="The index was";
			}
			else
			{
				results= "The table was";
			}
			
			Statement dropStatement = databaseConnection.createStatement();
			int affected = dropStatement.executeUpdate(currentQuery);
			
			dropStatement.close();
			
			if(affected==0)
			{
				results += "dropped";
			}
			JOptionPane.showMessageDialog(baseController.getAppFrame(), results), message);
		}
		catch(SQLException dropError)
		{
			displayErrors(dropError);
		}
	}
	catch(SQLException dropError)
	{
		displaySQLErrors(dropError);	
	}
			
			
	*Display the SQL Errors
	public void displaySQLErrors(Exception currentException)
	{
		JOptionPane.showMessageDialog(null, "The SQL error is: " + currentException.getMessage());
		if (currentException instanceof SQLException)
		{
			JOptionPane.showMessageDialog(null, "The MySQL server state is: " + ((SQLException) currentException).getSQLState());
			JOptionPane.showMessageDialog(null, "The MySQL error code is: " + ((SQLException) currentException).getErrorCode());
		}
	}

}
