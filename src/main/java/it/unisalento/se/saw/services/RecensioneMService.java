package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IRecensioneMService;
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.domain.RecensioneM;
import it.unisalento.se.saw.repositories.RecensioneLRepository;
import it.unisalento.se.saw.repositories.RecensioneMRepository;

@Service
public class RecensioneMService implements IRecensioneMService {
	
	@Autowired
	RecensioneMRepository recensioneMRepository; 
	
	
	@Transactional
	public List<RecensioneM> findAll() {
		return recensioneMRepository.findAll();
	}
	 
	@Transactional
	public List<RecensioneM> getRecMatByInsegnamento(String string) {
		return recensioneMRepository.getRecMatByInsegnamento(string);
	}
	
	@Transactional
	public List<RecensioneM> getRecMatByVoto(int voto) {
		return recensioneMRepository.getRecMatByVoto(voto);
	}
	
	@Transactional
	public RecensioneM getById(int idRecensioneM) {
		return recensioneMRepository.getOne(idRecensioneM);
	}
	
	@Transactional
	public RecensioneM save(RecensioneM recensioneM) {
		return recensioneMRepository.save(recensioneM);
	}
	
	@Transactional
	public RecensioneM updateRecMatById(int idRecensioneM) {
		return recensioneMRepository.getOne(idRecensioneM);
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
