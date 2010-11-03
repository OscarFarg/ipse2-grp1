package ipse;

import javax.swing.*;
import java.awt.event.*;

public class HoofdView extends JPanel implements ActionListener
{
	public enum ViewsEnum{ARTIKEL, BESTELLING, KLANT, MEDEWERKER, BESTELREGEL};
	private ViewsEnum viewEnum;
	public enum VerwijderEnum{VWARTIKEL, VWBESTELLING, VWKLANT, VWMEDEWERKER, VWBESTELREGEL};
	private VerwijderEnum vwEnum;

	private JButton bestellingKnop, artikelKnop, medewerkerKnop, klantenKnop, zoekKnop, uitlogKnop,
	nieuwKnop, bewerkKnop, verwijderKnop, meerInfoKnop;
	private JLabel logoLabel;

	View huidigeView;
	MedewerkerView mwView;
	ArtikelView atView;
	BestellingView bsView;
	BestelregelView brView;
	KlantView klView;

	Database database;
	Controller controller;

	public HoofdView( Controller controller, Database database)
	{
		this.controller = controller;
		this.database = database;
		setLayout( null );

		mwView = new MedewerkerView( database, controller );
		mwView.setBounds( 110, 120, 550, 350 );
		atView = new ArtikelView( database, controller );
		atView.setBounds( 110, 120, 550, 350 );
		bsView = new BestellingView( database, controller );
		bsView.setBounds( 110, 120, 550, 350 );
		brView = new BestelregelView(database, controller);
		brView.setBounds(110, 120, 550, 350);
		klView = new KlantView( database, controller );
		klView.setBounds( 110, 120, 550, 350 );

		viewEnum = ViewsEnum.BESTELLING;
		vwEnum = VerwijderEnum.VWBESTELLING;
		
		huidigeView = bsView;

		logoLabel = new JLabel( new ImageIcon("src/ipse/images/logo.png") );
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
		switch( viewEnum )
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
								repaint();
			break;
			case KLANT: 		remove(huidigeView);
								huidigeView = klView;
								this.add(huidigeView);
								this.validate();
								repaint();
			break;
			case MEDEWERKER:   	remove(huidigeView);
								huidigeView = mwView;
								this.add(huidigeView);
								this.validate();
								repaint();
			break;
			case BESTELREGEL:	remove(huidigeView);
								huidigeView = brView;
								this.add(huidigeView);
								this.validate();
								repaint();
			break;
		}
	}
	
	public void verwijderObject()
	{
		switch( vwEnum )
		{
			case VWBESTELLING:	database.deleteBestelling(Integer.parseInt(bsView.getGeselecteerdeBestelling()));
								bsView.maakLijst();
			break;
			case VWBESTELREGEL:	database.deleteBestelregel(Integer.parseInt(brView.getGeselecteerdeRegelBestelnr()), 
									Integer.parseInt(brView.getGeselecteerdeRegelArtikelid()));
								brView.maakLijst();
			break;
			case VWARTIKEL:		database.deleteArtikel(Integer.parseInt(atView.getGeselecteerdeBestelling()));
								atView.maakLijst();
			break;
			case VWMEDEWERKER:	database.deleteMedewerker(Integer.parseInt(mwView.getGeselecteerdeMedewerker()));
								atView.maakLijst();
			break;
			case VWKLANT:		database.deleteKlant(Integer.parseInt(klView.getGeselecteerdeKlant()));
								klView.maakLijst();
			break;
		}
	}

	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == bestellingKnop )
		{
			viewEnum = ViewsEnum.BESTELLING;
			vwEnum = VerwijderEnum.VWBESTELLING;
			veranderView();
		}

		if( e.getSource() == artikelKnop )
		{
			viewEnum = ViewsEnum.ARTIKEL;
			vwEnum = VerwijderEnum.VWARTIKEL;
			veranderView();
		}

		if( e.getSource() == medewerkerKnop )
		{
			viewEnum = ViewsEnum.MEDEWERKER;
			vwEnum = VerwijderEnum.VWMEDEWERKER;
			veranderView();
		}

		if( e.getSource() == klantenKnop )
		{
			viewEnum = ViewsEnum.KLANT;
			vwEnum = VerwijderEnum.VWKLANT;
			veranderView();
		}

		if( e.getSource() == zoekKnop)
			new Zoek();
		if (e.getSource() == verwijderKnop)
		{			
			int n = JOptionPane.showConfirmDialog(null, "Weet u het zeker?", "Verwijderen", JOptionPane.YES_NO_OPTION);
			if (n == 0)
			{
				verwijderObject();
			}
			else
			{
				
			}
		}
	}
}
