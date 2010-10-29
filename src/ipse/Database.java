package ipse;

import java.sql.*;
import java.util.ArrayList;

public class Database 
{
	private Connection dbConnectie;
	//	private PreparedStatement insertPersoon;
	//	private PreparedStatement insertRekening;
	//	private PreparedStatement geselecteerdeRekening;
	//	private PreparedStatement allePersonen;

	private PreparedStatement insertKlant;
	private PreparedStatement insertMedewerker;
	private PreparedStatement insertArtikel;
	private PreparedStatement insertBestelling;

	private ArrayList<Klant> klantLijst;
	private ArrayList<Medewerker> medewerkerLijst;
	private ArrayList<Artikel> artikelLijst;
	private ArrayList<Bestelling> bestellingLijst;

	private final String USERNAME = "ipse";
	private final String PASSWORD = "ipse2";

	public Database() 
	{
		if (initialiseer())
			prepare_statements();
	}

	private boolean initialiseer()
	{    	
		try
		{
			String url = "jdbc:derby://195.169.84.60:1527/IpseDB";
			dbConnectie = DriverManager.getConnection(url, USERNAME, PASSWORD);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return (false);
		}
		return (true);   	
	}

	// veelgebruikte statements 'klaarzetten' en (eventueel)
	// parameter-invoer mogelijk maken 
	private void prepare_statements()
	{
		try
		{	
			//			insertPersoon = deDbConnectie.prepareStatement ("insert into persoon (bsn, naam) values (?, ?)");
			//			insertRekening = deDbConnectie.prepareStatement ("insert into rekening (nr, saldo, bsn) values (?, ?, ?)");
			//			geselecteerdeRekening = deDbConnectie.prepareStatement("select * from rekening where bsn = ?");
			//			allePersonen = deDbConnectie.prepareStatement("select * from persoon");

			insertKlant = dbConnectie.prepareStatement(
					"insert into klant (voornaam, tussenvoegsel, achternaam, rekeningnr, betaalstatus, klant_status)" +
			"values (?, ?, ?, ?, ?, ?)");
			insertMedewerker = dbConnectie.prepareStatement(
					"instert into medewerker (voornaam, tussenvoegsel, achternaam, functie, chef_id, medewerker_status" +
			"values (?, ?, ?, ?, ?, ?)");

		}
		catch (Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
		}
	}

	public void insertKlant (Klant k)
	{
		// gebruik het PreparedStatement 'insertRekening'
		try
		{
			insertKlant.setString(1, k.getVoornaam());
			insertKlant.setString(2, k.getTussenvoegsel());
			insertKlant.setString(3, k.getAchternaam());
			insertKlant.setString(4, k.getRekeningNr());
			insertKlant.setString(5, k.getBetaalStatus());
			insertKlant.setString(6, k.getKlantStatus());
			insertKlant.executeUpdate();
		} 
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
	}

	public void insertMedewerker (Medewerker m)
	{
		try
		{
			insertMedewerker.setString(1, m.getVoornaam());
			insertMedewerker.setString(2, m.getTussenvoegsel());
			insertMedewerker.setString(3, m.getAchternaam());
			insertMedewerker.setString(4, m.getFunctie());
			insertMedewerker.setString(5, m.getChefId());
			insertMedewerker.setString(6, m.getMedewerkerStatus());
			insertMedewerker.executeUpdate();
		}
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
	}

	// sluit alle PreparedStatements en 
	// de database-connectie 
	public void close()
	{
		try
		{
			dbConnectie.close();
		}
		catch (Exception ex)
		{
			System.out.println (ex);
		}
	}
}