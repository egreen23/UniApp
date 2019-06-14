package it.unisalento.se.saw.restapi;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.allOf;

import static org.hamcrest.Matchers.hasItem;

import static org.hamcrest.Matchers.hasProperty;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

import it.unisalento.se.saw.IService.IRecensioneMService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.Recensionem;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.RecensioneMDTO;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class RecensioneMRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private RecensioneMRestController recensioneMController;
	
	@Mock
	private IRecensioneMService recensioneMService;
	
	@InjectMocks
	List<RecensioneMDTO> listDTO = new ArrayList<RecensioneMDTO>();

	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(recensioneMController).build();
		
	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
		
		body.put("idStudente", 1);
		body.put("idMateriale", 1);
		body.put("voto", 1);
		body.put("testo", "test");
		body.put("data", "test");
		
		when(recensioneMService.save(new Recensionem())).thenReturn(new Recensionem());
		
		mockMvc.perform(post("/recM/newRecMat")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());

		
	}
	
	@Test
	public void getByMatricolaStudIdInsegnamento() throws Exception {
		
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
		
//		User usecond = new User(20001111);
//		ufirst.setNome("Edoardo");
//		ufirst.setCognome("Mazzeo");
//		ufirst.setEmail("em@docenti.unisalento.it");
//		ufirst.setPassword("WIBTn+/7O35R6bpujmMmNOz5bBxlpbvYX+bhGD9NLDo=$nVYKzBVAdy1lc57bPR8+0AzbmeCOGppykJeQqqJ1iTs=");
//		ufirst.setDataDiNascita( "19/10/1977");
//		ufirst.setIndirizzo("Viale Duca");
//		ufirst.setTelefono("3298364821");
		
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
		
		Recensionem recMat = new Recensionem(1, mat, stud);
		
		recMat.setVoto(2);
		recMat.setTesto("ciao");
		recMat.setData("2018-12-10");
		
		when(recensioneMService.getByMatricolaStudIdInsegIdMaterial(10001028, 1, 1)).thenReturn(recMat);

		mockMvc.perform(get("/recM/getByMatricolaStudIdInsegIdMaterial/{idMatricola}/{idInsegnamento}/{idMateriale}", 10001028, 1, 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(recensioneMService.getByMatricolaStudIdInsegIdMaterial(10001028, 1, 1)).thenThrow(Exception.class);

		mockMvc.perform(get("/recM/getByMatricolaStudIdInsegIdMaterial/{idMatricola}/{idInsegnamento}/{idMateriale}", 10001028, 1, 1))
		.andExpect(status().isNoContent()); /*mi aspetto una risposta http "OK"*/
		
		
	}
	
	@Test
	public void getRecByIdMateriale() throws Exception {
		
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
		
		Recensionem recMat = new Recensionem(1, mat, stud);
		
		recMat.setVoto(2);
		recMat.setTesto("ciao");
		recMat.setData("2018-12-10");
		
		Recensionem recMat2 = new Recensionem(2,mat,stud);
		
		recMat2.setVoto(3);
		recMat2.setTesto("buono");
		recMat2.setData("2018-02-02");
		
		List<Recensionem> list = new ArrayList<Recensionem>();
		list.add(recMat);
		list.add(recMat2);
		
		when(recensioneMService.getRecByIdMateriale(1)).thenReturn(list);

		mockMvc.perform(get("/recM/getRecByIdMateriale/{idMateriale}", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(recMat);
		list.remove(recMat2);
		
		when(recensioneMService.getRecByIdMateriale(1)).thenReturn(list);
		
		mockMvc.perform(get("/recM/getRecByIdMateriale/{idMateriale}", 1))
		.andExpect(status().isNoContent()); /*mi aspetto una risposta http "OK"*/
		
		when(recensioneMService.getRecByIdMateriale(1)).thenThrow(Exception.class);

		mockMvc.perform(get("/recM/getRecByIdMateriale/{idMateriale}", 1))
		.andExpect(status().isBadRequest()); /*mi aspetto una risposta http "OK"*/
		
		
	}

}
