package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Esame;

@Repository
public interface EsameRepository extends JpaRepository<Esame, Integer> {
	
	@Query
	(value= "SELECT * FROM Esame e WHERE e.calendario_idCalendario = :idCalendario", nativeQuery=true)
	public List<Esame> getEsameByIdCalendario(@Param("idCalendario") int idCalendario);

	@Query
	(value= "SELECT * FROM Esame e, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE e.calendario_idCalendario = cal.idCalendario \n" + 
			"and e.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and u.cognome = :cognome and u.nome = :nome", nativeQuery=true)
	public List<Esame> getEsamiByDocente(@Param("cognome") String cognome, @Param("nome") String nome);
	
	
	@Query
	(value= "SELECT * FROM Esame e, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE e.calendario_idCalendario = cal.idCalendario \n" + 
			"and e.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and i.nome = :nome", nativeQuery=true)
	public List<Esame> getEsamiByInsegnamento(@Param("nome") String nome);
	
	
	@Query
	(value= "SELECT * FROM Esame e, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE e.calendario_idCalendario = cal.idCalendario \n" + 
			"and e.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and c.nome = :nome", nativeQuery=true)
	public List<Esame> getEsamiByCorso(@Param("nome") String nome);
	
	

}
