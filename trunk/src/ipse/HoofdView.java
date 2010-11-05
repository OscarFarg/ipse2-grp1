package ipse;

import javax.swing.*;
import java.awt.event.*;

public class HoofdView extends JPanel implements ActionListener {
	private ViewsEnum viewEnum;

	private JButton bestellingKnop, artikelKnop, medewerkerKnop, klantenKnop,
	zoekKnop, uitlogKnop, nieuwKnop, bewerkKnop, verwijderKnop,
	meerInfoKnop, huidigeKnop;
	private JLabel logoLabel;

	private TabelPaneel tabelPaneel;
	private ArtikelView atView;

	Database database;
	Controller controller;
	
	public HoofdView( Controller controller, Database database, ArtikelView atView)
	{
		this.controller = controller;
		this.database = database;
		this.atView = atView;
	}

	public HoofdView(Controller controller, Database database) {
		this.controller = controller;
		this.database = database;
		setLayout(null);
		
		/*
		 * mwView = new MedewerkerView( database, controller );
		 * mwView.setBounds( 110, 120, 550, 350 );
		 * 
		 * atView = new ArtikelView( database, controller ); atView.setBounds(
		 * 110, 120, 550, 350 );
		 * 
		 * bsView = new BestellingView( database, controller );
		 * bsView.setBounds( 110, 120, 550, 350 );
		 * 
		 * brView = new BestelregelView(database, controller);
		 * brView.setBounds(110, 120, 550, 350);
		 * 
		 * klView = new KlantView( database, controller ); klView.setBounds(
		 * 110, 120, 550, 350 );
		 */

		viewEnum = ViewsEnum.BESTELLING;

		tabelPaneel = new TabelPaneel(database, viewEnum);
		tabelPaneel.setBounds(110, 120, 550, 350);

		logoLabel = new JLabel(new ImageIcon("src/ipse/images/logo.png"));
		logoLabel.setBounds(250, 20, 300, 100);

		bestellingKnop = new JButton("Bestelling");
		bestellingKnop.addActionListener(this);
		bestellingKnop.setBounds(170, 540, 100, 30);

		artikelKnop = new JButton("Artikel");
		artikelKnop.addActionListener(this);
		artikelKnop.setBounds(300, 540, 100, 30);

		medewerkerKnop = new JButton("Medewerker");
		medewerkerKnop.addActionListener(this);
		medewerkerKnop.setBounds(430, 540, 100, 30);

		klantenKnop = new JButton("Klanten");
		klantenKnop.addActionListener(this);
		klantenKnop.setBounds(560, 540, 100, 30);

		zoekKnop = new JButton("Zoek");
		zoekKnop.addActionListener(this);
		zoekKnop.setBounds(670, 20, 100, 30);

		uitlogKnop = new JButton("Uitloggen");
		uitlogKnop.addActionListener(this);
		uitlogKnop.setBounds(30, 20, 100, 30);

		nieuwKnop = new JButton("Nieuw");
		nieuwKnop.addActionListener(this);
		nieuwKnop.setBounds(10, 210, 100, 30);

		bewerkKnop = new JButton("Bewerk");
		bewerkKnop.addActionListener(this);
		bewerkKnop.setBounds(10, 260, 100, 30);

		verwijderKnop = new JButton("Verwijder");
		verwijderKnop.addActionListener(this);
		verwijderKnop.setBounds(10, 310, 100, 30);

		meerInfoKnop = new JButton("Meer Info");
		meerInfoKnop.addActionListener(this);
		meerInfoKnop.setBounds(10, 360, 100, 30);

		huidigeKnop = nieuwKnop;

		add(logoLabel);
		add(bestellingKnop);
		add(artikelKnop);
		add(medewerkerKnop);
		add(klantenKnop);
		add(zoekKnop);
		add(uitlogKnop);
		add(nieuwKnop);
		add(bewerkKnop);
		add(verwijderKnop);
		add(meerInfoKnop);
		add(tabelPaneel);
		// add( huidigeView );
	}

	public void verwijderObject() 
	{
		switch (viewEnum) 
		{
		case BESTELLING:
			database.deleteBestelling(tabelPaneel.getGeselecteerdItem());
			tabelPaneel.herlaad();
			break;
		case ARTIKEL:
			database.deleteArtikel(tabelPaneel.getGeselecteerdItem());
			tabelPaneel.herlaad();
			break;
		case MEDEWERKER:
			database.deleteMedewerker(tabelPaneel.getGeselecteerdItem());
			tabelPaneel.herlaad();
			break;
		case KLANT:
			database.deleteKlant(tabelPaneel.getGeselecteerdItem());
			tabelPaneel.herlaad();
			break;
		}
	}

	public void bewerkObject()
	{
		switch (viewEnum) 
		{
		case BESTELLING:
			database.selectBestelling(tabelPaneel.getGeselecteerdItem());	
			break;
		case ARTIKEL:
			new ArtikelView(database, controller, tabelPaneel.getGeselecteerdItem());
			break;
		case MEDEWERKER:
			new MedewerkerView(database, controller, tabelPaneel.getGeselecteerdItem());
			break;
		case KLANT:
			new KlantView(database, controller, tabelPaneel.getGeselecteerdItem());
			break;
		}
	}

	public void voegObjectToe()
	{
		switch (viewEnum) 
		{
		case BESTELLING:
			new BestellingView(database, controller);
			break;
		case ARTIKEL:
			new ArtikelView(database, controller);
			break;
		case MEDEWERKER:
			new MedewerkerView( database, controller );
			break;
		case KLANT:
			new KlantView( database, controller );
			break;
		}
	}


	public void actionPerformed(ActionEvent e) 
	{
		//Switchen tussen de views

		if (e.getSource() == bestellingKnop) 
		{
			viewEnum = ViewsEnum.BESTELLING;
			tabelPaneel.veranderView(viewEnum);
		}

		if (e.getSource() == artikelKnop) 
		{
			viewEnum = ViewsEnum.ARTIKEL;
			tabelPaneel.veranderView(viewEnum);
		}

		if (e.getSource() == medewerkerKnop) 
		{
			viewEnum = ViewsEnum.MEDEWERKER;
			tabelPaneel.veranderView(viewEnum);
		}

		if (e.getSource() == klantenKnop) 
		{
			viewEnum = ViewsEnum.KLANT;
			tabelPaneel.veranderView(viewEnum);
		}

		// Data aanpassen 

		if( e.getSource() == nieuwKnop )
		{
			voegObjectToe();
		}
		
		if( e.getSource() == bewerkKnop )
		{
			bewerkObject();
		}

		if (e.getSource() == zoekKnop)
			new Zoek(database);

		if (e.getSource() == verwijderKnop) 
		{	
			int n = JOptionPane.showConfirmDialog(null, "Weet u het zeker?",
					"Verwijderen", JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				verwijderObject();
			} else {

			}
		}
	}
}
