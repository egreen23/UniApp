package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.IService.IStudenteService;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.StudenteDTO;
import it.unisalento.se.saw.util.PasswordUtil;

@RestController
@RequestMapping("/stud")
public class StudenteRestController {
	
	@Autowired
	IStudenteService studenteService;
	
	
	
	public StudenteRestController() {
		super();
	}
	
	public StudenteRestController(IStudenteService studenteService) {		
		this.studenteService=studenteService;
	}
	
	
	
	@PostMapping(value="/studentLog/{idMatricola}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudenteDTO> logStudent(@PathVariable("idMatricola") int idMatricola, @PathVariable("password") String password) throws Exception {
		
		StudenteDTO studLogDTO = new StudenteDTO();
		
		Studente stu = studenteService.logStudent(idMatricola);	
		
		
		
		if(stu == null)
		{
			return new ResponseEntity<StudenteDTO>(studLogDTO, HttpStatus.UNAUTHORIZED);
			
		}
		else
		{	
			boolean result = PasswordUtil.check(password, stu.getUser().getPassword());
			if(result==false)
			{
				//SI OTTIENE TALE RESPONSE PER MAIL CORRETTA MA PASSWORD ERRATA
				return new ResponseEntity<StudenteDTO>(studLogDTO,HttpStatus.UNAUTHORIZED);
			}

			studLogDTO.setIdMatricola(stu.getUser().getIdMatricola());
			studLogDTO.setIdStudente(stu.getIdStudente());
			studLogDTO.setNome(stu.getUser().getNome());
			studLogDTO.setCognome(stu.getUser().getCognome());
			studLogDTO.setDataDiNascita(stu.getUser().getDataDiNascita());
			studLogDTO.setEmail(stu.getUser().getEmail());
			studLogDTO.setPassword(stu.getUser().getPassword());
			studLogDTO.setIndirizzo(stu.getUser().getIndirizzo());
			studLogDTO.setTelefono(stu.getUser().getTelefono());
			
			studLogDTO.setIdCorsoDiStudio(stu.getCorsoDiStudio().getIdCorsoDiStudio());
			studLogDTO.setAnnoIscrizione(stu.getAnnoIscrizione());
			studLogDTO.setNomeCorsoDiStudio(stu.getCorsoDiStudio().getNome());
			studLogDTO.setTipo(stu.getCorsoDiStudio().getTipo());
			
			return new ResponseEntity<StudenteDTO>(studLogDTO, HttpStatus.OK);
			
		}

	}

}
