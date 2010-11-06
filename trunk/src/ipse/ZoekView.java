package ipse;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ZoekView extends JFrame implements ActionListener
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
	private ButtonGroup group;

	String[] kolomStrings = {"", "", ""};
	String[] klantStrings = { "id", "voornaam", "tussenvoegsel", "achternaam", "rekeningnr", "betaal_status", "klant_status"};
	String[] medewerkerStrings = { "id", "voornaam", "tussenvoegsel", "achternaam", "functie", "chefid", "medewerker_status"};
	String[] bestellingStrings = { "bestelnr", "bestel_datum", "lever_datum", "betaal_datum", "klantid", "medewerkerid"};
	String[] artikelStrings = { "artikelid", "artikel_naam", "prijs"};

	private String tabelNaam = artikelString;

	public ZoekView( Database database)
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
		contentPane.add(zoekVeld);
		contentPane.add(zoekKnop);		


		group = new ButtonGroup();
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == artikelRadio )
		{
			tabelNaam = artikelString;
			kolomBox.removeAllItems();
			for(int i = 0; i < artikelStrings.length; i++)
			{
				kolomBox.addItem(artikelStrings[i]);
			}
		}

		if(e.getSource() == bestellingRadio )
		{
			tabelNaam = bestellingString;
			kolomBox.removeAllItems();
			for(int i = 0; i < bestellingStrings.length; i++)
			{
				kolomBox.addItem(bestellingStrings[i]);
			}
		}

		if(e.getSource() == klantRadio )
		{
			tabelNaam = klantString;
			kolomBox.removeAllItems();
			for(int i = 0; i < klantStrings.length; i++)
			{
				kolomBox.addItem(klantStrings[i]);
			}
		}

		if(e.getSource() == medewerkerRadio )
		{
			tabelNaam = medewerkerString;
			kolomBox.removeAllItems();
			for(int i = 0; i < medewerkerStrings.length; i++)
			{
				kolomBox.addItem(medewerkerStrings[i]);
			}
		}
		
		if (e.getSource() == zoekKnop)
		{
			System.out.println("Zoeken naar: " + kolomBox.getSelectedItem() + " in " + tabelNaam);
			database.zoeken(tabelNaam, kolomBox.getSelectedItem().toString(), zoekVeld.getText());
		}
	}
}
