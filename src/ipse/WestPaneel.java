package ipse;

import javax.swing.*;
import java.awt.event.*;

public class WestPaneel extends JPanel implements ActionListener
{
	private JButton nieuwKnop, bewerkKnop, verwijderKnop, openKnop;
	
	public WestPaneel()
	{
		//setLayout(null);
		
		nieuwKnop = new JButton("Nieuw");
		nieuwKnop.addActionListener(this);
		//nieuwKnop.setBounds(, , , );
		add(nieuwKnop);
		
		bewerkKnop = new JButton("Bewerk");
		bewerkKnop.addActionListener(this);
		//bewerkKnop.setBounds(, , , );
		add(bewerkKnop);
		
		verwijderKnop = new JButton("Verwijder");
		verwijderKnop.addActionListener(this);
		//verwijderKnop.setBounds(, , , );
		add(verwijderKnop);
		
		openKnop = new JButton("Open");
		openKnop.addActionListener(this);
		//openKnop.setBounds(, , , );
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
