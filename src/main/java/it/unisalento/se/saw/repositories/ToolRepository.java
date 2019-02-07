package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {
	
	@Query(value= "SELECT * from Tool t where t.nome= :string", nativeQuery=true)
	public List<Tool> getByName(@Param("string") String string);
	
}
