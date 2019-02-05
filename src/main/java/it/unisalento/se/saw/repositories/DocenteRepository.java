package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {
	
	@Query
	(value= "SELECT * FROM  User u, Docente d WHERE d.user_idMatricola = u.idMatricola AND d.user_idMatricola = :idMatricola", nativeQuery=true)
	public List<Docente> isDocente(@Param("idMatricola") int idMatricola);

}
