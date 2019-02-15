package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Insegnamento> findByName(String string){
		return insegnamentoRepository.getByName(string);
	}

	
	
	
	
}
