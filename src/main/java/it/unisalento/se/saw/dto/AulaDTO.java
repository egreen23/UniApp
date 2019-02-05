package it.unisalento.se.saw.dto;

public class AulaDTO {
	
    private Integer idAula;
    private String nome;
    private Long longitudine;
    private Long latitudine;
    private String edificio;
    
    
    
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
	public Long getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(Long longitudine) {
		this.longitudine = longitudine;
	}
	public Long getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(Long latitudine) {
		this.latitudine = latitudine;
	}
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
    

}
