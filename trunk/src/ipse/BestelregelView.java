package ipse;

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class BestelregelView extends View
{
	private Database database;
	private Controller controller;
	private JTable bestelregels;
	
	public BestelregelView(Database database, Controller controller)
	{
		this.database = database;
		this.controller = controller;
		
		maakLijst();
		
		JScrollPane scrollPane = new JScrollPane(bestelregels);
		add(scrollPane);
	}
	
	public void maakLijst()//haal de resultaten uit de db, maak de vectoren en vul de bestellingentabel
	{
		ResultSet resultSet = database.getBestelregels(bestelnr);
		
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
		bestelregels = new JTable(data, koppen)
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}
		};
		bestelregels.setAutoCreateRowSorter(true);
	}
}
