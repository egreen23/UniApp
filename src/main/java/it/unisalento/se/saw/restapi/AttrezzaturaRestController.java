package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IAttrezzaturaService;
import it.unisalento.se.saw.IService.IAulaService;
import it.unisalento.se.saw.IService.IToolService;
import it.unisalento.se.saw.domain.Attrezzatura;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.dto.AttrezzaturaDTO;
import it.unisalento.se.saw.dto.ToolDTO;
import it.unisalento.se.saw.exceptions.ToolNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/attrezzatura")
public class AttrezzaturaRestController {
	
	@Autowired
	IAttrezzaturaService attrezzaturaService;
	
	
	
	public AttrezzaturaRestController() {
		super();
	}
	
	public AttrezzaturaRestController(IAttrezzaturaService attrezzaturaService) {
		this.attrezzaturaService = attrezzaturaService;
	}
	
	
		
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AttrezzaturaDTO> findAll() {
		
		List<Attrezzatura> attrezzaturas = attrezzaturaService.findAll();
		List<AttrezzaturaDTO> ListAttDTO = new ArrayList<AttrezzaturaDTO>();
		
		for (Attrezzatura att : attrezzaturas)
		{
			AttrezzaturaDTO attDTO = new AttrezzaturaDTO();
			
			attDTO.setIdAttrezzatura(att.getIdAttrezzatura());
			attDTO.setIdAula(att.getAula().getIdAula());
			attDTO.setIdTool(att.getTool().getIdTool());
			
			ListAttDTO.add(attDTO);
			
		}
		return ListAttDTO;
		
	}
	
	
	
	@PostMapping(value="/newAtt", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Attrezzatura save(@RequestBody AttrezzaturaDTO attrezzaturaDTO) {
		
		Attrezzatura att = new Attrezzatura();
		Aula aula = new Aula();
		Tool tool = new Tool();
		
		 aula.setIdAula(attrezzaturaDTO.getIdAula());
		 tool.setIdTool(attrezzaturaDTO.getIdTool());
		
		att.setAula(aula);
		att.setTool(tool);
		
		 return attrezzaturaService.save(att);

	}
	
	
	
	@DeleteMapping(value="/deleteAtt/{idAula}/{idTool}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void deleteAtt(@PathVariable("idAula") int idAula, @PathVariable("idTool") int idTool){

		attrezzaturaService.deleteAtt(idAula, idTool);
	}
	
	
	
	@GetMapping(value="/getIdAttByAT/{idAula}/{idTool}", produces=MediaType.APPLICATION_JSON_VALUE)
	public AttrezzaturaDTO getIdAttByAT(@PathVariable("idAula") int idAula, @PathVariable("idTool") int idTool) {

		
		Attrezzatura att = attrezzaturaService.getIdAttByAT(idAula, idTool);
		AttrezzaturaDTO attDTO = new AttrezzaturaDTO();
		
		attDTO.setIdAttrezzatura(att.getIdAttrezzatura());
//		attDTO.setIdAula(att.getAula().getIdAula());
//		attDTO.setIdTool(att.getTool().getIdTool());
		
		return attDTO;	
		
	}
	
	
	
	

}
