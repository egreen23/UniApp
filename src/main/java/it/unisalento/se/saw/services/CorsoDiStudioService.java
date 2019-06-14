package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.ICorsoDiStudioService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.repositories.CorsoDiStudioRepository;

@Service
public class CorsoDiStudioService implements ICorsoDiStudioService {

	@Autowired
	CorsoDiStudioRepository corsoDiStudioRepository;
	
	
	
	@Transactional
	public List<CorsoDiStudio> findAll(){
		return corsoDiStudioRepository.findAll();
	}
	
	@Transactional
	public List<CorsoDiStudio> getByTipo(String string) {
		return corsoDiStudioRepository.getByTipo(string);
	}
	
	
	@Transactional
	public CorsoDiStudio getById(int idCorso) {
		return corsoDiStudioRepository.getOne(idCorso);
	}

	@Transactional
	public CorsoDiStudio save(CorsoDiStudio corsoDiStudio) {
		return corsoDiStudioRepository.save(corsoDiStudio);
	}


	
	
	
	
}
