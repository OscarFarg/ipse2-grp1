package ipse;

import java.sql.*;
import java.util.ArrayList;

public class Database 
{
	private Connection dbConnectie;

	private PreparedStatement insertKlant;
	private PreparedStatement insertMedewerker;
	private PreparedStatement insertArtikel;
	private PreparedStatement selectBestellingen, insertBestelling, updateBestelling, deleteBestelling, totaalPrijsBestelling;
	private PreparedStatement selectBestelregels, insertBestelregel, updateBestelregel, deleteBestelregel;
	private PreparedStatement selectArtikelen;
	private PreparedStatement selectMedewerkers;
	private PreparedStatement selectKlanten;

	private PreparedStatement deleteArtikel;

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
			insertKlant = dbConnectie.prepareStatement(
					"insert into klant (voornaam, tussenvoegsel, achternaam, rekeningnr, betaal_status, status)" +
			"values (?, ?, ?, ?, ?, ?)");
			insertMedewerker = dbConnectie.prepareStatement(
					"insert into medewerker (voornaam, tussenvoegsel, achternaam, functie, chefid, status)" +
			"values (?, ?, ?, ?, ?, ?)");
			insertArtikel = dbConnectie.prepareStatement(
					"insert into artikel ( artikel_naam, prijs ) values ( ?, ? )");
			selectBestellingen = dbConnectie.prepareStatement("select * from bestelling");
			insertBestelling = dbConnectie.prepareStatement("insert into bestelling (bestel_datum, lever_datum, betaal_datum, " + 
				"klantid, medewerkerid) values(?, ?, ?, ?, ?)");
			updateBestelling = dbConnectie.prepareStatement("update bestelling set bestel_datum = ?, lever_datum = ?, " + 
				"betaal_datum = ?, klantid = ?, medewerkerid = ? where bestelnr = ?");
			deleteBestelling = dbConnectie.prepareStatement("delete from bestelling where bestelnr = ?");
			selectArtikelen = dbConnectie.prepareStatement("select * from artikel");
			selectMedewerkers = dbConnectie.prepareStatement("select * from medewerker");
			selectKlanten = dbConnectie.prepareStatement("select * from klant");
			deleteArtikel = dbConnectie.prepareStatement("delete from artikel where artikelid = ?");
			totaalPrijsBestelling = dbConnectie.prepareStatement("select sum(totaal_prijs) from bestelregel where bestelnr = ?");
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
			insertKlant.setString(6, k.getStatus());
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
			insertMedewerker.setString(6, m.getMwStatus());
			insertMedewerker.executeUpdate();
		}
		catch (SQLException ex)
		{
			System.out.println(ex);
		}
	}
	
	public void insertArtikel (Artikel a)
	{
		// gebruik het PreparedStatement 'insertArtikel'
		try
		{
			insertArtikel.setInt(1, a.getArtikelid());
			insertArtikel.setString(2, a.getArtikelnaam());
			insertArtikel.setDouble(3, a.getPrijs());
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
			totaalPrijs = resultSet.getString(1);
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return totaalPrijs;
	}
	
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