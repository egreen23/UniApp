package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.ISegreteriaService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.SegreteriaDTO;

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
	
	
	
	@GetMapping(value="/isSegreteria/{idMatricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SegreteriaDTO> isSegreteria(@PathVariable("idMatricola") int idMatricola){
		
		List<Segreteria> list = segreteriaService.isSegreteria(idMatricola);
		List<SegreteriaDTO> ListSegreteriaDTO = new ArrayList<SegreteriaDTO>();

		for (Segreteria segreteria : list)
		{
			SegreteriaDTO segreteriaDTO = new SegreteriaDTO();

			segreteriaDTO.setIdSegreteria(segreteria.getIdSegreteria());
			segreteriaDTO.setIdMatricola(segreteria.getUser().getIdMatricola());
			segreteriaDTO.setNome(segreteria.getUser().getNome());
		    segreteriaDTO.setCognome(segreteria.getUser().getCognome());
			segreteriaDTO.setDataDiNascita(segreteria.getUser().getDataDiNascita());
			segreteriaDTO.setEmail(segreteria.getUser().getEmail());
			segreteriaDTO.setIndirizzo(segreteria.getUser().getIndirizzo());
			segreteriaDTO.setTelefono(segreteria.getUser().getTelefono());
			
			ListSegreteriaDTO.add(segreteriaDTO);
			
		}
		return ListSegreteriaDTO;
	}

}
