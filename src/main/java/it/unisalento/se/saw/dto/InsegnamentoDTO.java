package it.unisalento.se.saw.dto;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;

public class InsegnamentoDTO {
	
    private Integer idInsegnamento;
    private String nome;
    private Integer crediti;
    private String descrizione;
    private String annoCorso;
    
    private int idDocente;
    private int idCorsoDiStudio;
    private String nomeDocente;
    private String cognomeDocente;
    private String  nomeCorsoDiStudio;
    private String tipo;
    
    
    
	public Integer getIdInsegnamento() {
		return idInsegnamento;
	}
	public void setIdInsegnamento(Integer idInsegnamento) {
		this.idInsegnamento = idInsegnamento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCrediti() {
		return crediti;
	}
	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getAnnoCorso() {
		return annoCorso;
	}
	public void setAnnoCorso(String annoCorso) {
		this.annoCorso = annoCorso;
	}
	
	
	
	public int getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	public int getIdCorsoDiStudio() {
		return idCorsoDiStudio;
	}
	public void setIdCorsoDiStudio(int idCorsoDiStudio) {
		this.idCorsoDiStudio = idCorsoDiStudio;
	}
	public String getNomeDocente() {
		return nomeDocente;
	}
	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}
	public String getNomeCorsoDiStudio() {
		return nomeCorsoDiStudio;
	}
	public String getCognomeDocente() {
		return cognomeDocente;
	}
	public void setCognomeDocente(String cognomeDocente) {
		this.cognomeDocente = cognomeDocente;
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
