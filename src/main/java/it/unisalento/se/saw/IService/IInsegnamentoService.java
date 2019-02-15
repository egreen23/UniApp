package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Insegnamento;

public interface IInsegnamentoService {
	
	public List<Insegnamento> findAll();
	
	public List<Insegnamento> findByName(String string);


}
