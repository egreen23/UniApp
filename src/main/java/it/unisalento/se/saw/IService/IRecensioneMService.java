package it.unisalento.se.saw.IService;

import java.util.List;
import it.unisalento.se.saw.domain.RecensioneM;

public interface IRecensioneMService {
	
	public List<RecensioneM> findAll();
	
	public List<RecensioneM> getRecMatByInsegnamento(String string);
	
	public List<RecensioneM> getRecMatByVoto(int voto);
	
	public RecensioneM getById(int idRecensioneM);
	
	public RecensioneM save(RecensioneM recensioneM);

	public RecensioneM updateRecMatById(int idRecensioneM);

}
