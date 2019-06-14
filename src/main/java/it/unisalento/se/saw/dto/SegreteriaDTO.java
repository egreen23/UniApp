package it.unisalento.se.saw.dto;

public class SegreteriaDTO extends UserDTO {
	
    private int idSegreteria;

	private String segretario;
    
    
    
	public int getIdSegreteria() {
		return idSegreteria;
	}

	public void setIdSegreteria(int idSegreteria) {
		this.idSegreteria = idSegreteria;
	}
    
    
	public String getSegretario() {
			return segretario;
	}

	public void setSegretario(String segretario) {
			this.segretario = segretario;
	}


}
