package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import it.unisalento.se.saw.domain.Segreteria;

public interface SegreteriaRepository extends JpaRepository<Segreteria, Integer> {
	
	@Query
	(value= "SELECT * FROM  User u, Segreteria s WHERE s.user_idMatricola = u.idMatricola AND s.user_idMatricola = :idMatricola", nativeQuery=true)
	public List<Segreteria> isSegreteria(@Param("idMatricola") int idMatricola);
	

}
