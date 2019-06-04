package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Aula;

public interface IAulaService {
	
	public List<Aula> findAll();
	
	public Aula save(Aula aula);

	
	public Aula getById(int idAula);
		
	public Aula getByNomeAula(String string);
		
	public void deleteAula(int id);
	
	public void deleteAll(List<Aula> list);
	



		
}
