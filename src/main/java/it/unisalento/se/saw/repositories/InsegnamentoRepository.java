package it.unisalento.se.saw.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Insegnamento;

@Repository
public interface InsegnamentoRepository extends JpaRepository<Insegnamento, Integer>{
	
	@Query
	(value = "SELECT * FROM Insegnamento ins WHERE ins.corso_di_studio_idCorso_di_studio = :id", nativeQuery=true)
	public List<Insegnamento> getByIdCorso(@Param("id") int id);
	
//	@Query
//	(value = "SELECT * FROM Insegnamento ins WHERE ins.docente_idDocente = :id", nativeQuery=true)
//	public List<Insegnamento> getbyIdDoc(@Param("id") int id);
	
	@Query
	(value= "SELECT * FROM Insegnamento ins, Docente doc, User u, Corso_di_studio c \n" + 
			"WHERE \n" + 
			"ins.docente_idDocente = doc.idDocente and \n" + 
			"doc.user_idMatricola = u.idMatricola  and \n" + 
			"ins.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio and \n" + 
			"doc.idDocente= :idDocente", nativeQuery=true)
	public List<Insegnamento> getByIdDocente(@Param("idDocente") int idDocente);
	
	
}