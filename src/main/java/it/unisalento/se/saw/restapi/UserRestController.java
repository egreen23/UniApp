package it.unisalento.se.saw.restapi;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import it.unisalento.se.saw.IService.IStudenteService;
import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.StudenteDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.repositories.StudenteRepository;
import it.unisalento.se.saw.services.StudentService;
import it.unisalento.se.saw.services.UserService;
import it.unisalento.se.saw.util.PasswordUtil;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@RestController
@RequestMapping("/user")
public class UserRestController {
	
	
	
	@Autowired
	IUserService userService;
	
	
	
	public UserRestController() {
		super();
	}
	
	public UserRestController(IUserService userService) {
		this.userService=userService;
		
	}
	
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> findAll() throws Exception {
		try {
	
			List<User> userList = userService.findAll();
			Iterator<User> userIterator = userList.iterator();
			
			List<UserDTO> listUserDTO = new ArrayList<UserDTO>();			

			while(userIterator.hasNext())
			{
				User user = userIterator.next();
				UserDTO userDTO = new UserDTO();

				userDTO.setIdMatricola(user.getIdMatricola());
				userDTO.setNome(user.getNome());
				userDTO.setCognome(user.getCognome());
				userDTO.setPassword(user.getPassword());
				userDTO.setEmail(user.getEmail());
				userDTO.setDataDiNascita(user.getDataDiNascita());
				userDTO.setIndirizzo(user.getIndirizzo());
				userDTO.setTelefono(user.getTelefono());
				
				listUserDTO.add(userDTO);

			}
			return new ResponseEntity<List<UserDTO>>(listUserDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<UserDTO>>(HttpStatus.BAD_REQUEST);
		}
		
	}	
	

	
	
	@GetMapping(value="/getById/{idMatricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getById(@PathVariable("idMatricola") int idMatricola) {
		
		try {
			User user = userService.getById(idMatricola);
			UserDTO userDTO = new UserDTO();

			userDTO.setIdMatricola(user.getIdMatricola());
			userDTO.setNome(user.getNome());
			userDTO.setCognome(user.getCognome());
			userDTO.setEmail(user.getEmail());
			userDTO.setDataDiNascita(user.getDataDiNascita());
			userDTO.setIndirizzo(user.getIndirizzo());
			userDTO.setTelefono(user.getTelefono());
			
			Studente stud = userService.isStudente(idMatricola);
			Docente doc = userService.isDocente(idMatricola);
			Segreteria seg = userService.isSegreteria(idMatricola);
			
			if (stud != null) {
				userDTO.setIdStudente(stud.getIdStudente());
				userDTO.setTipo("studente");
			}
			
			if (doc != null) {
				userDTO.setIdDocente(doc.getIdDocente());
				userDTO.setTipo("docente");
			}
			
			if (seg != null) {
				userDTO.setIdSegreteria(seg.getIdSegreteria());
				userDTO.setTipo("segreteria");
			}
			
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		
		} catch (Exception e) {
			
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	@PostMapping(value="/login/{idMatricola}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> login(@PathVariable("idMatricola") int idMatricola, @PathVariable("password") String password) throws Exception {
		
//		DocenteDTO docLogDTO = new DocenteDTO();
//		
//		Docente doc = docenteService.logDocente(idMatricola);	
		
		User u = userService.getById(idMatricola);
		
		UserDTO user = new UserDTO();
		
		if(u == null)
		{
			return new ResponseEntity<UserDTO>(user, HttpStatus.UNAUTHORIZED);
			
		}
		else
		{	
			boolean result = PasswordUtil.check(password, u.getPassword());
			if(result==false)
			{
				//SI OTTIENE TALE RESPONSE PER MAIL CORRETTA MA PASSWORD ERRATA
				return new ResponseEntity<UserDTO>(user,HttpStatus.UNAUTHORIZED);
			}
			
			user.setIdMatricola(u.getIdMatricola());
			user.setNome(u.getNome());
			user.setCognome(u.getCognome());
			user.setDataDiNascita(u.getDataDiNascita());
			user.setEmail(u.getEmail());
			user.setPassword(u.getPassword());
			user.setIndirizzo(u.getIndirizzo());
			user.setTelefono(u.getTelefono());
			
			Studente stud = userService.isStudente(idMatricola);
			Docente doc = userService.isDocente(idMatricola);
			Segreteria seg = userService.isSegreteria(idMatricola);
			
			if (stud != null) {
				user.setIdStudente(stud.getIdStudente());
				user.setTipo("studente");
			}
			
			if (doc != null) {
				user.setIdDocente(doc.getIdDocente());
				user.setTipo("docente");
			}
			
			if (seg != null) {
				user.setIdSegreteria(seg.getIdSegreteria());
				user.setTipo("segreteria");
			}

		
						
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
			
		}

	}
	
	
//	
//	@GetMapping(value="/getById/{idMatricola}", produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<JSONObject> getById(@PathVariable("idMatricola") int idMatricola) throws Exception{
//		try {
//			
//			User user = userService.getById(idMatricola);
//			JSONObject sendJson = new JSONObject();
//			
//			sendJson.put("id", user.getIdMatricola());
//			sendJson.put("nome", user.getNome());
//			sendJson.put("cognome", user.getCognome());
//			sendJson.put("email", user.getEmail());
//			sendJson.put("data", user.getDataDiNascita());
//			sendJson.put("indirizzo", user.getIndirizzo());
//			sendJson.put("telefono", user.getTelefono());
//			
//			
//			return new ResponseEntity<JSONObject>(sendJson, HttpStatus.OK);
//			
//			
//		} catch (Exception e) {
//			
//			JSONObject errorJson = new JSONObject();
//			errorJson.put("message", "ID not found");
//			return new ResponseEntity<JSONObject>(errorJson, HttpStatus.BAD_REQUEST);
//		}
//		
//	}
	
		
	
	
	
	
	
	
	
	

	
}
