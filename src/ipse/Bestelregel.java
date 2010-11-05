package ipse;

public class Bestelregel
{
	private int bestelnr, artikelid, aantal;
	private double prijs, totaalPrijs;
	
	public Bestelregel(int bestelnr, int artikelid, double prijs, int aantal, double totaalPrijs)
	{
		this.bestelnr = bestelnr;
		this.artikelid = artikelid;
		this.prijs = prijs;
		this.aantal = aantal;
		this.totaalPrijs = totaalPrijs;;
	}

	public int getBestelnr()
	{
		return bestelnr;
	}

	public int getArtikelid()
	{
		return artikelid;
	}

	public int getAantal()
	{
		return aantal;
	}

	public double getPrijs()
	{
		return prijs;
	}

	public double getTotaalPrijs()
	{
		return totaalPrijs;
	}
}
