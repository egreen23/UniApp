package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IInsegnamentoService;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.repositories.InsegnamentoRepository;

@Service
public class InsegnamentoService implements IInsegnamentoService {

	@Autowired
	InsegnamentoRepository insegnamentoRepository;
	
	
	@Transactional
	public List<Insegnamento> findAll(){
		return insegnamentoRepository.findAll();
	}
	
	@Transactional
	public Insegnamento getById(int idInsegnamento) {
		return insegnamentoRepository.getOne(idInsegnamento);
	}
	
	@Transactional
	public Insegnamento save(Insegnamento insegnamento) {
		return insegnamentoRepository.save(insegnamento);
	}
	
	@Transactional
	public List<Insegnamento> getByIdCorso(int id) {
		return insegnamentoRepository.getByIdCorso(id);
	}

//	@Transactional
//	public List<Insegnamento> getbyIdDoc(int id) {
//		return insegnamentoRepository.getbyIdDoc(id);
//	}

	@Transactional
	public List<Insegnamento> getByIdDocente(int idDocente){
		return insegnamentoRepository.getByIdDocente(idDocente);
	}

	
	
	
	
}
