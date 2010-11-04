package ipse;

public class Klant
{
	private int id;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private String rekeningNr;
	private String betaalStatus;
	private String status;
	
	public Klant(int id, String voornaam, String tussenvoegsel, String achternaam, String rekeningNr, String betaalStatus, String status)
	{
		this.id = id;
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.rekeningNr = rekeningNr;
		this.betaalStatus = betaalStatus;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}
	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getRekeningNr() {
		return rekeningNr;
	}
	public void setRekeningNr(String rekeningNr) {
		this.rekeningNr = rekeningNr;
	}
	public String getBetaalStatus() {
		return betaalStatus;
	}
	public void setBetaalStatus(String betaalStatus) {
		this.betaalStatus = betaalStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
