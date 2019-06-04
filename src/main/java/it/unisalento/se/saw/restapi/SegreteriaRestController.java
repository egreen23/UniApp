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
			String segr = segLogDTO.getNome() + " " + segLogDTO.getCognome();
			segLogDTO.setSegretario(segr);
			
			return new ResponseEntity<SegreteriaDTO>(segLogDTO, HttpStatus.OK);
			
		}

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
	
	
	@GetMapping(value="/getByMatricola/{matricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SegreteriaDTO> getByMatricola(@PathVariable("matricola") int matricola) throws Exception {
		try {
			
			Segreteria seg = segreteriaService.getByMatricola(matricola);
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
			
			return new ResponseEntity<SegreteriaDTO>(segDTO, HttpStatus.OK);

		} catch (Exception e) {
	
		return new ResponseEntity<SegreteriaDTO>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	@PostMapping(value="/newSegreteria", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segreteria> save(@RequestBody SegreteriaDTO segreteriaDTO) throws Exception {
		try {
						
			Segreteria newSeg = new Segreteria();
			
			User newUser = new User();
			
			newUser.setNome(segreteriaDTO.getNome());
			newUser.setCognome(segreteriaDTO.getCognome());
			newUser.setDataDiNascita(segreteriaDTO.getDataDiNascita());
			newUser.setEmail(segreteriaDTO.getEmail());
			newUser.setPassword(PasswordUtil.getSaltedHash(segreteriaDTO.getPassword())); //GENERE PASSWORD CRIPTATA CON PasswordUtil
			newUser.setIndirizzo(segreteriaDTO.getIndirizzo());
			newUser.setTelefono(segreteriaDTO.getTelefono());
			
			userService.save(newUser);
			
			int matricola = userService.getMatricola(newUser.getEmail());			
			newUser.setIdMatricola(matricola);
			
			newSeg.setUser(newUser);
			
			segreteriaService.save(newSeg);
			
			return new ResponseEntity<Segreteria>(newSeg, HttpStatus.CREATED);
			
		} catch (Exception e) {
		
			return new ResponseEntity<Segreteria>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	
	@PostMapping(value="/updateSegByMatricola/{idMatricola}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segreteria> updateSegByMatricola(@PathVariable("idMatricola") int idMatricola, @RequestBody SegreteriaDTO segreteriaDTO) throws Exception {
		try {
			
			User userUpdate = userService.getById(idMatricola);
			Segreteria updateSeg = segreteriaService.updateSegByMatricola(idMatricola);
						
//			userUpdate.setIdMatricola(segreteriaDTO.getIdMatricola());
			userUpdate.setNome(segreteriaDTO.getNome());
			userUpdate.setCognome(segreteriaDTO.getCognome());
//			userUpdate.setEmail(studenteDTO.getEmail());  // L'EMAIL NON PUO' ESSERE CAMBIATA POICHE' UNIQUE 
			userUpdate.setPassword(PasswordUtil.getSaltedHash(segreteriaDTO.getPassword()));  //GENERE PASSWORD CRIPTATA CON PasswordUtil
			userUpdate.setDataDiNascita(segreteriaDTO.getDataDiNascita());
			userUpdate.setIndirizzo(segreteriaDTO.getIndirizzo());
			userUpdate.setTelefono(segreteriaDTO.getTelefono());
			
			userService.save(userUpdate);
			
			updateSeg.setUser(userUpdate);
			
			segreteriaService.save(updateSeg);
								
			return new ResponseEntity<Segreteria>(updateSeg, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<Segreteria>(HttpStatus.BAD_REQUEST);
		}
	}
}
