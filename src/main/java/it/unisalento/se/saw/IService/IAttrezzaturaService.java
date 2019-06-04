package it.unisalento.se.saw.IService;

import java.util.List;

import it.unisalento.se.saw.domain.Attrezzatura;
import it.unisalento.se.saw.domain.Tool;

public interface IAttrezzaturaService {
	
	public List<Attrezzatura> findAll();
	
	public Attrezzatura save(Attrezzatura attrezzatura);
	
	public Attrezzatura getIdAttByAT(int idAula, int idTool); //inutile
	
	public Attrezzatura updateById(int idAttrezzatura);
	
	public void deleteAtt(int id);
	
	public List<Attrezzatura> getAttrezzaturabyIdAula(int id);
	
	public void deleteAll(List<Attrezzatura> list);
	
	public Attrezzatura getOne(int id);
}
