package ipse;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class KlantView extends View{
	private Database database;
	private Controller controller;
	private JTable klanten;

	public KlantView( Database database, Controller controller )
	{
		this.database = database;
		this.controller = controller;

		setSize(800,300);

		maakLijst();

		JScrollPane scrollPane = new JScrollPane(klanten);
		add(scrollPane);

		setVisible( true );
	}

	public void maakLijst()//haal de resultaten uit de db, maak de vectoren en vul de bestellingentabel
	{
		try
		{
			ResultSet resultSet = database.getKlanten();

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
			klanten = new JTable(data, koppen)
			{
				public boolean isCellEditable(int rowIndex, int mColIndex)
				{
					return false;
				}
			};
			klanten.setAutoCreateRowSorter(true);
			klanten.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		catch( SQLException se )
		{
			System.out.println( se );
		}
	}
}
