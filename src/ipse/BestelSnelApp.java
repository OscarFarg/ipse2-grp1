package ipse;

import javax.swing.*;

public class BestelSnelApp extends JFrame
{
	public BestelSnelApp()
	{
		this.setSize( 800, 600 );
		this.setLocation( 100,100 );
		this.setTitle( "BestelSnel Groep1" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JPanel knopView = new KnopView();
		this.add( knopView );
		
		this.setVisible( true );
	}
	
	public static void main( String[]args )
	{
		new LoginView();
	}
}
