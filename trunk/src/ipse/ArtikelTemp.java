package ipse;

import javax.swing.*;
import java.awt.event.*;

public class ArtikelTemp extends JFrame implements ActionListener
{
	private JTextField artikelIdVeld, artikelNaamVeld, artikelPrijsVeld;
	private JLabel imageLabel, bsLabel, artikelIdLabel, artikelNaamLabel, artikelPrijsLabel;
	private JButton voegToeKnop;
	private ImageIcon logoIcon;

	public ArtikelTemp()
	{
		setSize( 600,400 );
		setLocation( 100,100 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable( false );
		
		JPanel mainPanel = new JPanel();
		setContentPane( mainPanel );
		
		mainPanel.setLayout( null );
		
		logoIcon = new ImageIcon("src/ipse/images/logo.png");
		imageLabel = new JLabel( logoIcon );
		imageLabel.setBounds( 150, 10, 300, 100 );
		
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
		
		voegToeKnop = new JButton( "Voeg Toe" );
		voegToeKnop.setBounds( 350, 250, 100, 30 );
		
		mainPanel.add( imageLabel );
		mainPanel.add( bsLabel );
		mainPanel.add( artikelIdVeld );
		mainPanel.add( artikelNaamVeld );
		mainPanel.add( artikelPrijsVeld );
		mainPanel.add( artikelIdLabel );
		mainPanel.add( artikelNaamLabel );
		mainPanel.add( artikelPrijsLabel );
		mainPanel.add( voegToeKnop );
		
		setVisible( true );
	}

	public void actionPerformed( ActionEvent e )
	{
		
	}
	
	public static void main(String[] args) 
	{
		new ArtikelTemp();
	}

}
