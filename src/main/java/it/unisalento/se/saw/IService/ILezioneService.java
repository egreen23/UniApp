package it.unisalento.se.saw.IService;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import it.unisalento.se.saw.domain.Lezione;


public interface ILezioneService {
	
	public List<Lezione> findAll();
	
	public List<Lezione> getLezioniByIdCalendario(int idCalendario);
	
	public List<Lezione> getLezioniByDocente(String cognome, String nome);
	
	public List<Lezione> getLezioniByInsegnamento(String nome);
	
	public List<Lezione> getLezioniByCorso(String nome);

	public Lezione updateById(int idLezione);
		
	public Lezione save(Lezione lezione);
		
	public Lezione getLezioneById(int idLezione);
	
//	public void deleteLez(int idLezione);
	
	public void deleteLezione(int id);
	
	//nuovo metodo CH
	public List<Lezione> getLezioniByIdDocente(int idDocente);
	
	//nuovo metodo CH
	public List<Lezione> getCalLezioniByCorso(String nome);
	
	//nuovo metodo CH
	public List<Lezione> getLezioniByIdInsegnamento(int idInsegnamento);



	


}
