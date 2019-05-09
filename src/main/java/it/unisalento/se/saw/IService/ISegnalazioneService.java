package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Segnalazione;

public interface ISegnalazioneService {
	
	public List<Segnalazione> findAll();
	
	public List<Segnalazione> getByStato(String string);
	
	public List<Segnalazione> getByDocente(String cognome, String nome);
	
	public Segnalazione getById(int idSegnalazione);
	
	public Segnalazione save(Segnalazione segnalazione);
	
	public Segnalazione updateStatoSegnal(int idSegnalazione);
	
		
	

}
