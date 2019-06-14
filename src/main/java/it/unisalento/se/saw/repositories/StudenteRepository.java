package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;

public interface StudenteRepository extends JpaRepository<Studente, Integer> {
	
	@Query
	(value= "SELECT * FROM Studente s, User u WHERE s.user_idMatricola = u.idMatricola and u.idMatricola = :idMatricola", nativeQuery=true)
	public Studente logStudent(@Param("idMatricola") int idMatricola);
		
	@Query
	(value= "SELECT * FROM Studente s WHERE s.user_idMatricola =:id", nativeQuery = true)
	public Studente getbyMatricola(@Param("id") int id);
	
	//nuovo Ch 2.0
	@Query
	(value= "	SELECT * from Studente s, User u, Corso_di_studio c where \n" + 
			"	u.idMatricola = s.user_idMatricola and \n" + 
			"	c.idCorso_di_studio = s.corso_di_studio_idCorso_di_studio \n" + 
			"	and c.idCorso_di_studio = :idCdS", nativeQuery = true)
	public List<Studente> getAllStudByIdCdS(@Param("idCdS") int idCdS);

	
//	@Query
//	(value= "SELECT * FROM Studente s, User u, Corso_di_studio c WHERE s.user_idMatricola = u.idMatricola and s.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio", nativeQuery=true)
//	public List<Studente> findAll();

}