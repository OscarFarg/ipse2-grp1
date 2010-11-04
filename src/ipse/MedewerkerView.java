package ipse;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MedewerkerView extends View
{
	private JTextField idVeld, voornaamVeld, tussenvoegselVeld, achternaamVeld, rekeningnrVeld;
	private JComboBox betaalstatusVeld;
	
	public MedewerkerView(Database database, Controller controller)
	{
		super(database, controller);
		this.setResizable(true);
		
		//ÍD
		idVeld = new JTextField(5);
		idVeld.setEditable(false);
		idVeld.setBounds( 150, 110, 100, 20 );
		JLabel idLabel = new JLabel("Medewerker ID");
		idLabel.setBounds(40, 110, 120, 20);
		
		//Voornaam
		voornaamVeld = new JTextField(15);
		voornaamVeld.setBounds(150, 135, 150, 20);
		JLabel voornaamLabel = new JLabel("Voornaam");
		voornaamLabel.setBounds(40, 135, 120, 20);
		
		tussenvoegselVeld = new JTextField(10);
		tussenvoegselVeld.setBounds(150, 160, 150, 20);
		JLabel tussenvoegselLabel = new JLabel("Tussenvoegsel");
		tussenvoegselLabel.setBounds(40, 160, 120, 20);

		achternaamVeld = new JTextField(15);
		achternaamVeld.setBounds(150, 185, 150, 20);
		JLabel achternaamLabel = new JLabel("Achternaam");
		achternaamLabel.setBounds(40, 185, 120, 20);

		rekeningnrVeld = new JTextField(15);
		rekeningnrVeld.setBounds(150, 210, 150, 20);
		JLabel rekeningnrLabel = new JLabel("Rekening");
		rekeningnrLabel.setBounds(40, 210, 120, 20);

		betaalstatusVeld = new JComboBox();
		betaalstatusVeld.setBounds(150, 235, 150, 20);
		JLabel betaalstatusLabel = new JLabel("Betaalstatus");
		betaalstatusLabel.setBounds(40, 235, 120, 20);
		
		mainPanel.add(idLabel);
		mainPanel.add(idVeld);
		mainPanel.add(voornaamLabel);
		mainPanel.add(voornaamVeld);
		mainPanel.add(tussenvoegselLabel);
		mainPanel.add(tussenvoegselVeld);
		mainPanel.add(achternaamLabel);
		mainPanel.add(achternaamVeld);
		mainPanel.add(rekeningnrLabel);
		mainPanel.add(rekeningnrVeld);
		mainPanel.add(betaalstatusLabel);
		mainPanel.add(betaalstatusVeld);
		setVisible(true);
	}
	
	@Override
	public void annuleren() {
		System.out.println("Annulerenknop ingedrukt.");
	}

	@Override
	public void opslaan() {
		System.out.println("Opslaanknop ingedrukt.");
	}
/*
	public static void main(String[] args)
	{
		new MedewerkerView(null, null);
	*/
}
