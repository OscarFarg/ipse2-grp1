package ipse;

import java.awt.*;
import javax.swing.*;

public class BestelSnelApp extends JFrame
{
	Database database;
	Controller controller;
	
	public BestelSnelApp()
	{
		database = new Database();
		controller = new Controller();
		
		this.setLayout( new BorderLayout() );
		
		this.setSize( 800, 600 );
		this.setLocation( 100,100 );
		this.setTitle( "BestelSnel Groep1" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		HoofdView knopView = new HoofdView( controller, database);
		this.add(knopView);
		
		this.setVisible( true );
	}
	
	public static void main( String[]args )
	{
		new BestelSnelApp();
	}
}
