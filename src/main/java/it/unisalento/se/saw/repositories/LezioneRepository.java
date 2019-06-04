package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.composite.LezioneDTOComp;

@Repository
public interface LezioneRepository extends JpaRepository<Lezione, Integer> {
	
	@Query
	(value= "SELECT * FROM Lezione l WHERE l.calendario_idCalendario = :idCalendario", nativeQuery=true)
	public List<Lezione> getLezioniByIdCalendario(@Param("idCalendario") int idCalendario);
	
	@Query
	(value= "SELECT * FROM Lezione l, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and c.nome = :nome", nativeQuery=true)
	public List<Lezione> getLezioniByCorso(@Param("nome") String nome);
	
	@Query
	(value= "SELECT * FROM Lezione l WHERE l.aula_idAula =:id", nativeQuery=true)
	public List<Lezione> getLezionibyIdAula(@Param("id") int id);
	
	//nuovo metodo CH
	@Query
	(value= "SELECT * FROM Calendario cal, Lezione l, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and cal.idCalendario = l.calendario_idCalendario\n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento \n" + 
			"and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and d.idDocente= :idDocente", nativeQuery=true)
	public List<Lezione> getLezioniByIdDocente(@Param("idDocente") int idDocente);

//	//nuovo metodo CH
//	@Query
//	(value= "SELECT * FROM Lezione l, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
//			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
//			"and l.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
//			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and c.nome = :nome", nativeQuery=true)
//	public List<Lezione> getCalLezioniByCorso(@Param("nome") String nome);
	
	//nuovo metodo CH
	@Query
	(value= "SELECT * FROM Lezione l, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
<<<<<<< HEAD
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and i.idInsegnamento = :idInsegnamento", nativeQuery=true)
	public List<Lezione> getLezioniByIdInsegnamento(@Param("idInsegnamento") int idInsegnamento);
=======
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and c.nome = :nome", nativeQuery=true)
	public List<Lezione> getLezioniByCorso(@Param("nome") String nome);
	
	
	//nuovo metodo CH
	@Query
	(value= "SELECT * FROM Calendario cal, Lezione l, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and cal.idCalendario = l.calendario_idCalendario\n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento \n" + 
			"and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and d.idDocente= :idDocente", nativeQuery=true)
	public List<Lezione> getLezioniByIdDocente(@Param("idDocente") int idDocente);

	//nuovo metodo CH
	@Query
	(value= "SELECT * FROM Lezione l, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and c.nome = :nome", nativeQuery=true)
	public List<Lezione> getCalLezioniByCorso(@Param("nome") String nome);
	
	//nuovo metodo CH
	@Query
	(value= "SELECT * FROM Lezione l, Calendario cal, Insegnamento i, Corso_di_studio c, Docente d, User u\n" + 
			"WHERE l.calendario_idCalendario = cal.idCalendario \n" + 
			"and l.insegnamento_idInsegnamento = i.idInsegnamento and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and i.docente_idDocente = d.idDocente and d.user_idMatricola = u.idMatricola and i.idInsegnamento = :idInsegnamento", nativeQuery=true)
	public List<Lezione> getLezioniByIdInsegnamento(@Param("idInsegnamento") int idInsegnamento);
	
//	@Modifying
//	@Query
//	(value= "DELETE FROM Lezione WHERE idLezione= :idLezione", nativeQuery=true)
//	public void deleteLez(@Param("idLezione") int idLezione);
>>>>>>> master
	
}
