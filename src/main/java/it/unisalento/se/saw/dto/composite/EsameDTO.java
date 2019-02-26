package it.unisalento.se.saw.dto.composite;

import net.minidev.json.JSONObject;

public class EsameDTO extends CalendarioComponent {
	
    private Integer idEsame;
    private String data;
    private String orarioInizio;
    private String orarioFine;
    
    private String nomeAula;
    private String nomeInsegnamento;
    private String nomeDocente;
    private String cognomeDocente;
    private int crediti;
    
    private String nomeCorso;
    private String tipoCorso;
    
    
	public EsameDTO(Integer idEsame, String data, String orarioInizio, String orarioFine, String nomeAula, String nomeInsegnamento, 
		    String nomeDocente, String cognomeDocente, int crediti, String nomeCorso, String tipoCorso) {
		super();
		this.idEsame = idEsame;
		this.data = data;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		
		this.nomeAula = nomeAula;
		this.nomeInsegnamento = nomeInsegnamento;
		this.nomeDocente = nomeDocente;
		this.cognomeDocente = cognomeDocente;
		this.crediti = crediti;
		this.nomeCorso = nomeCorso;
		this.tipoCorso = tipoCorso;
		
	}
	
	
	public Integer getIdEsame() {
		return idEsame;
	}
	public void setIdEsame(Integer idEsame) {
		this.idEsame = idEsame;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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


	@Override
    public JSONObject toJson() {
		
		JSONObject esameJson = new JSONObject();
		
		esameJson.put("idEsame", idEsame);
		esameJson.put("data", data);
		esameJson.put("orarioInizio", orarioInizio);
		esameJson.put("orarioFine", orarioFine);
		esameJson.put("nomeAula", nomeAula);
		esameJson.put("nomeInsegnamento", nomeInsegnamento);
		esameJson.put("nomeDocente", nomeDocente);
		esameJson.put("cognomeDocente", cognomeDocente);
		esameJson.put("crediti", crediti);
		esameJson.put("nomeCorso", nomeCorso);
		esameJson.put("tipoCorso", tipoCorso);
		
		return esameJson;	
	
	}
    
    
}
