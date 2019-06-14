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

import it.unisalento.se.saw.IService.IAulaService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class AulaRestControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private AulaRestController aulaController;
	
	@Mock
	IAulaService aulaService;
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(aulaController).build();
		
	}
	
	@Test
	public void findAll() throws Exception {
		
		Aula first = new Aula();
		Aula second = new Aula();
		
		first.setIdAula(1);
		first.setNome("I1");
		first.setLatitudine(40.334145);
		first.setLongitudine(18.113869);
		first.setEdificio("La Stecca");
		first.setPiano("Secondo Piano");
		
		second.setIdAula(2);
		second.setNome("I2");
		second.setLatitudine(40.334145);
		second.setLongitudine(18.113869);
		second.setEdificio("La Stecca");
		second.setPiano("Secondo Piano");
		
		List<Aula> list = new ArrayList<Aula>();
		list.add(first);
		list.add(second);
		
		when(aulaService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/aula/findAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		
		
		when(aulaService.findAll()).thenThrow(Exception.class);
		
		mockMvc.perform(get("/aula/findAll"))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void getById() throws Exception {
		Aula first = new Aula();
		
		first.setIdAula(1);
		first.setNome("I1");
		first.setLatitudine(40.334145);
		first.setLongitudine(18.113869);
		first.setEdificio("La Stecca");
		first.setPiano("Secondo Piano");
		
		when(aulaService.getById(1)).thenReturn(first);
		
		mockMvc.perform(get("/aula/getById/{idAula}",1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		
		
		when(aulaService.getById(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/aula/getById/{idAula}",1))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getByNomeAula() throws Exception {
		Aula first = new Aula();
		
		first.setIdAula(1);
		first.setNome("I1");
		first.setLatitudine(40.334145);
		first.setLongitudine(18.113869);
		first.setEdificio("La Stecca");
		first.setPiano("Secondo Piano");
		
		when(aulaService.getByNomeAula("I1")).thenReturn(first);
		
		mockMvc.perform(get("/aula/getByNomeAula/{string}",first.getNome()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		
		
		when(aulaService.getByNomeAula("I1")).thenThrow(Exception.class);
		
		mockMvc.perform(get("/aula/getByNomeAula/{string}",first.getNome()))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
		
		
		body.put("nome","test");
		body.put("latitudine", 23);
		body.put("longitudine", 23);
		body.put("edificio", "test");
		body.put("piano", "test");
	

		
		
		when(aulaService.save(new Aula())).thenReturn(new Aula());
		
		mockMvc.perform(post("/aula/newAula")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
		
	}
	
	@Test
	public void updateAulaById() throws Exception {
		
		JSONObject body = new JSONObject();
		
		
		body.put("nome","test");
		body.put("latitudine", 23);
		body.put("longitudine", 23);
		body.put("edificio", "test");
		body.put("piano", "test");
		
		Aula first = new Aula();
		
		first.setIdAula(1);
		first.setNome("I1");
		first.setLatitudine(40.334145);
		first.setLongitudine(18.113869);
		first.setEdificio("La Stecca");
		first.setPiano("Secondo Piano");
		
		when(aulaService.getById(1)).thenReturn(first);
		when(aulaService.save(new Aula())).thenReturn(new Aula());
		
		mockMvc.perform(post("/aula/updateAulaById/{idAula}", 1)
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk());
		
		when(aulaService.getById(1)).thenThrow(Exception.class);
		when(aulaService.save(new Aula())).thenReturn(new Aula());
		
		mockMvc.perform(post("/aula/updateAulaById/{idAula}", 1)
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void deleteAula() throws Exception {
		
		mockMvc.perform(delete("/aula/deleteAula/{id}", 1))
		.andExpect(status().isOk());
				
		mockMvc.perform(delete("/aula/deleteAula/{id}", "ciao"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void search() throws Exception {
		
		Aula first = new Aula();
		
		first.setIdAula(1);
		first.setNome("I1");
		first.setLatitudine(40.334145);
		first.setLongitudine(18.113869);
		first.setEdificio("La Stecca");
		first.setPiano("Secondo Piano");
		
		List<Aula> list = new ArrayList<Aula>();
		list.add(first);
		
		when(aulaService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/aula/searchAula/{term}",first.getNome()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(aulaService.findAll()).thenThrow(Exception.class);
		
		mockMvc.perform(get("/aula/searchAula/{term}","ciao"))
		.andExpect(status().isBadRequest());
	}
}
