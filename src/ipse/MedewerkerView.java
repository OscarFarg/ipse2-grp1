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

		maakLijst();

		JScrollPane scrollPane = new JScrollPane(medewerkers);
		add(scrollPane);
	}

	public void maakLijst()
	{
		ResultSet resultSet = database.getMedewekers();

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
		medewerkers = new JTable(data, koppen)
		{
			public boolean isCellEditable(int rowIndex, int mColIndex)
			{
				return false;
			}
		};
		medewerkers.setAutoCreateRowSorter(true);
	}
}
