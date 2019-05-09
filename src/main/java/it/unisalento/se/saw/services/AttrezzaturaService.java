package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IAttrezzaturaService;
import it.unisalento.se.saw.domain.Attrezzatura;
import it.unisalento.se.saw.repositories.AttrezzaturaRepository;

@Service
public class AttrezzaturaService implements IAttrezzaturaService {
	
	@Autowired
	AttrezzaturaRepository attrezzaturaRepository;
	
	
	
	@Transactional
	public List<Attrezzatura> findAll() {
		return attrezzaturaRepository.findAll();
	}	
	
	@Transactional
	public Attrezzatura save(Attrezzatura attrezzatura) {
		return attrezzaturaRepository.save(attrezzatura);
	}
	
	@Transactional
	public void deleteAtt(int idAula, int idTool) {
		attrezzaturaRepository.deleteAtt(idAula, idTool);
	}

	@Transactional
	public Attrezzatura getIdAttByAT(int idAula, int idTool) {
		return attrezzaturaRepository.getIdAttByAT(idAula, idTool);
	}
	
	@Transactional
	public Attrezzatura updateById(int idAttrezzatura) {
		return attrezzaturaRepository.getOne(idAttrezzatura);
	}
	

}
