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
		
//		List<UserDTO> ListUserDTO = new ArrayList< UserDTO>();
//				
//		for (int i =0; i<users.size(); i++)
//		{
//			UserDTO userDTO = new UserDTO();
//
//			userDTO.setIdMatricola(users.get(i).getIdMatricola());
//			userDTO.setNome(users.get(i).getNome());
//			userDTO.setCognome(users.get(i).getCognome());
//			userDTO.setDataDiNascita(users.get(i).getDataDiNascita());
//			userDTO.setEmail(users.get(i).getEmail());
//			userDTO.setIndirizzo(users.get(i).getIndirizzo());
//			userDTO.setTelefono(users.get(i).getTelefono());
//			
//			ListUserDTO.add(userDTO);
//
//			
//		}
//		return ListUserDTO;

//	}
	
	
	
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
			userDTO.setDataDiNascita(user.getDataDiNascita());
			userDTO.setEmail(user.getEmail());
			userDTO.setIndirizzo(user.getIndirizzo());
			userDTO.setTelefono(user.getTelefono());
			
			ListUserDTO.add(userDTO);

		}
		return ListUserDTO;
		
	}
	
	
	
	@GetMapping(value="/getById/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getById(@PathVariable("id") int id) throws UserNotFoundException {
		
		User user = userService.getById(id);
		UserDTO userDTO = new UserDTO();

		userDTO.setIdMatricola(user.getIdMatricola());
		userDTO.setNome(user.getNome());
		userDTO.setCognome(user.getCognome());
		userDTO.setDataDiNascita(user.getDataDiNascita());
		userDTO.setEmail(user.getEmail());
		userDTO.setIndirizzo(user.getIndirizzo());
		userDTO.setTelefono(user.getTelefono());
		return userDTO;
	}
	
	
	
	@PostMapping(value="/save", consumes=MediaType.APPLICATION_JSON_VALUE)
	public User post(@RequestBody UserDTO userDTO) {
		User user = new User();
		user.setNome(userDTO.getNome());
		user.setCognome(userDTO.getCognome());
		return userService.save(user);
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
			userDTO.setDataDiNascita(user.getDataDiNascita());
			userDTO.setEmail(user.getEmail());
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
	
	
	/*@GetMapping(value="/isStudente/{idMatricola}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StudenteDTO> isStudente(@PathVariable("idMatricola") int idMatricola){
		
		List<Studente> list = studenteService.isStudente(idMatricola);
		List<StudenteDTO> ListStudenteDTO = new ArrayList<StudenteDTO>();

		for (Studente studente : list)
		{
			StudenteDTO studenteDTO = new StudenteDTO();

			studenteDTO.setIdStudente(studente.getIdStudente());
//			studenteDTO.setNome(studente.getUser().getNome());
//			studenteDTO.setCognome(studente.getUser().getCognome());
//			studenteDTO.setDataDiNascita(studente.getUser().getDataDiNascita());
//			studenteDTO.setEmail(user.getEmail());
//			studenteDTO.setIndirizzo(user.getIndirizzo());
//			userDTO.setTelefono(user.getTelefono());
			studenteDTO.setAnnoIscrizione(studente.getAnnoIscrizione());
			
			ListStudenteDTO.add(studenteDTO);

		}
		return ListStudenteDTO;
	}*/
}
