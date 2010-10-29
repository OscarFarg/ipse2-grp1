package ipse;

import java.awt.*;
import javax.swing.*;

public class BestelSnelApp extends JFrame
{
	public BestelSnelApp()
	{
		this.setLayout( new BorderLayout() );
		
		this.setSize( 800, 600 );
		this.setLocation( 100,100 );
		this.setTitle( "BestelSnel Groep1" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		NoordPaneel noordPaneel = new NoordPaneel();
		WestPaneel westPaneel = new WestPaneel();
		OostPaneel oostPaneel = new OostPaneel();
		ZuidPaneel zuidPaneel = new ZuidPaneel();
		
		add(noordPaneel, BorderLayout.NORTH);
		add(westPaneel, BorderLayout.WEST);
		add(oostPaneel, BorderLayout.EAST );
		add(zuidPaneel, BorderLayout.SOUTH);
		
		this.setVisible( true );
	}
	
	public static void main( String[]args )
	{
		new BestelSnelApp();
	}
}
