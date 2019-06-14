package it.unisalento.se.saw.IService;

import java.util.List;


import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Tool;

public interface IStudenteService {
		
	public Studente logStudent(int idMatricola);
	
	public List<Studente> findAll();
	
	public Studente getByMatricola(int idMatricola);
	
	public Studente save(Studente studente);
	
	public Studente updateStudByMatricola(int matricola);

	
	public List<Studente> getAllStudByIdCdS(int idCdS);

	
	

}
