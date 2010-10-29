package ipse;

import javax.swing.*;
import java.awt.event.*;

public class KnopView extends JPanel implements ActionListener
{
	private JButton bestellingKnop, artikelKnop, medewerkerKnop, klantenKnop, zoekKnop, uitlogKnop;
	
	public KnopView()
	{
		setLayout( null );
		
		bestellingKnop = new JButton( "Bestelling" );
		bestellingKnop.addActionListener( this );
		bestellingKnop.setBounds( 130, 540, 100, 30 );
		
		artikelKnop = new JButton( "Artikel" );
		artikelKnop.addActionListener( this );
		artikelKnop.setBounds( 260, 540, 100, 30 );
		
		medewerkerKnop = new JButton( "Medewerker" );
		medewerkerKnop.addActionListener( this );
		medewerkerKnop.setBounds( 390, 540, 100, 30 );
		
		klantenKnop = new JButton( "Klanten" );
		klantenKnop.addActionListener( this );
		klantenKnop.setBounds( 520, 540, 100, 30 );
		
		zoekKnop = new JButton( "Zoek" );
		zoekKnop.addActionListener( this );
		zoekKnop.setBounds( 670, 20, 100, 30 );
		
		uitlogKnop = new JButton( "Uitloggen" );
		uitlogKnop.addActionListener( this );
		
		add( bestellingKnop );
		add( artikelKnop );
		add( medewerkerKnop );
		add( klantenKnop );
		add( zoekKnop );
		add( uitlogKnop );
		
	}

	public void actionPerformed( ActionEvent e )
	{
		
	}
}
