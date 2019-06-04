package it.unisalento.se.saw.dto;

public class LezioneDTO {
	
    private int idLezione;
    private String orarioInizio;
    private String orarioFine;
    private String data;

    private int idAula;
    private int idCalendario;
    private int idInsegnamento;
    
    

//	
//	public LezioneDTO(Integer idLezione, String orarioInizio, String orarioFine, String data, int idAula,
//			int idCalendario, int idInsegnamento) {
//		this.idLezione = idLezione;
//		this.orarioInizio = orarioInizio;
//		this.orarioFine = orarioFine;
//		this.data = data;
//		this.idAula = idAula;
//		this.idCalendario = idCalendario;
//		this.idInsegnamento = idInsegnamento;
//	}
	


	public int getIdLezione() {
		return idLezione;
	}
	public void setIdLezione(int idLezione) {
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
	
	
	public int getIdAula() {
		return idAula;
	}
	
	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}
	
	public int getIdCalendario() {
		return idCalendario;
	}
	
	public void setIdCalendario(int idCalendario) {
		this.idCalendario = idCalendario;
	}
	
	public int getIdInsegnamento() {
		return idInsegnamento;
	}


	public void setIdInsegnamento(int idInsegnamento) {
		this.idInsegnamento = idInsegnamento;
	}
	

    

}
