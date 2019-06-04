package it.unisalento.se.saw.dto;


public class StudenteDTO extends UserDTO {
	
    private int idStudente;
    private String annoIscrizione;
    
    private int idCorsoDiStudio;
    private String  nomeCorsoDiStudio;
    private String tipo;
    
    private String studente;

    
    
    
	public String getStudente() {
		return studente;
	}
	public void setStudente(String studente) {
		this.studente = studente;
	}
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
	
	
	public int getIdCorsoDiStudio() {
		return idCorsoDiStudio;
	}
	public void setIdCorsoDiStudio(int idCorsoDiStudio) {
		this.idCorsoDiStudio = idCorsoDiStudio;
	}
	public String getNomeCorsoDiStudio() {
		return nomeCorsoDiStudio;
	}
	public void setNomeCorsoDiStudio(String nomeCorsoDiStudio) {
		this.nomeCorsoDiStudio = nomeCorsoDiStudio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
    
    
}
