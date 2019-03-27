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
	public List<Insegnamento> getByDocente(String cognome, String nome){
		return insegnamentoRepository.getByDocente(cognome, nome);
	}
	
	@Transactional
	public List<Insegnamento> getByInsegnamento(String nome) {
		return insegnamentoRepository.getByInsegnamento(nome);
	}

	@Transactional
	public List<Insegnamento> getByCorso(String nome) {
		return insegnamentoRepository.getByCorso(nome);
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
	public Insegnamento updateById(int idInsegnamento) {
		return insegnamentoRepository.getOne(idInsegnamento);
	}

	

	
	
	
	
}
