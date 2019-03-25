package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.repositories.UserRepository;

@Repository
@Service
public class UserService implements IUserService{
	
	@Autowired
	UserRepository userRepository;
	
	
	
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Transactional
	public User save(User user) {
//		return userRepository.saveAndFlush(user);
		userRepository.save(user);
		userRepository.flush();
		return user;
	}

	@Transactional
	public User updateUserById(int idMatricola) {
		return userRepository.getOne(idMatricola);
	}
	
	@Transactional
	public User isValidate(int idMatricola, String password) {
		return userRepository.isValidate(idMatricola, password);
	}
 
	@Transactional
	public User getById(int idMatricola){
			User user = userRepository.getOne(idMatricola);
			return user;
	}
	
	@Transactional
	public User removeUserById(int idMatricola) {
			User user = userRepository.getOne(idMatricola);
			userRepository.delete(user);
			return user;
	}
	
	@Transactional
	public Integer getMatricola(String email){
		return userRepository.getMatricola(email);
	}
	
}
