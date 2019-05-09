package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.ISegnalazioneService;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.repositories.SegnalazioneRepository;

@Service
public class SegnalazioneService implements ISegnalazioneService {

	@Autowired
	SegnalazioneRepository segnalazioneRepository;
	
	@Transactional
	public List<Segnalazione> findAll() {
		return segnalazioneRepository.findAll();
	}
	
	@Transactional
	public List<Segnalazione> getByStato(String string) {
		return segnalazioneRepository.getByStato(string);
	}
	
	@Transactional
	public List<Segnalazione> getByDocente(String cognome, String nome) {
		return segnalazioneRepository.getByDocente(cognome, nome);
	}
	
	@Transactional
	public Segnalazione getById(int idSegnalazione) {
		return segnalazioneRepository.getOne(idSegnalazione);
	}
	
	@Transactional
	public Segnalazione save(Segnalazione segnalazione) {
		return segnalazioneRepository.save(segnalazione);
	}
	
	@Transactional
	public Segnalazione updateStatoSegnal(int idSegnalazione) {
		return segnalazioneRepository.getOne(idSegnalazione);
	}
	
	
}
