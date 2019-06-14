package it.unisalento.se.saw.restapi;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

import it.unisalento.se.saw.IService.IToolService;
import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.ToolDTO;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class ToolRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	// @Spy
	@InjectMocks
	private ToolRestController toolController;

	
	@Mock
	private IToolService toolService;
	
	@InjectMocks
	ToolDTO toolDTO;

	@Before
	public void setUp() {
		
		// mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userServiceMock)).build();
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(toolController).build();
		
	}
	
	@Test /*scelgo la funzione da testare*/
	public void findAll() throws Exception {
		
		List<Tool> toolList = new ArrayList<Tool>();
		Tool oneTool = new Tool();
		Tool twotool = new Tool();
		oneTool.setNome("pc");
		twotool.setNome("wifi");
	
		toolList.add(oneTool);
		toolList.add(twotool);
		
		when(toolService.findAll()).thenReturn(toolList); /*impostare la condizione di test*/
				
		mockMvc.perform(get("/tool/findAll"))
			.andExpect(status().isOk())  /*mi aspetto una risposta http "OK"*/
			.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(toolService.findAll()).thenThrow(Exception.class); /*impostare la condizione di test*/
		
		mockMvc.perform(get("/tool/findAll"))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test /*scelgo la funzione da testare*/
	public void save() throws Exception {
		
		Tool oneTool = new Tool();
		oneTool.setNome("pc");
		oneTool.setDescrizione("descrizione");
		
		JSONObject body = new JSONObject();		
		body.put("nome", oneTool.getNome());
		body.put("descrizione", oneTool.getDescrizione());
		
		when(toolService.save(oneTool)).thenReturn(oneTool); /*impostare la condizione di test*/
				
		mockMvc.perform(post("/tool/newTool").contentType(APPLICATION_JSON_UTF8).content(body.toJSONString())
				.accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isCreated());
		
     	when(toolService.save(new Tool())).thenThrow(Exception.class);
//		when(toolService.save(new Tool())).thenReturn(new Tool());
		
		
		mockMvc.perform(post("/tool/newTool").contentType(APPLICATION_JSON_UTF8).content("")
				.accept(APPLICATION_JSON_UTF8))
		.andExpect(status().isBadRequest());
		
//		when(toolService.findAll()).thenThrow(Exception.class); /*impostare la condizione di test*/
//		
//		mockMvc.perform(get("/tool/findAll"))
//		.andExpect(status().isBadRequest());
		
	}
	
}
