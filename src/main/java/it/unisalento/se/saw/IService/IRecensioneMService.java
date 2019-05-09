package it.unisalento.se.saw.IService;

import java.util.List;
<<<<<<< HEAD
import it.unisalento.se.saw.domain.Recensionem;

public interface IRecensioneMService {
	
	public List<Recensionem> findAll();
	
	public List<Recensionem> getRecMatByInsegnamento(String string);
	
	public List<Recensionem> getRecMatByVoto(int voto);
	
	public Recensionem getById(int idRecensioneM);
	
	public Recensionem save(Recensionem recensioneM);

	public Recensionem updateRecMatById(int idRecensioneM);
=======
import it.unisalento.se.saw.domain.RecensioneM;

public interface IRecensioneMService {
	
	public List<RecensioneM> findAll();
	
	public List<RecensioneM> getRecMatByInsegnamento(String string);
	
	public List<RecensioneM> getRecMatByVoto(int voto);
	
	public RecensioneM getById(int idRecensioneM);
	
	public RecensioneM save(RecensioneM recensioneM);

	public RecensioneM updateRecMatById(int idRecensioneM);
>>>>>>> master

}
