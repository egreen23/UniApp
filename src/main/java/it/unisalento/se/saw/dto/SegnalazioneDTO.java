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
    private int idAula;
	private String nomeAula;
	private String prof;
	private String seg;
	private String nota;
	private String titolo;
	
	private String data;
    
    
    
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
	public void setNomeSegretario(String nomeSegreterio) {
		this.nomeSegretario = nomeSegreterio;
	}
	public String getCognomeSegretario() {
		return cognomeSegretario;
	}
	public void setCognomeSegretario(String cognomeSegreterio) {
		this.cognomeSegretario = cognomeSegreterio;
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
	
	
	public int getIdAula() {
		return idAula;
	}
	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}
	public String getNomeAula() {
		return nomeAula;
	}
	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}
	
    
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
    
	public String getSeg() {
		return seg;
	}
	public void setSeg(String seg) {
		this.seg = seg;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
}
