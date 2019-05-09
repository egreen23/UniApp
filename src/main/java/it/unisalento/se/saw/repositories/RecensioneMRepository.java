package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import it.unisalento.se.saw.domain.Recensionem;

@Repository
public interface RecensioneMRepository extends JpaRepository<Recensionem, Integer> {
=======
import it.unisalento.se.saw.domain.RecensioneM;

@Repository
public interface RecensioneMRepository extends JpaRepository<RecensioneM, Integer> {
>>>>>>> master

	@Query
	(value= "SELECT * FROM RecensioneM rm, Materiale m, Insegnamento i, Corso_di_studio c\n" + 
			"	WHERE m.idMateriale = rm.materiale_idMateriale\n" + 
			"	and m.insegnamento_idInsegnamento = i.idInsegnamento\n" + 
			"	and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"	and i.nome= :string", nativeQuery=true)
<<<<<<< HEAD
	public List<Recensionem> getRecMatByInsegnamento(@Param("string") String string);	
=======
	public List<RecensioneM> getRecMatByInsegnamento(@Param("string") String string);	
>>>>>>> master
	
	@Query
	(value= "SELECT * FROM RecensioneM rm, Materiale m, Insegnamento i, Corso_di_studio c\n" + 
			"WHERE m.idMateriale = rm.materiale_idMateriale\n" + 
			"and m.insegnamento_idInsegnamento = i.idInsegnamento\n" + 
			"and i.corso_di_studio_idCorso_di_studio = c.idCorso_di_studio\n" + 
			"and rm.voto= :voto", nativeQuery=true)
<<<<<<< HEAD
	public List<Recensionem> getRecMatByVoto(@Param("voto") int voto);
=======
	public List<RecensioneM> getRecMatByVoto(@Param("voto") int voto);
>>>>>>> master
	
	
}
