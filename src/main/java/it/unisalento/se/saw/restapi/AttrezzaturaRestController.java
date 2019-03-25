package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	public ResponseEntity<List<AttrezzaturaDTO>> findAll() throws Exception {
		try {
			List<Attrezzatura> attrezzaturaList = attrezzaturaService.findAll();
			Iterator<Attrezzatura> attIterator = attrezzaturaList.iterator();
			
			List<AttrezzaturaDTO> ListAttDTO = new ArrayList<AttrezzaturaDTO>();
			
			while(attIterator.hasNext())		
			{
				Attrezzatura att = attIterator.next();
				AttrezzaturaDTO attDTO = new AttrezzaturaDTO();
				
				attDTO.setIdAttrezzatura(att.getIdAttrezzatura());
				attDTO.setIdAula(att.getAula().getIdAula());
				attDTO.setIdTool(att.getTool().getIdTool());
				
				ListAttDTO.add(attDTO);
				
			}
			
			return new ResponseEntity<List<AttrezzaturaDTO>>(ListAttDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<AttrezzaturaDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/newAtt", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Attrezzatura> save(@RequestBody AttrezzaturaDTO attrezzaturaDTO) throws Exception {
		try {
			
			Attrezzatura att = new Attrezzatura();
			Aula aula = new Aula();
			Tool tool = new Tool();
			
			 aula.setIdAula(attrezzaturaDTO.getIdAula());
			 tool.setIdTool(attrezzaturaDTO.getIdTool());
			
			att.setAula(aula);
			att.setTool(tool);
			
			 return new ResponseEntity<Attrezzatura>(attrezzaturaService.save(att), HttpStatus.CREATED);
			
		} catch (Exception e) {
			 return new ResponseEntity<Attrezzatura>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@DeleteMapping(value="/deleteAtt/{idAula}/{idTool}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void deleteAtt(@PathVariable("idAula") int idAula, @PathVariable("idTool") int idTool){

		attrezzaturaService.deleteAtt(idAula, idTool);
	}
	
	
	@GetMapping(value="/getIdAttByAT/{idAula}/{idTool}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AttrezzaturaDTO> getIdAttByAT(@PathVariable("idAula") int idAula, @PathVariable("idTool") int idTool) throws Exception {
		try {
						
			Attrezzatura att = attrezzaturaService.getIdAttByAT(idAula, idTool);
			AttrezzaturaDTO attDTO = new AttrezzaturaDTO();
			
			attDTO.setIdAttrezzatura(att.getIdAttrezzatura());
//			attDTO.setIdAula(att.getAula().getIdAula());
//			attDTO.setIdTool(att.getTool().getIdTool());
			
			return new ResponseEntity<AttrezzaturaDTO>(attDTO, HttpStatus.OK);	
			
		} catch (Exception e) {
			return new ResponseEntity<AttrezzaturaDTO>(HttpStatus.BAD_REQUEST);	
		}

	}
	
	//DA TESTARE SE DOVESSE SRVIRE 
	@PostMapping(value="/updateById/{idAttrezzatura}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Attrezzatura> updateById(@PathVariable("idAttrezzatura") int idAttrezzatura, @RequestBody AttrezzaturaDTO attrezzaturaDTO) throws Exception {
		try {
			
			Attrezzatura att = attrezzaturaService.updateById(idAttrezzatura);
			Aula aula = new Aula();
			Tool tool = new Tool();
			
			 aula.setIdAula(attrezzaturaDTO.getIdAula());
			 tool.setIdTool(attrezzaturaDTO.getIdTool());
			
			att.setAula(aula);
			att.setTool(tool);
			
			 return new ResponseEntity<Attrezzatura>(attrezzaturaService.save(att), HttpStatus.CREATED);
			
		} catch (Exception e) {
			 return new ResponseEntity<Attrezzatura>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	

}
