package it.unisalento.se.saw.dto;

public class RecensioneLDTO {
	
    private Integer idRecensioneL;
    private Integer voto;
    private String testo;
    
    private int idLezione;
    private String dataLezione;
    
    private int idInsegnamento;
    private String nomeInsegnamento;
    
    private int idDocente;
    private String nomeDocente;
    private String cognomeDocente;
    
    private int idStudente;
    private int matricolaStudente;
    private String nomeStudente;
    private String cognomeStudente;
    
    private int idcorsoDiStudio;
    private String nomeCorso;
    private String tipoCorso;
    
    
	private String data;
    
    
    
	public Integer getIdRecensioneL() {
		return idRecensioneL;
	}
	public void setIdRecensioneL(Integer idRecensioneL) {
		this.idRecensioneL = idRecensioneL;
	}
	public Integer getVoto() {
		return voto;
	}
	public void setVoto(Integer voto) {
		this.voto = voto;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public int getIdInsegnamento() {
		return idInsegnamento;
	}
	public void setIdInsegnamento(int idInsegnamento) {
		this.idInsegnamento = idInsegnamento;
	}
	public String getNomeInsegnamento() {
		return nomeInsegnamento;
	}
	public void setNomeInsegnamento(String nomeInsegnamento) {
		this.nomeInsegnamento = nomeInsegnamento;
	}
	public String getDataLezione() {
		return dataLezione;
	}
	public void setDataLezione(String dataLezione) {
		this.dataLezione = dataLezione;
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
	public int getIdLezione() {
		return idLezione;
	}
	public void setIdLezione(int idLezione) {
		this.idLezione = idLezione;
	}
	public int getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	public int getMatricolaStudente() {
		return matricolaStudente;
	}
	public void setMatricolaStudente(int matricolaStudente) {
		this.matricolaStudente = matricolaStudente;
	}
	public String getNomeStudente() {
		return nomeStudente;
	}
	public void setNomeStudente(String nomeStudente) {
		this.nomeStudente = nomeStudente;
	}
	public String getCognomeStudente() {
		return cognomeStudente;
	}
	public void setCognomeStudente(String cognomeStudente) {
		this.cognomeStudente = cognomeStudente;
	}
	public int getIdStudente() {
		return idStudente;
	}
	public void setIdStudente(int idStudente) {
		this.idStudente = idStudente;
	}
	public int getIdcorsoDiStudio() {
		return idcorsoDiStudio;
	}
	public void setIdcorsoDiStudio(int idcorsoDiStudio) {
		this.idcorsoDiStudio = idcorsoDiStudio;
	}
	public String getTipoCorso() {
		return tipoCorso;
	}
	public void setTipoCorso(String tipoCorso) {
		this.tipoCorso = tipoCorso;
	}
	public String getNomeCorso() {
		return nomeCorso;
	}
	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	
    

}
