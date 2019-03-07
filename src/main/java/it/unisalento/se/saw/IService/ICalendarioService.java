package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.dto.composite.CalendarioComponent;

public interface ICalendarioService {
	
	public List<CalendarioComponent> findAll();
	
	public List<CalendarioComponent> getAll();
	
	public CalendarioComponent getCalendarioById(int idCalendario);
		
//	public List<CalendarioComponent> calLezione(String string);
	
//	public List<CalendarioComponent> findByNameLez;
	
	

}
