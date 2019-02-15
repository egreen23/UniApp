package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IInsegnamentoService;
import it.unisalento.se.saw.domain.Attrezzatura;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.AttrezzaturaDTO;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.InsegnamentoDTO;
import it.unisalento.se.saw.dto.UserDTO;

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
	public List<InsegnamentoDTO> findAll() {
		
		List<Insegnamento> insegnamentos = insegnamentoService.findAll();
		List<InsegnamentoDTO> ListInsDTO = new ArrayList<InsegnamentoDTO>();
				
		
		for (Insegnamento ins : insegnamentos)
		{
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
		return ListInsDTO;
		
	}
	
	@GetMapping(value="/getByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<InsegnamentoDTO> getByName(@PathVariable("string") String string) {
		
		List<Insegnamento> insegnamentos = insegnamentoService.findByName(string);
		List<InsegnamentoDTO> ListInsDTO = new ArrayList<InsegnamentoDTO>();
				
		
		for (Insegnamento ins : insegnamentos)
		{
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
		return ListInsDTO;
		
	}
	
	

}
