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
import it.unisalento.se.saw.IService.IStudenteService;
import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.StudenteDTO;
import it.unisalento.se.saw.util.PasswordUtil;

@RestController
@RequestMapping("/stud")
public class StudenteRestController {
	
	@Autowired
	IStudenteService studenteService;
	
	@Autowired
	IUserService userService;
	
	
	
	public StudenteRestController() {
		super();
	}
	
	public StudenteRestController(IStudenteService studenteService) {		
		this.studenteService=studenteService;
	}
	
	
	
	
	//no test
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudenteDTO>> findAll() throws Exception {
		try {
			
			List<Studente> stuList = studenteService.findAll();
			Iterator<Studente> stuIterator = stuList.iterator();
			
			List<StudenteDTO> listStuDTO = new ArrayList<StudenteDTO>();
			
			while(stuIterator.hasNext())
			{
				Studente stu = stuIterator.next();
				StudenteDTO studDTO = new StudenteDTO();

				studDTO.setIdMatricola(stu.getUser().getIdMatricola());
				studDTO.setIdStudente(stu.getIdStudente());
				studDTO.setNome(stu.getUser().getNome());
				studDTO.setCognome(stu.getUser().getCognome());
				studDTO.setDataDiNascita(stu.getUser().getDataDiNascita());
				studDTO.setEmail(stu.getUser().getEmail());
				studDTO.setPassword(stu.getUser().getPassword());
				studDTO.setIndirizzo(stu.getUser().getIndirizzo());
				studDTO.setTelefono(stu.getUser().getTelefono());
				studDTO.setIdStudente(stu.getIdStudente());
				
				studDTO.setIdCorsoDiStudio(stu.getCorsoDiStudio().getIdCorsoDiStudio());
				studDTO.setAnnoIscrizione(stu.getAnnoIscrizione());
				studDTO.setNomeCorsoDiStudio(stu.getCorsoDiStudio().getNome());
				studDTO.setTipo(stu.getCorsoDiStudio().getTipo());
				listStuDTO.add(studDTO);
				
			}
			return new ResponseEntity<List<StudenteDTO>>(listStuDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<List<StudenteDTO>>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	@GetMapping(value="/getByMatricola/{matricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudenteDTO> getByMatricola(@PathVariable("matricola") int matricola) throws Exception {
		try {
			
			Studente stu = studenteService.getByMatricola(matricola);
			StudenteDTO studDTO = new StudenteDTO();

			studDTO.setIdMatricola(stu.getUser().getIdMatricola());
			studDTO.setIdStudente(stu.getIdStudente());
			studDTO.setNome(stu.getUser().getNome());
			studDTO.setCognome(stu.getUser().getCognome());
			studDTO.setDataDiNascita(stu.getUser().getDataDiNascita());
			studDTO.setEmail(stu.getUser().getEmail());
			studDTO.setPassword(stu.getUser().getPassword());
			studDTO.setIndirizzo(stu.getUser().getIndirizzo());
			studDTO.setTelefono(stu.getUser().getTelefono());
			studDTO.setIdStudente(stu.getIdStudente());

			
			studDTO.setIdCorsoDiStudio(stu.getCorsoDiStudio().getIdCorsoDiStudio());
			studDTO.setAnnoIscrizione(stu.getAnnoIscrizione());
			studDTO.setNomeCorsoDiStudio(stu.getCorsoDiStudio().getNome());
			studDTO.setTipo(stu.getCorsoDiStudio().getTipo());
				
			return new ResponseEntity<StudenteDTO>(studDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<StudenteDTO>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	
		
	
	

	
	
	//NUOVO CH 2.0
		@GetMapping(value="/getAllStudByIdCdS/{idCdS}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<StudenteDTO>> getAllStudByIdCdS(@PathVariable("idCdS") int idCdS) throws Exception {
			try {
				
				List<Studente> stuList = studenteService.getAllStudByIdCdS(idCdS);
				Iterator<Studente> stuIterator = stuList.iterator();
				
				List<StudenteDTO> listStuDTO = new ArrayList<StudenteDTO>();
				
				while(stuIterator.hasNext())
				{
					Studente stu = stuIterator.next();
					StudenteDTO studDTO = new StudenteDTO();

					studDTO.setIdMatricola(stu.getUser().getIdMatricola());
					studDTO.setIdStudente(stu.getIdStudente());
					studDTO.setNome(stu.getUser().getNome());
					studDTO.setCognome(stu.getUser().getCognome());
					studDTO.setDataDiNascita(stu.getUser().getDataDiNascita());
					studDTO.setEmail(stu.getUser().getEmail());
					studDTO.setPassword(stu.getUser().getPassword());
					studDTO.setIndirizzo(stu.getUser().getIndirizzo());
					studDTO.setTelefono(stu.getUser().getTelefono());
					
					studDTO.setIdCorsoDiStudio(stu.getCorsoDiStudio().getIdCorsoDiStudio());
					studDTO.setAnnoIscrizione(stu.getAnnoIscrizione());
					studDTO.setNomeCorsoDiStudio(stu.getCorsoDiStudio().getNome());
					studDTO.setTipo(stu.getCorsoDiStudio().getTipo());
					listStuDTO.add(studDTO);
					
				}
				return new ResponseEntity<List<StudenteDTO>>(listStuDTO, HttpStatus.OK);
				
			} catch (Exception e) {
			
				return new ResponseEntity<List<StudenteDTO>>(HttpStatus.BAD_REQUEST);
			
			}
		}
}
