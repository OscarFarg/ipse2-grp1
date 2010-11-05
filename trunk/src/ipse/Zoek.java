package ipse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Zoek extends JFrame implements ActionListener
{
	private JRadioButton artikelRadio;
	private JRadioButton bestellingRadio;
	private JRadioButton klantRadio;
	private JRadioButton medewerkerRadio;

	private JButton zoekKnop;

	private Database database;

	private JTextField zoekVeld;
	private JComboBox kolomBox;
	private String artikelString = "artikel";
	private String bestellingString = "besteling";
	private String klantString = "klant";
	private String medewerkerString = "medewerker";

	private String zoekSegment = "artikel"; // Bepaald waarop wordt gezocht
	private String zoekKolom = "";  // Kolom waarop word gezocht

	String[] kolomStrings = {"", "", ""};
	String[] klantStrings = { "id", "voornaam", "tussenvoegsel", "achternaam", "rekeningnr", "betaal_status", "klant_status"};
	String[] medewerkerStrings = { "id", "voornaam", "tussenvoegsel", "achternaam", "functie", "chefid", "medewerker_status"};
	String[] bestellingStrings = { "bestelnr", "bestel_datum", "lever_datum", "betaal_datum", "klantid", "medewerkerid"};
	String[] artikelStrings = { "artikelid", "artikel_naam", "prijs"};



	public Zoek( Database database)
	{	
		this.database = database;
		JPanel contentPane = new JPanel();	

		JFrame venster = new JFrame();
		venster.setSize(400,320); 
		venster.setResizable(false);
		venster.setTitle("ZoekVenster");
		venster.setLocation(300,300);
		venster.setContentPane(contentPane);


		artikelRadio = new JRadioButton(artikelString);
		bestellingRadio = new JRadioButton(bestellingString);
		klantRadio = new JRadioButton(klantString);
		medewerkerRadio = new JRadioButton(medewerkerString);
		kolomBox = new JComboBox(artikelStrings );

		artikelRadio.setSelected(true);

		zoekKnop = new JButton("Zoek");

		zoekVeld = new JTextField("zoekwoord", 20);

		JPanel radiocontentPane = new JPanel(new GridLayout(0,1));
		radiocontentPane.add(artikelRadio);
		radiocontentPane.add(bestellingRadio);
		radiocontentPane.add(klantRadio);
		radiocontentPane.add(medewerkerRadio);

		contentPane.add(new JLabel( new ImageIcon("src/ipse/Images/logo.png")));
		contentPane.add(new JLabel("Kies hier uw zoekoptie:"));
		contentPane.add(radiocontentPane);
		contentPane.add(kolomBox);
		contentPane.add( zoekVeld);
		contentPane.add(zoekKnop);		


		ButtonGroup group = new ButtonGroup();
		group.add(artikelRadio);
		group.add(bestellingRadio);
		group.add(klantRadio);
		group.add(medewerkerRadio);

		artikelRadio.addActionListener(this);
		bestellingRadio.addActionListener(this);
		klantRadio.addActionListener(this);
		medewerkerRadio.addActionListener(this);
		zoekKnop.addActionListener(this);

		venster.setVisible(true);

	}

	public String getZoekSegment() 
	{
		return zoekSegment;
	}

	public String getZoekVeld()
	{
		return( zoekVeld.getText());
	}

	public String getZoekKolom() 
	{
		return zoekKolom;
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == artikelRadio )
		{
			zoekSegment = artikelString;
			kolomBox.removeAllItems();
			for(int i = 0; i < artikelStrings.length; i++)
			{
				kolomBox.addItem(artikelStrings[i]);
			}
		}

		if(e.getSource() == bestellingRadio )
		{
			zoekSegment = bestellingString;
			kolomBox.removeAllItems();
			for(int i = 0; i < bestellingStrings.length; i++)
			{
				kolomBox.addItem(bestellingStrings[i]);
			}
		}

		if(e.getSource() == klantRadio )
		{
			zoekSegment = klantString;
			kolomBox.removeAllItems();
			for(int i = 0; i < klantStrings.length; i++)
			{
				kolomBox.addItem(klantStrings[i]);
			}
		}

		if(e.getSource() == medewerkerRadio )
		{
			zoekSegment = medewerkerString;
			kolomBox.removeAllItems();
			for(int i = 0; i < medewerkerStrings.length; i++)
			{
				kolomBox.addItem(medewerkerStrings[i]);
			}
		}

		if(e.getSource() == zoekKnop )
		{
			if( zoekSegment == medewerkerString)
			{
				try
				{
					ResultSet resultSet = database.zoekArtikel(this);
					System.out.println("You are the King");
					while(resultSet.next())
					{
						System.out.println( resultSet.getInt(1));
						System.out.println("Halloo");
					}
				}
				catch(SQLException sq)
				{
					System.out.println(sq);
				}
			}

			if( zoekSegment == klantString)
			{
				System.out.println( database.zoekArtikel(this));
			}
			if( zoekSegment == artikelString)
			{
				System.out.println( database.zoekArtikel(this));
			}
			if( zoekSegment == bestellingString)
			{
				System.out.println(database.zoekBestelling(this));
			}



		}


	}

}
