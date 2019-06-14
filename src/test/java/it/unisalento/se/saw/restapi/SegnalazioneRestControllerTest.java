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

import it.unisalento.se.saw.IService.ISegnalazioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Recensionem;
import it.unisalento.se.saw.domain.Segnalazione;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.SegnalazioneDTO;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class SegnalazioneRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private SegnalazioneRestController segnalazioneController;
	
	@Mock
	private ISegnalazioneService segnalazioneService;
	
	@InjectMocks
	List<SegnalazioneDTO> listDTO = new ArrayList<SegnalazioneDTO>();
	

	
	
	@Before
	public void setUp() {
		
		// mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userServiceMock)).build();
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(segnalazioneController).build();
		
	}
	
	@Test
	public void getById() throws Exception {
		
		Aula aula = new Aula();
		User ufirst = new User(10001246);
		
		aula.setIdAula(1);
		aula.setNome("test");
		
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Segreteria seg = new Segreteria(ufirst);
		Docente doc = new Docente(ufirst);
		seg.setIdSegreteria(1);
		doc.setIdDocente(1);
		
		Segnalazione segnal = new Segnalazione(aula, doc, seg);
		segnal.setIdSegnalazione(1);
		segnal.setData("2017-01-01");
		segnal.setStato("test");
		segnal.setTesto("test");
		segnal.setNota("test");
		segnal.setTitolo("titolo");
		
		when(segnalazioneService.getById(1)).thenReturn(segnal);
		
		mockMvc.perform(get("/segnala/getById/{idSegnalazione}", 1))
		.andExpect(status().isOk());
		
		when(segnalazioneService.getById(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/segnala/getById/{idSegnalazione}", 1))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
		
		body.put("idDocente", 1);
		body.put("idSegreteria", 1);
		body.put("idAula", 1);
		body.put("testo", "test");
		body.put("stato", "stato");
		body.put("data", "test");
		body.put("titolo", "ciao");
		
		when(segnalazioneService.save(new Segnalazione())).thenReturn(new Segnalazione());

		mockMvc.perform(post("/segnala/newSegnalazione")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
		// NON VA MIA IN BAD REQUEST
		
//		JSONObject body2 = new JSONObject();
//		
//		Segnalazione segnull = null;
//		
//		when(segnalazioneSerevice.save(segnull)).thenThrow(Exception.class);
//		
//		mockMvc.perform(post("/segnala/newSegnalazione")
//				.contentType(APPLICATION_JSON_UTF8)
//				.content(body2.toJSONString()).accept(APPLICATION_JSON_UTF8))
//		        .andExpect(status().isBadRequest());

	
	}
	
	@Test
	public void update() throws Exception {
		
		Aula aula = new Aula();
		User ufirst = new User(10001246);
		
		aula.setIdAula(1);
		aula.setNome("test");
		
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Segreteria seg = new Segreteria(ufirst);
		Docente doc = new Docente(ufirst);
		seg.setIdSegreteria(1);
		doc.setIdDocente(1);
		
		Segnalazione segnal = new Segnalazione(aula, doc, seg);
		segnal.setIdSegnalazione(1);
		segnal.setData("test");
		segnal.setStato("test");
		segnal.setTesto("test");
		segnal.setNota("test");
		segnal.setTitolo("titolo");
		
		JSONObject body = new JSONObject();
		
		body.put("nota", "nota");
		body.put("stato", "stato");
		
		when(segnalazioneService.getById(1)).thenReturn(segnal);
		when(segnalazioneService.save(new Segnalazione())).thenReturn(new Segnalazione());

		mockMvc.perform(post("/segnala/updateSegnal/{idSegnalazione}", 1)
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
		
		when(segnalazioneService.getById(1)).thenThrow(Exception.class);
		when(segnalazioneService.save(new Segnalazione())).thenReturn(new Segnalazione());

		mockMvc.perform(post("/segnala/updateSegnal/{idSegnalazione}", 1)
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void getbyIdDocente() throws Exception {
		
		Aula aula = new Aula();
		User ufirst = new User(10001246);
		
		aula.setIdAula(1);
		aula.setNome("test");
		
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Segreteria seg = new Segreteria(ufirst);
		Docente doc = new Docente(ufirst);
		seg.setIdSegreteria(1);
		doc.setIdDocente(1);
		
		Segnalazione segnal = new Segnalazione(aula, doc, seg);
		Segnalazione segnal2 = new Segnalazione(aula, doc, seg);
		
		segnal.setIdSegnalazione(1);
		segnal.setData("2017-01-01");
		segnal.setStato("test");
		segnal.setTesto("test");
		segnal.setNota("test");
		segnal.setTitolo("titolo");
		segnal2.setIdSegnalazione(2);
		segnal2.setStato("test");
		segnal2.setTesto("test");
		segnal2.setNota("test");
		segnal2.setTitolo("titolo");
		segnal2.setData("2018-01-01");
		
		List<Segnalazione> list = new ArrayList<Segnalazione>();
		list.add(segnal);
		list.add(segnal2);
		
		when(segnalazioneService.getByidDocente(1)).thenReturn(list);
		
		mockMvc.perform(get("/segnala/getbyIdDocente/{id}", 1))
		.andExpect(status().isOk());
	
		
		
		
		when(segnalazioneService.getByidDocente(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/segnala/getbyIdDocente/{id}", 1))
		.andExpect(status().isBadRequest());

		
		
		
	}
	
	@Test
	public void filterDoc() throws Exception {
		
		Aula aula = new Aula();
		User ufirst = new User(10001246);
		
		aula.setIdAula(1);
		aula.setNome("test");
		
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Segreteria seg = new Segreteria(ufirst);
		Docente doc = new Docente(ufirst);
		seg.setIdSegreteria(1);
		doc.setIdDocente(1);
		
		Segnalazione segnal = new Segnalazione(aula, doc, seg);
		Segnalazione segnal2 = new Segnalazione(aula, doc, seg);
		
		segnal.setIdSegnalazione(1);
		segnal.setData("2017-01-01");
		segnal.setStato("Aperto");
		segnal.setTesto("test");
		segnal.setNota("test");
		segnal.setTitolo("titolo");
		segnal2.setIdSegnalazione(2);
		segnal2.setStato("Aperto");
		segnal2.setTesto("test");
		segnal2.setNota("test");
		segnal2.setTitolo("titolo");
		segnal2.setData("2018-01-01");
		
		List<Segnalazione> list = new ArrayList<Segnalazione>();
		list.add(segnal);
		list.add(segnal2);
		
		when(segnalazioneService.getByidDocente(1)).thenReturn(list);

		
		mockMvc.perform(get("/segnala/filterDoc/{id}/{stato}", 1, segnal.getStato()))
		.andExpect(status().isOk());
		
		when(segnalazioneService.getByidDocente(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/segnala/filterDoc/{id}/{stato}", 1, segnal.getStato()))
		.andExpect(status().isBadRequest());

		
		
	}
	
	@Test
	public void getbyIdAula() throws Exception {
		
		Aula aula = new Aula();
		User ufirst = new User(10001246);
		
		aula.setIdAula(1);
		aula.setNome("test");
		
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Segreteria seg = new Segreteria(ufirst);
		Docente doc = new Docente(ufirst);
		seg.setIdSegreteria(1);
		doc.setIdDocente(1);
		
		Segnalazione segnal = new Segnalazione(aula, doc, seg);
		Segnalazione segnal2 = new Segnalazione(aula, doc, seg);
		
		segnal.setIdSegnalazione(1);
		segnal.setData("2017-01-01");
		segnal.setStato("Aperto");
		segnal.setTesto("test");
		segnal.setNota("test");
		segnal.setTitolo("titolo");
		segnal2.setIdSegnalazione(2);
		segnal2.setStato("Aperto");
		segnal2.setTesto("test");
		segnal2.setNota("test");
		segnal2.setTitolo("titolo");
		segnal2.setData("2018-01-01");
		
		List<Segnalazione> list = new ArrayList<Segnalazione>();
		list.add(segnal);
		list.add(segnal2);
		
		when(segnalazioneService.getByidAula(1)).thenReturn(list);
		
		mockMvc.perform(get("/segnala/getbyIdAula/{id}", 1))
		.andExpect(status().isOk());
		
		when(segnalazioneService.getByidAula(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/segnala/getbyIdAula/{id}", 1))
		.andExpect(status().isBadRequest());

		
		
	}
	
	@Test
	public void getbyIdSegr() throws Exception {
		Aula aula = new Aula();
		User ufirst = new User(10001246);
		
		aula.setIdAula(1);
		aula.setNome("test");
		
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Segreteria seg = new Segreteria(ufirst);
		Docente doc = new Docente(ufirst);
		seg.setIdSegreteria(1);
		doc.setIdDocente(1);
		
		Segnalazione segnal = new Segnalazione(aula, doc, seg);
		Segnalazione segnal2 = new Segnalazione(aula, doc, seg);
		
		segnal.setIdSegnalazione(1);
		segnal.setData("2017-01-01");
		segnal.setStato("Aperto");
		segnal.setTesto("test");
		segnal.setNota("test");
		segnal.setTitolo("titolo");
		segnal2.setIdSegnalazione(2);
		segnal2.setStato("Aperto");
		segnal2.setTesto("test");
		segnal2.setNota("test");
		segnal2.setTitolo("titolo");
		segnal2.setData("2018-01-01");
		
		List<Segnalazione> list = new ArrayList<Segnalazione>();
		list.add(segnal);
		list.add(segnal2);
		
		when(segnalazioneService.getByidSegr(1)).thenReturn(list);
		
		mockMvc.perform(get("/segnala/getbyIdSegr/{id}", 1))
		.andExpect(status().isOk());
		
		when(segnalazioneService.getByidSegr(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/segnala/getbyIdSegr/{id}", 1))
		.andExpect(status().isBadRequest());

	}
	
	
	@Test
	public void filterSegr() throws Exception {
		Aula aula = new Aula();
		User ufirst = new User(10001246);
		
		aula.setIdAula(1);
		aula.setNome("test");
		
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Segreteria seg = new Segreteria(ufirst);
		Docente doc = new Docente(ufirst);
		seg.setIdSegreteria(1);
		doc.setIdDocente(1);
		
		Segnalazione segnal = new Segnalazione(aula, doc, seg);
		Segnalazione segnal2 = new Segnalazione(aula, doc, seg);
		
		segnal.setIdSegnalazione(1);
		segnal.setData("2017-01-01");
		segnal.setStato("Aperto");
		segnal.setTesto("test");
		segnal.setNota("test");
		segnal.setTitolo("titolo");
		segnal2.setIdSegnalazione(2);
		segnal2.setStato("Aperto");
		segnal2.setTesto("test");
		segnal2.setNota("test");
		segnal2.setTitolo("titolo");
		segnal2.setData("2018-01-01");
		
		List<Segnalazione> list = new ArrayList<Segnalazione>();
		list.add(segnal);
		list.add(segnal2);
		
		when(segnalazioneService.getByidSegr(1)).thenReturn(list);
		
		mockMvc.perform(get("/segnala/filterSegr/{id}/{stato}", 1, segnal.getStato()))
		.andExpect(status().isOk());
		
		when(segnalazioneService.getByidSegr(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/segnala/filterSegr/{id}/{stato}", 1, segnal.getStato()))
		.andExpect(status().isBadRequest());

	}
	

}
