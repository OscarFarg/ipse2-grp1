package ipse;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class BestelSnelApp extends JFrame implements WindowListener
{
	Database database;
	Controller controller;
	
	public BestelSnelApp()
	{
		database = Database.getDatabase();
		controller = new Controller();
		
		this.setLayout( new BorderLayout() );
		this.addWindowListener(this);
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


	@Override
	public void windowClosing(WindowEvent arg0) {
		database.close();
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}
