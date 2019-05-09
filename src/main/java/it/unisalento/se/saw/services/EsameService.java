package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IEsameService;
import it.unisalento.se.saw.domain.Esame;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.repositories.EsameRepository;

@Service
public class EsameService implements IEsameService {
	
	@Autowired
	EsameRepository esameRepository;
	
	
	@Transactional
	public List<Esame>findAll(){
		return esameRepository.findAll();
	}
	
	@Transactional
	public List<Esame> getEsameByIdCalendario(int idCalendario){
		return esameRepository.getEsameByIdCalendario(idCalendario);
	}
	
	@Transactional
	public List<Esame> getEsamiByDocente(String cognome, String nome){
		return esameRepository.getEsamiByDocente(cognome, nome);
	}
	
	@Transactional
	public List<Esame> getEsamiByInsegnamento(String nome){
		return esameRepository.getEsamiByInsegnamento(nome);
	}
	
	@Transactional
	public List<Esame> getEsamiByCorso(String nome){
		return esameRepository.getEsamiByCorso(nome);
	}
	
	@Transactional
	public Esame updateById(int idLezione){
		return esameRepository.getOne(idLezione);
	}
	
	@Transactional 
	public Esame save(Esame esame) {
		return esameRepository.save(esame);
	}
	
	@Transactional
	public Esame getEsameById(int idEsame) {
		return esameRepository.getOne(idEsame);
	}
	
//	@Transactional
//	public void deleteEsameById(int idEsame) {
//		esameRepository.deleteById(idEsame);
//	}
	
	
}
