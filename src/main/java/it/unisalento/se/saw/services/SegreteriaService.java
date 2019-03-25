package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.ISegreteriaService;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.repositories.SegreteriaRepository;

@Service
public class SegreteriaService implements ISegreteriaService{
	
	@Autowired
	SegreteriaRepository segreteriaRepository;
	
	
	@Transactional
	public Segreteria logSegreteria(int idMatricola){
		return segreteriaRepository.logSegreteria(idMatricola);
	}
	
	@Transactional
	public List<Segreteria> findAll(){
		return segreteriaRepository.findAll();
	}
		
	@Transactional
	public Segreteria getByMatricola(int idMatricola) {
		return segreteriaRepository.logSegreteria(idMatricola);
	}
	
	@Transactional
	public Segreteria save(Segreteria segreteria) {
		return segreteriaRepository.saveAndFlush(segreteria);
	}
	
	@Transactional
	public Segreteria updateSegByMatricola(int matricola) {
		return segreteriaRepository.logSegreteria(matricola);
	}

}
