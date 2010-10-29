package ipse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame implements ActionListener
{
	private JButton okKnop, annulerenKnop, vergetenKnop;
	private JTextField inlogNaamVeld, wachtWoordVeld;
	private JLabel imageLabel;
	
	public LoginView()
	{
		setLayout( null );
		
		setSize( 800,600 );
		setLocation( 100,100 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setTitle( "BestelSnel Login" );
		
		inlogNaamVeld = new JTextField( 10 );
		wachtWoordVeld = new JTextField( 10 );
		
		okKnop = new JButton( "OK" );
		okKnop.addActionListener( this );
		
		annulerenKnop = new JButton( "Annuleren" );
		annulerenKnop.addActionListener( this );
		
		vergetenKnop = new JButton( "Wachtwoord vergeten?" );
		vergetenKnop.addActionListener( this );
	
		imageLabel = new JLabel( new ImageIcon( "/ipse2/src/ipse/Images/logo.png" ) );
		imageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		imageLabel.setBounds( 10, 10, 200, 100 );
		
		add( okKnop );
		add( annulerenKnop );
		add( vergetenKnop );
		add( inlogNaamVeld );
		add( wachtWoordVeld );
		add( imageLabel );
		
		setVisible( true );
	}
	
	public void actionPerformed( ActionEvent e )
	{
		
	}
}
