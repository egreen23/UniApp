package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IToolService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.ToolDTO;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;
import it.unisalento.se.saw.strategy.StringSortStrategy;


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
	public ResponseEntity<List<ToolDTO>> findAll() throws Exception {
		try {
			
			List<Tool> toolList = toolService.findAll();
			List<String> nomitool = new ArrayList<String>();
			Iterator<Tool> toolIterator = toolList.iterator();
			
			List<ToolDTO> listToolDTO = new ArrayList<ToolDTO>();
			
			while(toolIterator.hasNext())
			{
				Tool tool = toolIterator.next();
				nomitool.add(tool.getNome());
				
				
			}
			SortStrategy<String> stringsort = new StringSortStrategy();
			SortContext stringorderer = new SortContext<String>(stringsort);
			stringorderer.setList(nomitool);
			stringorderer.sort();
			
			for(String s : nomitool) {
				
				toolIterator = toolList.iterator();
				
				while(toolIterator.hasNext()) {
					
					Tool tool = toolIterator.next();
										
					if (tool.getNome().equals(s)) {
						
						ToolDTO toolDTO = new ToolDTO();
						
						toolDTO.setIdTool(tool.getIdTool());
						toolDTO.setNome(tool.getNome());
						toolDTO.setDescrizione(tool.getDescrizione());
									
						listToolDTO.add(toolDTO);
						
						
						toolList.remove(tool);
						
						break;
					}
				}
			}
			return new ResponseEntity<List<ToolDTO>>(listToolDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<ToolDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	@GetMapping(value="/getById/{idToll}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ToolDTO> getById(@PathVariable("idToll") int idTool) throws Exception {
		try {
			
			Tool tool = toolService.getById(idTool);
			
			ToolDTO toolDTO = new ToolDTO();
			
			toolDTO.setIdTool(tool.getIdTool());
			toolDTO.setNome(tool.getNome());
			toolDTO.setDescrizione(tool.getDescrizione());
			
			return new ResponseEntity<ToolDTO>(toolDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<ToolDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping(value="/getByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ToolDTO> getByName(@PathVariable("string") String string) throws Exception {
		try {
			
			Tool tool = toolService.getByName(string);
			ToolDTO toolDTO = new ToolDTO();
				
				toolDTO.setIdTool(tool.getIdTool());
				toolDTO.setNome(tool.getNome());
				toolDTO.setDescrizione(tool.getDescrizione());
							
			return new ResponseEntity<ToolDTO>(toolDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<ToolDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PostMapping(value="/newTool", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tool> save(@RequestBody ToolDTO toolDTO) throws Exception {
		try {
			
			Tool newTool = new Tool();
			
			newTool.setIdTool(toolDTO.getIdTool());
			newTool.setNome(toolDTO.getNome());
			newTool.setDescrizione(toolDTO.getDescrizione());
			
			return new ResponseEntity<Tool>(toolService.save(newTool), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<Tool>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	@PostMapping(value="/updateToolById/{idTool}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tool> updateToolById(@PathVariable("idTool") int idTool, @RequestBody ToolDTO toolDTO) throws Exception {
		try {
			
			Tool toolUpdate = toolService.updateToolById(idTool);
			
//			toolUpdate.setIdTool(toolDTO.getIdTool());
			toolUpdate.setNome(toolDTO.getNome());
			toolUpdate.setDescrizione(toolDTO.getDescrizione());
			
			return new ResponseEntity<Tool>(toolService.save(toolUpdate), HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<Tool>(HttpStatus.OK);
		}
	}
	
	
	
}
