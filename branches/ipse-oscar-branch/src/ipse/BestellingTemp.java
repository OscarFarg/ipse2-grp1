package ipse;

import javax.swing.*;
import java.awt.event.*;

public class BestellingTemp extends JFrame implements ActionListener
{
	private JTextField bestellingNrVeld, bestellingDatumVeld, leverDatumVeld, 
		betaalDatumVeld, klantIdVeld, medewerkerIdVeld;
	private JLabel imageLabel, bsLabel, bestellingNrLabel, bestellingDatumLabel, leverDatumLabel, 
		betaalDatumLabel, klantIdLabel, medewerkerIdLabel;
	
	private JButton voegToeKnop;
	private ImageIcon logoIcon;

	public BestellingTemp()
	{
		setSize( 400,600 );
		setLocation( 100,100 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable( false );
		
		JPanel mainPanel = new JPanel();
		setContentPane( mainPanel );
		
		mainPanel.setLayout( null );
		
		logoIcon = new ImageIcon("src/ipse/images/logo.png");
		imageLabel = new JLabel( logoIcon );
		imageLabel.setBounds( 50, 10, 300, 100 );
		
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
		
		voegToeKnop = new JButton( "Voeg Toe" );
		voegToeKnop.setBounds( 250, 340, 100, 30 );
		
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
		mainPanel.add( voegToeKnop );
		
		setVisible( true );
	}

	public void actionPerformed( ActionEvent e )
	{
		
	}
	
	public static void main(String[] args) 
	{
		new BestellingTemp();
	}

}
