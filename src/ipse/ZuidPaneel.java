package ipse;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

public class ZuidPaneel extends JPanel implements ActionListener
{
	private JButton bestellingKnop, artikelKnop, medewerkerKnop, klantKnop;
	
	public ZuidPaneel()
	{
		GridLayout gLayout = new GridLayout(1,0);
		gLayout.setVgap(50);
		setLayout(gLayout); 
		
		bestellingKnop = new JButton("Bestellingen");
		bestellingKnop.addActionListener(this);
		add(bestellingKnop);
		
		artikelKnop = new JButton("Artikelen");
		artikelKnop.addActionListener(this);
		add(artikelKnop);
		
		medewerkerKnop = new JButton("Medewerkers");
		medewerkerKnop.addActionListener(this);
		add(medewerkerKnop);
		
		klantKnop = new JButton("Klanten");
		klantKnop.addActionListener(this);
		add(klantKnop);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == bestellingKnop)
		{
			
		}
		if (ae.getSource() == artikelKnop)
		{
			
		}
		if (ae.getSource() == medewerkerKnop)
		{
			
		}
		if (ae.getSource() == klantKnop)
		{
			
		}
	}
}
