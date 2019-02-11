package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.User;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
	
//	@Query(value= "SELECT * from Aula a where a.nome= :string ", nativeQuery=true)
//	public Aula getByName(@Param("string") String string);
	
	@Query(value= "SELECT * from Aula a where a.nome= :string || a.edificio= :string || a.piano = :string", nativeQuery=true)
	public List<Aula> getByName(@Param("string") String string);
	
	
}
