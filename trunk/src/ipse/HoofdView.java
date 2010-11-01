package ipse;

import javax.swing.*;
import java.awt.event.*;

public class HoofdView extends JPanel implements ActionListener
{
	public enum Views{ARTIKEL, BESTELLING, KLANT, MEDWEWERKER};
	private Views huidigeView;
	
	private JButton bestellingKnop, artikelKnop, medewerkerKnop, klantenKnop, zoekKnop, uitlogKnop,
					nieuwKnop, bewerkKnop, verwijderKnop, meerInfoKnop;
	private JLabel logoLabel;
	
	MedewerkerView mwView;
	ArtikelView atView;
	BestellingView bsView;
	KlantView klView;
	
	Database database;
	Controller controller;
	
	public HoofdView( Controller controller )
	{
		this.controller = controller;
		database = new Database();
		
		setLayout( null );
		
		mwView = new MedewerkerView( database, controller );
		mwView.setBounds( 110, 120, 550, 350 );
		atView = new ArtikelView( database, controller );
		atView.setBounds( 110, 120, 550, 350 );
		bsView = new BestellingView( database, controller );
		bsView.setBounds( 110, 120, 550, 350 );
		//klView = new KlantView( database, controller );
		//klView.setBounds( 110, 120, 550, 350 );
		
		logoLabel = new JLabel( new ImageIcon("src/ipse/Images/logo.png") );
		logoLabel.setBounds( 250, 20, 300, 100 );
		
		bestellingKnop = new JButton( "Bestelling" );
		bestellingKnop.addActionListener( this );
		bestellingKnop.setBounds( 170, 540, 100, 30 );
		
		artikelKnop = new JButton( "Artikel" );
		artikelKnop.addActionListener( this );
		artikelKnop.setBounds( 300, 540, 100, 30 );
		
		medewerkerKnop = new JButton( "Medewerker" );
		medewerkerKnop.addActionListener( this );
		medewerkerKnop.setBounds( 430, 540, 100, 30 );
		
		klantenKnop = new JButton( "Klanten" );
		klantenKnop.addActionListener( this );
		klantenKnop.setBounds( 560, 540, 100, 30 );
		
		zoekKnop = new JButton( "Zoek" );
		zoekKnop.addActionListener( this );
		zoekKnop.setBounds( 670, 20, 100, 30 );
		
		uitlogKnop = new JButton( "Uitloggen" );
		uitlogKnop.addActionListener( this );
		uitlogKnop.setBounds( 30, 20, 100, 30 );
		
		nieuwKnop = new JButton( "Nieuw" );
		nieuwKnop.addActionListener( this );
		nieuwKnop.setBounds( 10, 210, 100, 30 );
		
		bewerkKnop = new JButton( "Bewerk" );
		bewerkKnop.addActionListener( this );
		bewerkKnop.setBounds( 10, 260, 100, 30 );
		
		verwijderKnop = new JButton( "Verwijder" );
		verwijderKnop.addActionListener( this );
		verwijderKnop.setBounds( 10, 310, 100, 30 );
		
		meerInfoKnop = new JButton( "Meer Info" );
		meerInfoKnop.addActionListener( this );
		meerInfoKnop.setBounds( 10, 360, 100, 30 );
		
		add( logoLabel );
		add( bestellingKnop );
		add( artikelKnop );
		add( medewerkerKnop );
		add( klantenKnop );
		add( zoekKnop );
		add( uitlogKnop );
		add( nieuwKnop );
		add( bewerkKnop );
		add( verwijderKnop );
		add( meerInfoKnop );
		add( bsView );	
	}

	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == artikelKnop )
		{
			removeAll();
			add(atView);
			repaint();
		}
		
		if( e.getSource() == zoekKnop)
		{		new Zoek();		}
	}
}
