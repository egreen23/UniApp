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

	
	

}
