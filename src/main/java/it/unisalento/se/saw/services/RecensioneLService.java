package it.unisalento.se.saw.services;

import java.util.List;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IRecensioneLService;
import it.unisalento.se.saw.domain.Insegnamento;
<<<<<<< HEAD
import it.unisalento.se.saw.domain.Recensionel;
=======
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.domain.RecensioneM;
>>>>>>> master
import it.unisalento.se.saw.repositories.RecensioneLRepository;

@Service
public class RecensioneLService implements IRecensioneLService {
	
	@Autowired
	RecensioneLRepository recensioneLRepository;
	
	
	@Transactional
<<<<<<< HEAD
	public List<Recensionel> findAll() {
=======
	public List<RecensioneL> findAll() {
>>>>>>> master
		return recensioneLRepository.findAll();
	}
	 
	@Transactional
<<<<<<< HEAD
	public List<Recensionel> getRecLByInsegnamento(String string) {
=======
	public List<RecensioneL> getRecLByInsegnamento(String string) {
>>>>>>> master
		return recensioneLRepository.getRecLByInsegnamento(string);
	}
	
	@Transactional
<<<<<<< HEAD
	public List<Recensionel> getRecLByVoto(int voto) {
=======
	public List<RecensioneL> getRecLByVoto(int voto) {
>>>>>>> master
		return recensioneLRepository.getRecLByVoto(voto);
	}
	
	@Transactional
<<<<<<< HEAD
	public Recensionel getById(int idRecensioneL) {
=======
	public RecensioneL getById(int idRecensioneL) {
>>>>>>> master
		return recensioneLRepository.getOne(idRecensioneL);
	}
	
	@Transactional
<<<<<<< HEAD
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
=======
	public RecensioneL save(RecensioneL recensioneL) {
		return recensioneLRepository.save(recensioneL);
	}
	
	public RecensioneL updateRecLezById(int idRecensioneL) {
		return recensioneLRepository.getOne(idRecensioneL);
>>>>>>> master
	}
	
	//nuovo metodo
	@Transactional
	public RecensioneL getByMatricolaStudIdInsegIdLez(int idMatricola, int idInsegnamento, int idLezione) {
		return recensioneLRepository.getByMatricolaStudIdInsegIdLez(idMatricola, idInsegnamento, idLezione);
	}

	//nuovo metodo
	@Transactional
	public List<RecensioneL> getRecLByIdLezione(int idLezione){
		return recensioneLRepository.getRecLByIdLezione(idLezione);
	}
	






}
