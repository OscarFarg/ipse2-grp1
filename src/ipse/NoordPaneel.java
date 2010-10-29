package ipse;

import javax.swing.*;
import java.awt.event.*;

public class NoordPaneel extends JPanel implements ActionListener
{
	private JButton zoekKnop, uitlogKnop;
	
	public NoordPaneel()
	{
		//setLayout(null);
		
		JLabel logo = new JLabel(new ImageIcon("src/ipse/Images/logo.png"));
		//logo.setBounds(0, 0, 300, 150);
		add(logo);
		
		uitlogKnop = new JButton("Log uit");
		uitlogKnop.addActionListener(this);
		//uitlogKnop.setBounds(0, 160, 50, 20);
		add(uitlogKnop);
		
		zoekKnop = new JButton("Zoek");
		zoekKnop.addActionListener(this);
		//zoekKnop.setBounds(700, 0, 50, 20);
		add(zoekKnop);
	}

	public void actionPerformed(ActionEvent ae)
	{
		
	}	
}
