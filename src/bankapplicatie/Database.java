package bankapplicatie;

import java.sql.*;
import java.util.ArrayList;

public class Database 
{
    private Connection deDbConnectie;
    private PreparedStatement insertPersoon;
    private PreparedStatement insertRekening;
    private PreparedStatement geselecteerdeRekening;
    private PreparedStatement allePersonen;

    private ArrayList<Persoon> dePersonen;
    private ArrayList<Rekening> deRekeningen;


    public Database() 
    {
	if (initialiseer())
	    prepare_statements();
    }

    private boolean initialiseer()
    {    	
	try
	{
	    String url = "jdbc:derby:ioop3;create=true";
	    deDbConnectie = DriverManager.getConnection(url);
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
	    insertPersoon = deDbConnectie.prepareStatement ("insert into persoon (bsn, naam) values (?, ?)");
	    insertRekening = deDbConnectie.prepareStatement ("insert into rekening (nr, saldo, bsn) values (?, ?, ?)");
	    geselecteerdeRekening = deDbConnectie.prepareStatement("select * from rekening where bsn = ?");
	    allePersonen = deDbConnectie.prepareStatement("select * from persoon");

	}
	catch (Exception ex)
	{
	    System.out.println(ex);
	    ex.printStackTrace();

	}
    }

    // Een persoons-object toevoegen aan de (relationele) database
    public void insertPersoon (Persoon pers)
    {
	try
	{
	    insertPersoon.setString (1, pers.getBsn());
	    insertPersoon.setString (2, pers.getNaam());
	    insertPersoon.executeUpdate();
	}
	catch (Exception ex)
	{
	    System.out.println (ex);
	    ex.printStackTrace();
	}
    }

    // Een rekening-object toevoegen aan de (relationele) database
    public void insertRekening (Rekening rek)
    {
	// gebruik het PreparedStatement 'insertRekening'
	try
	{
	    insertRekening.setString(1, rek.getNummer());
	    insertRekening.setDouble(2, rek.getSaldo());
	    insertRekening.setString(3, rek.getPersoon().getBsn());
	    insertRekening.executeUpdate();
	} catch (SQLException ex)
	{
	    System.out.println(ex);
	}

    }

    // Alle personen ophalen uit de database .. 
    public ArrayList<Persoon> getPersonen()
    {
	return (dePersonen);	
    }

    // Alle rekeningen ophalen uit de database ..
    public ArrayList<Rekening> getRekeningen()
    {
	return (deRekeningen);	
    }


    // 'verversen' van de persoon- en rekening-lijsten .. 
    public void refresh ()
    {
	dePersonen = new ArrayList<Persoon>();
	deRekeningen = new ArrayList<Rekening>();
	Persoon pers = null;

	try
	{

	    ResultSet resultSet = allePersonen.executeQuery();
	    while (resultSet.next())
	    {
		pers = new Persoon (resultSet.getString("bsn"), resultSet.getString("naam"));
		for (Rekening rek: getRekeningen(pers)) // zie verder 
		{
		    deRekeningen.add(rek);
		}
		dePersonen.add (pers);
	    }
	    addRekeningenToPersoon(deRekeningen, pers);


	}
	catch (Exception ex)
	{
	    System.out.println (ex);
	    ex.printStackTrace();

	}
    }


    private void addRekeningenToPersoon(ArrayList<Rekening> rekList, Persoon p)
    {
	p.clearRekeningen();

	for(Rekening r : rekList)
	{
	    if (r.getPersoon() == p)
	    {
		p.voegRekeningToe(r);
	    }
	}
    }

    // ophalen uit de database .. de rekeningen van een geselecteerd persoon
    // deze methode wordt gebruikt in 'refresh()' 
    private ArrayList<Rekening> getRekeningen(Persoon pers)
    {
	ArrayList<Rekening> rekList = new ArrayList<Rekening>();
	// gebruik het PreparedStatement 'geselecteerdeRekening' 
	try
	{
	    geselecteerdeRekening.setString(1, pers.getBsn());
	    ResultSet rs = geselecteerdeRekening.executeQuery();
	    while(rs.next())
	    {
		Rekening r = new Rekening(rs.getString(1), pers);
		rekList.add(r);
	    }

	} catch (SQLException ex)
	{
	    System.out.println(ex);
	    //ex.printStackTrace();
	}
	return rekList;


    }

    // sluit alle PreparedStatements en 
    // de database-connectie 
    public void close()
    {
	try
	{
	    insertPersoon.close();
	    insertRekening.close();
	    geselecteerdeRekening.close();
	    allePersonen.close();
	    deDbConnectie.close();
	}
	catch (Exception ex)
	{
	    System.out.println (ex);
	}
    }
}