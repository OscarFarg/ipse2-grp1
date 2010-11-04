package ipse;

public class Artikel 
{
	private int artikelid;
	private String artikelnaam;
	private double prijs;
	
	
	public Artikel( String artikelnaam, double prijs)
	{
		this.artikelid = artikelid;
		this.artikelnaam = artikelnaam;
		this.prijs = prijs;
	}


	public int getArtikelid() 
	{
		return artikelid;
	}


	public String getArtikelnaam() 
	{
		return artikelnaam;
	}


	public double getPrijs() 
	{
		return prijs;
	}
	
	
}
