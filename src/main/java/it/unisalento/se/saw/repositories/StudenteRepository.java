package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Studente;

public interface StudenteRepository extends JpaRepository<Studente, Integer> {
	

	@Query
	(value= "SELECT * FROM  User u, Studente s WHERE s.user_idMatricola = u.idMatricola AND s.user_idMatricola = :idMatricola", nativeQuery=true)
	public List<Studente> isStudente(@Param("idMatricola") int idMatricola);
	

}
