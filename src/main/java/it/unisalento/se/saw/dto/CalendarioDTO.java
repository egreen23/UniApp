package it.unisalento.se.saw.dto;

public class CalendarioDTO {
	
    private int idCalendario;
    private String tipo;
    private String anno;
    private String dataInizio;
    private String dataFine;
    private String semestre;
	private int idCds; //08/05/2019  niko + get e set metodi
    
    
    
	public int getIdCalendario() {
		return idCalendario;
	}
	public void setIdCalendario(int idCalendario) {
		this.idCalendario = idCalendario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	public int getIdCds() {
			return idCds;
	}
	
	public void setIdCds(int idCds) {
			this.idCds = idCds;
		}
	
	
	
    
    

}
