package it.unisalento.se.saw.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Studente;

@Repository
public interface InsegnamentoRepository extends JpaRepository<Insegnamento, Integer>{
	
	
	
//	@Query("select i from Insegnamento i where i.corsoDiStudio.idcorsoDiStudio=:idCorsoDiStudio")
//	public List<Insegnamento> getInsegnamentiByIdCorsoDiStudio(@Param("idCorsoDiStudio")int idCorsoDiStudio);
	
	@Query
	(value= "SELECT * FROM Insegnamento ins, Docente doc, User u, Corso_di_studio c WHERE ins.docente_idDocente = doc.idDocente and doc.user_idMatricola = u.idMatricola  and ins.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio and ins.nome = :string || u.cognome= :string ", nativeQuery=true)
	public List<Insegnamento> getByName(@Param("string") String string);
	

}
