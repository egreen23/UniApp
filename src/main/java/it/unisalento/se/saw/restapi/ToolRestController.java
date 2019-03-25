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
	public ResponseEntity<List<ToolDTO>> findAll() throws Exception {
		try {
			
			List<Tool> toolList = toolService.findAll();
			Iterator<Tool> toolIterator = toolList.iterator();
			
			List<ToolDTO> listToolDTO = new ArrayList<ToolDTO>();
			
			while(toolIterator.hasNext())
			{
				Tool tool = toolIterator.next();
				ToolDTO toolDTO = new ToolDTO();
				
				toolDTO.setIdTool(tool.getIdTool());
				toolDTO.setNome(tool.getNome());
				toolDTO.setDescrizione(tool.getDescrizione());
							
				listToolDTO.add(toolDTO);
				
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
			
			Tool tool = new Tool();
			
			tool.setIdTool(toolDTO.getIdTool());
			tool.setNome(toolDTO.getNome());
			tool.setDescrizione(toolDTO.getDescrizione());
			
			return new ResponseEntity<Tool>(toolService.save(tool), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<Tool>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	@PostMapping(value="/updateToolById/{idTool}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Tool> updateToolById(@PathVariable("idTool") int idTool, @RequestBody ToolDTO toolDTO) throws Exception {
		try {
			
			Tool toolUpdate = toolService.updateToolById(idTool);
			
			toolUpdate.setIdTool(toolDTO.getIdTool());
			toolUpdate.setNome(toolDTO.getNome());
			toolUpdate.setDescrizione(toolDTO.getDescrizione());
			
			return new ResponseEntity<Tool>(toolService.save(toolUpdate), HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<Tool>(HttpStatus.OK);
		}
	}
	
	
	
}
