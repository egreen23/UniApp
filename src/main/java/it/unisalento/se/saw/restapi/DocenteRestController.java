package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Date;
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
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CalendarioDTO;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.SegreteriaDTO;
import it.unisalento.se.saw.strategy.DateSortStrategy;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;
import it.unisalento.se.saw.strategy.StringSortStrategy;
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
	
	

	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DocenteDTO>> findAll() throws Exception {
		try {
			
			List<Docente> docList = docenteService.findAll();
			Iterator<Docente> docIterator = docList.iterator();
			List<String> profarray = new ArrayList<String>();
			List<DocenteDTO> listDocDTO = new ArrayList<DocenteDTO>();
			
			while(docIterator.hasNext())
			{
				Docente doc = docIterator.next();

				
				String prof = doc.getUser().getCognome() + " " + doc.getUser().getNome();
				profarray.add(prof);
				
				
				
			}
			
			SortStrategy<String> stringsort = new StringSortStrategy();
			SortContext stringorderer = new SortContext<String>(stringsort);
			stringorderer.setList(profarray);
			stringorderer.sort();
			
			for(String s : profarray) {
				
				docIterator = docList.iterator();
				
				while(docIterator.hasNext()) {
					
					Docente doc = docIterator.next();
					
					String prof2 = doc.getUser().getCognome() + " " + doc.getUser().getNome();
					
					if (prof2.equals(s)) {
						
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
						String prof = docDTO.getCognome() + " " + docDTO.getNome();
						docDTO.setProfessore(prof);
						
						
						listDocDTO.add(docDTO);
						
						docList.remove(doc);
						
						break;
					}
				}
			}
			
			return new ResponseEntity<List<DocenteDTO>>(listDocDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<List<DocenteDTO>>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	
	@GetMapping(value="/getByMatricola/{matricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DocenteDTO> getByMatricola(@PathVariable("matricola") int matricola) throws Exception {
		try {
			
			Docente doc = docenteService.logDocente(matricola);
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
						
			Docente newDoc = new Docente();
			User newUser = new User();

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
			
			newDoc.setUser(newUser);

			docenteService.save(newDoc);
			
			return new ResponseEntity<Docente>(newDoc, HttpStatus.CREATED);
			
		} catch (Exception e) {
		
			return new ResponseEntity<Docente>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	
	@PostMapping(value="/updateDocByMatricola/{idMatricola}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Docente> updateDocByMatricola(@PathVariable("idMatricola") int idMatricola, @RequestBody DocenteDTO docenteDTO) throws Exception {
		try {
			
			User userUpdate = userService.getById(idMatricola);
			Docente updateDoc = docenteService.logDocente(idMatricola);
						
//			userUpdate.setIdMatricola(segreteriaDTO.getIdMatricola());
			userUpdate.setNome(docenteDTO.getNome());
			userUpdate.setCognome(docenteDTO.getCognome());
//			userUpdate.setEmail(studenteDTO.getEmail());  // L'EMAIL NON PUO' ESSERE CAMBIATA POICHE' UNIQUE 
			userUpdate.setPassword(PasswordUtil.getSaltedHash(docenteDTO.getPassword()));  //GENERE PASSWORD CRIPTATA CON PasswordUtil
			userUpdate.setDataDiNascita(docenteDTO.getDataDiNascita());
			userUpdate.setIndirizzo(docenteDTO.getIndirizzo());
			userUpdate.setTelefono(docenteDTO.getTelefono());
			
			userService.save(userUpdate);
			
			updateDoc.setUser(userUpdate);
			
			docenteService.save(updateDoc);
								
			return new ResponseEntity<Docente>(updateDoc, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<Docente>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/searchDoc/{term}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DocenteDTO>> searchDoc(@PathVariable("term") String term) throws Exception {

		
		try {
			
			List<Docente> docList = docenteService.findAll();
			Iterator<Docente> docIterator = docList.iterator();
			
			List<DocenteDTO> listDocDTO = new ArrayList<DocenteDTO>();
			
			while(docIterator.hasNext())
			{
				
				Docente doc = docIterator.next();
				DocenteDTO docDTO = new DocenteDTO();
				String prof1 = doc.getUser().getNome() + " " + doc.getUser().getCognome();

				// int matricola = Integer.parseInt(term);
				
				if (doc.getUser().getNome().equalsIgnoreCase(term) || doc.getUser().getCognome().equalsIgnoreCase(term)
						|| prof1.equalsIgnoreCase(term)) {
					
					docDTO.setIdMatricola(doc.getUser().getIdMatricola());
					docDTO.setIdDocente(doc.getIdDocente());
					docDTO.setNome(doc.getUser().getNome());
					docDTO.setCognome(doc.getUser().getCognome());
					docDTO.setDataDiNascita(doc.getUser().getDataDiNascita());
					docDTO.setEmail(doc.getUser().getEmail());
					docDTO.setPassword(doc.getUser().getPassword());
					docDTO.setIndirizzo(doc.getUser().getIndirizzo());
					docDTO.setTelefono(doc.getUser().getTelefono());
					String prof = docDTO.getNome() + " " + docDTO.getCognome();
					docDTO.setProfessore(prof);
					
					
					listDocDTO.add(docDTO);
				}

				
				
			}
			return new ResponseEntity<List<DocenteDTO>>(listDocDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<List<DocenteDTO>>(HttpStatus.BAD_REQUEST);
		
		}
	}
	


}