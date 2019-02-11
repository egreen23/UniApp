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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IAulaService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.AulaDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.AulaNotFoundException;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

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
	public List<AulaDTO> findAll() {
		
		List<Aula> aulas = aulaService.findAll();
		List<AulaDTO> ListAulaDTO = new ArrayList<AulaDTO>();
		
		for (Aula aula : aulas)
		{
			AulaDTO aulaDTO = new AulaDTO();
			
			aulaDTO.setIdAula(aula.getIdAula());
			aulaDTO.setNome(aula.getNome());
			aulaDTO.setLatitudine(aula.getLatitudine());
			aulaDTO.setLongitudine(aula.getLongitudine());
			aulaDTO.setEdificio(aula.getEdificio());
			aulaDTO.setPiano(aula.getPiano());
			
			ListAulaDTO.add(aulaDTO);
			
		}
		return ListAulaDTO;
		
		
	}
	
	
	
	@GetMapping(value="/getById/{idAula}", produces=MediaType.APPLICATION_JSON_VALUE)
	public AulaDTO getById(@PathVariable("idAula") int idAula) throws AulaNotFoundException {
		
		Aula aula = aulaService.getById(idAula);
		
		AulaDTO aulaDTO = new AulaDTO();
		
		aulaDTO.setIdAula(aula.getIdAula());
		aulaDTO.setNome(aula.getNome());
		aulaDTO.setLatitudine(aula.getLatitudine());
		aulaDTO.setLongitudine(aula.getLongitudine());
		aulaDTO.setEdificio(aula.getEdificio());
		aulaDTO.setPiano(aula.getPiano());
		
		return aulaDTO;
		
	}
	
	
	
	@GetMapping(value="/getByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AulaDTO> getByName(@PathVariable("string") String string) throws AulaNotFoundException {
							
			List<Aula> aulas = aulaService.getByName(string);
			List<AulaDTO> ListAulaDTO = new ArrayList<AulaDTO>();
			
			for (Aula aula : aulas)
			{
				AulaDTO aulaDTO = new AulaDTO();
				
				aulaDTO.setIdAula(aula.getIdAula());
				aulaDTO.setNome(aula.getNome());
				aulaDTO.setLatitudine(aula.getLatitudine());
				aulaDTO.setLongitudine(aula.getLongitudine());
				aulaDTO.setEdificio(aula.getEdificio());
				aulaDTO.setPiano(aula.getPiano());
				
				ListAulaDTO.add(aulaDTO);
				
			}
			return ListAulaDTO;
			
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
	public Aula save(@RequestBody AulaDTO aulaDTO) {
		
		Aula aula = new Aula();
		
		aula.setIdAula(aulaDTO.getIdAula());
		aula.setNome(aulaDTO.getNome());
		aula.setLatitudine(aulaDTO.getLatitudine());
		aula.setLongitudine(aulaDTO.getLongitudine());
		aula.setEdificio(aulaDTO.getEdificio());
		aula.setPiano(aulaDTO.getPiano());
		
		return aulaService.save(aula);
	}

	
	
	@PostMapping(value="/updateAulaById/{idAula}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Aula updateAulaById(@PathVariable("idAula") int idAula, @RequestBody AulaDTO aulaDTO) {
		
		Aula aulaUpdate = aulaService.updateAulaById(idAula);
		
		aulaUpdate.setIdAula(aulaDTO.getIdAula());
		aulaUpdate.setNome(aulaDTO.getNome());
		aulaUpdate.setLatitudine(aulaDTO.getLatitudine());
		aulaUpdate.setLongitudine(aulaDTO.getLongitudine());
		aulaUpdate.setEdificio(aulaDTO.getEdificio());
		aulaUpdate.setPiano(aulaDTO.getPiano());
		
		return aulaService.save(aulaUpdate);
		
	}
	
//	@PostMapping(value="/deleteAulaById/{idAula}", consumes=MediaType.APPLICATION_JSON_VALUE)
//	public void deleteAulaById(@PathVariable("idAula") int idAula) throws AulaNotFoundException{
//		
//		aulaService.deleteAulaById(idAula);
//		
//	}

	
	

}
