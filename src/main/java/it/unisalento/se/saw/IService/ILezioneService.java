package it.unisalento.se.saw.IService;

import java.util.List;



import it.unisalento.se.saw.domain.Lezione;


public interface ILezioneService {
	
	public List<Lezione> findAll();
	
	public List<Lezione> getLezioniByIdCalendario(int idCalendario);
		
	public List<Lezione> getCalLezioniByCorso(String nome);
			
	public Lezione save(Lezione lezione);
		
	public Lezione getLezioneById(int idLezione);
	
	public List<Lezione> getLezionibyIdAula(int id);
	
	public void deleteLezione(int id);
	
<<<<<<< HEAD
	public List<Lezione> saveAll(List<Lezione> list);
	
	public List<Lezione> getLezioniByIdDocente(int idDocente);
	
	public List<Lezione> getLezioniByIdInsegnamento(int idInsegnamento);
=======
	//nuovo metodo CH
	public List<Lezione> getLezioniByIdDocente(int idDocente);
	
	//nuovo metodo CH
	public List<Lezione> getCalLezioniByCorso(String nome);
	
	//nuovo metodo CH
	public List<Lezione> getLezioniByIdInsegnamento(int idInsegnamento);


>>>>>>> master

	


}
