package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.composite.CalendarioComponent;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Integer> {
	
//	@Query
//	(value= "SELECT * FROM Calendario c, Lezione l, Insegnamento i, WHERE l.calendario_idCalendario = :c.idCalendario and l.insegnamento_idInsegnamento = :i.idInsegnamento and i.nome = :string ", nativeQuery=true)
//	public List<Lezione> calLezione(@Param("string") String string);
//
//	
	
	@Query
	(value= "SELECT * FROM Calendario", nativeQuery=true)
		public List<Calendario> getAll();
	
	@Query //08/05/2019 niko
	(value= "SELECT * FROM Calendario c WHERE c.corso_di_studio_idCorso_di_studio = :idCds", nativeQuery=true)
	public List<Calendario> getCalendaribyCds(@Param("idCds") int idCds);
	

}
