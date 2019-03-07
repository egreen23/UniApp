package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.ISegreteriaService;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.dto.SegreteriaDTO;
import it.unisalento.se.saw.util.PasswordUtil;

@RestController
@RequestMapping("/seg")
public class SegreteriaRestController {
	
	@Autowired
	ISegreteriaService segreteriaService;
	
	
	
	public SegreteriaRestController() {
		super();	
	}
	
	public SegreteriaRestController(ISegreteriaService segreteriaService) {
		this.segreteriaService=segreteriaService;
	}
	
	
	
	@PostMapping(value="/logSegreteria/{idMatricola}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SegreteriaDTO> logSegreteria(@PathVariable("idMatricola") int idMatricola, @PathVariable("password") String password) throws Exception {
		
		SegreteriaDTO segLogDTO = new SegreteriaDTO();
		
		Segreteria seg = segreteriaService.logSegreteria(idMatricola);	
		
	
		
		if(seg == null)
		{
			return new ResponseEntity<SegreteriaDTO>(segLogDTO, HttpStatus.UNAUTHORIZED);
			
		}
		else
		{	
			boolean result = PasswordUtil.check(password, seg.getUser().getPassword());
			if(result==false)
			{
				//SI OTTIENE TALE RESPONSE PER MAIL CORRETTA MA PASSWORD ERRATA
				return new ResponseEntity<SegreteriaDTO>(segLogDTO,HttpStatus.UNAUTHORIZED);
			}

			segLogDTO.setIdMatricola(seg.getUser().getIdMatricola());
			segLogDTO.setIdSegreteria(seg.getIdSegreteria());
			segLogDTO.setNome(seg.getUser().getNome());
			segLogDTO.setCognome(seg.getUser().getCognome());
			segLogDTO.setDataDiNascita(seg.getUser().getDataDiNascita());
			segLogDTO.setEmail(seg.getUser().getEmail());
			segLogDTO.setIndirizzo(seg.getUser().getIndirizzo());
			segLogDTO.setTelefono(seg.getUser().getTelefono());
			
			return new ResponseEntity<SegreteriaDTO>(segLogDTO, HttpStatus.OK);
			
		}

	}

}
