package it.unisalento.se.saw.IService;

import java.util.List;
<<<<<<< HEAD
import it.unisalento.se.saw.domain.Recensionel;

public interface IRecensioneLService {
	
	public List<Recensionel> findAll();
	
	public List<Recensionel> getRecLByInsegnamento(String string);
	
	public List<Recensionel> getRecLByVoto(int voto);
	
	public Recensionel getById(int idRecensioneL);
	
	public Recensionel save(Recensionel recensioneL);
=======
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.domain.RecensioneM;

public interface IRecensioneLService {
	
	public List<RecensioneL> findAll();
>>>>>>> master
	
	public Recensionel getByMatricolaStudIdInsegIdLez(int idMatricola, int idInsegnamento, int idLezione);
	
<<<<<<< HEAD
	public List<Recensionel> getRecLByIdLezione(int idLezione);
=======
	public List<RecensioneL> getRecLByVoto(int voto);
	
	public RecensioneL getById(int idRecensioneL);
	
	public RecensioneL save(RecensioneL recensioneL);

	public RecensioneL updateRecLezById(int idRecensioneL);

	//nuovo metodo
	public RecensioneL getByMatricolaStudIdInsegIdLez(int idMatricola, int idInsegnamento, int idLezione);
		
	//nuovo metodo
	public List<RecensioneL> getRecLByIdLezione(int idLezione);
>>>>>>> master

	
	


}
