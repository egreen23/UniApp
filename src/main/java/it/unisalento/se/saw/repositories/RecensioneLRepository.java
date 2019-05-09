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
	
	
	
	 
}
