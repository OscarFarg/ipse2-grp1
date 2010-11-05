package ipse;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.DateFormatter;

public class BestellingView extends View
{
	private JTextField bestellingNrVeld, bestellingDatumVeld, leverDatumVeld, 
	betaalDatumVeld;
	private JLabel bsLabel, bestellingNrLabel, bestellingDatumLabel, leverDatumLabel, 
	betaalDatumLabel, klantIdLabel, medewerkerIdLabel;
	private JComboBox klantBox, medewerkerBox;

	private int klantId, mwId;

	public BestellingView( Database database, Controller controller )
	{
		super( database, controller );

		bsLabel = new JLabel( "Voeg hier onder een nieuwe Bestelling toe" );
		bsLabel.setBounds( 50, 120, 300, 20 );

		bestellingNrLabel = new JLabel( "Bestel Nummer" );
		bestellingNrLabel.setBounds( 50, 160, 100, 20 );

		bestellingNrVeld = new JTextField( 5 );
		bestellingNrVeld.setBounds( 150, 160, 200, 20 );
		bestellingNrVeld.setEditable( false );

		bestellingDatumLabel = new JLabel( "Bestel Datum" );
		bestellingDatumLabel.setBounds( 50, 190, 100, 20 );

		bestellingDatumVeld = new JTextField( 20 );
		bestellingDatumVeld.setBounds( 150, 190, 200, 20 );

		leverDatumLabel = new JLabel( "Lever Datum" );
		leverDatumLabel.setBounds( 50, 220, 100, 20 );

		leverDatumVeld = new JTextField( 10 );
		leverDatumVeld.setBounds( 150, 220, 200, 20 );

		betaalDatumLabel = new JLabel( "Betaal Datum" );
		betaalDatumLabel.setBounds( 50, 250, 100, 20 );

		betaalDatumVeld = new JTextField( 10 );
		betaalDatumVeld.setBounds( 150, 250, 200, 20 );

		klantIdLabel = new JLabel( "Klant ID" );
		klantIdLabel.setBounds( 50, 280, 100, 20 );

		klantBox = new JComboBox();
		try
		{
			ResultSet klantSet = database.selectKlantBestelling();
			while(klantSet.next())
			{
				klantBox.addItem(klantSet.getString(1) + ", " + klantSet.getString(2));
			}
		}
		catch( SQLException se )
		{
			System.out.println( se );
		}

		klantBox.setBounds( 150, 280, 200, 20 );
		klantBox.addActionListener(this);

		medewerkerIdLabel = new JLabel( "Medewerker ID" );
		medewerkerIdLabel.setBounds( 50, 310, 100, 20 );

		medewerkerBox = new JComboBox();

		try
		{
			ResultSet medewerkerSet = database.selectMedewerkerBestelling();
			while(medewerkerSet.next())
			{
				medewerkerBox.addItem(medewerkerSet.getString(1) + ", " + medewerkerSet.getString(2) );
			}
		}
		catch( SQLException se )
		{
			System.out.println( se );
		}

		medewerkerBox.setBounds( 150, 310, 200, 20 );
		medewerkerBox.addActionListener(this);



		mainPanel.add( bsLabel );
		mainPanel.add( bestellingNrVeld );
		mainPanel.add( bestellingDatumVeld );
		mainPanel.add( leverDatumVeld );
		mainPanel.add( betaalDatumVeld );
		mainPanel.add( bestellingNrLabel );
		mainPanel.add( bestellingDatumLabel );
		mainPanel.add( leverDatumLabel );
		mainPanel.add( betaalDatumLabel );
		mainPanel.add( klantIdLabel );
		mainPanel.add( medewerkerIdLabel );
		mainPanel.add(klantBox);
		mainPanel.add(medewerkerBox);

		setVisible( true );
	}

	public BestellingView(Database database, Controller controller, int id)
	{
		this(database, controller);
		updateMode = true;
		Bestelling bestelling = database.selectBestelling(id);
		bestellingNrVeld.setText(bestelling.getBestelnr() + "");
		bestellingDatumVeld.setText(bestelling.getBestelDatum().toString());
		if (bestelling.getLeverDatum() != null)
			leverDatumVeld.setText(bestelling.getLeverDatum().toString() + "");
		//Bepalen welke item er in de combobox geselecteerd moet worden.
		for (int i = 0; i < klantBox.getItemCount(); i++)
		{
			String klant = (String) klantBox.getItemAt(i);
			if (klant.substring(0, 4).equals(String.valueOf(bestelling.getKlantid())))
			{
				klantBox.setSelectedIndex(i);
			}

		}

		for (int i = 0; i < medewerkerBox.getItemCount(); i++)
		{
			String klant = (String) medewerkerBox.getItemAt(i);
			if (klant.split(",")[0].equals(String.valueOf(bestelling.getMedewerkerid())))
			{
				medewerkerBox.setSelectedIndex(i);
			}

		}
	}

	public void opslaan()
	{
		try
		{
			System.out.println("Opslaanknop ingedrukt.");
			String id = bestellingNrVeld.getText();
			int bestelNr = 0;
			try
			{
				bestelNr = Integer.parseInt(id);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}

			Date bestelDate = null;
			Date leverDate = null;
			Date betaalDate = null;
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-mm-dd" );

			//Besteldatum
			try 
			{ bestelDate = format.parse(bestellingDatumVeld.getText());}
			catch (Exception e) 
			{ System.out.println("Besteldatum: " + e.getMessage());}

			//leverdatum
			try 
			{ leverDate = format.parse(leverDatumVeld.getText());} 
			catch (Exception e) 
			{ System.out.println("Leverdatum: " + e.getMessage());}

			//betaaldatum
			try 
			{ betaalDate = format.parse(betaalDatumVeld.getText());} 
			catch (Exception e) 
			{ System.out.println("Betaaldatum: " + e.getMessage());}

			Bestelling bestelling = new Bestelling(bestelNr, bestelDate, leverDate, betaalDate, klantId, mwId );

			if (updateMode)
			{
				System.out.println("update");
				database.updateBestelling(bestelling);
			}
			else
			{
				System.out.println("insert");
				database.insertBestelling(bestelling);
			}
		}
		catch( Exception e )
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		super.actionPerformed(ae);
		if( ae.getSource() == klantBox )
		{
			String klSelected = (String) klantBox.getSelectedItem();
			Scanner kl = new Scanner(klSelected).useDelimiter("\\,");
			klantId = kl.nextInt();
		}

		if( ae.getSource() == medewerkerBox )
		{
			String mwSelected = (String) medewerkerBox.getSelectedItem();
			Scanner mw = new Scanner(mwSelected).useDelimiter("\\,");
			mwId = mw.nextInt();
		}

	}

}
