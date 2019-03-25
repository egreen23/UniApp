package it.unisalento.se.saw.dto.composite;

import it.unisalento.se.saw.domain.Lezione;
import net.minidev.json.JSONObject;

public class LezioneDTOComp extends CalendarioComponent {
	
    private Integer idLezione;
    private String orarioInizio;
    private String orarioFine;
    private String data;
    
    private String nomeAula;
    private String nomeInsegnamento;
    private String nomeDocente;
    private String cognomeDocente;
    private int crediti;
    
    private String nomeCorso;
    private String tipoCorso;
    
//    private int idAula;
//    private int idCalendario;
//    private int idInsegnamento;
    
    
    
    
    
	public LezioneDTOComp(Integer idLezione, String orarioInizio, String orarioFine, String data, String nomeAula, String nomeInsegnamento,
			String nomeDocente, String cognomeDocente, int crediti, String nomeCorso, String tipoCorso) {
		super();
		this.idLezione = idLezione;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.data = data;
		this.nomeAula = nomeAula;
		this.nomeInsegnamento = nomeInsegnamento;
		this.nomeDocente = nomeDocente;
		this.cognomeDocente = cognomeDocente;
		this.crediti = crediti;
		this.nomeCorso = nomeCorso;
		this.tipoCorso = tipoCorso;
	}



	public Integer getIdLezione() {
		return idLezione;
	}
	public void setIdLezione(Integer idLezione) {
		this.idLezione = idLezione;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
	
//	public int getIdAula() {
//		return idAula;
//	}
//	
//	public void setIdAula(int idAula) {
//		this.idAula = idAula;
//	}
//	
//	public int getIdCalendario() {
//		return idCalendario;
//	}
//	
//	public void setIdCalendario(int idCalendario) {
//		this.idCalendario = idCalendario;
//	}
//	
//	public int getIdInsegnamento() {
//		return idInsegnamento;
//	}
//
//
//	public void setIdInsegnamento(int idInsegnamento) {
//		this.idInsegnamento = idInsegnamento;
//	}


	@Override
    public JSONObject toJson() {
		
		JSONObject lezJson = new JSONObject();
		
		lezJson.put("idLezione", idLezione);
		lezJson.put("orarioInizio",orarioInizio);
		lezJson.put("orarioFine", orarioFine);
		lezJson.put("data", data);
		lezJson.put("nomeAula", nomeAula);
		lezJson.put("nomeInsegnamento", nomeInsegnamento);
		lezJson.put("nomeDocente", nomeDocente);
		lezJson.put("cognomeDocente", cognomeDocente);
		lezJson.put("crediti", crediti);
		lezJson.put("nomeCorso", nomeCorso);
		lezJson.put("tipoCorso", tipoCorso);
		
		return lezJson;	
	
	}
	
	
//	@Override
//	public String toString() {
//		return "LezioneDTOComp [idLezione=" + idLezione + ", orarioInizio=" + orarioInizio + ", orarioFine=" + orarioFine
//				+ ", data=" + data + "]";
//	}
//
//

    public JSONObject toJson_2() throws ParseException {

    		JSONObject lezJson = new JSONObject();

    		String title = nomeInsegnamento +"  "+ nomeDocente;
    		String start,end;

    		String day = data.substring(0, 2);
    		int giorno = Integer.parseInt(day);
    		String month = data.substring(3, 5);
    		int mese = Integer.parseInt(month);
    		String year = data.substring(6, 10);
    		int anno = Integer.parseInt(year);


    		String hour = orarioInizio.substring(0, 2);
    		int ore = Integer.parseInt(hour);
    		System.out.println(hour);
    		System.out.println(ore);
    		String minute = orarioInizio.substring(3,5);
    		int minuti = Integer.parseInt(minute);
    		System.out.println(minute);
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

    		/*TimeZone tz = TimeZone.getTimeZone("UTC");
    		DateFormat df = new SimpleDateFormat("dd-MM-yyyy 'at' kk:mm"); // Quoted "Z" to indicate UTC, no timezone offset
    		df.setTimeZone(tz);
    		Date kibba = df.parse(fulldatastart);
    		System.out.println(fulldatastart);
    		Date kibba2 = df.parse(fulldataend);
    		System.out.println(fulldataend);
    		System.out.println("ora : " + kibba.getHours());
    		System.out.println("min : " + kibba.getMinutes());
    		System.out.println("anno : "+ kibba.getYear());*/

    		//Date kibba = new Date();
    		//Date kibba2 = new Date();


    		lezJson.put("title", title);
    		lezJson.put("start", start);
    		lezJson.put("end", end);

    		return lezJson;

    	}


    //	@Override
    //	public String toString() {
    //		return "LezioneDTO [idLezione=" + idLezione + ", orarioInizio=" + orarioInizio + ", orarioFine=" + orarioFine
    //				+ ", data=" + data + "]";
    //	}
    //
    //



    }

	
    

}
