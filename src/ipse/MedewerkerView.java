package ipse;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class MedewerkerView extends View
{
	private JTextField idVeld, voornaamVeld, tussenvoegselVeld, achternaamVeld, functieVeld;
	private JComboBox chefBox;
	private ArrayList<Medewerker> medewerkerLijst;
	private ResultSet medewerkerSet;

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

		chefBox = new JComboBox();
		chefBox.setBounds(150, 235, 150, 20);
		chefBox.addActionListener(this);
		JLabel chefidLabel = new JLabel("Chef");
		chefidLabel.setBounds(40, 235, 120, 20);

		vulBox();
		
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
		mainPanel.add(chefBox);
		setVisible(true);
	}

	//Constructor voor update ipv insert
	public MedewerkerView(Database database, Controller controller, int id)
	{
		this(database, controller);
		updateMode = true; //Boolean in super klasse view. Op true zetten bij update.
		vulBox();
		//Hier kan je dus doen wat er nodig is om de velden te vullen.
		Medewerker m = database.selectMedewerker(id);
		idVeld.setText(m.getId() + "");
		voornaamVeld.setText(m.getVoornaam());
		tussenvoegselVeld.setText(m.getTussenvoegsel());
		achternaamVeld.setText(m.getAchternaam());
		functieVeld.setText(m.getFunctie());
		int chefId = m.getChefId();
		if (chefId != 0)
			for (int i = 0; i < medewerkerLijst.size(); i ++)
			{
				Medewerker temp = (Medewerker) medewerkerLijst.get(i);
				if (temp.getId() == chefId)
				{
					chefBox.setSelectedItem(temp.getId() + ", " + temp.getAchternaam());
				}
			}
	}
	
	public void vulBox()
	{
		medewerkerLijst = new ArrayList<Medewerker>();
		chefBox.removeAllItems();
		chefBox.addItem("");
		try
		{
			medewerkerSet = database.getMedewerkersAll();
			while(medewerkerSet.next())
			{
				Medewerker m = new Medewerker(medewerkerSet.getInt(1), medewerkerSet.getString(2), 
						medewerkerSet.getString(3), medewerkerSet.getString(4), medewerkerSet.getString(5), 
						medewerkerSet.getInt(6), medewerkerSet.getString(7));
				medewerkerLijst.add(m);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}

		for (int i = 0; i < medewerkerLijst.size(); i ++)
		{
			Medewerker temp = (Medewerker) medewerkerLijst.get(i);
			chefBox.addItem(temp.getId() + ", " + temp.getAchternaam());
		}
	}

	public void reportChange()
	{
		vulBox();
	}
	
	@Override
	public void opslaan() {
		Medewerker m = new Medewerker();
		try
		{
			String voornaam = voornaamVeld.getText();
			String tussenvoegsel = tussenvoegselVeld.getText();
			String achternaam = achternaamVeld.getText();
			String functie = functieVeld.getText();
			m.setVoornaam(voornaam);
			m.setTussenvoegsel(tussenvoegsel);
			m.setAchternaam(achternaam);
			m.setFunctie(functie);
			try {
				String selected = (String) chefBox.getSelectedItem();
				Scanner s = new Scanner(selected).useDelimiter("\\,");
				int chefId = Integer.parseInt(s.next());
				m.setChefId(chefId);
			}
			catch (Exception e){
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
		catch (Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, probeer het opnieuw.", "Fout", JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			super.opslaan();
		}
	}
}
