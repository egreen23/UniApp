package it.unisalento.se.saw.IService;

import java.util.List;

<<<<<<< HEAD
import org.springframework.data.repository.query.Param;

=======
import it.unisalento.se.saw.domain.Esame;
>>>>>>> master
import it.unisalento.se.saw.domain.Insegnamento;

public interface IInsegnamentoService {
	
	public List<Insegnamento> findAll();
	
	public List<Insegnamento> getByDocente(String cognome, String nome);
	
	public List<Insegnamento> getByInsegnamento(String nome);

	public List<Insegnamento> getByCorso(String nome);
	
	public Insegnamento getById(int idInsegnamento);
	
	public Insegnamento save(Insegnamento insegnamento);
	
	public Insegnamento updateById(int idInsegnamento);
<<<<<<< HEAD
	
	public List<Insegnamento> getByIdCorso(int id);

	
	// public int getIdbyName(String string);
=======
>>>>>>> master


}
