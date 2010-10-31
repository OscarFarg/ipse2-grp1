package ipse;

public class Medewerker 
{
	private String voornaam, tussenvoegsel, achternaam, functie, chefId, mwStatus;
	
	public Medewerker( String voornaam, String tussenvoegsel, String achternaam, String functie, String chefId, String mwStatus )
	{
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.functie = functie;
		this.chefId = chefId;
		this.mwStatus = mwStatus;
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

	public String getChefId() {
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

	public void setChefId(String chefId) {
		this.chefId = chefId;
	}

	public void setMwStatus(String mwStatus) {
		this.mwStatus = mwStatus;
	}
}
