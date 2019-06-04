package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Studente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {
	
	@Query
	(value= "SELECT * FROM  User u, Docente doc WHERE doc.user_idMatricola = u.idMatricola AND u.idMatricola = :idMatricola", nativeQuery=true)
	public Docente logDocente(@Param("idMatricola") int idMatricola);
	
	@Query
	(value= "SELECT * FROM Docente d WHERE d.user_idMatricola =:id", nativeQuery = true)
	public Docente getbyMatricola(@Param("id") int id);

	@Query
	(value= "SELECT * FROM Docente d WHERE d.user_idMatricola =:id", nativeQuery = true)
	public Docente getbyMatricola(@Param("id") int id);
}
