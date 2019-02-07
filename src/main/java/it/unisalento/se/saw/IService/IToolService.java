package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.exceptions.ToolNotFoundException;

public interface IToolService {
	
	public List<Tool> findAll();
	
	public Tool getById(int idTool) throws ToolNotFoundException;
	
	public List<Tool> getByName(String string) throws ToolNotFoundException;
	
	public Tool save(Tool tool);

	public Tool updateToolById(int idTool);
	
//	public void deleteToolyId(int idTool) throws AulaNotFoundException;
	
}
