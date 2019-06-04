package it.unisalento.se.saw.dto.composite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.minidev.json.JSONObject;

public class EsameDTOComp extends CalendarioComponent {
	
    private Integer idEsame;
    private String data;
    private String orarioInizio;
    private String orarioFine;
    
    private String nomeAula;
    private String nomeInsegnamento;
    private String nomeDocente;
    private String cognomeDocente;
    private int crediti;
    private String docente;

    private String nomeCorso;
    private String tipoCorso;
	private int IdInsegnamento;
	
	private int IdCalendario;
	private int IdAula;

    
	public EsameDTOComp(Integer idEsame, String data, String orarioInizio, String orarioFine, String nomeAula, String nomeInsegnamento, 
		    String nomeDocente, String cognomeDocente, int crediti, String nomeCorso, String tipoCorso, int idins, int idcal, int idaula) {
		super();
		this.idEsame = idEsame;
		this.data = data;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		
		this.nomeAula = nomeAula;
		this.nomeInsegnamento = nomeInsegnamento;
		this.nomeDocente = nomeDocente;
		this.cognomeDocente = cognomeDocente;
		this.crediti = crediti;
		this.nomeCorso = nomeCorso;
		this.tipoCorso = tipoCorso;
		this.IdInsegnamento = idins;
		this.IdCalendario = idcal;
		this.IdAula = idaula;

		
	}
	
	
	public Integer getIdEsame() {
		return idEsame;
	}
	public void setIdEsame(Integer idEsame) {
		this.idEsame = idEsame;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getOrarioInizio() {
		return orarioInizio;
	}
	public void setOrarioInizio(String orarioInizio) {
		this.orarioInizio = orarioInizio;
	}
	public String getOrarioFine() {
		return orarioFine;
	}
	public void setOrarioFine(String orarioFine) {
		this.orarioFine = orarioFine;
	}
		
	public String getNomeAula() {
		return nomeAula;
	}

	public void setNomeAula(String nomeAula) {
		this.nomeAula = nomeAula;
	}
	
	public String getNomeInsegnamento() {
		return nomeInsegnamento;
	}

	public void setNomeInsegnamento(String nomeInsegnamento) {
		this.nomeInsegnamento = nomeInsegnamento;
	}

	public String getNomeDocente() {
		return nomeDocente;
	}
	
	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}
	
	public String getCognomeDocente() {
		return cognomeDocente;
	}
	
	public void setCognomeDocente(String cognomeDocente) {
		this.cognomeDocente = cognomeDocente;
	}
	
	public int getCrediti() {
		return crediti;
	}
	
	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}
	


	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public String getTipoCorso() {
		return tipoCorso;
	}

	public void setTipoCorso(String tipoCorso) {
		this.tipoCorso = tipoCorso;
	}
	
	public String getDocente() {
		return docente;
	}

	public void setDocente(String docente) {
		this.docente = docente;
	}
	
	public int getIdInsegnamento() {
		return IdInsegnamento;
	}


	public void setIdInsegnamento(int idInsegnamento) {
		IdInsegnamento = idInsegnamento;
	}
	
	public int getIdAula() {
		return IdAula;
	}


	public void setIdAula(int idAula) {
		IdAula = idAula;
	}

	public int getIdCalendario() {
		return IdCalendario;
	}


	public void setIdCalendario(int idCalendario) {
		IdCalendario = idCalendario;
	}



	@Override
    public JSONObject toJson() {
		
		JSONObject esameJson = new JSONObject();
		
		esameJson.put("id", idEsame);
		esameJson.put("data", data);
		esameJson.put("orarioInizio", orarioInizio);
		esameJson.put("orarioFine", orarioFine);
		esameJson.put("nomeAula", nomeAula);
		esameJson.put("nomeInsegnamento", nomeInsegnamento);
		esameJson.put("nomeDocente", nomeDocente);
		esameJson.put("cognomeDocente", cognomeDocente);
		esameJson.put("crediti", crediti);
		esameJson.put("nomeCorso", nomeCorso);
		esameJson.put("tipoCorso", tipoCorso);
		esameJson.put("docente", docente);
		esameJson.put("idins", IdInsegnamento);
		esameJson.put("idcal", IdCalendario);


		
		return esameJson;	
	
	}
	
	// vedere se si può usare qualche desgin pattern per non avere questo metodo uguale 2 volte
    public JSONObject toJson_2() throws ParseException {

		JSONObject esameJson = new JSONObject();

		String title = nomeInsegnamento +"  "+ docente +"  "+ nomeAula;
		String start,end;

		String day = data.substring(8, 10);
		int giorno = Integer.parseInt(day);
		String month = data.substring(5, 7);
		int mese = Integer.parseInt(month);
		String year = data.substring(0, 4);
		int anno = Integer.parseInt(year);



		String hour = orarioInizio.substring(0, 2);
		System.out.println(hour);

		int ore = Integer.parseInt(hour);
		System.out.println(ore);
		String minute = orarioInizio.substring(3,5);
		System.out.println(minute);

		int minuti = Integer.parseInt(minute);
		System.out.println(minuti);

		String hour2 = orarioFine.substring(0, 2);
		int ore2 = Integer.parseInt(hour2);
		String minute2 = orarioFine.substring(3,5);
		int minuti2 = Integer.parseInt(minute2);

		// Input
		Calendar calendarstart = Calendar.getInstance();

		calendarstart.set(anno, mese-1, giorno, ore, minuti, 0);
		calendarstart.set(Calendar.MILLISECOND, 0);
		Date datestart = calendarstart.getTime();

		Calendar calendarend = Calendar.getInstance();

		calendarend.set(anno, mese-1, giorno, ore2, minuti2, 0);
		calendarend.set(Calendar.MILLISECOND, 0);
		Date dateend = calendarend.getTime();

		// Conversion
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		sdf.setTimeZone(TimeZone.getTimeZone("CET"));
		start = sdf.format(datestart);
		end = sdf.format(dateend);


		esameJson.put("title", title);
		esameJson.put("start", start);
		esameJson.put("end", end);
		esameJson.put("id", idEsame);

		return esameJson;

	}
    
    
}
