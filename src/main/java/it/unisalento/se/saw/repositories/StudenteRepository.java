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

	

}
