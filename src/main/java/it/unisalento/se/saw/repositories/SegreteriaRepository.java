package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.domain.Studente;

public interface SegreteriaRepository extends JpaRepository<Segreteria, Integer> {
	
	@Query
	(value= "SELECT * FROM  User u, Segreteria s WHERE s.user_idMatricola = u.idMatricola AND u.idMatricola = :idMatricola", nativeQuery=true)
	public Segreteria logSegreteria(@Param("idMatricola") int idMatricola);
	
	@Query
	(value= "SELECT * FROM Segreteria s WHERE s.user_idMatricola =:id", nativeQuery = true)
	public Segreteria getbyMatricola(@Param("id") int id);
	

}
