package it.unisalento.se.saw.dto.composite;

import it.unisalento.se.saw.domain.Lezione;
import net.minidev.json.JSONObject;

public class LezioneDTOComp extends CalendarioComponent {
	
    private Integer idLezione;
    private String orarioInizio;
    private String orarioFine;
    private String data;
    
    private String nomeAula;
    private String nomeInsegnamento;
    private String nomeDocente;
    private String cognomeDocente;
    private int crediti;
    
    private String nomeCorso;
    private String tipoCorso;
    
//    private int idAula;
//    private int idCalendario;
//    private int idInsegnamento;
    
    
    
    
    
	public LezioneDTOComp(Integer idLezione, String orarioInizio, String orarioFine, String data, String nomeAula, String nomeInsegnamento,
			String nomeDocente, String cognomeDocente, int crediti, String nomeCorso, String tipoCorso) {
		super();
		this.idLezione = idLezione;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.data = data;
		this.nomeAula = nomeAula;
		this.nomeInsegnamento = nomeInsegnamento;
		this.nomeDocente = nomeDocente;
		this.cognomeDocente = cognomeDocente;
		this.crediti = crediti;
		this.nomeCorso = nomeCorso;
		this.tipoCorso = tipoCorso;
	}



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
	
	public String getNomeAula() {
		return nomeAula;
	}
	
	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}
	
	public String getNomeInsegnamento() {
		return nomeInsegnamento;
	}

	public void setNomeInsegnamento(String nomeInsegnamento) {
		this.nomeInsegnamento = nomeInsegnamento;
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
	
	public int getCrediti() {
		return crediti;
	}
	
	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}
	

	
	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public String getTipoCorso() {
		return tipoCorso;
	}

	public void setTipoCorso(String tipoCorso) {
		this.tipoCorso = tipoCorso;
	}
	
//	public int getIdAula() {
//		return idAula;
//	}
//	
//	public void setIdAula(int idAula) {
//		this.idAula = idAula;
//	}
//	
//	public int getIdCalendario() {
//		return idCalendario;
//	}
//	
//	public void setIdCalendario(int idCalendario) {
//		this.idCalendario = idCalendario;
//	}
//	
//	public int getIdInsegnamento() {
//		return idInsegnamento;
//	}
//
//
//	public void setIdInsegnamento(int idInsegnamento) {
//		this.idInsegnamento = idInsegnamento;
//	}


	@Override
    public JSONObject toJson() {
		
		JSONObject lezJson = new JSONObject();
		
		lezJson.put("idLezione", idLezione);
		lezJson.put("orarioInizio",orarioInizio);
		lezJson.put("orarioFine", orarioFine);
		lezJson.put("data", data);
		lezJson.put("nomeAula", nomeAula);
		lezJson.put("nomeInsegnamento", nomeInsegnamento);
		lezJson.put("nomeDocente", nomeDocente);
		lezJson.put("cognomeDocente", cognomeDocente);
		lezJson.put("crediti", crediti);
		lezJson.put("nomeCorso", nomeCorso);
		lezJson.put("tipoCorso", tipoCorso);
		
		return lezJson;	
	
	}
	
	
//	@Override
//	public String toString() {
//		return "LezioneDTOComp [idLezione=" + idLezione + ", orarioInizio=" + orarioInizio + ", orarioFine=" + orarioFine
//				+ ", data=" + data + "]";
//	}
//
//	
	
    

}
