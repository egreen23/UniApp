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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IAulaService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.AulaDTO;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;
import it.unisalento.se.saw.strategy.StringSortStrategy;


@RestController
@RequestMapping("/aula")
public class AulaRestController {
	
	@Autowired
	IAulaService aulaService;

	public AulaRestController() {
		super();
	}

	public AulaRestController(IAulaService aulaService) {
		this.aulaService = aulaService;
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AulaDTO>> findAll() throws Exception {
		try {
			
			List<Aula> aulaList = aulaService.findAll();
			Iterator<Aula> aulaIterator = aulaList.iterator();
			List<String> nomiaule = new ArrayList<String>();

			List<AulaDTO> ListAulaDTO = new ArrayList<AulaDTO>();
			
			while(aulaIterator.hasNext())
			{
				Aula aula = aulaIterator.next();
				nomiaule.add(aula.getNome());
				
				
			}
			
			SortStrategy<String> stringsort = new StringSortStrategy();
			SortContext stringorderer = new SortContext<String>(stringsort);
			stringorderer.setList(nomiaule);
			stringorderer.sort();
			
			for(String s : nomiaule) {
				
				aulaIterator = aulaList.iterator();
				
				while(aulaIterator.hasNext()) {
					
					Aula aula = aulaIterator.next();
					
					if (aula.getNome().equals(s)) {
						
						AulaDTO aulaDTO = new AulaDTO();
						
						aulaDTO.setIdAula(aula.getIdAula());
						aulaDTO.setNome(aula.getNome());
						aulaDTO.setLatitudine(aula.getLatitudine());
						aulaDTO.setLongitudine(aula.getLongitudine());
						aulaDTO.setEdificio(aula.getEdificio());
						aulaDTO.setPiano(aula.getPiano());
						
						ListAulaDTO.add(aulaDTO);
						
						aulaList.remove(aula);
						
						break;
					}
				}
			}
			return new ResponseEntity<List<AulaDTO>>(ListAulaDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<AulaDTO>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	@GetMapping(value="/getById/{idAula}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AulaDTO> getById(@PathVariable("idAula") int idAula) throws Exception {
		try {
			
			Aula aula = aulaService.getById(idAula);
			
			AulaDTO aulaDTO = new AulaDTO();
			
			aulaDTO.setIdAula(aula.getIdAula());
			aulaDTO.setNome(aula.getNome());
			aulaDTO.setLatitudine(aula.getLatitudine());
			aulaDTO.setLongitudine(aula.getLongitudine());
			aulaDTO.setEdificio(aula.getEdificio());
			aulaDTO.setPiano(aula.getPiano());
			
			return new ResponseEntity<AulaDTO>(aulaDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<AulaDTO>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//NUOVO CH
	@GetMapping(value="/getByNomeAula/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AulaDTO> getByNomeAula(@PathVariable("string") String string) throws Exception {
			try {
				
				Aula aula = aulaService.getByNomeAula(string);
				
				AulaDTO aulaDTO = new AulaDTO();	
				
					
					aulaDTO.setIdAula(aula.getIdAula());
					aulaDTO.setNome(aula.getNome());
					aulaDTO.setLatitudine(aula.getLatitudine());
					aulaDTO.setLongitudine(aula.getLongitudine());
					aulaDTO.setEdificio(aula.getEdificio());
					aulaDTO.setPiano(aula.getPiano());
					
					return new ResponseEntity<AulaDTO>(aulaDTO, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<AulaDTO>(HttpStatus.BAD_REQUEST);
			}					
		}
	
	@PostMapping(value="/newAula", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aula> save(@RequestBody AulaDTO aulaDTO) throws Exception {
		try {
			
			Aula newAula = new Aula();
			
			// newAula.setIdAula(aulaDTO.getIdAula());
			newAula.setNome(aulaDTO.getNome());
			newAula.setLatitudine(aulaDTO.getLatitudine());
			newAula.setLongitudine(aulaDTO.getLongitudine());
			newAula.setEdificio(aulaDTO.getEdificio());
			newAula.setPiano(aulaDTO.getPiano());
			
			return new ResponseEntity<Aula>(aulaService.save(newAula), HttpStatus.CREATED);
		
			
		} catch (Exception e) {
			
			return new ResponseEntity<Aula>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@PostMapping(value="/updateAulaById/{idAula}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aula> updateAulaById(@PathVariable("idAula") int idAula, @RequestBody AulaDTO aulaDTO) throws Exception{
		try {
			
			Aula aulaUpdate = aulaService.getById(idAula);
			
//			aulaUpdate.setIdAula(aulaDTO.getIdAula());
			aulaUpdate.setNome(aulaDTO.getNome());
			aulaUpdate.setLatitudine(aulaDTO.getLatitudine());
			aulaUpdate.setLongitudine(aulaDTO.getLongitudine());
			aulaUpdate.setEdificio(aulaDTO.getEdificio());
			aulaUpdate.setPiano(aulaDTO.getPiano());
			
			return new ResponseEntity<Aula>(aulaService.save(aulaUpdate), HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<Aula>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(path="deleteAula/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAula(@PathVariable("id") int id) throws Exception {
		try {
    		aulaService.deleteAula(id);
    		return ResponseEntity.ok().build();
    	 } catch (Exception e) {
    	  return ResponseEntity.notFound().build();
    	 }
	}
	
	@RequestMapping(path="deleteAule", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAule(@RequestParam Map<String,String> allParams) throws Exception {
    	try {
    		List<Aula> list = new ArrayList<Aula>();
    		for (int i = 0; i < allParams.size(); i++) {
    			String key = "id"+i;
    			int idAula = Integer.parseInt(allParams.get(key));
    			Aula a = aulaService.getById(idAula);
    			list.add(a);
    			
    		}
    		aulaService.deleteAll(list);
    		return ResponseEntity.ok().build();
    	} catch (Exception e) {
      	  return ResponseEntity.notFound().build();
    	}
    }
	
	@GetMapping(value="/searchAula/{term}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AulaDTO>> search(@PathVariable("term") String term) throws Exception {
		try {
			
			List<Aula> aulaList = aulaService.findAll();
			Iterator<Aula> aulaIterator = aulaList.iterator();
			
			List<AulaDTO> ListAulaDTO = new ArrayList<AulaDTO>();
			
			while(aulaIterator.hasNext())
			{
				Aula aula = aulaIterator.next();
				AulaDTO aulaDTO = new AulaDTO();
				
				if(aula.getNome().equalsIgnoreCase(term)) {
					
					aulaDTO.setIdAula(aula.getIdAula());
					aulaDTO.setNome(aula.getNome());
					aulaDTO.setLatitudine(aula.getLatitudine());
					aulaDTO.setLongitudine(aula.getLongitudine());
					aulaDTO.setEdificio(aula.getEdificio());
					aulaDTO.setPiano(aula.getPiano());
					
					ListAulaDTO.add(aulaDTO);
				}
				
				
				
			}
			return new ResponseEntity<List<AulaDTO>>(ListAulaDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<AulaDTO>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	

	}
