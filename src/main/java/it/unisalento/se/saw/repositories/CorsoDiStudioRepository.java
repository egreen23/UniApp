package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Insegnamento;

@Repository
public interface CorsoDiStudioRepository extends JpaRepository<CorsoDiStudio, Integer> {

	@Query
	(value= "SELECT * FROM Corso_di_studio c where c.tipo = :string", nativeQuery=true)
	public List<CorsoDiStudio> getByTipo(@Param("string") String string);

	
}
