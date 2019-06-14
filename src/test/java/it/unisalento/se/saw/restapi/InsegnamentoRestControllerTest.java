package it.unisalento.se.saw.restapi;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import it.unisalento.se.saw.IService.IInsegnamentoService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.User;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class InsegnamentoRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private InsegnamentoRestController insegnamentoController;
	
	@Mock
	private IInsegnamentoService insegnamentoService;
	
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(insegnamentoController).build();
		
	}
	
	@Test
	public void findAll() throws Exception {
		
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
		ins.setAnnoCorso("I");
		
		Insegnamento ins2 = new Insegnamento(c, doc);
		ins2.setIdInsegnamento(2);
		ins2.setNome("FISICA GENERALE I");
		ins2.setCrediti(7);
		ins2.setAnnoCorso("I");
		
		List<Insegnamento> list = new ArrayList<Insegnamento>();
		list.add(ins);
		list.add(ins2);
		
		when(insegnamentoService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/insegnamento/findAll"))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(insegnamentoService.findAll()).thenThrow(Exception.class);
		
		mockMvc.perform(get("/insegnamento/findAll"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getById() throws Exception {
		
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
		ins.setAnnoCorso("I");
		
		when(insegnamentoService.getById(1)).thenReturn(ins);
		
		mockMvc.perform(get("/insegnamento/getById/{idInsegnamento}",1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(insegnamentoService.getById(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/insegnamento/getById/{idInsegnamento}",1))
		.andExpect(status().isBadRequest());
		
		
	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
		
		body.put("nome", "test");
		body.put("crediti", 1);
		body.put("descrizione","test");
		body.put("annoCorso", "test");
		body.put("idDocente", 1);
		body.put("idCorsoDiStudio", 1);
		body.put("professore", "test");
		
		

		
		when(insegnamentoService.save(new Insegnamento())).thenReturn(new Insegnamento());
		
		mockMvc.perform(post("/insegnamento/newInsegnamento")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
	}
	
	@Test
	public void updateById() throws Exception {
		
		JSONObject body = new JSONObject();
		
		body.put("nome", "test");
		body.put("crediti", 1);
		body.put("descrizione","test");
		body.put("annoCorso", "test");
		body.put("idDocente", 1);
		body.put("idCorsoDiStudio", 1);
		
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
		ins.setAnnoCorso("I");
		
		when(insegnamentoService.getById(1)).thenReturn(ins);
		when(insegnamentoService.save(new Insegnamento())).thenReturn(new Insegnamento());
		
		mockMvc.perform(post("/insegnamento/updateById/{idInsegnamento}", 1)
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk());
		
		when(insegnamentoService.getById(1)).thenThrow(Exception.class);
		when(insegnamentoService.save(new Insegnamento())).thenReturn(new Insegnamento());
		
		mockMvc.perform(post("/insegnamento/updateById/{idInsegnamento}", 1)
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isBadRequest());
	}

	@Test
	public void getByIdCorso() throws Exception {
		
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
		ins.setAnnoCorso("I");
		
		Insegnamento ins2 = new Insegnamento(c, doc);
		ins2.setIdInsegnamento(2);
		ins2.setNome("FISICA GENERALE I");
		ins2.setCrediti(7);
		ins2.setAnnoCorso("I");
		
		List<Insegnamento> list = new ArrayList<Insegnamento>();
		list.add(ins);
		list.add(ins2);
		
		when(insegnamentoService.getByIdCorso(1)).thenReturn(list);
		
		mockMvc.perform(get("/insegnamento/getByIdCorso/{id}", 1))
				.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
				.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(ins);
		list.remove(ins2);
		
		when(insegnamentoService.getByIdCorso(1)).thenReturn(list);
		
		mockMvc.perform(get("/insegnamento/getByIdCorso/{id}", 1))
				.andExpect(status().isNotFound()) ;
		    
		
		when(insegnamentoService.getByIdCorso(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/insegnamento/getByIdCorso/{id}", 1))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void getByDocente() throws Exception {
		
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
		ins.setAnnoCorso("I");
		
		Insegnamento ins2 = new Insegnamento(c, doc);
		ins2.setIdInsegnamento(2);
		ins2.setNome("FISICA GENERALE I");
		ins2.setCrediti(7);
		ins2.setAnnoCorso("I");
		
		List<Insegnamento> list = new ArrayList<Insegnamento>();
		list.add(ins);
		list.add(ins2);
		
		when(insegnamentoService.getByIdDocente(12)).thenReturn(list);
		
		mockMvc.perform(get("/insegnamento/getByIdDocente/{idDocente}", 12))
				.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
				.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(ins);
		list.remove(ins2);
		
		when(insegnamentoService.getByIdDocente(12)).thenReturn(list);
		
		mockMvc.perform(get("/insegnamento/getByIdDocente/{idDocente}", 12))
				.andExpect(status().isNotFound()) ;
		    
		
		when(insegnamentoService.getByIdDocente(12)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/insegnamento/getByIdDocente/{idDocente}", 12))
		.andExpect(status().isBadRequest());
		
	}
}
