package ipse;

import javax.swing.*;

public class BestellingView extends View
{
	private JTextField bestellingNrVeld, bestellingDatumVeld, leverDatumVeld, 
	betaalDatumVeld, klantIdVeld, medewerkerIdVeld;
	private JLabel imageLabel, bsLabel, bestellingNrLabel, bestellingDatumLabel, leverDatumLabel, 
	betaalDatumLabel, klantIdLabel, medewerkerIdLabel;


	public BestellingView( Database database, Controller controller )
	{
		super( database, controller );

		bsLabel = new JLabel( "Voeg hier onder een nieuwe Bestelling toe" );
		bsLabel.setBounds( 50, 120, 300, 20 );

		bestellingNrLabel = new JLabel( "Bestel Nummer" );
		bestellingNrLabel.setBounds( 50, 160, 100, 20 );

		bestellingNrVeld = new JTextField( 5 );
		bestellingNrVeld.setBounds( 150, 160, 200, 20 );
		bestellingNrVeld.setEditable( false );

		bestellingDatumLabel = new JLabel( "Bestel Datum" );
		bestellingDatumLabel.setBounds( 50, 190, 100, 20 );

		bestellingDatumVeld = new JTextField( 20 );
		bestellingDatumVeld.setBounds( 150, 190, 200, 20 );

		leverDatumLabel = new JLabel( "Lever Datum" );
		leverDatumLabel.setBounds( 50, 220, 100, 20 );

		leverDatumVeld = new JTextField( 10 );
		leverDatumVeld.setBounds( 150, 220, 200, 20 );

		betaalDatumLabel = new JLabel( "Betaal Datum" );
		betaalDatumLabel.setBounds( 50, 250, 100, 20 );

		betaalDatumVeld = new JTextField( 10 );
		betaalDatumVeld.setBounds( 150, 250, 200, 20 );

		klantIdLabel = new JLabel( "Klant ID" );
		klantIdLabel.setBounds( 50, 280, 100, 20 );

		klantIdVeld = new JTextField( 5 );
		klantIdVeld.setBounds( 150, 280, 200, 20 );

		medewerkerIdLabel = new JLabel( "Medewerker ID" );
		medewerkerIdLabel.setBounds( 50, 310, 100, 20 );

		medewerkerIdVeld = new JTextField( 5 );
		medewerkerIdVeld.setBounds( 150, 310, 200, 20 );

		mainPanel.add( imageLabel );
		mainPanel.add( bsLabel );
		mainPanel.add( bestellingNrVeld );
		mainPanel.add( bestellingDatumVeld );
		mainPanel.add( leverDatumVeld );
		mainPanel.add( betaalDatumVeld );
		mainPanel.add( klantIdVeld );
		mainPanel.add( medewerkerIdVeld );
		mainPanel.add( bestellingNrLabel );
		mainPanel.add( bestellingDatumLabel );
		mainPanel.add( leverDatumLabel );
		mainPanel.add( betaalDatumLabel );
		mainPanel.add( klantIdLabel );
		mainPanel.add( medewerkerIdLabel );

		setVisible( true );
	}
	
	public void opslaan()
	{
		
	}
	
	public void annuleren()
	{
		
	}
}
