package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IRecensioneMService;
<<<<<<< HEAD
import it.unisalento.se.saw.domain.Recensionel;
import it.unisalento.se.saw.domain.Recensionem;
=======
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.domain.RecensioneM;
>>>>>>> master
import it.unisalento.se.saw.repositories.RecensioneLRepository;
import it.unisalento.se.saw.repositories.RecensioneMRepository;

@Service
public class RecensioneMService implements IRecensioneMService {
	
	@Autowired
	RecensioneMRepository recensioneMRepository; 
	
	
	@Transactional
<<<<<<< HEAD
	public List<Recensionem> findAll() {
=======
	public List<RecensioneM> findAll() {
>>>>>>> master
		return recensioneMRepository.findAll();
	}
	 
	@Transactional
<<<<<<< HEAD
	public List<Recensionem> getRecMatByInsegnamento(String string) {
=======
	public List<RecensioneM> getRecMatByInsegnamento(String string) {
>>>>>>> master
		return recensioneMRepository.getRecMatByInsegnamento(string);
	}
	
	@Transactional
<<<<<<< HEAD
	public List<Recensionem> getRecMatByVoto(int voto) {
=======
	public List<RecensioneM> getRecMatByVoto(int voto) {
>>>>>>> master
		return recensioneMRepository.getRecMatByVoto(voto);
	}
	
	@Transactional
<<<<<<< HEAD
	public Recensionem getById(int idRecensioneM) {
=======
	public RecensioneM getById(int idRecensioneM) {
>>>>>>> master
		return recensioneMRepository.getOne(idRecensioneM);
	}
	
	@Transactional
<<<<<<< HEAD
	public Recensionem save(Recensionem recensioneM) {
=======
	public RecensioneM save(RecensioneM recensioneM) {
>>>>>>> master
		return recensioneMRepository.save(recensioneM);
	}
	
	//nuovo metodo
	@Transactional
<<<<<<< HEAD
	public Recensionem getByMatricolaStudIdInsegIdMaterial(int idMatricola, int idInsegnamento, int idMateriale) {
		return recensioneMRepository.getByMatricolaStudIdInsegIdMaterial(idMatricola, idInsegnamento, idMateriale);
	}
	//nuovo metodo
	@Transactional
	public List<Recensionem> getRecByIdMateriale(int idMateriale){
		return recensioneMRepository.getRecByIdMateriale(idMateriale);
=======
	public RecensioneM updateRecMatById(int idRecensioneM) {
		return recensioneMRepository.getOne(idRecensioneM);
>>>>>>> master
	}
	
	//nuovo metodo
	@Transactional
	public RecensioneM getByMatricolaStudIdInsegIdMaterial(int idMatricola, int idInsegnamento, int idMateriale) {
		return recensioneMRepository.getByMatricolaStudIdInsegIdMaterial(idMatricola, idInsegnamento, idMateriale);
	}
	//nuovo metodo
	@Transactional
	public List<RecensioneM> getRecByIdMateriale(int idMateriale){
		return recensioneMRepository.getRecByIdMateriale(idMateriale);
	}






}
