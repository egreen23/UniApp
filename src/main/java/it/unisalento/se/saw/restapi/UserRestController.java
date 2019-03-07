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
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
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
	
//	@RequestMapping(value="/findAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<JSONObject> findAll() {
//		
//		List<User> userList = userService.findAll();
//		Iterator<User> userIterator = userList.iterator();
//		
//		JSONArray arrayuser= new JSONArray();
//		while(userIterator.hasNext()) {
//			
//			User user = userIterator.next();
//			JSONObject userJson = new JSONObject();
//			
//			userJson.put("id", user.getIdMatricola());
//			userJson.put("nome", user.getNome());
//			userJson.put("cognome", user.getCognome());
//			userJson.put("email", user.getEmail());
//			userJson.put("data", user.getDataDiNascita());
//			userJson.put("indirizzo", user.getIndirizzo());
//			userJson.put("telefono", user.getTelefono());
//			
//			//insert single json in arrayJson
//			arrayuser.add(userJson);
//		}
//		
//		JSONObject sendJson = new JSONObject();
//		sendJson.put("users", arrayuser);
//		return new ResponseEntity<JSONObject>(sendJson, HttpStatus.OK);
//		
//	}
	
	
	@GetMapping(value="/getById/{idMatricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getById(@PathVariable("idMatricola") int idMatricola) {
		
		User user = userService.getById(idMatricola);
		UserDTO userDTO = new UserDTO();

		userDTO.setIdMatricola(user.getIdMatricola());
		userDTO.setNome(user.getNome());
		userDTO.setCognome(user.getCognome());
		userDTO.setEmail(user.getEmail());
		userDTO.setDataDiNascita(user.getDataDiNascita());
		userDTO.setIndirizzo(user.getIndirizzo());
		userDTO.setTelefono(user.getTelefono());
		
		return userDTO;
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
	
		
	
	
	@PostMapping(value="/newUser", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> save(@RequestBody UserDTO userDTO) throws Exception {
		try {
			
			User user = new User();
			
			user.setIdMatricola(userDTO.getIdMatricola());
			user.setNome(userDTO.getNome());
			user.setCognome(userDTO.getCognome());
			user.setEmail(userDTO.getEmail());
			user.setPassword(PasswordUtil.getSaltedHash(userDTO.getPassword())); //GENERE PASSWORD CRIPTATA CON PasswordUtil
			user.setDataDiNascita(userDTO.getDataDiNascita());
			user.setIndirizzo(userDTO.getIndirizzo());
			user.setTelefono(userDTO.getTelefono());
			
			userService.save(user);
			
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
			
		} catch (Exception e) {
						
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@PostMapping(value="/removeUserById/{idMatricola}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void removeUserById(@PathVariable("idMatricola") int idMatricola) {
		
		userService.removeUserById(idMatricola);		
	}
	
	
	
	@PostMapping(value="/updateUserById/{idMatricola}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public User updateUserById(@PathVariable("idMatricola") int idMatricola, @RequestBody UserDTO userDTO) throws Exception {
		
		User userUpdate = userService.updateUserById(idMatricola);
		
		userUpdate.setIdMatricola(userDTO.getIdMatricola());
		userUpdate.setNome(userDTO.getNome());
		userUpdate.setCognome(userDTO.getCognome());
		userUpdate.setEmail(userDTO.getEmail());
		userUpdate.setPassword(PasswordUtil.getSaltedHash(userDTO.getPassword()));  //GENERE PASSWORD CRIPTATA CON PasswordUtil
		userUpdate.setDataDiNascita(userDTO.getDataDiNascita());
		userUpdate.setIndirizzo(userDTO.getIndirizzo());
		userUpdate.setTelefono(userDTO.getTelefono());
		
		return userService.save(userUpdate);
	}

	
	
//	@RequestMapping(value="/findUser", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<UserDTO> findUser() {
//		
//		List<User> users = userService.findUser();
//		
// TEST STAMPA IN CONCOLE 		
//		int i;
//		for(i=0; i<users.size();i++)
//		{	
//			User u = users.get(i);
//			System.out.println("matricola i : " +u.getIdMatricola()+" nome : "+u.getNome()+" ");
//		}
//		i=0;		
//		List<UserDTO> ListUserDTO = new ArrayList< UserDTO>();
//
//		for (User user : users)
//		{
//			UserDTO userDTO = new UserDTO();
//
//			userDTO.setIdMatricola(user.getIdMatricola());
//			userDTO.setNome(user.getNome());
//			userDTO.setCognome(user.getCognome());
//			userDTO.setDataDiNascita(user.getDataDiNascita());
//			userDTO.setEmail(user.getEmail());
//			userDTO.setIndirizzo(user.getIndirizzo());
//			userDTO.setTelefono(user.getTelefono());
//			
//			ListUserDTO.add(userDTO);
//
//		}
//		return ListUserDTO;
//	}
	
	

	
}
