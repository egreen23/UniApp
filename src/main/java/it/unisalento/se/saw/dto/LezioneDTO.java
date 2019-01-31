package it.unisalento.se.saw.dto;

public class LezioneDTO {
	
    private Integer idLezione;
    private String orarioInizio;
    private String orarioFine;
    private String data;
    
    
    
	public Integer getIdLezione() {
		return idLezione;
	}
	public void setIdLezione(Integer idLezione) {
		this.idLezione = idLezione;
	}
	public String getOrarioInizio() {
		return orarioInizio;
	}
	public void setOrarioInizio(String orarioInizio) {
		this.orarioInizio = orarioInizio;
	}
	public String getOrarioFine() {
		return orarioFine;
	}
	public void setOrarioFine(String orarioFine) {
		this.orarioFine = orarioFine;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
    

}
