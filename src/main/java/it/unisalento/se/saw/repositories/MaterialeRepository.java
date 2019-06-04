package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Materiale;

@Repository
public interface MaterialeRepository extends JpaRepository<Materiale, Integer> {
	
	@Query(value= "SELECT * from Materiale m, Insegnamento i where m.insegnamento_idInsegnamento = i.idInsegnamento and i.nome= :string", nativeQuery=true)
	public List<Materiale> getByName(@Param("string") String string);
	
	//nuovo metodo CH
	@Query(value= "SELECT * from Materiale m, Insegnamento i where m.insegnamento_idInsegnamento = i.idInsegnamento and i.idInsegnamento= :idInsegnamento", nativeQuery=true)
	public List<Materiale> getMatByIdInsegnamento(@Param("idInsegnamento") int idInsegnamento);
	
	
}
