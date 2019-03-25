package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query
	(value= "SELECT * FROM User u WHERE u.idMatricola = :idMatricola AND u.password = :password", nativeQuery=true)
	public User isValidate(@Param("idMatricola") int idMatricola, @Param("password") String password);
	
	@Query
	(value="SELECT idMatricola From User where email=:email",nativeQuery=true)
	public Integer getMatricola(@Param("email")String email);

	
//	@Modifying
//	@Query
//	(value= "INSERT INTO User (idMatricola, nome, cognome) VALUES (:user.getIdMatricola, :user.getNome, :user.getCognome)", nativeQuery=true)
//	public User newUser(@Param("user") User user);


	/*@Query("select u from User u where u.userType.iduserType=1 and u.corsoDiStudio.idcorsoDiStudio=:idCorsoDiStudio")
	public List<User> getUserByCorsoDiStudioId(@Param("idCorsoDiStudio")int idCorsoDiStudio);*/
	
//	@Query(value= "SELECT * FROM User  ", nativeQuery = true)
//	public List<User> findUser();
	
}
