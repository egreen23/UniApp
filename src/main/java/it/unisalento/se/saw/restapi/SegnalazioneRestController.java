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

import it.unisalento.se.saw.IService.ISegnalazioneService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.dto.InsegnamentoDTO;
import it.unisalento.se.saw.dto.SegnalazioneDTO;

@RestController
@RequestMapping("segnala")
public class SegnalazioneRestController {
	
	@Autowired
	ISegnalazioneService segnalazioneService;
	
	
	
	public SegnalazioneRestController() {
		super();
	}

	public SegnalazioneRestController(ISegnalazioneService segnalazioneService) {
		this.segnalazioneService = segnalazioneService;
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegnalazioneDTO>> findAll() throws Exception {
		try {
			
			List<Segnalazione> segnalList = segnalazioneService.findAll();
			Iterator<Segnalazione> segnalIterator = segnalList.iterator();
			
			List<SegnalazioneDTO> listSegnalDTO = new ArrayList<SegnalazioneDTO>();
					
			
			while(segnalIterator.hasNext())
			{
				Segnalazione segnal = segnalIterator.next();
				SegnalazioneDTO segnalazioneDTO = new SegnalazioneDTO();			
				
				segnalazioneDTO.setIdSegnalazione(segnal.getIdSegnalazione());
				segnalazioneDTO.setTesto(segnal.getTesto());
				segnalazioneDTO.setStato(segnal.getStato());
				segnalazioneDTO.setIdSegreteria(segnal.getSegreteria().getIdSegreteria());
				segnalazioneDTO.setIdDocente(segnal.getDocente().getIdDocente());
				segnalazioneDTO.setCognomeDocente(segnal.getDocente().getUser().getCognome());
				segnalazioneDTO.setNomeDocente(segnal.getDocente().getUser().getNome());
				segnalazioneDTO.setEmailDocente(segnal.getDocente().getUser().getEmail());
				segnalazioneDTO.setCognomeSegretario(segnal.getSegreteria().getUser().getCognome());
				segnalazioneDTO.setNomeSegretario(segnal.getSegreteria().getUser().getNome());
				segnalazioneDTO.setEmailSegreteria(segnal.getSegreteria().getUser().getEmail());

				
				listSegnalDTO.add(segnalazioneDTO);
				
			}
			return new ResponseEntity<List<SegnalazioneDTO>>(listSegnalDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<SegnalazioneDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getByStato/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegnalazioneDTO>> getByStato(@PathVariable("string") String string) throws Exception {
		try {
			
			List<Segnalazione> segnalList = segnalazioneService.getByStato(string);
			Iterator<Segnalazione> segnalIterator = segnalList.iterator();
			
			List<SegnalazioneDTO> listSegnalDTO = new ArrayList<SegnalazioneDTO>();
					
			
			while(segnalIterator.hasNext())
			{
				Segnalazione segnal = segnalIterator.next();
				SegnalazioneDTO segnalazioneDTO = new SegnalazioneDTO();			
				
				segnalazioneDTO.setIdSegnalazione(segnal.getIdSegnalazione());
				segnalazioneDTO.setTesto(segnal.getTesto());
				segnalazioneDTO.setStato(segnal.getStato());
				segnalazioneDTO.setIdSegreteria(segnal.getSegreteria().getIdSegreteria());
				segnalazioneDTO.setIdDocente(segnal.getDocente().getIdDocente());
				segnalazioneDTO.setCognomeDocente(segnal.getDocente().getUser().getCognome());
				segnalazioneDTO.setNomeDocente(segnal.getDocente().getUser().getNome());
				segnalazioneDTO.setEmailDocente(segnal.getDocente().getUser().getEmail());
				segnalazioneDTO.setCognomeSegretario(segnal.getSegreteria().getUser().getCognome());
				segnalazioneDTO.setNomeSegretario(segnal.getSegreteria().getUser().getNome());
				segnalazioneDTO.setEmailSegreteria(segnal.getSegreteria().getUser().getEmail());

				
				listSegnalDTO.add(segnalazioneDTO);
				
			}
			return new ResponseEntity<List<SegnalazioneDTO>>(listSegnalDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<SegnalazioneDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getByDocente/{cognome}/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegnalazioneDTO>> getByDocente(@PathVariable("cognome") String cognome, @PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Segnalazione> segnalList = segnalazioneService.getByDocente(cognome, nome);
			Iterator<Segnalazione> segnalIterator = segnalList.iterator();
			
			List<SegnalazioneDTO> listSegnalDTO = new ArrayList<SegnalazioneDTO>();
					
			
			while(segnalIterator.hasNext())
			{
				Segnalazione segnal = segnalIterator.next();
				SegnalazioneDTO segnalazioneDTO = new SegnalazioneDTO();			
				
				segnalazioneDTO.setIdSegnalazione(segnal.getIdSegnalazione());
				segnalazioneDTO.setTesto(segnal.getTesto());
				segnalazioneDTO.setStato(segnal.getStato());
				segnalazioneDTO.setIdSegreteria(segnal.getSegreteria().getIdSegreteria());
				segnalazioneDTO.setIdDocente(segnal.getDocente().getIdDocente());
				segnalazioneDTO.setCognomeDocente(segnal.getDocente().getUser().getCognome());
				segnalazioneDTO.setNomeDocente(segnal.getDocente().getUser().getNome());
				segnalazioneDTO.setEmailDocente(segnal.getDocente().getUser().getEmail());
				segnalazioneDTO.setCognomeSegretario(segnal.getSegreteria().getUser().getCognome());
				segnalazioneDTO.setNomeSegretario(segnal.getSegreteria().getUser().getNome());
				segnalazioneDTO.setEmailSegreteria(segnal.getSegreteria().getUser().getEmail());

				
				listSegnalDTO.add(segnalazioneDTO);
				
			}
			return new ResponseEntity<List<SegnalazioneDTO>>(listSegnalDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<SegnalazioneDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getById/{idSegnalazione}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SegnalazioneDTO> getByDocente(@PathVariable("idSegnalazione") int idSegnalazione) throws Exception {
		try {
			Segnalazione segnal = segnalazioneService.getById(idSegnalazione);
			SegnalazioneDTO segnalazioneDTO = new SegnalazioneDTO();			
			
			segnalazioneDTO.setIdSegnalazione(segnal.getIdSegnalazione());
			segnalazioneDTO.setTesto(segnal.getTesto());
			segnalazioneDTO.setStato(segnal.getStato());
			segnalazioneDTO.setIdSegreteria(segnal.getSegreteria().getIdSegreteria());
			segnalazioneDTO.setIdDocente(segnal.getDocente().getIdDocente());
			segnalazioneDTO.setCognomeDocente(segnal.getDocente().getUser().getCognome());
			segnalazioneDTO.setNomeDocente(segnal.getDocente().getUser().getNome());
			segnalazioneDTO.setEmailDocente(segnal.getDocente().getUser().getEmail());
			segnalazioneDTO.setCognomeSegretario(segnal.getSegreteria().getUser().getCognome());
			segnalazioneDTO.setNomeSegretario(segnal.getSegreteria().getUser().getNome());
			segnalazioneDTO.setEmailSegreteria(segnal.getSegreteria().getUser().getEmail());

			return new ResponseEntity<SegnalazioneDTO>(segnalazioneDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<SegnalazioneDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PostMapping(value="/newSegnalazione", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segnalazione> save(@RequestBody SegnalazioneDTO segnalazioneDTO) throws Exception {
		try { 
			
			Segnalazione newSegnal = new Segnalazione(); 
			
			Docente doc = new Docente();
			Segreteria seg = new Segreteria();
			
			doc.setIdDocente(segnalazioneDTO.getIdDocente());
			seg.setIdSegreteria(segnalazioneDTO.getIdSegreteria());

			newSegnal.setTesto(segnalazioneDTO.getTesto());
			newSegnal.setStato(segnalazioneDTO.getStato());
			newSegnal.setDocente(doc);
			newSegnal.setSegreteria(seg);
			
			return new ResponseEntity<Segnalazione>(segnalazioneService.save(newSegnal), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Segnalazione>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/updateStatoSegnal/{idSegnalazione}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segnalazione> updateStatoSegnal(@PathVariable("idSegnalazione") int idSegnalazione, @RequestBody SegnalazioneDTO segnalazioneDTO) throws Exception {
		try { 
			
			Segnalazione updateSegnal = segnalazioneService.updateStatoSegnal(idSegnalazione);

//			updateSegnal.setIdSegnalazione(segnalazioneDTO.getIdSegnalazione());
			updateSegnal.setStato(segnalazioneDTO.getStato());
			
			return new ResponseEntity<Segnalazione>(segnalazioneService.save(updateSegnal), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Segnalazione>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	

}
