package ipse;

import java.awt.*;
import javax.swing.*;

public class BestelSnelApp extends JFrame
{
	public BestelSnelApp()
	{
		this.setSize( 800, 600 );
		this.setLocation( 100,100 );
		this.setTitle( "BestelSnel Groep1" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		NoordPaneel noordPaneel = new NoordPaneel();
		WestPaneel westPaneel = new WestPaneel();
		ZuidPaneel zuidPaneel = new ZuidPaneel();
		
		add(noordPaneel, BorderLayout.NORTH);
		add(westPaneel, BorderLayout.WEST);
		add(zuidPaneel, BorderLayout.SOUTH);
		
		//JPanel knopView = new KnopView();
		//this.add( knopView );
		
		this.setVisible( true );
	}
	
	public static void main( String[]args )
	{
		new BestelSnelApp();
	}
}
