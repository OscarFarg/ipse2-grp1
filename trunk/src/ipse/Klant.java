package ipse;

public class Klant {
	private String id;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private String rekeningNr;
	private String betaalStatus;
	private String status;
	
	public Klant()
	{
		
	}
	
	public Klant(String id, String voornaam, String tussenvoegsel, String achternaam, String rekeningNr, String betaalStatus, String status)
	{
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
