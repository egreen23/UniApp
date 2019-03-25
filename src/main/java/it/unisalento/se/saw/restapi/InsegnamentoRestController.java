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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IInsegnamentoService;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.dto.InsegnamentoDTO;

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
			
			List<InsegnamentoDTO> ListInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
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
				
				ListInsDTO.add(insDTO);
				
			}
			return new ResponseEntity<List<InsegnamentoDTO>>(ListInsDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<List<InsegnamentoDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InsegnamentoDTO>> getByName(@PathVariable("string") String string) throws Exception {
		try {
			
			List<Insegnamento> insList = insegnamentoService.findByName(string);
			Iterator<Insegnamento> insIterator = insList.iterator();
			
			List<InsegnamentoDTO> ListInsDTO = new ArrayList<InsegnamentoDTO>();
					
			
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
				
				
				ListInsDTO.add(insDTO);
				
			}
			return new ResponseEntity<List<InsegnamentoDTO>>(ListInsDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<InsegnamentoDTO>>(HttpStatus.BAD_REQUEST);
		}

	}
	
	

}
