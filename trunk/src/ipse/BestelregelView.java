package ipse;

import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class BestelregelView extends View
{
	private JTextField bestelnrVeld, prijsVeld, aantalVeld, totaalVeld;
	private JComboBox artikelBox;
	private ResultSet artikelSet;
	private ArrayList<Artikel> artikelLijst;

	public BestelregelView(Database database, Controller controller, int bestelling)
	{
		super(database, controller);

		bestelnrVeld = new JTextField(5);
		bestelnrVeld.setText(bestelling + "");
		bestelnrVeld.setEditable(false);
		bestelnrVeld.setBounds( 150, 110, 100, 20 );
		JLabel bestelnrLabel = new JLabel("Bestelnummer");
		bestelnrLabel.setBounds(40, 110, 120, 20);

		artikelBox = new JComboBox();
		artikelBox.setBounds(150, 135, 150, 20);
		artikelBox.addActionListener(this);
		JLabel artikelLabel = new JLabel("Artikel");
		artikelLabel.setBounds(40, 135, 120, 20);

		prijsVeld = new JTextField(10);
		prijsVeld.setBounds(150, 160, 150, 20);
		JLabel prijsLabel = new JLabel("Prijs p.s.");
		prijsLabel.setBounds(40, 160, 120, 20);

		aantalVeld = new JTextField(15);
		aantalVeld.setBounds(150, 185, 150, 20);
		aantalVeld.addActionListener(this);
		JLabel aantalLabel = new JLabel("Aantal");
		aantalLabel.setBounds(40, 185, 120, 20);

		totaalVeld = new JTextField(15);
		totaalVeld.setBounds(150, 210, 150, 20);
		JLabel totaalLabel = new JLabel("Totaal prijs");
		totaalLabel.setBounds(40, 210, 120, 20);

		vulBox();

		mainPanel.add(bestelnrLabel);
		mainPanel.add(bestelnrVeld);
		mainPanel.add(artikelLabel);
		mainPanel.add(artikelBox);
		mainPanel.add(prijsLabel);
		mainPanel.add(prijsVeld);
		mainPanel.add(aantalLabel);
		mainPanel.add(aantalVeld);
		mainPanel.add(totaalLabel);
		mainPanel.add(totaalVeld);

		this.setVisible(true);
	}

	public BestelregelView(Database database, Controller controller, int bestelling, int artikel)
	{
		this(database, controller, bestelling);
		updateMode = true;
		Bestelregel bestelregel = database.selectBestelregel(bestelling, artikel);
		vulBox();
		bestelnrVeld.setText(bestelregel.getBestelnr() + "");
		for (int i = 0; i < artikelLijst.size(); i ++)
		{
			Artikel temp = (Artikel) artikelLijst.get(i);
			if (temp.getArtikelid() == artikel)
			{
				artikelBox.setSelectedItem(temp.getArtikelid() + ", " + temp.getArtikelnaam());
			}
		}
		prijsVeld.setText(bestelregel.getPrijs() + "");
		aantalVeld.setText(bestelregel.getAantal() + "");
		totaalVeld.setText(bestelregel.getTotaalPrijs() + "");
	}

	public void vulBox()
	{
		artikelLijst = new ArrayList<Artikel>();
		try
		{
			artikelSet = database.getArtikelen();
			while(artikelSet.next())
			{
				Artikel a = new Artikel(artikelSet.getInt(1), artikelSet.getString(2), artikelSet.getDouble(3));
				artikelLijst.add(a);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}

		for (int i = 0; i < artikelLijst.size(); i ++)
		{
			Artikel temp = (Artikel) artikelLijst.get(i);
			artikelBox.addItem(temp.getArtikelid() + ", " + temp.getArtikelnaam());
		}
	}

	public void opslaan()
	{
		try
		{
			int bestelnr = Integer.parseInt(bestelnrVeld.getText());
			String selected = (String) artikelBox.getSelectedItem();
			Scanner s = new Scanner(selected).useDelimiter("\\,");
			int artikelid = Integer.parseInt(s.next());
			double prijs = Double.parseDouble(prijsVeld.getText());
			int aantal = Integer.parseInt(aantalVeld.getText());
			double totaalprijs = Double.parseDouble(totaalVeld.getText());
			Bestelregel bestelregel = new Bestelregel(bestelnr, artikelid, prijs, aantal, totaalprijs);
			if (updateMode)
			{
				System.out.println("update");
				database.updateBestelregel(bestelregel);
			}
			else
			{
				System.out.println("insert");
				database.insertBestelregel(bestelregel);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		finally
		{
			super.opslaan();
		}
	}

	public void prijsInstellen(int id)
	{
		for (int i = 0; i < artikelLijst.size(); i ++)
		{
			Artikel a = (Artikel) artikelLijst.get(i);
			if (id == a.getArtikelid())
			{
				try
				{
					prijsVeld.setText("" + a.getPrijs());
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		super.actionPerformed(ae);

		if (ae.getSource() == artikelBox)
		{
			String selected = (String) artikelBox.getSelectedItem();
			Scanner s = new Scanner(selected).useDelimiter("\\,");
			int id = Integer.parseInt(s.next());
			prijsInstellen(id);
		}
		if (ae.getSource() == aantalVeld)
		{
			try
			{	
				int aantal = Integer.parseInt(aantalVeld.getText());
				double totaalprijs = aantal * Double.parseDouble(prijsVeld.getText());
				BigDecimal bd = new BigDecimal(totaalprijs);
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				totaalprijs = bd.doubleValue();
				totaalVeld.setText("" + totaalprijs);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
