package it.unisalento.se.saw.restapi;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import it.unisalento.se.saw.IService.IRecensioneLService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.Recensionel;
import it.unisalento.se.saw.domain.Recensionem;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.User;
import net.minidev.json.JSONObject;

@RunWith(MockitoJUnitRunner.class)
public class RecensioneLRestControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private RecensioneLRestController recensioneLController;
	
	@Mock
	private IRecensioneLService recensioneLService;
	
	@Before
	public void setUp() {
		
	
		/*set up fisso test*/
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(recensioneLController).build();
		
	}
	
	@Test
	public void save() throws Exception {
		
		JSONObject body = new JSONObject();
		
		body.put("idStudente", 1);
		body.put("idLezione", 1);
		body.put("voto", 1);
		body.put("testo", "test");
		body.put("data", "test");
		
		when(recensioneLService.save(new Recensionel())).thenReturn(new Recensionel());
		
		mockMvc.perform(post("/recL/newRecLez")
				.contentType(APPLICATION_JSON_UTF8)
				.content(body.toJSONString()).accept(APPLICATION_JSON_UTF8))
		        .andExpect(status().isCreated());
		
	}
	
	@Test
	public void getByMatricolaStudIdInsegnamento() throws Exception {
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(1);
		c.setNome("INGEGNERIA DELL'INFORMAZIONE");
		c.setTipo("Triennale");
		c.setDescrizione("Il corso di Laurea in Ingegneria dell’Informazione prevede un percorso di studio che mira a fornire una adeguata conoscenza sia nelle materie di base come le matematiche e le fisiche sia nell’ambito dell’Ingegneria Informatica, Elettronica, delle Telecomunicazioni ed Automatica.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
//		User usecond = new User(20001111);
//		ufirst.setNome("Edoardo");
//		ufirst.setCognome("Mazzeo");
//		ufirst.setEmail("em@docenti.unisalento.it");
//		ufirst.setPassword("WIBTn+/7O35R6bpujmMmNOz5bBxlpbvYX+bhGD9NLDo=$nVYKzBVAdy1lc57bPR8+0AzbmeCOGppykJeQqqJ1iTs=");
//		ufirst.setDataDiNascita( "19/10/1977");
//		ufirst.setIndirizzo("Viale Duca");
//		ufirst.setTelefono("3298364821");
		
		Studente stud = new Studente(c, ufirst);
		Docente doc = new Docente(ufirst);
		stud.setIdStudente(1);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		
		Aula aula = new Aula();
		aula.setIdAula(3);
		
		Calendario cal = new Calendario();
		cal.setIdCalendario(1);
		
		Lezione lez = new Lezione(aula, cal,ins);
		lez.setIdLezione(1);
		lez.setData("2019-03-12");
		
		Recensionel recLez = new Recensionel(lez, stud);
		recLez.setIdrecensionel(1);
		
		recLez.setVoto(1);
		recLez.setTesto("ciao");
		recLez.setData("'2019-01-02'");
		
		when(recensioneLService.getByMatricolaStudIdInsegIdLez(10001028, 1, 1)).thenReturn(recLez);

		mockMvc.perform(get("/recL/getByMatricolaStudIdInsegIdLez/{idMatricola}/{idInsegnamento}/{idLezione}", 10001028, 1, 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		when(recensioneLService.getByMatricolaStudIdInsegIdLez(10001028, 1, 1)).thenThrow(Exception.class);

		mockMvc.perform(get("/recL/getByMatricolaStudIdInsegIdLez/{idMatricola}/{idInsegnamento}/{idLezione}", 10001028, 1, 1))
		.andExpect(status().isNoContent()); /*mi aspetto una risposta http "OK"*/
		
		
	}
	
	@Test
	public void getRecByIdLezione() throws Exception {
		
		User ufirst = new User(10001028);
		CorsoDiStudio c = new CorsoDiStudio();
		c.setIdCorsoDiStudio(3);
		c.setNome("ELECTRONIC TECHNOLOGIES");
		c.setTipo("Magistrale");
		c.setDescrizione("Il Corso di Laurea Magistrale in Communication Engineering and Electronic Technologies approfondisce le tecnologie abilitanti e le applicazioni del trattamento del segnale (anche a radiofrequenza) nonché gli aspetti dell''elettronica circuitale, delle tecnologie e dei dispositivi di particolare interesse per il settore delle telecomunicazioni.");

		ufirst.setNome("Michele");
		ufirst.setCognome("Scippa");
		ufirst.setEmail("ms@studenti.unisalento.it");
		ufirst.setPassword("mLlDFYDIzMpcgJJv4oXPhUTM/MfjQ6GqY5hPCsZGhcw=$mGl9W5khG3oWXUgFn+lsKiLUb5qb2Ilkyr7r8Fpk+NU=");
		ufirst.setDataDiNascita( "05/10/1993");
		ufirst.setIndirizzo("Viale Marconi");
		ufirst.setTelefono("3332984792");
		
		
		Studente stud = new Studente(c, ufirst);
		Docente doc = new Docente(ufirst);
		stud.setIdStudente(1);
		doc.setIdDocente(12);
		
		Insegnamento ins = new Insegnamento(c, doc);
		ins.setIdInsegnamento(1);
		ins.setNome("ANALISI MATEMATICA I");
		
		Aula aula = new Aula();
		aula.setIdAula(3);
		
		Calendario cal = new Calendario();
		cal.setIdCalendario(1);
		
		Lezione lez = new Lezione(aula, cal,ins);
		lez.setIdLezione(1);
		lez.setData("2019-03-12");
		
		Recensionel recLez = new Recensionel(lez, stud);
		recLez.setIdrecensionel(1);
		
		recLez.setVoto(1);
		recLez.setTesto("ciao");
		recLez.setData("'2019-01-02'");
		
		Recensionel recLez2 = new Recensionel(lez,stud);
		
		recLez2.setVoto(3);
		recLez2.setTesto("buono");
		recLez2.setData("2018-02-02");
		
		List<Recensionel> list = new ArrayList<Recensionel>();
		list.add(recLez);
		list.add(recLez2);
		
		when(recensioneLService.getRecLByIdLezione(1)).thenReturn(list);

		mockMvc.perform(get("/recL/getRecLByIdLezione/{idLezione}/", 1))
		.andExpect(status().isOk()) /*mi aspetto una risposta http "OK"*/
		.andExpect(content().contentType(APPLICATION_JSON_UTF8));
		
		list.remove(recLez);
		list.remove(recLez2);
		
		when(recensioneLService.getRecLByIdLezione(1)).thenReturn(list);
		
		mockMvc.perform(get("/recL/getRecLByIdLezione/{idLezione}", 1))
		.andExpect(status().isNoContent()); /*mi aspetto una risposta http "OK"*/
		
		when(recensioneLService.getRecLByIdLezione(1)).thenThrow(Exception.class);

		mockMvc.perform(get("/recL/getRecLByIdLezione/{idLezione}", 1))
		.andExpect(status().isBadRequest()); /*mi aspetto una risposta http "OK"*/
		
		
	}
}
