package it.unisalento.se.saw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IAulaService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.repositories.AulaRepository;

@Service
public class AulaService implements IAulaService {

	@Autowired
	AulaRepository aulaRepository;

	
	@Transactional
	public List<Aula> findAll() {
		return aulaRepository.findAll();
	}
	
	
	
	@Transactional
	public Aula save(Aula aula) {
		return aulaRepository.save(aula);
	}
	
	
	
	@Transactional
	public Aula updateAulaById(int idAula) {
		return aulaRepository.getOne(idAula);
	}
	
	
	
	@Transactional
	public Aula getById(int idAula) throws AulaNotFoundException {
		try {
			Aula aula = aulaRepository.getOne(idAula);
			return aula;
		} catch (Exception e) {
			throw new AulaNotFoundException();
		}
	}
	
	
/*FARE @Query e Iservice Aula */
	
//	@Transactional
//	public Aula getByName(String nome) throws AulaNotFoundException {
//		try {
//			Aula aula = aulaRepository.getOne(nome);
//			return aula;
//		} catch (Exception e) {
//			throw new AulaNotFoundException();
//		}
//	}
	
	
	
	@Transactional
	public List<Aula> getByName(String string) {
		return aulaRepository.getByName(string);
	}
	

//	Query Niko prende solo il nome dell'aulae da un OGETTO 
//	
//	@Transactional
//	public Aula getByName(String string) {
//		return aulaRepository.getByName(string);
//	}

	

//	@Transactional
//	public void deleteAulaById(int idAula) throws AulaNotFoundException {
//		// TODO Auto-generated method stub
//		try {
//			Aula aula = aulaRepository.getOne(idAula);
//			aulaRepository.delete(aula);
//		} catch (Exception e) {
//			throw new AulaNotFoundException();
//		}
//
//	}
	
	


}
