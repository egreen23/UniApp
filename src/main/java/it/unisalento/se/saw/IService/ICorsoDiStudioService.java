package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.CorsoDiStudio;

public interface ICorsoDiStudioService {
	
	public List<CorsoDiStudio> findAll();
	
	public List<CorsoDiStudio> getByTipo(String string);
		
	public CorsoDiStudio getById(int idCorso);
	
	public CorsoDiStudio save(CorsoDiStudio corsoDiStudio);
	
	public CorsoDiStudio updateById(int idCorso);


	

	

}
