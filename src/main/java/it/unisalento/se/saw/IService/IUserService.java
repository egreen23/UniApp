package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;

public interface IUserService {
	
	public List<User> findAll();
	
	public User isValidate(int idMatricola, String password);
	
	public User save(User user);
		
	public User updateUserById(int idMatricola);
	
	public User removeUserById(int idMatricola);	
	
	public User getById(int idMatricola);
	
	public Integer getMatricola(String email);	


	


}
