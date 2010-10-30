package ipse;

import java.util.*;

public class Bestelling
{
	private int bestelnr, klantid, medewerkerid;
	private String bestelDatum, leverDatum, betaalDatum;
	private ArrayList<Bestelregel> bestelregels;
	
	public Bestelling(int bestelnr, String bestelDatum, String leverDatum, String betaalDatum, int klantid, int medewerkerid)
	{
		this.bestelnr = bestelnr;
		this.bestelDatum = bestelDatum;
		this.leverDatum = leverDatum;
		this.betaalDatum = betaalDatum;
		this.klantid = klantid;
		this.medewerkerid = medewerkerid;
		bestelregels = new ArrayList<Bestelregel>();
	}
	
	public void voegBestelregelToe(Bestelregel regel)
	{
		bestelregels.add(regel);
	}
	
	public void verwijderBestelregel(Bestelregel regel)
	{
		for (int i = 0; i <  bestelregels.size(); i ++)
		{
			if (bestelregels.get(i) == regel)
			{
				bestelregels.remove(regel);
			}
		}
	}

	public int getBestelnr()
	{
		return bestelnr;
	}

	public int getKlantid()
	{
		return klantid;
	}

	public int getMedewerkerid()
	{
		return medewerkerid;
	}

	public String getBestelDatum()
	{
		return bestelDatum;
	}

	public String getLeverDatum()
	{
		return leverDatum;
	}

	public String getBetaalDatum()
	{
		return betaalDatum;
	}
	
	public ArrayList getBestelregels()
	{
		return bestelregels;
	}
}
