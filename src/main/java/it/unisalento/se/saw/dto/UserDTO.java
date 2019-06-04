package it.unisalento.se.saw.dto;

public class UserDTO {
	
    private int idMatricola;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String dataDiNascita;
    private String indirizzo;
    private String telefono;
    private int IdStudente;
	private int IdDocente;
    private int IdSegreteria;
 	private String tipo;

    
    
	public int getIdMatricola() {
		return idMatricola;
	}
	public void setIdMatricola(int idMatricola) {
		this.idMatricola = idMatricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	    
	 public int getIdStudente() {
		return IdStudente;
	}
	 
	public void setIdStudente(int idStudente) {
		IdStudente = idStudente;
	}
	
	public int getIdDocente() {
		return IdDocente;
	}
	public void setIdDocente(int idDocente) {
		IdDocente = idDocente;
	}
	
	public int getIdSegreteria() {
		return IdSegreteria;
	}
	
	public void setIdSegreteria(int idSegreteria) {
		IdSegreteria = idSegreteria;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
	


}
