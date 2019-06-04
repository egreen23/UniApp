package it.unisalento.se.saw.dto;

public class CalendarioDTO {
	
    private int idCalendario;
    private String tipo;
    private String anno;
    private String data_inizio;
    private String data_fine;
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
		return data_inizio;
	}
	public void setDataInizio(String dataInizio) {
		this.data_inizio = dataInizio;
	}
	public String getDataFine() {
		return data_fine;
	}
	public void setDataFine(String dataFine) {
		this.data_fine = dataFine;
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
