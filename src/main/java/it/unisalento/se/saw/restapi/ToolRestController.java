package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IToolService;
import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.dto.ToolDTO;

@RestController
@RequestMapping("/tool")
public class ToolRestController {
	
	@Autowired
	IToolService toolService;

	public ToolRestController() {
		super();
	}
	
	public ToolRestController(IToolService toolService) {
		this.toolService = toolService;
	}




	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ToolDTO> findAll() {
		
		List<Tool> tools = toolService.findAll();
		List<ToolDTO> ListTollDTO = new ArrayList<ToolDTO>();
		
		for (Tool toll : tools)
		{
			ToolDTO toolDTO = new ToolDTO();
			
			toolDTO.setIdTool(toll.getIdTool());
			toolDTO.setNome(toll.getNome());
			toolDTO.setDescrizione(toll.getDescrizione());
						
			ListTollDTO.add(toolDTO);
			
		}
		return ListTollDTO;
		
	}
	
	
	
	@GetMapping(value="/getById/{idToll}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ToolDTO getById(@PathVariable("idToll") int idTool) {
		
		Tool tool = toolService.getById(idTool);
		
		ToolDTO toolDTO = new ToolDTO();
		
		toolDTO.setIdTool(tool.getIdTool());
		toolDTO.setNome(tool.getNome());
		toolDTO.setDescrizione(tool.getDescrizione());
		
		return toolDTO;
		
	}
	
	
	
	
	@GetMapping(value="/getByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ToolDTO getByName(@PathVariable("string") String string) {

		
		Tool tool = toolService.getByName(string);
		ToolDTO toolDTO = new ToolDTO();
		
		
		
			
			toolDTO.setIdTool(tool.getIdTool());
			toolDTO.setNome(tool.getNome());
			toolDTO.setDescrizione(tool.getDescrizione());
						
			
		return toolDTO;
		
	}
	
	@PostMapping(value="/newTool", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Tool save(@RequestBody ToolDTO toolDTO) {
		
		Tool tool = new Tool();
		
		tool.setIdTool(toolDTO.getIdTool());
		tool.setNome(toolDTO.getNome());
		tool.setDescrizione(toolDTO.getDescrizione());
		
		return toolService.save(tool);
		
	}
	
	
	
	@PostMapping(value="/updateToolById/{idTool}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Tool updateToolById(@PathVariable("idTool") int idTool, @RequestBody ToolDTO toolDTO) {
		
		Tool toolUpdate = toolService.updateToolById(idTool);
		
		toolUpdate.setIdTool(toolDTO.getIdTool());
		toolUpdate.setNome(toolDTO.getNome());
		toolUpdate.setDescrizione(toolDTO.getDescrizione());
		
		return toolService.save(toolUpdate);
		
	}
	
	
	
}
