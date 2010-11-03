package ipse;

import java.sql.*;
import java.util.ArrayList;

public class Database 
{
	private Connection dbConnectie;

	private PreparedStatement insertKlant;
	private PreparedStatement selectMedewerkers, insertMedewerker, updateMedewerker, deleteMedewerker;
	private PreparedStatement selectArtikelen, insertArtikel, updateArtikel, deleteArtikel;
	private PreparedStatement selectBestellingen, insertBestelling, updateBestelling, deleteBestelling, totaalPrijsBestelling;
	private PreparedStatement selectBestelregels, insertBestelregel, updateBestelregel, deleteBestelregel;
	private PreparedStatement selectKlanten;
	private PreparedStatement zoekDatabase;


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
			//medewerkers
			selectMedewerkers = dbConnectie.prepareStatement("select * from medewerker");
			insertMedewerker = dbConnectie.prepareStatement("insert into medewerker (voornaam, tussenvoegsel, achternaam, " + 
					"functie, chefid, status) values (?, ?, ?, ?, ?, ?)");
			updateMedewerker = dbConnectie.prepareStatement("update medewerker set voornaam = ?, tussenvoegsel = ?, " + 
					"achternaam = ?, functie = ?, chefid = ?, status = ? where id = ?");
			deleteMedewerker = dbConnectie.prepareStatement("delete from medewerker where id = ?");
			
			//klanten
			selectKlanten = dbConnectie.prepareStatement("select * from klant");
			insertKlant = dbConnectie.prepareStatement("insert into klant (voornaam, tussenvoegsel, achternaam, rekeningnr, " + 
					"betaal_status, status)values (?, ?, ?, ?, ?, ?)");
			
			//artikelen
			selectArtikelen = dbConnectie.prepareStatement("select * from artikel");
			insertArtikel = dbConnectie.prepareStatement("insert into artikel ( artikel_naam, prijs ) values ( ?, ? )");
			updateArtikel = dbConnectie.prepareStatement("update artikel set artikel_naam = ?, prijs = ?");
			deleteArtikel = dbConnectie.prepareStatement("delete from artikel where artikelid = ?");
			
			//bestellingen
			selectBestellingen = dbConnectie.prepareStatement("select * from bestelling");
			insertBestelling = dbConnectie.prepareStatement("insert into bestelling (bestel_datum, lever_datum, betaal_datum, " + 
				"klantid, medewerkerid) values(?, ?, ?, ?, ?)");
			updateBestelling = dbConnectie.prepareStatement("update bestelling set bestel_datum = ?, lever_datum = ?, " + 
				"betaal_datum = ?, klantid = ?, medewerkerid = ? where bestelnr = ?");
			deleteBestelling = dbConnectie.prepareStatement("delete from bestelling where bestelnr = ?");
			totaalPrijsBestelling = dbConnectie.prepareStatement("select sum(totaal_prijs) from bestelregel where bestelnr = ?");
		
			//Zoek Functie
			zoekDatabase = dbConnectie.prepareStatement("select * from ? where ? = ?");
			
		}
		catch (Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
		}
	}

	//medewerker
	public ResultSet getMedewerkers()
	{
		ResultSet resultSet = null;
		try
		{
			resultSet = selectMedewerkers.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
	}
	
	public void insertMedewerker (Medewerker m)
	{
		try
		{
			insertMedewerker.setString(1, m.getVoornaam());
			insertMedewerker.setString(2, m.getTussenvoegsel());
			insertMedewerker.setString(3, m.getAchternaam());
			insertMedewerker.setString(4, m.getFunctie());
			insertMedewerker.setInt(5, m.getChefId());
			insertMedewerker.setString(6, m.getMwStatus());
			insertMedewerker.executeUpdate();
		}
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
	}
	
	public void updateMedewerker(Medewerker m)
	{
		try
		{
			updateMedewerker.setString(1, m.getVoornaam());
			updateMedewerker.setString(2, m.getTussenvoegsel());
			updateMedewerker.setString(3, m.getAchternaam());
			updateMedewerker.setString(4, m.getFunctie());
			updateMedewerker.setInt(5, m.getChefId());
			updateMedewerker.setString(6, m.getMwStatus());
			updateMedewerker.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public void deleteMedewerker(Medewerker m)
	{
		try
		{
			deleteMedewerker.setInt(1, m.getId());
			deleteMedewerker.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	//klant
	public ResultSet getKlanten()
	{
		ResultSet resultSet = null;
		try
		{
			resultSet = selectKlanten.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
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
			insertKlant.setString(6, k.getStatus());
			insertKlant.executeUpdate();
		} 
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
	}
	
	//artikel
	public ResultSet getArtikelen()
	{
		ResultSet resultSet = null;
		try
		{
			resultSet = selectArtikelen.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
	}
	
	public void insertArtikel (Artikel a)
	{
		// gebruik het PreparedStatement 'insertArtikel'
		try
		{
			insertArtikel.setString(1, a.getArtikelnaam());
			insertArtikel.setDouble(2, a.getPrijs());
			insertArtikel.executeUpdate();
		} 
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
	}
	
	public void deleteArtikel(Artikel a )
	{
		try
		{
			deleteArtikel.setInt(1, a.getArtikelid());
			deleteArtikel.executeUpdate();
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}
	
	public void updateArtikel(Artikel a )
	{
		try
		{
			updateArtikel.setString(1, a.getArtikelnaam());
			updateArtikel.setDouble(2, a.getPrijs());
			updateArtikel.executeUpdate();
		}
		catch(SQLException ex)
		{
			System.out.println(ex);
		}
	}
	
	//bestelling
	public ResultSet getBestellingen()
	{
		ResultSet resultSet = null;
		try
		{
			resultSet = selectBestellingen.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
	}

	public void insertBestelling(Bestelling b)
	{
		try
		{
			insertBestelling.setDate(1, (Date) b.getBestelDatum());
			insertBestelling.setDate(2, (Date) b.getLeverDatum());
			insertBestelling.setDate(3, (Date) b.getBetaalDatum());
			insertBestelling.setInt(4, b.getKlantid());
			insertBestelling.setInt(5, b.getMedewerkerid());
			insertBestelling.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	public void updateBestelling(Bestelling b)
	{
		try
		{
			updateBestelling.setDate(1, (Date) b.getBestelDatum());
			updateBestelling.setDate(2, (Date) b.getLeverDatum());
			updateBestelling.setDate(3, (Date) b.getBetaalDatum());
			updateBestelling.setInt(4, b.getKlantid());
			updateBestelling.setInt(5, b.getMedewerkerid());
			updateBestelling.setInt(6, b.getBestelnr());
			updateBestelling.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	public void deleteBestelling(int bestelnr)
	{
		try
		{
			deleteBestelling.setInt(1, bestelnr);
			deleteBestelling.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	public String getTotaalPrijs(int bestelnr)
	{
		String totaalPrijs = "";
		try
		{
			totaalPrijsBestelling.setInt(1, bestelnr);
			ResultSet resultSet = totaalPrijsBestelling.executeQuery();
			while (resultSet.next())
				totaalPrijs = resultSet.getString(1);
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return totaalPrijs;
	}
	
	//zoek
	public void zoekDatabase (Zoek z)
	{
		try
		{
			zoekDatabase.setString(1, z.getZoekSegment());
			zoekDatabase.setString(2, z.getZoekVeld());
			zoekDatabase.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
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