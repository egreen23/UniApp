package it.unisalento.se.saw.restapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.dto.CalendarioDTO;
import it.unisalento.se.saw.dto.InsegnamentoDTO;
import it.unisalento.se.saw.dto.SegnalazioneDTO;
import it.unisalento.se.saw.strategy.DateSortStrategy;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;

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
	
	
	
	
	
	
	@GetMapping(value="/getById/{idSegnalazione}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SegnalazioneDTO> getById(@PathVariable("idSegnalazione") int idSegnalazione) throws Exception {
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
			segnalazioneDTO.setIdAula(segnal.getAula().getIdAula());
			segnalazioneDTO.setNomeAula(segnal.getAula().getNome());
			segnalazioneDTO.setTitolo(segnal.getTitolo());
			
			String prof = segnal.getDocente().getUser().getNome() + " "+segnal.getDocente().getUser().getCognome();
			segnalazioneDTO.setProf(prof);
			String seg = segnal.getSegreteria().getUser().getNome()+" "+segnal.getSegreteria().getUser().getCognome();
			segnalazioneDTO.setSeg(seg);
			segnalazioneDTO.setData(segnal.getData());

			segnalazioneDTO.setNota(segnal.getNota());
			
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
			Aula room = new Aula();
			
			doc.setIdDocente(segnalazioneDTO.getIdDocente());
			seg.setIdSegreteria(segnalazioneDTO.getIdSegreteria());
			room.setIdAula(segnalazioneDTO.getIdAula());

			newSegnal.setTesto(segnalazioneDTO.getTesto());
			newSegnal.setStato(segnalazioneDTO.getStato());
			newSegnal.setData(segnalazioneDTO.getData());
			newSegnal.setTitolo(segnalazioneDTO.getTitolo());
			// newSegnal.setNota(" ");
			newSegnal.setDocente(doc);
			newSegnal.setSegreteria(seg);
			newSegnal.setAula(room);
			
			return new ResponseEntity<Segnalazione>(segnalazioneService.save(newSegnal), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Segnalazione>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/updateSegnal/{idSegnalazione}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segnalazione> updateStatoSegnal(@PathVariable("idSegnalazione") int idSegnalazione, @RequestBody SegnalazioneDTO segnalazioneDTO) throws Exception {
		try { 
			
			Segnalazione updateSegnal = segnalazioneService.getById(idSegnalazione);

//			updateSegnal.setIdSegnalazione(segnalazioneDTO.getIdSegnalazione());
			updateSegnal.setNota(segnalazioneDTO.getNota());
			updateSegnal.setStato(segnalazioneDTO.getStato());
			
			return new ResponseEntity<Segnalazione>(segnalazioneService.save(updateSegnal), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Segnalazione>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/getbyIdDocente/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegnalazioneDTO>> getbyIdDocente(@PathVariable("id") int id) throws Exception {
		try {
			
			List<Segnalazione> segnalList = segnalazioneService.getByidDocente(id);
			
			Iterator<Segnalazione> segnalIterator = segnalList.iterator();
			List<Date> datearray = new ArrayList<Date>();

			List<SegnalazioneDTO> listSegnalDTO = new ArrayList<SegnalazioneDTO>();
					
			
			while(segnalIterator.hasNext())
			{
				Segnalazione segnal = segnalIterator.next();
				
				Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(segnal.getData());
				datearray.add(date1);
						
				
			}
			
			SortStrategy<Date> datesort = new DateSortStrategy();
			SortContext dateorderer = new SortContext<Date>(datesort);
			dateorderer.setList(datearray);
			dateorderer.sort();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

			for(Date d : datearray) {
				
				String strDate = dateFormat.format(d);  

				segnalIterator = segnalList.iterator();
				
				while(segnalIterator.hasNext()) {
					
					Segnalazione segnal = segnalIterator.next();
					
					if (segnal.getData().equals(strDate)) {
												
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
						segnalazioneDTO.setIdAula(segnal.getAula().getIdAula());
						segnalazioneDTO.setNomeAula(segnal.getAula().getNome());
						String prof = segnal.getDocente().getUser().getNome() + " "+segnal.getDocente().getUser().getCognome();
						segnalazioneDTO.setProf(prof);
						String seg = segnal.getSegreteria().getUser().getNome()+" "+segnal.getSegreteria().getUser().getCognome();
						segnalazioneDTO.setSeg(seg);
						segnalazioneDTO.setData(segnal.getData());
						segnalazioneDTO.setTitolo(segnal.getTitolo());
						segnalazioneDTO.setNota(segnal.getNota());


						listSegnalDTO.add(segnalazioneDTO);
						
						segnalList.remove(segnal);
						
						break;
					}
				}
				
			}
			return new ResponseEntity<List<SegnalazioneDTO>>(listSegnalDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<SegnalazioneDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/filterDoc/{id}/{stato}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegnalazioneDTO>> filterDoc(@PathVariable("id") int id, @PathVariable("stato") String stato) throws Exception {
		try {
			
			List<Segnalazione> segnalList = segnalazioneService.getByidDocente(id);
			List<Date> datearray = new ArrayList<Date>();

			Iterator<Segnalazione> segnalIterator = segnalList.iterator();
			
			List<SegnalazioneDTO> listSegnalDTO = new ArrayList<SegnalazioneDTO>();
					
			
			while(segnalIterator.hasNext())
			{
				Segnalazione segnal = segnalIterator.next();				
				if (segnal.getStato().equals(stato) || stato.equals("Tutte"))
				{					
					Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(segnal.getData());
					datearray.add(date1);
					
				}
			}
			
			SortStrategy<Date> datesort = new DateSortStrategy();
			SortContext dateorderer = new SortContext<Date>(datesort);
			dateorderer.setList(datearray);
			dateorderer.sort();
				
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				
			for(Date d : datearray) {
					
				String strDate = dateFormat.format(d);  

				segnalIterator = segnalList.iterator();
					
				while(segnalIterator.hasNext()) {
						
					Segnalazione segnal = segnalIterator.next();
						
					if (segnal.getData().equals(strDate) && (segnal.getStato().equals(stato) || stato.equals("Tutte"))) {
													
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
						segnalazioneDTO.setIdAula(segnal.getAula().getIdAula());
						segnalazioneDTO.setNomeAula(segnal.getAula().getNome());
						segnalazioneDTO.setTitolo(segnal.getTitolo());

						String prof = segnal.getDocente().getUser().getNome() + " "+segnal.getDocente().getUser().getCognome();
						segnalazioneDTO.setProf(prof);
						String seg = segnal.getSegreteria().getUser().getNome()+" "+segnal.getSegreteria().getUser().getCognome();
						segnalazioneDTO.setSeg(seg);
						segnalazioneDTO.setData(segnal.getData());

						segnalazioneDTO.setNota(segnal.getNota());


						listSegnalDTO.add(segnalazioneDTO);
							
						segnalList.remove(segnal);
							
						break;
					}
				}
					
			}
			
			return new ResponseEntity<List<SegnalazioneDTO>>(listSegnalDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<SegnalazioneDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/getbyIdAula/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegnalazioneDTO>> getbyIdAula(@PathVariable("id") int id) throws Exception {
		try {
			
			List<Segnalazione> segnalList = segnalazioneService.getByidAula(id);
			Iterator<Segnalazione> segnalIterator = segnalList.iterator();
			List<Date> datearray = new ArrayList<Date>();

			List<SegnalazioneDTO> listSegnalDTO = new ArrayList<SegnalazioneDTO>();
					
			
			while(segnalIterator.hasNext())
			{
				Segnalazione segnal = segnalIterator.next();
				
				Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(segnal.getData());
				datearray.add(date1);
						
				
			}
			
			SortStrategy<Date> datesort = new DateSortStrategy();
			SortContext dateorderer = new SortContext<Date>(datesort);
			dateorderer.setList(datearray);
			dateorderer.sort();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

			for(Date d : datearray) {
				
				String strDate = dateFormat.format(d);  

				segnalIterator = segnalList.iterator();
				
				while(segnalIterator.hasNext()) {
					
					Segnalazione segnal = segnalIterator.next();
					
					if (segnal.getData().equals(strDate)) {
												
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
						segnalazioneDTO.setIdAula(segnal.getAula().getIdAula());
						segnalazioneDTO.setNomeAula(segnal.getAula().getNome());
						String prof = segnal.getDocente().getUser().getNome() + " "+segnal.getDocente().getUser().getCognome();
						segnalazioneDTO.setProf(prof);
						String seg = segnal.getSegreteria().getUser().getNome()+" "+segnal.getSegreteria().getUser().getCognome();
						segnalazioneDTO.setSeg(seg);
						segnalazioneDTO.setData(segnal.getData());
						segnalazioneDTO.setTitolo(segnal.getTitolo());
						segnalazioneDTO.setNota(segnal.getNota());


						listSegnalDTO.add(segnalazioneDTO);
						
						segnalList.remove(segnal);
						
						break;
					}
				}
				
			}
			return new ResponseEntity<List<SegnalazioneDTO>>(listSegnalDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<SegnalazioneDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/getbyIdSegr/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegnalazioneDTO>> getbyIdSegr(@PathVariable("id") int id) throws Exception {

		try {
			
			List<Segnalazione> segnalList = segnalazioneService.getByidSegr(id);
			Iterator<Segnalazione> segnalIterator = segnalList.iterator();
			List<Date> datearray = new ArrayList<Date>();

			List<SegnalazioneDTO> listSegnalDTO = new ArrayList<SegnalazioneDTO>();
					
			
			while(segnalIterator.hasNext())
			{
				Segnalazione segnal = segnalIterator.next();
				
				Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(segnal.getData());
				datearray.add(date1);
						
				
			}
			
			SortStrategy<Date> datesort = new DateSortStrategy();
			SortContext dateorderer = new SortContext<Date>(datesort);
			dateorderer.setList(datearray);
			dateorderer.sort();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

			for(Date d : datearray) {
				
				String strDate = dateFormat.format(d);  

				segnalIterator = segnalList.iterator();
				
				while(segnalIterator.hasNext()) {
					
					Segnalazione segnal = segnalIterator.next();
					
					if (segnal.getData().equals(strDate)) {
												
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
						segnalazioneDTO.setIdAula(segnal.getAula().getIdAula());
						segnalazioneDTO.setNomeAula(segnal.getAula().getNome());
						String prof = segnal.getDocente().getUser().getNome() + " "+segnal.getDocente().getUser().getCognome();
						segnalazioneDTO.setProf(prof);
						String seg = segnal.getSegreteria().getUser().getNome()+" "+segnal.getSegreteria().getUser().getCognome();
						segnalazioneDTO.setSeg(seg);
						segnalazioneDTO.setData(segnal.getData());
						segnalazioneDTO.setTitolo(segnal.getTitolo());
						segnalazioneDTO.setNota(segnal.getNota());


						listSegnalDTO.add(segnalazioneDTO);
						
						segnalList.remove(segnal);
						
						break;
					}
				}
				
			}
			return new ResponseEntity<List<SegnalazioneDTO>>(listSegnalDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<SegnalazioneDTO>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value="/filterSegr/{id}/{stato}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegnalazioneDTO>> filterSegr(@PathVariable("id") int id, @PathVariable("stato") String stato) throws Exception {
		try {
			
			List<Segnalazione> segnalList = segnalazioneService.getByidSegr(id);
			List<Date> datearray = new ArrayList<Date>();

			Iterator<Segnalazione> segnalIterator = segnalList.iterator();
			
			List<SegnalazioneDTO> listSegnalDTO = new ArrayList<SegnalazioneDTO>();
					
			
			while(segnalIterator.hasNext())
			{
				Segnalazione segnal = segnalIterator.next();				
				if (segnal.getStato().equals(stato) || stato.equals("Tutte"))
				{					
					Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(segnal.getData());
					datearray.add(date1);
					
				}
			}
			
			SortStrategy<Date> datesort = new DateSortStrategy();
			SortContext dateorderer = new SortContext<Date>(datesort);
			dateorderer.setList(datearray);
			dateorderer.sort();
				
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				
			for(Date d : datearray) {
					
				String strDate = dateFormat.format(d);  

				segnalIterator = segnalList.iterator();
					
				while(segnalIterator.hasNext()) {
						
					Segnalazione segnal = segnalIterator.next();
						
					if (segnal.getData().equals(strDate) && (segnal.getStato().equals(stato) || stato.equals("Tutte"))) {
													
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
						segnalazioneDTO.setIdAula(segnal.getAula().getIdAula());
						segnalazioneDTO.setNomeAula(segnal.getAula().getNome());
						segnalazioneDTO.setTitolo(segnal.getTitolo());

						String prof = segnal.getDocente().getUser().getNome() + " "+segnal.getDocente().getUser().getCognome();
						segnalazioneDTO.setProf(prof);
						String seg = segnal.getSegreteria().getUser().getNome()+" "+segnal.getSegreteria().getUser().getCognome();
						segnalazioneDTO.setSeg(seg);
						segnalazioneDTO.setData(segnal.getData());

						segnalazioneDTO.setNota(segnal.getNota());


						listSegnalDTO.add(segnalazioneDTO);
							
						segnalList.remove(segnal);
							
						break;
					}
				}
					
			}
			
			return new ResponseEntity<List<SegnalazioneDTO>>(listSegnalDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<SegnalazioneDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
}
