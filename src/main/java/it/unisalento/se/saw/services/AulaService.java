package it.unisalento.se.saw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IAulaService;
import it.unisalento.se.saw.domain.Attrezzatura;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Esame;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.repositories.AttrezzaturaRepository;
import it.unisalento.se.saw.repositories.AulaRepository;
import it.unisalento.se.saw.repositories.EsameRepository;
import it.unisalento.se.saw.repositories.LezioneRepository;

@Service
public class AulaService implements IAulaService {

	@Autowired 
	AulaRepository aulaRepository;
	
	@Autowired
	AttrezzaturaRepository attRepository;
	
	@Autowired
	LezioneRepository lezRepository;
	
	@Autowired
	EsameRepository esameRepository;

	
	@Transactional
	public List<Aula> findAll() {
		return aulaRepository.findAll();
	}
		
	@Transactional
	public Aula save(Aula aula) {
		return aulaRepository.save(aula);
	}
	
	@Transactional
	public Aula getById(int idAula){
			Aula aula = aulaRepository.getOne(idAula);
			return aula;
	}
	
	@Transactional
	public void deleteAula(int id) {
		//elimino corrispondenze in tab M:M
		List<Attrezzatura> listAtt = attRepository.getAttrezzaturabyIdAula(id);
		if (listAtt.isEmpty() == false) {
			for (Attrezzatura a : listAtt) {
				attRepository.deleteById(a.getIdAttrezzatura());
			}
		}
		
		//elimino lezioni e esami con l'id dell'aula
		List<Lezione> listLez = lezRepository.getLezionibyIdAula(id);
		if (listLez.isEmpty() == false) {
			for (Lezione l : listLez) {
				lezRepository.deleteById(l.getIdLezione());
			}
			
		}
		
		
		List<Esame> listEsame = esameRepository.getEsamiibyIdAula(id);
		if (listEsame.isEmpty()==false) {
			for (Esame e : listEsame) {
				esameRepository.deleteById(e.getIdEsame());
			}
		}
		
		
		aulaRepository.deleteById(id);
		return;
	}
	
	@Transactional
	public void deleteAll(List<Aula> list) {
		
		for (Aula a : list) {
			//elimino corrispondenze in tab M:M
			List<Attrezzatura> listAtt = attRepository.getAttrezzaturabyIdAula(a.getIdAula());
			if (listAtt.isEmpty() == false) {
				for (Attrezzatura att : listAtt) {
					attRepository.deleteById(att.getIdAttrezzatura());
				}
			}
		
			//elimino lezioni e esami con l'id dell'aula
			List<Lezione> listLez = lezRepository.getLezionibyIdAula(a.getIdAula());
			if (listLez.isEmpty() == false) {
				for (Lezione l : listLez) {
					lezRepository.deleteById(l.getIdLezione());
				}
				
			}
			List<Esame> listEsame = esameRepository.getEsamiibyIdAula(a.getIdAula());
			if (listEsame.isEmpty() == false) {
				for (Esame e : listEsame) {
					esameRepository.deleteById(e.getIdEsame());
				}
			}
			
		}
		
		aulaRepository.deleteAll(list);
	}
	
	@Transactional
	public Aula getByNomeAula(String string) {
		return aulaRepository.getByNomeAula(string);
	}

}


	
	



