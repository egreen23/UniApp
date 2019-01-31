package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.ISegreteriaService;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.repositories.SegreteriaRepository;

@Service
public class SegreteriaService implements ISegreteriaService{
	
	@Autowired
	SegreteriaRepository segreteriaRepository;
	
	
	@Transactional
	public List<Segreteria> isSegreteria(int idMatricola){
		return segreteriaRepository.isSegreteria(idMatricola);
	}
	

}
