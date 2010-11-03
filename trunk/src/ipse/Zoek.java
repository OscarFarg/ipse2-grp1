package ipse;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Zoek extends JFrame implements ActionListener
{
	private JRadioButton artikelRadio;
	private JRadioButton bestellingRadio;
	private JRadioButton bestelregelRadio;
	private JRadioButton klantRadio;
	private JRadioButton medewerkerRadio;
	
	private JButton zoekKnop;
	
	private JTextField zoekVeld;
	
	private String artikelString = "artikel";
	private String bestellingString = "besteling";
	private String bestelregelString = "bestelregel";
	private String klantString = "klant";
	private String medewerkerString = "medewerker";
	
	private String zoekSegment = "artikel"; // Bepaald waarop wordt gezocht
	private String zoekKolom = "";  // Kolom waarop word gezocht
	

	public Zoek()
	{	
		JPanel contentPane = new JPanel();	
			
		JFrame venster = new JFrame();
		venster.setSize(450,320); 
		venster.setResizable(false);
		venster.setTitle("ZoekVenster");
		venster.setLocation(300,300);
		venster.setContentPane(contentPane);
		
		
		artikelRadio = new JRadioButton(artikelString);
		bestellingRadio = new JRadioButton(bestellingString);
		bestelregelRadio = new JRadioButton(bestelregelString);
		klantRadio = new JRadioButton(klantString);
		medewerkerRadio = new JRadioButton(medewerkerString);
		
		artikelRadio.setSelected(true);
		
		zoekKnop = new JButton("Zoek");
		
		zoekVeld = new JTextField("zoekwoord", 20);
	
		JPanel radiocontentPane = new JPanel(new GridLayout(0,1));
		radiocontentPane.add(artikelRadio);
		radiocontentPane.add(bestellingRadio);
		radiocontentPane.add(bestelregelRadio);
		radiocontentPane.add(klantRadio);
		radiocontentPane.add(medewerkerRadio);
		
		contentPane.add(new JLabel( new ImageIcon("src/ipse/Images/logo.png")));
		contentPane.add(new JLabel("Kies hier uw zoekoptie:"));
		contentPane.add(radiocontentPane);
		contentPane.add( zoekVeld);
		contentPane.add(zoekKnop);		
		
	
		ButtonGroup group = new ButtonGroup();
		group.add(artikelRadio);
		group.add(bestellingRadio);
		group.add(bestelregelRadio);
		group.add(klantRadio);
		group.add(medewerkerRadio);
		
		artikelRadio.addActionListener(this);
		bestellingRadio.addActionListener(this);
		bestelregelRadio.addActionListener(this);
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

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == artikelRadio )
		{
			zoekSegment = artikelString;
		}
		
		if(e.getSource() == bestellingRadio )
		{
			zoekSegment = bestellingString;
		}
		
		if(e.getSource() == bestelregelRadio )
		{
			zoekSegment = bestelregelString;
		}
		
		if(e.getSource() == klantRadio )
		{
			zoekSegment = klantString;
		}
		
		if(e.getSource() == medewerkerRadio )
		{
			zoekSegment = medewerkerString;
		}
		
		if(e.getSource() == zoekKnop )
		{
			// Hier moet Query of methode komen uit andere klasse voor het doorzoeken van de database
			
		}
		
		
	}
	
}
