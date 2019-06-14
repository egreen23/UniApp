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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IInsegnamentoService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.dto.InsegnamentoDTO;
import it.unisalento.se.saw.dto.LezioneDTO;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;
import it.unisalento.se.saw.strategy.StringSortStrategy;

@RestController
@RequestMapping("/insegnamento")
public class InsegnamentoRestController {
	
	@Autowired
	IInsegnamentoService insegnamentoService;
	
	
	
	public InsegnamentoRestController() {
		super();
	}
	
	public InsegnamentoRestController(IInsegnamentoService insegnamentoService) {
		this.insegnamentoService = insegnamentoService;
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InsegnamentoDTO>> findAll() throws Exception {
		try {
			
			List<Insegnamento> insList = insegnamentoService.findAll();
			Iterator<Insegnamento> insIterator = insList.iterator();
			List<String> nomiins = new ArrayList<String>();

			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
			while(insIterator.hasNext())
			{
				Insegnamento ins = insIterator.next();
				nomiins.add(ins.getNome());
				
				
			}
			
			SortStrategy<String> stringsort = new StringSortStrategy();
			SortContext stringorderer = new SortContext<String>(stringsort);
			stringorderer.setList(nomiins);
			stringorderer.sort();
			
			for(String s : nomiins) {
				insIterator = insList.iterator();
				
				while(insIterator.hasNext()) {
					Insegnamento ins = insIterator.next();
					
					if (ins.getNome().equals(s)) {
						
						InsegnamentoDTO insDTO = new InsegnamentoDTO();			
						
						insDTO.setIdInsegnamento(ins.getIdInsegnamento());
						insDTO.setNome(ins.getNome());
						insDTO.setCrediti(ins.getCrediti());
						insDTO.setDescrizione(ins.getDescrizione());
						insDTO.setAnnoCorso(ins.getAnnoCorso());
						insDTO.setIdDocente(ins.getDocente().getIdDocente());
						insDTO.setIdCorsoDiStudio(ins.getCorsoDiStudio().getIdCorsoDiStudio());
						insDTO.setNomeDocente(ins.getDocente().getUser().getNome()); //niko
						insDTO.setCognomeDocente(ins.getDocente().getUser().getCognome());
						insDTO.setNomeCorsoDiStudio(ins.getCorsoDiStudio().getNome());
						insDTO.setTipo(ins.getCorsoDiStudio().getTipo());
						String prof = ins.getDocente().getUser().getNome() + " " + ins.getDocente().getUser().getCognome();
						insDTO.setProfessore(prof);
						
						listInsDTO.add(insDTO);
						
						insList.remove(ins);
						
						break;
					}

				}
			}
			
			return new ResponseEntity<List<InsegnamentoDTO>>(listInsDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<InsegnamentoDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getById/{idInsegnamento}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InsegnamentoDTO> getById(@PathVariable("idInsegnamento") int idInsegnamento) throws Exception {
		try { 
						
			Insegnamento ins = insegnamentoService.getById(idInsegnamento);
	
			InsegnamentoDTO insDTO = new InsegnamentoDTO();
			
			insDTO.setIdInsegnamento(ins.getIdInsegnamento());
			insDTO.setNome(ins.getNome());
			insDTO.setCrediti(ins.getCrediti());
			insDTO.setDescrizione(ins.getDescrizione());
			insDTO.setAnnoCorso(ins.getAnnoCorso());
			insDTO.setIdCorsoDiStudio(ins.getCorsoDiStudio().getIdCorsoDiStudio());
			insDTO.setIdDocente(ins.getDocente().getIdDocente());
			
			insDTO.setNomeDocente(ins.getDocente().getUser().getNome());
			insDTO.setCognomeDocente(ins.getDocente().getUser().getCognome());
			insDTO.setNomeCorsoDiStudio(ins.getCorsoDiStudio().getNome());
			insDTO.setTipo(ins.getCorsoDiStudio().getTipo());
			String prof = insDTO.getNomeDocente() + " " + insDTO.getCognomeDocente();
			insDTO.setProfessore(prof);
			return new ResponseEntity<InsegnamentoDTO>(insDTO, HttpStatus.OK);

			
		} catch (Exception e) {
			
			return new ResponseEntity<InsegnamentoDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/newInsegnamento", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Insegnamento> save(@RequestBody InsegnamentoDTO insegnamentoDTO) throws Exception {
		try { 
			
			Insegnamento newInsegnamento = new Insegnamento(); 
			
			Docente doc = new Docente();
			CorsoDiStudio corsoStudio = new CorsoDiStudio();

			doc.setIdDocente(insegnamentoDTO.getIdDocente());
			corsoStudio.setIdCorsoDiStudio(insegnamentoDTO.getIdCorsoDiStudio());
			
			newInsegnamento.setNome(insegnamentoDTO.getNome());
			newInsegnamento.setCrediti(insegnamentoDTO.getCrediti());
			newInsegnamento.setDescrizione(insegnamentoDTO.getDescrizione());
			newInsegnamento.setAnnoCorso(insegnamentoDTO.getAnnoCorso());
			newInsegnamento.setDocente(doc);
			newInsegnamento.setCorsoDiStudio(corsoStudio);
						
			return new ResponseEntity<Insegnamento>(insegnamentoService.save(newInsegnamento), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Insegnamento>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@PostMapping(value="/updateById/{idInsegnamento}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Insegnamento> updateById(@PathVariable("idInsegnamento") int idInsegnamento, @RequestBody InsegnamentoDTO insegnamentoDTO) throws Exception {
		try { 
			
			Insegnamento insegnUpdate = insegnamentoService.getById(idInsegnamento);
										
//			insegnUpdate.setIdInsegnamento(insegnamentoDTO.getIdInsegnamento());
			insegnUpdate.setNome(insegnamentoDTO.getNome());
			insegnUpdate.setCrediti(insegnamentoDTO.getCrediti());
			insegnUpdate.setDescrizione(insegnamentoDTO.getDescrizione());
			insegnUpdate.setAnnoCorso(insegnamentoDTO.getAnnoCorso());
			insegnUpdate.getDocente().setIdDocente(insegnamentoDTO.getIdDocente());
			insegnUpdate.getCorsoDiStudio().setIdCorsoDiStudio(insegnamentoDTO.getIdCorsoDiStudio());
			
			
			return new ResponseEntity<Insegnamento>(insegnamentoService.save(insegnUpdate), HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Insegnamento>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping(value="/getByIdCorso/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InsegnamentoDTO>> getByIdCorso(@PathVariable("id") int id) throws Exception {
		try {
			
			List<Insegnamento> insList = insegnamentoService.getByIdCorso(id);
			Iterator<Insegnamento> insIterator = insList.iterator();
			List<String> nomiins = new ArrayList<String>();

			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
			while(insIterator.hasNext())
			{
				Insegnamento ins = insIterator.next();
				nomiins.add(ins.getNome());
				
				
			}
			
			SortStrategy<String> stringsort = new StringSortStrategy();
			SortContext stringorderer = new SortContext<String>(stringsort);
			stringorderer.setList(nomiins);
			stringorderer.sort();
			
			for(String s : nomiins) {
				insIterator = insList.iterator();
				
				while(insIterator.hasNext()) {
					Insegnamento ins = insIterator.next();
					
					if (ins.getNome().equals(s)) {
						
						InsegnamentoDTO insDTO = new InsegnamentoDTO();			
						
						insDTO.setIdInsegnamento(ins.getIdInsegnamento());
						insDTO.setNome(ins.getNome());
						insDTO.setCrediti(ins.getCrediti());
						insDTO.setDescrizione(ins.getDescrizione());
						insDTO.setAnnoCorso(ins.getAnnoCorso());
						insDTO.setIdDocente(ins.getDocente().getIdDocente());
						insDTO.setIdCorsoDiStudio(ins.getCorsoDiStudio().getIdCorsoDiStudio());
						insDTO.setNomeDocente(ins.getDocente().getUser().getNome()); //niko
						insDTO.setCognomeDocente(ins.getDocente().getUser().getCognome());
						insDTO.setNomeCorsoDiStudio(ins.getCorsoDiStudio().getNome());
						insDTO.setTipo(ins.getCorsoDiStudio().getTipo());
						String prof = ins.getDocente().getUser().getNome() + " " + ins.getDocente().getUser().getCognome();
						insDTO.setProfessore(prof);
						
						listInsDTO.add(insDTO);
						
						insList.remove(ins);
						
						break;
					}

				}
			}
			if (listInsDTO.isEmpty())
			{
				return new ResponseEntity<List<InsegnamentoDTO>>(listInsDTO, HttpStatus.NOT_FOUND);				
			}
			else
			{
				return new ResponseEntity<List<InsegnamentoDTO>>(listInsDTO, HttpStatus.OK);
			}			
		} catch (Exception e) {
			
			return new ResponseEntity<List<InsegnamentoDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	

	
	//nuovo metodo CH
		@GetMapping(value="/getByIdDocente/{idDocente}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<InsegnamentoDTO>> getByDocente(@PathVariable("idDocente") int idDocente) throws Exception {
			try {
				
				List<Insegnamento> insList = insegnamentoService.getByIdDocente(idDocente);
				Iterator<Insegnamento> insIterator = insList.iterator();
				List<String> nomiins = new ArrayList<String>();

				List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
						
				
				while(insIterator.hasNext())
				{
					Insegnamento ins = insIterator.next();
					nomiins.add(ins.getNome());
					
					
				}
				
				SortStrategy<String> stringsort = new StringSortStrategy();
				SortContext stringorderer = new SortContext<String>(stringsort);
				stringorderer.setList(nomiins);
				stringorderer.sort();
				
				for(String s : nomiins) {
					insIterator = insList.iterator();
					
					while(insIterator.hasNext()) {
						Insegnamento ins = insIterator.next();
						
						if (ins.getNome().equals(s)) {
							
							InsegnamentoDTO insDTO = new InsegnamentoDTO();			
							
							insDTO.setIdInsegnamento(ins.getIdInsegnamento());
							insDTO.setNome(ins.getNome());
							insDTO.setCrediti(ins.getCrediti());
							insDTO.setDescrizione(ins.getDescrizione());
							insDTO.setAnnoCorso(ins.getAnnoCorso());
							insDTO.setIdDocente(ins.getDocente().getIdDocente());
							insDTO.setIdCorsoDiStudio(ins.getCorsoDiStudio().getIdCorsoDiStudio());
							insDTO.setNomeDocente(ins.getDocente().getUser().getNome()); //niko
							insDTO.setCognomeDocente(ins.getDocente().getUser().getCognome());
							insDTO.setNomeCorsoDiStudio(ins.getCorsoDiStudio().getNome());
							insDTO.setTipo(ins.getCorsoDiStudio().getTipo());
							String prof = ins.getDocente().getUser().getNome() + " " + ins.getDocente().getUser().getCognome();
							insDTO.setProfessore(prof);
							
							listInsDTO.add(insDTO);
							
							insList.remove(ins);
							
							break;
						}

					}
				}
				if (listInsDTO.isEmpty())
				{
					return new ResponseEntity<List<InsegnamentoDTO>>(listInsDTO, HttpStatus.NOT_FOUND);				
				}
				else
				{
					return new ResponseEntity<List<InsegnamentoDTO>>(listInsDTO, HttpStatus.OK);
				}			
			} catch (Exception e) {
				
				return new ResponseEntity<List<InsegnamentoDTO>>(HttpStatus.BAD_REQUEST);
			}
		}

}
