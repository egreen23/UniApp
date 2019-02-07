package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
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
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.services.UserService;

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
	public List<UserDTO> findAll() {
		
		List<User> users = userService.findAll();
		List<UserDTO> ListUserDTO = new ArrayList<UserDTO>();
		
		for (User user : users)
		{
			UserDTO userDTO = new UserDTO();

			userDTO.setIdMatricola(user.getIdMatricola());
			userDTO.setNome(user.getNome());
			userDTO.setCognome(user.getCognome());
			userDTO.setEmail(user.getEmail());
			userDTO.setDataDiNascita(user.getDataDiNascita());
			userDTO.setIndirizzo(user.getIndirizzo());
			userDTO.setTelefono(user.getTelefono());
			
			ListUserDTO.add(userDTO);

		}
		return ListUserDTO;
		
	}
	
	
	
	@GetMapping(value="/getById/{idMatricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getById(@PathVariable("idMatricola") int idMatricola) throws UserNotFoundException {
		
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
	
	
	
	@GetMapping(value="/isValidate/{idMatricola}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> isValidate(@PathVariable("idMatricola") int matricola, @PathVariable("password") String password){
		
		List<User> list = userService.isValidate(matricola, password);
		List<UserDTO> ListUserDTO = new ArrayList<UserDTO>();

		for (User user : list)
		{
			UserDTO userDTO = new UserDTO();

			userDTO.setIdMatricola(user.getIdMatricola());
			userDTO.setNome(user.getNome());
			userDTO.setCognome(user.getCognome());
			userDTO.setEmail(user.getEmail());
			userDTO.setDataDiNascita(user.getDataDiNascita());
			userDTO.setIndirizzo(user.getIndirizzo());
			userDTO.setTelefono(user.getTelefono());
			
			ListUserDTO.add(userDTO);

		}
		if (ListUserDTO.size() != 0) {
			return ListUserDTO;
		}

		else {
			return null;
		}
	}
	
	
	
	@PostMapping(value="/newUser", consumes=MediaType.APPLICATION_JSON_VALUE)
	public User save(@RequestBody UserDTO userDTO) {
		
		User user = new User();
		
		user.setIdMatricola(userDTO.getIdMatricola());
		user.setNome(userDTO.getNome());
		user.setCognome(userDTO.getCognome());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setDataDiNascita(userDTO.getDataDiNascita());
		user.setIndirizzo(userDTO.getIndirizzo());
		user.setTelefono(userDTO.getTelefono());
		
		return userService.save(user);
	}
	
	
	@PostMapping(value="/removeUserById/{idMatricola}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void removeUserById(@PathVariable("idMatricola") int idMatricola) throws UserNotFoundException{
		
		userService.removeUserById(idMatricola);		
	}
	
	
	
	@PostMapping(value="/updateUserById/{idMatricola}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public User updateUserById(@PathVariable("idMatricola") int idMatricola, @RequestBody UserDTO userDTO) {
		
		User userUpdate = userService.updateUserById(idMatricola);
			
		
		userUpdate.setIdMatricola(userDTO.getIdMatricola());
		userUpdate.setNome(userDTO.getNome());
		userUpdate.setCognome(userDTO.getCognome());
		userUpdate.setEmail(userDTO.getEmail());
		userUpdate.setPassword(userDTO.getPassword());
		userUpdate.setDataDiNascita(userDTO.getDataDiNascita());
		userUpdate.setIndirizzo(userDTO.getIndirizzo());
		userUpdate.setTelefono(userDTO.getTelefono());
		
		return userService.save(userUpdate);
	}

		
		
//		FUNIONA
		
//		User user = new User();
//				
//		user.setIdMatricola(userUpdate.getIdMatricola());
//		user.setNome(userUpdate.getNome());
//		user.setCognome(userUpdate.getCognome());
//		user.setEmail(userUpdate.getEmail());
//		user.setDataDiNascita(userUpdate.getDataDiNascita());
//		user.setIndirizzo(userUpdate.getIndirizzo());
//		user.setTelefono(userUpdate.getTelefono());
//		
////		user.setNome("PIPPO");  TEST PER VEDERE SE MODIFICA 
//		
//		return userService.save(user);	

	
//	@RequestMapping(value="/home/users", method = RequestMethod.GET)
//	public String users(ModelMap modelMap) throws UserNotFoundException {
//		
//		ArrayList<User> users = (ArrayList<User>)userService.findAll();
//		modelMap.addAttribute("users",users);
//		int i;
//		for(i=0; i<users.size();i++)
//		{	
//			User u = users.get(i);
//			System.out.println("matricola i : " +u.getIdMatricola()+" nome : "+u.getNome()+" ");
//		}
//		return "users";
//	}

	
	
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
	
	
//	@PostMapping(value="/newUser/{idMatricola}/{nome}/{cognome}")
//	public User newUser(@PathVariable("idMatricola") int idMatricola, @PathVariable("nome") String nome, @PathVariable("cognome") String cognome){
//		
//		User user = new User();
//		
//		user.setIdMatricola(500000);
//		user.setNome(nome);
//		user.setCognome(cognome);
//		
//		return userService.newUser(user);
//	}

}
