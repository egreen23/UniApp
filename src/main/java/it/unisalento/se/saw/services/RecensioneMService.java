package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IRecensioneMService;
import it.unisalento.se.saw.domain.Recensionel;
import it.unisalento.se.saw.domain.Recensionem;
import it.unisalento.se.saw.repositories.RecensioneLRepository;
import it.unisalento.se.saw.repositories.RecensioneMRepository;

@Service
public class RecensioneMService implements IRecensioneMService {
	
	@Autowired
	RecensioneMRepository recensioneMRepository; 
	
	
	@Transactional
	public List<Recensionem> findAll() {
		return recensioneMRepository.findAll();
	}
	 
	@Transactional
	public List<Recensionem> getRecMatByInsegnamento(String string) {
		return recensioneMRepository.getRecMatByInsegnamento(string);
	}
	
	@Transactional
	public List<Recensionem> getRecMatByVoto(int voto) {
		return recensioneMRepository.getRecMatByVoto(voto);
	}
	
	@Transactional
	public Recensionem getById(int idRecensioneM) {
		return recensioneMRepository.getOne(idRecensioneM);
	}
	
	@Transactional
	public Recensionem save(Recensionem recensioneM) {
		return recensioneMRepository.save(recensioneM);
	}
	
	@Transactional
	public Recensionem updateRecMatById(int idRecensioneM) {
		return recensioneMRepository.getOne(idRecensioneM);
	}






}
