package it.unisalento.se.saw.dto;


public class StudenteDTO extends UserDTO {
	
    private int idStudente;
    private String annoIscrizione;
    
    
    
	public int getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}
	public String getAnnoIscrizione() {
		return annoIscrizione;
	}
	public void setAnnoIscrizione(String annoIscrizione) {
		this.annoIscrizione = annoIscrizione;
	}
    
    
}
