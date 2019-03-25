package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.domain.Studente;

public interface ISegreteriaService {
	
	public Segreteria logSegreteria(int idMatricola);
	
	public List<Segreteria> findAll();
	
	public Segreteria getByMatricola(int idMatricola);
	
	public Segreteria save(Segreteria segreteria);

	public Segreteria updateSegByMatricola(int matricola);


	

}
