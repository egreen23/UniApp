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
=======

import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.domain.RecensioneM;

public interface IRecensioneMService {
	
	public List<RecensioneM> findAll();
>>>>>>> master
	
	public Recensionem getByMatricolaStudIdInsegIdMaterial(int idMatricola, int idInsegnamento, int idMateriale);
	
<<<<<<< HEAD
	public List<Recensionem> getRecByIdMateriale(int idMateriale);
=======
	public List<RecensioneM> getRecMatByVoto(int voto);
	
	public RecensioneM getById(int idRecensioneM);
	
	public RecensioneM save(RecensioneM recensioneM);

	public RecensioneM updateRecMatById(int idRecensioneM);
	
	//nuovo metodo
	public RecensioneM getByMatricolaStudIdInsegIdMaterial(int idMatricola, int idInsegnamento, int idMateriale);

	//nuovo metodo
	public List<RecensioneM> getRecByIdMateriale(int idMateriale);
>>>>>>> master

}
