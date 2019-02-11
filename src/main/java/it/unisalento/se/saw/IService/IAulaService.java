package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;

public interface IAulaService {
	
	public List<Aula> findAll();
	
	public Aula save(Aula aula);

	public Aula updateAulaById(int idAula);
	
	public Aula getById(int idAula) throws AulaNotFoundException;
		
	public List<Aula> getByName(String string);
	
//	Query Niko prende solo il nome dell'aulae da un OGETTO 
//	public Aula getByName(String string);
	
	
//	public void deleteAulaById(int idAula) throws AulaNotFoundException;


		
}
