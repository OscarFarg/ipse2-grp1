package bankapplicatie;

import java.util.ArrayList;

public class Persoon 
{
    private String bsn;
    private String naam;
    private ArrayList<Rekening> rekList;

    public Persoon(String bsn, String naam) 
    {
	this.bsn = bsn;
	this.naam = naam;
	rekList = new ArrayList<Rekening>();
    }

    public void voegRekeningToe(Rekening rek)
    {
	rekList.add(rek);
    }

    public void verwijderRek(Rekening rek)
    {
	rekList.remove(rek);
    }

    public String getBsn() 
    {
	return bsn;
    }

    public String getNaam() 
    {
	return naam;
    }

    public ArrayList<Rekening> getRekeningen() 
    {
	return rekList;
    }

    public void clearRekeningen()
    {
	rekList = new ArrayList<Rekening>();
    }
    public void voegtoeRek(Rekening rek)
    {
	voegRekeningToe(rek);
    }
}
