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
		ImageIcon logoIcon = new ImageIcon("src/ipse/images/logo.png");
		JLabel imageLabel = new JLabel( logoIcon );
		imageLabel.setBounds( 150, 10, 300, 100 );
		
		//Dit geld voor alle views.
		setSize( 600,400 );
		setLocationRelativeTo(null);
		//setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setResizable( false );
		
		mainPanel = new JPanel();
		setContentPane( mainPanel );
		
		mainPanel.add( imageLabel );

		
		mainPanel.setLayout( null );
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
