package ipse;

public class Artikel 
{
	private int artikelid;
	private String artikelNaam;
	private double prijs;
	
	
	public Artikel( int artikelid, String artikelNaam, double prijs)
	{
		this.artikelid = artikelid;
		this.artikelNaam = artikelNaam;
		this.prijs = prijs;
	}


	public int getArtikelid() 
	{
		return artikelid;
	}


	public String getArtikelNaam() 
	{
		return artikelNaam;
	}


	public double getPrijs() 
	{
		return prijs;
	}
	
	
}
