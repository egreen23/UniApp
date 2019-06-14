package it.unisalento.se.saw.dto;

public class MaterialeDTO {
	
    private Integer idMateriale;
    private String nome;
    private String url;
    private String data;
    
    private int idInsegnamento;
    private String nomeInsegnamento;
  
    private String nomeCorsoDiStudio;
    private String tipo;
    
    
    
	public Integer getIdMateriale() {
		return idMateriale;
	}
	public void setIdMateriale(Integer idMateriale) {
		this.idMateriale = idMateriale;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getNomeCorsoDiStudio() {
		return nomeCorsoDiStudio;
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
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}    
    

}
