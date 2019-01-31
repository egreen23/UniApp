package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import it.unisalento.se.saw.IService.IStudenteService;
import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.StudenteDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.services.UserService;

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
	
	
	
	@GetMapping(value="/isStudente/{idMatricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StudenteDTO> isStudente(@PathVariable("idMatricola") int idMatricola){
		
		List<Studente> list = studenteService.isStudente(idMatricola);
		List<StudenteDTO> ListStudenteDTO = new ArrayList<StudenteDTO>();

		for (Studente studente : list)
		{
			StudenteDTO studenteDTO = new StudenteDTO();

			studenteDTO.setIdStudente(studente.getIdStudente());
			studenteDTO.setIdMatricola(studente.getUser().getIdMatricola());
			studenteDTO.setNome(studente.getUser().getNome());
		    studenteDTO.setCognome(studente.getUser().getCognome());
			studenteDTO.setDataDiNascita(studente.getUser().getDataDiNascita());
			studenteDTO.setEmail(studente.getUser().getEmail());
			studenteDTO.setIndirizzo(studente.getUser().getIndirizzo());
			studenteDTO.setTelefono(studente.getUser().getTelefono());
			studenteDTO.setAnnoIscrizione(studente.getAnnoIscrizione());
			
			ListStudenteDTO.add(studenteDTO);

		}
		return ListStudenteDTO;
	}

}
