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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.ILezioneService;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.dto.composite.LezioneDTO;

@RestController
@RequestMapping("/lez")
public class LezioneRestController {
	
	@Autowired
	ILezioneService lezioneService;
	
	
	
	public LezioneRestController() {
		super();
	}
	
	public LezioneRestController(ILezioneService lezioneService) {
		this.lezioneService = lezioneService;
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LezioneDTO>> findAll() throws Exception {
		try {
			
			List<Lezione> lezlist = lezioneService.findAll();
			Iterator<Lezione> lezIterator = lezlist.iterator();
			
			List<LezioneDTO> listLezDTO = new ArrayList<LezioneDTO>();
			
			while (lezIterator.hasNext()) {
				
				Lezione lezione = lezIterator.next();
				LezioneDTO LezDTO = new LezioneDTO(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
						lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
						lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listLezDTO.add(LezDTO);
				
			}
			return new ResponseEntity<List<LezioneDTO>>(listLezDTO,HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<LezioneDTO>>(HttpStatus.BAD_REQUEST);

		}
	}

	
	@GetMapping(value="/getLezioniByDocente/{cognome}/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LezioneDTO>> getLezioniByDocente(@PathVariable("cognome") String cognome, @PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Lezione> lezlist = lezioneService.getLezioniByDocente(cognome, nome);
			Iterator<Lezione> lezIterator = lezlist.iterator();
			
			List<LezioneDTO> listLezDTO = new ArrayList<LezioneDTO>();
			
			while (lezIterator.hasNext()) {
				
				Lezione lezione = lezIterator.next();
				LezioneDTO LezDTO = new LezioneDTO(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
						lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
						lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listLezDTO.add(LezDTO);
				
			}
			if (listLezDTO.isEmpty())
			{
				return new ResponseEntity<List<LezioneDTO>>(listLezDTO,HttpStatus.NOT_FOUND);				
			}
			else 
			{
				return new ResponseEntity<List<LezioneDTO>>(listLezDTO,HttpStatus.OK);				
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<LezioneDTO>>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@GetMapping(value="/getLezioniByInsegnamento/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LezioneDTO>> getLezioniByInsegnamento(@PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Lezione> lezlist = lezioneService.getLezioniByInsegnamento(nome);
			Iterator<Lezione> lezIterator = lezlist.iterator();
			
			List<LezioneDTO> listLezDTO = new ArrayList<LezioneDTO>();
			
			while (lezIterator.hasNext()) {
				
				Lezione lezione = lezIterator.next();
				LezioneDTO LezDTO = new LezioneDTO(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
						lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
						lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listLezDTO.add(LezDTO);
				
			}
			if (listLezDTO.isEmpty())
			{
				return new ResponseEntity<List<LezioneDTO>>(listLezDTO,HttpStatus.NOT_FOUND);				
			}
			else 
			{
				return new ResponseEntity<List<LezioneDTO>>(listLezDTO,HttpStatus.OK);				
			}			
		} catch (Exception e) {
			
			return new ResponseEntity<List<LezioneDTO>>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@GetMapping(value="/getLezioniByCorso/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LezioneDTO>> getLezioniByCorso(@PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Lezione> lezlist = lezioneService.getLezioniByCorso(nome);
			Iterator<Lezione> lezIterator = lezlist.iterator();
			
			List<LezioneDTO> listLezDTO = new ArrayList<LezioneDTO>();
			
			while (lezIterator.hasNext()) {
				
				Lezione lezione = lezIterator.next();
				LezioneDTO LezDTO = new LezioneDTO(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
						lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
						lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listLezDTO.add(LezDTO);
				
			}
			if (listLezDTO.isEmpty())
			{
				return new ResponseEntity<List<LezioneDTO>>(listLezDTO,HttpStatus.NOT_FOUND);				
			}
			else 
			{
				return new ResponseEntity<List<LezioneDTO>>(listLezDTO,HttpStatus.OK);				
			}			
		} catch (Exception e) {
			
			return new ResponseEntity<List<LezioneDTO>>(HttpStatus.BAD_REQUEST);

		}
	}
	

}
