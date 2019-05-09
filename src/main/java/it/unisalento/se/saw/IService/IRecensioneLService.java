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

	public Recensionel updateRecLezById(int idRecensioneL);
=======
import it.unisalento.se.saw.domain.RecensioneL;

public interface IRecensioneLService {
	
	public List<RecensioneL> findAll();
	
	public List<RecensioneL> getRecLByInsegnamento(String string);
	
	public List<RecensioneL> getRecLByVoto(int voto);
	
	public RecensioneL getById(int idRecensioneL);
	
	public RecensioneL save(RecensioneL recensioneL);

	public RecensioneL updateRecLezById(int idRecensioneL);
>>>>>>> master

	
	


}
