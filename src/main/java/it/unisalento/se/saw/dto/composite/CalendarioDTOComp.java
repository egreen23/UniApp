package it.unisalento.se.saw.dto.composite;

import java.util.ArrayList;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class CalendarioDTOComp extends CalendarioComponent {
	
	
	
    private ArrayList<CalendarioComponent> calendario= new ArrayList<>();

    private int idCalendario;
    private String tipo;
    private String anno;
    private String dataInizio;
    private String dataFine;
    private String semestre;
    
	public CalendarioDTOComp(int idCalendario, String tipo, String anno, String dataInizio, String dataFine,
			String semestre) {

		this.idCalendario = idCalendario;
		this.tipo = tipo;
		this.anno = anno;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.semestre = semestre;
	}
    
    
    
	public int getIdCalendario() {
		return idCalendario;
	}
	public void setIdCalendario(int idCalendario) {
		this.idCalendario = idCalendario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	
	
	
	@Override
	public void add(CalendarioComponent calendarioComponent)
	{
		this.calendario.add(calendarioComponent);
	}

	@Override
	public void remove(CalendarioComponent calendarioComponent)
	{
		this.calendario.remove(calendarioComponent);
	}
	
//	@Override
//    public void print(){
//		//new ArrayJson
//       for(CalendarioComponent comp : calendario)
//        {
//    	   	//comp.toJson
//    	  //arrayJson.add
//            System.out.println(comp.toString());
//        }
//       //return arrayJson
//    }

	
	@Override
    public JSONObject toJson() {
		
		JSONObject calJson = new JSONObject();
		
		calJson.put("idCalendario", idCalendario);
		calJson.put("tipo", tipo);
		calJson.put("anno", anno);
		calJson.put("dataInizio", dataInizio);
		calJson.put("dataFine", dataFine);
		calJson.put("semestre", semestre);
		
		JSONArray componentsArray = new JSONArray();
		
		for(CalendarioComponent comp : calendario)
        {
            componentsArray.add(comp.toJson());
        }
		
		calJson.put(tipo, componentsArray);
		return calJson;	
	
	}

    
    

}
