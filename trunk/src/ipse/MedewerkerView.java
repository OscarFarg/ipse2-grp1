package ipse;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MedewerkerView extends View
{
	private JTextField idVeld, voornaamVeld, tussenvoegselVeld, achternaamVeld, functieVeld, chefidVeld;

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

		functieVeld = new JTextField(15);
		functieVeld.setBounds(150, 210, 150, 20);
		JLabel functieLabel = new JLabel("Functie");
		functieLabel.setBounds(40, 210, 120, 20);

		chefidVeld = new JTextField();
		chefidVeld.setBounds(150, 235, 150, 20);
		JLabel chefidLabel = new JLabel("Chef ID");
		chefidLabel.setBounds(40, 235, 120, 20);

		mainPanel.add(idLabel);
		mainPanel.add(idVeld);
		mainPanel.add(voornaamLabel);
		mainPanel.add(voornaamVeld);
		mainPanel.add(tussenvoegselLabel);
		mainPanel.add(tussenvoegselVeld);
		mainPanel.add(achternaamLabel);
		mainPanel.add(achternaamVeld);
		mainPanel.add(functieLabel);
		mainPanel.add(functieVeld);
		mainPanel.add(chefidLabel);
		mainPanel.add(chefidVeld);
		setVisible(true);
	}

	//Constructor voor update ipv insert
	public MedewerkerView(Database database, Controller controller, int id)
	{
		this(database, controller);
		updateMode = true; //Boolean in super klasse view. Op true zetten bij update.

		//Hier kan je dus doen wat er nodig is om de velden te vullen.
		Medewerker m = database.selectMedewerker(id);
		idVeld.setText(m.getId() + "");
		voornaamVeld.setText(m.getVoornaam());
		tussenvoegselVeld.setText(m.getTussenvoegsel());
		achternaamVeld.setText(m.getAchternaam());
		functieVeld.setText(m.getFunctie());
		int chefId = m.getChefId();
		if (chefId != 0)
			chefidVeld.setText("" + chefId);
	}

	@Override
	public void opslaan() {
		Medewerker m = new Medewerker();

		String voornaam = voornaamVeld.getText();
		String tussenvoegsel = tussenvoegselVeld.getText();
		String achternaam = achternaamVeld.getText();
		String functie = functieVeld.getText();
		m.setVoornaam(voornaam);
		m.setTussenvoegsel(tussenvoegsel);
		m.setAchternaam(achternaam);
		m.setFunctie(functie);
		try {
			int chefId = Integer.parseInt(chefidVeld.getText());
			m.setChefId(chefId);
		}
		catch (NumberFormatException nfe){
			m.setChefId(0);
		}
		m.setMwStatus("Actief");

		if (updateMode)
		{
			m.setId(Integer.parseInt(idVeld.getText()));
			database.updateMedewerker(m);
		}
		else
		{
			database.insertMedewerker(m);
		}

	}

}
