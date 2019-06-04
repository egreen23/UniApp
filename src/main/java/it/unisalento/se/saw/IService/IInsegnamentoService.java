package it.unisalento.se.saw.IService;

import java.util.List;

import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Insegnamento;

public interface IInsegnamentoService {
	
	public List<Insegnamento> findAll();
			
	public Insegnamento getById(int idInsegnamento);
	
	public Insegnamento save(Insegnamento insegnamento);
<<<<<<< HEAD
		
	public List<Insegnamento> getByIdCorso(int id);

//	public List<Insegnamento> getbyIdDoc(int id);
	
=======
	
	public Insegnamento updateById(int idInsegnamento);
	
	public List<Insegnamento> getByIdCorso(int id);

	//nuovo metodo CH
>>>>>>> master
	public List<Insegnamento> getByIdDocente(int idDocente);

	

}
