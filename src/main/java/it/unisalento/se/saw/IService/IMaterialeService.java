package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Materiale;

public interface IMaterialeService {
	
	public List<Materiale> findAll();
	
	public Materiale save(Materiale materiale);
	
	public Materiale updateById(int idMateriale);
	
	public Materiale getById(int idMateriale);
	
	public List<Materiale> getByName(String string);

}
