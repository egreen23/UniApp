package it.unisalento.se.saw.restapi;

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

import java.net.PasswordAuthentication;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.util.PasswordUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	// @Spy
	@InjectMocks
	private UserRestController userController;

	
	@Mock
	private IUserService userService;
	
	@InjectMocks
	UserDTO userDTO;
	
	
	@Before
	public void setUp() {
		
		// mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userServiceMock)).build();
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
	}
	
	@Test /*scelgo la funzione da testare*/
	public void findAll() throws Exception {
	
		List<User> userList = new ArrayList<User>();
		userList.add(new User());
		userList.add(new User());
		
		when(userService.findAll()).thenReturn(userList); /*impostare la condizione di test*/
				
		mockMvc.perform(get("/user/findAll"))
			.andExpect(status().isOk())  /*mi aspetto una risposta http "OK"*/
			.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(userService.findAll()).thenThrow(Exception.class); /*impostare la condizione di test*/
		
		mockMvc.perform(get("/user/findAll"))
		.andExpect(status().isBadRequest());

		verify(userService, times(2)).findAll(); /*verifica che con una chiamata al metodo le asserzioni siano corrette*/
		verifyNoMoreInteractions(userService);	
	}
	
	@Test
	public void getById() throws Exception {
		
		int studmat = 10001028;
		int docmat = 20001111;
		int segmat = 30001123;
		
		Studente stud = new Studente();
		CorsoDiStudio corso = new CorsoDiStudio();
		Docente doc = new Docente();
		Segreteria seg = new Segreteria();
		
		Studente studnull = null;
		Docente docnull = null;
		Segreteria segnull = null;
		
		User userStud = new User();
		User userDoc = new User();
		User userSeg = new User();
		userStud.setIdMatricola(studmat);
		userDoc.setIdMatricola(docmat);
		userSeg.setIdMatricola(segmat);

		corso.setIdCorsoDiStudio(5);

		stud.setUser(userStud);
		stud.setIdStudente(1);
//		stud.setAnnoIscrizione("2018/2019");
		stud.setCorsoDiStudio(corso);
		
		doc.setUser(userDoc);
		doc.setIdDocente(12);
		
		seg.setUser(userSeg);
		seg.setIdSegreteria(1);
		

	
		
		when(userService.getById(studmat)).thenReturn(userStud); /*impostare la condizione di test*/
		when(userService.isStudente(studmat)).thenReturn(stud);
		when(userService.isDocente(studmat)).thenReturn(docnull);
		when(userService.isSegreteria(studmat)).thenReturn(segnull);

		
		mockMvc.perform(get("/user/getById/{idMatricola}", studmat))
		.andExpect(status().isOk())  /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("tipo", is("studente")));
		
		when(userService.getById(docmat)).thenReturn(userDoc); /*impostare la condizione di test*/
		when(userService.isStudente(docmat)).thenReturn(studnull);
		when(userService.isDocente(docmat)).thenReturn(doc);
		when(userService.isSegreteria(docmat)).thenReturn(segnull);


		
		mockMvc.perform(get("/user/getById/{idMatricola}", docmat))
		.andExpect(status().isOk())  /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("tipo", is("docente")));
		
		when(userService.getById(segmat)).thenReturn(userSeg); /*impostare la condizione di test*/
		when(userService.isStudente(segmat)).thenReturn(studnull);
		when(userService.isDocente(segmat)).thenReturn(docnull);
		when(userService.isSegreteria(segmat)).thenReturn(seg);


		
		mockMvc.perform(get("/user/getById/{idMatricola}", segmat))
		.andExpect(status().isOk())  /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("tipo", is("segreteria")));
		
		
		when(userService.getById(studmat)).thenThrow(Exception.class); /*impostare la condizione di test*/

		mockMvc.perform(get("/user/getById/10001028"))
		.andExpect(status().isBadRequest());
		
		
	
		
		// when(userService.isStudente(matricola)).thenReturn(stud);
		
		// assertEquals("studente", userDTO.getTipo());
	
		
//		when(userService.isStudente(matricola)).thenReturn(stud); /*impostare la condizione di test*/
//		
//		mockMvc.perform(get("/user/getById/10001028"))
//		.andExpect(jsonPath("tipo", is("studente")));
		
//		when(userService.isStudente(matricola)).thenReturn(new Studente());
//		mockMvc.perform(get("/user/getById/10001028"))
		
		// verify(userService).isStudente(matricola).equals(stud);
	}
	
	@Test
	public void login() throws Exception {
		
		String password = "0000";
		String passwordfake = "1111";
		
		Studente stud = new Studente();
		CorsoDiStudio corso = new CorsoDiStudio();
		Docente doc = new Docente();
		Segreteria seg = new Segreteria();
		
		Studente studnull = null;
		Docente docnull = null;
		Segreteria segnull = null;
		User usernull = null;
		
		User userStud = new User(10001246);

		userStud.setNome("Roberto");
		userStud.setCognome("Campa");
		userStud.setEmail("rc@studenti.unisalento.it");
		userStud.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		userStud.setDataDiNascita( "14/08/1997");
		userStud.setIndirizzo("Viale Eritrea");
		userStud.setTelefono("3472937482");
		
		User userDoc = new User(20001111);
		
		userDoc.setNome("Edoardo");
		userDoc.setCognome("Mazzeo");
		userDoc.setEmail("em@docenti.unisalento.it");
		userDoc.setPassword("WIBTn+/7O35R6bpujmMmNOz5bBxlpbvYX+bhGD9NLDo=$nVYKzBVAdy1lc57bPR8+0AzbmeCOGppykJeQqqJ1iTs=");
		userDoc.setDataDiNascita( "19/10/1977");
		userDoc.setIndirizzo("Viale Duca");
		userDoc.setTelefono("3472937482");
		
		User userSeg = new User(30001123);
		
		userSeg.setNome("Edoardo");
		userSeg.setCognome("Mazzeo");
		userSeg.setEmail("em@docenti.unisalento.it");
		userSeg.setPassword("WIBTn+/7O35R6bpujmMmNOz5bBxlpbvYX+bhGD9NLDo=$nVYKzBVAdy1lc57bPR8+0AzbmeCOGppykJeQqqJ1iTs=");
		userSeg.setDataDiNascita( "19/10/1977");
		userSeg.setIndirizzo("Viale Duca");
		userSeg.setTelefono("3472937482");
		

		corso.setIdCorsoDiStudio(5);

		stud.setUser(userStud);
		stud.setIdStudente(1);
		stud.setCorsoDiStudio(corso);
		
		doc.setUser(userDoc);
		doc.setIdDocente(12);
		
		seg.setUser(userSeg);
		seg.setIdSegreteria(1);
		
		when(userService.getById(10001246)).thenReturn(userStud); /*impostare la condizione di test*/
		when(userService.isStudente(10001246)).thenReturn(stud);
		when(userService.isDocente(10001246)).thenReturn(docnull);
		when(userService.isSegreteria(10001246)).thenReturn(segnull);
		
		
		mockMvc.perform(post("/user/login/{idMatricola}/{password}", 10001246, password).contentType(APPLICATION_JSON_UTF8)
				.content(" ").accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
		
		when(userService.getById(20001111)).thenReturn(userDoc); /*impostare la condizione di test*/
		when(userService.isStudente(20001111)).thenReturn(studnull);
		when(userService.isDocente(20001111)).thenReturn(doc);
		when(userService.isSegreteria(20001111)).thenReturn(segnull);
		
		mockMvc.perform(post("/user/login/{idMatricola}/{password}", 20001111, password).contentType(APPLICATION_JSON_UTF8)
				.content(" ").accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
		
		
		when(userService.getById(30001123)).thenReturn(userSeg); /*impostare la condizione di test*/
		when(userService.isStudente(30001123)).thenReturn(studnull);
		when(userService.isDocente(30001123)).thenReturn(docnull);
		when(userService.isSegreteria(30001123)).thenReturn(seg);
		
		mockMvc.perform(post("/user/login/{idMatricola}/{password}", 30001123, password).contentType(APPLICATION_JSON_UTF8)
				.content(" ").accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
		
		when(userService.getById(30001123)).thenReturn(userSeg); /*impostare la condizione di test*/
		when(userService.isStudente(30001123)).thenReturn(studnull);
		when(userService.isDocente(30001123)).thenReturn(docnull);
		when(userService.isSegreteria(30001123)).thenReturn(seg);
		
		mockMvc.perform(post("/user/login/{idMatricola}/{password}", 30001123, passwordfake).contentType(APPLICATION_JSON_UTF8)
				.content(" ").accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isUnauthorized());
		

		when(userService.getById(30001123)).thenReturn(usernull); /*impostare la condizione di test*/
	
		
		mockMvc.perform(post("/user/login/{idMatricola}/{password}", 30001123, password).contentType(APPLICATION_JSON_UTF8)
				.content(" ").accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isUnauthorized());

		
	}
}
