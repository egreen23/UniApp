package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Aula;

public interface IAulaService {
	
	public List<Aula> findAll();
	
	public Aula save(Aula aula);

	
	public Aula getById(int idAula);
		
<<<<<<< HEAD
	public Aula getByNomeAula(String string);
		
	public void deleteAula(int id);
=======
	public List<Aula> getByName(String string);
	
	//NUOVO CH
	public Aula getByNomeAula(String string);

	
//	Query Niko prende solo il nome dell'aulae da un OGETTO 
//	public Aula getByName(String string);
>>>>>>> master
	
	public void deleteAll(List<Aula> list);
	



		
}
