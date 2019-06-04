package it.unisalento.se.saw.IService;

import java.util.List;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Studente;

public interface IDocenteService {
	
	public Docente logDocente(int idMatricola);
	
	public List<Docente> findAll();
		
	public Docente save(Docente docente);
	


}
