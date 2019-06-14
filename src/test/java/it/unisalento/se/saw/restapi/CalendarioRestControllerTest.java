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
import it.unisalento.se.saw.IService.ICalendarioService;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.dto.composite.CalendarioComponent;
import it.unisalento.se.saw.dto.composite.CalendarioDTOComp;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class CalendarioRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private CalendarioRestController calendarioController;
	
	@Mock
	ICalendarioService calendarioService;
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(calendarioController).build();
		
	}
	
	@Test
	public void getAll() throws Exception {
		
		CorsoDiStudio c2 = new CorsoDiStudio();
		c2.setIdCorsoDiStudio(3);
		c2.setNome("INGEGNERIA CIVILE");
		c2.setTipo("Triennale");
		c2.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
		
//		CorsoDiStudio c3 = new CorsoDiStudio();
//		c3.setIdCorsoDiStudio(2);
//		c3.setNome("COMPUTER ENGINEERING");
//		c3.setTipo("Magistrale");
//		c3.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");
//		
		Calendario first = new Calendario(c2);
		Calendario second = new Calendario(c2);
		
		first.setIdCalendario(1);
		first.setTipo("Lezione");
		first.setTipo("2018/2019");
		first.setDataInizio("2019-03-01");
		first.setDataFine("2019-06-01");
		first.setSemestre("Primo");
		
		second.setIdCalendario(1);
		second.setTipo("Lezione");
		second.setTipo("2018/2019");
		second.setDataInizio("2019-06-01");
		second.setDataFine("2019-11-01");
		second.setSemestre("Secondo");
		
		List<Calendario> list = new ArrayList<Calendario>();
		list.add(first);
		list.add(second);
		
		when(calendarioService.getCalendari()).thenReturn(list);
		
		mockMvc.perform(get("/cal/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		
		
		when(calendarioService.getCalendari()).thenThrow(Exception.class);
		
		mockMvc.perform(get("/cal/getAll"))
		.andExpect(status().isBadRequest());

	}
	
	@Test
	public void getCalendarioById() throws Exception {
		
		CalendarioComponent cal = new CalendarioDTOComp(1, "Lezione", "2018/2019", "2019-03-01", "2019-06-01", "Primo", 3, "INGEGNERIA CIVILE");
		
		when(calendarioService.getCalendarioById(1)).thenReturn(cal);
		
		mockMvc.perform(get("/cal/getCalendarioById/{idCalendario}",1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		
		
		when(calendarioService.getCalendarioById(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/cal/getCalendarioById/{idCalendario}",1))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
		
		
		body.put("tipo","test");
		body.put("anno", "test");
		body.put("dataInizio", "2017-05-05");
		body.put("dataFine", "2018-04-04");
		body.put("semestre", "test");
		body.put("idCds", 1);

		
		
		when(calendarioService.save(new Calendario())).thenReturn(new Calendario());
		
		mockMvc.perform(post("/cal/newCalendario")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
	}
	
	@Test
	public void deleteCal() throws Exception {
		
		mockMvc.perform(delete("/cal/deleteCalendario/{id}", 1))
		.andExpect(status().isOk());
				
		mockMvc.perform(delete("/cal/deleteCalendario/{id}", "ciao"))
		.andExpect(status().isBadRequest());
		
	}

}
