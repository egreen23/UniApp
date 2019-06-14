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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import it.unisalento.se.saw.IService.IDocenteService;
import it.unisalento.se.saw.IService.IUserService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Esame;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.StudenteDTO;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class DocenteRestControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private DocenteRestController docenteController;
	
	@Mock
	private IDocenteService docenteService;
	
	@Mock
	private IUserService userService;
	
	@InjectMocks
	DocenteDTO docenteDTO;
	
	@InjectMocks
	List<DocenteDTO> listDocDTO = new ArrayList<DocenteDTO>();
	
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(docenteController).build();
		
	}
	
	@Test
	public void findAll() throws Exception {
		
		User ufirst = new User(20001111);
		User usecond = new User(20009134);
		
		ufirst.setNome("Edoardo");
		ufirst.setCognome("Mazzeo");
		ufirst.setEmail("em@docenti.unisalento.it");
		ufirst.setPassword("WIBTn+/7O35R6bpujmMmNOz5bBxlpbvYX+bhGD9NLDo=$nVYKzBVAdy1lc57bPR8+0AzbmeCOGppykJeQqqJ1iTs=");
		ufirst.setDataDiNascita( "19/10/1977");
		ufirst.setIndirizzo("Viale Duca");
		ufirst.setTelefono("3472937482");
		
		usecond.setNome("Marco");
		usecond.setCognome("Marzo");
		usecond.setEmail("mm@docenti.unisalento.it");
		usecond.setPassword("9GCnsqQCYtnqEpEadUHc6xel7EefDazwphccWthRWi0=$hYJ/fHKv/XQZhQESKziDfodQZ/36ERtyKAS8NTXldnw=");
		usecond.setDataDiNascita( "02/02/1964");
		usecond.setIndirizzo("Via Duca");
		usecond.setTelefono("3472918738");
		
		Docente first = new Docente( ufirst);
		Docente second = new Docente( usecond);
		first.setIdDocente(12);
		second.setIdDocente(2);
		
		List<Docente> list = new ArrayList<Docente>();
		list.add(first);
		list.add(second);
		
		when(docenteService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/doc/findAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(docenteService.findAll()).thenThrow(Exception.class);
		
		mockMvc.perform(get("/doc/findAll"))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void  getByMatricola() throws Exception {
		
		User ufirst = new User(20001111);
		
		ufirst.setNome("Edoardo");
		ufirst.setCognome("Mazzeo");
		ufirst.setEmail("em@docenti.unisalento.it");
		ufirst.setPassword("WIBTn+/7O35R6bpujmMmNOz5bBxlpbvYX+bhGD9NLDo=$nVYKzBVAdy1lc57bPR8+0AzbmeCOGppykJeQqqJ1iTs=");
		ufirst.setDataDiNascita( "19/10/1977");
		ufirst.setIndirizzo("Viale Duca");
		ufirst.setTelefono("3472937482");
		
		Docente first = new Docente( ufirst);
		
		first.setIdDocente(12);
		
		
		when(docenteService.logDocente(20001111)).thenReturn(first);/*impostare la condizione di test*/
		
		mockMvc.perform(get("/doc/getByMatricola/{matricola}", 20001111))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(docenteService.logDocente(20001111)).thenThrow(Exception.class); /*impostare la condizione di test*/

		mockMvc.perform(get("/doc/getByMatricola/{matricola}", 20001111))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void save() throws Exception {
				
		JSONObject body = new JSONObject();
		
		body.put("nome", "test");
		body.put("cogonome", "test");
		body.put("email","test");
		body.put("password", "test");
		body.put("dataDiNascita", "test");
		body.put("indirizzo", "test");
		body.put("telefono","test");
		body.put("idMatricola", 1);
		
		when(docenteService.save(new Docente())).thenReturn(new Docente());
		when(userService.save(new User())).thenReturn(new User());
		
		mockMvc.perform(post("/doc/newDocente")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
	}
	
	@Test
	public void updateDocByMatricola() throws Exception {
		
		User ufirst = new User(20001111);
		
		ufirst.setNome("Edoardo");
		ufirst.setCognome("Mazzeo");
		ufirst.setEmail("em@docenti.unisalento.it");
		ufirst.setPassword("WIBTn+/7O35R6bpujmMmNOz5bBxlpbvYX+bhGD9NLDo=$nVYKzBVAdy1lc57bPR8+0AzbmeCOGppykJeQqqJ1iTs=");
		ufirst.setDataDiNascita( "19/10/1977");
		ufirst.setIndirizzo("Viale Duca");
		ufirst.setTelefono("3472937482");
		
		Docente first = new Docente( ufirst);
		
		first.setIdDocente(12);
		
		JSONObject body = new JSONObject();
		
		body.put("nome", "test");
		body.put("cogonome", "test");
		body.put("email","test");
		body.put("password", "test");
		body.put("dataDiNascita", "test");
		body.put("indirizzo", "test");
		body.put("telefono","test");
		
		when(docenteService.save(new Docente())).thenReturn(new Docente());
		when(userService.save(new User())).thenReturn(new User());
		when(userService.getById(20001111)).thenReturn(ufirst);
		when(docenteService.logDocente(20001111)).thenReturn(first);
		
		mockMvc.perform(post("/doc/updateDocByMatricola/{idMatricola}", 20001111)
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk());
	}
	
	@Test
	public void searchDoc() throws Exception {
		
		User ufirst = new User(20001111);
		User usecond = new User(20009134);
		
		ufirst.setNome("Edoardo");
		ufirst.setCognome("Mazzeo");
		ufirst.setEmail("em@docenti.unisalento.it");
		ufirst.setPassword("WIBTn+/7O35R6bpujmMmNOz5bBxlpbvYX+bhGD9NLDo=$nVYKzBVAdy1lc57bPR8+0AzbmeCOGppykJeQqqJ1iTs=");
		ufirst.setDataDiNascita( "19/10/1977");
		ufirst.setIndirizzo("Viale Duca");
		ufirst.setTelefono("3472937482");
		
		usecond.setNome("Marco");
		usecond.setCognome("Marzo");
		usecond.setEmail("mm@docenti.unisalento.it");
		usecond.setPassword("9GCnsqQCYtnqEpEadUHc6xel7EefDazwphccWthRWi0=$hYJ/fHKv/XQZhQESKziDfodQZ/36ERtyKAS8NTXldnw=");
		usecond.setDataDiNascita( "02/02/1964");
		usecond.setIndirizzo("Via Duca");
		usecond.setTelefono("3472918738");
		
		Docente first = new Docente( ufirst);
		Docente second = new Docente( usecond);
		first.setIdDocente(12);
		second.setIdDocente(2);
		
		List<Docente> list = new ArrayList<Docente>();
		list.add(first);
		list.add(second);
		
		when(docenteService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/doc/searchDoc/{term}", "Marco Marzo"))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(docenteService.findAll()).thenThrow(Exception.class); /*impostare la condizione di test*/

		mockMvc.perform(get("/doc/searchDoc/{term}", 20001111))
		.andExpect(status().isBadRequest());
	
	}
}
