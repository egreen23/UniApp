package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import it.unisalento.se.saw.domain.Segnalazione;

@Repository
public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Integer> {
	
	@Query
	(value= "SELECT * FROM Segnalazione s  WHERE s.stato= :string", nativeQuery=true)
	public List<Segnalazione> getByStato(@Param("string") String string);
	
	
	@Query
	(value= "SELECT * FROM Segnalazione s, Docente doc, User u WHERE s.docente_idDocente = doc.idDocente \n" + 
			"and doc.user_idMatricola = u.idMatricola and u.cognome= :cognome and u.nome = :nome", nativeQuery=true)
	public List<Segnalazione> getByDocente(@Param("cognome") String cognome, @Param("nome") String nome);
	
	@Query
	(value= "SELECT * FROM Segnalazione s WHERE s.docente_idDocente =:id", nativeQuery = true)
	public List<Segnalazione> getByidDocente(@Param("id") int id);
	
	@Query
	(value= "SELECT * FROM Segnalazione s WHERE s.aula_idAula =:id", nativeQuery = true)
	public List<Segnalazione> getByidAula(@Param("id") int id);
	
	@Query
	(value= "SELECT * FROM Segnalazione s WHERE s.segreteria_idSegreteria =:id", nativeQuery = true)
	public List<Segnalazione> getByidSegr(@Param("id") int id);
	
	
	
}
