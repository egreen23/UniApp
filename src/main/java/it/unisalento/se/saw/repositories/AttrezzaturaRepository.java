package it.unisalento.se.saw.repositories;

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
	
//	@Modifying
//	@Query(value= "Insert INTO Attrezzatura a (idAttrezzatura, tool_idTool, aula_idAula)  VALUES (a.aula_idAula= :?, a.tool_idTool= :?) ", nativeQuery=true)
//	public void saveAtt(@Param("idaula") int idAula, @Param("idtool") int idTool );
//	
	
	@Query(value= "SELECT * FROM  Attrezzatura WHERE aula_idAula= :idAula and tool_idTool= :idTool ", nativeQuery=true)
	public Attrezzatura getIdAttByAT(@Param("idAula") int idAula, @Param("idTool") int idTool );
	
	
		
}
