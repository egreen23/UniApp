package it.unisalento.se.saw.IService;

import java.util.List;
import it.unisalento.se.saw.domain.Recensionem;

public interface IRecensioneMService {
	
	public List<Recensionem> findAll();
	
	public List<Recensionem> getRecMatByInsegnamento(String string);
	
	public List<Recensionem> getRecMatByVoto(int voto);
	
	public Recensionem getById(int idRecensioneM);
	
	public Recensionem save(Recensionem recensioneM);
	
	public Recensionem getByMatricolaStudIdInsegIdMaterial(int idMatricola, int idInsegnamento, int idMateriale);
	
	public List<Recensionem> getRecByIdMateriale(int idMateriale);

}
