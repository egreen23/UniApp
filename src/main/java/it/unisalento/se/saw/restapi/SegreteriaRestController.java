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

import it.unisalento.se.saw.IService.ISegreteriaService;
import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.SegreteriaDTO;
import it.unisalento.se.saw.util.PasswordUtil;

@RestController
@RequestMapping("/seg")
public class SegreteriaRestController {
	
	@Autowired
	ISegreteriaService segreteriaService;
	
	@Autowired
	IUserService userService;
	
	
	
	public SegreteriaRestController() {
		super();	
	}
	
	public SegreteriaRestController(ISegreteriaService segreteriaService) {
		this.segreteriaService=segreteriaService;
	}
	
	
	
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SegreteriaDTO>> findAll() throws Exception {
		try {
			
			List<Segreteria> segList = segreteriaService.findAll();
			Iterator<Segreteria> segIterator = segList.iterator();
			
			List<SegreteriaDTO> listSegDTO = new ArrayList<SegreteriaDTO>();
			
			while(segIterator.hasNext())
			{
				Segreteria seg = segIterator.next();
				SegreteriaDTO segDTO = new SegreteriaDTO();

				segDTO.setIdMatricola(seg.getUser().getIdMatricola());
				segDTO.setIdSegreteria(seg.getIdSegreteria());
				segDTO.setNome(seg.getUser().getNome());
				segDTO.setCognome(seg.getUser().getCognome());
				segDTO.setDataDiNascita(seg.getUser().getDataDiNascita());
				segDTO.setEmail(seg.getUser().getEmail());
				segDTO.setPassword(seg.getUser().getPassword());
				segDTO.setIndirizzo(seg.getUser().getIndirizzo());
				segDTO.setTelefono(seg.getUser().getTelefono());
				String segr = segDTO.getNome() + " " + segDTO.getCognome();
				segDTO.setSegretario(segr);
				
				
				listSegDTO.add(segDTO);
				
			}
			return new ResponseEntity<List<SegreteriaDTO>>(listSegDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<List<SegreteriaDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
