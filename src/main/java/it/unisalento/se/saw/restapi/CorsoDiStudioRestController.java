package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.ICalendarioService;
import it.unisalento.se.saw.IService.ICorsoDiStudioService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.dto.CorsoDiStudioDTO;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.InsegnamentoDTO;
import it.unisalento.se.saw.repositories.CorsoDiStudioRepository;
import it.unisalento.se.saw.services.CorsoDiStudioService;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;
import it.unisalento.se.saw.strategy.StringSortStrategy;

@RestController
@RequestMapping("/corso")
public class CorsoDiStudioRestController {
	
	@Autowired
	ICorsoDiStudioService corsoDiStudioService;	
	
	
	public CorsoDiStudioRestController() {
		super();
	}
	
	public CorsoDiStudioRestController(ICorsoDiStudioService corsoDiStudioService) {
		this.corsoDiStudioService = corsoDiStudioService;
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CorsoDiStudioDTO>> findAll() throws Exception {
		try {
			
			List<CorsoDiStudio> corsoList = corsoDiStudioService.findAll();
			Iterator<CorsoDiStudio> corsoIterator = corsoList.iterator();
			List<String> corsiarray = new ArrayList<String>();

			
			List<CorsoDiStudioDTO> listCorsoDTO = new ArrayList<CorsoDiStudioDTO>();
					
			
			while(corsoIterator.hasNext())
			{
				CorsoDiStudio corso = corsoIterator.next();
				corsiarray.add(corso.getNome());
				
			}
			
			SortStrategy<String> stringsort = new StringSortStrategy();
			SortContext stringorderer = new SortContext<String>(stringsort);
			stringorderer.setList(corsiarray);
			stringorderer.sort();
			
			for(String s : corsiarray) {
				
				corsoIterator = corsoList.iterator();
				
				while(corsoIterator.hasNext()) {
					
					CorsoDiStudio corso = corsoIterator.next();
					
					if (corso.getNome().equals(s)) {
						
						CorsoDiStudioDTO corsoDTO = new CorsoDiStudioDTO();			
						
						corsoDTO.setIdcorsoDiStudio(corso.getIdCorsoDiStudio());
						corsoDTO.setNome(corso.getNome());
						corsoDTO.setDescrizione(corso.getDescrizione());	
						corsoDTO.setTipo(corso.getTipo());
						
						listCorsoDTO.add(corsoDTO);
						
						corsoList.remove(corso);
						
						break;
					}
				}
			}
			
			return new ResponseEntity<List<CorsoDiStudioDTO>>(listCorsoDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<CorsoDiStudioDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getByTipo/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CorsoDiStudioDTO>> getByTipo(@PathVariable("string") String string) throws Exception {
		try {
			
			List<CorsoDiStudio> corsoList = new ArrayList<CorsoDiStudio>();
			
			if (string.equals("Tutti")) {
				
				corsoList = corsoDiStudioService.findAll();

			} else {
				
				corsoList = corsoDiStudioService.getByTipo(string);
			}
			
			Iterator<CorsoDiStudio> corsoIterator = corsoList.iterator();
			List<String> corsiarray = new ArrayList<String>();

			
			List<CorsoDiStudioDTO> listCorsoDTO = new ArrayList<CorsoDiStudioDTO>();
					
			while(corsoIterator.hasNext())
			{
				CorsoDiStudio corso = corsoIterator.next();
				corsiarray.add(corso.getNome());
				
			}
			
			SortStrategy<String> stringsort = new StringSortStrategy();
			SortContext stringorderer = new SortContext<String>(stringsort);
			stringorderer.setList(corsiarray);
			stringorderer.sort();
			
			for(String s : corsiarray) {
				
				corsoIterator = corsoList.iterator();
				
				while(corsoIterator.hasNext()) {
					
					CorsoDiStudio corso = corsoIterator.next();
					
					if (corso.getNome().equals(s)) {
						
						CorsoDiStudioDTO corsoDTO = new CorsoDiStudioDTO();			
						
						corsoDTO.setIdcorsoDiStudio(corso.getIdCorsoDiStudio());
						corsoDTO.setNome(corso.getNome());
						corsoDTO.setDescrizione(corso.getDescrizione());	
						corsoDTO.setTipo(corso.getTipo());
						
						listCorsoDTO.add(corsoDTO);
						
						corsoList.remove(corso);
						
						break;
					}
				}
			}
			if (listCorsoDTO.isEmpty())
			{
				return new ResponseEntity<List<CorsoDiStudioDTO>>(listCorsoDTO, HttpStatus.NOT_FOUND);				
			}
			else
			{
				return new ResponseEntity<List<CorsoDiStudioDTO>>(listCorsoDTO, HttpStatus.OK);
			}			
		} catch (Exception e) {
			
			return new ResponseEntity<List<CorsoDiStudioDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getById/{idCorso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorsoDiStudioDTO> getById(@PathVariable("idCorso") int idCorso) throws Exception {
		try {
			
			CorsoDiStudio corso = corsoDiStudioService.getById(idCorso);
			CorsoDiStudioDTO corsoDTO = new CorsoDiStudioDTO();			
			
			corsoDTO.setIdcorsoDiStudio(corso.getIdCorsoDiStudio());
			corsoDTO.setNome(corso.getNome());
			corsoDTO.setDescrizione(corso.getDescrizione());	
			corsoDTO.setTipo(corso.getTipo());

			return new ResponseEntity<CorsoDiStudioDTO>(corsoDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<CorsoDiStudioDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/newCorso", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorsoDiStudio> save(@RequestBody CorsoDiStudioDTO corsoDiStudioDTO) throws Exception {
		try {
			
			CorsoDiStudio newCorso = new CorsoDiStudio();
			
			newCorso.setNome(corsoDiStudioDTO.getNome());
			newCorso.setDescrizione(corsoDiStudioDTO.getDescrizione());
			newCorso.setTipo(corsoDiStudioDTO.getTipo());
			newCorso = corsoDiStudioService.save(newCorso);
			// System.out.println(newCorso.getIdCorsoDiStudio());
			

			// return new ResponseEntity<CorsoDiStudio>(corsoDiStudioService.save(newCorso), HttpStatus.CREATED);
			return new ResponseEntity<CorsoDiStudio>(newCorso, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<CorsoDiStudio>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/updateById/{idCorso}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CorsoDiStudio> update(@PathVariable("idCorso") int idCorso, @RequestBody CorsoDiStudioDTO corsoDiStudioDTO) throws Exception {
		try {
			
			CorsoDiStudio updateCorso = corsoDiStudioService.getById(idCorso);
			
//			updateCorso.setIdCorsoDiStudio(corsoDiStudioDTO.getIdcorsoDiStudio());
			updateCorso.setNome(corsoDiStudioDTO.getNome());
			updateCorso.setDescrizione(corsoDiStudioDTO.getDescrizione());
			updateCorso.setTipo(corsoDiStudioDTO.getTipo());
			
			return new ResponseEntity<CorsoDiStudio>(corsoDiStudioService.save(updateCorso), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<CorsoDiStudio>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
