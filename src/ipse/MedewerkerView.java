package ipse;

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class MedewerkerView extends View
{
	private Database database;
	private Controller controller;
	private JTable medewerkers;

	public MedewerkerView( Database database, Controller controller )
	{
		this.database = database;
		this.controller = controller;
		
		setSize(800,300);

		maakLijst();

		JScrollPane scrollPane = new JScrollPane(medewerkers);
		add(scrollPane);
		
		setVisible( true );
	}

	public void maakLijst()
	{
		try
		{
			ResultSet resultSet = database.getMedewerkers();

			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();

			Vector<String> koppen = new Vector<String>();
			for (int kolom = 1; kolom <= numberOfColumns; kolom++)
			{
				koppen.add(metaData.getColumnName(kolom));
			}

			Vector<Vector<String>> data = new Vector<Vector<String>>();
			while (resultSet.next())
			{
				Vector<String> rij = new Vector<String>();
				for (int kolom = 1; kolom <= numberOfColumns; kolom++)
				{
					rij.add(resultSet.getString(kolom));
				}
				data.add(rij);
			}
			
			JTable medewerkers = new JTable(data, koppen);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView( medewerkers );
		
			medewerkers.setAutoCreateRowSorter(true);
		}
		catch( SQLException se )
		{
			System.out.println( se );
		}
	}
}
