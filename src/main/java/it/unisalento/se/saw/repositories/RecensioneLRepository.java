package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Recensionel;

@Repository
public interface RecensioneLRepository extends JpaRepository<Recensionel, Integer>{

	@Query
	(value= "SELECT * FROM RecensioneL rl, Lezione l, Insegnamento i, Corso_di_studio c\n" + 
			"	WHERE  rl.lezione_idLezione = l.idLezione\n" + 
			"	and l.insegnamento_idInsegnamento = i.idInsegnamento\n" + 
			"	and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"	and i.nome= :string ", nativeQuery=true)
	public List<Recensionel> getRecLByInsegnamento(@Param("string") String string);	
	
	@Query
	(value= "SELECT * FROM RecensioneL rl, Lezione l, Insegnamento i, Corso_di_studio c\n" + 
			"	WHERE  rl.lezione_idLezione = l.idLezione\n" + 
			"	and l.insegnamento_idInsegnamento = i.idInsegnamento\n" + 
			"	and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"	and rl.voto= :voto ", nativeQuery=true)
	public List<Recensionel> getRecLByVoto(@Param("voto") int voto);	
	
	//nuovo metodo
		@Query
		(value= "SELECT * from Lezione l, Insegnamento i, RecensioneL r, Studente s, User u, Corso_di_studio c where \n" + 
				"u.idMatricola = s.user_idMatricola and \n" + 
				"s.idStudente = r.studente_idStudente and\n" + 
				"l.idLezione = r.lezione_idLezione and \n" + 
				"c.idCorso_di_studio = s.corso_di_studio_idCorso_di_studio and\n" + 
				"i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio \n" + 
				"and u.idMatricola= :idMatricola \n" + 
				"and i.idInsegnamento= :idInsegnamento\n" + 
				"and l.idLezione= :idLezione", nativeQuery=true)
		public Recensionel getByMatricolaStudIdInsegIdLez(@Param("idMatricola") int idMatricola, @Param("idInsegnamento") int idInsegnamento, @Param("idLezione") int idLezione);
		
		//nuovo metodo
		@Query
		(value= "SELECT * FROM RecensioneL rl, Lezione l\n" + 
				"WHERE  rl.lezione_idLezione = l.idLezione\n" + 
				"and l.idLezione= :idLezione", nativeQuery=true)
		public List<Recensionel> getRecLByIdLezione(@Param("idLezione") int idLezione);
	
	
	
	 
}
