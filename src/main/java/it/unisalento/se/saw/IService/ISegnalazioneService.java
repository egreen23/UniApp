package it.unisalento.se.saw.IService;

import java.util.List;

import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Segnalazione;

public interface ISegnalazioneService {
	
	public List<Segnalazione> findAll();
	
	public List<Segnalazione> getByStato(String string);
	
	public List<Segnalazione> getByDocente(String cognome, String nome);
	
	public Segnalazione getById(int idSegnalazione);
	
	public Segnalazione save(Segnalazione segnalazione);
	
	public List<Segnalazione> getByidDocente(int id);
	
	public List<Segnalazione> getByidAula(int id);
	
	public List<Segnalazione> getByidSegr(int id);

	
	
		
	

}
