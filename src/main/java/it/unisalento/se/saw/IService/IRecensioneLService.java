package it.unisalento.se.saw.IService;

import java.util.List;
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.domain.RecensioneM;

public interface IRecensioneLService {
	
	public List<RecensioneL> findAll();
	
	public List<RecensioneL> getRecLByInsegnamento(String string);
	
	public List<RecensioneL> getRecLByVoto(int voto);
	
	public RecensioneL getById(int idRecensioneL);
	
	public RecensioneL save(RecensioneL recensioneL);

	public RecensioneL updateRecLezById(int idRecensioneL);

	//nuovo metodo
	public RecensioneL getByMatricolaStudIdInsegIdLez(int idMatricola, int idInsegnamento, int idLezione);
		
	//nuovo metodo
	public List<RecensioneL> getRecLByIdLezione(int idLezione);

	
	


}
