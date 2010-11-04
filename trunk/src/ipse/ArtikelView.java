package ipse;

import javax.swing.*;

public class ArtikelView extends View
{
	private JTextField artikelIdVeld, artikelNaamVeld, artikelPrijsVeld;
	private JLabel bsLabel, artikelIdLabel, artikelNaamLabel, artikelPrijsLabel;

	Artikel artikel;

	public ArtikelView( Database database, Controller controller )
	{
		super( database, controller );

		bsLabel = new JLabel( "Voeg hier onder een nieuw artikel toe" );
		bsLabel.setBounds( 185, 120, 250, 20 );

		artikelIdLabel = new JLabel( "Artikel ID" );
		artikelIdLabel.setBounds( 150, 160, 100, 20 );

		artikelIdVeld = new JTextField( 5 );
		artikelIdVeld.setBounds( 250, 160, 200, 20 );
		artikelIdVeld.setEditable( false );

		artikelNaamLabel = new JLabel( "Artikel Naam" );
		artikelNaamLabel.setBounds( 150, 190, 100, 20 );

		artikelNaamVeld = new JTextField( 20 );
		artikelNaamVeld.setBounds( 250, 190, 200, 20 );

		artikelPrijsLabel = new JLabel( "Artikel Prijs" );
		artikelPrijsLabel.setBounds( 150, 220, 100, 20 );

		artikelPrijsVeld = new JTextField( 5 );
		artikelPrijsVeld.setBounds( 250, 220, 200, 20 );

		mainPanel.add( bsLabel );
		mainPanel.add( artikelIdVeld );
		mainPanel.add( artikelNaamVeld );
		mainPanel.add( artikelPrijsVeld );
		mainPanel.add( artikelIdLabel );
		mainPanel.add( artikelNaamLabel );
		mainPanel.add( artikelPrijsLabel );


		setVisible( true );
	}

	public ArtikelView( Database database, Controller controller, int artikelid )
	{
		this(database, controller);
		updateMode = true;
		
		Artikel artikel = database.selectArtikel(artikelid);
		
		int id = artikel.getArtikelid();
		String idNaam = Integer.toString(id);
		String naam = artikel.getArtikelnaam();
		double prijs = artikel.getPrijs();
		String prijsNaam = Double.toString(prijs);

		artikelIdVeld.setText(idNaam);
		artikelNaamVeld.setText(naam);
		artikelPrijsVeld.setText(prijsNaam);
	}

	public void vulWaardesIn()
	{
		int id = artikel.getArtikelid();
		String idNaam = Integer.toString(id);
		String naam = artikel.getArtikelnaam();
		double prijs = artikel.getPrijs();
		String prijsNaam = Double.toString(prijs);

		artikelIdVeld.setText(idNaam);
		artikelNaamVeld.setText(naam);
		artikelPrijsVeld.setText(prijsNaam);
	}

	public void opslaan() 
	{
		if( artikelIdVeld.getText().equals("") )
		{
			String id = artikelIdVeld.getText();
			int idNr = Integer.parseInt(id);
			String naam = artikelNaamVeld.getText();
			double prijs = Double.parseDouble( artikelPrijsVeld.getText() );
			Artikel artikel = new Artikel( idNr, naam, prijs );

			if( naam.length() != 0 && prijs >= 0.0 )
			{
				if( naam.length() != 0 && prijs >= 0.0 )
				{
					int n = JOptionPane.showConfirmDialog(null, "Artikel is toegevoegd!, Wilt u er nog een toevoegen?",
							"Toevoegen", JOptionPane.YES_NO_OPTION);
					if (n == 0) 
					{
						artikelNaamVeld.setText("");
						artikelPrijsVeld.setText("");
					} 
					else {
						this.dispose();
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "De ingevoerde waarden kloppen niet. Controleer of alles is ingevuld en of de prijs hoger is dan 0.0", 
						"Fout", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {}
		
		if( updateMode )
		{
			database.updateArtikel(artikel);
		}
		else
		{
			database.insertArtikel(artikel);
		}
	}
}
