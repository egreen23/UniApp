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

import it.unisalento.se.saw.IService.IDocenteService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.CorsoDiStudio;

import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.StudenteDTO;
import it.unisalento.se.saw.util.PasswordUtil;


@RestController
@RequestMapping("/doc")
public class DocenteRestController {
	
	@Autowired
	IDocenteService docenteService;
	
	
	
	public DocenteRestController() {
		super();	
	}
	
	public DocenteRestController(IDocenteService docenteService) {
		this.docenteService=docenteService;
	}
	
	
	
	
	@PostMapping(value="/logDocente/{idMatricola}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocenteDTO> logDocente(@PathVariable("idMatricola") int idMatricola, @PathVariable("password") String password) throws Exception {
		
		DocenteDTO docLogDTO = new DocenteDTO();
		
		Docente doc = docenteService.logDocente(idMatricola);	
		
		
		
		if(doc == null)
		{
			return new ResponseEntity<DocenteDTO>(docLogDTO, HttpStatus.UNAUTHORIZED);
			
		}
		else
		{	
			boolean result = PasswordUtil.check(password, doc.getUser().getPassword());
			if(result==false)
			{
				//SI OTTIENE TALE RESPONSE PER MAIL CORRETTA MA PASSWORD ERRATA
				return new ResponseEntity<DocenteDTO>(docLogDTO,HttpStatus.UNAUTHORIZED);
			}

			docLogDTO.setIdMatricola(doc.getUser().getIdMatricola());
			docLogDTO.setIdDocente(doc.getIdDocente());
			docLogDTO.setNome(doc.getUser().getNome());
			docLogDTO.setCognome(doc.getUser().getCognome());
			docLogDTO.setDataDiNascita(doc.getUser().getDataDiNascita());
			docLogDTO.setEmail(doc.getUser().getEmail());
			docLogDTO.setPassword(doc.getUser().getPassword());
			docLogDTO.setIndirizzo(doc.getUser().getIndirizzo());
			docLogDTO.setTelefono(doc.getUser().getTelefono());
						
			return new ResponseEntity<DocenteDTO>(docLogDTO, HttpStatus.OK);
			
		}

	}		
}
