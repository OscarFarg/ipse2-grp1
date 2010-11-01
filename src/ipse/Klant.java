package ipse;

public class Klant {
	private String id;
	private String voornaam;
	private String tussenvoegsel;
	private String rekeningNr;
	private String betaalStatus;
	private String klantStatus;
	
	public Klant()
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
	public String getKlantStatus() {
		return klantStatus;
	}
	public void setKlantStatus(String klantStatus) {
		this.klantStatus = klantStatus;
	}
	
	

}
