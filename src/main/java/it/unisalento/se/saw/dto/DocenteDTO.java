package it.unisalento.se.saw.dto;

public class DocenteDTO extends UserDTO {
	
    private int idDocente;
    private String professore;
    
    

	
	public String getProfessore() {
		return professore;
	}

	public void setProfessore(String professore) {
		this.professore = professore;
	}
    
	public int getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
    
    
    


}