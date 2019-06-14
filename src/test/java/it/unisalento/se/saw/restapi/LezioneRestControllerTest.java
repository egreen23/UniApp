package it.unisalento.se.saw.restapi;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import it.unisalento.se.saw.IService.ILezioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.LezioneDTO;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class LezioneRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private LezioneRestController lezioneController;
	
	@Mock
	private ILezioneService lezioneService;
	
	@Mock
	List<LezioneDTO> listDTO = new ArrayList<LezioneDTO>();
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(lezioneController).build();
		
	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
		
		body.put("orarioInizio", "test");
		body.put("orarioFine", "test");
		body.put("idAula",1);
		body.put("idCalendario", 1);
		body.put("idInsegnamento", 1);
		
		when(lezioneService.save(new Lezione())).thenReturn(new Lezione());
		
		mockMvc.perform(post("/lez/newLezione")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
	}
	
	@Test
	public void getLezioneById() throws Exception {
		
		Calendario cal = new Calendario();
		cal.setIdCalendario(1);
		Aula aula = new Aula();
		aula.setIdAula(3);
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		
		Docente doc = new Docente(ufirst);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		ins.setCrediti(12);
		
		Lezione lez = new Lezione(aula, cal, ins);
		
		when(lezioneService.getLezioneById(1)).thenReturn(lez);
		
		mockMvc.perform(get("/lez/getLezioneById/{idLezione}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(lezioneService.getLezioneById(1)).thenReturn(new Lezione());
		
		mockMvc.perform(get("/lez/getLezioneById/{idLezione}", 1))
		.andExpect(status().isBadRequest());

		
	}
	
	@Test
	public void getLezioniByCalendario() throws Exception {
		
		Calendario cal = new Calendario();
		cal.setIdCalendario(1);
		Aula aula = new Aula();
		aula.setIdAula(3);
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		
		Docente doc = new Docente(ufirst);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		ins.setCrediti(12);
		
		Lezione lez = new Lezione(aula, cal, ins);
		lez.setOrarioInizio("08:00");
		lez.setOrarioFine("10:00");
		lez.setData("2019-03-12");
		Lezione lez2 = new Lezione(aula, cal, ins);
		lez2.setOrarioInizio("11:00");
		lez2.setOrarioFine("13:00");
		lez2.setData("2019-03-12");
		
		List<Lezione> list = new ArrayList<Lezione>();
		list.add(lez);
		list.add(lez2);
		
		when(lezioneService.getLezioniByIdCalendario(1)).thenReturn(list);
		
		mockMvc.perform(get("/lez/getLezioniByCalendario/{idCalendario}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(lez);
		list.remove(lez2);

		when(lezioneService.getLezioniByIdCalendario(1)).thenReturn(list);
		
		mockMvc.perform(get("/lez/getLezioniByCalendario/{idCalendario}", 1))
		.andExpect(status().isNotFound());
		
		when(lezioneService.getLezioniByIdCalendario(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/lez/getLezioniByCalendario/{idCalendario}", 1))
		.andExpect(status().isBadRequest());
		


		
	}
	
	@Test
	public void deleteLez() throws Exception {
		
		mockMvc.perform(delete("/lez/deleteLezione/{id}", 1))
		.andExpect(status().isOk());
				
		mockMvc.perform(delete("/lez/deleteLezione/{id}", "ciao"))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void  addLezioni() throws Exception {
		
		
		JSONObject body = new JSONObject();
		
		body.put("orarioInizio", "test");
		body.put("orarioFine", "test");
		body.put("idAula",1);
		body.put("idCalendario", 1);
		body.put("idInsegnamento", 1);
		
		JSONObject body2 = new JSONObject();
		
		body2.put("orarioInizio", "test");
		body2.put("orarioFine", "test");
		body2.put("idAula",1);
		body2.put("idCalendario", 1);
		body2.put("idInsegnamento", 1);
		
		JSONArray json = new JSONArray();
		
		json.add(body);
		json.add(body2);
		
		when(lezioneService.saveAll(new ArrayList<Lezione>())).thenReturn(new ArrayList<Lezione>());
		
		mockMvc.perform(post("/lez/addLezioni")
				.contentType(APPLICATION_JSON_UTF8)
				.content(json.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
		
		
	}
	
	@Test
	public void  getLezioniByIdCalendario() throws Exception {
		
		Calendario cal = new Calendario();
		cal.setIdCalendario(1);
		Aula aula = new Aula();
		aula.setIdAula(3);
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		
		Docente doc = new Docente(ufirst);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		ins.setCrediti(12);
		
		Lezione lez = new Lezione(aula, cal, ins);
		lez.setOrarioInizio("08:00");
		lez.setOrarioFine("10:00");
		lez.setData("2019-03-12");
		Lezione lez2 = new Lezione(aula, cal, ins);
		lez2.setOrarioInizio("11:00");
		lez2.setOrarioFine("13:00");
		lez2.setData("2019-03-12");
		
		List<Lezione> list = new ArrayList<Lezione>();
		list.add(lez);
		list.add(lez2);
		
		when(lezioneService.getLezioniByIdCalendario(1)).thenReturn(list);
		
		mockMvc.perform(get("/lez/getLezioniByIdCalendario/{idCalendario}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(lez);
		list.remove(lez2);

		when(lezioneService.getLezioniByIdCalendario(1)).thenReturn(list);
		
		mockMvc.perform(get("/lez/getLezioniByIdCalendario/{idCalendario}", 1))
		.andExpect(status().isNotFound());
		
		when(lezioneService.getLezioniByIdCalendario(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/lez/getLezioniByIdCalendario/{idCalendario}", 1))
		.andExpect(status().isBadRequest());
		
		
		
		
	}
	
	@Test
	public void update() throws Exception {
		
		JSONObject body = new JSONObject();
		
		body.put("orarioInizio", "test");
		body.put("orarioFine", "test");
		body.put("idAula",1);
		body.put("idCalendario", 1);
		body.put("idInsegnamento", 1);
		body.put("idLezione", 1);
		
		when(lezioneService.save(new Lezione())).thenReturn(new Lezione());
		
		mockMvc.perform(post("/lez/update")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk());
		
		
	}
	
	@Test
	public void getLezioniByIdDocente() throws Exception {
		
		Calendario cal = new Calendario();
		cal.setIdCalendario(1);
		Aula aula = new Aula();
		aula.setIdAula(3);
		aula.setNome("I3");
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		
		Docente doc = new Docente(ufirst);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		ins.setCrediti(12);
		
		Lezione lez = new Lezione(aula, cal, ins);
		lez.setIdLezione(1);
		lez.setOrarioInizio("08:00");
		lez.setOrarioFine("10:00");
		lez.setData("2019-03-12");
		Lezione lez2 = new Lezione(aula, cal, ins);
		lez2.setIdLezione(2);
		lez2.setOrarioInizio("11:00");
		lez2.setOrarioFine("13:00");
		lez2.setData("2019-03-12");
		
		List<Lezione> list = new ArrayList<Lezione>();
		list.add(lez);
		list.add(lez2);
		
		when(lezioneService.getLezioniByIdDocente(12)).thenReturn(list);
		
		mockMvc.perform(get("/lez/getLezioniByIdDocente/{idDocente}", 12))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(lez);
		list.remove(lez2);

		when(lezioneService.getLezioniByIdDocente(12)).thenReturn(list);
		
		mockMvc.perform(get("/lez/getLezioniByIdDocente/{idDocente}", 12))
		.andExpect(status().isNotFound());
		
		when(lezioneService.getLezioniByIdDocente(12)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/lez/getLezioniByIdDocente/{idDocente}", 12))
		.andExpect(status().isBadRequest());
		
		
		
	}
	
	@Test
	public void getCalLezioniByCorso() throws Exception {
		
		Calendario cal = new Calendario();
		cal.setIdCalendario(1);
		Aula aula = new Aula();
		aula.setIdAula(3);
		aula.setNome("I3");
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		
		Docente doc = new Docente(ufirst);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		ins.setCrediti(12);
		
		Lezione lez = new Lezione(aula, cal, ins);
		lez.setIdLezione(1);
		lez.setOrarioInizio("08:00");
		lez.setOrarioFine("10:00");
		lez.setData("2019-03-12");
		Lezione lez2 = new Lezione(aula, cal, ins);
		lez2.setIdLezione(2);
		lez2.setOrarioInizio("11:00");
		lez2.setOrarioFine("13:00");
		lez2.setData("2019-03-12");
		
		List<Lezione> list = new ArrayList<Lezione>();
		list.add(lez);
		list.add(lez2);
		
		when(lezioneService.getCalLezioniByCorso(c.getNome())).thenReturn(list);
		
		mockMvc.perform(get("/lez/getCalLezioniByCorso/{nome}", c.getNome()))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(lez);
		list.remove(lez2);

		when(lezioneService.getCalLezioniByCorso(c.getNome())).thenReturn(list);
		
		mockMvc.perform(get("/lez/getCalLezioniByCorso/{nome}", c.getNome()))
		.andExpect(status().isNotFound());
		
		when(lezioneService.getCalLezioniByCorso(c.getNome())).thenThrow(Exception.class);
		
		mockMvc.perform(get("/lez/getCalLezioniByCorso/{nome}", c.getNome()))
		.andExpect(status().isBadRequest());
		
		
	}
	
	@Test
	public void  getLezioniByIdInsegnamento() throws Exception {
		
		Calendario cal = new Calendario();
		cal.setIdCalendario(1);
		Aula aula = new Aula();
		aula.setIdAula(3);
		aula.setNome("I3");
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		
		Docente doc = new Docente(ufirst);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		ins.setCrediti(12);
		
		Lezione lez = new Lezione(aula, cal, ins);
		lez.setIdLezione(1);
		lez.setOrarioInizio("08:00");
		lez.setOrarioFine("10:00");
		lez.setData("2019-03-12");
		Lezione lez2 = new Lezione(aula, cal, ins);
		lez2.setIdLezione(2);
		lez2.setOrarioInizio("11:00");
		lez2.setOrarioFine("13:00");
		lez2.setData("2019-03-12");
		
		List<Lezione> list = new ArrayList<Lezione>();
		list.add(lez);
		list.add(lez2);
		
		when(lezioneService.getLezioniByIdInsegnamento(1)).thenReturn(list);
		
		mockMvc.perform(get("/lez/getLezioniByIdInsegnamento/{idInsegnamento}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(lez);
		list.remove(lez2);

		when(lezioneService.getLezioniByIdInsegnamento(1)).thenReturn(list);
		
		mockMvc.perform(get("/lez/getLezioniByIdInsegnamento/{idInsegnamento}", 1))
		.andExpect(status().isNoContent());
		
		when(lezioneService.getLezioniByIdInsegnamento(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/lez/getLezioniByIdInsegnamento/{idInsegnamento}", 1))
		.andExpect(status().isBadRequest());
		
	}

}
