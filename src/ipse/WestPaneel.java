package ipse;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class WestPaneel extends JPanel implements ActionListener
{
	private JButton nieuwKnop, bewerkKnop, verwijderKnop, openKnop, testKnop;
	
	public WestPaneel()
	{	
		//setLayout(null);
		setBackground( Color.red );
		
		nieuwKnop = new JButton("Nieuw");
		nieuwKnop.addActionListener(this);
		//nieuwKnop.setBounds( 10, 0, 100, 20 );
		add(nieuwKnop);
		
		testKnop = new JButton( "test" );
		add(testKnop);
		
		bewerkKnop = new JButton("Bewerk");
		bewerkKnop.addActionListener(this);
		//bewerkKnop.setBounds( 10, 70, 100, 20 );
		add(bewerkKnop);
		
		verwijderKnop = new JButton("Verwijder");
		verwijderKnop.addActionListener(this);
		//verwijderKnop.setBounds( 10, 140, 100, 20 );
		add(verwijderKnop);
		
		openKnop = new JButton("Open");
		openKnop.addActionListener(this);
		//openKnop.setBounds( 10, 210, 100, 20 );
		add(openKnop);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == nieuwKnop)
		{
			
		}
		if (ae.getSource() == bewerkKnop)
		{
			
		}
		if (ae.getSource() == verwijderKnop)
		{
			
		}
		if (ae.getSource() == openKnop)
		{
			
		}
	}	
}
