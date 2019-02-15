package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Tool;

public interface IToolService {
	
	public List<Tool> findAll();
	
	public Tool getById(int idTool);
	
	public Tool getByName(String string);
	
	public Tool save(Tool tool);

	public Tool updateToolById(int idTool);
	
//	public void deleteToolyId(int idTool) throws AulaNotFoundException;
	
}
