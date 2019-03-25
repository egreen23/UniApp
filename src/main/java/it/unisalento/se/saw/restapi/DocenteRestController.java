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

import it.unisalento.se.saw.IService.IDocenteService;
import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.SegreteriaDTO;
import it.unisalento.se.saw.util.PasswordUtil;

@RestController
@RequestMapping("/doc")
public class DocenteRestController {
	
	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	IUserService userService;
	
	
	
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
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DocenteDTO>> findAll() throws Exception {
		try {
			
			List<Docente> docList = docenteService.findAll();
			Iterator<Docente> docIterator = docList.iterator();
			
			List<DocenteDTO> listDocDTO = new ArrayList<DocenteDTO>();
			
			while(docIterator.hasNext())
			{
				Docente doc = docIterator.next();
				DocenteDTO docDTO = new DocenteDTO();

				docDTO.setIdMatricola(doc.getUser().getIdMatricola());
				docDTO.setIdDocente(doc.getIdDocente());
				docDTO.setNome(doc.getUser().getNome());
				docDTO.setCognome(doc.getUser().getCognome());
				docDTO.setDataDiNascita(doc.getUser().getDataDiNascita());
				docDTO.setEmail(doc.getUser().getEmail());
				docDTO.setPassword(doc.getUser().getPassword());
				docDTO.setIndirizzo(doc.getUser().getIndirizzo());
				docDTO.setTelefono(doc.getUser().getTelefono());
				
				
				listDocDTO.add(docDTO);
				
			}
			return new ResponseEntity<List<DocenteDTO>>(listDocDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<List<DocenteDTO>>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	
	@GetMapping(value="/getByMatricola/{matricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocenteDTO> getByMatricola(@PathVariable("matricola") int matricola) throws Exception {
		try {
			
			Docente doc = docenteService.getByMatricola(matricola);
			DocenteDTO docDTO = new DocenteDTO();

			docDTO.setIdMatricola(doc.getUser().getIdMatricola());
			docDTO.setIdDocente(doc.getIdDocente());
			docDTO.setNome(doc.getUser().getNome());
			docDTO.setCognome(doc.getUser().getCognome());
			docDTO.setDataDiNascita(doc.getUser().getDataDiNascita());
			docDTO.setEmail(doc.getUser().getEmail());
			docDTO.setPassword(doc.getUser().getPassword());
			docDTO.setIndirizzo(doc.getUser().getIndirizzo());
			docDTO.setTelefono(doc.getUser().getTelefono());
					
			return new ResponseEntity<DocenteDTO>(docDTO, HttpStatus.OK);

		} catch (Exception e) {
	
		return new ResponseEntity<DocenteDTO>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	@PostMapping(value="/newDocente", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Docente> save(@RequestBody DocenteDTO studenteDTO) throws Exception {
		try {
						
			Docente doc = new Docente();
			User user = new User();

			user.setNome(studenteDTO.getNome());
			user.setCognome(studenteDTO.getCognome());
			user.setDataDiNascita(studenteDTO.getDataDiNascita());
			user.setEmail(studenteDTO.getEmail());
			user.setPassword(PasswordUtil.getSaltedHash(studenteDTO.getPassword())); //GENERE PASSWORD CRIPTATA CON PasswordUtil
			user.setIndirizzo(studenteDTO.getIndirizzo());
			user.setTelefono(studenteDTO.getTelefono());
			
			userService.save(user);
			
			int matricola = userService.getMatricola(user.getEmail());			
			user.setIdMatricola(matricola);
			
			doc.setUser(user);

			docenteService.save(doc);
			
			return new ResponseEntity<Docente>(doc, HttpStatus.CREATED);
			
		} catch (Exception e) {
		
			return new ResponseEntity<Docente>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	
	@PostMapping(value="/updateDocByMatricola/{idMatricola}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Docente> updateDocByMatricola(@PathVariable("idMatricola") int idMatricola, @RequestBody SegreteriaDTO segreteriaDTO) throws Exception {
		try {
			
			User userUpdate = userService.getById(idMatricola);
			Docente updateDoc = docenteService.updateDocByMatricola(idMatricola);
						
			userUpdate.setIdMatricola(segreteriaDTO.getIdMatricola());
			userUpdate.setNome(segreteriaDTO.getNome());
			userUpdate.setCognome(segreteriaDTO.getCognome());
//			userUpdate.setEmail(studenteDTO.getEmail());  // L'EMAIL NON PUO' ESSERE CAMBIATA POICHE' UNIQUE 
			userUpdate.setPassword(PasswordUtil.getSaltedHash(segreteriaDTO.getPassword()));  //GENERE PASSWORD CRIPTATA CON PasswordUtil
			userUpdate.setDataDiNascita(segreteriaDTO.getDataDiNascita());
			userUpdate.setIndirizzo(segreteriaDTO.getIndirizzo());
			userUpdate.setTelefono(segreteriaDTO.getTelefono());
			
			userService.save(userUpdate);
			
			updateDoc.setUser(userUpdate);
			
			docenteService.save(updateDoc);
								
			return new ResponseEntity<Docente>(updateDoc, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<Docente>(HttpStatus.BAD_REQUEST);
		}
	}
	

}