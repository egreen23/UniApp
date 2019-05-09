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
			
			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
			while(insIterator.hasNext())
			{
				Insegnamento ins = insIterator.next();
				InsegnamentoDTO insDTO = new InsegnamentoDTO();			
				
				insDTO.setIdInsegnamento(ins.getIdInsegnamento());
				insDTO.setNome(ins.getNome());
				insDTO.setCrediti(ins.getCrediti());
				insDTO.setDescrizione(ins.getDescrizione());
				insDTO.setAnnoCorso(ins.getAnnoCorso());
				insDTO.setIdDocente(ins.getDocente().getIdDocente());
				insDTO.setIdCorsoDiStudio(ins.getCorsoDiStudio().getIdCorsoDiStudio());
				insDTO.setNomeDocente(ins.getDocente().getUser().getNome()); //niko
				
				listInsDTO.add(insDTO);
				
			}
			return new ResponseEntity<List<InsegnamentoDTO>>(listInsDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<InsegnamentoDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
<<<<<<< HEAD
	// niko 
	/*@GetMapping(value="/getIdByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getIdByName(@PathVariable("string") String string) throws Exception {
		try {
			int Idins = insegnamentoService.getIdbyName(string);
			return new ResponseEntity<Integer>(Idins, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}*/
=======
>>>>>>> master
	
	@GetMapping(value="/getByDocente/{cognome}/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InsegnamentoDTO>> getByDocente(@PathVariable("cognome") String cognome, @PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Insegnamento> insList = insegnamentoService.getByDocente(cognome, nome);
			Iterator<Insegnamento> insIterator = insList.iterator();
<<<<<<< HEAD
			
			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
=======
			
			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
>>>>>>> master
			while(insIterator.hasNext())
			{
				Insegnamento ins = insIterator.next();
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
				
				
				listInsDTO.add(insDTO);
				
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
	
	
	@GetMapping(value="/getByInsegnamento/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InsegnamentoDTO>> getByInsegnamento(@PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Insegnamento> insList = insegnamentoService.getByInsegnamento(nome);
			Iterator<Insegnamento> insIterator = insList.iterator();
			
			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
			while(insIterator.hasNext())
			{
				Insegnamento ins = insIterator.next();
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
				
				
				listInsDTO.add(insDTO);
				
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
<<<<<<< HEAD
			
			return new ResponseEntity<List<InsegnamentoDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getByCorso/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InsegnamentoDTO>> getByCorso(@PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Insegnamento> insList = insegnamentoService.getByCorso(nome);
			Iterator<Insegnamento> insIterator = insList.iterator();
			
			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
=======
			
			return new ResponseEntity<List<InsegnamentoDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getByCorso/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InsegnamentoDTO>> getByCorso(@PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Insegnamento> insList = insegnamentoService.getByCorso(nome);
			Iterator<Insegnamento> insIterator = insList.iterator();
			
			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
>>>>>>> master
			while(insIterator.hasNext())
			{
				Insegnamento ins = insIterator.next();
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
				
				
				listInsDTO.add(insDTO);
				
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
			
			return new ResponseEntity<InsegnamentoDTO>(insDTO, HttpStatus.OK);
<<<<<<< HEAD

			
		} catch (Exception e) {
			
			return new ResponseEntity<InsegnamentoDTO>(HttpStatus.BAD_REQUEST);
=======

			
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

>>>>>>> master
		}
	}
	
	
<<<<<<< HEAD
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
	
	
=======
>>>>>>> master
	@PostMapping(value="/updateById/{idInsegnamento}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Insegnamento> updateById(@PathVariable("idInsegnamento") int idInsegnamento, @RequestBody InsegnamentoDTO insegnamentoDTO) throws Exception {
		try { 
			
			Insegnamento insegnUpdate = insegnamentoService.updateById(idInsegnamento);
										
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
<<<<<<< HEAD
	
	@GetMapping(value="/getByIdCorso/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InsegnamentoDTO>> getByIdCorso(@PathVariable("id") int id) throws Exception {
		try {
			
			List<Insegnamento> insList = insegnamentoService.getByIdCorso(id);
			Iterator<Insegnamento> insIterator = insList.iterator();
			
			List<InsegnamentoDTO> listInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
			while(insIterator.hasNext())
			{
				Insegnamento ins = insIterator.next();
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
				
				
				listInsDTO.add(insDTO);
				
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
=======
>>>>>>> master

}
