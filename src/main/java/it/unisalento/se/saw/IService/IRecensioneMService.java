package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.domain.RecensioneM;

public interface IRecensioneMService {
	
	public List<RecensioneM> findAll();
	
	public List<RecensioneM> getRecMatByInsegnamento(String string);
	
	public List<RecensioneM> getRecMatByVoto(int voto);
	
	public RecensioneM getById(int idRecensioneM);
	
	public RecensioneM save(RecensioneM recensioneM);

	public RecensioneM updateRecMatById(int idRecensioneM);
	
	//nuovo metodo
	public RecensioneM getByMatricolaStudIdInsegIdMaterial(int idMatricola, int idInsegnamento, int idMateriale);

	//nuovo metodo
	public List<RecensioneM> getRecByIdMateriale(int idMateriale);

}
