package ipse;

import javax.swing.*;
import java.awt.event.*;

public class LoginView extends JFrame implements ActionListener
{
	private JButton okKnop, annulerenKnop, vergetenKnop;
	private JTextField inlogNaamVeld;
	private JPasswordField wwVeld;
	private JLabel imageLabel, inlogLabel, wwLabel;
	private ImageIcon login;
	
	public LoginView()
	{
		setLayout( null );
		
		setSize( 400,300 );
		setLocation( 100,100 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setTitle( "BestelSnel Login" );
		
		inlogNaamVeld = new JTextField( 10 );
		inlogNaamVeld.setBounds( 170, 110, 130, 20 );
		inlogLabel = new JLabel( "Inlognaam" );
		inlogLabel.setBounds( 90, 110, 90, 20 );
		
		wwVeld = new JPasswordField( 10 );
		wwVeld.setBounds( 170, 140, 130, 20 );
		wwLabel = new JLabel( "Wachtwoord" );
		wwLabel.setBounds( 90, 140, 90, 20 );
		
		okKnop = new JButton( "OK" );
		okKnop.addActionListener( this );
		okKnop.setBounds( 90, 200, 100, 50 );
		
		annulerenKnop = new JButton( "Annuleren" );
		annulerenKnop.addActionListener( this );
		annulerenKnop.setBounds( 195, 200, 100, 50 );
		
		vergetenKnop = new JButton( "Wachtwoord vergeten?" );
		vergetenKnop.addActionListener( this );
	
		login = new ImageIcon( "src/ipse/images/logo.png" );
		imageLabel = new JLabel( login );
		imageLabel.setBounds( 50, 0, 300, 100 );
		
		add( okKnop );
		add( annulerenKnop );
		add( vergetenKnop );
		add( inlogNaamVeld );
		add( wwVeld );
		add( imageLabel );
		add( inlogLabel );
		add( wwLabel );
		
		setVisible( true );
	}
	
	public void actionPerformed( ActionEvent ae )
	{
		String naam = inlogNaamVeld.getText();
		String ww = wwVeld.getText();
		
		if (ae.getSource() == okKnop)
		{
			if (naam.equals("medewerker") && ww.equals("1234"))
			{
				new BestelSnelApp();
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "De naam en/of het wachtwoord is niet goed.", "Fout", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			System.exit(0);
		}
	}
}
