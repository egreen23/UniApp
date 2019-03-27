package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.ILezioneService;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.repositories.LezioneRepository;

@Service
public class LezioneService implements ILezioneService {
	
	@Autowired
	LezioneRepository lezioneRepository;
	
	
	@Transactional
	public List<Lezione>findAll(){
		return lezioneRepository.findAll();
	};
	
	@Transactional
	public List<Lezione> getLezioniByIdCalendario(int idCalendario){
		return lezioneRepository.getLezioniByIdCalendario(idCalendario);
	}
	
	@Transactional
	public List<Lezione> getLezioniByDocente(String cognome, String nome){
		return lezioneRepository.getLezioniByDocente(cognome, nome);
	}
	
	@Transactional
	public List<Lezione> getLezioniByInsegnamento(String nome){
		return lezioneRepository.getLezioniByInsegnamento(nome);
	}
	
	@Transactional
	public List<Lezione> getLezioniByCorso(String nome){
		return lezioneRepository.getLezioniByCorso(nome);
	}
	
	@Transactional
	public Lezione updateById(int idLezione) {
		return lezioneRepository.getOne(idLezione);
	}
	
	@Transactional
	public Lezione save(Lezione lezione) {
		return lezioneRepository.save(lezione);
	}
	
	@Transactional
	public Lezione getLezioneById(int idLezione) {
		return lezioneRepository.getOne(idLezione);
	}
	
//	@Transactional
//	public void deleteLez(int idLezione) {
//		lezioneRepository.deleteLez(idLezione);
//	}


}
