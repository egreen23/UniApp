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

import it.unisalento.se.saw.IService.ICorsoDiStudioService;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.User;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class CorsoDiStudioRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private CorsoDiStudioRestController corsoController;
	
	@Mock
	private ICorsoDiStudioService corsoDiStudioService;
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(corsoController).build();
		
	}
	
	@Test
	public void findAll() throws Exception {
		
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		CorsoDiStudio c2 = new CorsoDiStudio();
		c2.setIdCorsoDiStudio(2);
		c2.setNome("COMPUTER ENGINEERING");
		c2.setTipo("Magistrale");
		c2.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		List<CorsoDiStudio> list = new ArrayList<CorsoDiStudio>();
		list.add(c);
		list.add(c2);
		
		when(corsoDiStudioService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/corso/findAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(corsoDiStudioService.findAll()).thenThrow(Exception.class);
		
		mockMvc.perform(get("/corso/findAll"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getByTipoTutti() throws Exception {
		
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		CorsoDiStudio c2 = new CorsoDiStudio();
		c2.setIdCorsoDiStudio(3);
		c2.setNome("INGEGNERIA CIVILE");
		c2.setTipo("Triennale");
		c2.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		CorsoDiStudio c3 = new CorsoDiStudio();
		c3.setIdCorsoDiStudio(2);
		c3.setNome("COMPUTER ENGINEERING");
		c3.setTipo("Magistrale");
		c3.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		List<CorsoDiStudio> list = new ArrayList<CorsoDiStudio>();
		list.add(c);
		list.add(c2);
		list.add(c3);
		
		when(corsoDiStudioService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/corso/getByTipo/{string}", "Tutti"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		
		
		when(corsoDiStudioService.findAll()).thenThrow(Exception.class);
		
		mockMvc.perform(get("/corso/getByTipo/{string}", "Tutti"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getByTipoSetted() throws Exception {
		
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		CorsoDiStudio c2 = new CorsoDiStudio();
		c2.setIdCorsoDiStudio(3);
		c2.setNome("INGEGNERIA CIVILE");
		c2.setTipo("Triennale");
		c2.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		List<CorsoDiStudio> list = new ArrayList<CorsoDiStudio>();
		list.add(c);
		list.add(c2);
		
		when(corsoDiStudioService.getByTipo("Triennale")).thenReturn(list);
		
		mockMvc.perform(get("/corso/getByTipo/{string}", "Triennale"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(c);
		list.remove(c2);
		
		when(corsoDiStudioService.getByTipo("Triennale")).thenReturn(list);
		
		mockMvc.perform(get("/corso/getByTipo/{string}", "Triennale"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void getById() throws Exception {
		
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		when(corsoDiStudioService.getById(1)).thenReturn(c);
		
		mockMvc.perform(get("/corso/getById/{idCorso}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(corsoDiStudioService.getById(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/corso/getById/{idCorso}", 1))
		.andExpect(status().isBadRequest());
		
		
	}
	
	@Test
	public void save() throws Exception {
				
		JSONObject body = new JSONObject();
		
		body.put("nome", "test");
		body.put("descrizione", "test");
		body.put("tipo","test");
		
		
		when(corsoDiStudioService.save(new CorsoDiStudio())).thenReturn(new CorsoDiStudio());
		
		mockMvc.perform(post("/corso/newCorso")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
	}
	
	@Test
	public void update() throws Exception {
				
		JSONObject body = new JSONObject();
		
		body.put("nome", "test");
		body.put("descrizione", "test");
		body.put("tipo","test");
		
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
		when(corsoDiStudioService.getById(1)).thenReturn(c);
		when(corsoDiStudioService.save(new CorsoDiStudio())).thenReturn(new CorsoDiStudio());
		
		mockMvc.perform(post("/corso/updateById/{idCorso}",1)
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
	}

}
