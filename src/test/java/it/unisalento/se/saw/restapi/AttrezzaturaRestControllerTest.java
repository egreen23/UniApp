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

import it.unisalento.se.saw.IService.IAttrezzaturaService;
import it.unisalento.se.saw.IService.IAulaService;
import it.unisalento.se.saw.domain.Attrezzatura;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Tool;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class AttrezzaturaRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private AttrezzaturaRestController attrezzaturaController;
	
	@Mock
	IAttrezzaturaService attrezzaturaService;
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(attrezzaturaController).build();
		
	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
	
		body.put("idAula",1);
		body.put("idTool", 1);
		
		when(attrezzaturaService.save(new Attrezzatura())).thenReturn(new Attrezzatura());
		
		mockMvc.perform(post("/attrezzatura/newAtt")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
	}

	@Test
	public void deleteAtt() throws Exception {
		
		mockMvc.perform(delete("/attrezzatura/deleteAtt/{id}", 1))
		.andExpect(status().isOk());
				
		mockMvc.perform(delete("/attrezzatura/deleteAtt/{id}", "ciao"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getAttbyIdAula() throws Exception {
		
		Aula first = new Aula();
		
		first.setIdAula(1);
		first.setNome("I1");
		first.setLatitudine(40.334145);
		first.setLongitudine(18.113869);
		first.setEdificio("La Stecca");
		first.setPiano("Secondo Piano");
		
		Tool oneTool = new Tool();
		oneTool.setIdTool(1);
		oneTool.setNome("pc");
		oneTool.setDescrizione("descrizione");
		
		Tool secondTool = new Tool();
		secondTool.setIdTool(2);
		secondTool.setNome("pc");
		secondTool.setDescrizione("descrizione");
		
		Attrezzatura att = new Attrezzatura(first, oneTool);
		Attrezzatura att2 = new Attrezzatura(first, secondTool);
		
		List<Attrezzatura> list = new ArrayList<Attrezzatura>();
		list.add(att2);
		list.add(att);
		
		when(attrezzaturaService.getAttrezzaturabyIdAula(1)).thenReturn(list);
		
		mockMvc.perform(get("/attrezzatura/getAttbyIdAula/{id}",1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(attrezzaturaService.getAttrezzaturabyIdAula(1)).thenThrow(Exception.class);
		
		mockMvc.perform(get("/attrezzatura/getAttbyIdAula/{id}","ciao"))
		.andExpect(status().isBadRequest());

		
	}
}
