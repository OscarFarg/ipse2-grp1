package ipse;

import javax.swing.*;
import java.awt.event.*;

public class HoofdView extends JPanel implements ActionListener
{
	public enum Views{ARTIKEL, BESTELLING, KLANT, MEDEWERKER};
	private Views view;

	private JButton bestellingKnop, artikelKnop, medewerkerKnop, klantenKnop, zoekKnop, uitlogKnop,
	nieuwKnop, bewerkKnop, verwijderKnop, meerInfoKnop;
	private JLabel logoLabel;

	View huidigeView;
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
		klView = new KlantView( database, controller );
		klView.setBounds( 110, 120, 550, 350 );

		view = Views.BESTELLING;
		huidigeView = bsView;

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
		add( huidigeView );	
	}

	public void veranderView()
	{
		switch( view )
		{
		case ARTIKEL:		remove(huidigeView);
							huidigeView = atView;
							this.add(huidigeView);
							this.validate();
							repaint();
		break;
		case BESTELLING:	remove(huidigeView);
							huidigeView = bsView;
							this.add(huidigeView);
							this.validate();
							repaint();;
		break;
		case KLANT: 		remove(huidigeView);
							huidigeView = klView;
							this.add(huidigeView);
							this.validate();
							repaint();;
		break;
		case MEDEWERKER:   	remove(huidigeView);
							huidigeView = mwView;
							this.add(huidigeView);
							this.validate();
							repaint();;
		break;
		}
	}

	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == bestellingKnop )
		{
			view = Views.BESTELLING;
			veranderView();
		}

		if( e.getSource() == artikelKnop )
		{
			view = Views.ARTIKEL;
			veranderView();
		}

		if( e.getSource() == medewerkerKnop )
		{
			view = Views.MEDEWERKER;
			veranderView();
		}

		if( e.getSource() == klantenKnop )
		{
			view = Views.KLANT;
			veranderView();
		}

		if( e.getSource() == zoekKnop)
			new Zoek();		
	}
}
