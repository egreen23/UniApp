package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
		

	
	@PostMapping(value="/newAtt", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Attrezzatura> save(@RequestBody AttrezzaturaDTO attrezzaturaDTO) throws Exception {
		try {
			
			Attrezzatura newAtt = new Attrezzatura();
			Aula aula = new Aula();
			Tool newTool = new Tool();
			
			 aula.setIdAula(attrezzaturaDTO.getIdAula());
			 newTool.setIdTool(attrezzaturaDTO.getIdTool());
			
			newAtt.setAula(aula);
			newAtt.setTool(newTool);
			
			 return new ResponseEntity<Attrezzatura>(attrezzaturaService.save(newAtt), HttpStatus.CREATED);
			
		} catch (Exception e) {
			 return new ResponseEntity<Attrezzatura>(HttpStatus.BAD_REQUEST);
		}

	}
	
	

	@RequestMapping(path="deleteAtt/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAtt(@PathVariable("id") int id) throws Exception {
		try {
    		attrezzaturaService.deleteAtt(id);
    		return ResponseEntity.ok().build();
    	 } catch (Exception e) {
    	  return ResponseEntity.notFound().build();
    	 }
	}
	
	@RequestMapping(path="deleteAttrezzature", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAttrezzature(@RequestParam Map<String,String> allParams) throws Exception {
    	try {
    		List<Attrezzatura> list = new ArrayList<Attrezzatura>();
    		for (int i = 0; i < allParams.size(); i++) {
    			String key = "id"+i;
    			int idAtt = Integer.parseInt(allParams.get(key));
    			Attrezzatura a = attrezzaturaService.getOne(idAtt);
    			list.add(a);
    			
    		}
    		attrezzaturaService.deleteAll(list);
    		return ResponseEntity.ok().build();
    	} catch (Exception e) {
      	  return ResponseEntity.notFound().build();
    	}
    }
	
	
//	@GetMapping(value="/getIdAttByAT/{idAula}/{idTool}", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<AttrezzaturaDTO> getIdAttByAT(@PathVariable("idAula") int idAula, @PathVariable("idTool") int idTool) throws Exception {
//		try {
//						
//			Attrezzatura att = attrezzaturaService.getIdAttByAT(idAula, idTool);
//			AttrezzaturaDTO attDTO = new AttrezzaturaDTO();
//			
//			attDTO.setIdAttrezzatura(att.getIdAttrezzatura());
////			attDTO.setIdAula(att.getAula().getIdAula());
////			attDTO.setIdTool(att.getTool().getIdTool());
//			
//			return new ResponseEntity<AttrezzaturaDTO>(attDTO, HttpStatus.OK);	
//			
//		} catch (Exception e) {
//			return new ResponseEntity<AttrezzaturaDTO>(HttpStatus.BAD_REQUEST);	
//		}
//
//	}
	
	
	@GetMapping(value="/getAttbyIdAula/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttrezzaturaDTO>> getAttbyIdAula(@PathVariable("id") int id) throws Exception {
		try {
			List<Attrezzatura> attrezzaturaList = attrezzaturaService.getAttrezzaturabyIdAula(id);
			Iterator<Attrezzatura> attIterator = attrezzaturaList.iterator();
			
			List<AttrezzaturaDTO> ListAttDTO = new ArrayList<AttrezzaturaDTO>();
			
			while(attIterator.hasNext())		
			{
				Attrezzatura att = attIterator.next();
				AttrezzaturaDTO attDTO = new AttrezzaturaDTO();
				
				attDTO.setIdAttrezzatura(att.getIdAttrezzatura());
				attDTO.setIdAula(att.getAula().getIdAula());
				attDTO.setIdTool(att.getTool().getIdTool());
				attDTO.setNomeTool(att.getTool().getNome());
				attDTO.setDescrizioneTool(att.getTool().getDescrizione());
				
				ListAttDTO.add(attDTO);
				
			}
			
			return new ResponseEntity<List<AttrezzaturaDTO>>(ListAttDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<AttrezzaturaDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
