package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.User;

@Repository
public interface LezioneRepository extends JpaRepository<Lezione, Integer> {
	
	@Query
	(value= "SELECT * FROM Lezione l WHERE l.calendario_idCalendario = :idCalendario", nativeQuery=true)
	public List<Lezione> getLezioniByIdCalendario(@Param("idCalendario") int idCalendario);

	@Query
	(value= "SELECT * FROM Lezione l, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and u.cognome = :cognome and u.nome = :nome", nativeQuery=true)
	public List<Lezione> getLezioniByDocente(@Param("cognome") String cognome, @Param("nome") String nome);
	
	
	@Query
	(value= "SELECT * FROM Lezione l, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and i.nome = :nome", nativeQuery=true)
	public List<Lezione> getLezioniByInsegnamento(@Param("nome") String nome);
	
	
	@Query
	(value= "SELECT * FROM Lezione l, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and c.nome = :nome", nativeQuery=true)
	public List<Lezione> getLezioniByCorso(@Param("nome") String nome);
	

	
//	@Modifying
//	@Query
//	(value= "DELETE FROM Lezione WHERE idLezione= :idLezione", nativeQuery=true)
//	public void deleteLez(@Param("idLezione") int idLezione);
	
}
