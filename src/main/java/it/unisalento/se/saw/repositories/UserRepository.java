package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query
	(value= "SELECT * FROM User u WHERE u.idMatricola = :idMatricola AND u.password = :password", nativeQuery=true)
	public List<User> isValidate(@Param("idMatricola") int idMatricola, @Param("password") String password);

	
	

//	public List<User> findAll();
//
//	
//	public User getById(int id);
	
	
	/*@Query("select u from User u where u.userType.iduserType=1 and u.corsoDiStudio.idcorsoDiStudio=:idCorsoDiStudio")
	public List<User> getUserByCorsoDiStudioId(@Param("idCorsoDiStudio")int idCorsoDiStudio);*/
	
//	@Query(value= "SELECT * FROM User  ", nativeQuery = true)
//	public List<User> findUser();
	
}
