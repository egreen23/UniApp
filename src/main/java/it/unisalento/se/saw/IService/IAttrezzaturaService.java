package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Attrezzatura;
import it.unisalento.se.saw.domain.Tool;

public interface IAttrezzaturaService {
	
	public List<Attrezzatura> findAll();
	
	public Attrezzatura save(Attrezzatura attrezzatura);
	
	public void deleteAtt(int idAula, int idTool);
	
	public Attrezzatura getIdAttByAT(int idAula, int idTool);
	
}
