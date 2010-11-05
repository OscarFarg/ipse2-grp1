package ipse;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class TabelPaneel extends JPanel 
{
	private Database database;
	private JTable contentTabel;
	private ViewsEnum view;

	public TabelPaneel(Database database, ViewsEnum view)
	{
		this.database = database;
		this.view = view;	
		//this.setLayout(null);
		this.setSize(800,300);
		maakLijst();
		add(new JScrollPane(contentTabel));
	}

	public void maakLijst()//haal de resultaten uit de db, maak de vectoren en vul de bestellingentabel
	{
		try
		{
			ResultSet resultSet = null;
			switch (view)
			{
			case ARTIKEL:
				resultSet = database.getArtikelen();
				break;
			case BESTELLING:
				resultSet = database.getBestellingen();
				break;
			case MEDEWERKER:
				resultSet = database.getMedewerkers();
				break;
			case KLANT:
				resultSet = database.getKlanten();
				break;
			case BESTELREGEL:
				resultSet = database.getBestelregels(getGeselecteerdItem());
				break;
			}

			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();

			Vector<String> koppen = new Vector<String>();
			for (int kolom = 1; kolom <= numberOfColumns; kolom++)
			{
				koppen.add(metaData.getColumnName(kolom));
			}
			if (view.equals(ViewsEnum.BESTELLING))
				koppen.add("Totaal prijs");

			Vector<Vector<String>> data = new Vector<Vector<String>>();
			while (resultSet.next())
			{
				Vector<String> rij = new Vector<String>();
				for (int kolom = 1; kolom <= numberOfColumns; kolom++)
				{
					rij.add(resultSet.getString(kolom));
				}
				if (view.equals(ViewsEnum.BESTELLING))
					rij.add(database.getTotaalPrijs(resultSet.getInt(1)));

				data.add(rij);

			}
			contentTabel = new JTable(data, koppen)
			{
				public boolean isCellEditable(int rowIndex, int mColIndex)
				{
					return false;
				}
			};
			contentTabel.setAutoCreateRowSorter(true);
			contentTabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		catch( SQLException se )
		{
			System.out.println( se );
		}
	}

	public void veranderView(ViewsEnum view)
	{
		this.view = view;
		remove(this.getComponent(0));
		maakLijst();
		add(new JScrollPane(contentTabel));
		revalidate();
	}

	public void herlaad()
	{
		veranderView(view);
	}
	
	public int getGeselecteerdItem()
	{
		int id = 0;
		try
		{
			id = Integer.parseInt(contentTabel.getValueAt(contentTabel.getSelectedRow(), 0).toString());
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Er is geen regel geselecteerd.", "Fout", JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}

	public int getArtikelId()
	{
		int id = 0;
		try
		{
			id = Integer.parseInt(contentTabel.getValueAt(contentTabel.getSelectedRow(), 1).toString());
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Er is geen regel geselecteerd.", "Fout", JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}
}
