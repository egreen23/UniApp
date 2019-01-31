package it.unisalento.se.saw.dto;

public class InsegnamentoDTO {
	
    private Integer idInsegnamento;
    private String nome;
    private Integer crediti;
    private String descrizione;
    private String annoCorso;
    
    
    
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
    
    
    

}
