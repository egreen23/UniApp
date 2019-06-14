package it.unisalento.se.saw.restapi;

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

import it.unisalento.se.saw.IService.ISegreteriaService;
import it.unisalento.se.saw.domain.Segreteria;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.SegreteriaDTO;

@RunWith(MockitoJUnitRunner.class)
public class SegreteriaRestControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private SegreteriaRestController segreteriaController;
	
	@Mock
	private ISegreteriaService segreteriaService;
	
	@InjectMocks
	SegreteriaDTO segDTO;
	
	@InjectMocks
	List<SegreteriaDTO> listDTO = new ArrayList<SegreteriaDTO>();
	
	@Before
	public void setUp() {
		
		// mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userServiceMock)).build();
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(segreteriaController).build();
		
	}
	
	@Test
	public void findAll() throws Exception {
		
		List<Segreteria> list = new ArrayList<Segreteria>();
		
		User ufirst = new User(10001246);
		User usecond = new User(10008362);
		
		ufirst.setNome("Roberto");
		ufirst.setCognome("Campa");
		ufirst.setEmail("rc@studenti.unisalento.it");
		ufirst.setPassword("fccApYh5a2QVPH4Zm4fLVutCGYTTq8YwcJSnojdTw2k=$/LM6tmTK+UAkHEoCUeCbsxjaZFbIOhB5hZPyPlSvtdA=");
		ufirst.setDataDiNascita( "14/08/1997");
		ufirst.setIndirizzo("Viale Eritrea");
		ufirst.setTelefono("3472937482");
		
		Segreteria first = new Segreteria(ufirst);
		Segreteria second = new Segreteria(usecond);
		
		first.setIdSegreteria(1);
		second.setIdSegreteria(2);
		
		list.add(first);
		list.add(second);
		
		when(segreteriaService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/seg/findAll"))
		.andExpect(status().isOk());
		
		when(segreteriaService.findAll()).thenThrow(Exception.class);
		
		mockMvc.perform(get("/seg/findAll"))
		.andExpect(status().isBadRequest());
	}
	
	

	

}
