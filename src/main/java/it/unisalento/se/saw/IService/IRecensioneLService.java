package it.unisalento.se.saw.IService;

import java.util.List;
import it.unisalento.se.saw.domain.RecensioneL;

public interface IRecensioneLService {
	
	public List<RecensioneL> findAll();
	
	public List<RecensioneL> getRecLByInsegnamento(String string);
	
	public List<RecensioneL> getRecLByVoto(int voto);
	
	public RecensioneL getById(int idRecensioneL);
	
	public RecensioneL save(RecensioneL recensioneL);

	public RecensioneL updateRecLezById(int idRecensioneL);

	
	


}
