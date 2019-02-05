package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IDocenteService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.StudenteDTO;

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
	
	
	
	@GetMapping(value="/isDocente/{idMatricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DocenteDTO> isDocente(@PathVariable("idMatricola") int idMatricola){
		
		List<Docente> list = docenteService.isDocente(idMatricola);
		List<DocenteDTO> ListDocenteDTO = new ArrayList<DocenteDTO>();

		for (Docente docente : list)
		{
			DocenteDTO docenteDTO = new DocenteDTO();

			docenteDTO.setIdDocente(docente.getIdDocente());
			docenteDTO.setIdMatricola(docente.getUser().getIdMatricola());
			docenteDTO.setNome(docente.getUser().getNome());
		    docenteDTO.setCognome(docente.getUser().getCognome());
			docenteDTO.setDataDiNascita(docente.getUser().getDataDiNascita());
			docenteDTO.setEmail(docente.getUser().getEmail());
			docenteDTO.setIndirizzo(docente.getUser().getIndirizzo());
			docenteDTO.setTelefono(docente.getUser().getTelefono());
			
			ListDocenteDTO.add(docenteDTO);
			
		}
		return ListDocenteDTO;
	}
			
}
