package it.unisalento.se.saw.IService;

import java.util.List;
import it.unisalento.se.saw.domain.Esame;
import it.unisalento.se.saw.domain.Lezione;

public interface IEsameService {
	
	public List<Esame> findAll();
	
	public List<Esame> getEsameByIdCalendario(int idEsame);
	
	public List<Esame> getEsamiByDocente(String cognome, String nome);
	
	public List<Esame> getEsamiByInsegnamento(String nome);

	public List<Esame> getEsamiByCorso(String nome);
	
	public Esame updateById(int idEsame);
	
	public Esame save(Esame esame);
	
	public Esame getEsameById(int idEsame);

	
//	public void deleteEsameById(int idEsame);


	

}
