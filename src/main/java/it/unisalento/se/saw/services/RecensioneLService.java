package it.unisalento.se.saw.services;

import java.util.List;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IRecensioneLService;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Recensionel;
import it.unisalento.se.saw.repositories.RecensioneLRepository;

@Service
public class RecensioneLService implements IRecensioneLService {
	
	@Autowired
	RecensioneLRepository recensioneLRepository;
	
	
	@Transactional
	public List<Recensionel> findAll() {
		return recensioneLRepository.findAll();
	}
	 
	@Transactional
	public List<Recensionel> getRecLByInsegnamento(String string) {
		return recensioneLRepository.getRecLByInsegnamento(string);
	}
	
	@Transactional
	public List<Recensionel> getRecLByVoto(int voto) {
		return recensioneLRepository.getRecLByVoto(voto);
	}
	
	@Transactional
	public Recensionel getById(int idRecensioneL) {
		return recensioneLRepository.getOne(idRecensioneL);
	}
	
	@Transactional
	public Recensionel save(Recensionel recensioneL) {
		return recensioneLRepository.save(recensioneL);
	}
	
	//nuovo metodo
	@Transactional
	public Recensionel getByMatricolaStudIdInsegIdLez(int idMatricola, int idInsegnamento, int idLezione) {
		return recensioneLRepository.getByMatricolaStudIdInsegIdLez(idMatricola, idInsegnamento, idLezione);
	}

	//nuovo metodo
	@Transactional
	public List<Recensionel> getRecLByIdLezione(int idLezione){
		return recensioneLRepository.getRecLByIdLezione(idLezione);
	}






}
