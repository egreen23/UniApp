package it.unisalento.se.saw.services;

import java.util.List;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IRecensioneLService;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.repositories.RecensioneLRepository;

@Service
public class RecensioneLService implements IRecensioneLService {
	
	@Autowired
	RecensioneLRepository recensioneLRepository;
	
	
	@Transactional
	public List<RecensioneL> findAll() {
		return recensioneLRepository.findAll();
	}
	 
	@Transactional
	public List<RecensioneL> getRecLByInsegnamento(String string) {
		return recensioneLRepository.getRecLByInsegnamento(string);
	}
	
	@Transactional
	public List<RecensioneL> getRecLByVoto(int voto) {
		return recensioneLRepository.getRecLByVoto(voto);
	}
	
	@Transactional
	public RecensioneL getById(int idRecensioneL) {
		return recensioneLRepository.getOne(idRecensioneL);
	}
	
	@Transactional
	public RecensioneL save(RecensioneL recensioneL) {
		return recensioneLRepository.save(recensioneL);
	}
	
	public RecensioneL updateRecLezById(int idRecensioneL) {
		return recensioneLRepository.getOne(idRecensioneL);
	}






}
