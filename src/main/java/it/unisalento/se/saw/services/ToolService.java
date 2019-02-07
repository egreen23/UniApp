package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IToolService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.ToolNotFoundException;
import it.unisalento.se.saw.repositories.ToolRepository;

@Service
public class ToolService implements IToolService {
	
	@Autowired
	ToolRepository toolRepository;
	
	
	
	@Transactional
	public List<Tool> findAll() {
		return toolRepository.findAll();
	}
	
	
	@Transactional
	public Tool getById(int idTool) throws ToolNotFoundException {
		try {
			Tool tool = toolRepository.getOne(idTool);
			return tool;
		} catch (Exception e) {
			throw new ToolNotFoundException();
		}
	}
	
	
	
	@Transactional
	public List<Tool> getByName(String string) {
		return toolRepository.getByName(string);
	}
	
	
	
	@Transactional
	public Tool save(Tool tool) {
		return toolRepository.save(tool);
	}
	
	
	
	@Transactional
	public Tool updateToolById(int idTool) {
		return toolRepository.getOne(idTool);
	}
	
	

}
