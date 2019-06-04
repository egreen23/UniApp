package it.unisalento.se.saw.dto;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Tool;

public class AttrezzaturaDTO {
	
    private Integer idAttrezzatura;
    private int idAula;
    private int idTool;
	private String nomeTool;
    private String descrizioneTool;
    
    
    public String getNomeTool() {
		return nomeTool;
	}

	public void setNomeTool(String nomeTool) {
		this.nomeTool = nomeTool;
	}

	public String getDescrizioneTool() {
		return descrizioneTool;
	}

	public void setDescrizioneTool(String descrizioneTool) {
		this.descrizioneTool = descrizioneTool;
	}

	public Integer getIdAttrezzatura() {
		return idAttrezzatura;
	}

	public void setIdAttrezzatura(Integer idAttrezzatura) {
		this.idAttrezzatura = idAttrezzatura;
	}

	public int getIdAula() {
		return idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public int getIdTool() {
		return idTool;
	}

	public void setIdTool(int idTool) {
		this.idTool = idTool;
	}
	
	
	
	
	
	
	
    


}
