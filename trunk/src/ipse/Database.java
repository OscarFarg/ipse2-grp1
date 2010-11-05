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
	private PreparedStatement selectBestellingen, selectBestelling, insertBestelling, updateBestelling, deleteBestelling, totaalPrijsBestelling, selectKlantBestelling, selectMedewerkerBestelling;
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
			selectMedewerker = dbConnectie.prepareStatement( "select * from medewerker where id = ?" );
			insertMedewerker = dbConnectie.prepareStatement("insert into medewerker (voornaam, tussenvoegsel, achternaam, " + 
			"functie, chefid, status) values (?, ?, ?, ?, ?, ?)");
			updateMedewerker = dbConnectie.prepareStatement("update medewerker set voornaam = ?, tussenvoegsel = ?, " + 
			"achternaam = ?, functie = ?, chefid = ?, status = ? where id = ?");
			deleteMedewerker = dbConnectie.prepareStatement("delete from medewerker where id = ?");

			//klanten
			selectKlanten = dbConnectie.prepareStatement("select * from klant where status = 'Actief'");
			selectKlant = dbConnectie.prepareStatement( "select * from klant where id = ?" );
			insertKlant = dbConnectie.prepareStatement("insert into klant (voornaam, tussenvoegsel, achternaam, rekeningnr, " + 
			"betaal_status, status)values (?, ?, ?, ?, ?, ?)");
			updateKlant = dbConnectie.prepareStatement("update klant set voornaam = ?, tussenvoegsel = ?, achternaam = ?, " + 
			"rekeningnr = ?, betaal_status = ? where id = ?");
			deleteKlant = dbConnectie.prepareStatement("update klant set status = 'Niet actief' where id = ?");

			//artikelen
			selectArtikelen = dbConnectie.prepareStatement("select * from artikel");
			selectArtikel = dbConnectie.prepareStatement( "select * from artikel where artikelid = ?" );
			insertArtikel = dbConnectie.prepareStatement("insert into artikel ( artikel_naam, prijs ) values ( ?, ? )");
			updateArtikel = dbConnectie.prepareStatement("update artikel set artikel_naam = ?, prijs = ? where artikelid = ?");
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
			selectKlantBestelling = dbConnectie.prepareStatement("select id, achternaam from klant");
			selectMedewerkerBestelling = dbConnectie.prepareStatement("select id, achternaam from medewerker");

			//bestelregels
			selectBestelregels = dbConnectie.prepareStatement("select b.bestelnr \"Bestelnummer\", a.artikelid \"Artikelid\", " + 
					"a.artikel_naam \"Artikel\", b.prijs \"Prijs\", b.aantal \"Aantal\", b.totaal_prijs \"Totaal\" " + 
			"from bestelregel b, artikel a where bestelnr = ? and b.artikelid = a.artikelid");
			selectBestelregel = dbConnectie.prepareStatement("select * from bestelregel where bestelnr = ? and artikelid = ?");
			insertBestelregel = dbConnectie.prepareStatement("insert into bestelregel values(?, ?, ?, ?, ?)");
			updateBestelregel = dbConnectie.prepareStatement("update bestelregel set bestelnr = ?, artikelid = ?, " + 
			"prijs = ?, aantal = ?, totaal_prijs = ? where bestelnr = ? and artikelid = ?");
			deleteBestelregel = dbConnectie.prepareStatement("delete from bestelregel where bestelnr = ? and artikelid = ?");

			//Zoek
			zoekKlant = dbConnectie.prepareStatement("select * from klant where ? like ?");
			zoekMedewerker = dbConnectie.prepareStatement("select * from medewerker where voornaam like ?");
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

	public Medewerker selectMedewerker(int id)
	{
		Medewerker m = new Medewerker();
		try
		{
			selectMedewerker.setInt(1, id);
			ResultSet rs = selectMedewerker.executeQuery();
			while (rs.next())
			{
				m.setId(rs.getInt(1));
				m.setVoornaam(rs.getString(2));
				m.setTussenvoegsel(rs.getString(3));
				m.setAchternaam(rs.getString(4));
				m.setFunctie(rs.getString(5));
				m.setChefId(rs.getInt(6));
				m.setMwStatus(rs.getString(7));
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return m;
	}

	public ResultSet selectMedewerkerBestelling()
	{
		ResultSet resultSet = null;
		try
		{
			resultSet = selectMedewerkerBestelling.executeQuery();
		}
		catch( SQLException se )
		{
			System.out.println( se );
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
			if (m.getChefId() != 0)
				insertMedewerker.setInt(5, m.getChefId());
			else
				insertMedewerker.setString(5, null);
			insertMedewerker.setString(6, m.getMwStatus());
			System.out.println(insertMedewerker.executeUpdate());

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
			if (m.getChefId() != 0)
				updateMedewerker.setInt(5, m.getChefId());
			else
				updateMedewerker.setString(5, null);

			updateMedewerker.setString(6, m.getMwStatus());
			updateMedewerker.setInt(7, m.getId());
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

	public Klant selectKlant(int id)
	{
		Klant klant = null;
		int klantId = 0;
		String voornaam = "";
		String tussenvoegsel = "";
		String achternaam = "";
		String rekeningNr = "";
		String betaalStatus = "";
		String status = "";
		try
		{
			selectKlant.setInt(1, id);
			ResultSet resultSet = selectKlant.executeQuery();
			while(resultSet.next())
			{
				klantId = resultSet.getInt(1);
				voornaam = resultSet.getString(2);
				tussenvoegsel = resultSet.getString(3);
				achternaam = resultSet.getString(4);
				rekeningNr = resultSet.getString(5);
				betaalStatus = resultSet.getString(6);
				status = resultSet.getString(7);
			}
			klant = new Klant(klantId, voornaam, tussenvoegsel, achternaam, rekeningNr, betaalStatus, status);
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return klant;
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
			JOptionPane.showMessageDialog(null, "Een invoer is fout.", "Fout", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateKlant(Klant k)
	{
		try
		{
			updateKlant.setString(1, k.getVoornaam());
			updateKlant.setString(2, k.getTussenvoegsel());
			updateKlant.setString(3, k.getAchternaam());
			updateKlant.setString(4, k.getRekeningNr());
			updateKlant.setString(5, k.getBetaalStatus());
			updateKlant.setInt(6, k.getId());
			updateKlant.executeUpdate();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Een invoer is fout.", "Fout", JOptionPane.ERROR_MESSAGE);
		}
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
			JOptionPane.showMessageDialog(null, "De klant is niet verwijderd.", "Fout", JOptionPane.ERROR_MESSAGE);
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

	public Artikel selectArtikel(int artikelid)
	{
		Artikel artikel = null;
		int artikelId = 0;
		String naam = "";
		double prijs = 0.0;
		try
		{
			selectArtikel.setInt(1, artikelid);
			ResultSet resultSet = selectArtikel.executeQuery();
			while(resultSet.next())
			{	
				artikelId = resultSet.getInt(1);
				naam = resultSet.getString(2);
				prijs = resultSet.getDouble(3);
			}
			artikel = new Artikel(artikelId, naam, prijs);
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return artikel;
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
			updateArtikel.setInt(3, a.getArtikelid());
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

	public Bestelling selectBestelling(int bestelnr)
	{
		Bestelling bestelling = null;
		int bestelNr = 0;
		java.sql.Date bestel_datum = null;
		java.sql.Date lever_datum = null;
		java.sql.Date betaal_datum = null;
		int klant_id = 0;
		int medewerker_id = 0;
		try
		{
			selectBestelling.setInt(1, bestelnr);
			ResultSet resultSet = selectBestelling.executeQuery();
			while(resultSet.next())
			{	
				bestelNr = resultSet.getInt(1);
				bestel_datum = resultSet.getDate(2);
				lever_datum = resultSet.getDate(3);
				betaal_datum = resultSet.getDate(4);
				klant_id = resultSet.getInt(5);
				medewerker_id = resultSet.getInt(6);
			}
			bestelling = new Bestelling( bestelNr, bestel_datum, lever_datum, betaal_datum, klant_id, medewerker_id );
		}
		catch (SQLException e)
		{
			System.out.println(e + "\n");
			e.printStackTrace();
		}
		return bestelling;
	}

	public void insertBestelling(Bestelling b)
	{
		try
		{

			if (b.getBestelDatum() != null)
				insertBestelling.setDate(1, new Date(b.getBestelDatum().getTime()));
			else
				insertBestelling.setString(1, null);
			
			if (b.getLeverDatum() != null)
				insertBestelling.setDate(2, new Date(b.getLeverDatum().getTime()));
			else
				insertBestelling.setString(2, null);
			
			if (b.getBetaalDatum() != null)
				insertBestelling.setDate(3, new Date(b.getBetaalDatum().getTime()));
			else
				insertBestelling.setString(3, null);
			
			insertBestelling.setInt(4, b.getKlantid());
			insertBestelling.setInt(5, b.getMedewerkerid());
			insertBestelling.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void updateBestelling(Bestelling b)
	{
		try
		{
			if (b.getBestelDatum() != null)
				updateBestelling.setDate(1, new Date(b.getBestelDatum().getTime()));
			else
				updateBestelling.setString(1, null);
			
			if (b.getLeverDatum() != null)
				updateBestelling.setDate(2, new Date(b.getLeverDatum().getTime()));
			else
				updateBestelling.setString(2, null);
			
			if (b.getBetaalDatum() != null)
				updateBestelling.setDate(3, new Date(b.getBetaalDatum().getTime()));
			else
				updateBestelling.setString(3, null);
			updateBestelling.setInt(4, b.getKlantid());
			updateBestelling.setInt(5, b.getMedewerkerid());
			updateBestelling.setInt(6, b.getBestelnr());
			updateBestelling.executeUpdate();
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

	public Bestelregel selectBestelregel(int bestelnr, int artikelid)
	{
		Bestelregel bestelregel = null;
		int bestelnummer = 0;
		int artikelnummer = 0;
		double prijs = 0.0;
		int aantal = 0;
		double totaal = 0.0;
		try
		{
			selectBestelregel.setInt(1, bestelnr);
			selectBestelregel.setInt(2, artikelid);
			ResultSet resultSet = selectBestelregel.executeQuery();
			while (resultSet.next())
			{
				bestelnummer = resultSet.getInt(1);
				artikelnummer = resultSet.getInt(2);
				prijs = resultSet.getDouble(3);
				aantal = resultSet.getInt(4);
				totaal = resultSet.getDouble(5);
			}
			bestelregel = new Bestelregel(bestelnummer, artikelnummer, prijs, aantal, totaal);
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return bestelregel;
	}

	public ResultSet selectKlantBestelling()
	{
		ResultSet resultSet = null;
		try
		{
			resultSet = selectKlantBestelling.executeQuery();
		}
		catch( SQLException se )
		{
			System.out.println( se );
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
			JOptionPane.showMessageDialog(null, "Een invoer is fout.", "Fout", JOptionPane.ERROR_MESSAGE);
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
			updateBestelregel.setInt(6, b.getBestelnr());
			updateBestelregel.setInt(7, b.getArtikelid());
			updateBestelregel.executeUpdate();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Een invoer is fout.", "Fout", JOptionPane.ERROR_MESSAGE);
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
	public ResultSet zoekKlant (Zoek z)
	{
		ResultSet resultSet = null;
		try
		{
			zoekKlant.setString(1, z.getZoekKolom());
			zoekKlant.setString(2, z.getZoekVeld());
			resultSet = zoekKlant.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
	}

	public ResultSet zoekMedewerker (String kolom, String zoekterm)
	{
		ResultSet resultSet = null;
		try
		{
			//zoekMedewerker.setString(1, kolom);
			zoekMedewerker.setString(1, zoekterm);
			resultSet = zoekMedewerker.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
	}

	public ResultSet zoekBestelling (Zoek z)
	{
		ResultSet resultSet = null;
		try
		{
			zoekBestelling.setString(1, z.getZoekKolom());
			zoekBestelling.setString(1, z.getZoekVeld());
			resultSet = zoekBestelling.executeQuery();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return resultSet;
	}

	public ResultSet zoekArtikel (Zoek z)
	{
		ResultSet resultSet = null;
		try
		{
			zoekArtikel.setString(1, z.getZoekKolom());
			zoekArtikel.setString(2, z.getZoekVeld());
			resultSet = zoekArtikel.executeQuery();
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