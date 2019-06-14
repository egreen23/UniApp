package it.unisalento.se.saw.restapi;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import it.unisalento.se.saw.IService.IStudenteService;
import it.unisalento.se.saw.IService.IToolService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.StudenteDTO;
import it.unisalento.se.saw.dto.ToolDTO;

import javax.json.JsonObject;

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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class StudenteRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	// @Spy
	@InjectMocks
	private StudenteRestController studenteController;

	
	@Mock
	private IStudenteService studenteService;
	
	@InjectMocks
	StudenteDTO studenteDTO;
	
	@InjectMocks
	List<StudenteDTO> listStuDTO = new ArrayList<StudenteDTO>();
	
	
	@Before
	public void setUp() {
		
		// mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userServiceMock)).build();
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(studenteController).build();
		
	}
	
	@Test
	public void  getByMatricola() throws Exception {
		
		int matricola = 10001028;
		Studente stud = new Studente();
		User user = new User();
		user.setIdMatricola(matricola);
		stud.setUser(user);
		stud.setIdStudente(1);
		stud.setAnnoIscrizione("2018/2019");
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(5);
		stud.setCorsoDiStudio(c);
		
		when(studenteService.getByMatricola(matricola)).thenReturn(stud);/*impostare la condizione di test*/
		
		mockMvc.perform(get("/stud/getByMatricola/{matricola}", matricola))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("idMatricola", is(10001028)));
		
		when(studenteService.getByMatricola(matricola)).thenThrow(Exception.class); /*impostare la condizione di test*/

		mockMvc.perform(get("/stud/getByMatricola/{matricola}", matricola))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getAllStudByIdCdS() throws Exception {
		
		CorsoDiStudio corso = new CorsoDiStudio();
		
		User ufirst = new User(10001246);
		User usecond = new User(10008362);
		
		corso.setIdCorsoDiStudio(1);
		corso.setNome("prova");
		corso.setTipo("test");
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Studente first = new Studente(corso, ufirst);
		Studente second = new Studente(corso, usecond);
		
		
		first.setIdStudente(3);
		second.setIdStudente(15);
		first.setAnnoIscrizione( "2017/2018");
		second.setAnnoIscrizione( "2018/2019");
		
		List<Studente> list = new ArrayList<Studente>();
		list.add(first);
		list.add(second);
		
		when(studenteService.getAllStudByIdCdS(1)).thenReturn(list);
		
		mockMvc.perform(get("/stud/getAllStudByIdCdS/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(studenteService.getAllStudByIdCdS(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/stud/getAllStudByIdCdS/{idCdS}", corso.getIdCorsoDiStudio()))
		.andExpect(status().isBadRequest());
		
		
	
	}
	
}
	

