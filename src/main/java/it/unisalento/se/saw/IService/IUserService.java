package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

public interface IUserService {
	
	public List<User> findAll();
	
	public List<User> isValidate(int idMatricola, String password);
	
	public User save(User user);
		
	public User updateUserById(int idMatricola);
	
	public User removeUserById(int idMatricola) throws UserNotFoundException;	
	
	public User getById(int idMatricola) throws UserNotFoundException;



	


}
