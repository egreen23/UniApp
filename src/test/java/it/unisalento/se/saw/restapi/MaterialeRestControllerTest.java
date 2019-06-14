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


import it.unisalento.se.saw.IService.IMaterialeService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class MaterialeRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private MaterialeRestController materialeController;
	
	@Mock
	private IMaterialeService materialeService;
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(materialeController).build();
		
	}
	
	

	@Test
	public void getById() throws Exception {
		
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(3);
		c.setNome("ELECTRONIC TECHNOLOGIES");
		c.setTipo("Magistrale");
		c.setDescrizione("Il Corso di Laurea Magistrale in Communication Engineering and Electronic Technologies approfondisce le tecnologie abilitanti e le applicazioni del trattamento del segnale (anche a radiofrequenza) nonché gli aspetti dell''elettronica circuitale, delle tecnologie e dei dispositivi di particolare interesse per il settore delle telecomunicazioni.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		
		Studente stud = new Studente(c, ufirst);
		Docente doc = new Docente(ufirst);
		stud.setIdStudente(1);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		
		Materiale mat = new Materiale(ins);
		mat.setIdMateriale(1);
		mat.setNome("Slide ");
		mat.setUrl("materiale/dp_java.pdf");
		
		when(materialeService.getById(1)).thenReturn(mat);
		
		mockMvc.perform(get("/materiale/getById/{idMateriale}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(materialeService.getById(1)).thenReturn(new Materiale());
		
		mockMvc.perform(get("/materiale/getById/{idMateriale}", 1))
		.andExpect(status().isBadRequest());

	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
		
		body.put("idInsegnamento", 1);
		body.put("nome", 1);
		body.put("url", 1);
		body.put("data", "test");
		
		when(materialeService.save(new Materiale())).thenReturn(new Materiale());
		
		mockMvc.perform(post("/materiale/newMat")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());

		
	}
	
	@Test
	public void getbyIdInsegnamento() throws Exception {
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(3);
		c.setNome("ELECTRONIC TECHNOLOGIES");
		c.setTipo("Magistrale");
		c.setDescrizione("Il Corso di Laurea Magistrale in Communication Engineering and Electronic Technologies approfondisce le tecnologie abilitanti e le applicazioni del trattamento del segnale (anche a radiofrequenza) nonché gli aspetti dell''elettronica circuitale, delle tecnologie e dei dispositivi di particolare interesse per il settore delle telecomunicazioni.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		
		Studente stud = new Studente(c, ufirst);
		Docente doc = new Docente(ufirst);
		stud.setIdStudente(1);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		
		Materiale mat = new Materiale(ins);
		mat.setIdMateriale(1);
		mat.setNome("Slide ");
		mat.setUrl("materiale/dp_java.pdf");
		mat.setData("2019-01-01");
		
		Materiale mat2 = new Materiale(ins);
		mat2.setIdMateriale(14);
		mat2.setNome("estim1.pdf ");
		mat2.setUrl("materiale/estim1.pdf");
		mat2.setData("2019-06-05");

		List<Materiale> list = new ArrayList<Materiale>();
		list.add(mat);
		list.add(mat2);
		
		when(materialeService.getMatByIdInsegnamento(1)).thenReturn(list);
		
		mockMvc.perform(get("/materiale/getMatByIdInsegnamento/{idInsegnamento}", 1))
				.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
				.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(mat);
		list.remove(mat2);
		
		when(materialeService.getMatByIdInsegnamento(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/materiale/getMatByIdInsegnamento/{idInsegnamento}", 1))
		.andExpect(status().isBadRequest());
		

		
	}
}
