package it.unisalento.se.saw.restapi;

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

import it.unisalento.se.saw.IService.IEsameService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Esame;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.User;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class EsameRestControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private EsameRestController esameController;
	
	@Mock
	private IEsameService esameService;
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(esameController).build();
		
	}
	
	@Test
	public void save() throws Exception {
	
		JSONObject body = new JSONObject();
		
		body.put("orarioInizio", "test");
		body.put("orarioFine", "test");
		body.put("idAula",1);
		body.put("idCalendario", 1);
		body.put("idInsegnamento", 1);
		
		when(esameService.save(new Esame())).thenReturn(new Esame());
		
		mockMvc.perform(post("/esame/newEsame")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
	}
	
	@Test
	public void getEsameById() throws Exception {
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
		
		Esame esame = new Esame(aula, cal, ins);
		
		when(esameService.getEsameById(1)).thenReturn(esame);
		
		mockMvc.perform(get("/esame/getEsameById/{idEsame}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(esameService.getEsameById(	1)).thenReturn(new Esame());
		
		mockMvc.perform(get("/esame/getEsameById/{idEsame}", 1))
		.andExpect(status().isBadRequest());

	}
	
	@Test
	public void getEsameByCalendario() throws Exception {
		
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
		
		Esame esame = new Esame(aula, cal, ins);
		esame.setOrarioInizio("08:00");
		esame.setOrarioFine("10:00");
		esame.setData("2019-03-12");
		Esame esame2 = new Esame(aula, cal, ins);
		esame2.setOrarioInizio("11:00");
		esame2.setOrarioFine("13:00");
		esame2.setData("2019-03-12");
		
		List<Esame> list = new ArrayList<Esame>();
		list.add(esame);
		list.add(esame2);
		
		when(esameService.getEsameByIdCalendario(1)).thenReturn(list);
		
		mockMvc.perform(get("/esame/getEsameByCalendario/{idCalendario}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(esame);
		list.remove(esame2);

		when(esameService.getEsameByIdCalendario(1)).thenReturn(list);
		
		mockMvc.perform(get("/esame/getEsameByCalendario/{idCalendario}", 1))
		.andExpect(status().isNotFound());
		
		when(esameService.getEsameByIdCalendario(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/esame/getEsameByCalendario/{idCalendario}", 1))
		.andExpect(status().isBadRequest());
		


		
	}
	
	@Test
	public void deleteEsame() throws Exception {
		
		mockMvc.perform(delete("/esame/deleteEsame/{id}", 1))
		.andExpect(status().isOk());
				
		mockMvc.perform(delete("/esame/deleteEsame/{id}", "ciao"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void  addEsami() throws Exception {
		
		
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
		
		when(esameService.saveAll(new ArrayList<Esame>())).thenReturn(new ArrayList<Esame>());
		
		mockMvc.perform(post("/esame/addEsami")
				.contentType(APPLICATION_JSON_UTF8)
				.content(json.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
		
		
	}
	
	@Test
	public void  getEsameByIdCalendario() throws Exception {
		
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
		
		Esame esame = new Esame(aula, cal, ins);
		esame.setOrarioInizio("08:00");
		esame.setOrarioFine("10:00");
		esame.setData("2019-03-12");
		Esame esame2 = new Esame(aula, cal, ins);
		esame2.setOrarioInizio("11:00");
		esame2.setOrarioFine("13:00");
		esame2.setData("2019-03-12");
		
		List<Esame> list = new ArrayList<Esame>();
		list.add(esame);
		list.add(esame2);
		
		when(esameService.getEsameByIdCalendario(1)).thenReturn(list);
		
		mockMvc.perform(get("/esame/getEsameByIdCalendario/{idCalendario}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(esame);
		list.remove(esame2);

		when(esameService.getEsameByIdCalendario(1)).thenReturn(list);
		
		mockMvc.perform(get("/esame/getEsameByIdCalendario/{idCalendario}", 1))
		.andExpect(status().isNotFound());
		
		when(esameService.getEsameByIdCalendario(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/esame/getEsameByIdCalendario/{idCalendario}", 1))
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
		body.put("idEsame", 1);
		
		when(esameService.save(new Esame())).thenReturn(new Esame());
		
		mockMvc.perform(post("/esame/update")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk());
		
		
	}
	
	
}
