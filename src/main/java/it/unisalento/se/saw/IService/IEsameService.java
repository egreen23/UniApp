package it.unisalento.se.saw.IService;

import java.util.List;

import org.springframework.data.repository.query.Param;

import it.unisalento.se.saw.domain.Esame;

public interface IEsameService {
	
	public List<Esame> findAll();
	
	public List<Esame> getEsameByIdCalendario(int idEsame);
		
	public Esame save(Esame esame);
	
	public Esame getEsameById(int idEsame);
	
	public void deleteEsame(int id);
	
	public List<Esame> saveAll(List<Esame> list);
	
	public List<Esame> getEsamiibyIdAula(int id);

	



	

}
