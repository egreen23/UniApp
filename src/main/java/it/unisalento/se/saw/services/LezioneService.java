package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	public Lezione save(Lezione lezione) {
		return lezioneRepository.save(lezione);
	}
	
	@Transactional
	public Lezione getLezioneById(int idLezione) {
		return lezioneRepository.getOne(idLezione);
	}

	@Transactional
	public void deleteLezione(int id) {
		lezioneRepository.deleteById(id);
		return;
	}
	
<<<<<<< HEAD
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Lezione> saveAll(List<Lezione> list) {
		return lezioneRepository.saveAll(list);
=======
	//nuovo metodo CH
	@Transactional
	public List<Lezione> getLezioniByIdDocente(int idDocente){
		return lezioneRepository.getLezioniByIdDocente(idDocente);
	}
	
	//nuovo metodo CH
	@Transactional
	public List<Lezione> getCalLezioniByCorso(String nome){
		return lezioneRepository.getLezioniByCorso(nome);
	}
	
	//nuovo metodo CH
	@Transactional
	public List<Lezione> getLezioniByIdInsegnamento(int idInsegnamento){
		return lezioneRepository.getLezioniByIdInsegnamento(idInsegnamento);
>>>>>>> master
	}

	
	@Transactional
	public List<Lezione> getLezionibyIdAula(int id) {
		return lezioneRepository.getLezionibyIdAula(id);
	}
	
	//nuovo metodo CH
	@Transactional
	public List<Lezione> getLezioniByIdDocente(int idDocente){
		return lezioneRepository.getLezioniByIdDocente(idDocente);
	}
		
	//nuovo metodo CH
	@Transactional
	public List<Lezione> getCalLezioniByCorso(String nome){
		return lezioneRepository.getLezioniByCorso(nome);
	}
		
	//nuovo metodo CH
	@Transactional
	public List<Lezione> getLezioniByIdInsegnamento(int idInsegnamento){
		return lezioneRepository.getLezioniByIdInsegnamento(idInsegnamento);
	}


}
