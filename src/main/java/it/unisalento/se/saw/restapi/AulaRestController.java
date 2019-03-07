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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IAulaService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.AulaDTO;
import it.unisalento.se.saw.dto.UserDTO;


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
			
			List<AulaDTO> ListAulaDTO = new ArrayList<AulaDTO>();
			
			while(aulaIterator.hasNext())
			{
				Aula aula = aulaIterator.next();
				AulaDTO aulaDTO = new AulaDTO();
				
				aulaDTO.setIdAula(aula.getIdAula());
				aulaDTO.setNome(aula.getNome());
				aulaDTO.setLatitudine(aula.getLatitudine());
				aulaDTO.setLongitudine(aula.getLongitudine());
				aulaDTO.setEdificio(aula.getEdificio());
				aulaDTO.setPiano(aula.getPiano());
				
				ListAulaDTO.add(aulaDTO);
				
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
	
	
	
	@GetMapping(value="/getByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AulaDTO>> getByName(@PathVariable("string") String string) throws Exception {
		try {
			
			List<Aula> aulaList = aulaService.getByName(string);
			Iterator<Aula> aulaIterator = aulaList.iterator();
			
			List<AulaDTO> ListAulaDTO = new ArrayList<AulaDTO>();
			
			while(aulaIterator.hasNext())
			{
				Aula aula = aulaIterator.next();
				AulaDTO aulaDTO = new AulaDTO();
				
				aulaDTO.setIdAula(aula.getIdAula());
				aulaDTO.setNome(aula.getNome());
				aulaDTO.setLatitudine(aula.getLatitudine());
				aulaDTO.setLongitudine(aula.getLongitudine());
				aulaDTO.setEdificio(aula.getEdificio());
				aulaDTO.setPiano(aula.getPiano());
				
				ListAulaDTO.add(aulaDTO);
				
			}
			return new ResponseEntity<List<AulaDTO>>(ListAulaDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<AulaDTO>>(HttpStatus.BAD_REQUEST);
		}
							

		}
	

	/*NOME... EDIFICIO... PIANO*/ 
	
//	Query Niko prende solo il nome dell'aulae da un OGETTO 
//	
//	@GetMapping(value="/getByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
//	public AulaDTO getByName(@PathVariable("string") String string) throws AulaNotFoundException {
//		
//		Aula aula = aulaService.getByName(string);
//		
//		AulaDTO aulaDTO = new AulaDTO();
//		
//		
//			
//			
//			aulaDTO.setIdAula(aula.getIdAula());
//			aulaDTO.setNome(aula.getNome());
//			aulaDTO.setLatitudine(aula.getLatitudine());
//			aulaDTO.setLongitudine(aula.getLongitudine());
//			aulaDTO.setEdificio(aula.getEdificio());
//			aulaDTO.setPiano(aula.getPiano());
//			
//			
//		return aulaDTO;
//		
//	}
	
	
	
	@PostMapping(value="/newAula", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aula> save(@RequestBody AulaDTO aulaDTO) throws Exception {
		try {
			
			Aula aula = new Aula();
			
			aula.setIdAula(aulaDTO.getIdAula());
			aula.setNome(aulaDTO.getNome());
			aula.setLatitudine(aulaDTO.getLatitudine());
			aula.setLongitudine(aulaDTO.getLongitudine());
			aula.setEdificio(aulaDTO.getEdificio());
			aula.setPiano(aulaDTO.getPiano());
			
			return new ResponseEntity<Aula>(aulaService.save(aula), HttpStatus.CREATED);
		
			
		} catch (Exception e) {
			
			return new ResponseEntity<Aula>(HttpStatus.BAD_REQUEST);

		}}

	
	
	@PostMapping(value="/updateAulaById/{idAula}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aula> updateAulaById(@PathVariable("idAula") int idAula, @RequestBody AulaDTO aulaDTO) throws Exception{
		try {
			
			Aula aulaUpdate = aulaService.updateAulaById(idAula);
			
			aulaUpdate.setIdAula(aulaDTO.getIdAula());
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
	
//	@PostMapping(value="/deleteAulaById/{idAula}", consumes=MediaType.APPLICATION_JSON_VALUE)
//	public void deleteAulaById(@PathVariable("idAula") int idAula) throws AulaNotFoundException{
//		
//		aulaService.deleteAulaById(idAula);
//		
//	}

	
	

}
