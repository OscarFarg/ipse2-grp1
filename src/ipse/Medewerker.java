package ipse;

public class Medewerker 
{
	private String voornaam, tussenvoegsel, achternaam, functie, mwStatus;
	private int id, chefId;
	
	public Medewerker(int id, String voornaam, String tussenvoegsel, String achternaam, String functie, int chefId, String mwStatus )
	{
		this.id = id;
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.functie = functie;
		this.chefId = chefId;
		this.mwStatus = mwStatus;
	}

	public int getId()
	{
		return id;
	}
	
	public String getVoornaam() {
		return voornaam;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public String getFunctie() {
		return functie;
	}

	public int getChefId() {
		return chefId;
	}

	public String getMwStatus() {
		return mwStatus;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public void setFunctie(String functie) {
		this.functie = functie;
	}

	public void setChefId(int chefId) {
		this.chefId = chefId;
	}

	public void setMwStatus(String mwStatus) {
		this.mwStatus = mwStatus;
	}
}
