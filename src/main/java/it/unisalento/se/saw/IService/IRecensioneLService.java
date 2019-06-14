package it.unisalento.se.saw.IService;

import java.util.List;
import it.unisalento.se.saw.domain.Recensionel;

public interface IRecensioneLService {
	
	public List<Recensionel> findAll();
	
	public List<Recensionel> getRecLByInsegnamento(String string);
	
	public List<Recensionel> getRecLByVoto(int voto);
	
	public Recensionel getById(int idRecensioneL);
	
	public Recensionel save(Recensionel recensioneL);
	
	public Recensionel getByMatricolaStudIdInsegIdLez(int idMatricola, int idInsegnamento, int idLezione);
	
	public List<Recensionel> getRecLByIdLezione(int idLezione);

	
	


}
