package it.unisalento.se.saw.dto;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segreteria;

public class SegnalazioneDTO {
	
    private int idSegnalazione;
    private String testo;
    private String stato;
    private int idDocente;
    private String nomeDocente;
    private String cognomeDocente;
    private String emailDocente;
    private int idSegreteria;
    private String nomeSegretario;
    private String cognomeSegretario;
    private String emailSegreteria;

    
    
    
    
	public int getIdSegnalazione() {
		return idSegnalazione;
	}
	public void setIdSegnalazione(int idSegnalazione) {
		this.idSegnalazione = idSegnalazione;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	public String getNomeDocente() {
		return nomeDocente;
	}
	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}
	public String getCognomeDocente() {
		return cognomeDocente;
	}
	public void setCognomeDocente(String cognomeDocente) {
		this.cognomeDocente = cognomeDocente;
	}
	public int getIdSegreteria() {
		return idSegreteria;
	}
	public void setIdSegreteria(int idSegreteria) {
		this.idSegreteria = idSegreteria;
	}
	public String getNomeSegretario() {
		return nomeSegretario;
	}
	public void setNomeSegretario(String nomeSegretario) {
		this.nomeSegretario = nomeSegretario;
	}
	public String getCognomeSegretario() {
		return cognomeSegretario;
	}
	public void setCognomeSegretario(String cognomeSegretario) {
		this.cognomeSegretario = cognomeSegretario;
	}
	public String getEmailDocente() {
		return emailDocente;
	}
	public void setEmailDocente(String emailDocente) {
		this.emailDocente = emailDocente;
	}
	public String getEmailSegreteria() {
		return emailSegreteria;
	}
	public void setEmailSegreteria(String emailSegreteria) {
		this.emailSegreteria = emailSegreteria;
	}
	
	
    
    
    
    
    

}
