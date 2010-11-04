package ipse;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Database 
{
	private Connection dbConnectie;

	private PreparedStatement selectKlanten, selectKlant, insertKlant, updateKlant, deleteKlant;
	private PreparedStatement selectMedewerkers, selectMedewerker, insertMedewerker, updateMedewerker, deleteMedewerker;
	private PreparedStatement selectArtikelen, selectArtikel, insertArtikel, updateArtikel, deleteArtikel;
	private PreparedStatement selectBestellingen, selectBestelling, insertBestelling, updateBestelling, deleteBestelling, totaalPrijsBestelling;
	private PreparedStatement selectBestelregels, selectBestelregel, insertBestelregel, updateBestelregel, deleteBestelregel;
	private PreparedStatement zoekKlant, zoekMedewerker, zoekBestelling, zoekArtikel;


	private ArrayList<Klant> klantLijst;
	private ArrayList<Medewerker> medewerkerLijst;
	private ArrayList<Artikel> artikelLijst;
	private ArrayList<Bestelling> bestellingLijst;

	private final String USERNAME = "ipse";
	private final String PASSWORD = "ipse2";
	
	private static Database db;

	private Database() 
	{
		if (initialiseer())
			prepare_statements();
	}
	
	public static Database getDatabase()
	{
		if (db == null)
			db = new Database();
		return db;
	}
	

	private boolean initialiseer()
	{    	
		try
		{
			String url = "jdbc:derby://195.169.84.60:1527/IpseDB";
			dbConnectie = DriverManager.getConnection(url, USERNAME, PASSWORD);
		}
		catch (SQLNonTransientConnectionException connectEx)
		{
			JOptionPane.showMessageDialog(null, "Fout bij het verbinden met de database server. Programma word afgesloten");
			System.exit(0);
			return false;
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
			//updateKlant = dbConnectie.prepareStatement("");
			deleteKlant = dbConnectie.prepareStatement("delete from klant where id = ?");
			
			//artikelen
			selectArtikelen = dbConnectie.prepareStatement("select * from artikel");
			insertArtikel = dbConnectie.prepareStatement("insert into artikel ( artikel_naam, prijs ) values ( ?, ? )");
			updateArtikel = dbConnectie.prepareStatement("update artikel set artikel_naam = ?, prijs = ?");
			deleteArtikel = dbConnectie.prepareStatement("delete from artikel where artikelid = ?");
			
			//bestellingen
			selectBestellingen = dbConnectie.prepareStatement("select b.bestelnr \"Bestelnummer\", b.bestel_datum \"Bestel datum\", " + 
					"b.lever_datum \"Lever datum\", b.betaal_datum \"Betaal datum\", " + 
					"k.achternaam || ', ' || k.voornaam || ' ' || coalesce(k.tussenvoegsel, '') \"Klant\", " + 
					"m.achternaam || ', ' || m.voornaam || ' ' || coalesce(m.tussenvoegsel, '') \"Medewerker\" " + 
					"from bestelling b, klant k, medewerker m where b.klantid = k.id and b.medewerkerid = m.id");
			selectBestelling = dbConnectie.prepareStatement("select * from bestelling where bestelnr = ?");
			insertBestelling = dbConnectie.prepareStatement("insert into bestelling (bestel_datum, lever_datum, betaal_datum, " + 
				"klantid, medewerkerid) values(?, ?, ?, ?, ?)");
			updateBestelling = dbConnectie.prepareStatement("update bestelling set bestel_datum = ?, lever_datum = ?, " + 
				"betaal_datum = ?, klantid = ?, medewerkerid = ? where bestelnr = ?");
			deleteBestelling = dbConnectie.prepareStatement("delete from bestelling where bestelnr = ?");
			totaalPrijsBestelling = dbConnectie.prepareStatement("select sum(totaal_prijs) from bestelregel where bestelnr = ?");
		
			//bestelregels
			selectBestelregels = dbConnectie.prepareStatement("select * from bestelregel where bestelnr = ?");
			selectBestelregel = dbConnectie.prepareStatement("select * from bestelregel where bestelnr = ? and artikelid = ?");
			insertBestelregel = dbConnectie.prepareStatement("insert into bestelregel values(?, ?, ?, ?, ?)");
			updateBestelregel = dbConnectie.prepareStatement("update bestelregel set bestelnr = ?, artikelid = ?, " + 
					"prijs = ?, aantal = ?, totaal_prijs = ?");
			deleteBestelregel = dbConnectie.prepareStatement("delete from bestelregel where bestelnr = ? and artikelid = ?");
			
			//Zoek
			zoekKlant = dbConnectie.prepareStatement("select * from klant where ? like ?");
			zoekMedewerker = dbConnectie.prepareStatement("select * from medewerker where ? like ?");
			zoekBestelling = dbConnectie.prepareStatement("select * from bestelling where ? like ?");
			zoekArtikel = dbConnectie.prepareStatement("select * from artikel where ? like ?");
			
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
	
	public void deleteMedewerker(int id)
	{
		try
		{
			deleteMedewerker.setInt(1, id);
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
	
	public void updateKlant(Klant k)
	{
		
	}
	
	public void deleteKlant(int id)
	{
		try
		{
			deleteKlant.setInt(1, id);
			deleteKlant.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
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
	
	public void deleteArtikel(int artikelid )
	{
		try
		{
			deleteArtikel.setInt(1, artikelid);
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
	
	public ResultSet selectBestelling(int bestelnr)
	{
		ResultSet resultSet = null;
		try
		{
			selectBestelling.setInt(1, bestelnr);
			resultSet = selectBestelling.executeQuery();
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
	
	//bestelregel
	public ResultSet getBestelregels(int bestelnr)
	{
		ResultSet resultSet = null;
		try
		{
			selectBestelregels.setInt(1, bestelnr);
			resultSet = selectBestelregels.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
	}
	
	public ResultSet selectBestelregel(int bestelnr, int artikelid)
	{
		ResultSet resultSet = null;
		try
		{
			selectBestelregel.setInt(1, bestelnr);
			selectBestelregel.setInt(2, artikelid);
			resultSet = selectBestelregel.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
	}
	
	public void insertBestelregel(Bestelregel b)
	{
		try
		{
			insertBestelregel.setInt(1, b.getBestelnr());
			insertBestelregel.setInt(2, b.getArtikelid());
			insertBestelregel.setDouble(3, b.getPrijs());
			insertBestelregel.setInt(4, b.getAantal());
			insertBestelregel.setDouble(5, b.getTotaalPrijs());
			insertBestelregel.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public void updateBestelregel(Bestelregel b)
	{
		try
		{
			updateBestelregel.setInt(1, b.getBestelnr());
			updateBestelregel.setInt(2, b.getArtikelid());
			updateBestelregel.setDouble(3, b.getPrijs());
			updateBestelregel.setInt(4, b.getAantal());
			updateBestelregel.setDouble(5, b.getTotaalPrijs());
			updateBestelregel.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public void deleteBestelregel(int bestelnr, int artikelid)
	{
		try
		{
			deleteBestelregel.setInt(1, bestelnr);
			deleteBestelregel.setInt(2, artikelid);
			deleteBestelregel.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
//Zoek Methodes
	public void zoekKlant (Zoek z)
	{
		try
		{
			zoekKlant.setString(1, z.getZoekKolom());
			zoekKlant.setString(2, z.getZoekVeld());
			zoekKlant.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public void zoekMedewerker (Zoek z)
	{
		try
		{
			zoekMedewerker.setString(1, z.getZoekKolom());
			zoekMedewerker.setString(2, z.getZoekVeld());
			zoekMedewerker.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public void zoekBestelling (Zoek z)
	{
		try
		{
			zoekBestelling.setString(1, z.getZoekKolom());
			zoekBestelling.setString(2, z.getZoekVeld());
			zoekBestelling.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public void zoekArtikel (Zoek z)
	{
		try
		{
			zoekArtikel.setString(1, z.getZoekKolom());
			zoekArtikel.setString(2, z.getZoekVeld());
			zoekArtikel.executeUpdate();
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