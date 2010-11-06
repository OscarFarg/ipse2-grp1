package ipse;

import javax.swing.*;

public class KlantView extends View
{
	private JTextField idVeld, voornaamVeld, tussenvoegselVeld, achternaamVeld, rekeningVeld;
	private JComboBox betaalStatusBox;
	
	public KlantView(Database database, Controller controller)	
	{
		super(database, controller);
		
		//Id
		idVeld = new JTextField(5);
		idVeld.setEditable(false);
		idVeld.setBounds( 150, 110, 100, 20 );
		JLabel idLabel = new JLabel("Klant ID");
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

		rekeningVeld = new JTextField(15);
		rekeningVeld.setBounds(150, 210, 150, 20);
		JLabel rekeningnrLabel = new JLabel("Rekening");
		rekeningnrLabel.setBounds(40, 210, 120, 20);

		betaalStatusBox = new JComboBox();
		betaalStatusBox.addItem("Gemiddelde betaler");
		betaalStatusBox.addItem("Goede betaler");
		betaalStatusBox.addItem("Slechte betaler");
		betaalStatusBox.setBounds(150, 235, 150, 20);
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
		mainPanel.add(rekeningVeld);
		mainPanel.add(betaalstatusLabel);
		mainPanel.add(betaalStatusBox);
		
		this.setVisible(true);
	}
	
	public KlantView(Database database, Controller controller, int id)
	{
		this(database, controller);
		updateMode = true;
		Klant klant = database.selectKlant(id);
		idVeld.setText(klant.getId() + "");
		voornaamVeld.setText(klant.getVoornaam());
		tussenvoegselVeld.setText(klant.getTussenvoegsel());
		achternaamVeld.setText(klant.getAchternaam());
		rekeningVeld.setText(klant.getRekeningNr());
		betaalStatusBox.setSelectedItem(klant.getBetaalStatus());
	}

	public void opslaan()
	{
		System.out.println("Opslaanknop ingedrukt.");
		try
		{
			String id = idVeld.getText();
			int idNr = Integer.parseInt(id);
			String voornaam = voornaamVeld.getText();
			String tussenvoegsel = tussenvoegselVeld.getText();
			String achternaam = achternaamVeld.getText();
			String rekeningNr = rekeningVeld.getText();
			String betaalStatus = (String) betaalStatusBox.getSelectedItem();
			Klant klant = new Klant(idNr, voornaam, tussenvoegsel, achternaam, rekeningNr, betaalStatus, "Actief");
			if (updateMode)
			{
				System.out.println("update");
				database.updateKlant(klant);
			}
			else
			{
				System.out.println("insert");
				database.insertKlant(klant);
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, probeer het opnieuw", "Fout", JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			super.opslaan();
		}
	}
}
