package ipse;

import javax.swing.*;

public class ArtikelView extends View
{
	private JTextField artikelIdVeld, artikelNaamVeld, artikelPrijsVeld;
	private JLabel bsLabel, artikelIdLabel, artikelNaamLabel, artikelPrijsLabel;


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

	public void opslaan() 
	{
	
	}
	
	public void annuleren()
	{
		
	}
}
