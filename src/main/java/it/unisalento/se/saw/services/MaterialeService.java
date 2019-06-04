package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IMaterialeService;
import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.repositories.MaterialeRepository;

@Service
public class MaterialeService implements IMaterialeService {
	
	@Autowired
	MaterialeRepository materialeRepository;
	
	@Transactional
	public List<Materiale> findAll() {
		return materialeRepository.findAll();
	}
	
	@Transactional
	public Materiale save(Materiale materiale) {
		return materialeRepository.save(materiale);
	}
	
	@Transactional
	public Materiale updateById(int idMateriale) {
		return materialeRepository.getOne(idMateriale);
	}

	@Transactional
	public Materiale getById(int idMateriale) {
		return materialeRepository.getOne(idMateriale);
	}
	
	@Transactional
	public List<Materiale> getByName(String string) {
		return materialeRepository.getByName(string);
	}
	
	//nuovo metodo CH
	@Transactional
	public List<Materiale> getMatByIdInsegnamento(int idInsegnamento){
		return materialeRepository.getMatByIdInsegnamento(idInsegnamento);
	}



}
