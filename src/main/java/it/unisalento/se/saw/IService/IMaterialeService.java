package it.unisalento.se.saw.IService;

import java.util.List;

import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Materiale;

public interface IMaterialeService {
	
	public List<Materiale> findAll();
	
	public Materiale save(Materiale materiale);
		
	public Materiale getById(int idMateriale);
		
	public List<Materiale> getMatByIdInsegnamento(int idInsegnamento);
}
