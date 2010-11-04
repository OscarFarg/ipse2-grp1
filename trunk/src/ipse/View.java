package ipse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class View extends JFrame implements ActionListener
{
	//protected zodat die in alle subklasses gebruikt kan worden.
	protected JPanel mainPanel;
	
	public View()
	{
		//Dit geld voor alle views.
		setSize( 600,400 );
		setLocationRelativeTo(null);
		//setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable( false );
		
		mainPanel = new JPanel();
		setContentPane( mainPanel );
		
		mainPanel.setLayout( null );
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
