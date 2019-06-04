package it.unisalento.se.saw.repositories;

import java.util.List;

import org.eclipse.persistence.annotations.DeleteAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;

import it.unisalento.se.saw.domain.Attrezzatura;
import it.unisalento.se.saw.domain.Aula;

public interface AttrezzaturaRepository extends JpaRepository<Attrezzatura, Integer> {

	
	@Modifying
	@Query(value= "DELETE FROM Attrezzatura WHERE aula_idAula= :idAula and tool_idTool= :idTool", nativeQuery=true)
	public void deleteAtt(@Param("idAula") int idAula, @Param("idTool") int idTool );
	
	@Query(value= "SELECT * FROM  Attrezzatura WHERE aula_idAula= :idAula and tool_idTool= :idTool ", nativeQuery=true)
	public Attrezzatura getIdAttByAT(@Param("idAula") int idAula, @Param("idTool") int idTool );
	
	@Query(value= "SELECT * FROM Attrezzatura a WHERE a.aula_idAula =:id", nativeQuery=true)
	public List<Attrezzatura> getAttrezzaturabyIdAula(@Param("id") int id);
		
}
