package bankapplicatie;

public class Rekening 
{
    private String nummer;
    private double saldo;
    private Persoon persoon;

    public Rekening(String nummer, Persoon persoon)
    {
	this.nummer = nummer;
	this.persoon = persoon;
	persoon.voegRekeningToe(this);
    }

    public String getNummer() 
    {
	return nummer;
    }

    public double getSaldo() 
    {
	return saldo;
    }

    public Persoon getPersoon() 
    {
	return persoon;
    }

    public void pasSaldoAan(double bedrag)
    {
	this.saldo = bedrag;
    }
}
