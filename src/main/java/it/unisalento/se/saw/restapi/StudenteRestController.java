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
	
	
	
	@PostMapping(value="/logStudente/{idMatricola}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudenteDTO> logStudente(@PathVariable("idMatricola") int idMatricola, @PathVariable("password") String password) throws Exception {
		
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
			
			studDTO.setIdCorsoDiStudio(stu.getCorsoDiStudio().getIdCorsoDiStudio());
			studDTO.setAnnoIscrizione(stu.getAnnoIscrizione());
			studDTO.setNomeCorsoDiStudio(stu.getCorsoDiStudio().getNome());
			studDTO.setTipo(stu.getCorsoDiStudio().getTipo());
				
			return new ResponseEntity<StudenteDTO>(studDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<StudenteDTO>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	
		
	@PostMapping(value="/newStudente", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Studente> save(@RequestBody StudenteDTO studenteDTO) throws Exception {
		try {
						
			Studente newStu = new Studente();

			User newUser = new User();
			CorsoDiStudio newCorsoStudio= new CorsoDiStudio();

			newUser.setNome(studenteDTO.getNome());
			newUser.setCognome(studenteDTO.getCognome());
			newUser.setDataDiNascita(studenteDTO.getDataDiNascita());
			newUser.setEmail(studenteDTO.getEmail());
			newUser.setPassword(PasswordUtil.getSaltedHash(studenteDTO.getPassword())); //GENERE PASSWORD CRIPTATA CON PasswordUtil
			newUser.setIndirizzo(studenteDTO.getIndirizzo());
			newUser.setTelefono(studenteDTO.getTelefono());
			
			userService.save(newUser);
			
			int matricola = userService.getMatricola(newUser.getEmail());			
			newUser.setIdMatricola(matricola);
			
			newStu.setUser(newUser);
						
			newCorsoStudio.setIdCorsoDiStudio(studenteDTO.getIdCorsoDiStudio());
			newStu.setAnnoIscrizione(studenteDTO.getAnnoIscrizione());
			newStu.setCorsoDiStudio(newCorsoStudio);
						
			studenteService.save(newStu);
			
			return new ResponseEntity<Studente>(newStu, HttpStatus.CREATED);
			
		} catch (Exception e) {
		
			return new ResponseEntity<Studente>(HttpStatus.BAD_REQUEST);
		
		}
	}
	

	@PostMapping(value="/updateStudByMatricola/{idMatricola}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Studente> updateStudByMatricola(@PathVariable("idMatricola") int idMatricola, @RequestBody StudenteDTO studenteDTO) throws Exception {
		try {
			
			User userUpdate = userService.getById(idMatricola);
			Studente updateStud = studenteService.updateStudByMatricola(idMatricola);
			
			CorsoDiStudio corsoStudio = new CorsoDiStudio();
			
//			userUpdate.setIdMatricola(studenteDTO.getIdMatricola());
			userUpdate.setNome(studenteDTO.getNome());
			userUpdate.setCognome(studenteDTO.getCognome());
//			userUpdate.setEmail(studenteDTO.getEmail());  // L'EMAIL NON PUO' ESSERE CAMBIATA POICHE' UNIQUE 
			userUpdate.setPassword(PasswordUtil.getSaltedHash(studenteDTO.getPassword()));  //GENERE PASSWORD CRIPTATA CON PasswordUtil
			userUpdate.setDataDiNascita(studenteDTO.getDataDiNascita());
			userUpdate.setIndirizzo(studenteDTO.getIndirizzo());
			userUpdate.setTelefono(studenteDTO.getTelefono());
			
			userService.save(userUpdate);
			
			updateStud.setUser(userUpdate);
						
			corsoStudio.setIdCorsoDiStudio(studenteDTO.getIdCorsoDiStudio());
			updateStud.setCorsoDiStudio(corsoStudio);
			updateStud.setAnnoIscrizione(studenteDTO.getAnnoIscrizione());
			
			studenteService.save(updateStud);
			
			return new ResponseEntity<Studente>(updateStud, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<Studente>(HttpStatus.BAD_REQUEST);
		}
	}
}
