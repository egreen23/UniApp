package it.unisalento.se.saw.dto;

public class AulaDTO {
	
    private Integer idAula;
    private String nome;
    private Double longitudine;
    private Double latitudine;
    private String edificio;
    private String piano;
    
    
    
	public Integer getIdAula() {
		return idAula;
	}
	public void setIdAula(Integer idAula) {
		this.idAula = idAula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(Double longitudine) {
		this.longitudine = longitudine;
	}
	public Double getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(Double latitudine) {
		this.latitudine = latitudine;
	}
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	public String getPiano() {
		return piano;
	}
	public void setPiano(String piano) {
		this.piano = piano;
	}
    

}
